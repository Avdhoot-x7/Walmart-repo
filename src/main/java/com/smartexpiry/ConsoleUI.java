package com.smartexpiry;

import java.util.List;

/**
 * Utility class for console display of {@link Item} details.
 */
public class ConsoleUI {

    /**
     * Displays a table of item information to the console.
     *
     * @param items list of items to display
     */
    public static void displayItems(List<Item> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("No items to display.");
            return;
        }

        String headerFormat = "%-20s %-15s %-12s %-17s%n";
        String rowFormat = "%-20s %-15d %-12s %-17d%n";

        System.out.printf(headerFormat, "Name", "Days to Expiry", "Risk Level", "Suggested Discount");
        System.out.println("--------------------------------------------------------------------------");

        for (Item item : items) {
            String risk = RiskCalculator.calculateRisk(item);
            int discount = DiscountEngine.getSuggestedDiscount(risk);
            System.out.printf(rowFormat, item.getName(), item.getDaysToExpiry(), risk, discount);
        }
    }
}
