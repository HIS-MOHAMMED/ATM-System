package com.atm;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String userID;
    private String userPin;
    private ArrayList<Account> accounts = new ArrayList<>();
    public User(String firstName, String lastName,String userPin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPin = userPin;
        System.out.print("New User " + lastName +", " + firstName);
        this.userID = genUserID();
        System.out.println(" with ID " + userID + " created.");
        this.accounts = genAccounts();

    }
    public String genUserID(){
        String id = "";
        for (int i = 0; i < 7 ; i++) {
            id += (int)(Math.random()* 7);
        }
        return id;
    }
    public ArrayList<Account> genAccounts(){
        accounts.add(new Account(genUserID(),"Savings",0.00));
        accounts.add(new Account(genUserID(),"Checking",0.00));
        return accounts;
    }
    public String getUserID(){
        return userID;
    }
    public String getUserPin(){
        return userPin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
