package fr.javafreelance.byteman;

import fr.javafreelance.model.StatisticService;
import org.jboss.byteman.contrib.bmunit.BMNGRunner;
import org.jboss.byteman.contrib.bmunit.BMRule;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author : Mathilde Lemee
 */


public class DemoEnvironnement extends BMNGRunner {


  @Test
  @BMRule(
      name = "deuxieme",
      targetClass = "StatisticService",
      targetMethod = "get",
      targetLocation = "AT ENTRY",
      action = "throw new RuntimeException(\"Boum : \");"
  )
  public void play_with_byteman_location2() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println(updateStatistic.get(4));
  }


  /**
   * ********* Demo1 *************
   */
  @Test
  @BMRule(
      name = "deuxieme",
      targetClass = "StatisticService",
      targetMethod = "get",
      targetLocation = "AT ENTRY",
      action = "return new Statistic(2)"
  )
  public void play_with_byteman_environnement_return() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println(updateStatistic.get(4));
  }

  /**
   * ********* Demo2 *************
   */
  @Test
  @BMRule(
      name = "deuxieme",
      targetClass = "StatisticService",
      targetMethod = "get",
      targetLocation = "AT ENTRY",
      condition = "$1==2",
      action = "throw new RuntimeException(\"Boum : \");"
  )
  public void play_with_byteman_environnement_condition() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println(updateStatistic.get(4));
  }


  /**
   * ********* Demo3 *************
   */
  @Test
  @BMRule(
      name = "deuxieme",
      targetClass = "StatisticService",
      targetMethod = "get",
      targetLocation = "AT ENTRY",
      action = "$this.reset();"
  )
  public void play_with_byteman_environnement_this() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println(updateStatistic.get(3));
  }


  /**
   * ********* Demo4 *************
   */
  @Test
  @BMRule(name = "play with expression",
      targetClass = "StatisticService",
      targetMethod = "getAll",
      targetLocation = "AT EXIT",
      action = "throw new RuntimeException(\"Boum : \"+$!);")
  public void play_with_byteman_expression_array() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    updateStatistic.getAll(1, 2, 3, 5);
  }

  /**
   * ********* Demo5 *************
   */
  @Test
  @BMRule(name = "play with expression",
      targetClass = "StatisticService",
      targetMethod = "getAll",
      targetLocation = "AT EXIT",
      action = "throw new RuntimeException(\"Boum : \"+$!.get(1));")
  public void play_with_byteman_expression_array_1() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    updateStatistic.getAll(1, 2, 3, 5);
  }


}

