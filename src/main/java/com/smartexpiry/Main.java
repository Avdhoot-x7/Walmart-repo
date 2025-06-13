package com.smartexpiry;

import java.io.IOException;
import java.util.List;

/**
 * Entry point for the SmartExpiry application.
 */
public class Main {
    /**
     * Reads inventory items from a CSV file and displays them via the console UI.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        DataReader reader = new DataReader();
        try {
            List<Item> items = reader.readItems("data/inventory_data.csv");
            ConsoleUI.displayItems(items);
        } catch (IOException e) {
            System.err.println("Error reading inventory data: " + e.getMessage());
        }
    }
}
