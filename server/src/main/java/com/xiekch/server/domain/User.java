package com.xiekch.server.domain;

public class User {
    private String name;
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // override equals method to make contains work
    public boolean equals(Object object) {
        if (this == object)
            return true;

        User user = (User) object;
        if (name.equals(user.getName()) && password.equals(user.getPassword()))
            return true;
        else
            return false;
    }
}
