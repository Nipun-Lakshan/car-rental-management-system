public class Customer {

    private String customerId;
    private String customerName;
    private String contactNo;
    private String licenseNumber;

    public Customer() {
        this("Unknown");
    }

    public Customer(String customerId) {
        this(customerId, "Unknown");
    }

    public Customer(String customerId, String customerName) {
        this(customerId, customerName, "Unknown");
    }

    public Customer(String customerId, String customerName, String contactNo) {
        this(customerId, customerName, contactNo, "Unknown");
    }

    public Customer(String customerId, String customerName, String contactNo, String licenseNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactNo = contactNo;
        this.licenseNumber = licenseNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

}