package project;
import java.util.Scanner;

public class User_Interface {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Registeration_and_Login userLogin = new Registeration_and_Login();
    private static final ATMservices accountService = new ATMservices();

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.println("\n1. Login\n2. Register\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                	register();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userLogin.login(username, password)) {
            System.out.println("Login successful!");
            int userId = getUserId(username);
            displayMenu(userId);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userLogin.register(username, password)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private static int getUserId(String username) {
      
        return 1; 
    }

    private static void displayMenu(int userId) {
        while (true) {
            System.out.println("\n1. Balance Inquiry\n2. Deposit\n3. Withdraw\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    double balance = accountService.getBalance(userId);
                    System.out.println("Current balance: " + balance);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    if (accountService.deposit(userId, depositAmount)) {
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Deposit failed.");
                    }
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    if (accountService.withdraw(userId, withdrawalAmount)) {
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Insufficient balance or withdrawal failed.");
                    }
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
