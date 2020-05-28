package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

/**
 * Anything that will read symptom data from a source The important part is, the
 * return value from the operation, which is a list of strings, that may contain
 * many duplications. The implementation order the list.
 * 
 */
public interface ISymptomReader {
  /**
   * If no data is available, return an empty List.
   * 
   * @return a raw listing of all Symptoms obtained from a data source, duplicates
   *         are possible/probable.
   */
  public List<String> readSymptomDataFromFile();

  /**
   * Count the symptoms read from a file and order the result.
   * 
   * @param symptomsList contains the list of symptoms.
   * @return a list of symptoms and its number of occurrences.
   */
  public Map<String, Integer> symptomsCounter(List<String> symptomsList);

  /**
   * Write the count of the symptoms in a file in alphabetical order.
   * 
   * @param counter contains list of symptoms and its occurrences.
   * @return true if the writing of the result goes well otherwise an error
   *         message
   */
  public boolean writeResultToFile(Map<String, Integer> counter);

}
