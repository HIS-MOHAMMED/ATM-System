package com.atm;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ATM {
    private Transaction tran;
    private User user;
    public ATM(User user,Transaction tran){
        this.user = user;
        this.tran = tran;
        int choice = 0;
        int account_From = 0;
        int account_To = 0;
        String memo = "";
        double amount = 0.0;
        do{
            Scanner in = new Scanner(System.in);
            System.out.println(user.getFirstName() + "'s " + "accounts summary:");
            showAccountsSummary(user.getAccounts());
            System.out.println();
            System.out.println("Welcome " + user.getFirstName() + ",what would you do like to do?");
            System.out.println("1) Show account transaction history\n2) Withdraw\n3) Deposit\n4) Transfer\n5) Qiut");
            System.out.print("Enter Choice:");
            choice = in.nextInt();
            switch (choice){
                case 1 :
                    System.out.print("Enter the number of account you want to show its history(1/2):");
                    account_From = in.nextInt();
                    System.out.println("Transaction history for account " + setAccountNumber(account_From).getAccount_ID() + ":");
                    transactionsHistory(setAccountNumber(account_From),tran.getTransactions());
                    break;
                case 2 :
                    System.out.print("Enter the number of account you want to withdraw from it (1/2): ");
                    account_From = in.nextInt();
                    System.out.print("Enter the memo: ");
                    in.nextLine();
                    memo = in.nextLine();
                    System.out.print("Enter the amount of transfer(max:" +float_format(setAccountNumber(account_From).getBalance())+"$): ");
                    amount = in.nextDouble();
                    withdraw(setAccountNumber(account_From),amount,memo);
                    break;
                case 3 :
                    System.out.print("Enter the number of account you want to deposit from it (1/2): ");
                    account_From = in.nextInt();
                    System.out.print("Enter the memo: ");
                    in.nextLine();
                    memo = in.nextLine();
                    System.out.print("Enter the amount of transfer(max:" +float_format(setAccountNumber(account_From).getBalance())+"$): ");
                    amount = in.nextDouble();
                    deposit(setAccountNumber(account_From),amount,memo);
                    break;
                case 4 :
                    System.out.print("Enter the number of account you want to transfer from it (1/2): ");
                    account_From = in.nextInt();
                    System.out.print("Enter the number of account you want to transfer to it (1/2): ");
                    account_To = in.nextInt();
                    System.out.print("Enter the memo: ");
                    in.nextLine();
                    memo = in.nextLine();
                    System.out.print("Enter the amount of transfer(max:" +float_format(setAccountNumber(account_From).getBalance())+"$): ");
                    amount = in.nextDouble();
                    transfer(amount,setAccountNumber(account_From),setAccountNumber(account_To),memo);
                    break;
            }
            System.out.print("Do you want to another transfer(y/n): ");
            in.nextLine();
            String another_transfer = in.nextLine().toLowerCase(Locale.ROOT);
            if(another_transfer.equals("yes") || another_transfer.equals("y")){
                continue;
            } else {
                break;
            }
        }while (choice != 5);
    }
    public void showAccountsSummary(ArrayList<Account> accounts){
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i+1)+") " + accounts.get(i).toString());
        }
    }
    public Account setAccountNumber(double num){
        num -= 1;
       return  user.getAccounts().get((int)num);
    }
    public void withdraw(Account account,double amount,String memo){
        if(amount <= account.getBalance()){
            account.setBalance(amount);
            Transaction t = new Transaction(-amount,account.getAccount_ID(),memo);
            tran.setTransactions(t);
            System.out.println("You are withdraw from account " + account.getAccount_ID() + ": " + float_format(amount) + "$");
        }
        else System.out.println("Your Balance is not enough.");
    }
    public void deposit(Account account,double  amount,String memo){
        if(amount > 0){
            account.setBalanceD(amount);
            Transaction t = new Transaction(amount,account.getAccount_ID(),memo);
            tran.setTransactions(t);
            System.out.println("You are deposit to account " + account.getAccount_ID() + ": " + float_format(amount) + "$");
        }
    }
    public void transfer(double amount,Account source_Account, Account destination_Account,String memo){
        if(amount <= source_Account.getBalance()){
            source_Account.setBalance(amount);
            destination_Account.setBalanceD(amount);
            Transaction t = new Transaction(-amount,source_Account.getAccount_ID(),destination_Account,memo);
            tran.setTransactions(t);
            System.out.println("You are transfer from account " + source_Account.getAccount_ID() + ": " + float_format(amount) + "$ to account: " + destination_Account.getAccount_ID());
        }else System.out.println("Your balance is not enough.");
    }
    public void transactionsHistory(Account account,ArrayList<Transaction> transactions){
        if(transactions.isEmpty()){
            System.out.println("No history for this account yet.");
        }else{
            for (int i = 0; i < transactions.size(); i++) {
                if(transactions.get(i).getTransaction_id() == account.getAccount_ID()){
                    System.out.println(transactions.get(i).getTimeStamp() + " : " +"$"+float_format(transactions.get(i).getAmount()) + " : " + transactions.get(i).getMemo());
                }
            }
        }
    }
    public String float_format(double num ){
        String str = String.format("%.2f",num);
        return str;
    }
}
