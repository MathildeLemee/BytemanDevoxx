package fr.javafreelance.byteman;

import fr.javafreelance.model.Cache;
import fr.javafreelance.model.StatisticService;
import org.jboss.byteman.contrib.bmunit.BMNGRunner;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author : Mathilde Lemee
 */


public class DemoCustomHelper extends BMNGRunner {

  @Test
  @BMScript(value = "scripts/demo1.btm")
  public void play_with_byteman_location_with_script() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.update();
  }

  @Test
  @BMScript(value = "scripts/demo2.btm")
  public void play_with_byteman_location_with_script2() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.update();
  }


}
