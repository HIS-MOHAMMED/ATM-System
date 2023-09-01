package com.atm;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    /*
    The first name of user
     */
    private String firstName;
    /*
    The last name of user
     */
    private String lastName;
    /*
    The unique ID of the user
     */
    private String uuid;
    /*
    The pin of the user
     */
    private byte pinHash[];
    /*
    The list of accounts the user has it
     */
    private ArrayList<Account> accounts;

    /**
     * create new user
     * @param firstName   the user's first name
     * @param lastName    the user's last name
     * @param userPin     the user's pin number
     * @param theBank     the Bank object  that the user is a customer of
     */
    public User(String firstName, String lastName,String userPin,Bank theBank){

        //set the user's name
        this.firstName = firstName;
        this.lastName = lastName;

        //store the pin's MD5 hash,rather than original value , for
        //security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(userPin.getBytes(StandardCharsets.UTF_8));
        }catch (NoSuchAlgorithmException e){
            System.err.println("error, caught NoSuchAlgorithmException.");
            e.printStackTrace();
            System.exit(0);
        }
        //get a new , unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();
        //create empty list of accounts
        this.accounts = new ArrayList<>();
        //print log message
        System.out.printf("New user %s , %s with ID %s created.\n",firstName,lastName,uuid);
    }
    public String genUserID(){
        String id = "";
        for (int i = 0; i < 7 ; i++) {
            id += (int)(Math.random()* 7);
        }
        return id;
    }
    public String getUserID(){
        return uuid;
    }
    public String getFirstName() {
        return firstName;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    /**
     * Add an account for the user
     * @param anAcct   the account to add
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);

    }

    /**
     * Get a User ID
     * @return the uuid
     */
    public String getUuid(){
        return this.uuid;
    }

    /**
     *check whether a given pin mahches the user pin
     * @param userPin   the pin to check
     * @return          whether the pin if valid or not
     */
    public boolean validatePin(String userPin){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(userPin.getBytes(StandardCharsets.UTF_8)),this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException.");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }
    public void printAccountSummary(){

        System.out.printf("Welcome %s, accounts summary: \n" ,this.firstName);
        for(int i = 0; i < accounts.size(); i++){
            System.out.printf("%d) %s\n",i+1,accounts.get(i).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Get the number of accounts of the user
     * @return  the number of accounts
     */
    public int numAccounts(){
       return this.accounts.size();
    }

    /**
     *  print the transaction history for a particular account
     * @param acctIdx   the index of the account to use
     */
    public void printAcctTransHistory(int acctIdx){
        this.accounts.get(acctIdx).printTransHistory();
    }

    /**
     * Add a transaction to a particular account
     * @param acctIdx   the index of the account
     * @param amount    the amount of the transaction
     * @param memo      the memo of the transaction
     */
    public void addAcctTransaction(int acctIdx,double amount,String memo){
            this.accounts.get(acctIdx).addTransaction(amount,memo);
    }
}
