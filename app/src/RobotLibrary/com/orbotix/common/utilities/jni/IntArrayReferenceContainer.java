package com.orbotix.common.utilities.jni;



public class IntArrayReferenceContainer
{
  private int[] a;
  


  public IntArrayReferenceContainer()
  {
    a = new int[0];
  }
  
  public int[] getIntArray() {
    return a;
  }
  
  public void setIntArray(int[] intArray) {
    a = intArray;
  }
}
