package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

/**
 * The program will read a list of symptoms from a file and count these
 * symptoms, then print the result of the count by sorting it into another file.
 * 
 * @author Mamoudou BA
 * @version 0.0.4
 *
 */
public class Main {
  /**
   * This is the main method which makes use of AnalyticsCounter class.
   * 
   * @param args is an array that contains source and destination files
   * @throws Exception make exception
   */
  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.out.println("two arguments needed: source and destination files");
      System.exit(1);
    }
    ISymptomReader analyticsCounter = new AnalyticsCounter(args[0], args[1]);
    List<String> symptomsList = analyticsCounter.readSymptomDataFromFile();
    if (symptomsList == null) {
      System.out.println("can't read file " + args[0] + ", exiting now");
      System.exit(1);
    } else {
      Map<String, Integer> counter = analyticsCounter.symptomsCounter(symptomsList);
      boolean result = analyticsCounter.writeResultToFile(counter);
      if (result) {
        System.out.println("ok");

      } else {
        System.out.println("ko");

      }

    }

  }

}
