package fr.javafreelance.byteman.extra;

import fr.javafreelance.model.StatisticService;
import org.jboss.byteman.contrib.bmunit.BMNGRunner;
import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMRules;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author : Mathilde Lemee
 */


public class Demo extends BMNGRunner {

  @Test
  @BMRule(name = "default",
  targetClass = "StatisticService",
  targetMethod = "update",
  targetLocation = "AT CALL Map.put",
  action = "throw new RuntimeException(\"Boum : \")")
  public void play_with_byteman_location() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.update();
  }



  @Test
  @BMRule(
      name = "deuxieme",
      targetClass = "StatisticService",
      targetMethod = "get",
      targetLocation = "AT ENTRY",
      action = "$1=2"
  )
  public void play_with_byteman_location2() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println(updateStatistic.get(5));
  }


  @Test
  @BMRule(name = "play with expression",
      targetClass = "StatisticService",
      targetMethod = "get",
      targetLocation = "AT ENTRY",
      condition = "$1 == 5",
      action = "throw new RuntimeException(\"Boum : \");")

  public void play_with_byteman_expression() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println(updateStatistic.get(5));
  }

  @Test
  @BMRule(name = "play with expression",
      targetClass = "StatisticService",
      targetMethod = "getAll",
      targetLocation = "AT EXIT",
      action = "throw new RuntimeException(\"Boum : \"+$!.get(1))")
  public void play_with_byteman_expression_array() throws IOException {
    StatisticService updateStatistic = new StatisticService();

    updateStatistic.init();
    updateStatistic.getAll(1, 2, 3, 5);
  }

  @Test          //traceStack
  @BMRule(name = "play with expression",
      targetClass = "Map",
      isInterface = true,
      targetMethod = "clear",
      targetLocation = "AT ENTRY",
      condition = "callerEquals(\"StatisticService.reset\",true)",
      action = "throw new RuntimeException(\"Boum\");")
  public void play_with_byteman_build_caller_array() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println("init done");
    updateStatistic.reset();
  }


  @Test
  @BMRule(name = "play with expression",
      targetClass = "Map",
      isInterface = true,
      targetMethod = "clear",
      targetLocation = "AT ENTRY",
      condition = "callerEquals(\"StatisticService.reset\",true)",
      action = "throw new RuntimeException(\"Boum\");")
  public void play_with_byteman_caller() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println("init done");
    updateStatistic.reset();
  }

  /**
   * State Management"*
   */

  @Test
  @BMRules(rules = {
      @BMRule(name = "play with expression",
          targetClass = "StatisticService",
          targetMethod = "init",
          targetLocation = "AT ENTRY",
          action = "flag(\"toto\");"),
      @BMRule(name = "play with expression 2",
          targetClass = "StatisticService",
          targetMethod = "reset",
          targetLocation = "AT ENTRY",
          condition = "flagged(\"toto\")",
          action = "throw new RuntimeException(\"Boum\");")
  }
  )
  public void play_with_byteman_flag() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.reset();
    updateStatistic.init();
    System.out.println("init done");
    updateStatistic.reset();
  }

  @Test
  @BMRules(rules = {
      @BMRule(name = "play with expression",
          targetClass = "StatisticService",
          targetMethod = "init",
          targetLocation = "AT ENTRY",
          action = "createCountDown(\"toto\",2);"),
      @BMRule(name = "play with expression 2",
          targetClass = "StatisticService",
          targetMethod = "update",
          targetLocation = "AT READ $i",
          condition = "countDown(\"toto\") ",
          action = "throw new RuntimeException(\"Boum\");")
  }
  )
  public void play_with_byteman_flag_countdown() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    updateStatistic.update();
  }


  /**
   * Thread Management"*
   */

  /**
   * killThread()
   */

  @Test
  @BMRule(name = "play with expression",
      targetClass = "Map",
      isInterface = true,
      targetMethod = "clear",
      targetLocation = "AT ENTRY",
      action = "killJVM()")
  public void play_with_byteman_build_caller_kill() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println("init done");
    updateStatistic.reset();
  }


  @Test
  @BMRules(rules = {
      @BMRule(name = "play with expression",
          targetClass = "StatisticService",
          targetMethod = "<init>",
          targetLocation = "AT ENTRY",
          action = "createTimer(\"toto\");"),
      @BMRule(name = "play with expression 2",
          targetClass = "StatisticService",
          targetMethod = "update",
          targetLocation = "AT READ $i",
          condition = "getElapsedTimeFromTimer(\"toto\") > 1000 ",
          action = "throw new RuntimeException(\"Boum\");")
  })
  public void play_with_byteman_build_caller_timer() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    while (true) {
      updateStatistic.update();
    }
  }


  @Test
  @BMScript(value = "scripts/demo1.btm")
  public void play_with_byteman_location_with_script() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.update();
  }


  /**
   * Thread manipulation
   */


}

