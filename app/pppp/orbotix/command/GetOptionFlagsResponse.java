package com.orbotix.command;

import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;

























public class GetOptionFlagsResponse
  extends DeviceResponse
{
  private static final int a = 4;
  public static final long OPTION_FLAGS_PREVENT_SLEEP_IN_CHARGER = 1L;
  public static final long OPTION_FLAGS_ENABLE_VECTORE_DRIVE = 2L;
  public static final long OPTION_FLAGS_DISABLE_SELF_LEVEL_IN_CHARGER = 4L;
  public static final long OPTION_FLAGS_TAIL_LIGHT_ALWAYS_ON = 8L;
  public static final long OPTION_FLAGS_ENABLE_MOTION_TIMOUT = 16L;
  private long b;
  
  protected GetOptionFlagsResponse(byte[] packet, DeviceCommand command)
  {
    super(packet, command);
  }
  
  protected void parseData()
  {
    super.parseData();
    
    if (getResponseCode() == ResponseCode.OK) {
      b = ((getPacket()[5] & 0xFF) << 24);
      b += ((getPacket()[6] & 0xFF) << 16);
      b += ((getPacket()[7] & 0xFF) << 8);
      b += (getPacket()[8] & 0xFF);
    }
  }
  













  public long getOptionFlags()
  {
    return b;
  }
  





  public boolean isOptionFlagSet(long optionFlag)
  {
    return (optionFlag & b) > 0L;
  }
}
