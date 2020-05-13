package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map;
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
    
    
    File file = new File("result.out");
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    PrintWriter printWriter = new PrintWriter(fileOutputStream);
    printWriter.println("############################################################################### ");
    printWriter.println("Result after sorting by keys in ascending order ");
    printWriter.println("############################################################################### ");
    for (Map.Entry<String, Integer> map : counter.entrySet()) {
      printWriter.println(map.getKey() + "=" + map.getValue());
    }
    // close resources
    printWriter.close();
    fileOutputStream.close();


  }
}
