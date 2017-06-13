package com.orbotix.le;

import java.util.UUID;

public class RobotRadioDescriptor extends RadioDescriptor
{
  private static final String a = "2B-";
  private static final String b = "1C-";
  private static final UUID c = UUID.fromString("22bb746f-2ba0-7554-2d6f-726568705327");
  private static final UUID d = UUID.fromString("22bb746F-2ba1-7554-2D6F-726568705327");
  private static final UUID e = UUID.fromString("22bb746F-2ba6-7554-2D6F-726568705327");
  
  private static final UUID f = UUID.fromString("0000180a-0000-1000-8000-00805f9b34fb");
  private static final UUID g = UUID.fromString("00002A24-0000-1000-8000-00805f9b34fb");
  private static final UUID h = UUID.fromString("00002A25-0000-1000-8000-00805f9b34fb");
  private static final UUID i = UUID.fromString("00002A26-0000-1000-8000-00805f9b34fb");
  
  private UUID j;
  
  private UUID k;
  private UUID l;
  private UUID m = f;
  private UUID n = g;
  private UUID o = h;
  private UUID p = i;
  
  public static RobotRadioDescriptor getOllieDescriptor() {
    RobotRadioDescriptor localRobotRadioDescriptor = new RobotRadioDescriptor();
    localRobotRadioDescriptor.setNamePrefixes(new String[] { "2B-" });
    return localRobotRadioDescriptor;
  }
  
  public static RobotRadioDescriptor getWeBallDescriptor() {
    RobotRadioDescriptor localRobotRadioDescriptor = new RobotRadioDescriptor();
    localRobotRadioDescriptor.setNamePrefixes(new String[] { "1C-" });
    return localRobotRadioDescriptor;
  }
  
  public RobotRadioDescriptor()
  {
    j = c;
    k = d;
    l = e;
    _namePrefixes = new String[] { "2B-", "1C-" };
    _advertisedUUIDs = new UUID[] { j };
    _requiredUUIDs = new UUID[] { j, _uuidRadioService, f };
  }
  
  public UUID getUUIDRobotService() {
    return j;
  }
  
  public UUID getUUIDControlCharacteristic() {
    return k;
  }
  
  public UUID getUUIDResponseCharacteristic() {
    return l;
  }
  
  public UUID getUUIDDeviceInformationService() { return m; }
  
  public UUID getUUIDModelNumberCharacteristic() { return n; }
  
  public UUID getUUIDSerialNumberCharacteristic() { return o; }
  
  public UUID getUUIDRadioFirmwareVersionCharacteristic() { return p; }
}
