package utils;

import java.io.*;
import java.util.*;

public class TestDataUtil {
    public static List<String[]> readCustomerData() {
        List<String[]> data = new ArrayList<>();
        String filePath = "main/resources/testData/customerData.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header row
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
