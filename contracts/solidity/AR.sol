pragma solidity ^0.4.22;

contract AR {
    // event
    event resultEvent(int ret, uint receiptId);

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

    function newCompany(address com, string memory name, uint balance, uint rate) public{
        if(companies[com].isValid == true){
            emit resultEvent(0,0);
        }
        companies[com] = Company(name, balance, rate, 0, 0, false, true);
        emit resultEvent(1,0);
    }

    function newBank(address bank, string memory name, uint balance, uint rate) public{
        if(companies[bank].isValid == true){
            emit resultEvent(0,0);
        }
        companies[bank] = Company(name, balance, rate, 0, 0, true, true);
        emit resultEvent(1,0);
    }

    // send `to` a reciept after a deal
    function deal(address from, address to, uint amount) public returns (uint id) {
        if(companies[to].isValid == false){
            emit resultEvent(0,0);
        }

        if(companies[from].isValid == false){
            emit resultEvent(0,0);
        }
        companies[from].toPay += amount;
        companies[to].toReceive += amount;
        receiptsSize += 1;
        id = receiptsSize;
        receipts[id] = Receipt(from, to, amount, true);
        emit resultEvent(1,id);
    }

    // transfer account receivable from `from` to `to` with the `receiptId`
    function transfer(address from, address to, uint receiptId, uint amount) public returns (uint id) {
        if(companies[to].isValid == false){
            emit resultEvent(0,0);
        }

        if(companies[from].isValid == false){
            emit resultEvent(0,0);
        }

        if(receiptId > receiptsSize){
            emit resultEvent(0,0);
        }

        if(receipts[receiptId].to != from){
            emit resultEvent(0,0);
        }

        if(receipts[receiptId].amount < amount){
            emit resultEvent(0,0);
        }
        address upper = receipts[receiptId].from;
        receipts[receiptId].amount -= amount;

        companies[from].toReceive -= amount;
        companies[to].toReceive += amount;
        receiptsSize += 1;
        id = receiptsSize;
        receipts[id] = Receipt(upper, to, amount, true);
        emit resultEvent(1,id);
    }

    // finace from bank
    function financing(address to, address bank, uint receiptId, uint amount)public returns(bool success, uint id) {
        if(companies[to].isValid == false){
            emit resultEvent(0,0);
        }

        if(companies[bank].isValid == false){
            emit resultEvent(0,0);
        }

        if(receiptId > receiptsSize){
            emit resultEvent(0,0);
        }

        if(receipts[receiptId].to != to){
            emit resultEvent(0,0);
        }

        if(receipts[receiptId].amount < amount){
            emit resultEvent(0,0);
        }
        // address to = msg.sender;
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
        emit resultEvent(1,id);
    }

    // pay a receipt
    function pay(address from, uint receiptId)public{
        address to = receipts[receiptId].to;
        require(from == receipts[receiptId].from,"");
        require(companies[from].balance >= receipts[receiptId].amount,"");
        if(companies[to].isValid == false){
            emit resultEvent(0,0);
        }

        if(companies[from].isValid == false){
            emit resultEvent(0,0);
        }

        if(receiptId > receiptsSize){
            emit resultEvent(0,0);
        }
        companies[from].balance -= receipts[receiptId].amount;
        companies[to].balance += receipts[receiptId].amount;
        receipts[receiptId].amount = 0;
        companies[from].toPay -= receipts[receiptId].amount;
        companies[to].toReceive -= receipts[receiptId].amount;
        emit resultEvent(1,0);
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