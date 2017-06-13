package com.orbotix.common.internal;

import com.orbotix.common.RobotMajorMinorVersion;










public class RobotVersion
{
  private final int a;
  private final String b;
  private final String c;
  private final String d;
  private final String e;
  private final String f;
  private final String g;
  private final RobotMajorMinorVersion h;
  private final boolean i;
  
  public RobotVersion(int model, String recordVersion, String hwVersion, String mainApplicationVersion, String bootloaderVersion, String orbBasicVersion, String overlayManagerVersion)
  {
    a = model;
    b = recordVersion;
    c = hwVersion;
    d = mainApplicationVersion;
    e = bootloaderVersion;
    f = orbBasicVersion;
    g = overlayManagerVersion;
    i = false;
    h = new RobotMajorMinorVersion(mainApplicationVersion);
  }
  
  public RobotVersion()
  {
    i = true;
    a = 0;
    b = "0";
    c = "0";
    d = "0.0";
    e = "0.0";
    f = "0.0";
    g = "0.0";
    h = new RobotMajorMinorVersion("0.0");
  }
  
  public String getRecordVersion()
  {
    return b;
  }
  


  public int getModelNumber()
  {
    return a;
  }
  
  public String getHardwareVersion()
  {
    return c;
  }
  
  public String getMainApplicationVersion()
  {
    return d;
  }
  
  public String getBootloaderVersion() {
    return e;
  }
  
  public String getOrbBasicVersion() {
    return f;
  }
  
  public String getOverlayManagerVersion() {
    return g;
  }
  
  public RobotMajorMinorVersion getMainAppMajorMinorVersion() {
    return h;
  }
  
  public String toString()
  {
    return "RobotVersion{overlayManagerVersion='" + g + '\'' + ", orbBasicVersion='" + f + '\'' + ", bootloaderVersion='" + e + '\'' + ", mainApplicationVersion='" + d + '\'' + ", hardwareVersion='" + c + '\'' + ", recordVersion='" + b + '\'' + ", modelNumber=" + a + '}';
  }
}
