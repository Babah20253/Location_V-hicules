package mr.iscae.location.model;

public class Contract {
    private Long id;
    private Long reservationId;
    private Long vehicleId;
    private Long managerId;
    private String departureTime;
    private String expectedReturn;
    private String actualReturn;
    private int departureMileage;
    private int returnMileage;
        private double fuelStart;
        private double fuelReturn;
    private String departureState;
    private String returnState;
    private double penalties;
        private int damagesCount;

    public Contract() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getReservationId() { return reservationId; }
    public void setReservationId(Long reservationId) { this.reservationId = reservationId; }
    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }
    public Long getManagerId() { return managerId; }
    public void setManagerId(Long managerId) { this.managerId = managerId; }
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }
    public String getExpectedReturn() { return expectedReturn; }
    public void setExpectedReturn(String expectedReturn) { this.expectedReturn = expectedReturn; }
    public String getActualReturn() { return actualReturn; }
    public void setActualReturn(String actualReturn) { this.actualReturn = actualReturn; }
    public int getDepartureMileage() { return departureMileage; }
    public void setDepartureMileage(int departureMileage) { this.departureMileage = departureMileage; }
    public int getReturnMileage() { return returnMileage; }
    public void setReturnMileage(int returnMileage) { this.returnMileage = returnMileage; }
        public double getFuelStart() { return fuelStart; }
        public void setFuelStart(double fuelStart) { this.fuelStart = fuelStart; }
        public double getFuelReturn() { return fuelReturn; }
        public void setFuelReturn(double fuelReturn) { this.fuelReturn = fuelReturn; }
    public String getDepartureState() { return departureState; }
    public void setDepartureState(String departureState) { this.departureState = departureState; }
    public String getReturnState() { return returnState; }
    public void setReturnState(String returnState) { this.returnState = returnState; }
    public double getPenalties() { return penalties; }
    public void setPenalties(double penalties) { this.penalties = penalties; }
        public int getDamagesCount() { return damagesCount; }
        public void setDamagesCount(int damagesCount) { this.damagesCount = damagesCount; }
}
