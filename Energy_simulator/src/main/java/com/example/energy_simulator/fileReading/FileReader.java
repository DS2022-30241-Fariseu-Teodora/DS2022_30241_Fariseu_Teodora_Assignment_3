package com.example.energy_simulator.fileReading;

import java.util.Scanner;

public class FileReader {
    protected Scanner scanner;
    public FileReader() {
        ClassLoader classLoader = getClass().getClassLoader();
        scanner = new Scanner(classLoader.getResourceAsStream("sensor.csv"));
    }
    public Float readFile() {
        scanner.useDelimiter("");
        if(!scanner.hasNextLine()) {
            scanner.close();
            return null;
        }
        return Float.parseFloat(scanner.nextLine());
    }
}
