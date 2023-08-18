package com.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Transaction {
    private String transaction_id;
    private double amount;
    private String timeStamp;
    private Account account;
    private Account destination_Account;
    private String memo;
    private ArrayList<Transaction> transactions  = new ArrayList<>();
    public Transaction(){}
    public Transaction(double amount, String transaction_id, String memo) {
        this.transaction_id = transaction_id ;
        this.amount = amount;
        this.memo = memo;
        this.timeStamp = setTime();
    }

    public Transaction( double amount,String transaction_id,Account destination_Account, String memo) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.memo = memo;
        this.timeStamp = setTime();
        this.destination_Account = destination_Account;
    }
    public String setTime(){
        LocalDateTime currentDateTime  = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }

    public String getTransaction_id() {
        return transaction_id;
    }
    public double getAmount() {
        return amount;
    }
    public String getTimeStamp() {
        return timeStamp;
    }

    public String getMemo() {
        return memo;
    }
    public void setTransactions(Transaction t){
        transactions.add(t);
    }
    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }
}
