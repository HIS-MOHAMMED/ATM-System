package com.atm;
import java.util.Date;

public class Transaction {
    /*
    The amount of this transaction
     */
    private double amount;
    /*
    The time and date of this transaction
     */
    private Date timeStamp;
    /*
    The memo for this transaction.
     */
    private String memo;
    /*
    The account in which the transaction was performad.
     */
    private Account account;

    /**
     * Create a new transaction
     * @param amount the amount transacted
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount,Account inAccount) {
        this.amount = amount;
        this.account = inAccount;
        this.timeStamp = new Date();
        this.memo = "";
    }

    /**
     * Create a new transaction
     * @param amount the amount transacted
     * @param memo   the memo for the transaction
     * @param inAccount the account the transaction belongs to
     */
    public Transaction( double amount,String memo,Account inAccount) {
        //call the two-org constructor first
        this(amount,inAccount);

        //get a memo
        this.memo = memo;
    }

    /**
     * Get the amount of the transaction
     * @return the amount value
     */
    public double getAmount(){
        return this.amount;
    }

    /**
     * Get the string summarizing the transaction
     * @return  the summary string of transaction
     */
    public String getSummaryLine(){

        if(this.amount >= 0){
            return String.format("%s  :    $%.02f     :   %s",
                    this.timeStamp.toString(),this.amount,this.memo);
        }else{
            return String.format("%s  :    $(%.02f)   :   %s",
                    this.timeStamp.toString(),this.amount,this.memo);
        }
    }
}
