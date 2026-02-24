package mr.iscae.location.model;

public class Vehicle {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String registrationNumber;
    public enum Category { ECONOMIQUE, COMPACT, SUV, LUXE, UTILITAIRE }
    private Category category;
    private int seats;
    private String fuelType;
    private int mileage;
    private double dailyRate;
    public enum Status { AVAILABLE, RENTED, MAINTENANCE }
    private Status status;
    private String agency;

    public Vehicle() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }
    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public int getMileage() { return mileage; }
    public void setMileage(int mileage) { this.mileage = mileage; }
    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getAgency() { return agency; }
    public void setAgency(String agency) { this.agency = agency; }
}
