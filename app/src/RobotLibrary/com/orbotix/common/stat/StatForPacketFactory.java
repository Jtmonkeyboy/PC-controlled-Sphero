package com.orbotix.common.stat;

public class StatForPacketFactory {
  private static final String a = "Macro";
  private static final String b = "#%02X%02X%02X";
  
  public StatForPacketFactory() {}
  
  public static Stat statForPacketAndIdentifier(byte[] packet, String usuallyMac) { if (packet.length < 6) {
      return null;
    }
    if (packet[2] != 2) {
      return null;
    }
    if ((null == usuallyMac) || (usuallyMac.length() == 0)) {
      return null;
    }
    
    switch (packet[3]) {
    case 32: 
      Stat localStat1 = new Stat(Stat.StatKey.RGB_CHANGE, usuallyMac);
      String str = String.format("#%02X%02X%02X", new Object[] {
        Byte.valueOf(packet[6]), 
        Byte.valueOf(packet[7]), 
        Byte.valueOf(packet[8]) });
      localStat1.addData(Stat.StatDataKey.HEX, str);
      return localStat1;
    case 80: 
      Stat localStat2 = new Stat(Stat.StatKey.MACRO_RUN, usuallyMac);
      localStat2.addData(Stat.StatDataKey.NAME, "Macro");
      return localStat2;
    }
    return null;
  }
}
