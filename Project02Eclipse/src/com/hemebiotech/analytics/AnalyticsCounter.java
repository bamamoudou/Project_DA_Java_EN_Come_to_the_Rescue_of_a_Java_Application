package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
  public AnalyticsCounter() {

  }

  public List<String> readSymptomDataFromFile() throws IOException {
    // first get input
    BufferedReader reader = new BufferedReader(new FileReader("symptoms.txt"));
    List<String> symptomsList = new ArrayList<>();
    String line = reader.readLine();
    while (line != null) {
      symptomsList.add(line);
      System.out.println("symptom from file: " + line);
      line = reader.readLine(); // get another symptom
    }
    // close resources
    reader.close();
    return symptomsList;
  }

  public Map<String, Integer> symptomsCounter(List<String> symptomsList) throws Exception {
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

  public void writeResultToFile(Map<String, Integer> counter) throws IOException {

    // TreeMap<String, Integer> counter = readSymptomDataFromFile();
    // Write the symptom list to file "result.out"
    File file = new File("result.out");
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    PrintWriter printWriter = new PrintWriter(fileOutputStream);
    printWriter.println("Result after sorting by keys in ascending order ");
    printWriter.println("###################################################################### ");
    for (Map.Entry<String, Integer> map : counter.entrySet()) {
      printWriter.println(map.getKey() + "=" + map.getValue());
    }
    // close resources
    printWriter.close();
    fileOutputStream.close();
  }

}
