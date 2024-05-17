package atm;


import java.util.Scanner;

public class ATM {
    private String[] userIDs = {"user1", "user2"};
    private String[] pins = {"1234", "5678"};
    private double[] accountBalances = {1000.0, 2000.0};
    private String loggedInUser;

    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your user ID: ");
        String userID = sc.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = sc.nextLine();

        for (int i = 0; i < userIDs.length; i++) {
            if (userIDs[i].equals(userID) && pins[i].equals(pin)) {
                System.out.println("Login successful!");
                loggedInUser = userID;
                return;
            }
        }
        System.out.println("Invalid user ID or PIN");
    }

    public void showMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
    }

    public void displayBalance() {
        for (int i = 0; i < userIDs.length; i++) {
            if (userIDs[i].equals(loggedInUser)) {
                System.out.println("Your current balance is: $" + accountBalances[i]);
                return;
            }
        }
    }

    public void transactionsHistory() {
        System.out.println("Transactions History:");
        
    }

    public void withdraw() {
        for (int i = 0; i < userIDs.length; i++) {
            if (userIDs[i].equals(loggedInUser)) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter the amount to withdraw: ");
                double amount = sc.nextDouble();
                if (amount > 0 && amount <= accountBalances[i]) {
                    accountBalances[i] -= amount;
                    System.out.println("Withdrawal successful. Remaining balance: $" + accountBalances[i]);
                } else {
                    System.out.println("Invalid amount or insufficient balance");
                }
                return ;
            }
        }
    }

    public void deposit() {
        for (int i = 0; i < userIDs.length; i++) {
            if (userIDs[i].equals(loggedInUser)) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter the amount to deposit: ");
                double amount = sc.nextDouble();
                if (amount > 0) {
                    accountBalances[i] += amount;
                    System.out.println("Deposit successful. New balance: $" + accountBalances[i]);
                } else {
                    System.out.println("Invalid amount");
                }
                return;
            }
        }
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the recipient's user ID: ");
        String recipient = sc.nextLine();

        for (int i = 0; i < userIDs.length; i++) {
            if (userIDs[i].equals(loggedInUser)) {
                for (int j = 0; j < userIDs.length; j++) {
                    if (userIDs[j].equals(recipient)) {
                        System.out.print("Enter the amount to transfer: ");
                        double amount = sc.nextDouble();
                        if (amount > 0 && amount <= accountBalances[i]) {
                            accountBalances[i] -= amount;
                            accountBalances[j] += amount;
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Invalid amount or insufficient balance");
                        }
                        return;
                    }
                }
                System.out.println("Recipient user ID not found");
                return;
            }
        }
    }

    public void run() {
        login();
        if (loggedInUser != null) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                showMenu();
                System.out.print("Enter your choice: ");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        transactionsHistory();
                        break;
                    case "2":
                        withdraw();
                        break;
                    case "3":
                        deposit();
                        break;
                    case "4":
                        transfer();
                        break;
                    case "5":
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
    
}

