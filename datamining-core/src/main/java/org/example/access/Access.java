package org.example.access;
import java.util.*;

/**
 * This class has been created to temporarily track the process and control the access to the system.
 * It is not intended to be used in production.
 * It serves as a placeholder for future access control implementations.
 * Currently, we haven't started to implement the GUI yet, which means we can access the
 * system from the terminal and see if the login and registration processes work correctly.
 */
public class Access {

    private static Map<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {

        // This is a placeholder for the main method.
        // It is intended to be used for testing the access control system.
        Scanner scanner = new Scanner(System.in);
        welcome();
        handleMenu(scanner);


    }

    // Methods

    /**
     * This method displays a welcome message and the options available to the user.
     * It is intended to be used as the entry point for the access control system.
     */
    public static void welcome() {
        System.out.println("Welcome to the Access Control System!");
        System.out.println("Please choose an option:");
        System.out.println();
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println();
    }

    public static void handleMenu(Scanner scanner) {
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt(); // Read the initial choice
        while (choice != 3) {
            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);// Call login method here
                    break;
                default:
                    System.out.println("You have chosen " + choice);
                    System.out.println("Invalid choice. Please try again.");
            }

            welcome();
            choice = scanner.nextInt();
        }
    }

    public static void register(Scanner scanner) {
        // Placeholder for registration logic
        System.out.println("Enter ID: ");
        String ID = scanner.next();
        System.out.println("Enter Password: ");
        String password = scanner.next();
        System.out.println("Registration logic goes here for ID: " + ID + " with Password: " + password);
        // Simulate registration by adding to a user database
        if (userDatabase.containsKey(ID)) {
            System.out.println("ID already exists. Please try again.");
        } else {
            userDatabase.put(ID, password);
            System.out.println("Registration successful for ID: " + ID);
        }
    }

    public static void login(Scanner scanner) {
        // Placeholder for login logic
        System.out.println("Login logic goes here.");
        scanner = new Scanner(System.in);
        System.out.println("Enter ID: ");
        String ID = scanner.next();
        System.out.println("Enter Password: ");
        String password = scanner.next();
        System.out.println("Login logic goes here for ID: " + ID + " with Password: " + password);
        // Simulate login by checking the user database
        if (userDatabase.containsKey(ID) && userDatabase.get(ID).equals(password)) {
            System.out.println("Login successful for ID: " + ID);
        } else {
            System.out.println("Invalid ID or Password. Please try again.");
        }
    }


    public static void name(Scanner scanner) {
        // Placeholder for name logic
        System.out.println("Name logic goes here.");
        System.out.println("Enter your name: ");
        String name = scanner.next();
        System.out.println("Hello, " + name + "! Welcome to the Access Control System.");
    }

    public static void surname(Scanner scanner) {
        // Placeholder for surname logic
        System.out.println("Surname logic goes here.");
        System.out.println("Enter your surname: ");
        String surname = scanner.next();
        System.out.println("Hello, " + surname + "! Welcome to the Access Control System.");
    }

    public static void birthdate(Scanner scanner) {
        // Placeholder for birthdate logic
        System.out.println("Birthdate logic goes here.");
        System.out.println("Enter your birth year: ");
        String birthyear = scanner.next();
        System.out.println("Hello! Your birth year is " + birthyear + ". Welcome to the Access Control System.");
    }
}
