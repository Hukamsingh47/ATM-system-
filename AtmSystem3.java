import java.util.Scanner;

public class AtmSystem3 {
    static int pin = 3214;
    static double accountBalance = 10000;
    static double machineRemainingBalance = 5000;
    static final double MAX_DEPOSIT = 50000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        boolean authenticated = false;

        // Limit number of PIN attempts
        while (attempts < 3) {
            System.out.println("Please Enter Your ATM Pin:");
            if (sc.hasNextInt()) {
                int userPin = sc.nextInt();
                if (userPin == pin) {
                    authenticated = true;
                    break;
                } else {
                    System.out.println("Incorrect PIN. Try again.");
                    attempts++;
                }
            } else {
                System.out.println("Please enter a valid PIN.");
                sc.next(); // Clear invalid input
            }
        }

        if (!authenticated) {
            System.out.println("Too many incorrect attempts. Exiting...");
            return;
        }

        // Main menu
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Handle withdrawal process
                    System.out.println("Please Enter Amount to Withdraw:");
                    if (sc.hasNextInt()) {
                        int amount = sc.nextInt();
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
                    } else {
                        System.out.println("Please enter a valid amount to withdraw.");
                        sc.next(); // Clear invalid input
                    }
                    break;

                case 2:
                    // Handle deposit process
                    System.out.println("Please Enter Amount to Deposit:");
                    if (sc.hasNextInt()) {
                        int amount = sc.nextInt();
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
                    } else {
                        System.out.println("Please enter a valid amount to deposit.");
                        sc.next(); // Clear invalid input
                    }
                    break;

                case 3:
                    // Exit
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
