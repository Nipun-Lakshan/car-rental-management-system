// Application : Car Rental Management System
// Method      : Manual Implementation
// Author      : A. W. W. A. Nipun Lakshan

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

        int vehicleIdWidth =
                Math.max(columnNames[0].length(), maxVehicleId);

        int vehicleNameWidth =
                Math.max(columnNames[1].length(), maxVehicleName);

        int dailyRentalRateWidth =
                Math.max(columnNames[2].length(), maxDailyRentalRate);

        int availabilityStatusWidth =
                Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int numberOfSeatsWidth =
                Math.max(columnNames[4].length(), maxNumberOfSeats);

        int fuelTypeWidth =
                Math.max(columnNames[5].length(), maxFuelType);

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

        int vehicleIdWidth =
                Math.max(columnNames[0].length(), maxVehicleId);

        int vehicleNameWidth =
                Math.max(columnNames[1].length(), maxVehicleName);

        int dailyRentalRateWidth =
                Math.max(columnNames[2].length(), maxDailyRentalRate);

        int availabilityStatusWidth =
                Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int cargoCapacityWidth =
                Math.max(columnNames[4].length(), maxCargoCapacity);

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

        int vehicleIdWidth =
                Math.max(columnNames[0].length(), maxVehicleId);

        int vehicleNameWidth =
                Math.max(columnNames[1].length(), maxVehicleName);

        int dailyRentalRateWidth =
                Math.max(columnNames[2].length(), maxDailyRentalRate);

        int availabilityStatusWidth =
                Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int engineCapacityWidth =
                Math.max(columnNames[4].length(), maxEngineCapacity);

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

        int vehicleIdWidth =
                Math.max(columnNames[0].length(), maxVehicleId);

        int vehicleNameWidth =
                Math.max(columnNames[1].length(), maxVehicleName);

        int dailyRentalRateWidth =
                Math.max(columnNames[2].length(), maxDailyRentalRate);

        int availabilityStatusWidth =
                Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int numberOfSeatsWidth =
                Math.max(columnNames[4].length(), maxNumberOfSeats);

        int fuelTypeWidth =
                Math.max(columnNames[5].length(), maxFuelType);

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

        vehicleIdWidth =
                Math.max(columnNames[0].length(), maxVehicleId);

        vehicleNameWidth =
                Math.max(columnNames[1].length(), maxVehicleName);

        dailyRentalRateWidth =
                Math.max(columnNames[2].length(), maxDailyRentalRate);

        availabilityStatusWidth =
                Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int cargoCapacityWidth =
                Math.max(columnNames[4].length(), maxCargoCapacity);

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

        vehicleIdWidth =
                Math.max(columnNames[0].length(), maxVehicleId);

        vehicleNameWidth =
                Math.max(columnNames[1].length(), maxVehicleName);

        dailyRentalRateWidth =
                Math.max(columnNames[2].length(), maxDailyRentalRate);

        availabilityStatusWidth =
                Math.max(columnNames[3].length(), maxAvailabilityStatus);

        int engineCapacityWidth =
                Math.max(columnNames[4].length(), maxEngineCapacity);

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

                    System.out.println("Customer ID is already in use!");

                } else {

                    break;

                }

            } else {

                System.out.println("Invalid Customer ID (Ex: - C001)");

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

    // Main Method
    public static void main(String[] args) {

        // Load Some Demo Data For Cars
        cars.add(new Car("V001", "Toyota Prius 2015", 2000, true, 4, "Petrol"));
        cars.add(new Car("V002", "Honda Vezel RS 2019", 2500, true, 4, "Petrol"));
        cars.add(new Car("V003", "Toyota Axio 2018", 2200, true, 5, "Petrol"));
        cars.add(new Car("V004", "Suzuki Wagon R 2017", 1800, false, 5, "Petrol"));
        cars.add(new Car("V005", "Nissan X-Trail 2016", 3000, true, 5, "Petrol"));
        cars.add(new Car("V006", "Toyota Aqua 2019", 2300, true, 5, "Hybrid"));
        cars.add(new Car("V007", "Honda Grace 2018", 2400, false, 5, "Hybrid"));
        cars.add(new Car("V008", "Mazda Demio 2017", 1900, true, 5, "Petrol"));
        cars.add(new Car("V009", "Suzuki Alto 2019", 1600, true, 4, "Petrol"));
        cars.add(new Car("V010", "Toyota Corolla 2020", 3200, true, 5, "Petrol"));

        // Load Some Demo Data For Vans
        vans.add(new Van("V001", "Toyota Hiace Super GL 2017", 5000, true, 1000));
        vans.add(new Van("V002", "Toyota Hiace Super GL 2010", 4000, true, 1000));
        vans.add(new Van("V003", "Nissan Caravan 2018", 4800, false, 1200));
        vans.add(new Van("V004", "KDH 200 High Roof 2016", 4500, true, 1100));
        vans.add(new Van("V005", "Toyota Dolphin 2008", 3500, true, 1500));
        vans.add(new Van("V006", "Nissan NV350 2020", 5500, true, 1300));
        vans.add(new Van("V007", "Mitsubishi Delica 2015", 4300, false, 1000));
        vans.add(new Van("V008", "Toyota Hiace DX 2019", 5200, true, 1250));
        vans.add(new Van("V009", "Mazda Bongo 2014", 3700, true, 900));
        vans.add(new Van("V010", "Toyota Quantum 2021", 6000, true, 1500));

        // Load Some Demo Data to Motorcycles
        motorcycles.add(new Motorcycle("V001", "Honda CBR 150R", 1000, true, 150));
        motorcycles.add(new Motorcycle("V002", "Honda Hornet 160R", 1200, true, 160));
        motorcycles.add(new Motorcycle("V003", "Yamaha FZ-S V3", 1100, false, 150));
        motorcycles.add(new Motorcycle("V004", "Bajaj Pulsar NS200", 1300, true, 200));
        motorcycles.add(new Motorcycle("V005", "TVS Apache RTR 160", 1000, true, 160));
        motorcycles.add(new Motorcycle("V006", "Suzuki Gixxer SF", 1250, true, 155));
        motorcycles.add(new Motorcycle("V007", "KTM Duke 200", 1800, false, 200));
        motorcycles.add(new Motorcycle("V008", "Yamaha MT-15", 1700, true, 155));
        motorcycles.add(new Motorcycle("V009", "Honda Dio", 800, true, 110));
        motorcycles.add(new Motorcycle("V010", "Hero Hunk", 900, true, 150));

        // Load Some Demo Data to Customers
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

        // Clear Console
        clearConsole();

        // Load Main Menu
        mainMenu();
    }
}