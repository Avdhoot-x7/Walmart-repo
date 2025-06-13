package com.smartexpiry;

public class Item {
    private final String name;
    private final java.time.LocalDate expiryDate;
    private final int stock;
    private final int salesPerDay;
    private final String location;

    public Item(String name, java.time.LocalDate expiryDate, int stock, int salesPerDay, String location) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.stock = stock;
        this.salesPerDay = salesPerDay;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public java.time.LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getStock() {
        return stock;
    }

    public int getSalesPerDay() {
        return salesPerDay;
    }

    public String getLocation() {
        return location;
    }

    public long getDaysToExpiry() {
        java.time.LocalDate today = java.time.LocalDate.now();
        return java.time.temporal.ChronoUnit.DAYS.between(today, expiryDate);
    }
}
