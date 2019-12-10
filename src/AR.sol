pragma solidity ^0.4.22;

contract AR {
    address owner;
    struct Receipt {
        address from;
        address to;
        uint amount;
        bool isValid;
    }

    struct Company {
        string name;
        uint balance;
        uint rate;
        uint toPay;
        uint toReceive;
        bool isBank;
        // receipts received
        // mapping(uint => Receipt)receipts;
        bool isValid;
    }

    uint receiptsSize;
    mapping(address => Company) public companies;
    mapping(uint => Receipt) public receipts;

    modifier onlyOwner {
        require(msg.sender == owner,"only owner");
        _;
    }

    modifier onlyBank {
        require(companies[msg.sender].isBank,"only bank");
        _;
    }

    constructor(string name, uint balance, uint rate) public{
        owner = msg.sender;
        companies[owner] = Company(name, balance, rate, 0, 0, true, true);
        receiptsSize = 0;
    }

    function newCompany(address com, string name, uint balance, uint rate) public onlyOwner returns (bool success){
        require(companies[com].isValid == false,"existed company");
        companies[com] = Company(name, balance, rate, 0, 0, false, true);
        return true;
    }

    function newBank(address bank, string name, uint balance, uint rate) public onlyOwner returns (bool success){
        require(companies[bank].isValid == false,"existed company");
        companies[bank] = Company(name, balance, rate, 0, 0, true, true);
        return true;
    }

    // send `to` a reciept after a deal
    function deal(address to, uint amount) public returns (bool success, uint id) {
        require(companies[to].isValid,"invalid company");
        address from = msg.sender;
        require(companies[from].isValid,"invalid company");

        companies[from].toPay += amount;
        companies[to].toReceive += amount;
        receiptsSize += 1;
        id = receiptsSize;
        receipts[id] = Receipt(from, to, amount, true);
        success = true;
    }

    // transfer account receivable from `from` to `to` with the `receiptId`
    function transfer(address to, uint receiptId, uint amount) public returns (bool success, uint id) {
        require(receiptId < receiptsSize,"invalid receipt");
        require(companies[to].isValid,"invalid company");
        address from = msg.sender;
        require(companies[from].isValid,"invalid company");

        require(receipts[receiptId].to == to,"");
        require(receipts[receiptId].amount >= amount,"invalid amount");
        address upper = receipts[receiptId].from;
        receipts[receiptId].amount -= amount;

        companies[from].toReceive -= amount;
        companies[to].toReceive += amount;
        receiptsSize += 1;
        id = receiptsSize;
        receipts[id] = Receipt(upper, to, amount, true);
        success = true;
    }

    // finace from bank
    function financing(address bank, uint receiptId, uint amount)public returns(bool success, uint id) {
        require(receiptId < receiptsSize,"");
        address to = msg.sender;
        require(companies[to].isValid,"invalid company");
        require(companies[bank].isBank,"is not bank");
        require(receipts[receiptId].amount >= amount,"invalid amount");
        require(companies[receipts[receiptId].from].rate > 1,"");
        address from = receipts[receiptId].from;
        companies[to].toReceive -= amount;
        companies[bank].toReceive += amount;

        receipts[receiptId].amount -= amount;
        companies[bank].balance -= amount;
        companies[to].balance += amount;
        receiptsSize += 1;
        id = receiptsSize;
        // a new receipt to bank
        receipts[id] = Receipt(from, bank, amount, true);
        success = true;
    }

    // pay a receipt
    function pay(uint receiptId)public returns (bool success){
        require(receiptId < receiptsSize,"invalid receipt");
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