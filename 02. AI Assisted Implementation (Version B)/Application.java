import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Application.java
 * Entry point for the Premium Rental Services Management System.
 * Provides a console-based, menu-driven interface for managing
 * vehicles, customers, and rentals using in-memory ArrayList storage.
 */
public class Application {

    // ---------------------- In-Memory Storage ----------------------
    private static final ArrayList<Vehicle> vehicleList = new ArrayList<>();
    private static final ArrayList<Customer> customerList = new ArrayList<>();
    private static final ArrayList<Rental> rentalList = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // ==============================================================
    // MAIN METHOD
    // ==============================================================
    public static void main(String[] args) {
        int choice;

        do {
            clearConsole();
            displayHeading();
            System.out.println("MAIN MENU");
            System.out.println("-------------------------------------------");
            System.out.println("1. Vehicle Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Rental Management");
            System.out.println("4. Exit");
            System.out.println("-------------------------------------------");

            choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    vehicleManagementMenu();
                    break;
                case 2:
                    customerManagementMenu();
                    break;
                case 3:
                    rentalManagementMenu();
                    break;
                case 4:
                    System.out.println("\nThank you for using the Premium Rental Services");
                    System.out.println("Management System. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
                    pause();
            }
        } while (choice != 4);

        scanner.close();
    }

    // ==============================================================
    // CROSS-PLATFORM CONSOLE UTILITIES
    // ==============================================================
    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os != null && os.toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback: print blank lines if OS-level clear fails
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    private static void displayHeading() {
        System.out.println("=============================================");
        System.out.println("  PREMIUM RENTAL SERVICES MANAGEMENT SYSTEM  ");
        System.out.println("=============================================\n");
    }

    private static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // ==============================================================
    // INPUT HELPERS
    // ==============================================================
    private static int readIntInput(String prompt) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    private static double readDoubleInput(String prompt) {
        double value;
        while (true) {
            try {
                System.out.print(prompt);
                value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private static String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static boolean readBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y")) return true;
            if (input.equals("N")) return false;
            System.out.println("Invalid input! Please enter Y or N.");
        }
    }

    // ==============================================================
    // VEHICLE MANAGEMENT
    // ==============================================================
    private static void vehicleManagementMenu() {
        int choice;
        do {
            clearConsole();
            displayHeading();
            System.out.println("VEHICLE MANAGEMENT");
            System.out.println("-------------------------------------------");
            System.out.println("1. Register Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. Search Vehicle by ID");
            System.out.println("4. Remove Vehicle");
            System.out.println("5. Back to Main Menu");
            System.out.println("-------------------------------------------");

            choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerVehicle();
                    pause();
                    break;
                case 2:
                    viewAllVehicles();
                    pause();
                    break;
                case 3:
                    searchVehicleById();
                    pause();
                    break;
                case 4:
                    removeVehicle();
                    pause();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
                    pause();
            }
        } while (choice != 5);
    }

    private static void registerVehicle() {
        System.out.println("\n--- Register New Vehicle ---");
        System.out.println("Select Vehicle Type:");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Van");
        int type = readIntInput("Enter choice: ");

        if (type < 1 || type > 3) {
            System.out.println("Invalid vehicle type selected.");
            return;
        }

        String vehicleId = readStringInput("Enter Vehicle ID: ");
        if (findVehicleById(vehicleId) != null) {
            System.out.println("A vehicle with this ID already exists!");
            return;
        }
        String vehicleName = readStringInput("Enter Vehicle Name: ");
        double dailyRentalRate = readDoubleInput("Enter Daily Rental Rate: ");
        boolean availabilityStatus = readBooleanInput("Is the vehicle available for rent?");

        Vehicle vehicle;
        switch (type) {
            case 1:
                int seats = readIntInput("Enter Number of Seats: ");
                String fuelType = readStringInput("Enter Fuel Type: ");
                vehicle = new Car(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, seats, fuelType);
                break;
            case 2:
                int engineCapacity = readIntInput("Enter Engine Capacity (cc): ");
                vehicle = new Motorcycle(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, engineCapacity);
                break;
            default:
                int cargoCapacity = readIntInput("Enter Cargo Capacity (kg): ");
                vehicle = new Van(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, cargoCapacity);
                break;
        }

        vehicleList.add(vehicle);
        System.out.println("\nVehicle registered successfully!");
    }

    private static void viewAllVehicles() {
        System.out.println("\n--- All Registered Vehicles ---");
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (Vehicle v : vehicleList) {
            System.out.println(v);
        }
    }

    private static void searchVehicleById() {
        String id = readStringInput("\nEnter Vehicle ID to search: ");
        Vehicle vehicle = findVehicleById(id);
        if (vehicle != null) {
            System.out.println("\nVehicle Found:");
            System.out.println(vehicle);
        } else {
            System.out.println("\nNo vehicle found with ID: " + id);
        }
    }

    private static void removeVehicle() {
        String id = readStringInput("\nEnter Vehicle ID to remove: ");
        Vehicle vehicle = findVehicleById(id);
        if (vehicle != null) {
            vehicleList.remove(vehicle);
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("No vehicle found with ID: " + id);
        }
    }

    private static Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicleList) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

    // ==============================================================
    // CUSTOMER MANAGEMENT
    // ==============================================================
    private static void customerManagementMenu() {
        int choice;
        do {
            clearConsole();
            displayHeading();
            System.out.println("CUSTOMER MANAGEMENT");
            System.out.println("-------------------------------------------");
            System.out.println("1. Register Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer by ID");
            System.out.println("4. Remove Customer");
            System.out.println("5. Back to Main Menu");
            System.out.println("-------------------------------------------");

            choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerCustomer();
                    pause();
                    break;
                case 2:
                    viewAllCustomers();
                    pause();
                    break;
                case 3:
                    searchCustomerById();
                    pause();
                    break;
                case 4:
                    removeCustomer();
                    pause();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
                    pause();
            }
        } while (choice != 5);
    }

    private static void registerCustomer() {
        System.out.println("\n--- Register New Customer ---");
        String customerId = readStringInput("Enter Customer ID: ");
        if (findCustomerById(customerId) != null) {
            System.out.println("A customer with this ID already exists!");
            return;
        }
        String customerName = readStringInput("Enter Customer Name: ");
        String contactNo = readStringInput("Enter Contact No: ");
        String licenseNumber = readStringInput("Enter License Number: ");

        Customer customer = new Customer(customerId, customerName, contactNo, licenseNumber);
        customerList.add(customer);
        System.out.println("\nCustomer registered successfully!");
    }

    private static void viewAllCustomers() {
        System.out.println("\n--- All Registered Customers ---");
        if (customerList.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (Customer c : customerList) {
            System.out.println(c);
        }
    }

    private static void searchCustomerById() {
        String id = readStringInput("\nEnter Customer ID to search: ");
        Customer customer = findCustomerById(id);
        if (customer != null) {
            System.out.println("\nCustomer Found:");
            System.out.println(customer);
        } else {
            System.out.println("\nNo customer found with ID: " + id);
        }
    }

    private static void removeCustomer() {
        String id = readStringInput("\nEnter Customer ID to remove: ");
        Customer customer = findCustomerById(id);
        if (customer != null) {
            customerList.remove(customer);
            System.out.println("Customer removed successfully!");
        } else {
            System.out.println("No customer found with ID: " + id);
        }
    }

    private static Customer findCustomerById(String id) {
        for (Customer c : customerList) {
            if (c.getCustomerId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    // ==============================================================
    // RENTAL MANAGEMENT
    // ==============================================================
    private static void rentalManagementMenu() {
        int choice;
        do {
            clearConsole();
            displayHeading();
            System.out.println("RENTAL MANAGEMENT");
            System.out.println("-------------------------------------------");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Return a Vehicle");
            System.out.println("3. View All Rentals");
            System.out.println("4. Back to Main Menu");
            System.out.println("-------------------------------------------");

            choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    rentVehicle();
                    pause();
                    break;
                case 2:
                    returnVehicle();
                    pause();
                    break;
                case 3:
                    viewAllRentals();
                    pause();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
                    pause();
            }
        } while (choice != 4);
    }

    private static void rentVehicle() {
        System.out.println("\n--- Rent a Vehicle ---");

        String customerId = readStringInput("Enter Customer ID: ");
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("No customer found with ID: " + customerId);
            return;
        }

        String vehicleId = readStringInput("Enter Vehicle ID: ");
        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("No vehicle found with ID: " + vehicleId);
            return;
        }
        if (!vehicle.isAvailabilityStatus()) {
            System.out.println("This vehicle is currently not available for rent.");
            return;
        }

        LocalDate startDate = readDateInput("Enter Rental Start Date (yyyy-MM-dd): ");
        LocalDate endDate;
        while (true) {
            endDate = readDateInput("Enter Rental End Date (yyyy-MM-dd): ");
            if (!endDate.isAfter(startDate)) {
                System.out.println("End date must be after the start date.");
            } else {
                break;
            }
        }

        int numberOfRentalDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
        double rentalEstimatedCost = numberOfRentalDays * vehicle.getDailyRentalRate();
        double discount = vehicle.getDiscountRate();
        double rentalActualCost = rentalEstimatedCost - (rentalEstimatedCost * discount / 100);

        String rentalId = readStringInput("Enter Rental ID: ");
        if (findRentalById(rentalId) != null) {
            System.out.println("A rental with this ID already exists!");
            return;
        }

        Rental rental = new Rental(rentalId, customerId, vehicleId,
                startDate.format(DATE_FORMAT), endDate.format(DATE_FORMAT),
                numberOfRentalDays, rentalEstimatedCost, discount, rentalActualCost, "Active");

        rentalList.add(rental);
        vehicle.setAvailabilityStatus(false);

        System.out.println("\nRental created successfully!");
        System.out.println(rental);
    }

    private static void returnVehicle() {
        System.out.println("\n--- Return a Vehicle ---");
        String rentalId = readStringInput("Enter Rental ID: ");
        Rental rental = findRentalById(rentalId);

        if (rental == null) {
            System.out.println("No rental found with ID: " + rentalId);
            return;
        }
        if (rental.getRentalStatus().equalsIgnoreCase("Returned")) {
            System.out.println("This rental has already been marked as returned.");
            return;
        }

        rental.setRentalStatus("Returned");

        Vehicle vehicle = findVehicleById(rental.getVehicleId());
        if (vehicle != null) {
            vehicle.setAvailabilityStatus(true);
        }

        System.out.println("\nVehicle returned successfully!");
        System.out.println(rental);
    }

    private static void viewAllRentals() {
        System.out.println("\n--- All Rentals ---");
        if (rentalList.isEmpty()) {
            System.out.println("No rentals found.");
            return;
        }
        for (Rental r : rentalList) {
            System.out.println(r);
        }
    }

    private static Rental findRentalById(String id) {
        for (Rental r : rentalList) {
            if (r.getRentalId().equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }

    private static LocalDate readDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please use yyyy-MM-dd (e.g. 2026-07-13).");
            }
        }
    }
}