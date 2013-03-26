package fr.javafreelance.byteman;

import fr.javafreelance.model.Cache;
import fr.javafreelance.model.StatisticService;
import org.jboss.byteman.contrib.bmunit.BMNGRunner;
import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMRules;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author : Mathilde Lemee
 */


public class DemoHelper extends BMNGRunner {

  Cache cache;

  /**
   * Default logger no more space error
   */
  @Test
  public void when_disk_crash_on_log_then_programm_can_handle_it() throws InterruptedException {
    cache = new Cache();
    for (int i = 0; i < 10; i++) {
      cache.put("a" + i, "b");
    }
  }



  @Test
  @BMRules(rules = { @BMRule(name = "create countDown",
      targetClass = "Cache",
      targetMethod = "<init>",
      targetLocation = "AT ENTRY",
      action = "createCountDown(\"logger\",5)")
      ,
      @BMRule(name = "crash disk",
          targetClass = "Cache"
          , targetMethod = "put",
          targetLocation = "AT ENTRY",
          condition = "countDown(\"logger\") ",
          action = "throw new RuntimeException(\"boum\")")
  })
  public void when_disk_crash_then_programm_can_handle_it_with_logging_again() {
    cache = new Cache();
    int i = 0;
    while (true) {
      System.out.println("Setting Key in cache ... : " + i);
      cache.put("a" + i++, "b");
    }
  }


  @Test
  @BMRules(rules = {
      @BMRule(name = "play with expression 7",
          targetClass = "Cache",
          targetMethod = "put",
          targetLocation = "AT ENTRY",
          condition = "$1.equals(\"a5\")",
          action = "flag(\"toto\");"),
      @BMRule(name = "play with expression 8",
          targetClass = "Cache",
          targetMethod = "put",
          condition = "flagged(\"toto\") ",
          action = "throw new RuntimeException(\"Boum\");")
  })
  public void play_with_byteman_build_caller_flag() throws IOException {
    cache = new Cache();
    int i = 0;
    while (true) {
      System.out.println("Setting Key in cache ... : " + i);
      cache.put("a" + i++, "b");
    }
  }

  @Test
  @BMRules(rules = {
      @BMRule(name = "play with expression 7",
          targetClass = "Cache",
          targetMethod = "<init>",
          targetLocation = "AT ENTRY",
          action = "createTimer(\"toto\");"),
      @BMRule(name = "play with expression 8",
          targetClass = "Cache",
          targetMethod = "put",
          condition = "getElapsedTimeFromTimer(\"toto\") > 1000 ",
          action = "throw new RuntimeException(\"Boum\");")
  })
  public void play_with_byteman_build_caller_timer() throws IOException {
    cache = new Cache();
    int i = 0;
    while (true) {
      System.out.println("Setting Key in cache ... : " + i);
      cache.put("a" + i++, "b");
    }
  }




  @Test
  @BMRule(name = "play with expression",
      targetClass = "Map",
      isInterface = true,
      targetMethod = "put",
      targetLocation = "AT ENTRY",
      action = "traceStack()")
  public void play_with_byteman_build_caller_trace() throws IOException {
    cache = new Cache();
    cache.put("a1", "b");
  }


  @Test
  @BMRule(name = "play with expression",
      targetClass = "Map",
      isInterface = true,
      targetMethod = "clear",
      targetLocation = "AT ENTRY",
      action = "traceStack()")
  public void play_with_byteman_build_caller_array_2() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println("init done");
    updateStatistic.reset();
  }


  /**
   * ********* Demo5 *************
   */


  @Test
  @BMRule(name = "play with expression 2",
      targetClass = "Map",
      isInterface = true,
      targetMethod = "clear",
      targetLocation = "AT ENTRY",
      condition = "callerEquals(\"StatisticService.reset\",true)",
      action = "throw new RuntimeException(\"Boum : \");")
  public void play_with_byteman_build_caller_array() throws IOException {
    StatisticService updateStatistic = new StatisticService();
    updateStatistic.init();
    System.out.println("init done");
    updateStatistic.reset();
  }

//  @Test
//  @BMRule(name = "play with expression 6",
//      targetClass = "Map",
//      isInterface = true,
//      targetMethod = "clear",
//      targetLocation = "AT ENTRY",
//      action = "killJVM()")
//  public void play_with_byteman_build_caller_kill() throws IOException {
//    StatisticService updateStatistic = new StatisticService();
//    updateStatistic.init();
//    System.out.println("init done");
//    updateStatistic.reset();
//  }


}
