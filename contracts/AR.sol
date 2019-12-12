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

    constructor(string memory name, uint balance, uint rate) public{
        owner = msg.sender;
        companies[owner] = Company(name, balance, rate, 0, 0, true, true);
        receiptsSize = 0;
    }

    function newCompany(address com, string memory name, uint balance, uint rate) public onlyOwner{
        require(companies[com].isValid == false,"existed company");
        companies[com] = Company(name, balance, rate, 0, 0, false, true);
    }

    function newBank(address bank, string memory name, uint balance, uint rate) public onlyOwner{
        require(companies[bank].isValid == false,"existed company");
        companies[bank] = Company(name, balance, rate, 0, 0, true, true);
    }

    // send `to` a reciept after a deal
    function deal(address to, uint amount) public returns (uint id) {
        require(companies[to].isValid,"invalid company");
        address from = msg.sender;
        require(companies[from].isValid,"invalid company");

        companies[from].toPay += amount;
        companies[to].toReceive += amount;
        receiptsSize += 1;
        id = receiptsSize;
        receipts[id] = Receipt(from, to, amount, true);
    }

    // transfer account receivable from `from` to `to` with the `receiptId`
    function transfer(address to, uint receiptId, uint amount) public returns (uint id) {
        require(receiptId <= receiptsSize,"invalid receipt");
        require(companies[to].isValid,"invalid company");
        address from = msg.sender;
        require(companies[from].isValid,"invalid company");

        require(receipts[receiptId].to == from,"");
        require(receipts[receiptId].amount >= amount,"invalid amount");
        address upper = receipts[receiptId].from;
        receipts[receiptId].amount -= amount;

        companies[from].toReceive -= amount;
        companies[to].toReceive += amount;
        receiptsSize += 1;
        id = receiptsSize;
        receipts[id] = Receipt(upper, to, amount, true);
    }

    // finace from bank
    function financing(address bank, uint receiptId, uint amount)public returns(bool success, uint id) {
        success = false;
        require(receiptId <= receiptsSize,"invalid receipt");
        address to = msg.sender;
        require(companies[to].isValid,"invalid company");
        require(companies[bank].isBank,"is not bank");
        require(receipts[receiptId].amount >= amount,"invalid amount");
        require(companies[receipts[receiptId].from].rate > 2,"");
        address from = receipts[receiptId].from;
        companies[to].toReceive -= amount;
        companies[bank].toReceive += amount;

        receipts[receiptId].amount -= amount;
        companies[bank].balance -= amount;
        companies[to].balance += amount;
        receiptsSize += 1;
        id = receiptsSize;
        // a new receipt to bankF
        receipts[id] = Receipt(from, bank, amount, true);
        success = true;
    }

    // pay a receipt
    function pay(uint receiptId)public{
        require(receiptId <= receiptsSize,"invalid receipt");
        address from = msg.sender;
        address to = receipts[receiptId].to;
        require(from == receipts[receiptId].from,"");
        require(companies[from].balance >= receipts[receiptId].amount,"");
        companies[from].balance -= receipts[receiptId].amount;
        companies[to].balance += receipts[receiptId].amount;
        receipts[receiptId].amount = 0;
        companies[from].toPay -= receipts[receiptId].amount;
        companies[to].toReceive -= receipts[receiptId].amount;
    }

    // inquire company's balance
    function getBanalance(address com)public view returns(uint balance) {
        require(companies[com].isValid,"invalid company");
        balance = companies[com].balance;
    }

    // inquire company's toreceive
    function getToReceive(address com)public view returns(uint toReceive) {
        require(companies[com].isValid,"invalid company");
        toReceive = companies[com].toReceive;
    }

    // inquire company's topay
    function getToPay(address com)public view returns(uint toPay) {
        require(companies[com].isValid,"invalid company");
        toPay = companies[com].toPay;
    }

    function getReceipt(uint receiptId) public view returns(address to, address from, uint amount) {
        require(receiptId <= receiptsSize,"invalid receipt");
        to = receipts[receiptId].to;
        from = receipts[receiptId].from;
        amount = receipts[receiptId].amount;
    }
}