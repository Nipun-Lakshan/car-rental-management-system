/**
 * Van.java
 * Subclass of Vehicle representing a van.
 */
public class Van extends Vehicle {

    // Static discount rate applicable to all Van objects (8%)
    public static final double DISCOUNT_RATE = 8;

    // ---------------------- Private Fields ----------------------
    private int cargoCapacity;

    // ---------------------- Constructor Chaining ----------------------
    public Van() {
        this("Unknown");
    }

    public Van(String vehicleId) {
        this(vehicleId, "Unknown");
    }

    public Van(String vehicleId, String vehicleName) {
        this(vehicleId, vehicleName, 0);
    }

    public Van(String vehicleId, String vehicleName, double dailyRentalRate) {
        this(vehicleId, vehicleName, dailyRentalRate, false);
    }

    public Van(String vehicleId, String vehicleName, double dailyRentalRate, boolean availabilityStatus) {
        this(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, 0);
    }

    // Final constructor - initializes all fields (calls super for inherited fields)
    public Van(String vehicleId, String vehicleName, double dailyRentalRate, boolean availabilityStatus,
               int cargoCapacity) {
        super(vehicleId, vehicleName, dailyRentalRate, availabilityStatus);
        this.cargoCapacity = cargoCapacity;
    }

    // ---------------------- Getters & Setters ----------------------
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    // ---------------------- Overridden Abstract Methods ----------------------
    @Override
    public String getVehicleType() {
        return "Van";
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT_RATE;
    }

    @Override
    public String getExtraDetails() {
        return String.format("Cargo: %d kg", cargoCapacity);
    }
}