package com.orbotix.common.utilities.jni;


public class CharArrayReferenceContainer
{
  private char[] a;
  
  public CharArrayReferenceContainer()
  {
    a = new char[0];
  }
  
  public char[] getCharArray() {
    return a;
  }
  
  public void setCharArray(char[] charArray) {
    a = charArray;
  }
}
