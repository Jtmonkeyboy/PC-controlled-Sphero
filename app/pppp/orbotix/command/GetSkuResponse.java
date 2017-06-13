package com.orbotix.command;

import com.orbotix.common.DLog;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;
import com.orbotix.common.utilities.binary.ByteUtil;
import java.io.UnsupportedEncodingException;

public class GetSkuResponse extends DeviceResponse
{
  private static final String[] a = { "BLE", "BV2", "BV3" };
  private String b;
  
  protected GetSkuResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected void parseData()
  {
    byte[] arrayOfByte1 = getPacket();
    if (getResponseCode() == ResponseCode.OK) {
      byte[] arrayOfByte2 = new byte[arrayOfByte1[4] - 1];
      System.arraycopy(arrayOfByte1, 5, arrayOfByte2, 0, arrayOfByte2.length);
      ByteUtil.reverseNoCopy(arrayOfByte2);
      try
      {
        b = new String(arrayOfByte2, "UTF-8");
      } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        DLog.e("Could not build the sku string with bytes: " + ByteUtil.byteArrayToHex(arrayOfByte2));
      }
    }
  }
  
  public String getSku() {
    return b;
  }
  
  public boolean isDarkside() {
    if (b == null) {
      return false;
    }
    for (String str : a) {
      if (b.equals(str)) {
        return true;
      }
    }
    return false;
  }
}
