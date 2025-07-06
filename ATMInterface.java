package codesoft.task3;

import java.util.Scanner;

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 1000.00; 
        int choice;

        System.out.println(" Welcome to CodSoft ATM ");

        do {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    
                    System.out.printf("Your current balance is ₹%.2f\n", balance);
                    break;

                case 2:
                    
                    System.out.print("Enter amount to deposit: ₹");
                    double deposit = scanner.nextDouble();
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.printf("₹%.2f deposited successfully!\n", deposit);
                    } else {
                        System.out.println(" Invalid deposit amount!");
                    }
                    break;

                case 3:
                    
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdraw = scanner.nextDouble();
                    if (withdraw > 0 && withdraw <= balance) {
                        balance -= withdraw;
                        System.out.printf("₹%.2f withdrawn successfully!\n", withdraw);
                    } else {
                        System.out.println(" Invalid or insufficient balance!");
                    }
                    break;

                case 4:
                    
                    System.out.println(" Thank you for using CodSoft ATM. Goodbye!");
                    break;

                default:
                    System.out.println(" Invalid choice! Please enter 1–4.");
            }

        } while (choice != 4); 

        scanner.close(); 
    }
}
