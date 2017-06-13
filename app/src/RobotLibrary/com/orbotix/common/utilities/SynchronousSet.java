package com.orbotix.common.utilities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SynchronousSet<T> implements Iterable<T>
{
  public SynchronousSet() {}
  
  private final ArrayList<T> a = new ArrayList();
  
  public void add(T value) {
    synchronized (a) {
      if (!a.contains(value)) {
        a.add(value);
      }
    }
  }
  
  public void remove(T value) {
    synchronized (a) {
      a.remove(value);
    }
  }
  
  public void clear() {
    synchronized (a) {
      a.clear();
    }
  }
  
  public List<T> toList() {
    synchronized (a)
    {
      return (List)a.clone();
    }
  }
  
  public Iterator<T> iterator()
  {
    return toList().iterator();
  }
  
  public void sortUsingComparator(Comparator<T> comparator) {
    synchronized (a) {
      java.util.Collections.sort(a, comparator);
    }
  }
}
