package fr.javafreelance.byteman.extra;

import fr.javafreelance.model.Cache;
import org.jboss.byteman.contrib.bmunit.BMNGRunner;
import org.jboss.byteman.contrib.bmunit.BMRule;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author : Mathilde Lemee
 */


public class LanException extends BMNGRunner {

  Cache cache;

  /**
   * First example :
   * Basic io exception when connect on a url.
   * Will return socketTimeoutException
   *
   * @throws IOException
   */
  @Test
  @BMRule(name = "lan exception",
      targetClass = "PlainSocketImpl",
      targetMethod = "connect",
      targetLocation = "AT ENTRY",
      action = "throw new java.net.SocketTimeoutException(\"Url Not Found\");")
  public void when_disk_crash_then_programm_can_handle_it() throws IOException {
    URL url = new URL("https://itunes.apple.com/lookup?id=284910350");
    URLConnection connection = url.openConnection();
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.connect();

  }

  /**
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   * p
   */
  @Test
  @BMRule(name = "lan exception",
      targetClass = "PlainSocketImpl",
      targetMethod = "connect",
      targetLocation = "AT ENTRY",
      action = "throw new java.net.SocketTimeoutException(\"Url Not Found\");")
  public void when_disk_crash_then_programm_can_handle_it_2() throws IOException {
    URL url = new URL("https://itunes.apple.com/lookup?id=284910350");
    URLConnection connection = url.openConnection();
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.connect();

  }
}
