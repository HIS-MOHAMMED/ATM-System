package com.atm;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
/*
The name of bank
 */
private String name;
/*
The list of users that bank has it
 */
private ArrayList<User> users;
/*
The list of accounts that bank has it.
 */
private ArrayList<Account> accounts;

    /**
     * Create new bank with empty users and accounts list
     * @param name   the of the bank
     */
    public Bank(String name){
    this.name = name;
    this.users = new ArrayList<>();
    this.accounts = new ArrayList<>();
}
/**
 * Generate ne universally unique ID for a user
 * @return the uuid
 */
public String getNewUserUUID(){
    //inits
    String uuid;
    Random rng = new Random();
    int len = 6;
    boolean nonUnique;

    //Continue looping until we get a unique ID
    do{
        //generate the number
        uuid = "";
        for(int i = 0 ; i < len; i++){
            uuid += ((Integer) rng.nextInt(10));
        }
        nonUnique = false;
        //check to make sure it's unique
        for(User u : this.users){
            if(uuid.compareTo(u.getUuid()) == 0){
                nonUnique = true;
                break;
            }
        }
    }while(nonUnique);
    return  uuid;
}

/**
 * Gererate a new universally unique ID for an account
 * @return the uuid
 */
public  String getNewAccountUUID(){
    //inits
    String uuid;
    Random rng = new Random();
    int len = 10;
    boolean nonUnique;

    //continue looping until we get a unique id
    do{
        uuid = "";
        //generate a number
        for(int i = 0; i < len; i++){
            uuid += ((Integer)rng.nextInt(10));
        }
        //check to make sure it's unique
        nonUnique = false;
        for(Account a: this.accounts){
            if(uuid.compareTo(a.getUuid()) == 0) {
                nonUnique = true;
                break;
            }
        }

    }while (nonUnique);
    return  uuid;

}
    /**
     * Create a new user of the bank
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's pin
     * @return          the new use object
     */
    public User addUser(String firstName,String lastName,String pin){

        //create a new user object and add it to out list
        User newUser = new User(firstName,lastName,pin,this);
        this.users.add(newUser);

        //create a savings account for the user
        Account newAccount = new Account("Savings",newUser,this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);
        return newUser;
    }

/**
 * Add an account for the bank
 * @param anAcct    the account to add
 */
public void addAccount(Account anAcct){
    this.accounts.add(anAcct);
}

    /**
     * Get the User object associated with a particular userID and pin , if they are valid
     * @param userUuid  the UUID of the user to log in
     * @param userPin   the pin of the user
     * @return          the user  object , if the long in successful, or null if it is not
     */
    public User userLogin(String userUuid, String userPin){

    //search through list of users
    for(User u: this.users){

        //check user ID is correct
        if(u.getUuid().compareTo(userUuid) == 0 && u.validatePin(userPin)) return u;
    }

    // if we haven't found user or have an incorrect pin
    return null;
}
public String getName(){
        return this.name;
}
}
