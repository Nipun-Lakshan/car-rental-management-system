public class Rental {

    private String rentalId;
    private String customerId;
    private String vehicleId;
    private String rentalDate;
    private int numberOfRentalDays;
    private int rentalCost;
    private String rentalStatus;

    public Rental() {
        this("Unknown");
    }

    public Rental(String rentalId) {
        this(rentalId, "Unknown");
    }

    public Rental(String rentalId, String customerId) {
        this(rentalId, customerId, "Unknown");
    }

    public Rental(String rentalId, String customerId, String vehicleId) {
        this(rentalId, customerId, vehicleId, "Unknown");
    }

    public Rental(String rentalId, String customerId, String vehicleId, String rentalDate) {
        this(rentalId, customerId, vehicleId, rentalDate, 0);
    }

    public Rental(String rentalId, String customerId, String vehicleId, String rentalDate, int numberOfRentalDays) {
        this(rentalId, customerId, vehicleId, rentalDate, numberOfRentalDays, 0);
    }

    public Rental(String rentalId, String customerId, String vehicleId, String rentalDate, int numberOfRentalDays, int rentalCost) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.rentalDate = rentalDate;
        this.numberOfRentalDays = numberOfRentalDays;
        this.rentalCost = rentalCost;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public int getNumberOfRentalDays() {
        return numberOfRentalDays;
    }

    public void setNumberOfRentalDays(int numberOfRentalDays) {
        this.numberOfRentalDays = numberOfRentalDays;
    }

    public int getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(int dailyRentalCost, int numberOfRentalDays) {
        this.rentalCost = dailyRentalCost * numberOfRentalDays;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

}
