package mr.iscae.location.model;

public class Invoice {
    private Long id;
    private Long contractId;
    private double totalCost;
    private String breakdown;
    private double delayPenalty;
    private double fuelPenalty;
    private double damagePenalty;
    private double optionsCost;
    private double rentalDays;
    private double dailyRate;
    private Long clientId;

    public Invoice() {}

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getContractId() { return contractId; }
    public void setContractId(Long contractId) { this.contractId = contractId; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public String getBreakdown() { return breakdown; }
    public void setBreakdown(String breakdown) { this.breakdown = breakdown; }
    public double getDelayPenalty() { return delayPenalty; }
    public void setDelayPenalty(double delayPenalty) { this.delayPenalty = delayPenalty; }
    public double getFuelPenalty() { return fuelPenalty; }
    public void setFuelPenalty(double fuelPenalty) { this.fuelPenalty = fuelPenalty; }
    public double getDamagePenalty() { return damagePenalty; }
    public void setDamagePenalty(double damagePenalty) { this.damagePenalty = damagePenalty; }
    public double getOptionsCost() { return optionsCost; }
    public void setOptionsCost(double optionsCost) { this.optionsCost = optionsCost; }
    public double getRentalDays() { return rentalDays; }
    public void setRentalDays(double rentalDays) { this.rentalDays = rentalDays; }
    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
}
