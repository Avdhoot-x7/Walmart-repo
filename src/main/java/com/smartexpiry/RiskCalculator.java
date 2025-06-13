package com.smartexpiry;

public class RiskCalculator {
    public static String calculateRisk(Item item) {
        long daysLeft = item.getDaysToExpiry();
        int stock = item.getStock();
        int salesPerDay = item.getSalesPerDay();
        if (daysLeft <= 3 && stock > salesPerDay * daysLeft) {
            return "HIGH";
        } else if (daysLeft <= 5) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }
}
