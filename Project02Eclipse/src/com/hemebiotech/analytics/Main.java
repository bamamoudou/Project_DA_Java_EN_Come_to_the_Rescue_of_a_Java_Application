package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws Exception {
    AnalyticsCounter analyticsCounter = new AnalyticsCounter();
    List<String> symptomsList = analyticsCounter.readSymptomDataFromFile();
    Map<String, Integer> counter = analyticsCounter.symptomsCounter(symptomsList);
    analyticsCounter.writeResultToFile(counter);

  }

}
