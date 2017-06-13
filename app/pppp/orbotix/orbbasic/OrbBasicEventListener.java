package com.orbotix.orbbasic;

public abstract interface OrbBasicEventListener
{
  public abstract void onEraseCompleted(boolean paramBoolean);
  
  public abstract void onLoadProgramComplete(boolean paramBoolean);
  
  public abstract void onPrintMessage(String paramString);
  
  public abstract void onErrorMessage(String paramString);
  
  public abstract void onErrorByteArray(byte[] paramArrayOfByte);
}
