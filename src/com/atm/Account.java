package com.atm;

import java.util.ArrayList;

public class Account {
    /*
    The name of the account
     */
    private String name;
    /*
    The account ID number
     */
    private String uuid;
    /*
    The Use object that owns this account
     */
    private User holder;
    /*
    The list of transcations for this account
     */
    private ArrayList<Transaction> transactions;

    /**
     * create new account
     * @param name      the account's name
     * @param holder    the User object that holds this account
     * @param thebank   the bank that issues  the account
     */

    public Account(String name, User holder,Bank thebank){

     //get the account name and holder
        this.name = name;
        this.holder = holder;

     // get new account ID
        this.uuid = thebank.getNewAccountUUID();

     //init transactions
        this.transactions = new ArrayList<>();

     //add the account to holder and bank lists
     //   holder.addAccount(this);
     //   thebank.addAccount(this);

    }
    /**
     * Get the Account ID
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Get summary line for the account
     * @return  the string summary
     */
    public String getSummaryLine() {

        //get the account's balance
        double balance = this.getBalance();

        //format the summary line,depending  on the whether the balance is negative
        if(balance >= 0){
            return String.format("%s : $%.02f : %s",this.uuid,balance,this.name);
        }else{
            return String.format("%s : $(%.02f) : %s" ,this.uuid,balance,this.name);
        }
    }

    /**
     * Get the balance of this account by adding the amounts of the transactions
     * @return  the balance value
     */
    public double getBalance(){
        double balance = 0;
        for(Transaction t: this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    /**
     * print the transaction history of the account
     */
    public void printTransHistory(){
        System.out.printf("\nTransactions history for account: %s\n",this.uuid);
        for(int t = this.transactions.size()-1; t >= 0; t--){
            System.out.printf(this.transactions.get(t).getSummaryLine());
            System.out.println( );
            }
        System.out.println();
        }

    /**
     * Create new transaction
     * @param amount    the amount of the transaction
     * @param memo      the memo of the transaction
     */
    public void addTransaction(double amount,String memo){
        //Create new a transaction and add it to our list
        Transaction newTran = new Transaction(amount,memo,this);
        this.transactions.add(newTran);
    }
}
