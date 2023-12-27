package com.techelevator.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Stocker {


        public String[] getStock() { // used in Program
            String inputfile = "vendingmachine.csv";
            String[] stock;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputfile));
                List<String> stockList = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    stockList.add(line);
                }
                stock = stockList.toArray(new String[0]);
                reader.close();
                return stock;
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                return new String[0];
            }
        }
    }

