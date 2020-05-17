package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter implements ISymptomReader {
  public AnalyticsCounter() {

  }

  @Override
  public List<String> readSymptomDataFromFile() {
    // first get input
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader("symptoms.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    List<String> symptomsList = new ArrayList<>();
    String line = null;
    try {
      line = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (line != null) {
      symptomsList.add(line);
      System.out.println("symptom from file: " + line);
      try {
        line = reader.readLine(); // get another symptom
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    // close resources
    try {
      reader.close();
    } catch (IOException e) {

      e.printStackTrace();
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
  public void writeResultToFile(Map<String, Integer> counter) {
    // TreeMap<String, Integer> counter = readSymptomDataFromFile();
    // Write the symptom list to file "result.out"
    File file = new File("result.out");
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    PrintWriter printWriter = new PrintWriter(fileOutputStream);
    printWriter.println("Result after sorting by keys in ascending order ");
    printWriter.println("###################################################################### ");
    for (Map.Entry<String, Integer> map : counter.entrySet()) {
      printWriter.println(map.getKey() + "=" + map.getValue());
    }
    // close resources
    printWriter.close();
    try {
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
