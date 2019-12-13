package com.xiekch.server.domain;

import java.util.ArrayList;
import java.io.*;

public class Storage {
    private ArrayList<User> users;
    private ArrayList<Company> companies;
    private static Storage storage;
    private final static String UsersFileName = "./data/Users.txt";
    private final static String CompaniesFileName = "./data/Companies.txt";
    private static int dirty = 0;

    private Storage() {
        this.users = new ArrayList<User>();
        readFromFile();
    }

    private void readFromFile() {
        File inputFile = null;
        BufferedReader reader = null;
        String inline = null;
        String[] text = null;
        try {
            // users
            inputFile = new File(UsersFileName);
            if (!(inputFile.isFile() && inputFile.exists())) {
                inputFile.createNewFile();
            } else {
                reader = new BufferedReader(new FileReader(inputFile));
                inline = null;
                while ((inline = reader.readLine()) != null) {
                    text = inline.split(" +");
                    if (text.length != 2)
                        continue;
                    this.users.add(new User(text[0], text[1]));
                }
                reader.close();
            }

            // companies
            inputFile = new File(CompaniesFileName);
            if (!(inputFile.isFile() && inputFile.exists())) {
                inputFile.createNewFile();
            } else {
                reader = new BufferedReader(new FileReader(inputFile));
                inline = null;
                while ((inline = reader.readLine()) != null) {
                    text = inline.split(" +");
                    if (text.length != 3)
                        continue;
                    this.companies.add(new Company(text[0], text[1], text[2]));
                }
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        if (dirty < 10)
            return;
        dirty = 0;
        File outputFile = null;
        BufferedWriter writer = null;
        try {
            // users
            outputFile = new File(UsersFileName);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(outputFile));
            for (User user : this.users) {
                writer.write(user.getName());
                writer.write(" ");
                writer.write(user.getPassword());
                writer.newLine();
            }
            writer.close();

            // companies
            outputFile = new File(CompaniesFileName);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(outputFile));
            for (Company company : this.companies) {
                writer.write(company.getName());
                writer.write(" ");
                writer.write(company.getOwner());
                writer.write(" ");
                writer.write(company.getPrivateKey());
                writer.newLine();
            }
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public void createUser(User user) {
        this.users.add(user);
        dirty++;
        this.writeToFile();
    }

    public void deleteUser(String userName) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                this.users.remove(u);
            }
        }
        dirty++;
        this.writeToFile();
    }

    public void deleteUser(User user) {
        this.users.remove(user);
        dirty++;
        this.writeToFile();
    }

    public boolean isUser(String userName) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUser(final User user) {
        for (User u : users) {
            if (u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String userName) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void changeUser(String userName, User newUser) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                u = newUser;
            }
        }
        dirty++;
        this.writeToFile();
    }

    // company

    public void createCompany(Company company) {
        this.companies.add(company);
        dirty++;
        this.writeToFile();
    }

    public boolean hasCompany(User user) {
        for (Company company : this.companies) {
            if (company.getOwner().equals(user.getName())) {
                return true;
            }
        }
        return false;
    }

    public Company getCompany(User user) {
        for (Company company : this.companies) {
            if (company.getOwner().equals(user.getName())) {
                return company;
            }
        }
        return null;
    }

    public boolean isCompany(String companyName) {
        for (Company com: this.companies) {
            if (com.getName().equals(companyName)) {
                return true;
            }
        }
        return false;
    }




    public void sync() {
        this.writeToFile();
    }

}