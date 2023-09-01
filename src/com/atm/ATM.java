package com.atm;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        //init  scanner
        Scanner sc = new Scanner(System.in);

        //init Bank
        Bank theBank = new Bank("Bank Of Yemen");

        // add new user , which also creates a savings account
        User aUser = theBank.addUser("Hisham", "Mohammed", "1234");

        //add a checking account for our user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while(true){
            //stay in login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank,sc);

            //stay in main menu until user quits
            ATM.printUserMenu(curUser,sc);

        }
    }

    /**
     *Get a user object after check if it's exit
     * @param theBank   the bank who has the user object
     * @param sc        the scanner object for user input
     * @return          the user  object if it's exit , or null if not exit
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc){

        //inits
        String userID;
        String pin;
        User authUser;
        do{
            System.out.println("Welcome to " + theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();

            authUser = theBank.userLogin(userID,pin);
            if(authUser == null){
                System.out.println("Incorrect user ID/Pin combination.Please try again.");
            }

        }while(authUser == null); //continue looping util successful login
        return authUser;
    }
    public  static  void printUserMenu(User theUser, Scanner sc){

        //print a summary or the account
        theUser.printAccountSummary();

        //init
        int choice;

        //user menu
        do{
            System.out.printf("Welcome %s , what would do like to do?\n",theUser.getFirstName());
            System.out.println("1)show account transaction history.\n2)Withdrawl.\n3)Deposit.\n4)Transfer.\n5)Quit.");
            System.out.print("Enter the choice: ");
            choice = sc.nextInt();
            if(choice < 1 || choice > 5){
                System.out.println("Invalid Choice.Please choose 1-5");
            }
        }while (choice < 1 || choice > 5);

        //process the choice
        switch (choice){
            case 1 :
                ATM.showTransHistory(theUser,sc);
                break;
            case 2 :
                ATM.withdrawlFunds(theUser,sc);
                break;
            case 3 :
                ATM.depositFunds(theUser,sc);
                break;
            case 4 :
                ATM.transferFunds(theUser,sc);
                break;
        }
        //redisplay this menu unless the use wants to quit
        if(choice != 5){
            ATM.printUserMenu(theUser,sc);
        }
    }
    public static  void showTransHistory(User theUser,Scanner sc){
        //init
        int theAcct;

        //get account whose transaction history to look at
        do{
            System.out.printf("Enter the number (1-%d) of the account" +
                    "whose transactions you want to see: ",theUser.numAccounts());
            theAcct = sc.nextInt()-1;
            if(theAcct < 0 || theAcct >= theUser.numAccounts()){
                System.out.println("Invalid account.Please try again.");
            }
        }while (theAcct < 0 || theAcct >= theUser.numAccounts());

        //print the account transactions history
        theUser.printAcctTransHistory(theAcct);
    }

    /**
     * Process the transfer between two accounts.
     * @param theUser   the logged_in user object
     * @param sc        the Scanner object for user input
     */
    public static void transferFunds(User theUser, Scanner sc){
        //inits
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        //get the account to transfer from
        do{
            System.out.printf("Enter the number (1-%d) of the account " +
                    "to transfer from: ",theUser.numAccounts());
            fromAcct = sc.nextInt()-1;
            if(fromAcct < 0 || fromAcct >= theUser.numAccounts()){
                System.out.println("Invalid account.Please try again.");
            }
        }while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAccounts().get(fromAcct).getBalance();

        //get the account to transfer to
        do{
            System.out.printf("Enter the number (1-%d) of the account " +
                    "to transfer to: ",theUser.numAccounts());
            toAcct = sc.nextInt()-1;
            if(toAcct < 0 || toAcct >= theUser.numAccounts()){
                System.out.println("Invalid account.Please try again.");
            }
        }while (toAcct < 0 || toAcct >= theUser.numAccounts());

        //get the amount to transfer
        do{
            System.out.printf("Enter the amount to transfer(max $%.02f): $",acctBal);
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero.");
            }else if(amount > acctBal){
                System.out.printf("Amount must not be greater than balance" +
                        " of $%.02f.\n",acctBal);
            }
        }while (amount < 0 || amount > acctBal);

        //finally, do the transfer
        theUser.addAcctTransaction(fromAcct,-1*amount,
                String.format("Transfer to account %s",theUser.genUserID()));
        theUser.addAcctTransaction(toAcct, amount,
                String.format("Transfer to account %s",theUser.genUserID()));
    }

    /**
     * Process of the withdraw from an account
     * @param theUser   the logged_in user object
     * @param sc        the Scanner object   for user input
     */
    public static void withdrawlFunds(User theUser, Scanner sc){
        //inits
        int fromAcct;
        double amount;
        double acctBal;
        String memo;

        //get the account to withdraw from
        do{
            System.out.printf("Enter the number (1-%d) of account to withdraw from: ",theUser.numAccounts());
            fromAcct = sc.nextInt()-1;
            if(fromAcct < 0 || fromAcct > theUser.numAccounts()){
                System.out.println("Invalid account.Please try again.");
            }
        }while (fromAcct < 0 || fromAcct > theUser.numAccounts());
        acctBal = theUser.getAccounts().get(fromAcct).getBalance();

        //get the amount to withdraw
        do{
            System.out.print("Enter the amount to withdraw: ");
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero.");
            }else if(amount > acctBal){
                System.out.printf("Amount must not be greater than balance" +
                        " of $%.02f.\n",acctBal);
            }
        }while (amount < 0 || amount > acctBal);

        //clear up the rest of previous input
        sc.nextLine();

        //get the memo
        System.out.print("Enter the memo: ");
        memo = sc.nextLine();

        //do the withdraw
        theUser.addAcctTransaction(fromAcct,-1*amount,memo);
    }

    /**
     * Process the deposit to an account
     * @param theUser   the logged_in user object
     * @param sc        the Scanner object for user input
     */
    public static void depositFunds(User theUser, Scanner sc){
        //inits
        int toAcct;
        double amount;
        double acctBal;
        String memo;

        //get the account to deposit to
        do{
            System.out.printf("Enter the number(1-%d) of account to deposit in: ",theUser.numAccounts());
            toAcct = sc.nextInt()-1;
            if(toAcct < 0 || toAcct >= theUser.numAccounts()){
                System.out.println("Invalid account.Please try again.");
            }
        }while (toAcct < 0 || toAcct >= theUser.numAccounts());
        acctBal = theUser.getAccounts().get(toAcct).getBalance();

        //get the amount of deposit
        do{
            System.out.print("Enter the amount of deposit: ");
            amount = sc.nextDouble();
            if(amount < 0){
                System.out.println("Amount must be greater than zero.");
            }
        }while (amount < 0 );

        //clear up the rest of previous input
        sc.nextLine();

        //get a memo
        System.out.print("Enter the memo: ");
        memo = sc.nextLine();

        //finally , do the deposit
        theUser.addAcctTransaction(toAcct,amount,memo);
    }
}
