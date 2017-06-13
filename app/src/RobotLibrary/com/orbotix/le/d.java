package com.orbotix.le;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class d
{
  private static final String d = "OBX-LeAdRecord";
  public int a = -1;
  public int b = -1;
  public byte[] c = null;
  


  public d(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    a = paramInt1;
    b = paramInt2;
    c = paramArrayOfByte;
  }
  
  public static List<d> a(byte[] paramArrayOfByte) {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    

    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[(i++)];
      

      if (j == 0) {
        break;
      }
      int k = paramArrayOfByte[i];
      

      if (k == 0) {
        break;
      }
      byte[] arrayOfByte = Arrays.copyOfRange(paramArrayOfByte, i + 1, i + j);
      

      localArrayList.add(new d(j, k, arrayOfByte));
      

      i += j;
    }
    

    return localArrayList;
  }
}
