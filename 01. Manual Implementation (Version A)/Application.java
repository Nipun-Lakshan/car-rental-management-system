// Application : Car Rental Management System
// Method      : Manual Implementation
// Author      : A. W. W. A. Nipun Lakshan

// Import Libraries

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Enter your choice: ");

        try {

            int choice = scanner1.nextInt();

            if (choice > 12 || choice < 1) {

                System.out.println("Invalid choice!\n");
                scanner1.nextLine();

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
            scanner1.nextLine();

            askForChoice();

        }

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

            Scanner scanner2 = new Scanner(System.in);

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner2.nextInt();

                if (choice > 5 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner2.nextLine();

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
                scanner2.nextLine();

            }

        }

    }

    // Method to Register a Car
    public static void registerCar() {

        clearConsole();
        heading();

        Scanner scanner1 = new Scanner(System.in);

        String vehicleId;
        String vehicleName;
        int dailyRentalRate;
        boolean availabilityStatus;
        int numberOfSeats;
        String fuelType;

        while (true) {
            System.out.print("Enter Vehicle ID          : ");
            vehicleId = scanner1.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {

                boolean exists = false;

                for (Car car : cars) {

                    if (car.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        break;
                    }

                }

                if (exists) {

                    System.out.println("Vehicle ID is already in use!\n");

                } else {

                    break;

                }

            } else {

                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");

            }

        }

        System.out.print("\nEnter Vehicle Name        : ");
        vehicleName = ((scanner1.nextLine()).trim());

        while (true) {

            try {

                System.out.print("\nEnter Daily Rental Rate   : Rs. ");
                dailyRentalRate = scanner1.nextInt();

                if (dailyRentalRate < 0) {
                    System.out.println("Invalid Daily Rental Rate!");

                } else {

                    scanner1.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Daily Rental Rate!");
                scanner1.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter availability status : ");
                availabilityStatus = scanner1.nextBoolean();
                break;

            } catch (InputMismatchException e) {

                System.out.println("Invalid availability status!\n");
                scanner1.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter Number of Seats     : ");
                numberOfSeats = scanner1.nextInt();

                if (numberOfSeats < 3 || numberOfSeats > 5) {
                    System.out.println("Invalid Number of Seats!");

                } else {

                    scanner1.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input!");
                scanner1.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter Fuel Type           : ");
                fuelType = scanner1.nextLine().trim();

                if (fuelType.equalsIgnoreCase("Diesel") || fuelType.equalsIgnoreCase("Petrol") || fuelType.equalsIgnoreCase("Hybrid")) {

                    break;

                } else {

                    System.out.println("Invalid Fuel Type!");

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input!");
                scanner1.nextLine();

            }

        }

        cars.add(new Car(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, numberOfSeats, fuelType));

        askWantToExit();

    }

    // Method to Register a Van
    public static void registerVan() {

        clearConsole();
        heading();

        Scanner scanner1 = new Scanner(System.in);

        String vehicleId;
        String vehicleName;
        int dailyRentalRate;
        boolean availabilityStatus;
        int cargoCapacity;

        while (true) {

            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner1.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {

                boolean exists = false;

                for (Van van : vans) {

                    if (van.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        break;
                    }

                }

                if (exists) {

                    System.out.println("Vehicle ID is already in use!\n");

                } else {

                    break;

                }

            } else {

                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");

            }

        }

        System.out.print("\nEnter Vehicle Name           : ");
        vehicleName = ((scanner1.nextLine()).trim());

        while (true) {

            try {

                System.out.print("\nEnter Daily Rental Rate      : Rs. ");
                dailyRentalRate = scanner1.nextInt();

                if (dailyRentalRate < 0) {

                    System.out.println("Invalid Daily Rental Rate!");

                } else {

                    scanner1.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Daily Rental Rate!");
                scanner1.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter availability status    : ");
                availabilityStatus = scanner1.nextBoolean();
                break;

            } catch (InputMismatchException e) {

                System.out.println("Invalid availability status!\n");
                scanner1.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter Cargo Capacity (In Kg) : ");
                cargoCapacity = scanner1.nextInt();

                if (cargoCapacity < 0 || cargoCapacity > 1000) {

                    System.out.println("Invalid Cargo Capacity!");

                } else {

                    scanner1.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input!");
                scanner1.nextLine();

            }

        }

        vans.add(new Van(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, cargoCapacity));

        askWantToExit();

    }

    // Method to Register a Motorcycle
    public static void registerMotorCycle() {

        clearConsole();
        heading();

        Scanner scanner1 = new Scanner(System.in);

        String vehicleId;
        String vehicleName;
        int dailyRentalRate;
        boolean availabilityStatus;
        int engineCapacity;

        while (true) {

            System.out.print("Enter Vehicle ID              : ");
            vehicleId = scanner1.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {

                boolean exists = false;

                for (Motorcycle motorcycle : motorcycles) {

                    if (motorcycle.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        break;
                    }

                }

                if (exists) {

                    System.out.println("Vehicle ID is already in use!\n");

                } else {

                    break;

                }
            } else {

                System.out.println("Invalid Vehicle ID (Ex: - V001)\n");

            }

        }

        System.out.print("\nEnter Vehicle Name           : ");
        vehicleName = ((scanner1.nextLine()).trim());

        while (true) {

            try {

                System.out.print("\nEnter Daily Rental Rate       : Rs. ");
                dailyRentalRate = scanner1.nextInt();

                if (dailyRentalRate < 0) {

                    System.out.println("Invalid Daily Rental Rate!");

                } else {

                    scanner1.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Daily Rental Rate!");
                scanner1.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter availability status     : ");
                availabilityStatus = scanner1.nextBoolean();
                break;

            } catch (InputMismatchException e) {

                System.out.println("Invalid availability status!\n");
                scanner1.nextLine();

            }

        }

        while (true) {

            try {

                System.out.print("\nEnter Engine Capacity (In CC) : ");
                engineCapacity = scanner1.nextInt();

                if (engineCapacity < 100 || engineCapacity > 2000) {

                    System.out.println("Invalid Engine Capacity!");

                } else {

                    scanner1.nextLine();
                    break;

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input!");
                scanner1.nextLine();

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

            Scanner scanner2 = new Scanner(System.in);

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner2.nextInt();

                if (choice > 6 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner2.nextLine();

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
                scanner2.nextLine();

            }

        }

    }

    // Method to View All Cars
    public static void viewAllCars() {

        clearConsole();
        heading();

        String[] columnNames = {"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Number of Seats", "Fuel Type"};

        int[] columnNamesLength = new int[6];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        int maxVehicleId = Integer.MIN_VALUE;
        int maxVehicleName = Integer.MIN_VALUE;
        int maxDailyRentalRate = Integer.MIN_VALUE;
        int maxAvailabilityStatus = Integer.MIN_VALUE;
        int maxNumberOfSeats = Integer.MIN_VALUE;
        int maxFuelType = Integer.MIN_VALUE;

        for (Car car : cars) {

            if (car.getVehicleId() != null) {
                maxVehicleId = Math.max(maxVehicleId, car.getVehicleId().length());
            }

            if (car.getVehicleName() != null) {
                maxVehicleName = Math.max(maxVehicleName, car.getVehicleName().length());
            }

            if (car.getDailyRentalRate() != 0) {
                maxDailyRentalRate = Math.max(maxDailyRentalRate, String.valueOf(car.getDailyRentalRate()).length());
            }

            maxAvailabilityStatus = Math.max(maxAvailabilityStatus, String.valueOf(car.getAvailabilityStatus()).length());

            if (car.getNumberOfSeats() != 0) {
                maxNumberOfSeats = Math.max(maxNumberOfSeats, String.valueOf(car.getNumberOfSeats()).length());
            }

            if (!(car.getFuelType().equals("Unknown"))) {
                maxFuelType = Math.max(maxFuelType, String.valueOf(car.getFuelType()).length());
            }

        }

        int vehicleIdWidth = Math.max(columnNames[0].length(), maxVehicleId);

        int vehicleNameWidth = Math.max(columnNames[1].length(), maxVehicleName);

        int dailyRentalRateWidth = Math.max(columnNames[2].length(), maxDailyRentalRate);

        int availabilityStatusWidth = Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int numberOfSeatsWidth = Math.max(columnNames[4].length(), maxNumberOfSeats);

        int fuelTypeWidth = Math.max(columnNames[5].length(), maxFuelType);

        int[] maxColumnWidth = {vehicleIdWidth, vehicleNameWidth, dailyRentalRateWidth, availabilityStatusWidth, numberOfSeatsWidth, fuelTypeWidth};

        int totalColumnWidth = vehicleIdWidth + vehicleNameWidth + dailyRentalRateWidth + availabilityStatusWidth + numberOfSeatsWidth + fuelTypeWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {

            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Car car : cars) {

            // Vehicle ID
            if (car.getVehicleId().length() >= vehicleIdWidth) {
                System.out.print("\n|" + car.getVehicleId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (vehicleIdWidth - car.getVehicleId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getVehicleId() + "|");
            }

            // Vehicle Name
            if (car.getVehicleName().length() >= vehicleNameWidth) {
                System.out.print(car.getVehicleName() + "|");
            } else {
                for (int k = 0; k < (vehicleNameWidth - car.getVehicleName().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getVehicleName() + "|");
            }

            // Daily Rental Rate
            if ((String.valueOf(car.getDailyRentalRate())).length() >= dailyRentalRateWidth) {
                System.out.print(car.getDailyRentalRate() + "|");
            } else {
                for (int k = 0; k < (dailyRentalRateWidth - (String.valueOf(car.getDailyRentalRate())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getDailyRentalRate() + "|");
            }

            // Availability Status
            if ((String.valueOf(car.getAvailabilityStatus())).length() >= availabilityStatusWidth) {
                System.out.print(car.getAvailabilityStatus() + "|");
            } else {
                for (int k = 0; k < (availabilityStatusWidth - (String.valueOf(car.getAvailabilityStatus())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getAvailabilityStatus() + "|");
            }

            // Number of Seats
            if ((String.valueOf(car.getNumberOfSeats())).length() >= numberOfSeatsWidth) {
                System.out.print(car.getNumberOfSeats() + "|");
            } else {
                for (int k = 0; k < (numberOfSeatsWidth - (String.valueOf(car.getNumberOfSeats())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getNumberOfSeats() + "|");
            }

            // Fuel Type
            if (car.getFuelType().length() >= fuelTypeWidth) {
                System.out.print(car.getFuelType() + "|");
            } else {
                for (int k = 0; k < (fuelTypeWidth - car.getFuelType().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getFuelType() + "|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

        askWantToExit();

    }

    // Method to View All Vans
    public static void viewAllVans() {

        clearConsole();
        heading();

        String[] columnNames = {"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Cargo Capacity (In Kg)"};

        int[] columnNamesLength = new int[5];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        int maxVehicleId = Integer.MIN_VALUE;
        int maxVehicleName = Integer.MIN_VALUE;
        int maxDailyRentalRate = Integer.MIN_VALUE;
        int maxAvailabilityStatus = Integer.MIN_VALUE;
        int maxCargoCapacity = Integer.MIN_VALUE;

        for (Van van : vans) {

            if (van.getVehicleId() != null) {
                maxVehicleId = Math.max(maxVehicleId, van.getVehicleId().length());
            }

            if (van.getVehicleName() != null) {
                maxVehicleName = Math.max(maxVehicleName, van.getVehicleName().length());
            }

            if (van.getDailyRentalRate() != 0) {
                maxDailyRentalRate = Math.max(maxDailyRentalRate, String.valueOf(van.getDailyRentalRate()).length());
            }

            maxAvailabilityStatus = Math.max(maxAvailabilityStatus, String.valueOf(van.getAvailabilityStatus()).length());

            if (van.getCargoCapacity() != 0) {
                maxCargoCapacity = Math.max(maxCargoCapacity, String.valueOf(van.getCargoCapacity()).length());
            }

        }

        int vehicleIdWidth = Math.max(columnNames[0].length(), maxVehicleId);

        int vehicleNameWidth = Math.max(columnNames[1].length(), maxVehicleName);

        int dailyRentalRateWidth = Math.max(columnNames[2].length(), maxDailyRentalRate);

        int availabilityStatusWidth = Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int cargoCapacityWidth = Math.max(columnNames[4].length(), maxCargoCapacity);

        int[] maxColumnWidth = {vehicleIdWidth, vehicleNameWidth, dailyRentalRateWidth, availabilityStatusWidth, cargoCapacityWidth};

        int totalColumnWidth = vehicleIdWidth + vehicleNameWidth + dailyRentalRateWidth + availabilityStatusWidth + cargoCapacityWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {
            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Van van : vans) {

            // Vehicle ID
            if (van.getVehicleId().length() >= vehicleIdWidth) {
                System.out.print("\n|" + van.getVehicleId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (vehicleIdWidth - van.getVehicleId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getVehicleId() + "|");
            }

            // Vehicle Name
            if (van.getVehicleName().length() >= vehicleNameWidth) {
                System.out.print(van.getVehicleName() + "|");
            } else {
                for (int k = 0; k < (vehicleNameWidth - van.getVehicleName().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getVehicleName() + "|");
            }

            // Daily Rental Rate
            if ((String.valueOf(van.getDailyRentalRate())).length() >= dailyRentalRateWidth) {
                System.out.print(van.getDailyRentalRate() + "|");
            } else {
                for (int k = 0; k < (dailyRentalRateWidth - (String.valueOf(van.getDailyRentalRate())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getDailyRentalRate() + "|");
            }

            // Availability Status
            if ((String.valueOf(van.getAvailabilityStatus())).length() >= availabilityStatusWidth) {
                System.out.print(van.getAvailabilityStatus() + "|");
            } else {
                for (int k = 0; k < (availabilityStatusWidth - (String.valueOf(van.getAvailabilityStatus())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getAvailabilityStatus() + "|");
            }

            // Cargo Capacity
            if ((String.valueOf(van.getCargoCapacity())).length() >= cargoCapacityWidth) {
                System.out.print(van.getCargoCapacity() + "|");
            } else {
                for (int k = 0; k < (cargoCapacityWidth - (String.valueOf(van.getCargoCapacity())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getCargoCapacity() + "|");
            }

        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

        askWantToExit();

    }

    // Method to View All Motorcycles
    public static void viewAllMotorcycles() {

        clearConsole();
        heading();

        String[] columnNames = {"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Engine Capacity (In CC)"};

        int[] columnNamesLength = new int[5];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        int maxVehicleId = Integer.MIN_VALUE;
        int maxVehicleName = Integer.MIN_VALUE;
        int maxDailyRentalRate = Integer.MIN_VALUE;
        int maxAvailabilityStatus = Integer.MIN_VALUE;
        int maxEngineCapacity = Integer.MIN_VALUE;

        for (Motorcycle motorcycle : motorcycles) {

            if (motorcycle.getVehicleId() != null) {
                maxVehicleId = Math.max(maxVehicleId, motorcycle.getVehicleId().length());
            }

            if (motorcycle.getVehicleName() != null) {
                maxVehicleName = Math.max(maxVehicleName, motorcycle.getVehicleName().length());
            }

            if (motorcycle.getDailyRentalRate() != 0) {
                maxDailyRentalRate = Math.max(maxDailyRentalRate, String.valueOf(motorcycle.getDailyRentalRate()).length());
            }

            maxAvailabilityStatus = Math.max(maxAvailabilityStatus, String.valueOf(motorcycle.getAvailabilityStatus()).length());

            if (motorcycle.getEngineCapacity() != 0) {
                maxEngineCapacity = Math.max(maxEngineCapacity, String.valueOf(motorcycle.getEngineCapacity()).length());
            }

        }

        int vehicleIdWidth = Math.max(columnNames[0].length(), maxVehicleId);

        int vehicleNameWidth = Math.max(columnNames[1].length(), maxVehicleName);

        int dailyRentalRateWidth = Math.max(columnNames[2].length(), maxDailyRentalRate);

        int availabilityStatusWidth = Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int engineCapacityWidth = Math.max(columnNames[4].length(), maxEngineCapacity);

        int[] maxColumnWidth = {vehicleIdWidth, vehicleNameWidth, dailyRentalRateWidth, availabilityStatusWidth, engineCapacityWidth};

        int totalColumnWidth = vehicleIdWidth + vehicleNameWidth + dailyRentalRateWidth + availabilityStatusWidth + engineCapacityWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {
            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Motorcycle motorcycle : motorcycles) {

            // Vehicle ID
            if (motorcycle.getVehicleId().length() >= vehicleIdWidth) {
                System.out.print("\n|" + motorcycle.getVehicleId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (vehicleIdWidth - motorcycle.getVehicleId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getVehicleId() + "|");
            }

            // Vehicle Name
            if (motorcycle.getVehicleName().length() >= vehicleNameWidth) {
                System.out.print(motorcycle.getVehicleName() + "|");
            } else {
                for (int k = 0; k < (vehicleNameWidth - motorcycle.getVehicleName().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getVehicleName() + "|");
            }

            // Daily Rental Rate
            if ((String.valueOf(motorcycle.getDailyRentalRate())).length() >= dailyRentalRateWidth) {
                System.out.print(motorcycle.getDailyRentalRate() + "|");
            } else {
                for (int k = 0; k < (dailyRentalRateWidth - (String.valueOf(motorcycle.getDailyRentalRate())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getDailyRentalRate() + "|");
            }

            // Availability Status
            if ((String.valueOf(motorcycle.getAvailabilityStatus())).length() >= availabilityStatusWidth) {
                System.out.print(motorcycle.getAvailabilityStatus() + "|");
            } else {
                for (int k = 0; k < (availabilityStatusWidth - (String.valueOf(motorcycle.getAvailabilityStatus())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getAvailabilityStatus() + "|");
            }

            // Engine Capacity
            if ((String.valueOf(motorcycle.getEngineCapacity())).length() >= engineCapacityWidth) {
                System.out.print(motorcycle.getEngineCapacity() + "|");
            } else {
                for (int k = 0; k < (engineCapacityWidth - (String.valueOf(motorcycle.getEngineCapacity())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getEngineCapacity() + "|");
            }

        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

        askWantToExit();

    }

    // Method to ask whether you want to exit or not
    public static void askWantToExit() {

        Scanner scanner2 = new Scanner(System.in);

        System.out.print("\nDo you want to exit (Y/N): ");
        String input = scanner2.nextLine();

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

        // 01. View All Cars

        clearConsole();
        heading();

        System.out.println("========");
        System.out.println("All Cars");
        System.out.println("========\n");

        String[] columnNames = {"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Number of Seats", "Fuel Type"};

        int[] columnNamesLength = new int[6];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        int maxVehicleId = Integer.MIN_VALUE;
        int maxVehicleName = Integer.MIN_VALUE;
        int maxDailyRentalRate = Integer.MIN_VALUE;
        int maxAvailabilityStatus = Integer.MIN_VALUE;
        int maxNumberOfSeats = Integer.MIN_VALUE;
        int maxFuelType = Integer.MIN_VALUE;

        for (Car car : cars) {

            if (car.getVehicleId() != null) {
                maxVehicleId = Math.max(maxVehicleId, car.getVehicleId().length());
            }

            if (car.getVehicleName() != null) {
                maxVehicleName = Math.max(maxVehicleName, car.getVehicleName().length());
            }

            if (car.getDailyRentalRate() != 0) {
                maxDailyRentalRate = Math.max(maxDailyRentalRate, String.valueOf(car.getDailyRentalRate()).length());
            }

            maxAvailabilityStatus = Math.max(maxAvailabilityStatus, String.valueOf(car.getAvailabilityStatus()).length());

            if (car.getNumberOfSeats() != 0) {
                maxNumberOfSeats = Math.max(maxNumberOfSeats, String.valueOf(car.getNumberOfSeats()).length());
            }

            if (!(car.getFuelType().equals("Unknown"))) {
                maxFuelType = Math.max(maxFuelType, String.valueOf(car.getFuelType()).length());
            }
        }

        int vehicleIdWidth = Math.max(columnNames[0].length(), maxVehicleId);

        int vehicleNameWidth = Math.max(columnNames[1].length(), maxVehicleName);

        int dailyRentalRateWidth = Math.max(columnNames[2].length(), maxDailyRentalRate);

        int availabilityStatusWidth = Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int numberOfSeatsWidth = Math.max(columnNames[4].length(), maxNumberOfSeats);

        int fuelTypeWidth = Math.max(columnNames[5].length(), maxFuelType);

        int[] maxColumnWidth = {vehicleIdWidth, vehicleNameWidth, dailyRentalRateWidth, availabilityStatusWidth, numberOfSeatsWidth, fuelTypeWidth};

        int totalColumnWidth = vehicleIdWidth + vehicleNameWidth + dailyRentalRateWidth + availabilityStatusWidth + numberOfSeatsWidth + fuelTypeWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {
            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Car car : cars) {

            // Vehicle ID
            if (car.getVehicleId().length() >= vehicleIdWidth) {
                System.out.print("\n|" + car.getVehicleId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (vehicleIdWidth - car.getVehicleId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getVehicleId() + "|");
            }

            // Vehicle Name
            if (car.getVehicleName().length() >= vehicleNameWidth) {
                System.out.print(car.getVehicleName() + "|");
            } else {
                for (int k = 0; k < (vehicleNameWidth - car.getVehicleName().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getVehicleName() + "|");
            }

            // Daily Rental Rate
            if ((String.valueOf(car.getDailyRentalRate())).length() >= dailyRentalRateWidth) {
                System.out.print(car.getDailyRentalRate() + "|");
            } else {
                for (int k = 0; k < (dailyRentalRateWidth - (String.valueOf(car.getDailyRentalRate())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getDailyRentalRate() + "|");
            }

            // Availability Status
            if ((String.valueOf(car.getAvailabilityStatus())).length() >= availabilityStatusWidth) {
                System.out.print(car.getAvailabilityStatus() + "|");
            } else {
                for (int k = 0; k < (availabilityStatusWidth - (String.valueOf(car.getAvailabilityStatus())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getAvailabilityStatus() + "|");
            }

            // Number of Seats
            if ((String.valueOf(car.getNumberOfSeats())).length() >= numberOfSeatsWidth) {
                System.out.print(car.getNumberOfSeats() + "|");
            } else {
                for (int k = 0; k < (numberOfSeatsWidth - (String.valueOf(car.getNumberOfSeats())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getNumberOfSeats() + "|");
            }

            // Fuel Type
            if (car.getFuelType().length() >= fuelTypeWidth) {
                System.out.print(car.getFuelType() + "|");
            } else {
                for (int k = 0; k < (fuelTypeWidth - car.getFuelType().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(car.getFuelType() + "|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

        // 02. View All Vans

        System.out.println("\n========");
        System.out.println("All Vans");
        System.out.println("========\n");

        columnNames = new String[]{"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Cargo Capacity (In Kg)"};

        columnNamesLength = new int[5];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        maxVehicleId = Integer.MIN_VALUE;
        maxVehicleName = Integer.MIN_VALUE;
        maxDailyRentalRate = Integer.MIN_VALUE;
        maxAvailabilityStatus = Integer.MIN_VALUE;
        int maxCargoCapacity = Integer.MIN_VALUE;

        for (Van van : vans) {

            if (van.getVehicleId() != null) {
                maxVehicleId = Math.max(maxVehicleId, van.getVehicleId().length());
            }

            if (van.getVehicleName() != null) {
                maxVehicleName = Math.max(maxVehicleName, van.getVehicleName().length());
            }

            if (van.getDailyRentalRate() != 0) {
                maxDailyRentalRate = Math.max(maxDailyRentalRate, String.valueOf(van.getDailyRentalRate()).length());
            }

            maxAvailabilityStatus = Math.max(maxAvailabilityStatus, String.valueOf(van.getAvailabilityStatus()).length());

            if (van.getCargoCapacity() != 0) {
                maxCargoCapacity = Math.max(maxCargoCapacity, String.valueOf(van.getCargoCapacity()).length());
            }

        }

        vehicleIdWidth = Math.max(columnNames[0].length(), maxVehicleId);

        vehicleNameWidth = Math.max(columnNames[1].length(), maxVehicleName);

        dailyRentalRateWidth = Math.max(columnNames[2].length(), maxDailyRentalRate);

        availabilityStatusWidth = Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int cargoCapacityWidth = Math.max(columnNames[4].length(), maxCargoCapacity);

        maxColumnWidth = new int[]{vehicleIdWidth, vehicleNameWidth, dailyRentalRateWidth, availabilityStatusWidth, cargoCapacityWidth};

        totalColumnWidth = vehicleIdWidth + vehicleNameWidth + dailyRentalRateWidth + availabilityStatusWidth + cargoCapacityWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {
            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Van van : vans) {

            // Vehicle ID
            if (van.getVehicleId().length() >= vehicleIdWidth) {
                System.out.print("\n|" + van.getVehicleId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (vehicleIdWidth - van.getVehicleId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getVehicleId() + "|");
            }

            // Vehicle Name
            if (van.getVehicleName().length() >= vehicleNameWidth) {
                System.out.print(van.getVehicleName() + "|");
            } else {
                for (int k = 0; k < (vehicleNameWidth - van.getVehicleName().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getVehicleName() + "|");
            }

            // Daily Rental Rate
            if ((String.valueOf(van.getDailyRentalRate())).length() >= dailyRentalRateWidth) {
                System.out.print(van.getDailyRentalRate() + "|");
            } else {
                for (int k = 0; k < (dailyRentalRateWidth - (String.valueOf(van.getDailyRentalRate())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getDailyRentalRate() + "|");
            }

            // Availability Status
            if ((String.valueOf(van.getAvailabilityStatus())).length() >= availabilityStatusWidth) {
                System.out.print(van.getAvailabilityStatus() + "|");
            } else {
                for (int k = 0; k < (availabilityStatusWidth - (String.valueOf(van.getAvailabilityStatus())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getAvailabilityStatus() + "|");
            }

            // Cargo Capacity
            if ((String.valueOf(van.getCargoCapacity())).length() >= cargoCapacityWidth) {
                System.out.print(van.getCargoCapacity() + "|");
            } else {
                for (int k = 0; k < (cargoCapacityWidth - (String.valueOf(van.getCargoCapacity())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(van.getCargoCapacity() + "|");
            }

        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

        // 03. View All Motorcycles

        System.out.println("\n===============");
        System.out.println("All Motorcycles");
        System.out.println("===============\n");

        columnNames = new String[]{"Vehicle ID", "Vehicle Name", "Daily Rental Rate (Rs.)", "Availability Status", "Engine Capacity (In CC)"};

        columnNamesLength = new int[5];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        maxVehicleId = Integer.MIN_VALUE;
        maxVehicleName = Integer.MIN_VALUE;
        maxDailyRentalRate = Integer.MIN_VALUE;
        maxAvailabilityStatus = Integer.MIN_VALUE;
        int maxEngineCapacity = Integer.MIN_VALUE;

        for (Motorcycle motorcycle : motorcycles) {

            if (motorcycle.getVehicleId() != null) {
                maxVehicleId = Math.max(maxVehicleId, motorcycle.getVehicleId().length());
            }

            if (motorcycle.getVehicleName() != null) {
                maxVehicleName = Math.max(maxVehicleName, motorcycle.getVehicleName().length());
            }

            if (motorcycle.getDailyRentalRate() != 0) {
                maxDailyRentalRate = Math.max(maxDailyRentalRate, String.valueOf(motorcycle.getDailyRentalRate()).length());
            }

            maxAvailabilityStatus = Math.max(maxAvailabilityStatus, String.valueOf(motorcycle.getAvailabilityStatus()).length());

            if (motorcycle.getEngineCapacity() != 0) {
                maxEngineCapacity = Math.max(maxEngineCapacity, String.valueOf(motorcycle.getEngineCapacity()).length());
            }

        }

        vehicleIdWidth = Math.max(columnNames[0].length(), maxVehicleId);

        vehicleNameWidth = Math.max(columnNames[1].length(), maxVehicleName);

        dailyRentalRateWidth = Math.max(columnNames[2].length(), maxDailyRentalRate);

        availabilityStatusWidth = Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int engineCapacityWidth = Math.max(columnNames[4].length(), maxEngineCapacity);

        maxColumnWidth = new int[]{vehicleIdWidth, vehicleNameWidth, dailyRentalRateWidth, availabilityStatusWidth, engineCapacityWidth};

        totalColumnWidth = vehicleIdWidth + vehicleNameWidth + dailyRentalRateWidth + availabilityStatusWidth + engineCapacityWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {
            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Motorcycle motorcycle : motorcycles) {

            // Vehicle ID
            if (motorcycle.getVehicleId().length() >= vehicleIdWidth) {
                System.out.print("\n|" + motorcycle.getVehicleId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (vehicleIdWidth - motorcycle.getVehicleId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getVehicleId() + "|");
            }

            // Vehicle Name
            if (motorcycle.getVehicleName().length() >= vehicleNameWidth) {
                System.out.print(motorcycle.getVehicleName() + "|");
            } else {
                for (int k = 0; k < (vehicleNameWidth - motorcycle.getVehicleName().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getVehicleName() + "|");
            }

            // Daily Rental Rate
            if ((String.valueOf(motorcycle.getDailyRentalRate())).length() >= dailyRentalRateWidth) {
                System.out.print(motorcycle.getDailyRentalRate() + "|");
            } else {
                for (int k = 0; k < (dailyRentalRateWidth - (String.valueOf(motorcycle.getDailyRentalRate())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getDailyRentalRate() + "|");
            }

            // Availability Status
            if ((String.valueOf(motorcycle.getAvailabilityStatus())).length() >= availabilityStatusWidth) {
                System.out.print(motorcycle.getAvailabilityStatus() + "|");
            } else {
                for (int k = 0; k < (availabilityStatusWidth - (String.valueOf(motorcycle.getAvailabilityStatus())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getAvailabilityStatus() + "|");
            }

            // Engine Capacity
            if ((String.valueOf(motorcycle.getEngineCapacity())).length() >= engineCapacityWidth) {
                System.out.print(motorcycle.getEngineCapacity() + "|");
            } else {
                for (int k = 0; k < (engineCapacityWidth - (String.valueOf(motorcycle.getEngineCapacity())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(motorcycle.getEngineCapacity() + "|");
            }

        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

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

            Scanner scanner2 = new Scanner(System.in);

            System.out.print("Enter your choice: ");

            try {
                int choice = scanner2.nextInt();
                if (choice > 5 || choice < 1) {
                    System.out.println("Invalid choice!\n");
                    scanner2.nextLine();
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
                scanner2.nextLine();
            }
        }
    }

    // Method to Search a Car by ID
    public static void searchCar() {
        clearConsole();
        heading();

        Scanner scanner1 = new Scanner(System.in);

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner1.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                boolean exists = false;
                int index = -1;

                for (Car car : cars) {
                    if (car.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        index = cars.indexOf(car);
                    }
                }
                if (exists) {
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

        Scanner scanner1 = new Scanner(System.in);

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner1.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                boolean exists = false;
                int index = -1;

                for (Van van : vans) {
                    if (van.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        index = vans.indexOf(van);
                    }
                }
                if (exists) {
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

        Scanner scanner1 = new Scanner(System.in);

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner1.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                boolean exists = false;
                int index = -1;

                for (Motorcycle motorcycle : motorcycles) {
                    if (motorcycle.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        index = motorcycles.indexOf(motorcycle);
                    }
                }
                if (exists) {
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

            Scanner scanner2 = new Scanner(System.in);

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner2.nextInt();

                if (choice > 5 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner2.nextLine();

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
                scanner2.nextLine();

            }

        }

    }

    // Method to Remove a Car
    public static void removeCar() {

        clearConsole();
        heading();

        Scanner scanner1 = new Scanner(System.in);

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner1.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                boolean exists = false;
                int index = -1;

                for (Car car : cars) {
                    if (car.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        index = cars.indexOf(car);
                    }
                }
                if (exists) {
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

        Scanner scanner1 = new Scanner(System.in);

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner1.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                boolean exists = false;
                int index = -1;

                for (Motorcycle motorcycle : motorcycles) {
                    if (motorcycle.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        index = motorcycles.indexOf(motorcycle);
                    }
                }
                if (exists) {
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

        Scanner scanner1 = new Scanner(System.in);

        String vehicleId;

        while (true) {
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner1.nextLine().trim();
            if (vehicleId.matches("^V\\d{3}$")) {

                boolean exists = false;
                int index = -1;

                for (Van van : vans) {
                    if (van.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        index = vans.indexOf(van);
                    }
                }
                if (exists) {
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

        Scanner scanner1 = new Scanner(System.in);

        String customerId;
        String customerName;
        String contactNo;
        String licenseNumber;

        while (true) {
            System.out.print("Enter Customer ID               : ");
            customerId = scanner1.nextLine().trim();

            if (customerId.matches("^C\\d{3}$")) {

                boolean exists = false;

                for (Customer customer : customers) {

                    if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                        exists = true;
                        break;
                    }

                }

                if (exists) {

                    System.out.println("Customer ID is already in use!\n");

                } else {

                    break;

                }

            } else {

                System.out.println("Invalid Customer ID (Ex: - C001)\n");

            }

        }

        System.out.print("\nEnter Customer Name             : ");
        customerName = ((scanner1.nextLine()).trim());

        while (true) {
            System.out.print("\nEnter Customer Contact Number   : ");
            contactNo = scanner1.nextLine().trim();

            if (contactNo.matches("^07\\d{8}$")) {
                break;

            } else {
                System.out.println("Invalid Contact Number (Ex: - 0742515014)");

            }

        }

        while (true) {
            System.out.print("\nEnter Customer License's Number : ");
            licenseNumber = scanner1.nextLine().trim();

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

        int[] columnNamesLength = new int[4];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        int maxCustomerId = Integer.MIN_VALUE;
        int maxCustomerName = Integer.MIN_VALUE;
        int maxContactNo = Integer.MIN_VALUE;
        int maxLicenseNumber = Integer.MIN_VALUE;

        for (Customer customer : customers) {

            if (customer.getCustomerId() != null) {
                maxCustomerId = Math.max(maxCustomerId, customer.getCustomerId().length());
            }

            if (customer.getCustomerName() != null) {
                maxCustomerName = Math.max(maxCustomerName, customer.getCustomerName().length());
            }

            if (customer.getContactNo() != null) {
                maxContactNo = Math.max(maxContactNo, customer.getContactNo().length());
            }

            if (customer.getLicenseNumber() != null) {
                maxLicenseNumber = Math.max(maxLicenseNumber, customer.getLicenseNumber().length());
            }

        }

        int customerIdWidth = Math.max(columnNames[0].length(), maxCustomerId);

        int customerNameWidth = Math.max(columnNames[1].length(), maxCustomerName);

        int contactNoWidth = Math.max(columnNames[2].length(), maxContactNo);

        int licenseNumberWidth = Math.max(columnNames[3].length(), maxLicenseNumber);

        int[] maxColumnWidth = {customerIdWidth, customerNameWidth, contactNoWidth, licenseNumberWidth};

        int totalColumnWidth = customerIdWidth + customerNameWidth + contactNoWidth + licenseNumberWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {
            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Customer customer : customers) {

            // Customer ID
            if (customer.getCustomerId().length() >= customerIdWidth) {
                System.out.print("\n|" + customer.getCustomerId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (customerIdWidth - customer.getCustomerId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(customer.getCustomerId() + "|");
            }

            // Customer Name
            if (customer.getCustomerName().length() >= customerNameWidth) {
                System.out.print(customer.getCustomerName() + "|");
            } else {
                for (int k = 0; k < (customerNameWidth - customer.getCustomerName().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(customer.getCustomerName() + "|");
            }

            // Contact No
            if (customer.getContactNo().length() >= contactNoWidth) {
                System.out.print(customer.getContactNo() + "|");
            } else {
                for (int k = 0; k < (contactNoWidth - customer.getContactNo().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(customer.getContactNo() + "|");
            }

            // License Number
            if (customer.getLicenseNumber().length() >= licenseNumberWidth) {
                System.out.print(customer.getLicenseNumber() + "|");
            } else {
                for (int k = 0; k < (licenseNumberWidth - customer.getLicenseNumber().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(customer.getLicenseNumber() + "|");
            }

        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

        askWantToExit();
    }

    // Method to Search Customers
    public static void searchCustomer() {
        clearConsole();
        heading();

        Scanner scanner1 = new Scanner(System.in);

        String customerId;

        while (true) {
            System.out.print("Enter Customer ID             : ");
            customerId = scanner1.nextLine().trim();
            if (customerId.matches("^C\\d{3}$")) {

                boolean exists = false;
                int index = -1;

                for (Customer customer : customers) {
                    if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                        exists = true;
                        index = customers.indexOf(customer);
                    }
                }
                if (exists) {
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

        Scanner scanner1 = new Scanner(System.in);

        String customerId;

        while (true) {
            System.out.print("Enter Customer ID             : ");
            customerId = scanner1.nextLine().trim();
            if (customerId.matches("^C\\d{3}$")) {

                boolean exists = false;
                int index = -1;

                for (Customer customer : customers) {
                    if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                        exists = true;
                        index = customers.indexOf(customer);
                    }
                }
                if (exists) {
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

            Scanner scanner2 = new Scanner(System.in);

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner2.nextInt();

                if (choice > 5 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner2.nextLine();

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
                scanner2.nextLine();

            }

        }
    }

    // Method to rent a car
    public static void rentCar() {
        clearConsole();
        heading();

        Scanner scanner1 = new Scanner(System.in);

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
            rentalId = scanner1.nextLine().trim();

            if (rentalId.matches("^R\\d{3}$")) {
                boolean exists = false;

                for (Rental rent : rentalCars) {
                    if (rent.getRentalId().equalsIgnoreCase(rentalId)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
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
            customerId = scanner1.nextLine().trim();

            if (customerId.matches("^C\\d{3}$")) {
                boolean exists = false;

                for (Customer customer : customers) {
                    if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                        exists = true;
                        break;
                    }

                }

                if (exists) {
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
            vehicleId = scanner1.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {
                boolean exists = false;
                int index = -1;

                for (Car car : cars) {
                    if (car.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        index = cars.indexOf(car);
                        break;
                    }

                }

                if (exists) {
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
            rentalStartDate = scanner1.nextLine().trim();

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
            rentalEndDate = scanner1.nextLine().trim();

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
        for (Car car : cars) {
            if (car.getVehicleId().equalsIgnoreCase(vehicleId)) {
                rentalEstimatedCost = numberOfRentalDays * car.getDailyRentalRate();
                System.out.println("\nRental Estimated Cost    : Rs. " + rentalEstimatedCost);
                break;
            }
        }

        rentalCars.add(new Rental(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, numberOfRentalDays, rentalEstimatedCost, -1, -1, "Pending"));
        System.out.println("\nCar rental has been successfully recorded!");

        askWantToExit();

    }

    // Method to rent a van
    public static void rentVan() {
        clearConsole();
        heading();

        Scanner scanner1 = new Scanner(System.in);

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
            rentalId = scanner1.nextLine().trim();

            if (rentalId.matches("^R\\d{3}$")) {
                boolean exists = false;

                for (Rental rent : rentalVans) {
                    if (rent.getRentalId().equalsIgnoreCase(rentalId)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
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
            customerId = scanner1.nextLine().trim();

            if (customerId.matches("^C\\d{3}$")) {
                boolean exists = false;

                for (Customer customer : customers) {
                    if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                        exists = true;
                        break;
                    }

                }

                if (exists) {
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
            vehicleId = scanner1.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {
                boolean exists = false;
                int index = -1;

                for (Van van : vans) {
                    if (van.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        index = vans.indexOf(van);
                        break;
                    }

                }

                if (exists) {
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
            rentalStartDate = scanner1.nextLine().trim();

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
            rentalEndDate = scanner1.nextLine().trim();

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
        for (Van van : vans) {
            if (van.getVehicleId().equalsIgnoreCase(vehicleId)) {
                rentalEstimatedCost = numberOfRentalDays * van.getDailyRentalRate();
                System.out.println("\nRental Estimated Cost    : Rs. " + rentalEstimatedCost);
                break;
            }
        }

        rentalVans.add(new Rental(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, numberOfRentalDays, rentalEstimatedCost, -1, -1, "Pending"));
        System.out.println("\nVan rental has been successfully recorded!");

        askWantToExit();

    }

    // Method to rent a Motorcycle
    public static void rentMotorcycle() {
        clearConsole();
        heading();

        Scanner scanner1 = new Scanner(System.in);

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
            rentalId = scanner1.nextLine().trim();

            if (rentalId.matches("^R\\d{3}$")) {
                boolean exists = false;

                for (Rental rent : rentalMotorcycles) {
                    if (rent.getRentalId().equalsIgnoreCase(rentalId)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
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
            customerId = scanner1.nextLine().trim();

            if (customerId.matches("^C\\d{3}$")) {
                boolean exists = false;

                for (Customer customer : customers) {
                    if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                        exists = true;
                        break;
                    }

                }

                if (exists) {
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
            vehicleId = scanner1.nextLine().trim();

            if (vehicleId.matches("^V\\d{3}$")) {
                boolean exists = false;
                int index = -1;

                for (Motorcycle motorcycle : motorcycles) {
                    if (motorcycle.getVehicleId().equalsIgnoreCase(vehicleId)) {
                        exists = true;
                        index = motorcycles.indexOf(motorcycle);
                        break;
                    }

                }

                if (exists) {
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
            rentalStartDate = scanner1.nextLine().trim();

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
            rentalEndDate = scanner1.nextLine().trim();

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
        for (Motorcycle motorcycle : motorcycles) {
            if (motorcycle.getVehicleId().equalsIgnoreCase(vehicleId)) {
                rentalEstimatedCost = numberOfRentalDays * motorcycle.getDailyRentalRate();
                System.out.println("\nRental Estimated Cost    : Rs. " + rentalEstimatedCost);
                break;
            }
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

            Scanner scanner2 = new Scanner(System.in);

            System.out.print("Enter your choice: ");

            try {

                int choice = scanner2.nextInt();

                if (choice > 5 || choice < 1) {

                    System.out.println("Invalid choice!\n");
                    scanner2.nextLine();

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
                scanner2.nextLine();

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

        Scanner scanner1 = new Scanner(System.in);

        // Rental ID
        while (true) {
            System.out.print("Enter Rental ID          : ");
            rentalId = scanner1.nextLine().trim();
            int index = -1;

            if (rentalId.matches("^R\\d{3}$")) {
                boolean exists = false;
                boolean alreadyCompleted = false;

                for (Rental rent : rentalCars) {
                    if (rent.getRentalId().equalsIgnoreCase(rentalId)) {
                        index = rentalCars.indexOf(rent);

                        if (rentalCars.get(index).getRentalStatus().equals("Completed")) {
                            System.out.println("Rental is already completed!\n");
                            alreadyCompleted = true;
                            break;

                        } else {
                            exists = true;
                            break;
                        }

                    }
                }

                if (exists) {
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
                    rentalCars.get(index).setRentalAcutualCost(rentalActualCost);
                    rentalCars.get(index).setRentalStatus("Completed");

                    for (Car car : cars) {
                        if (car.getVehicleId().equalsIgnoreCase(rentalCars.get(index).getVehicleId())) {
                            car.setAvailabilityStatus(true);
                            break;
                        }
                    }

                    System.out.println("\nCar Rental Return Processed Successfully!\n");
                    break;

                } else {
                    if (!alreadyCompleted) {
                        System.out.println("Rental ID is not found!\n");
                    }

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

        Scanner scanner1 = new Scanner(System.in);

        // Rental ID
        while (true) {
            System.out.print("Enter Rental ID          : ");
            rentalId = scanner1.nextLine().trim();
            int index = -1;

            if (rentalId.matches("^R\\d{3}$")) {
                boolean exists = false;
                boolean alreadyCompleted = false;

                for (Rental rent : rentalVans) {
                    if (rent.getRentalId().equalsIgnoreCase(rentalId)) {
                        index = rentalVans.indexOf(rent);
                        if (rentalVans.get(index).getRentalStatus().equals("Completed")) {
                            System.out.println("Rental is already completed!\n");
                            alreadyCompleted = true;
                            break;
                        } else {
                            exists = true;
                            break;
                        }

                    }
                }

                if (exists) {
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
                    rentalVans.get(index).setRentalAcutualCost(rentalActualCost);
                    rentalVans.get(index).setRentalStatus("Completed");

                    for (Van van : vans) {
                        if (van.getVehicleId().equalsIgnoreCase(rentalVans.get(index).getVehicleId())) {
                            van.setAvailabilityStatus(true);
                            break;
                        }
                    }

                    System.out.println("\nVan Rental Return Processed Successfully!\n");
                    break;

                } else {
                    if (!alreadyCompleted) {
                        System.out.println("Rental ID is not found!\n");
                    }

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

        Scanner scanner1 = new Scanner(System.in);

        // Rental ID
        while (true) {
            System.out.print("Enter Rental ID          : ");
            rentalId = scanner1.nextLine().trim();
            int index = -1;

            if (rentalId.matches("^R\\d{3}$")) {
                boolean exists = false;
                boolean alreadyCompleted = false;

                for (Rental rent : rentalMotorcycles) {
                    if (rent.getRentalId().equalsIgnoreCase(rentalId)) {
                        index = rentalMotorcycles.indexOf(rent);
                        if (rentalMotorcycles.get(index).getRentalStatus().equals("Completed")) {
                            System.out.println("Rental is already completed!\n");
                            alreadyCompleted = true;
                            break;
                        } else {
                            exists = true;
                            break;
                        }

                    }
                }
                if (exists) {
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
                    rentalMotorcycles.get(index).setRentalAcutualCost(rentalActualCost);
                    rentalMotorcycles.get(index).setRentalStatus("Completed");

                    for (Motorcycle motorcycle : motorcycles) {
                        if (motorcycle.getVehicleId().equalsIgnoreCase(rentalMotorcycles.get(index).getVehicleId())) {
                            motorcycle.setAvailabilityStatus(true);
                            break;
                        }
                    }

                    System.out.println("\nMotorcycle Rental Return Processed Successfully!\n");
                    break;

                } else {
                    if (!alreadyCompleted) {
                        System.out.println("Rental ID is not found!\n");
                    }

                }

            } else {
                System.out.println("Invalid Rental ID (Ex: - R001)\n");

            }

        }

        askWantToExit();

    }

    // Method to View All Rentals
    public static void viewRentals() {

        // 01. View All Cars
        clearConsole();
        heading();

        System.out.println("========");
        System.out.println("All Cars");
        System.out.println("========\n");

        String[] columnNames = {"Rental ID", "Customer ID", "Vehicle ID", "Start Date", "End Date", "No. of Days", "Estimated Rental Cost (Rs.)", "Discount (Rs.)", "Actual Cost (Rs. )", "Status"};

        int[] columnNamesLength = new int[10];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        int maxRentalId = Integer.MIN_VALUE;
        int maxCustomerId = Integer.MIN_VALUE;
        int maxVehicleId = Integer.MIN_VALUE;
        int maxStartDate = Integer.MIN_VALUE;
        int maxEndDate = Integer.MIN_VALUE;
        int maxNumberOfDays = Integer.MIN_VALUE;
        int maxEstimatedCost = Integer.MIN_VALUE;
        int maxDiscount = Integer.MIN_VALUE;
        int maxActualCost = Integer.MIN_VALUE;
        int maxStatus = Integer.MIN_VALUE;

        for (Rental rent : rentalCars) {

            if (rent.getRentalId() != null) {
                maxRentalId = Math.max(maxRentalId, rent.getRentalId().length());
            }

            if (rent.getCustomerId() != null) {
                maxCustomerId = Math.max(maxCustomerId, rent.getCustomerId().length());
            }

            if (rent.getVehicleId() != null) {
                maxVehicleId = Math.max(maxVehicleId, rent.getVehicleId().length());
            }

            if (rent.getRentalStartDate() != null) {
                maxStartDate = Math.max(maxStartDate, rent.getRentalStartDate().length());
            }

            if (rent.getRentalEndDate() != null) {
                maxEndDate = Math.max(maxEndDate, rent.getRentalEndDate().length());
            }

            if (rent.getNumberOfRentalDays() != 0) {
                maxNumberOfDays = Math.max(maxNumberOfDays, String.valueOf(rent.getNumberOfRentalDays()).length());
            }

            if (rent.getRentalEstimatedCost() != 0) {
                maxEstimatedCost = Math.max(maxEstimatedCost, String.valueOf(rent.getRentalEstimatedCost()).length());
            }

            if (rent.getDiscount() != 0) {
                maxDiscount = Math.max(maxDiscount, String.valueOf(rent.getDiscount()).length());
            }

            if (rent.getRentalActualCost() != 0) {
                maxActualCost = Math.max(maxActualCost, String.valueOf(rent.getRentalActualCost()).length());
            }

            if (rent.getRentalStatus() != null) {
                maxStatus = Math.max(maxStatus, rent.getRentalStatus().length());
            }

        }

        int rentalIdWidth = Math.max(columnNames[0].length(), maxRentalId);
        int customerIdWidth = Math.max(columnNames[1].length(), maxCustomerId);
        int vehicleIdWidth = Math.max(columnNames[2].length(), maxVehicleId);
        int startDateWidth = Math.max(columnNames[3].length(), maxStartDate);
        int endDateWidth = Math.max(columnNames[4].length(), maxEndDate);
        int numberOfDaysWidth = Math.max(columnNames[5].length(), maxNumberOfDays);
        int estimatedCostWidth = Math.max(columnNames[6].length(), maxEstimatedCost);
        int discountWidth = Math.max(columnNames[7].length(), maxDiscount);
        int actualCostWidth = Math.max(columnNames[8].length(), maxActualCost);
        int statusWidth = Math.max(columnNames[9].length(), maxStatus);

        int[] maxColumnWidth = {rentalIdWidth, customerIdWidth, vehicleIdWidth, startDateWidth, endDateWidth, numberOfDaysWidth, estimatedCostWidth, discountWidth, actualCostWidth, statusWidth};

        int totalColumnWidth = rentalIdWidth + customerIdWidth + vehicleIdWidth + startDateWidth + endDateWidth + numberOfDaysWidth + estimatedCostWidth + discountWidth + actualCostWidth + statusWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {
            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Rental rent : rentalCars) {

            // Rental ID
            if (rent.getRentalId().length() >= rentalIdWidth) {
                System.out.print("\n|" + rent.getRentalId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (rentalIdWidth - rent.getRentalId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalId() + "|");
            }

            // Customer ID
            if (rent.getCustomerId().length() >= customerIdWidth) {
                System.out.print(rent.getCustomerId() + "|");
            } else {
                for (int k = 0; k < (customerIdWidth - rent.getCustomerId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getCustomerId() + "|");
            }

            // Vehicle ID
            if (rent.getVehicleId().length() >= vehicleIdWidth) {
                System.out.print(rent.getVehicleId() + "|");
            } else {
                for (int k = 0; k < (vehicleIdWidth - rent.getVehicleId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getVehicleId() + "|");
            }

            // Start Date
            if (rent.getRentalStartDate().length() >= startDateWidth) {
                System.out.print(rent.getRentalStartDate() + "|");
            } else {
                for (int k = 0; k < (startDateWidth - rent.getRentalStartDate().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalStartDate() + "|");
            }

            // End Date
            if (rent.getRentalEndDate().length() >= endDateWidth) {
                System.out.print(rent.getRentalEndDate() + "|");
            } else {
                for (int k = 0; k < (endDateWidth - rent.getRentalEndDate().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalEndDate() + "|");
            }

            // Number of Days
            if ((String.valueOf(rent.getNumberOfRentalDays())).length() >= numberOfDaysWidth) {
                System.out.print(rent.getNumberOfRentalDays() + "|");
            } else {
                for (int k = 0; k < (numberOfDaysWidth - (String.valueOf(rent.getNumberOfRentalDays())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getNumberOfRentalDays() + "|");
            }

            // Estimated Cost
            if ((String.valueOf(rent.getRentalEstimatedCost())).length() >= estimatedCostWidth) {
                System.out.print(rent.getRentalEstimatedCost() + "|");
            } else {
                for (int k = 0; k < (estimatedCostWidth - (String.valueOf(rent.getRentalEstimatedCost())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalEstimatedCost() + "|");
            }

            // Discount
            if ((String.valueOf(rent.getDiscount())).length() >= discountWidth) {
                System.out.print(rent.getDiscount() + "|");
            } else {
                for (int k = 0; k < (discountWidth - (String.valueOf(rent.getDiscount())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getDiscount() + "|");
            }

            // Actual Cost
            if ((String.valueOf(rent.getRentalActualCost())).length() >= actualCostWidth) {
                System.out.print(rent.getRentalActualCost() + "|");
            } else {
                for (int k = 0; k < (actualCostWidth - (String.valueOf(rent.getRentalActualCost())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalActualCost() + "|");
            }

            // Rental Status
            if (rent.getRentalStatus().length() >= statusWidth) {
                System.out.print(rent.getRentalStatus() + "|");
            } else {
                for (int k = 0; k < (statusWidth - rent.getRentalStatus().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalStatus() + "|");
            }

        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

        // 02. View All Vans
        System.out.println("\n========");
        System.out.println("All Vans");
        System.out.println("========\n");

        columnNames = new String[]{"Rental ID", "Customer ID", "Vehicle ID", "Start Date", "End Date", "No. of Days", "Estimated Rental Cost (Rs.)", "Discount (Rs.)", "Actual Cost (Rs. )", "Status"};

        columnNamesLength = new int[10];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        maxRentalId = Integer.MIN_VALUE;
        maxCustomerId = Integer.MIN_VALUE;
        maxVehicleId = Integer.MIN_VALUE;
        maxStartDate = Integer.MIN_VALUE;
        maxEndDate = Integer.MIN_VALUE;
        maxNumberOfDays = Integer.MIN_VALUE;
        maxEstimatedCost = Integer.MIN_VALUE;
        maxDiscount = Integer.MIN_VALUE;
        maxActualCost = Integer.MIN_VALUE;
        maxStatus = Integer.MIN_VALUE;

        for (Rental rent : rentalVans) {

            if (rent.getRentalId() != null) {
                maxRentalId = Math.max(maxRentalId, rent.getRentalId().length());
            }

            if (rent.getCustomerId() != null) {
                maxCustomerId = Math.max(maxCustomerId, rent.getCustomerId().length());
            }

            if (rent.getVehicleId() != null) {
                maxVehicleId = Math.max(maxVehicleId, rent.getVehicleId().length());
            }

            if (rent.getRentalStartDate() != null) {
                maxStartDate = Math.max(maxStartDate, rent.getRentalStartDate().length());
            }

            if (rent.getRentalEndDate() != null) {
                maxEndDate = Math.max(maxEndDate, rent.getRentalEndDate().length());
            }

            if (rent.getNumberOfRentalDays() != 0) {
                maxNumberOfDays = Math.max(maxNumberOfDays, String.valueOf(rent.getNumberOfRentalDays()).length());
            }

            if (rent.getRentalEstimatedCost() != 0) {
                maxEstimatedCost = Math.max(maxEstimatedCost, String.valueOf(rent.getRentalEstimatedCost()).length());
            }

            if (rent.getDiscount() != 0) {
                maxDiscount = Math.max(maxDiscount, String.valueOf(rent.getDiscount()).length());
            }

            if (rent.getRentalActualCost() != 0) {
                maxActualCost = Math.max(maxActualCost, String.valueOf(rent.getRentalActualCost()).length());
            }

            if (rent.getRentalStatus() != null) {
                maxStatus = Math.max(maxStatus, rent.getRentalStatus().length());
            }

        }

        rentalIdWidth = Math.max(columnNames[0].length(), maxRentalId);
        customerIdWidth = Math.max(columnNames[1].length(), maxCustomerId);
        vehicleIdWidth = Math.max(columnNames[2].length(), maxVehicleId);
        startDateWidth = Math.max(columnNames[3].length(), maxStartDate);
        endDateWidth = Math.max(columnNames[4].length(), maxEndDate);
        numberOfDaysWidth = Math.max(columnNames[5].length(), maxNumberOfDays);
        estimatedCostWidth = Math.max(columnNames[6].length(), maxEstimatedCost);
        discountWidth = Math.max(columnNames[7].length(), maxDiscount);
        actualCostWidth = Math.max(columnNames[8].length(), maxActualCost);
        statusWidth = Math.max(columnNames[9].length(), maxStatus);

        maxColumnWidth = new int[]{rentalIdWidth, customerIdWidth, vehicleIdWidth, startDateWidth, endDateWidth, numberOfDaysWidth, estimatedCostWidth, discountWidth, actualCostWidth, statusWidth};

        totalColumnWidth = rentalIdWidth + customerIdWidth + vehicleIdWidth + startDateWidth + endDateWidth + numberOfDaysWidth + estimatedCostWidth + discountWidth + actualCostWidth + statusWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {
            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Rental rent : rentalVans) {

            // Rental ID
            if (rent.getRentalId().length() >= rentalIdWidth) {
                System.out.print("\n|" + rent.getRentalId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (rentalIdWidth - rent.getRentalId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalId() + "|");
            }

            // Customer ID
            if (rent.getCustomerId().length() >= customerIdWidth) {
                System.out.print(rent.getCustomerId() + "|");
            } else {
                for (int k = 0; k < (customerIdWidth - rent.getCustomerId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getCustomerId() + "|");
            }

            // Vehicle ID
            if (rent.getVehicleId().length() >= vehicleIdWidth) {
                System.out.print(rent.getVehicleId() + "|");
            } else {
                for (int k = 0; k < (vehicleIdWidth - rent.getVehicleId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getVehicleId() + "|");
            }

            // Start Date
            if (rent.getRentalStartDate().length() >= startDateWidth) {
                System.out.print(rent.getRentalStartDate() + "|");
            } else {
                for (int k = 0; k < (startDateWidth - rent.getRentalStartDate().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalStartDate() + "|");
            }

            // End Date
            if (rent.getRentalEndDate().length() >= endDateWidth) {
                System.out.print(rent.getRentalEndDate() + "|");
            } else {
                for (int k = 0; k < (endDateWidth - rent.getRentalEndDate().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalEndDate() + "|");
            }

            // Number of Days
            if ((String.valueOf(rent.getNumberOfRentalDays())).length() >= numberOfDaysWidth) {
                System.out.print(rent.getNumberOfRentalDays() + "|");
            } else {
                for (int k = 0; k < (numberOfDaysWidth - (String.valueOf(rent.getNumberOfRentalDays())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getNumberOfRentalDays() + "|");
            }

            // Estimated Cost
            if ((String.valueOf(rent.getRentalEstimatedCost())).length() >= estimatedCostWidth) {
                System.out.print(rent.getRentalEstimatedCost() + "|");
            } else {
                for (int k = 0; k < (estimatedCostWidth - (String.valueOf(rent.getRentalEstimatedCost())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalEstimatedCost() + "|");
            }

            // Discount
            if ((String.valueOf(rent.getDiscount())).length() >= discountWidth) {
                System.out.print(rent.getDiscount() + "|");
            } else {
                for (int k = 0; k < (discountWidth - (String.valueOf(rent.getDiscount())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getDiscount() + "|");
            }

            // Actual Cost
            if ((String.valueOf(rent.getRentalActualCost())).length() >= actualCostWidth) {
                System.out.print(rent.getRentalActualCost() + "|");
            } else {
                for (int k = 0; k < (actualCostWidth - (String.valueOf(rent.getRentalActualCost())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalActualCost() + "|");
            }

            // Rental Status
            if (rent.getRentalStatus().length() >= statusWidth) {
                System.out.print(rent.getRentalStatus() + "|");
            } else {
                for (int k = 0; k < (statusWidth - rent.getRentalStatus().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalStatus() + "|");
            }

        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

        // 03. View All Motorcycles
        System.out.println("\n===============");
        System.out.println("All Motorcycles");
        System.out.println("===============\n");

        columnNames = new String[]{"Rental ID", "Customer ID", "Vehicle ID", "Start Date", "End Date", "No. of Days", "Estimated Rental Cost (Rs.)", "Discount (Rs.)", "Actual Cost (Rs. )", "Status"};

        columnNamesLength = new int[10];

        for (int i = 0; i < columnNames.length; i++) {
            columnNamesLength[i] = columnNames[i].length();
        }

        maxRentalId = Integer.MIN_VALUE;
        maxCustomerId = Integer.MIN_VALUE;
        maxVehicleId = Integer.MIN_VALUE;
        maxStartDate = Integer.MIN_VALUE;
        maxEndDate = Integer.MIN_VALUE;
        maxNumberOfDays = Integer.MIN_VALUE;
        maxEstimatedCost = Integer.MIN_VALUE;
        maxDiscount = Integer.MIN_VALUE;
        maxActualCost = Integer.MIN_VALUE;
        maxStatus = Integer.MIN_VALUE;

        for (Rental rent : rentalMotorcycles) {

            if (rent.getRentalId() != null) {
                maxRentalId = Math.max(maxRentalId, rent.getRentalId().length());
            }

            if (rent.getCustomerId() != null) {
                maxCustomerId = Math.max(maxCustomerId, rent.getCustomerId().length());
            }

            if (rent.getVehicleId() != null) {
                maxVehicleId = Math.max(maxVehicleId, rent.getVehicleId().length());
            }

            if (rent.getRentalStartDate() != null) {
                maxStartDate = Math.max(maxStartDate, rent.getRentalStartDate().length());
            }

            if (rent.getRentalEndDate() != null) {
                maxEndDate = Math.max(maxEndDate, rent.getRentalEndDate().length());
            }

            if (rent.getNumberOfRentalDays() != 0) {
                maxNumberOfDays = Math.max(maxNumberOfDays, String.valueOf(rent.getNumberOfRentalDays()).length());
            }

            if (rent.getRentalEstimatedCost() != 0) {
                maxEstimatedCost = Math.max(maxEstimatedCost, String.valueOf(rent.getRentalEstimatedCost()).length());
            }

            if (rent.getDiscount() != 0) {
                maxDiscount = Math.max(maxDiscount, String.valueOf(rent.getDiscount()).length());
            }

            if (rent.getRentalActualCost() != 0) {
                maxActualCost = Math.max(maxActualCost, String.valueOf(rent.getRentalActualCost()).length());
            }

            if (rent.getRentalStatus() != null) {
                maxStatus = Math.max(maxStatus, rent.getRentalStatus().length());
            }

        }

        rentalIdWidth = Math.max(columnNames[0].length(), maxRentalId);
        customerIdWidth = Math.max(columnNames[1].length(), maxCustomerId);
        vehicleIdWidth = Math.max(columnNames[2].length(), maxVehicleId);
        startDateWidth = Math.max(columnNames[3].length(), maxStartDate);
        endDateWidth = Math.max(columnNames[4].length(), maxEndDate);
        numberOfDaysWidth = Math.max(columnNames[5].length(), maxNumberOfDays);
        estimatedCostWidth = Math.max(columnNames[6].length(), maxEstimatedCost);
        discountWidth = Math.max(columnNames[7].length(), maxDiscount);
        actualCostWidth = Math.max(columnNames[8].length(), maxActualCost);
        statusWidth = Math.max(columnNames[9].length(), maxStatus);

        maxColumnWidth = new int[]{rentalIdWidth, customerIdWidth, vehicleIdWidth, startDateWidth, endDateWidth, numberOfDaysWidth, estimatedCostWidth, discountWidth, actualCostWidth, statusWidth};

        totalColumnWidth = rentalIdWidth + customerIdWidth + vehicleIdWidth + startDateWidth + endDateWidth + numberOfDaysWidth + estimatedCostWidth + discountWidth + actualCostWidth + statusWidth + (columnNames.length + 1);

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n|");

        for (int i = 0; i < columnNames.length; i++) {
            if (columnNamesLength[i] >= maxColumnWidth[i]) {
                System.out.print(columnNames[i] + "|");
            } else {
                System.out.print(columnNames[i]);
                for (int k = 0; k < (maxColumnWidth[i] - columnNamesLength[i]); k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }

        for (Rental rent : rentalMotorcycles) {

            // Rental ID
            if (rent.getRentalId().length() >= rentalIdWidth) {
                System.out.print("\n|" + rent.getRentalId() + "|");
            } else {
                System.out.print("\n|");
                for (int k = 0; k < (rentalIdWidth - rent.getRentalId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalId() + "|");
            }

            // Customer ID
            if (rent.getCustomerId().length() >= customerIdWidth) {
                System.out.print(rent.getCustomerId() + "|");
            } else {
                for (int k = 0; k < (customerIdWidth - rent.getCustomerId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getCustomerId() + "|");
            }

            // Vehicle ID
            if (rent.getVehicleId().length() >= vehicleIdWidth) {
                System.out.print(rent.getVehicleId() + "|");
            } else {
                for (int k = 0; k < (vehicleIdWidth - rent.getVehicleId().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getVehicleId() + "|");
            }

            // Start Date
            if (rent.getRentalStartDate().length() >= startDateWidth) {
                System.out.print(rent.getRentalStartDate() + "|");
            } else {
                for (int k = 0; k < (startDateWidth - rent.getRentalStartDate().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalStartDate() + "|");
            }

            // End Date
            if (rent.getRentalEndDate().length() >= endDateWidth) {
                System.out.print(rent.getRentalEndDate() + "|");
            } else {
                for (int k = 0; k < (endDateWidth - rent.getRentalEndDate().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalEndDate() + "|");
            }

            // Number of Days
            if ((String.valueOf(rent.getNumberOfRentalDays())).length() >= numberOfDaysWidth) {
                System.out.print(rent.getNumberOfRentalDays() + "|");
            } else {
                for (int k = 0; k < (numberOfDaysWidth - (String.valueOf(rent.getNumberOfRentalDays())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getNumberOfRentalDays() + "|");
            }

            // Estimated Cost
            if ((String.valueOf(rent.getRentalEstimatedCost())).length() >= estimatedCostWidth) {
                System.out.print(rent.getRentalEstimatedCost() + "|");
            } else {
                for (int k = 0; k < (estimatedCostWidth - (String.valueOf(rent.getRentalEstimatedCost())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalEstimatedCost() + "|");
            }

            // Discount
            if ((String.valueOf(rent.getDiscount())).length() >= discountWidth) {
                System.out.print(rent.getDiscount() + "|");
            } else {
                for (int k = 0; k < (discountWidth - (String.valueOf(rent.getDiscount())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getDiscount() + "|");
            }

            // Actual Cost
            if ((String.valueOf(rent.getRentalActualCost())).length() >= actualCostWidth) {
                System.out.print(rent.getRentalActualCost() + "|");
            } else {
                for (int k = 0; k < (actualCostWidth - (String.valueOf(rent.getRentalActualCost())).length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalActualCost() + "|");
            }

            // Rental Status
            if (rent.getRentalStatus().length() >= statusWidth) {
                System.out.print(rent.getRentalStatus() + "|");
            } else {
                for (int k = 0; k < (statusWidth - rent.getRentalStatus().length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(rent.getRentalStatus() + "|");
            }

        }
        System.out.print("\n");

        for (int i = 0; i < totalColumnWidth; i++) {
            System.out.print("=");
        }
        System.out.print("\n");

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