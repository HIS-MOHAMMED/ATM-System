package com.atm;

public class Account {
    private String account_ID;
    private String account_type;
    private double balance ;
    private int account_transaction;
    public Account(String account_ID,String account_type,double balance){
        this.account_ID = account_ID;
        this.account_type = account_type;
        this.balance = balance;
    }
    @Override
    public String toString() {
        String str = String.format("%.2f",balance);
        return account_ID + " : " + "$" + str + " : " + account_type;
    }
    public String getAccount_ID() {
        return account_ID;
    }
    public void setBalance(double balance) {
        this.balance -= balance;
    }
    public void setBalanceD(double balance) {
        this.balance += balance;
    }
    public double getBalance(){
        return balance;
    }
}
