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
    public static void registerVehicle() {
        clearConsole();
        heading();
        System.out.println("01. Register a Car");
        System.out.println("02. Register a Van");
        System.out.println("03. Register a Motorcycle\n");
        System.out.println("04. Exit\n");

        L1:
        while (true) {

            Scanner scanner2 = new Scanner(System.in);

            System.out.print("Enter your choice: ");
            try {
                int choice = scanner2.nextInt();
                if (choice > 4 || choice < 1) {
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
            vehicleId = scanner1.nextLine();
            if ((vehicleId.trim()).matches("^V\\d{3}$")) {
                break;
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
                if (fuelType.equalsIgnoreCase("Diesel") || fuelType.equalsIgnoreCase("Petrol")) {
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
            vehicleId = scanner1.nextLine();
            if ((vehicleId.trim()).matches("^V\\d{3}$")) {
                break;
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
            System.out.print("Enter Vehicle ID             : ");
            vehicleId = scanner1.nextLine();
            if ((vehicleId.trim()).matches("^V\\d{3}$")) {
                break;
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
                System.out.print("\nEnter Cargo Capacity (In CC) : ");
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
        System.out.println("04. View all Vehicles\n");
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
//                            viewAllCars(); *
                            break L1;
                        case 2:
                            clearConsole();
                            heading();
//                            viewAllVans(); *
                            break L1;
                        case 3:
                            clearConsole();
                            heading();
//                            viewAllMotorcycles(); *
                            break L1;
                        case 4:
                            clearConsole();
                            heading();
//                            viewAllVehicles(); *
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

    // Main Method
    public static void main(String[] args) {

        // Clear Console
        clearConsole();

        // Load Main Menu
        mainMenu();
    }
}