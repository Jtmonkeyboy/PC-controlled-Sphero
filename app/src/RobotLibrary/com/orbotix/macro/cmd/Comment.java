package com.orbotix.macro.cmd;

import com.orbotix.common.utilities.binary.ByteUtil;



public class Comment
  implements MacroCommand
{
  private static final String a = "Comment";
  public static final int COMMAND_ID = 32;
  private String b = "";
  

  public Comment(byte[] bytes)
  {
    int i = ByteUtil.convertUnsignedToInt(bytes[1], bytes[2]);
    
    byte[] arrayOfByte = new byte[i];
    
    for (int j = 0; j < arrayOfByte.length; j++) {
      arrayOfByte[j] = bytes[(j + 3)];
    }
    
    setComment(new String(arrayOfByte));
  }
  
  public Comment(String comment) {
    setComment(comment);
  }
  

  public void setComment(String comment)
  {
    b = a(comment);
  }
  

  private String a(String paramString)
  {
    if (paramString.getBytes().length < 65535) {
      return paramString;
    }
    
    byte[] arrayOfByte1 = new byte[65535];
    byte[] arrayOfByte2 = paramString.getBytes();
    
    for (int i = 0; i < arrayOfByte1.length; i++) {
      arrayOfByte1[i] = arrayOfByte2[i];
    }
    
    return new String(arrayOfByte1);
  }
  

























  public String getComment()
  {
    return b;
  }
  
  public int getLength()
  {
    return 3 + b.getBytes().length;
  }
  

  public byte[] getByteRepresentation()
  {
    byte[] arrayOfByte1 = b.getBytes();
    byte[] arrayOfByte2 = new byte[3 + arrayOfByte1.length];
    
    arrayOfByte2[0] = getCommandId();
    arrayOfByte2[1] = ((byte)((arrayOfByte1.length & 0xFFFF) >> 8));
    arrayOfByte2[2] = ((byte)(arrayOfByte1.length & 0xFF));
    

    for (int i = 0; i < arrayOfByte1.length; i++) {
      arrayOfByte2[(i + 3)] = arrayOfByte1[i];
    }
    
    return arrayOfByte2;
  }
  
  public String getName()
  {
    return "Comment";
  }
  
  public byte getCommandId()
  {
    return getCommandID();
  }
  
  public static byte getCommandID() {
    return 32;
  }
  
  public String getSettingsString()
  {
    return b;
  }
}
