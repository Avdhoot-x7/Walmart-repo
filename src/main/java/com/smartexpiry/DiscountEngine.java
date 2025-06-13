package com.smartexpiry;

public class DiscountEngine {
    public static int getSuggestedDiscount(String riskLevel) {
        if (riskLevel == null) {
            throw new IllegalArgumentException("riskLevel cannot be null");
        }
        switch (riskLevel.toUpperCase()) {
            case "HIGH":
                return 30;
            case "MEDIUM":
                return 15;
            case "LOW":
                return 0;
            default:
                throw new IllegalArgumentException("Invalid risk level: " + riskLevel);
        }
    }
}
