package com.atm;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        User user = new User("Hisham" ,"Mohammed","123456");
        Transaction tran = new Transaction();
        do{
            System.out.println("Welcome to Bank YemenBank");
            System.out.print("Enter ID: ");
            String id = in.next();
            System.out.print("Enter pin: ");
            String pin = in.next();
            if(id.equals(user.getUserID())){
                if(pin.equals(user.getUserPin())){
                    ATM atm = new ATM(user,tran);
                }else {
                    System.out.println("The Pin is incorrect,Try again.");
                }
            }else{
                System.out.println("The ID is incorrect.");
            }
        }while(true);
    }
}
