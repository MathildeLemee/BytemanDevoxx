package fr.javafreelance.model;

/**
 * @author : Mathilde Lemee
 */
public class Statistic {
  private final int id;
  private double value;

  public Statistic(final int i) {
    this.id = i;
  }

  @Override
  public String toString() {
    return "Statistic : id " + id;
  }
}
