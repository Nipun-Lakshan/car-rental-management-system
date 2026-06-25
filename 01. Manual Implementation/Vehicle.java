public abstract class Vehicle {

    private String vehicleId;
    private String vehicleName;
    private int dailyRentalRate;
    private boolean availabilityStatus;

    public Vehicle() {
        this("Unknown");
    }

    public Vehicle(String vehicleId) {
        this(vehicleId, "Unknown");
    }

    public Vehicle(String vehicleId, String vehicleName) {
        this(vehicleId, vehicleName, 0);
    }

    public Vehicle(String vehicleId, String vehicleName, int dailyRentalRate) {
        this(vehicleId, vehicleName, dailyRentalRate, false);
    }

    public Vehicle(String vehicleId, String vehicleName, int dailyRentalRate, boolean availabilityStatus) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.dailyRentalRate = dailyRentalRate;
        this.availabilityStatus = availabilityStatus;
    }

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

    public int getDailyRentalRate() {
        return dailyRentalRate;
    }

    public void setDailyRentalRate(int dailyRentalRate) {
        this.dailyRentalRate = dailyRentalRate;
    }

    public boolean isAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

}