package mr.iscae.location.model;

import java.util.List;

public class Reservation {
    private Long id;
    private Long clientId;
    private String vehicleCategory;
    private String startDateTime;
    private String endDateTime;
    private String departureAgency;
    private String returnAgency;
    private List<String> options; // GPS, babySeat, fullInsurance
        public enum Status { PENDING, VALIDATED, CANCELLED }
        private Status status;
        private String expectedStart;
        private String expectedEnd;
    private double cost;
    private Long vehicleId; // Assigned by manager

    public Reservation() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public String getVehicleCategory() { return vehicleCategory; }
    public void setVehicleCategory(String vehicleCategory) { this.vehicleCategory = vehicleCategory; }
    public String getStartDateTime() { return startDateTime; }
    public void setStartDateTime(String startDateTime) { this.startDateTime = startDateTime; }
    public String getEndDateTime() { return endDateTime; }
    public void setEndDateTime(String endDateTime) { this.endDateTime = endDateTime; }
    public String getDepartureAgency() { return departureAgency; }
    public void setDepartureAgency(String departureAgency) { this.departureAgency = departureAgency; }
    public String getReturnAgency() { return returnAgency; }
    public void setReturnAgency(String returnAgency) { this.returnAgency = returnAgency; }
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
        public Status getStatus() { return status; }
        public void setStatus(Status status) { this.status = status; }
        public String getExpectedStart() { return expectedStart; }
        public void setExpectedStart(String expectedStart) { this.expectedStart = expectedStart; }
        public String getExpectedEnd() { return expectedEnd; }
        public void setExpectedEnd(String expectedEnd) { this.expectedEnd = expectedEnd; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }
}
