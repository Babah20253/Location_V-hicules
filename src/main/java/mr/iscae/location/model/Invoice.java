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
}
