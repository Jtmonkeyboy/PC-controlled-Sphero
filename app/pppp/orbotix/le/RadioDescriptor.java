package com.orbotix.le;

import java.util.UUID;

public class RadioDescriptor {
  private static UUID a = UUID.fromString("22bb746f-2bb0-7554-2D6F-726568705327");
  private static UUID b = UUID.fromString("22bb746f-2bb2-7554-2D6F-726568705327");
  private static UUID c = UUID.fromString("22BB746F-2BB6-7554-2D6F-726568705327");
  private static UUID d = UUID.fromString("22BB746F-2bb7-7554-2D6F-726568705327");
  private static UUID e = UUID.fromString("22bb746f-2bbd-7554-2D6F-726568705327");
  private static UUID f = UUID.fromString("22bb746f-2bbe-7554-2D6F-726568705327");
  private static UUID g = UUID.fromString("22bb746f-2bbf-7554-2D6F-726568705327");
  
  protected UUID[] _advertisedUUIDs;
  protected UUID[] _requiredUUIDs;
  protected String[] _namePrefixes;
  protected UUID _uuidRadioService;
  protected UUID _uuidTxPowerCharacteristic;
  protected UUID _uuidDeepSleepCharacteristic;
  protected UUID _uuidAntiDOSCharactertistic;
  protected UUID _uuidAntiDOSTimeoutCharacteristic;
  protected UUID _uuidWakeCharacteristic;
  protected UUID _uuidRSSICharacteristic;
  
  public RadioDescriptor()
  {
    _uuidRadioService = a;
    _uuidTxPowerCharacteristic = b;
    _uuidRSSICharacteristic = c;
    _uuidDeepSleepCharacteristic = d;
    _uuidAntiDOSCharactertistic = e;
    _uuidAntiDOSTimeoutCharacteristic = f;
    _uuidWakeCharacteristic = g;
  }
  
  public RadioDescriptor(UUID[] advertisedUUIDs, UUID[] requiredUUIDs, String[] namePrefixes) {
    this();
    _advertisedUUIDs = advertisedUUIDs;
    _requiredUUIDs = requiredUUIDs;
    _namePrefixes = namePrefixes;
  }
  
  public boolean nameStartsWithValidPrefix(String name) {
    if ((name == null) || (name.isEmpty())) {
      return false;
    }
    for (String str : _namePrefixes) {
      if (name.startsWith(str)) {
        return true;
      }
    }
    return false;
  }
  
  public void setNamePrefixes(String[] namePrefixes) {
    _namePrefixes = namePrefixes;
  }
  
  public void setRequiredUUIDs(UUID[] requiredUUIDs) {
    _requiredUUIDs = requiredUUIDs;
  }
  
  public void setAdvertisedUUIDs(UUID[] advertisedUUIDs) {
    _advertisedUUIDs = advertisedUUIDs;
  }
  
  public UUID[] getAdvertisedUUIDs() {
    return _advertisedUUIDs;
  }
  
  public UUID[] getRequiredUUIDs() {
    return _requiredUUIDs;
  }
  
  public String[] getNamePrefixes() { return _namePrefixes; }
  
  public UUID getUUIDRadioService() {
    return _uuidRadioService;
  }
  
  public UUID getUUIDTxPowerCharacteristic() {
    return _uuidTxPowerCharacteristic;
  }
  
  public UUID getUUIDDeepSleepCharacteristic() {
    return _uuidDeepSleepCharacteristic;
  }
  
  public UUID getUUIDAntiDOSCharacteristic() {
    return _uuidAntiDOSCharactertistic;
  }
  
  public UUID getUUIDAntiDOSTimeoutCharactertistic() {
    return _uuidAntiDOSTimeoutCharacteristic;
  }
  
  public UUID getUUIDWakecharacteristic() {
    return _uuidWakeCharacteristic;
  }
  
  public UUID getUUIDRSSICharacteristic() {
    return _uuidRSSICharacteristic;
  }
}
