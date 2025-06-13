package com.smartexpiry;

/**
 * Utility class providing donation suggestions based on item expiry.
 */
public class DonationAdvisor {
    /**
     * Determines if an item should be donated based on days to expiry.
     *
     * @param item the item to evaluate
     * @return true if item expires in one day, otherwise false
     */
    public static boolean shouldDonate(Item item) {
        return item != null && item.getDaysToExpiry() == 1;
    }

    /**
     * Provides a mock NGO suggestion for donating items.
     *
     * @return NGO contact suggestion string
     */
    public static String getNGOSuggestion() {
        return "Suggest donation to: Goonj Foundation, Contact: +91-9876543210";
    }
}
