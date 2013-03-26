package fr.javafreelance.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author : Mathilde Lemee
 */
public class Cache implements ConcurrentMap {
  ConcurrentHashMap internalMap = new ConcurrentHashMap();
  final Logger logger = LoggerFactory.getLogger(Cache.class);
  private String name;

  @Override
  public int size() {
    return internalMap.size();
  }

  @Override
  public boolean isEmpty() {
    return internalMap.isEmpty();
  }

  @Override
  public boolean containsKey(final Object o) {
    return internalMap.containsKey(o);
  }

  @Override
  public boolean containsValue(final Object o) {
    return internalMap.containsValue(o);
  }

  @Override
  public Object get(final Object o) {
    return internalMap.get(o);
  }

  @Override
  public Object put(final Object o, final Object o2) {
    logger.info("put : key {} value {}", o, o2);
    return internalMap.put(o, o2);
  }

  @Override
  public Object remove(final Object o) {
    return internalMap.remove(o);
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public void putAll(final Map map) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void clear() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Set keySet() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Collection values() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Set entrySet() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean equals(final Object o) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int hashCode() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Object putIfAbsent(final Object o, final Object o2) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean remove(final Object o, final Object o2) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean replace(final Object o, final Object o2, final Object o3) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Object replace(final Object o, final Object o2) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
