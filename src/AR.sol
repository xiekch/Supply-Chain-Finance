pragma solidity ^0.4.22;

contract AR {
    address owner;
    struct Receipt {
        address from;
        address to;
        uint amount;
        // bool verifiedByBank;
        bool isValid;
    }

    struct Company {
        string name;
        uint balance;
        uint rate;
        uint toPay;
        uint toReceive;
        bool isBank;
        uint receiptId;
        // receipts received
        // mapping(uint => Receipt)receipts;
        bool isValid;
    }

    mapping(address => Company) public companies;
    mapping(uint => Receipt) public receipts;

    modifier onlyOwner {
        require(msg.sender == owner,"");
        _;
    }

    modifier onlyBank {
        require(companies[msg.sender].isBank,"");
        _;
    }

    constructor(string name, uint balance, uint rate) public {
        owner = msg.sender;
        companies[owner] = Company(name, balance, rate, 0, 0, true,0, true);
    }

    function newCompany(address com, string name, uint balance, uint rate) public onlyOwner returns (bool success){
        require(companies[com].isValid == false,"");
        companies[com] = Company(name, balance, rate, 0, 0, false,0, true);
        return true;
    }

    function newBank(address bank, string name, uint balance, uint rate) public onlyOwner returns (bool success){
        require(companies[bank].isValid == false,"");
        companies[bank] = Company(name, balance, rate, 0, 0, true,0, true);
        return true;
    }

    // send `to` a reciept after a deal
    function deal(address to, uint amount) public returns (uint id) {
        require(companies[to].isValid,"");
        address from = msg.sender;
        require(companies[from].isValid,"");

        companies[from].toPay += amount;
        companies[to].toReceive += amount;
        companies[from].receiptId += 1;
        id = companies[from].receiptId;
        receipts[id] = Receipt(from, to, amount, true);
    }

    // verify a receipt by a bank
    // function verify(uint receiptId) public onlyBank {
    //     require(receiptId < receipts.length,"");
    //     receipts[receiptId].verifiedByBank = true;
    // }

    // transfer account receivable from `from` to `to` with the `receiptId`
    function transfer(address to, uint receiptId, uint amount) public returns (uint id) {
        require(receiptId < receipts.length,"");
        require(companies[to].isValid,"");
        address from = msg.sender;
        require(companies[from].isValid,"");

        require(receipts[receiptId].to == to,"");
        require(receipts[receiptId].amount >= amount,"");
        address upper = receipts[receiptId].from;
        receipts[receiptId].amount -= amount;

        companies[from].toReceive -= amount;
        companies[to].toReceive += amount;
        receipts.push(Receipt(upper, to, amount, true));
        id = receipts.length - 1;
    }

    // finace from bank
    function financing(address bank, uint receiptId, uint amount)public returns(uint id) {
        require(receiptId < receipts.length,"");
        address to = msg.sender;
        require(companies[to].isValid,"");
        require(companies[bank].isBank,"");
        require(receipts[receiptId].amount >= amount,"");
        require(companies[receipts[receiptId].from].rate > 1,"");
        address from = receipts[receiptId].from;
        companies[to].toReceive -= amount;
        companies[bank].toReceive += amount;

        receipts[receiptId].amount -= amount;
        companies[bank].balance -= amount;
        companies[to].balance += amount;
        receipts.push(Receipt(from, bank, amount, true));
        // a new receipt to bank
        id = receipts.length - 1;
    }

    // pay a receipt
    function pay(uint receiptId)public returns (bool success){
        require(receiptId < receipts.length,"");
        address from = msg.sender;
        address to = receipts[receiptId].to;
        require(from == receipts[receiptId].from,"");
        if(companies[from].balance < receipts[receiptId].amount)
            return false;
        companies[from].balance -= receipts[receiptId].amount;
        companies[to].balance += receipts[receiptId].amount;
        receipts[receiptId].amount = 0;
        companies[from].toPay -= receipts[receiptId].amount;
        companies[to].toReceive -= receipts[receiptId].amount;
        return true;
    }

}