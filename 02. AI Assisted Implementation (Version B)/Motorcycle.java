/**
 * Motorcycle.java
 * Subclass of Vehicle representing a motorcycle.
 */
public class Motorcycle extends Vehicle {

    // Static discount rate applicable to all Motorcycle objects (10%)
    public static final double DISCOUNT_RATE = 10;

    // ---------------------- Private Fields ----------------------
    private int engineCapacity;

    // ---------------------- Constructor Chaining ----------------------
    public Motorcycle() {
        this("Unknown");
    }

    public Motorcycle(String vehicleId) {
        this(vehicleId, "Unknown");
    }

    public Motorcycle(String vehicleId, String vehicleName) {
        this(vehicleId, vehicleName, 0);
    }

    public Motorcycle(String vehicleId, String vehicleName, double dailyRentalRate) {
        this(vehicleId, vehicleName, dailyRentalRate, false);
    }

    public Motorcycle(String vehicleId, String vehicleName, double dailyRentalRate, boolean availabilityStatus) {
        this(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, 0);
    }

    // Final constructor - initializes all fields (calls super for inherited fields)
    public Motorcycle(String vehicleId, String vehicleName, double dailyRentalRate, boolean availabilityStatus,
                      int engineCapacity) {
        super(vehicleId, vehicleName, dailyRentalRate, availabilityStatus);
        this.engineCapacity = engineCapacity;
    }

    // ---------------------- Getters & Setters ----------------------
    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    // ---------------------- Overridden Abstract Methods ----------------------
    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT_RATE;
    }

    @Override
    public String getExtraDetails() {
        return String.format("Engine: %dcc", engineCapacity);
    }
}