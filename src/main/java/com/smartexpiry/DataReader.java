package com.smartexpiry;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DataReader {
    public List<Item> readItems(String filePath) throws IOException {
        Path path = Path.of(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader.lines()
                    .filter(line -> !line.trim().isEmpty())
                    .map(this::parseLine)
                    .collect(java.util.stream.Collectors.toList());
        }
    }

    private Item parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 5) {
            throw new IllegalArgumentException("Invalid line: " + line);
        }
        String name = parts[0];
        java.time.LocalDate expiryDate = java.time.LocalDate.parse(parts[1]);
        int stock = Integer.parseInt(parts[2]);
        int salesPerDay = Integer.parseInt(parts[3]);
        String location = parts[4];
        return new Item(name, expiryDate, stock, salesPerDay, location);
    }
}
