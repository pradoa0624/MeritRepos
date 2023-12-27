package com.techelevator.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Auditor {
        public static void Audit(String message) {

                try {
                    PrintWriter writer = new PrintWriter(new FileOutputStream("Log.txt", true));
                    writer.println(message);
                    writer.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

        }

     }



