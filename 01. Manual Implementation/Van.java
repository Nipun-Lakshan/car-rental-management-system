public class Van extends Vehicle {

    public static int DISCOUNT_RATE = 8;

    private int cargoCapacity;

    public Van() {
        this("Unknown");
    }

    public Van(String vehicleId) {
        this(vehicleId, "Unknown");
    }

    public Van(String vehicleId, String vehicleName) {
        this(vehicleId, vehicleName, 0);
    }

    public Van(String vehicleId, String vehicleName, int dailyRentalRate) {
        this(vehicleId, vehicleName, dailyRentalRate, false);
    }

    public Van(String vehicleId, String vehicleName, int dailyRentalRate, boolean availabilityStatus) {
        this(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, 0);
    }

    public Van(String vehicleId, String vehicleName, int dailyRentalRate, boolean availabilityStatus, int cargoCapacity) {
        super(vehicleId, vehicleName, dailyRentalRate, availabilityStatus);
        this.cargoCapacity = cargoCapacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

}