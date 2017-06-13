//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.common.internal;

import com.orbotix.common.DLog;

public enum ResponseCode {
  OK(0, "Command Succeeded"),
  GENERAL_ERROR(1, "General Error"),
  CHECKSUM_ERROR(2, "Checksum Failure"),
  FRAGMENT_ERROR(3, "Command Fragment Error"),
  BAD_COMMAND_ERROR(4, "Unknown Command"),
  UNSUPPORTED_ERROR(5, "Unsupported Command"),
  BAD_MESSAGE_ERROR(6, "Bad Message Format"),
  PARAMETER_ERROR(7, "Invalid Parameter"),
  EXECUTE_ERROR(8, "Command Execution Failure"),
  UNKNOWN_DEVICE_ID(9, "Unknown Device Subsystem"),
  LOW_VOLTAGE_ERROR(49, "Flash Fail: Voltage Too Low"),
  ILLEGAL_PAGE_NUM(50, "Flash Fail: Illegal Page Number"),
  FLASH_FAIL(51, "Flash Fail: Page Reprogram Fail"),
  MAIN_APP_CORRUPT(52, "MainAppCorrupt"),
  RESPONSE_TIMEOUT(53, "IncompleteCommand"),
  TIMEOUT_ERROR(255, "TimeoutError");

  private final byte a;
  private final String b;

  private ResponseCode(int code, String description) {
    this.a = (byte)code;
    this.b = description;
  }

  public byte getCode() {
    return this.a;
  }

  public static ResponseCode byteToResponseCode(byte val) {
    ResponseCode[] var1 = values();
    int var2 = var1.length;

    for(int var3 = 0; var3 < var2; ++var3) {
      ResponseCode var4 = var1[var3];
      if(var4.getCode() == val) {
        return var4;
      }
    }

    DLog.e("RobotKit", new Object[]{"Invalid ResponseCode: " + String.format("%02X", new Object[]{Byte.valueOf(val)})});
    return GENERAL_ERROR;
  }

  public String toString() {
    return this.b;
  }
}
