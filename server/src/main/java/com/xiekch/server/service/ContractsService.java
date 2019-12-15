package com.xiekch.server.service;

import com.xiekch.server.domain.*;
import com.xiekch.server.solidity.*;
import com.xiekch.server.solidity.AR.*;
import com.xiekch.server.domain.Storage;

import java.math.BigInteger;
import java.util.List;

import com.xiekch.server.constants.*;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

public class ContractsService {
    private String contractAddress = null;
    private String privateKey = null;
    private static ContractsService service;
    private AR ar;
    // @Autowired
    private static Web3j web3j;

    private ContractsService() {
        this.contractAddress = Storage.getInstance().getContractAddress();
        this.privateKey = Storage.getInstance().getPrivateKey();
        if (loadContract()) {
            System.out.println("alreadly deployed, loaded successfully");
        } else {
            System.out.println("deploying");
        }
        if (this.ar == null) {
            if (!this.deployContract("admin"))
                System.out.println("deploying error");
        }
    }

    public static ContractsService getInstance(Web3j web3j) {
        ContractsService.web3j = web3j;
        if (service == null) {
            service = new ContractsService();
        }
        return service;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public boolean loadContract() {
        if (this.contractAddress != null && this.privateKey != null && !this.contractAddress.isEmpty()
                && !this.privateKey.isEmpty()) {
            System.out.println(this.contractAddress);
            EncryptType.encryptType = 0;
            Credentials credentials = GenCredential.create(this.privateKey);
            this.ar = AR.load(contractAddress, web3j, credentials,
                    new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
            if (this.ar != null)
                return true;
            return false;
        }
        return false;
    }

    public boolean deployContract(String owner) {
        if (this.ar == null) {
            try {
                EncryptType.encryptType = 0;
                Credentials credentials = GenCredential.create();
                String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
                Storage.getInstance().createCompany(new Company("bank", owner, privateKey));

                System.out.println("call deploy function");
                if (web3j == null) {
                    System.out.println("web3j error");
                }
                AR ar = AR.deploy(web3j, credentials,
                        new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT), "bank",
                        new BigInteger("100000"), new BigInteger("5")).send();
                if (ar == null) {
                    System.out.println("deploy error");
                    throw (new RuntimeException("deploy error"));
                }
                this.ar = ar;
                System.out.println("deploy contract successfully, contract address is " + ar.getContractAddress());
                this.contractAddress = ar.getContractAddress();
                this.privateKey = privateKey;
                Storage.getInstance().saveContractInform(this.contractAddress, this.privateKey);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("already deployed contract");
            return false;
        }
        return true;
    }

    public boolean createCompany(String companyName, String owner, int balance, int rate) {
        if (this.ar == null) {
            System.out.println("AR hasn't been deployed");
            return false;
        }
        if (Storage.getInstance().isCompany(companyName)) {
            return false;
        }
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create();
        String address = credentials.getAddress();
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);

        try {
            TransactionReceipt receipt = this.ar.newCompany(address, companyName, new BigInteger(String.valueOf(balance)),
            new BigInteger(String.valueOf(rate))).send();
            List<ResultEventEventResponse> response = ar.getResultEventEvents(receipt);
            if (!response.isEmpty()) {
                if (!response.get(0).ret.toString().equals("0")) {
                    System.out.printf("create company success\n");                    
                    Storage.getInstance().createCompany(new Company(companyName, owner, privateKey));
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("create company failed");
        return false;
    }

    public int deal(String companyName, String toCompanyName, int amount) {
        if (this.ar == null) {
            System.out.println("AR hasn't been deployed");
            return 0;
        }
        Company company = Storage.getInstance().getCompanyByName(companyName);
        Company toCompany = Storage.getInstance().getCompanyByName(toCompanyName);
        if (company == null || toCompany == null) {
            return 0;
        }
        try {
            String to = GenCredential.create(toCompany.getPrivateKey()).getAddress();
            String from = GenCredential.create(company.getPrivateKey()).getAddress();
            TransactionReceipt receipt = this.ar.deal(from, to, new BigInteger(String.valueOf(amount))).send();
            List<ResultEventEventResponse> response = ar.getResultEventEvents(receipt);
            if (!response.isEmpty()) {
                if (!response.get(0).ret.toString().equals("0")) {
                    int newReceiptId = Integer.valueOf(response.get(0).receiptId.toString());
                    System.out.printf("deal success\nreceiptId: %d\n", newReceiptId);
                    return newReceiptId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("deal failed");
        return 0;
    }

    public int transfer(String companyName, String toCompanyName, int receiptId, int amount) {
        if (this.ar == null) {
            System.out.println("AR hasn't been deployed");
            return 0;
        }
        Company company = Storage.getInstance().getCompanyByName(companyName);
        Company toCompany = Storage.getInstance().getCompanyByName(toCompanyName);
        if (company == null || toCompany == null) {
            return 0;
        }
        try {
            String to = GenCredential.create(toCompany.getPrivateKey()).getAddress();
            String from = GenCredential.create(company.getPrivateKey()).getAddress();

            TransactionReceipt receipt = this.ar.transfer(from, to, new BigInteger(String.valueOf(receiptId)),
                    new BigInteger(String.valueOf(amount))).send();
            List<ResultEventEventResponse> response = ar.getResultEventEvents(receipt);
            if (!response.isEmpty()) {
                if (!response.get(0).ret.toString().equals("0")) {
                    int newReceiptId = Integer.valueOf(response.get(0).receiptId.toString());
                    System.out.printf("trasfer success\nreceiptId: %d\n", newReceiptId);
                    return newReceiptId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("transfer failed");
        return 0;
    }

    public int financing(String companyName, int receiptId, int amount) {
        if (this.ar == null) {
            System.out.println("AR hasn't been deployed");
            return 0;
        }
        Company company = Storage.getInstance().getCompanyByName(companyName);
        String to = GenCredential.create(company.getPrivateKey()).getAddress();

        String bank = GenCredential.create(this.privateKey).getAddress();
        try {
            TransactionReceipt receipt = this.ar.financing(to, bank, new BigInteger(String.valueOf(receiptId)),
                    new BigInteger(String.valueOf(amount))).send();
            List<ResultEventEventResponse> response = ar.getResultEventEvents(receipt);
            if (!response.isEmpty()) {
                if (!response.get(0).ret.toString().equals("0")) {
                    int newReceiptId = Integer.valueOf(response.get(0).receiptId.toString());
                    System.out.printf("finance success\nreceiptId: %d\n", newReceiptId);
                    return newReceiptId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("finance failed");
        return 0;
    }

    public int pay(String companyName, int receiptId) {
        if (this.ar == null) {
            System.out.println("AR hasn't been deployed");
            return 0;
        }
        Company company = Storage.getInstance().getCompanyByName(companyName);
        if (company == null) {
            System.out.println("no such company");
            return 0;
        }
        try {
            String from = GenCredential.create(company.getPrivateKey()).getAddress();

            TransactionReceipt receipt = this.ar.pay(from, new BigInteger(String.valueOf(receiptId))).send();
            List<ResultEventEventResponse> response = ar.getResultEventEvents(receipt);
            if (!response.isEmpty()) {
                if (!response.get(0).ret.toString().equals("0")) {
                    System.out.printf("pay success\n");
                    return 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("pay failed");
        return 0;
    }

}