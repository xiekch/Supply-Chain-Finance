package com.xiekch.server.domain;


public class Company {
    private String name;
    private String privateKey;
    private String owner;

    public Company() {
    }

    public Company(String name, String owner, String privateKey) {
        this.name = name;
        this.owner = owner;
        this.privateKey = privateKey;
    }

    public String getName() {
        return this.name;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    // override equals method to make contains work
    public boolean equals(Object object) {
        if (this == object)
            return true;

        Company Company = (Company) object;
        if (name.equals(Company.getName()) && privateKey.equals(Company.getPrivateKey()))
            return true;
        else
            return false;
    }

    public String getOwner(){
        return this.owner;
    }
}
