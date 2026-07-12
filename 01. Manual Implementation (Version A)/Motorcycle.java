public class Motorcycle extends Vehicle {

    public static int DISCOUNT_RATE = 10;

    private int engineCapacity;

    public Motorcycle() {
        this("Unknown");
    }

    public Motorcycle(String vehicleId) {
        this(vehicleId, "Unknown");
    }

    public Motorcycle(String vehicleId, String vehicleName) {
        this(vehicleId, vehicleName, 0);
    }

    public Motorcycle(String vehicleId, String vehicleName, int dailyRentalRate) {
        this(vehicleId, vehicleName, dailyRentalRate, false);
    }

    public Motorcycle(String vehicleId, String vehicleName, int dailyRentalRate, boolean availabilityStatus) {
        this(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, 0);
    }

    public Motorcycle(String vehicleId, String vehicleName, int dailyRentalRate, boolean availabilityStatus, int engineCapacity) {
        super(vehicleId, vehicleName, dailyRentalRate, availabilityStatus);
        this.engineCapacity = engineCapacity;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

}