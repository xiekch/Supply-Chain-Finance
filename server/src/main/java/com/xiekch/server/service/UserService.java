package com.xiekch.server.service;

import com.xiekch.server.domain.*;

public class UserService {
    private Storage storage;
    private static UserService service;

    private UserService() {
        this.storage = Storage.getInstance();
    }

    public static UserService getInstance() {
        if (service == null) {
            service = new UserService();
        }

        return service;
    }

    public boolean userSignUp(final User user) {
        if (user == null) {
            throw new RuntimeException("User must not be null!");
        }

        if (user.getName().equals("")) {
            throw new RuntimeException("User's name must be filled!");
        }
        if (user.getPassword().equals("")) {
            throw new RuntimeException("User's password must be filled!");
        }
        if (!Validator.userNameValid(user.getName())) {
            throw new RuntimeException("User's name is not valid!");
        }
        if (!Validator.userPasswordValid(user.getPassword())) {
            throw new RuntimeException("User's password is not valid!");
        }

        if (this.storage.isUser(user.getName())) {
            throw new RuntimeException("User's name has been used!");
        }

        this.storage.createUser(user);
        return true;
    }

    public boolean userSignIn(final User user) {
        if (!this.isUser(user)) {
            throw new RuntimeException("User's password is not correct or user's name has been used!");
        }

        return true;
    }

    public boolean isUser(User user) {
        if (user == null || user.getName() == null || user.getPassword() == null)
            return false;
        return this.storage.isUser(user);
    }

    public boolean hasCompany(User user){
        if (user == null || user.getName() == null || user.getPassword() == null)
            return false;
        return this.storage.hasCompany(user);
    }

    public Company getCompany(User user){
        if (user == null || user.getName() == null || user.getPassword() == null)
            return null;
        return this.storage.getCompany(user);
    }
}
