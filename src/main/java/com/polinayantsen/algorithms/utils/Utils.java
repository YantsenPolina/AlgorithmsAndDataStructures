package com.polinayantsen.algorithms.utils;

import sun.misc.ClassLoaderUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

    public static List<Integer> getListFromFile(String fileName) {
        ArrayList<Integer> result = new ArrayList<>();
        ClassLoader classLoader = Utils.class.getClassLoader();
        String data;
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                LOGGER.log(Level.SEVERE, "File with array is not found.");
                return result;
            }
            data = readFromInputStream(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while closing resource: " + e.getMessage());
            return result;
        }
        String[] numbers = data.split("\\s+");
        for (String number : numbers) {
            try {
                result.add(Integer.valueOf(number));
            } catch (NumberFormatException e) {
                LOGGER.log(Level.SEVERE, "Cannot parse number <" + number + "> to Integer. File must contain a list of numbers separated by spaces.");
            }
        }
        LOGGER.info("Array is read from file: " + result);
        return result;
    }

    private static String readFromInputStream(InputStream inputStream) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while reading file: ", e.getMessage());
        }
        return result.toString();
    }
}
