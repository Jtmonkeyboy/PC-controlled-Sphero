package com.orbotix.ovalcompiler;

public class CompiledOvalLengths {
  private int a;
  private int b;
  private int c;
  private int d;
  
  public CompiledOvalLengths(int codeLength, int sizesLength, int messageLength, int errorOccurred) {
    a = codeLength;
    b = sizesLength;
    c = messageLength;
    d = errorOccurred;
  }
  
  public int getCodeLength() {
    return a;
  }
  
  public int getSizesLength() {
    return b;
  }
  
  public int getMessageLength() {
    return c;
  }
  
  public int getErrorOccurred() {
    return d;
  }
  
  public String toString()
  {
    return String.format("<codeLengths:%d, sizesLength:%d, messageLength:%d, errorOccurred:%d>", new Object[] {
      Integer.valueOf(a), Integer.valueOf(b), Integer.valueOf(c), Integer.valueOf(d) });
  }
}
