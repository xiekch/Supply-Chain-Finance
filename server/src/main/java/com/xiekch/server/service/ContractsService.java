package com.xiekch.server.service;

import com.xiekch.server.domain.*;
import com.xiekch.server.solidity.AR;
import com.xiekch.server.domain.Storage;

import java.math.BigInteger;

import com.xiekch.server.constants.*;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;

public class ContractsService {
    private String contractAddress = null;
    private static ContractsService service;
    @Autowired
    private Web3j web3j;

    private ContractsService() {
        String address = Storage.getInstance().getContractAddress();
        if (address != null) {
            this.contractAddress = address;
        }
    }

    public static ContractsService getInstance() {
        if (service == null) {
            service = new ContractsService();
        }
        return service;
    }

    public String getContractAddress(){
        return this.contractAddress;
    }

    public boolean deployContract(String owner) {
        try {
            if (this.contractAddress == null) {
                EncryptType.encryptType = 0;
                Credentials credentials = GenCredential.create();
                String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
                Storage.getInstance().createCompany(new Company("bank", owner, privateKey));

                AR ar = AR.deploy(web3j, credentials,
                        new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT), "bank",
                        new BigInteger("100000"), new BigInteger("5")).send();
                this.contractAddress = ar.getContractAddress();
                Storage.getInstance().saveContractAddress(this.contractAddress);
            }
        } catch (TypeMismatchException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean createCompany(String companyName, String owner) {
        if (Storage.getInstance().isCompany(companyName)) {
            return false;
        }
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create();
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
        Storage.getInstance().createCompany(new Company(companyName, owner, privateKey));
        AR.load(contractAddress, web3j, credentials,
                new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

        return true;
    }

    public boolean deal(){
        return true;
    }

    public boolean transfer(){
        return true;
    }

    public boolean financing(){
        return true;
    }
}
