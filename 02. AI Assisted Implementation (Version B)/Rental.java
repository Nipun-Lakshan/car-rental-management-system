/**
 * Rental.java
 * Represents a rental transaction linking a Customer to a Vehicle.
 */
public class Rental {

    // ---------------------- Private Fields ----------------------
    private String rentalId;
    private String customerId;
    private String vehicleId;
    private String rentalStartDate;
    private String rentalEndDate;
    private int numberOfRentalDays;
    private double rentalEstimatedCost;
    private double discount;
    private double rentalActualCost;
    private String rentalStatus;

    // ---------------------- Constructor Chaining ----------------------
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
        this(rentalId, customerId, vehicleId, "Unknown", "Unknown");
    }

    public Rental(String rentalId, String customerId, String vehicleId,
                  String rentalStartDate, String rentalEndDate) {
        this(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, 0);
    }

    public Rental(String rentalId, String customerId, String vehicleId,
                  String rentalStartDate, String rentalEndDate, int numberOfRentalDays) {
        this(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate,
                numberOfRentalDays, 0, 0);
    }

    public Rental(String rentalId, String customerId, String vehicleId,
                  String rentalStartDate, String rentalEndDate, int numberOfRentalDays,
                  double rentalEstimatedCost, double discount) {
        this(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate,
                numberOfRentalDays, rentalEstimatedCost, discount, 0, "Unknown");
    }

    // Final constructor - initializes all fields
    public Rental(String rentalId, String customerId, String vehicleId,
                  String rentalStartDate, String rentalEndDate, int numberOfRentalDays,
                  double rentalEstimatedCost, double discount, double rentalActualCost,
                  String rentalStatus) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.numberOfRentalDays = numberOfRentalDays;
        this.rentalEstimatedCost = rentalEstimatedCost;
        this.discount = discount;
        this.rentalActualCost = rentalActualCost;
        this.rentalStatus = rentalStatus;
    }

    // ---------------------- Getters & Setters ----------------------
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

    public String getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(String rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public String getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(String rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public int getNumberOfRentalDays() {
        return numberOfRentalDays;
    }

    public void setNumberOfRentalDays(int numberOfRentalDays) {
        this.numberOfRentalDays = numberOfRentalDays;
    }

    public double getRentalEstimatedCost() {
        return rentalEstimatedCost;
    }

    public void setRentalEstimatedCost(double rentalEstimatedCost) {
        this.rentalEstimatedCost = rentalEstimatedCost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getRentalActualCost() {
        return rentalActualCost;
    }

    public void setRentalActualCost(double rentalActualCost) {
        this.rentalActualCost = rentalActualCost;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    // ---------------------- Utility ----------------------
    @Override
    public String toString() {
        return String.format(
                "Rental ID: %-8s | Customer: %-8s | Vehicle: %-8s | Start: %-10s | End: %-10s | " +
                        "Days: %-3d | Est. Cost: %-8.2f | Discount: %-5.2f%% | Actual Cost: %-8.2f | Status: %s",
                rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate,
                numberOfRentalDays, rentalEstimatedCost, discount, rentalActualCost, rentalStatus);
    }
}