package mr.iscae.location.util;

import java.util.HashMap;
import java.util.Map;
import mr.iscae.location.model.User;
import mr.iscae.location.model.Vehicle;
import mr.iscae.location.model.Reservation;
import mr.iscae.location.model.Contract;
import mr.iscae.location.model.Invoice;

public class DatabaseMemory {
    public static Map<Long, User> users = new HashMap<>();
    public static Map<Long, Vehicle> vehicles = new HashMap<>();
    public static Map<Long, Reservation> reservations = new HashMap<>();
    public static Map<Long, Contract> contracts = new HashMap<>();
    public static Map<Long, Invoice> invoices = new HashMap<>();

    // Constants
    public static final double FUEL_UNIT_PRICE = 10.0;
    public static final double DAMAGE_UNIT_PRICE = 50.0;
    public static final double GPS_PRICE = 5.0;
    public static final double BABY_SEAT_PRICE = 3.0;
    public static final double FULL_INSURANCE_PRICE = 15.0;

    // Utility methods
    public static boolean datesOverlap(String startA, String endA, String startB, String endB) {
        java.time.LocalDateTime sa = java.time.LocalDateTime.parse(startA);
        java.time.LocalDateTime ea = java.time.LocalDateTime.parse(endA);
        java.time.LocalDateTime sb = java.time.LocalDateTime.parse(startB);
        java.time.LocalDateTime eb = java.time.LocalDateTime.parse(endB);
        return sa.isBefore(eb) && ea.isAfter(sb);
    }
    public static long hoursBetween(String start, String end) {
        java.time.LocalDateTime s = java.time.LocalDateTime.parse(start);
        java.time.LocalDateTime e = java.time.LocalDateTime.parse(end);
        return java.time.Duration.between(s, e).toHours();
    }
    public static long daysCeil(String start, String end) {
        java.time.LocalDateTime s = java.time.LocalDateTime.parse(start);
        java.time.LocalDateTime e = java.time.LocalDateTime.parse(end);
        long hours = java.time.Duration.between(s, e).toHours();
        return Math.max(1, (long)Math.ceil(hours / 24.0));
    }
    public static double optionsCost(java.util.List<String> options, long rentalDays) {
        double total = 0;
        if (options == null) return 0;
        for (String opt : options) {
            if (opt.equalsIgnoreCase("GPS")) total += GPS_PRICE * rentalDays;
            if (opt.equalsIgnoreCase("babySeat")) total += BABY_SEAT_PRICE * rentalDays;
            if (opt.equalsIgnoreCase("fullInsurance")) total += FULL_INSURANCE_PRICE * rentalDays;
        }
        return total;
    }
    public static int yearsBetween(String dateStr) {
        java.time.LocalDate d = java.time.LocalDate.parse(dateStr);
        return java.time.Period.between(d, java.time.LocalDate.now()).getYears();
    }
    public static int ageFromBirthDate(String birthDate) {
        java.time.LocalDate d = java.time.LocalDate.parse(birthDate);
        return java.time.Period.between(d, java.time.LocalDate.now()).getYears();
    }
}
