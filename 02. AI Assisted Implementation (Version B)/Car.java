/**
 * Car.java
 * Subclass of Vehicle representing a car.
 */
public class Car extends Vehicle {

    // Static discount rate applicable to all Car objects (5%)
    public static final double DISCOUNT_RATE = 5;

    // ---------------------- Private Fields ----------------------
    private int numberOfSeats;
    private String fuelType;

    // ---------------------- Constructor Chaining ----------------------
    public Car() {
        this("Unknown");
    }

    public Car(String vehicleId) {
        this(vehicleId, "Unknown");
    }

    public Car(String vehicleId, String vehicleName) {
        this(vehicleId, vehicleName, 0);
    }

    public Car(String vehicleId, String vehicleName, double dailyRentalRate) {
        this(vehicleId, vehicleName, dailyRentalRate, false);
    }

    public Car(String vehicleId, String vehicleName, double dailyRentalRate, boolean availabilityStatus) {
        this(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, 0, "Unknown");
    }

    // Final constructor - initializes all fields (calls super for inherited fields)
    public Car(String vehicleId, String vehicleName, double dailyRentalRate, boolean availabilityStatus,
               int numberOfSeats, String fuelType) {
        super(vehicleId, vehicleName, dailyRentalRate, availabilityStatus);
        this.numberOfSeats = numberOfSeats;
        this.fuelType = fuelType;
    }

    // ---------------------- Getters & Setters ----------------------
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    // ---------------------- Overridden Abstract Methods ----------------------
    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT_RATE;
    }

    @Override
    public String getExtraDetails() {
        return String.format("Seats: %d | Fuel: %s", numberOfSeats, fuelType);
    }
}