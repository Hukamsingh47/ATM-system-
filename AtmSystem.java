import java.util.InputMismatchException;
import java.util.Scanner;

public class AtmSystem {

    // Define constants
    private static final int PIN = 3214;
    private static final double MAX_DEPOSIT = 50000;
    private static double accountBalance = 10000;
    private static double machineRemainingBalance = 5000;
    private static final int MAX_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isAuthenticated = authenticateUser(scanner);

        if (!isAuthenticated) {
            System.out.println("Too many incorrect attempts. Exiting...");
            return;
        }

        while (true) {
            displayMenu();
            int choice = getValidChoice(scanner);

            if (choice == 1) {
                withdrawMoney(scanner);
            } else if (choice == 2) {
                depositMoney(scanner);
            } else if (choice == 3) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Authenticate user
    private static boolean authenticateUser(Scanner scanner) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.println("Please Enter Your ATM Pin:");
            try {
                int userPin = scanner.nextInt();
                if (userPin == PIN) {
                    return true;
                } else {
                    System.out.println("Incorrect PIN. Try again.");
                    attempts++;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid PIN.");
                scanner.next(); 
            }
        }
        return false;
    }

    // Display main menu
    private static void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Exit");
    }

    // Get valid user choice
    private static int getValidChoice(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option.");
                scanner.next(); 
            }
        }
    }

    // Handle withdrawal
    private static void withdrawMoney(Scanner scanner) {
        System.out.println("Please Enter Amount to Withdraw:");
        try {
            int amount = scanner.nextInt();
            if (amount <= accountBalance && amount <= machineRemainingBalance) {
                accountBalance -= amount;
                machineRemainingBalance -= amount;
                System.out.println("Please collect your money.");
                System.out.println("Remaining balance: " + accountBalance);
                System.out.println("ATM machine remaining balance: " + machineRemainingBalance);
            } else if (amount > accountBalance) {
                System.out.println("Insufficient account balance.");
            } else {
                System.out.println("The ATM does not have enough money.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount to withdraw.");
            scanner.next(); 
        }
    }

    // Handle deposit
    private static void depositMoney(Scanner scanner) {
        System.out.println("Please Enter Amount to Deposit:");
        try {
            int amount = scanner.nextInt();
            if (amount > 0 && amount <= MAX_DEPOSIT) {
                accountBalance += amount;
                machineRemainingBalance += amount;
                System.out.println("Deposit successful.");
                System.out.println("Updated balance: " + accountBalance);
                System.out.println("ATM machine updated balance: " + machineRemainingBalance);
            } else if (amount > MAX_DEPOSIT) {
                System.out.println("Deposit amount exceeds maximum limit of " + MAX_DEPOSIT);
            } else {
                System.out.println("Please enter a valid amount to deposit.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount to deposit.");
            scanner.next(); 
        }
    }
}


