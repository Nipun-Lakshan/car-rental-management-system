public class Rental {

    private String rentalId;
    private String customerId;
    private String vehicleId;
    private String rentalStartDate;
    private String rentalEndDate;
    private int numberOfRentalDays;
    private int rentalEstimatedCost;
    private int discount;
    private int rentalActualCost;
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

    public Rental(String rentalId, String customerId, String vehicleId, String rentalStartDate) {
        this(rentalId, customerId, vehicleId, rentalStartDate, "Unknown");
    }

    public Rental(String rentalId, String customerId, String vehicleId, String rentalStartDate, String rentalEndDate) {
        this(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, 0);
    }

    public Rental(String rentalId, String customerId, String vehicleId, String rentalStartDate, String rentalEndDate, int numberOfRentalDays) {
        this(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, numberOfRentalDays, 0);
    }

    public Rental(String rentalId, String customerId, String vehicleId, String rentalStartDate, String rentalEndDate, int numberOfRentalDays, int rentalEstimatedCost) {
        this(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, numberOfRentalDays, rentalEstimatedCost, 0);
    }

    public Rental(String rentalId, String customerId, String vehicleId, String rentalStartDate, String rentalEndDate, int numberOfRentalDays, int rentalEstimatedCost, int discount) {
        this(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, numberOfRentalDays, rentalEstimatedCost, discount, 0);
    }

    public Rental(String rentalId, String customerId, String vehicleId, String rentalStartDate, String rentalEndDate, int numberOfRentalDays, int rentalEstimatedCost, int discount, int rentalAcutualCost) {
        this(rentalId, customerId, vehicleId, rentalStartDate, rentalEndDate, numberOfRentalDays, rentalEstimatedCost, discount, rentalAcutualCost, "Unknown");
    }

    public Rental(String rentalId, String customerId, String vehicleId, String rentalStartDate, String rentalEndDate, int numberOfRentalDays, int rentalEstimatedCost, int discount, int rentalAcutualCost, String rentalStatus) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.numberOfRentalDays = numberOfRentalDays;
        this.rentalEstimatedCost = rentalEstimatedCost;
        this.discount = discount;
        this.rentalActualCost = rentalAcutualCost;
        this.rentalStatus = rentalStatus;
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

    public int getRentalEstimatedCost() {
        return rentalEstimatedCost;
    }

    public void setRentalEstimatedCost(int rentalEstimatedCost) {
        this.rentalEstimatedCost = rentalEstimatedCost;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getRentalActualCost() {
        return rentalActualCost;
    }

    public void setRentalAcutualCost(int rentalAcutualCost) {
        this.rentalActualCost = rentalAcutualCost;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

}