package com.orbotix.common.utilities.binary;

import java.util.Iterator;
import java.util.List;




public class ByteUtil
{
  public ByteUtil() {}
  
  public static int convertUnsignedToInt(byte b)
  {
    return b & 0xFF;
  }
  






  public static int convertUnsignedToInt(byte msb, byte lsb)
  {
    int i = convertUnsignedToInt(msb);
    return i << 8 | convertUnsignedToInt(lsb);
  }
  





  public static short convertUnsignedToShort(byte b)
  {
    return (short)((short)b & 0xFF);
  }
  
  public static String byteArrayToHex(byte[] a) {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    for (int m : a) {
      localStringBuilder.append(String.format("%02x", new Object[] { Integer.valueOf(m & 0xFF) }));
      i++; if (i == 2) {
        i = 0;
        localStringBuilder.append(" ");
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String listOfBytesToHex(List<Byte> bytes) {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    for (Iterator localIterator = bytes.iterator(); localIterator.hasNext();) { int j = ((Byte)localIterator.next()).byteValue();
      localStringBuilder.append(String.format("%02x", new Object[] { Integer.valueOf(j & 0xFF) }));
      i++; if (i == 2) {
        i = 0;
        localStringBuilder.append(" ");
      }
    }
    return localStringBuilder.toString();
  }
  
  public static byte[] hexStringToByteArray(String s) {
    int i = s.length();
    byte[] arrayOfByte = new byte[i / 2];
    for (int j = 0; j < i; j += 2)
    {
      arrayOfByte[(j / 2)] = ((byte)((Character.digit(s.charAt(j), 16) << 4) + Character.digit(s.charAt(j + 1), 16)));
    }
    return arrayOfByte;
  }
  
  public static byte[] concatenateByteArrays(byte[] left, byte[] right) {
    if (left == null) {
      return right;
    }
    if (right == null) {
      return left;
    }
    byte[] arrayOfByte = new byte[left.length + right.length];
    System.arraycopy(left, 0, arrayOfByte, 0, left.length);
    System.arraycopy(right, 0, arrayOfByte, left.length, right.length);
    return arrayOfByte;
  }
  
  public static byte[] readBytesFromFront(byte[] in, int length) {
    byte[] arrayOfByte = new byte[length];
    System.arraycopy(in, 0, arrayOfByte, 0, length);
    return arrayOfByte;
  }
  
  public static byte[] removeBytesFromFront(byte[] in, int length) {
    byte[] arrayOfByte = new byte[in.length - length];
    if (arrayOfByte.length > 0) {
      System.arraycopy(in, length, arrayOfByte, 0, arrayOfByte.length);
    }
    return arrayOfByte;
  }
  
  public static byte[] reverse(byte[] source) {
    byte[] arrayOfByte = new byte[source.length];
    System.arraycopy(source, 0, arrayOfByte, 0, arrayOfByte.length);
    reverseNoCopy(arrayOfByte);
    return arrayOfByte;
  }
  
  public static void reverseNoCopy(byte[] source) {
    for (int i = 0; i < source.length / 2; i++) {
      int j = source[i];
      int k = source.length - 1 - i;
      source[i] = source[k];
      source[k] = j;
    }
  }
  
  public static int convertBytesTo16BitInt(byte[] source) {
    if (source.length != 2) {
      throw new IllegalArgumentException("Length of source bytes must be exactly 2");
    }
    int i = 0;
    i += ((source[0] & 0xFF) << 8);
    i += (source[1] & 0xFF);
    return i;
  }
  
  public static int convertBytesTo32BitInt(byte[] source) {
    if (source.length != 4) {
      throw new IllegalArgumentException("Length of source bytes must be exactly 4");
    }
    return (source[0] & 0xFF) << 24 | (source[1] & 0xFF) << 16 | (source[2] & 0xFF) << 8 | source[3] & 0xFF;
  }
  


  public static long convertBytesTo32BitUnsignedInt(byte[] source)
  {
    int i = convertBytesTo32BitInt(source);
    return i & 0xFFFFFFFF;
  }
  
  public static float convertBytesTo32BitFloat(byte[] source) {
    return Float.intBitsToFloat(convertBytesTo32BitInt(source));
  }
}
