package com.orbotix.common;

public class RobotMajorMinorVersion implements Comparable {
  private String a;
  private String b;
  
  public RobotMajorMinorVersion(String versionString) {
    String[] arrayOfString = versionString.split("\\.");
    a = arrayOfString[0];
    
    if (arrayOfString.length == 2) {
      b = arrayOfString[1];
    } else {
      b = "";
    }
  }
  
  public String getMajorVersion() {
    return a;
  }
  
  public String getMinorVersion() {
    return b;
  }
  
  public boolean isGreaterThan(RobotMajorMinorVersion version) {
    return compareTo(version) == 1;
  }
  
  public boolean isLessThan(RobotMajorMinorVersion version) {
    return compareTo(version) == -1;
  }
  
  public boolean isEqualTo(RobotMajorMinorVersion version) {
    return compareTo(version) == 0;
  }
  
  public String versionString() {
    return a + "." + b;
  }
  
  public int compareTo(Object object)
  {
    if ((object != null) && ((object instanceof RobotMajorMinorVersion))) {
      RobotMajorMinorVersion localRobotMajorMinorVersion = (RobotMajorMinorVersion)object;
      
      int i = Integer.parseInt(a);
      int j = Integer.parseInt(b);
      int k = Integer.parseInt(localRobotMajorMinorVersion.getMajorVersion());
      int m = Integer.parseInt(localRobotMajorMinorVersion.getMinorVersion());
      
      if (i != k) {
        return i > k ? 1 : i < k ? -1 : 0;
      }
      return j > m ? 1 : j < m ? -1 : 0;
    }
    

    return 1;
  }
}
