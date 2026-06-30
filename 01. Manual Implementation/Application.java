// Import Libraries

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Main Class
class Application {

    // Array Lists to Store Data as in Database
    public static ArrayList<Car> cars = new ArrayList<>();
    public static ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    public static ArrayList<Van> vans = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Rental> rentals = new ArrayList<>();

    // Method to Print Heading
    public static void heading() {
        System.out.println("===========================================");
        System.out.println("|PREMIUM RENTAL SERVICES MANAGEMENT SYSTEM|");
        System.out.println("===========================================\n");
    }

    // Main Menu Method
    public static void mainMenu() {

        heading();

        System.out.println("====================");
        System.out.println("|VEHICLE MANAGEMENT|");
        System.out.println("====================\n");

        System.out.println("01. Register a Vehicle");
        System.out.println("02. View Vehicles");
        System.out.println("03. Search Vehicle");
        System.out.println("04. Remove Vehicle\n");

        System.out.println("=====================");
        System.out.println("|CUSTOMER MANAGEMENT|");
        System.out.println("=====================\n");

        System.out.println("05. Register a Customer");
        System.out.println("06. View Customers");
        System.out.println("07. Search Customer");
        System.out.println("08. Remove Customer\n");

        System.out.println("==================");
        System.out.println("|RENTAL MANAGEMENT|");
        System.out.println("===================\n");

        System.out.println("09. Rent a Vehicle");
        System.out.println("10. Return a Vehicle\n");

        System.out.println("========");
        System.out.println("|REPORT|");
        System.out.println("========\n");

        System.out.println("11. Vehicle Report");
        System.out.println("12. Customer Report");
        System.out.println("13. Rental Report\n");

        System.out.println("14. Exit\n");

        askForChoice();

    }

    // Ask For a Choice Method
    public static void askForChoice() {

        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Enter your choice: ");

        try {
            int choice = scanner1.nextInt();
            if (choice > 14 || choice < 1) {
                System.out.println("Invalid choice!\n");
                scanner1.nextLine();
                askForChoice();
            } else {
                switch (choice) {
                    case 1:
                        registerVehcile();
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        //
                        break;
                    case 4:
                        //
                        break;
                    case 5:
                        //
                        break;
                    case 6:
                        //
                        break;
                    case 7:
                        //
                        break;
                    case 8:
                        //
                        break;
                    case 9:
                        //
                        break;
                    case 10:
                        //
                        break;
                    case 11:
                        //
                        break;
                    case 12:
                        //
                        break;
                    case 13:
                        //
                        break;
                    case 14:
                        clearConsole();
                        heading();
                        System.out.println("Programme terminated successfully!\n");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!\n");
            scanner1.nextLine();
            askForChoice();
        }

    }

    // Method to Register a Vehicle
    public static void registerVehcile() {

    }

    // Clear Console Method
    public static void clearConsole() {

        final String os = System.getProperty("os.name");

        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("Error : " + e);
        }

    }

    // Main Method
    public static void main(String[] args) {

        // Clear Console
        clearConsole();

        // Load Main Menu
        mainMenu();
    }

}