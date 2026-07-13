/**
 * Represents a rentable car.
 * Extends Vehicle with the extra attributes that are specific to
 * cars: the number of seats and the fuel type. DISCOUNT_RATE is the
 * percentage discount applied to the estimated cost when a car is returned.
 */
public class Car extends Vehicle {

    public static int DISCOUNT_RATE = 5;

    private int numberOfSeats;
    private String fuelType;

    public Car() {
        this("Unknown");
    }

    public Car(String vehicleId) {
        this(vehicleId, "Unknown");
    }

    public Car(String vehicleId, String vehicleName) {
        this(vehicleId, vehicleName, 0);
    }

    public Car(String vehicleId, String vehicleName, int dailyRentalRate) {
        this(vehicleId, vehicleName, dailyRentalRate, false);
    }

    public Car(String vehicleId, String vehicleName, int dailyRentalRate, boolean availabilityStatus) {
        this(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, 0);
    }

    public Car(String vehicleId, String vehicleName, int dailyRentalRate, boolean availabilityStatus, int numberOfSeats) {
        this(vehicleId, vehicleName, dailyRentalRate, availabilityStatus, numberOfSeats, "Unknown");
    }

    public Car(String vehicleId, String vehicleName, int dailyRentalRate, boolean availabilityStatus, int numberOfSeats, String fuelType) {
        super(vehicleId, vehicleName, dailyRentalRate, availabilityStatus);
        this.numberOfSeats = numberOfSeats;
        this.fuelType = fuelType;
    }

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

}