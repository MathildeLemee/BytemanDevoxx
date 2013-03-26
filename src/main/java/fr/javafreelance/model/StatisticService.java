package fr.javafreelance.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Mathilde Lemee
 */
public class StatisticService {
  private Map<Integer, Statistic> statistics = new HashMap<Integer, Statistic>();
  private int counter;

  public void init() {
    statistics.clear();
    counter=0;
    for (int i = 0; i < 5; i++) {
      statistics.put(i, new Statistic(i));
    }
  }

  public void update() {
    System.out.println("Entry Update");
    for (int i = 0; i < 5; i++) {
      System.out.println("Before put " + i);
      statistics.put(i, new Statistic(i));
      System.out.println("Before counter ++");
      counter++;
    }
    System.out.println("Exit Update");
  }

  public Statistic get(int i) {
    return statistics.get(i);
  }


  public List<Statistic> getAll(int... i) {
    List<Statistic> statisticsSubset = new ArrayList<Statistic>();
    for (int key : i) {
      System.out.println(key);
      statisticsSubset.add(statistics.get(key));
    }
    return statisticsSubset;
  }

  public void reset() {
    System.out.println("RESET");
    statistics.clear();
    counter = 0;
  }



}
