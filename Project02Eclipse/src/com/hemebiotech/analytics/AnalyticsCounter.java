package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;

public class AnalyticsCounter {

  public static void main(String args[]) throws Exception {
    // first get input
    BufferedReader reader = new BufferedReader(new FileReader("symptoms.txt"));
    String line = reader.readLine();
    TreeMap<String, Integer> counter = new TreeMap<>();

    while (line != null) {

      System.out.println("symptom from file: " + line);
      if (counter.containsKey(line)) {
        counter.put(line, counter.get(line) + 1);
      } else {
        counter.put(line, 1);
      }

      line = reader.readLine(); // get another symptom
    }
    reader.close();
    counter.forEach((key, value) -> System.out.println(key + ":" + value));

//		// next generate output
//		FileWriter writer = new FileWriter ("result.out");
//		writer.write("headache: " + headacheCount + "\n");
//		writer.write("rash: " + rashCount + "\n");
//		writer.write("dialated pupils: " + pupilCount + "\n");
//		writer.close();
  }
}
