package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This implementation makes it possible to read a list of data from a file.
 * Then do the count and write the result in a file.
 * 
 * @author Mamoudou BA
 *
 */
public class AnalyticsCounter implements ISymptomReader {
  private String filePath;
  private String outFile;

  /**
   * The constructor.
   * @param filePath a full or partial path to file with symptom strings in it, one per line
   * @param outFile a full or partial path to a file containing the symptoms count
   */
  public AnalyticsCounter(String filePath, String outFile) {
    this.filePath = filePath;
    this.outFile = outFile;

  }

  @Override
  public List<String> readSymptomDataFromFile() {
    List<String> symptomsList = new ArrayList<>();
    if (filePath != null) {
      try {
        // first get input
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();

        while (line != null) {
          symptomsList.add(line);
          System.out.println("symptom from file: " + line);

          line = reader.readLine(); // get another symptom

        }
        // close resources
        reader.close();

      } catch (FileNotFoundException e) {
        System.out.printf("The file %s does not exist: ", filePath.toString());
        // System.exit(1);
        return null;
      } catch (IOException e) {
        System.out.println("File reading problem: " + e.getMessage());
        // System.exit(1);
        return null;
      }

    }

    return symptomsList;

  }

  @Override
  public Map<String, Integer> symptomsCounter(List<String> symptomsList) {
    // I use TreeMap to order the symptom list by Keys in ascending Order
    TreeMap<String, Integer> counter = new TreeMap<>();
    // I check if the symptom exists in the file and we count its number of
    // occurrences
    for (String symptom : symptomsList) {
      Integer occurence = counter.getOrDefault(symptom, 0);
      counter.put(symptom, occurence + 1);
    }

    return counter;

  }

  @Override
  public boolean writeResultToFile(Map<String, Integer> counter) {
    try {
      // Write the symptom list to file "result.out"
      PrintWriter printWriter = new PrintWriter(outFile);
      printWriter.println("Result after sorting by keys in ascending order ");
      printWriter.println("######################################################################");
      for (Map.Entry<String, Integer> map : counter.entrySet()) {
        printWriter.println(map.getKey() + "=" + map.getValue());
      }
      // close resources
      printWriter.close();
      return true;
    } catch (IOException e) {
      System.out.println("File writing problem: " + e.getMessage());
      return false;
    }

  }

}
