// Application : Car Rental Management System
// Method      : Manual Implementation
// Author      : A. W. W. A. Nipun Lakshan

// Import Libraries

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

// Main Class
class Application {

    // Array Lists to Store Data as in Database
    public static ArrayList<Car> cars = new ArrayList<>();
    public static ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    public static ArrayList<Van> vans = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Rental> rentalCars = new ArrayList<>();
    public static ArrayList<Rental> rentalVans = new ArrayList<>();
    public static ArrayList<Rental> rentalMotorcycles = new ArrayList<>();

    // Single shared Scanner for all console input
    private static final Scanner scanner = new Scanner(System.in);

    // Method to Print Heading
    public static void heading() {

        System.out.println("\n===========================================");
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

        System.out.println("===================");
        System.out.println("|RENTAL MANAGEMENT|");
        System.out.println("===================\n");

        System.out.println("09. Rent a Vehicle");
        System.out.println("10. Return a Vehicle");
        System.out.println("11. View all Rentals\n");

        System.out.println("12. Exit\n");

        askForChoice();

    }

    // Ask For a Choice Method
    public static void askForChoice() {

        System.out.print("Enter your choice: ");

        try {

            int choice = scanner.nextInt();

            if (choice > 12 || choice < 1) {

                System.out.println("Invalid choice!\n");
                scanner.nextLine();

                askForChoice();

            } else {

                switch (choice) {

                    case 1:
                        registerVehicle();
                        break;

                    case 2:
                        viewVehicles();
                        break;

                    case 3:
                        searchVehicle();
                        break;

                    case 4:
                        removeVehicle();
                        break;

                    case 5:
                        registerCustomer();
                        break;

                    case 6:
                        viewCustomers();
                        break;

                    case 7:
                        searchCustomer();
                        break;

                    case 8:
                        removeCustomer();
                        break;

                    case 9:
                        rentVehicle();
                        break;

                    case 10:
                        returnVehicle();
                        break;

                    case 11:
                        viewRentals();
                        break;

                    case 12:
                        clearConsole();
                        heading();
                        System.out.println("Programme terminated successfully!\n");
                        System.exit(0);
                        break;

                }

            }

        } catch (InputMismatchException e) {

            System.out.println("Invalid input!\n");
            scanner.nextLine();

            askForChoice();

        }

    }

    // ================================================================
    // Generic lookup helpers
    // ================================================================

    // Finds the index of a vehicle (car/van/motorcycle) by its ID, or -1 if not found
    private static <T extends Vehicle> int findVehicleIndex(List<T> vehicles, String vehicleId) {

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleId().equalsIgnoreCase(vehicleId)) {
                return i;
            }
        }

        return -1;

    }

    // Finds the index of a customer by ID, or -1 if not found
    private static int findCustomerIndex(String customerId) {

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equalsIgnoreCase(customerId)) {
                return i;
            }
        }

        return -1;

    }

    // Finds the index of a rental by ID within a given rentals list, or -1 if not found
    private static int findRentalIndex(List<Rental> rentals, String rentalId) {

        for (int i = 0; i < rentals.size(); i++) {
            if (rentals.get(i).getRentalId().equalsIgnoreCase(rentalId)) {
                return i;
            }
        }

        return -1;

    }

    // ================================================================
    // Generic table-printing helpers (replaces the repeated column-width
    // calculation and manual "|"-separated printing that used to be
    // duplicated in every "view" method)
    // ================================================================

    // Prints a table with the given column headers and rows. Every column is
    // sized to fit its widest header or cell, headers are left-aligned and
    // data cells are right-aligned, matching the original table style.
    private static void printTable(String[] columnNames, List<String[]> rows) {

        int columns = columnNames.length;
        int[] widths = new int[columns];

        for (int i = 0; i < columns; i++) {
            widths[i] = columnNames[i].length();
        }

        for (String[] row : rows) {
            for (int i = 0; i < columns; i++) {
                widths[i] = Math.max(widths[i], row[i].length());
            }
        }

        int totalWidth = columns + 1;
        for (int width : widths) {
            totalWidth += width;
        }

        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < totalWidth; i++) {
            separator.append("=");
        }

        System.out.println(separator);
        System.out.println(formatTableRow(columnNames, widths, true));
        System.out.println(separator);

        for (String[] row : rows) {
            System.out.println(formatTableRow(row, widths, false));
        }

        System.out.println(separator);

    }

    // Formats one table row, left-aligning headers and right-aligning data
    private static String formatTableRow(String[] values, int[] widths, boolean leftAlign) {

        StringBuilder row = new StringBuilder("|");

        for (int i = 0; i < values.length; i++) {
            row.append(String.format("%" + (leftAlign ? "-" : "") + widths[i] + "s", values[i]));
            row.append("|");
        }

        return row.toString();

    }

    // Builds the table rows for a list of cars
    private static List<String[]> buildCarRows(List<Car> carList) {

        List<String[]> rows = new ArrayList<>();

        for (Car car : carList) {
            rows.add(new String[]{
                    car.getVehicleId(),
                    car.getVehicleName(),
                    String.valueOf(car.getDailyRentalRate()),
                    String.valueOf(car.getAvailabilityStatus()),
                    String.valueOf(car.getNumberOfSeats()),
                    car.getFuelType()
            });
        }

        return rows;

    }

    // Builds the table rows for a list of vans
    private static List<String[]> buildVanRows(List<Van> vanList) {

        List<String[]> rows = new ArrayList<>();

        for (Van van : vanList) {
            rows.add(new String[]{
                    van.getVehicleId(),
                    van.getVehicleName(),
                    String.valueOf(van.getDailyRentalRate()),
                    String.valueOf(van.getAvailabilityStatus()),
                    String.valueOf(van.getCargoCapacity())
            });
        }

        return rows;

    }

    // Builds the table rows for a list of motorcycles
    private static List<String[]> buildMotorcycleRows(List<Motorcycle> motorcycleList) {

        List<String[]> rows = new ArrayList<>();

        for (Motorcycle motorcycle : motorcycleList) {
            rows.add(new String[]{
                    motorcycle.getVehicleId(),
                    motorcycle.getVehicleName(),
                    String.valueOf(motorcycle.getDailyRentalRate()),
                    String.valueOf(motorcycle.getAvailabilityStatus()),
                    String.valueOf(motorcycle.getEngineCapacity())
            });
        }

        return rows;

    }

    // Builds the table rows for a list of customers
    private static List<String[]> buildCustomerRows(List<Customer> customerList) {

        List<String[]> rows = new ArrayList<>();

        for (Customer customer : customerList) {
            rows.add(new String[]{
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getContactNo(),
                    customer.getLicenseNumber()
            });
        }

        return rows;

    }

    // Builds the table rows for a list of rentals
    private static List<String[]> buildRentalRows(List<Rental> rentalList) {

        List<String[]> rows = new ArrayList<>();

        for (Rental rent : rentalList) {
            rows.add(new String[]{
                    rent.getRentalId(),
                    rent.getCustomerId(),
                    rent.getVehicleId(),
                    rent.getRentalStartDate(),
                    rent.getRentalEndDate(),
                    String.valueOf(rent.getNumberOfRentalDays()),
                    String.valueOf(rent.getRentalEstimatedCost()),
                    String.valueOf(rent.getDiscount()),
                    String.valueOf(rent.getRentalActualCost()),
                    rent.getRentalStatus()
            });
        }

        return rows;

    }

    // Method to Register a Vehicle
    public static void registerVehicle() {

        clearConsole();
        heading();

        System.out.println("01. Register a Car");
        System.out.println("02. Register a Van");
        System.out.println("03. Register a Motorcycle");
        System.out.println("04. Main Menu\n");
        System.out.println("05. Exit\n");

        L1:
        while (true) {

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner.nextInt();

                if (choice > 5 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner.nextLine();

                } else {

                    switch (choice) {

                        case 1:
                            clearConsole();
                            heading();
                            registerCar();
                            break L1;

                        case 2:
                            clearConsole();
                            heading();
                            registerVan();
                            break L1;

                        case 3:
                            clearConsole();
                            heading();
                            registerMotorCycle();
                            break L1;

                        case 4:
                            mainMenu();
                            break L1;

                        case 5:
                            clearConsole();
                            heading();
                            System.out.println("Programme terminated successfully!\n");
                            System.exit(0);
                            break L1;

                    }

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid input!\n");
                scanner.nextLine();

            }

        }

    }

    // Method to Register a Car
    public static void registerCar() {

        clearConsole();
        heading();

        String vehicleId;
        String vehicleName;
        int dailyRentalRate;
        boolean availabilityStatus;
        int numberOfSeats;
        String fuelType;

        while (true) {
            System.out.print("Enter Vehicle ID          : ");
            vehicleId = scanner.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {

                if (findVehicleIndex(cars, vehicleId) != -1) {

                    System.out.println("Vehicle ID is already in use!\n");

                } else {

                    break;

                }

            } else {

                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");

            }

        }

        System.out.print("\nEnter Vehicle Name        : ");
        vehicleName = ((scanner.nextLine()).trim());

        while (true) {

            try {

                System.out.print("\nEnter Daily Rental Rate   : Rs. ");
                dailyRentalRate = scanner.nextInt();

                if (dailyRentalRate < 0) {
                    System.out.println("Invalid Daily Rental Rate!");

                } else {

                    scanner.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Daily Rental Rate!");
                scanner.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter availability status : ");
                availabilityStatus = scanner.nextBoolean();
                break;

            } catch (InputMismatchException e) {

                System.out.println("Invalid availability status!\n");
                scanner.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter Number of Seats     : ");
                numberOfSeats = scanner.nextInt();

                if (numberOfSeats < 3 || numberOfSeats > 5) {
                    System.out.println("Invalid Number of Seats!");

                } else {

                    scanner.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input!");
                scanner.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter Fuel Type           : ");
                fuelType = scanner.nextLine().trim();

                if (fuelType.equalsIgnoreCase("Diesel") || fuelType.equalsIgnoreCase("Petrol") || fuelType.equalsIgnoreCase("Hybrid")) {

                    break;

                } else {

                    System.out.println("Invalid Fuel Type!");

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input!");
                scanner.nextLine();

            }

        }

        cars.add(new Car(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, numberOfSeats, fuelType));

        askWantToExit();

    }

    // Method to Register a Van
    public static void registerVan() {

        clearConsole();
        heading();

        String vehicleId;
        String vehicleName;
        int dailyRentalRate;
        boolean availabilityStatus;
        int cargoCapacity;

        while (true) {

            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {

                if (findVehicleIndex(vans, vehicleId) != -1) {

                    System.out.println("Vehicle ID is already in use!\n");

                } else {

                    break;

                }

            } else {

                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");

            }

        }

        System.out.print("\nEnter Vehicle Name           : ");
        vehicleName = ((scanner.nextLine()).trim());

        while (true) {

            try {

                System.out.print("\nEnter Daily Rental Rate      : Rs. ");
                dailyRentalRate = scanner.nextInt();

                if (dailyRentalRate < 0) {

                    System.out.println("Invalid Daily Rental Rate!");

                } else {

                    scanner.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Daily Rental Rate!");
                scanner.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter availability status    : ");
                availabilityStatus = scanner.nextBoolean();
                break;

            } catch (InputMismatchException e) {

                System.out.println("Invalid availability status!\n");
                scanner.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter Cargo Capacity (In Kg) : ");
                cargoCapacity = scanner.nextInt();

                if (cargoCapacity < 0 || cargoCapacity > 1000) {

                    System.out.println("Invalid Cargo Capacity!");

                } else {

                    scanner.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input!");
                scanner.nextLine();

            }

        }

        vans.add(new Van(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, cargoCapacity));

        askWantToExit();

    }

    // Method to Register a Motorcycle
    public static void registerMotorCycle() {

        clearConsole();
        heading();

        String vehicleId;
        String vehicleName;
        int dailyRentalRate;
        boolean availabilityStatus;
        int engineCapacity;

        while (true) {

            System.out.print("Enter Vehicle ID              : ");
            vehicleId = scanner.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {

                if (findVehicleIndex(motorcycles, vehicleId) != -1) {

                    System.out.println("Vehicle ID is already in use!\n");

                } else {

                    break;

                }
            } else {

                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");

            }

        }

        System.out.print("\nEnter Vehicle Name           : ");
        vehicleName = ((scanner.nextLine()).trim());

        while (true) {

            try {

                System.out.print("\nEnter Daily Rental Rate       : Rs. ");
                dailyRentalRate = scanner.nextInt();

                if (dailyRentalRate < 0) {

                    System.out.println("Invalid Daily Rental Rate!");

                } else {

                    scanner.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Daily Rental Rate!");
                scanner.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter availability status     : ");
                availabilityStatus = scanner.nextBoolean();
                break;

            } catch (InputMismatchException e) {

                System.out.println("Invalid availability status!\n");
                scanner.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter Engine Capacity (In CC) : ");
                engineCapacity = scanner.nextInt();

                if (engineCapacity < 100 || engineCapacity > 2000) {

                    System.out.println("Invalid Engine Capacity!");

                } else {

                    scanner.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input!");
                scanner.nextLine();

            }

        }

        motorcycles.add(new Motorcycle(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, engineCapacity));

        askWantToExit();

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

    // Method to View Vehicles
    public static void viewVehicles() {

        clearConsole();
        heading();

        System.out.println("01. View all Cars");
        System.out.println("02. View all Vans");
        System.out.println("03. View all Motorcycle");
        System.out.println("04. View all Vehicles");
        System.out.println("05. Main Menu\n");

        System.out.println("06. Exit\n");

        L1:
        while (true) {

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner.nextInt();

                if (choice > 6 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner.nextLine();

                } else {

                    switch (choice) {

                        case 1:
                            clearConsole();
                            heading();
                            viewAllCars();
                            break L1;

                        case 2:
                            clearConsole();
                            heading();
                            viewAllVans();
                            break L1;

                        case 3:
                            clearConsole();
                            heading();
                            viewAllMotorcycles();
                            break L1;

                        case 4:
                            clearConsole();
                            heading();
                            viewAllVehicles();
                            break L1;

                        case 5:
                            clearConsole();
                            mainMenu();
                            break L1;

                        case 6:
                            clearConsole();
                            heading();
                            System.out.println("Programme terminated successfully!\n");
                            System.exit(0);
                            break L1;

                    }

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid input!\n");
                scanner.nextLine();

            }

        }

    }

    // Method to View All Cars
    public static void viewAllCars() {

        clearConsole();
        heading();

        String[] columnNames = {"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Number of Seats", "Fuel Type"};

        printTable(columnNames, buildCarRows(cars));

        askWantToExit();

    }

    // Method to View All Vans
    public static void viewAllVans() {

        clearConsole();
        heading();

        String[] columnNames = {"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Cargo Capacity (In Kg)"};

        printTable(columnNames, buildVanRows(vans));

        askWantToExit();

    }

    // Method to View All Motorcycles
    public static void viewAllMotorcycles() {

        clearConsole();
        heading();

        String[] columnNames = {"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Engine Capacity (In CC)"};

        printTable(columnNames, buildMotorcycleRows(motorcycles));

        askWantToExit();

    }

    // Method to ask whether you want to exit or not
    public static void askWantToExit() {

        System.out.print("\nDo you want to exit (Y/N): ");
        String input = scanner.nextLine();

        if ((input.equalsIgnoreCase("Y")) || (input.equalsIgnoreCase("N"))) {
            if (input.equalsIgnoreCase("N")) {
                clearConsole();
                mainMenu();
            } else {
                clearConsole();
                heading();
                System.out.println("Programme terminated successfully.");
                System.exit(0);
            }
        } else {
            System.out.println("Invalid Input!");
            askWantToExit();
        }

    }

    // Method to View All Vehicles
    public static void viewAllVehicles() {

        clearConsole();
        heading();

        System.out.println("========");
        System.out.println("All Cars");
        System.out.println("========\n");

        printTable(new String[]{"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Number of Seats", "Fuel Type"}, buildCarRows(cars));

        System.out.println("\n========");
        System.out.println("All Vans");
        System.out.println("========\n");

        printTable(new String[]{"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Cargo Capacity (In Kg)"}, buildVanRows(vans));

        System.out.println("\n===============");
        System.out.println("All Motorcycles");
        System.out.println("===============\n");

        printTable(new String[]{"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Engine Capacity (In CC)"}, buildMotorcycleRows(motorcycles));

        askWantToExit();

    }

    // Method to Search a Vehicle
    public static void searchVehicle() {

        clearConsole();
        heading();
        System.out.println("01. Search a Car");
        System.out.println("02. Search a Van");
        System.out.println("03. Search a Motorcycle");
        System.out.println("04. Main Menu\n");

        System.out.println("05. Exit\n");

        L1:
        while (true) {

            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                if (choice > 5 || choice < 1) {
                    System.out.println("Invalid choice!\n");
                    scanner.nextLine();
                } else {
                    switch (choice) {
                        case 1:
                            clearConsole();
                            heading();
                            searchCar();
                            break L1;
                        case 2:
                            clearConsole();
                            heading();
                            searchVan();
                            break L1;
                        case 3:
                            clearConsole();
                            heading();
                            searchMotorcycle();
                            break L1;
                        case 4:
                            clearConsole();
                            mainMenu();
                            break L1;
                        case 5:
                            clearConsole();
                            heading();
                            System.out.println("Programme terminated successfully!\n");
                            System.exit(0);
                            break L1;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!\n");
                scanner.nextLine();
            }
        }
    }

    // Method to Search a Car by ID
    public static void searchCar() {
        clearConsole();
        heading();

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                int index = findVehicleIndex(cars, vehicleId);

                if (index != -1) {
                    clearConsole();
                    heading();
                    System.out.println("Vehicle found!\n");
                    System.out.println("01. Car ID             : " + cars.get(index).getVehicleId());
                    System.out.println("02. Car Name           : " + cars.get(index).getVehicleName());
                    System.out.println("03. Daily Rental Rate  : Rs. " + cars.get(index).getDailyRentalRate());
                    System.out.println("04. Availablity Status : " + cars.get(index).getAvailabilityStatus());
                    System.out.println("05. Number of Seats    : " + cars.get(index).getNumberOfSeats());
                    System.out.println("06. Fuel Type          : " + cars.get(index).getFuelType());

                    askWantToExit();

                    break;

                } else {
                    System.out.println("Vehicle isn't registered yet!\n");
                }
            } else {
                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");
            }
        }

    }

    // Method to Search a Van by ID
    public static void searchVan() {
        clearConsole();
        heading();

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                int index = findVehicleIndex(vans, vehicleId);

                if (index != -1) {
                    clearConsole();
                    heading();
                    System.out.println("Vehicle found!\n");
                    System.out.println("01. Van ID             : " + vans.get(index).getVehicleId());
                    System.out.println("02. Van Name           : " + vans.get(index).getVehicleName());
                    System.out.println("03. Daily Rental Rate  : Rs. " + vans.get(index).getDailyRentalRate());
                    System.out.println("04. Availablity Status : " + vans.get(index).getAvailabilityStatus());
                    System.out.println("05. Cargo Capacity     : " + vans.get(index).getCargoCapacity() + "Kg");

                    askWantToExit();

                    break;

                } else {
                    System.out.println("Vehicle isn't registered yet!\n");
                }
            } else {
                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");
            }
        }

    }

    // Method to Search a Motorcycle by ID
    public static void searchMotorcycle() {
        clearConsole();
        heading();

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                int index = findVehicleIndex(motorcycles, vehicleId);

                if (index != -1) {
                    clearConsole();
                    heading();
                    System.out.println("Vehicle found!\n");
                    System.out.println("01. Motorcycle ID      : " + motorcycles.get(index).getVehicleId());
                    System.out.println("02. Motorcycle Name    : " + motorcycles.get(index).getVehicleName());
                    System.out.println("03. Daily Rental Rate  : Rs. " + motorcycles.get(index).getDailyRentalRate());
                    System.out.println("04. Availablity Status : " + motorcycles.get(index).getAvailabilityStatus());
                    System.out.println("05. Engine Capacity    : " + motorcycles.get(index).getEngineCapacity() + "CC");

                    askWantToExit();

                    break;

                } else {
                    System.out.println("Vehicle isn't registered yet!\n");
                }
            } else {
                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");
            }
        }

    }

    // Method to Remove a Vehicle
    public static void removeVehicle() {

        clearConsole();
        heading();

        System.out.println("01. Remove a Car");
        System.out.println("02. Remove a Van");
        System.out.println("03. Remove a Motorcycle");
        System.out.println("04. Main Menu\n");

        System.out.println("05. Exit\n");

        L1:
        while (true) {

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner.nextInt();

                if (choice > 5 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner.nextLine();

                } else {

                    switch (choice) {

                        case 1:
                            clearConsole();
                            heading();
                            removeCar();
                            break L1;

                        case 2:
                            clearConsole();
                            heading();
                            removeVan();
                            break L1;

                        case 3:
                            clearConsole();
                            heading();
                            removeMotorcycle();
                            break L1;

                        case 4:
                            clearConsole();
                            mainMenu();
                            break L1;

                        case 5:
                            clearConsole();
                            heading();
                            System.out.println("Programme terminated successfully!\n");
                            System.exit(0);
                            break L1;

                    }

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid input!\n");
                scanner.nextLine();

            }

        }

    }

    // Method to Remove a Car
    public static void removeCar() {

        clearConsole();
        heading();

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                int index = findVehicleIndex(cars, vehicleId);

                if (index != -1) {
                    clearConsole();
                    heading();
                    cars.remove(index);
                    System.out.println("Car removed from the system!");

                    askWantToExit();

                    break;

                } else {
                    System.out.println("Vehicle isn't registered yet!\n");
                }
            } else {
                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");
            }
        }

    }

    // Method to Remove a Motorcycle
    public static void removeMotorcycle() {

        clearConsole();
        heading();

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                int index = findVehicleIndex(motorcycles, vehicleId);

                if (index != -1) {
                    clearConsole();
                    heading();
                    motorcycles.remove(index);
                    System.out.println("Motorcycle removed from the system!");

                    askWantToExit();

                    break;

                } else {
                    System.out.println("Vehicle isn't registered yet!\n");
                }
            } else {
                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");
            }
        }

    }

    // Method to Remove a Van
    public static void removeVan() {

        clearConsole();
        heading();

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                int index = findVehicleIndex(vans, vehicleId);

                if (index != -1) {
                    clearConsole();
                    heading();
                    vans.remove(index);
                    System.out.println("Van removed from the system!");

                    askWantToExit();

                    break;

                } else {
                    System.out.println("Vehicle isn't registered yet!\n");
                }
            } else {
                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");
            }
        }

    }

    // Method to Register a Customer
    public static void registerCustomer() {

        clearConsole();
        heading();

        String customerId;
        String customerName;
        String contactNo;
        String licenseNumber;

        while (true) {
            System.out.print("Enter Customer ID               : ");
            customerId = scanner.nextLine().trim();

            if (customerId.matches("^C\\d{3}$")) {

                if (findCustomerIndex(customerId) != -1) {

                    System.out.println("Customer ID is already in use!\n");

                } else {

                    break;

                }

            } else {

                System.out.println("Invalid Customer ID (Ex: - C001)\n");

            }

        }

        System.out.print("\nEnter Customer Name             : ");
        customerName = ((scanner.nextLine()).trim());

        while (true) {
            System.out.print("\nEnter Customer Contact Number   : ");
            contactNo = scanner.nextLine().trim();

            if (contactNo.matches("^07\\d{8}$")) {
                break;

            } else {
                System.out.println("Invalid Contact Number (Ex: - 0742515014)");

            }

        }

        while (true) {
            System.out.print("\nEnter Customer License's Number : ");
            licenseNumber = scanner.nextLine().trim();

            if (licenseNumber.matches("^[A-Z]{2}\\d{6}$")) {
                break;

            } else {
                System.out.println("Invalid License Number (Ex: - AB123456)");

            }

        }

        customers.add(new Customer(customerId, customerName, contactNo, licenseNumber));

        askWantToExit();

    }

    // Method to View Customers
    public static void viewCustomers() {

        clearConsole();
        heading();

        String[] columnNames = {"Customer ID", "Customer Name", "Contact No", "License Number"};

        printTable(columnNames, buildCustomerRows(customers));

        askWantToExit();
    }

    // Method to Search Customers
    public static void searchCustomer() {
        clearConsole();
        heading();

        String customerId;

        while (true) {
            System.out.print("Enter Customer ID             : ");
            customerId = scanner.nextLine().trim();
            if (customerId.matches("^C\\d{3}$")) {

                int index = findCustomerIndex(customerId);

                if (index != -1) {
                    clearConsole();
                    heading();
                    System.out.println("Customer found!\n");
                    System.out.println("01. Customer ID             : " + customers.get(index).getCustomerId());
                    System.out.println("02. Customer Name           : " + customers.get(index).getCustomerName());
                    System.out.println("03. Customer Contact No     : " + customers.get(index).getContactNo());
                    System.out.println("04. Customer License Number : " + customers.get(index).getLicenseNumber());

                    askWantToExit();

                    break;

                } else {
                    System.out.println("Customer isn't registered yet!\n");
                }
            } else {
                System.out.println("Invalid Customer ID (Ex: - C001)\n");
            }
        }
    }

    // Method to Remove Customers
    public static void removeCustomer() {
        clearConsole();
        heading();

        String customerId;

        while (true) {
            System.out.print("Enter Customer ID             : ");
            customerId = scanner.nextLine().trim();
            if (customerId.matches("^C\\d{3}$")) {

                int index = findCustomerIndex(customerId);

                if (index != -1) {
                    clearConsole();
                    heading();
                    customers.remove(index);
                    System.out.println("Customer removed from the system!");

                    askWantToExit();

                    break;

                } else {
                    System.out.println("Customer isn't registered yet!\n");
                }
            } else {
                System.out.println("Invalid Customer ID (Ex: - C001)\n");
            }
        }
    }

    // Method to rent a vehicle
    public static void rentVehicle() {
        clearConsole();
        heading();

        System.out.println("01. Rent a Car");
        System.out.println("02. Rent a Van");
        System.out.println("03. Rent a Motorcycle");
        System.out.println("04. Main Menu\n");
        System.out.println("05. Exit\n");

        L1:
        while (true) {

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner.nextInt();

                if (choice > 5 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner.nextLine();

                } else {

                    switch (choice) {

                        case 1:
                            clearConsole();
                            heading();
                            rentCar();
                            break L1;

                        case 2:
                            clearConsole();
                            heading();
                            rentVan();
                            break L1;

                        case 3:
                            clearConsole();
                            heading();
                            rentMotorcycle();
                            break L1;

                        case 4:
                            mainMenu();
                            break L1;

                        case 5:
                            clearConsole();
                            heading();
                            System.out.println("Programme terminated successfully!\n");
                            System.exit(0);
                            break L1;

                    }

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid input!\n");
                scanner.nextLine();

            }

        }
    }

    // Method to rent a car
    public static void rentCar() {
        clearConsole();
        heading();

        String rentalId;
        String customerId;
        String vehicleId;
        String rentalStartDate;
        String rentalEndDate;
        int numberOfRentalDays;
        int rentalEstimatedCost = -1;

        // Rental ID
        while (true) {
            System.out.print("Enter Rental ID          : ");
            rentalId = scanner.nextLine().trim();

            if (rentalId.matches("^R\\d{3}$")) {
                if (findRentalIndex(rentalCars, rentalId) != -1) {
                    System.out.println("Rental ID is already in use!\n");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid Rental ID (Ex: - R001)\n");
            }
        }

        // Customer ID
        while (true) {
            System.out.print("\nEnter Customer ID        : ");
            customerId = scanner.nextLine().trim();

            if (customerId.matches("^C\\d{3}$")) {
                if (findCustomerIndex(customerId) != -1) {
                    break;
                } else {
                    System.out.println("Customer has not been registered yet!");
                }
            } else {
                System.out.println("Invalid Customer ID (Ex: - C001)");
            }
        }

        // Vehicle ID
        while (true) {
            System.out.print("\nEnter Vehicle ID         : ");
            vehicleId = scanner.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {
                int index = findVehicleIndex(cars, vehicleId);

                if (index != -1) {
                    if (cars.get(index).getAvailabilityStatus()) {
                        System.out.println("\nChoose Vehicle Name      : " + cars.get(index).getVehicleName());
                        cars.get(index).setAvailabilityStatus(false);
                        break;
                    } else {
                        System.out.println("Vehicle isn't available at the moment!");
                    }
                } else {
                    System.out.println("Vehicle has not been registered yet!");
                }
            } else {
                System.out.println("Invalid Vehicle ID (Ex: - V001)");
            }
        }

        // Rental Start Date
        while (true) {

            System.out.print("\nEnter Rental Start Date  : ");
            rentalStartDate = scanner.nextLine().trim();

            if (rentalStartDate.matches("^\\d{4}\\.\\d{2}\\.\\d{2}$")) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    LocalDate.parse(rentalStartDate, formatter);
                    break;

                } catch (DateTimeParseException e) {
                    System.out.println("Invalid Rental Start Date!");
                }

            } else {
                System.out.println("Invalid Rental Start Date (Ex: - 2026.01.01)");

            }

        }

        // Rental End Date & Rental Days
        while (true) {

            System.out.print("\nEnter Rental End Date    : ");
            rentalEndDate = scanner.nextLine().trim();

            if (rentalEndDate.matches("^\\d{4}\\.\\d{2}\\.\\d{2}$")) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    LocalDate startDate = LocalDate.parse(rentalStartDate, formatter);
                    LocalDate endDate = LocalDate.parse(rentalEndDate, formatter);
                    if (endDate.isBefore(startDate)) {
                        System.out.println("Rental End Date cannot be earlier than Rental Start Date!");
                    } else if (endDate.isEqual(startDate)) {
                        System.out.println("Rental End Date cannot be same as Rental Start Date!");
                    } else {
                        numberOfRentalDays = (int) (ChronoUnit.DAYS.between(startDate, endDate));
                        System.out.println("\nPeriod of Rental Days    : " + numberOfRentalDays);
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid Rental End Date!");
                }

            } else {
                System.out.println("Invalid Rental End Date (Ex: - 2026.01.01)");

            }

        }

        // Estimated Rental Cost
        int carIndex = findVehicleIndex(cars, vehicleId);
        if (carIndex != -1) {
            rentalEstimatedCost = numberOfRentalDays * cars.get(carIndex).getDailyRentalRate();
            System.out.println("\nRental Estimated Cost    : Rs. " + rentalEstimatedCost);
        }

        rentalCars.add(new Rental(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, numberOfRentalDays, rentalEstimatedCost, -1, -1, "Pending"));
        System.out.println("\nCar rental has been successfully recorded!");

        askWantToExit();

    }

    // Method to rent a van
    public static void rentVan() {
        clearConsole();
        heading();

        String rentalId;
        String customerId;
        String vehicleId;
        String rentalStartDate;
        String rentalEndDate;
        int numberOfRentalDays;
        int rentalEstimatedCost = -1;

        // Rental ID
        while (true) {
            System.out.print("Enter Rental ID          : ");
            rentalId = scanner.nextLine().trim();

            if (rentalId.matches("^R\\d{3}$")) {
                if (findRentalIndex(rentalVans, rentalId) != -1) {
                    System.out.println("Rental ID is already in use!\n");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid Rental ID (Ex: - R001)\n");
            }
        }

        // Customer ID
        while (true) {
            System.out.print("\nEnter Customer ID        : ");
            customerId = scanner.nextLine().trim();

            if (customerId.matches("^C\\d{3}$")) {
                if (findCustomerIndex(customerId) != -1) {
                    break;
                } else {
                    System.out.println("Customer has not been registered yet!");
                }
            } else {
                System.out.println("Invalid Customer ID (Ex: - C001)");
            }
        }

        // Vehicle ID
        while (true) {
            System.out.print("\nEnter Vehicle ID         : ");
            vehicleId = scanner.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {
                int index = findVehicleIndex(vans, vehicleId);

                if (index != -1) {
                    if (vans.get(index).getAvailabilityStatus()) {
                        System.out.println("\nChoose Vehicle Name      : " + vans.get(index).getVehicleName());
                        vans.get(index).setAvailabilityStatus(false);
                        break;
                    } else {
                        System.out.println("Vehicle isn't available at the moment!");
                    }
                } else {
                    System.out.println("Vehicle has not been registered yet!");
                }
            } else {
                System.out.println("Invalid Vehicle ID (Ex: - V001)");
            }
        }

        // Rental Start Date
        while (true) {

            System.out.print("\nEnter Rental Start Date  : ");
            rentalStartDate = scanner.nextLine().trim();

            if (rentalStartDate.matches("^\\d{4}\\.\\d{2}\\.\\d{2}$")) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    LocalDate.parse(rentalStartDate, formatter);
                    break;

                } catch (DateTimeParseException e) {
                    System.out.println("Invalid Rental Start Date!");
                }

            } else {
                System.out.println("Invalid Rental Start Date (Ex: - 2026.01.01)\n");

            }

        }

        // Rental End Date & Rental Days
        while (true) {

            System.out.print("\nEnter Rental End Date    : ");
            rentalEndDate = scanner.nextLine().trim();

            if (rentalEndDate.matches("^\\d{4}\\.\\d{2}\\.\\d{2}$")) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    LocalDate startDate = LocalDate.parse(rentalStartDate, formatter);
                    LocalDate endDate = LocalDate.parse(rentalEndDate, formatter);
                    if (endDate.isBefore(startDate)) {
                        System.out.println("Rental End Date cannot be earlier than Rental Start Date!");
                    } else if (endDate.isEqual(startDate)) {
                        System.out.println("Rental End Date cannot be same as Rental Start Date!");
                    } else {
                        numberOfRentalDays = (int) (ChronoUnit.DAYS.between(startDate, endDate));
                        System.out.println("\nPeriod of Rental Days    : " + numberOfRentalDays);
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid Rental End Date (Ex: 2026.01.01)");
                }

            } else {
                System.out.println("Invalid Rental End Date (Ex: - 2026.01.01)");

            }

        }

        // Estimated Rental Cost
        int vanIndex = findVehicleIndex(vans, vehicleId);
        if (vanIndex != -1) {
            rentalEstimatedCost = numberOfRentalDays * vans.get(vanIndex).getDailyRentalRate();
            System.out.println("\nRental Estimated Cost    : Rs. " + rentalEstimatedCost);
        }

        rentalVans.add(new Rental(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, numberOfRentalDays, rentalEstimatedCost, -1, -1, "Pending"));
        System.out.println("\nVan rental has been successfully recorded!");

        askWantToExit();

    }

    // Method to rent a Motorcycle
    public static void rentMotorcycle() {
        clearConsole();
        heading();

        String rentalId;
        String customerId;
        String vehicleId;
        String rentalStartDate;
        String rentalEndDate;
        int numberOfRentalDays;
        int rentalEstimatedCost = -1;

        // Rental ID
        while (true) {
            System.out.print("Enter Rental ID          : ");
            rentalId = scanner.nextLine().trim();

            if (rentalId.matches("^R\\d{3}$")) {
                if (findRentalIndex(rentalMotorcycles, rentalId) != -1) {
                    System.out.println("Rental ID is already in use!\n");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid Rental ID (Ex: - R001)\n");
            }
        }

        // Customer ID
        while (true) {
            System.out.print("\nEnter Customer ID        : ");
            customerId = scanner.nextLine().trim();

            if (customerId.matches("^C\\d{3}$")) {
                if (findCustomerIndex(customerId) != -1) {
                    break;
                } else {
                    System.out.println("Customer has not been registered yet!\n");
                }
            } else {
                System.out.println("Invalid Customer ID (Ex: - C001)\n");
            }
        }

        // Vehicle ID
        while (true) {
            System.out.print("\nEnter Vehicle ID         : ");
            vehicleId = scanner.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {
                int index = findVehicleIndex(motorcycles, vehicleId);

                if (index != -1) {
                    if (motorcycles.get(index).getAvailabilityStatus()) {
                        System.out.println("\nChoose Vehicle Name      : " + motorcycles.get(index).getVehicleName());
                        motorcycles.get(index).setAvailabilityStatus(false);
                        break;
                    } else {
                        System.out.println("Vehicle isn't available at the moment!\n");
                    }
                } else {
                    System.out.println("Vehicle has not been registered yet!\n");
                }
            } else {
                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");
            }
        }

        // Rental Start Date
        while (true) {

            System.out.print("\nEnter Rental Start Date  : ");
            rentalStartDate = scanner.nextLine().trim();

            if (rentalStartDate.matches("^\\d{4}\\.\\d{2}\\.\\d{2}$")) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    LocalDate.parse(rentalStartDate, formatter);
                    break;

                } catch (DateTimeParseException e) {
                    System.out.println("Invalid Rental Start Date (Ex: - 2026.01.01)");
                }

            } else {
                System.out.println("Invalid Rental Start Date (Ex: - 2026.01.01)\n");

            }

        }

        // Rental End Date & Rental Days
        while (true) {

            System.out.print("\nEnter Rental End Date    : ");
            rentalEndDate = scanner.nextLine().trim();

            if (rentalEndDate.matches("^\\d{4}\\.\\d{2}\\.\\d{2}$")) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                    LocalDate startDate = LocalDate.parse(rentalStartDate, formatter);
                    LocalDate endDate = LocalDate.parse(rentalEndDate, formatter);
                    if (endDate.isBefore(startDate)) {
                        System.out.println("Rental End Date cannot be earlier than Rental Start Date!");
                    } else if (endDate.isEqual(startDate)) {
                        System.out.println("Rental End Date cannot be same as Rental Start Date!");
                    } else {
                        numberOfRentalDays = (int) (ChronoUnit.DAYS.between(startDate, endDate));
                        System.out.println("\nPeriod of Rental Days    : " + numberOfRentalDays);
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid Rental End Date!");
                }

            } else {
                System.out.println("Invalid Rental End Date (Ex: - 2026.01.01)");

            }

        }

        // Estimated Rental Cost
        int motorcycleIndex = findVehicleIndex(motorcycles, vehicleId);
        if (motorcycleIndex != -1) {
            rentalEstimatedCost = numberOfRentalDays * motorcycles.get(motorcycleIndex).getDailyRentalRate();
            System.out.println("\nRental Estimated Cost    : Rs. " + rentalEstimatedCost);
        }

        rentalMotorcycles.add(new Rental(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, numberOfRentalDays, rentalEstimatedCost, -1, -1, "Pending"));
        System.out.println("\nMotorcycle rental has been successfully recorded!");

        askWantToExit();

    }

    // Method to Return a Vehicle
    public static void returnVehicle() {
        clearConsole();
        heading();

        System.out.println("01. Return a Car");
        System.out.println("02. Return Van");
        System.out.println("03. Return a Motorcycle");
        System.out.println("04. Main Menu\n");
        System.out.println("05. Exit\n");

        L1:
        while (true) {

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner.nextInt();

                if (choice > 5 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner.nextLine();

                } else {

                    switch (choice) {

                        case 1:
                            clearConsole();
                            heading();
                            returnCar();
                            break L1;

                        case 2:
                            clearConsole();
                            heading();
                            returnVan();
                            break L1;

                        case 3:
                            clearConsole();
                            heading();
                            returnMotorcycle();
                            break L1;

                        case 4:
                            mainMenu();
                            break L1;

                        case 5:
                            clearConsole();
                            heading();
                            System.out.println("Programme terminated successfully!\n");
                            System.exit(0);
                            break L1;

                    }

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid input!\n");
                scanner.nextLine();

            }

        }

    }

    // Method to return a car
    public static void returnCar() {
        clearConsole();
        heading();

        String rentalId;
        int discount;
        int rentalActualCost;

        // Rental ID
        while (true) {
            System.out.print("Enter Rental ID          : ");
            rentalId = scanner.nextLine().trim();

            if (rentalId.matches("^R\\d{3}$")) {

                int index = findRentalIndex(rentalCars, rentalId);

                if (index != -1 && rentalCars.get(index).getRentalStatus().equals("Completed")) {
                    System.out.println("Rental is already completed!\n");

                } else if (index != -1) {
                    clearConsole();
                    heading();

                    System.out.println("Rental ID is found!\n");

                    System.out.println("01. Car Rental ID         : " + rentalCars.get(index).getRentalId());
                    System.out.println("02. Customer ID           : " + rentalCars.get(index).getCustomerId());
                    System.out.println("03. Vehicle ID            : " + rentalCars.get(index).getVehicleId());
                    System.out.println("04. Rental Start Date     : " + rentalCars.get(index).getRentalStartDate());
                    System.out.println("05. Rental End Date       : " + rentalCars.get(index).getRentalEndDate());
                    System.out.println("06. Number of Rental Days : " + rentalCars.get(index).getNumberOfRentalDays());
                    System.out.println("07. Rental Estimated Cost : " + rentalCars.get(index).getRentalEstimatedCost());

                    // Header
                    System.out.println("\n=============");
                    System.out.println("Return a Car");
                    System.out.println("=============\n");

                    // Discount Rate
                    discount = (int) Math.round((rentalCars.get(index).getRentalEstimatedCost() * Car.DISCOUNT_RATE) / 100.0);
                    System.out.println("01. Car Rental Discount Rate : " + discount);

                    // Actual Cost
                    rentalActualCost = (rentalCars.get(index).getRentalEstimatedCost() - discount);
                    System.out.println("02. Car Rental Actual Rate   : " + rentalActualCost);

                    // Status Update
                    rentalCars.get(index).setDiscount(discount);
                    rentalCars.get(index).setRentalActualCost(rentalActualCost);
                    rentalCars.get(index).setRentalStatus("Completed");

                    int carIndex = findVehicleIndex(cars, rentalCars.get(index).getVehicleId());
                    if (carIndex != -1) {
                        cars.get(carIndex).setAvailabilityStatus(true);
                    }

                    System.out.println("\nCar Rental Return Processed Successfully!\n");
                    break;

                } else {
                    System.out.println("Rental ID is not found!\n");
                }

            } else {
                System.out.println("Invalid Rental ID (Ex: - R001)\n");
            }

        }

        askWantToExit();

    }

    // Method to return a Van
    public static void returnVan() {
        clearConsole();
        heading();

        String rentalId;
        int discount;
        int rentalActualCost;

        // Rental ID
        while (true) {
            System.out.print("Enter Rental ID          : ");
            rentalId = scanner.nextLine().trim();

            if (rentalId.matches("^R\\d{3}$")) {

                int index = findRentalIndex(rentalVans, rentalId);

                if (index != -1 && rentalVans.get(index).getRentalStatus().equals("Completed")) {
                    System.out.println("Rental is already completed!\n");

                } else if (index != -1) {
                    clearConsole();
                    heading();

                    System.out.println("Rental ID is found!\n");

                    System.out.println("01. Van Rental ID         : " + rentalVans.get(index).getRentalId());
                    System.out.println("02. Customer ID           : " + rentalVans.get(index).getCustomerId());
                    System.out.println("03. Vehicle ID            : " + rentalVans.get(index).getVehicleId());
                    System.out.println("04. Rental Start Date     : " + rentalVans.get(index).getRentalStartDate());
                    System.out.println("05. Rental End Date       : " + rentalVans.get(index).getRentalEndDate());
                    System.out.println("06. Number of Rental Days : " + rentalVans.get(index).getNumberOfRentalDays());
                    System.out.println("07. Rental Estimated Cost : " + rentalVans.get(index).getRentalEstimatedCost());

                    // Header
                    System.out.println("\n=============");
                    System.out.println("Return a Van");
                    System.out.println("=============\n");

                    // Discount Rate
                    discount = (int) Math.round((rentalVans.get(index).getRentalEstimatedCost() * Van.DISCOUNT_RATE) / 100.0);
                    System.out.println("01. Van Rental Discount Rate : " + discount);

                    // Actual Cost
                    rentalActualCost = (rentalVans.get(index).getRentalEstimatedCost() - discount);
                    System.out.println("02. Van Rental Actual Rate   : " + rentalActualCost);

                    // Status Update
                    rentalVans.get(index).setDiscount(discount);
                    rentalVans.get(index).setRentalActualCost(rentalActualCost);
                    rentalVans.get(index).setRentalStatus("Completed");

                    int vanIndex = findVehicleIndex(vans, rentalVans.get(index).getVehicleId());
                    if (vanIndex != -1) {
                        vans.get(vanIndex).setAvailabilityStatus(true);
                    }

                    System.out.println("\nVan Rental Return Processed Successfully!\n");
                    break;

                } else {
                    System.out.println("Rental ID is not found!\n");
                }

            } else {
                System.out.println("Invalid Rental ID (Ex: - R001)\n");
            }

        }

        askWantToExit();

    }

    // Method to return a Motorcycle
    public static void returnMotorcycle() {
        clearConsole();
        heading();

        String rentalId;
        int discount;
        int rentalActualCost;

        // Rental ID
        while (true) {
            System.out.print("Enter Rental ID          : ");
            rentalId = scanner.nextLine().trim();

            if (rentalId.matches("^R\\d{3}$")) {

                int index = findRentalIndex(rentalMotorcycles, rentalId);

                if (index != -1 && rentalMotorcycles.get(index).getRentalStatus().equals("Completed")) {
                    System.out.println("Rental is already completed!\n");

                } else if (index != -1) {
                    clearConsole();
                    heading();

                    System.out.println("Rental ID is found!\n");
                    System.out.println("01. Customer ID           : " + rentalMotorcycles.get(index).getCustomerId());
                    System.out.println("02. Motorcycle Rental ID  : " + rentalMotorcycles.get(index).getRentalId());
                    System.out.println("03. Vehicle ID            : " + rentalMotorcycles.get(index).getVehicleId());
                    System.out.println("04. Rental Start Date     : " + rentalMotorcycles.get(index).getRentalStartDate());
                    System.out.println("05. Rental End Date       : " + rentalMotorcycles.get(index).getRentalEndDate());
                    System.out.println("06. Number of Rental Days : " + rentalMotorcycles.get(index).getNumberOfRentalDays());
                    System.out.println("07. Rental Estimated Cost : " + rentalMotorcycles.get(index).getRentalEstimatedCost());

                    // Header
                    System.out.println("\n===================");
                    System.out.println("Return a Motorcycle");
                    System.out.println("===================\n");

                    // Discount Rate
                    discount = (int) Math.round((rentalMotorcycles.get(index).getRentalEstimatedCost() * Motorcycle.DISCOUNT_RATE) / 100.0);
                    System.out.println("01. Motorcycle Rental Discount Rate : " + discount);

                    // Actual Cost
                    rentalActualCost = (rentalMotorcycles.get(index).getRentalEstimatedCost() - discount);
                    System.out.println("02. Motorcycle Rental Actual Rate   : " + rentalActualCost);

                    // Status Update
                    rentalMotorcycles.get(index).setDiscount(discount);
                    rentalMotorcycles.get(index).setRentalActualCost(rentalActualCost);
                    rentalMotorcycles.get(index).setRentalStatus("Completed");

                    int motorcycleIndex = findVehicleIndex(motorcycles, rentalMotorcycles.get(index).getVehicleId());
                    if (motorcycleIndex != -1) {
                        motorcycles.get(motorcycleIndex).setAvailabilityStatus(true);
                    }

                    System.out.println("\nMotorcycle Rental Return Processed Successfully!\n");
                    break;

                } else {
                    System.out.println("Rental ID is not found!\n");
                }

            } else {
                System.out.println("Invalid Rental ID (Ex: - R001)\n");
            }

        }

        askWantToExit();

    }

    // Method to View All Rentals
    public static void viewRentals() {

        clearConsole();
        heading();

        String[] columnNames = {"Rental ID", "Customer ID", "Vehicle ID", "Start Date", "End Date", "No. of Days", "Estimated Rental Cost (Rs.)", "Discount (Rs.)", "Actual Cost (Rs. )", "Status"};

        System.out.println("========");
        System.out.println("All Cars");
        System.out.println("========\n");

        printTable(columnNames, buildRentalRows(rentalCars));

        System.out.println("\n========");
        System.out.println("All Vans");
        System.out.println("========\n");

        printTable(columnNames, buildRentalRows(rentalVans));

        System.out.println("\n===============");
        System.out.println("All Motorcycles");
        System.out.println("===============\n");

        printTable(columnNames, buildRentalRows(rentalMotorcycles));

        askWantToExit();

    }

    // Main Method
    public static void main(String[] args) {

        // Load Some Demo Data For Cars
        cars.add(new Car("V001", "Toyota Prius 2015", 2000, true, 4, "Petrol"));
        cars.add(new Car("V002", "Honda Vezel RS 2019", 2500, true, 4, "Petrol"));
        cars.add(new Car("V003", "Toyota Axio 2018", 2200, true, 5, "Petrol"));
        cars.add(new Car("V004", "Suzuki Wagon R 2017", 1800, true, 5, "Petrol"));
        cars.add(new Car("V005", "Nissan X-Trail 2016", 3000, true, 5, "Petrol"));
        cars.add(new Car("V006", "Toyota Aqua 2019", 2300, true, 5, "Hybrid"));
        cars.add(new Car("V007", "Honda Grace 2018", 2400, true, 5, "Hybrid"));
        cars.add(new Car("V008", "Mazda Demio 2017", 1900, true, 5, "Petrol"));
        cars.add(new Car("V009", "Suzuki Alto 2019", 1600, true, 4, "Petrol"));
        cars.add(new Car("V010", "Toyota Corolla 2020", 3200, true, 5, "Petrol"));

        // Load Some Demo Data For Vans
        vans.add(new Van("V001", "Toyota Hiace Super GL 2017", 5000, true, 1000));
        vans.add(new Van("V002", "Toyota Hiace Super GL 2010", 4000, true, 1000));
        vans.add(new Van("V003", "Nissan Caravan 2018", 4800, true, 200));
        vans.add(new Van("V004", "KDH 200 High Roof 2016", 4500, true, 100));
        vans.add(new Van("V005", "Toyota Dolphin 2008", 3500, true, 500));
        vans.add(new Van("V006", "Nissan NV350 2020", 5500, true, 300));
        vans.add(new Van("V007", "Mitsubishi Delica 2015", 4300, true, 1000));
        vans.add(new Van("V008", "Toyota Hiace DX 2019", 5200, true, 250));
        vans.add(new Van("V009", "Mazda Bongo 2014", 3700, true, 900));
        vans.add(new Van("V010", "Toyota Quantum 2021", 6000, true, 500));

        // Load Some Demo Data For Motorcycles
        motorcycles.add(new Motorcycle("V001", "Honda CBR 150R", 1000, true, 150));
        motorcycles.add(new Motorcycle("V002", "Honda Hornet 160R", 1200, true, 160));
        motorcycles.add(new Motorcycle("V003", "Yamaha FZ-S V3", 1100, true, 150));
        motorcycles.add(new Motorcycle("V004", "Bajaj Pulsar NS200", 1300, true, 200));
        motorcycles.add(new Motorcycle("V005", "TVS Apache RTR 160", 1000, true, 160));
        motorcycles.add(new Motorcycle("V006", "Suzuki Gixxer SF", 1250, true, 155));
        motorcycles.add(new Motorcycle("V007", "KTM Duke 200", 1800, false, 200));
        motorcycles.add(new Motorcycle("V008", "Yamaha MT-15", 1700, true, 155));
        motorcycles.add(new Motorcycle("V009", "Honda Dio", 800, true, 110));
        motorcycles.add(new Motorcycle("V010", "Hero Hunk", 900, true, 150));

        // Load Some Demo Data For Customers
        customers.add(new Customer("C001", "Shashini Weerasinghe", "0777425425", "AB123456"));
        customers.add(new Customer("C002", "Nimal Perera", "0712345678", "BC234567"));
        customers.add(new Customer("C003", "Kavindu Fernando", "0763456789", "CD345678"));
        customers.add(new Customer("C004", "Dinithi Silva", "0754567890", "DE456789"));
        customers.add(new Customer("C005", "Kasun Jayawardena", "0785678901", "EF567890"));
        customers.add(new Customer("C006", "Tharushi Gunawardena", "0706789012", "FG678901"));
        customers.add(new Customer("C007", "Chamod Wijesinghe", "0727890123", "GH789012"));
        customers.add(new Customer("C008", "Piumi Ranasinghe", "0748901234", "HJ890123"));
        customers.add(new Customer("C009", "Isuru Senanayake", "0779012345", "JK901234"));
        customers.add(new Customer("C010", "Malsha Karunaratne", "0750123456", "KL012345"));

        // Load Some Demo Rental Data For Cars
        rentalCars.add(new Rental("R001", "C001", "V001", "2016.02.21", "2016.04.06", 45, 90000, 4500, 85500, "Completed"));
        rentalCars.add(new Rental("R002", "C002", "V002", "2016.03.10", "2016.03.30", 20, 50000, 2500, 47500, "Completed"));
        rentalCars.add(new Rental("R003", "C003", "V003", "2016.04.05", "2016.04.20", 15, 33000, 1650, 31350, "Completed"));
        rentalCars.add(new Rental("R004", "C004", "V004", "2016.05.12", "2016.06.11", 30, 54000, 2700, 51300, "Completed"));
        rentalCars.add(new Rental("R005", "C005", "V005", "2016.06.18", "2016.06.30", 12, 36000, 1800, 34200, "Completed"));
        rentalCars.add(new Rental("R006", "C006", "V006", "2016.07.25", "2016.08.19", 25, 57500, 2875, 54625, "Completed"));
        rentalCars.add(new Rental("R007", "C007", "V007", "2016.08.14", "2016.09.01", 18, 43200, 2160, 41040, "Completed"));
        rentalCars.add(new Rental("R008", "C008", "V008", "2016.09.02", "2016.09.24", 22, 41800, 2090, 39710, "Completed"));
        rentalCars.add(new Rental("R009", "C009", "V009", "2016.10.19", "2016.10.29", 10, 16000, 800, 15200, "Completed"));
        rentalCars.add(new Rental("R010", "C010", "V010", "2016.11.28", "2016.12.12", 14, 44800, 2240, 42560, "Completed"));

        // Load Some Demo Rental Data For Motorcycles
        rentalMotorcycles.add(new Rental("R001", "C001", "V001", "2017.01.15", "2017.01.25", 10, 10000, 1000, 9000, "Completed"));
        rentalMotorcycles.add(new Rental("R002", "C002", "V002", "2017.02.20", "2017.03.04", 12, 14400, 1440, 12960, "Completed"));
        rentalMotorcycles.add(new Rental("R003", "C003", "V003", "2017.03.05", "2017.03.20", 15, 16500, 1650, 14850, "Completed"));
        rentalMotorcycles.add(new Rental("R004", "C004", "V004", "2017.04.18", "2017.04.26", 8, 10400, 1040, 9360, "Completed"));
        rentalMotorcycles.add(new Rental("R005", "C005", "V005", "2017.05.22", "2017.06.11", 20, 20000, 2000, 18000, "Completed"));
        rentalMotorcycles.add(new Rental("R006", "C006", "V006", "2017.06.10", "2017.06.24", 14, 17500, 1750, 15750, "Completed"));
        rentalMotorcycles.add(new Rental("R007", "C007", "V007", "2017.07.25", "2017.08.01", 7, 12600, 1260, 11340, "Completed"));
        rentalMotorcycles.add(new Rental("R008", "C008", "V008", "2017.08.12", "2017.08.28", 16, 27200, 2720, 24480, "Completed"));
        rentalMotorcycles.add(new Rental("R009", "C009", "V009", "2017.09.08", "2017.09.17", 9, 7200, 720, 6480, "Completed"));
        rentalMotorcycles.add(new Rental("R010", "C010", "V010", "2017.10.30", "2017.11.10", 11, 9900, 990, 8910, "Completed"));

        // Load Some Demo Rental Data For Vans
        rentalVans.add(new Rental("R001", "C001", "V001", "2018.01.05", "2018.01.15", 10, 50000, 4000, 46000, "Completed"));
        rentalVans.add(new Rental("R002", "C002", "V002", "2018.02.14", "2018.03.01", 15, 60000, 4800, 55200, "Completed"));
        rentalVans.add(new Rental("R003", "C003", "V003", "2018.03.20", "2018.04.01", 12, 57600, 4608, 52992, "Completed"));
        rentalVans.add(new Rental("R004", "C004", "V004", "2018.04.25", "2018.05.13", 18, 81000, 6480, 74520, "Completed"));
        rentalVans.add(new Rental("R005", "C005", "V005", "2018.05.10", "2018.05.30", 20, 70000, 5600, 64400, "Completed"));
        rentalVans.add(new Rental("R006", "C006", "V006", "2018.06.15", "2018.06.29", 14, 77000, 6160, 70840, "Completed"));
        rentalVans.add(new Rental("R007", "C007", "V007", "2018.07.22", "2018.08.07", 16, 68800, 5504, 63296, "Completed"));
        rentalVans.add(new Rental("R008", "C008", "V008", "2018.08.30", "2018.09.10", 11, 57200, 4576, 52624, "Completed"));
        rentalVans.add(new Rental("R009", "C009", "V009", "2018.09.18", "2018.10.01", 13, 48100, 3848, 44252, "Completed"));
        rentalVans.add(new Rental("R010", "C010", "V010", "2018.10.27", "2018.11.05", 9, 54000, 4320, 49680, "Completed"));

        // Clear Console
        clearConsole();

        // Load Main Menu
        mainMenu();

    }

}