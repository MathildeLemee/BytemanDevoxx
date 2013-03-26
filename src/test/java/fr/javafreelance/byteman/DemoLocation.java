package fr.javafreelance.byteman;

import fr.javafreelance.model.StatisticService;
import org.jboss.byteman.contrib.bmunit.BMNGRunner;
import org.jboss.byteman.contrib.bmunit.BMRule;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author : Mathilde Lemee
 */


public class DemoLocation extends BMNGRunner {


  @Test
  public void play_with_byteman_location() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.update();
  }

  /**
   * ********* Demo1 *************
   */
  @Test
  @BMRule(name = "demo1",
      targetClass = "StatisticService",
      targetMethod = "update",
      action = "throw new RuntimeException(\"Boum  \");")
  public void play_with_byteman_expression_entry() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    updateStatistic.update();
  }


  /**
   * ********* Demo2 *************
   */
  @Test
  @BMRule(name = "demo2",
      targetClass = "StatisticService",
      targetLocation = "AT EXIT",
      targetMethod = "update",
      action = "throw new RuntimeException(\"Boum  \");")
  public void play_with_byteman_expression_exit() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    updateStatistic.update();
  }


  /**
   * ********* Demo3 *************
   */
  @Test
  @BMRule(name = "demo3",
      targetClass = "StatisticService",
      targetMethod = "update",
      targetLocation = "AT READ counter",
      action = "throw new RuntimeException(\"Boum  \");")
  public void play_with_byteman_expression_counter() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    updateStatistic.update();
  }


  /**
   * ********* Demo4 *************
   */
  @Test
  @BMRule(name = "demo4",
      targetClass = "StatisticService",
      targetMethod = "update",
      targetLocation = "AT WRITE $i",
      action = "throw new RuntimeException(\"Boum  \");")
  public void play_with_byteman_expression_write() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    updateStatistic.update();
  }


  /**
   * ********* Demo5 *************
   */
  @Test
  @BMRule(name = "demo5",
      targetClass = "StatisticService",
      targetMethod = "update",
      targetLocation = "AT CALL java.util.Map.put",
      action = "throw new RuntimeException(\"Boum  \");")
  public void play_with_byteman_method_call() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    updateStatistic.update();
  }


}


