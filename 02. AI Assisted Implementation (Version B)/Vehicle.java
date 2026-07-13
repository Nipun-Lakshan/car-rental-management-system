/**
 * Vehicle.java
 * Abstract base class representing a generic rentable vehicle.
 * Demonstrates constructor chaining, encapsulation, and abstraction.
 */
public abstract class Vehicle {

    // ---------------------- Private Fields ----------------------
    private String vehicleId;
    private String vehicleName;
    private double dailyRentalRate;
    private boolean availabilityStatus;

    // ---------------------- Constructor Chaining ----------------------
    public Vehicle() {
        this("Unknown");
    }

    public Vehicle(String vehicleId) {
        this(vehicleId, "Unknown");
    }

    public Vehicle(String vehicleId, String vehicleName) {
        this(vehicleId, vehicleName, 0);
    }

    public Vehicle(String vehicleId, String vehicleName, double dailyRentalRate) {
        this(vehicleId, vehicleName, dailyRentalRate, false);
    }

    // Final constructor - initializes all fields
    public Vehicle(String vehicleId, String vehicleName, double dailyRentalRate, boolean availabilityStatus) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.dailyRentalRate = dailyRentalRate;
        this.availabilityStatus = availabilityStatus;
    }

    // ---------------------- Getters & Setters ----------------------
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public double getDailyRentalRate() {
        return dailyRentalRate;
    }

    public void setDailyRentalRate(double dailyRentalRate) {
        this.dailyRentalRate = dailyRentalRate;
    }

    public boolean isAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    // ---------------------- Abstract Methods ----------------------
    /**
     * Returns the specific type/category of the vehicle (e.g. "Car").
     * Implemented differently by each subclass (polymorphism).
     */
    public abstract String getVehicleType();

    /**
     * Returns the discount rate (%) applicable to this vehicle category.
     */
    public abstract double getDiscountRate();

    /**
     * Returns a formatted string with subclass-specific extra details.
     */
    public abstract String getExtraDetails();

    // ---------------------- Common Utility ----------------------
    @Override
    public String toString() {
        return String.format(
                "ID: %-8s | Type: %-10s | Name: %-15s | Rate/Day: %-10.2f | Available: %-5s | %s",
                vehicleId, getVehicleType(), vehicleName, dailyRentalRate,
                availabilityStatus ? "Yes" : "No", getExtraDetails());
    }
}