//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.command;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.orbotix.command.BackLEDOutputResponse;
import com.orbotix.command.BoostResponse;
import com.orbotix.command.ConfigureCollisionDetectionResponse;
import com.orbotix.command.ConfigureLocatorResponse;
import com.orbotix.command.GetBluetoothInfoResponse;
import com.orbotix.command.GetChargerStateResponse;
import com.orbotix.command.GetChassisIdResponse;
import com.orbotix.command.GetDeviceModeResponse;
import com.orbotix.command.GetOdometerResponse;
import com.orbotix.command.GetOptionFlagsResponse;
import com.orbotix.command.GetPowerStateResponse;
import com.orbotix.command.GetSkuResponse;
import com.orbotix.command.GetUserRGBColorResponse;
import com.orbotix.command.JumpToBootloaderResponse;
import com.orbotix.command.JumpToMainResponse;
import com.orbotix.command.Level1DiagnosticsResponse;
import com.orbotix.command.NonPersistentGetOptionFlagsResponse;
import com.orbotix.command.NonPersistentSetOptionFlagsResponse;
import com.orbotix.command.PingResponse;
import com.orbotix.command.PollPacketTimesResponse;
import com.orbotix.command.RGBLEDOutputResponse;
import com.orbotix.command.RawMotorResponse;
import com.orbotix.command.RollResponse;
import com.orbotix.command.RotationRateResponse;
import com.orbotix.command.SelfLevelResponse;
import com.orbotix.command.SetDataStreamingResponse;
import com.orbotix.command.SetHeadingResponse;
import com.orbotix.command.SetInactivityTimeoutResponse;
import com.orbotix.command.SetMotionTimeoutResponse;
import com.orbotix.command.SetOptionFlagsResponse;
import com.orbotix.command.SetPowerNotificationResponse;
import com.orbotix.command.SetRobotNameResponse;
import com.orbotix.command.SetUserHackModeResponse;
import com.orbotix.command.SleepResponse;
import com.orbotix.command.StabilizationResponse;
import com.orbotix.command.VersioningResponse;
import com.orbotix.common.DLog;
import com.orbotix.common.internal.ByteResponse;
import com.orbotix.common.internal.CoreCommandId;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ImmutableCommand;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.macro.AbortMacroResponse;
import com.orbotix.macro.InitMacroExecutiveResponse;
import com.orbotix.macro.RunMacroResponse;
import com.orbotix.macro.SaveMacroResponse;
import com.orbotix.macro.SaveTemporaryMacroChunkResponse;
import com.orbotix.macro.SaveTemporaryMacroResponse;
import com.orbotix.orbbasic.OrbBasicAbortProgramResponse;
import com.orbotix.orbbasic.OrbBasicAppendFragmentResponse;
import com.orbotix.orbbasic.OrbBasicEraseStorageResponse;
import com.orbotix.orbbasic.OrbBasicExecuteProgramResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ResponseFactory {
  private static final String a = "obx-rfactory";
  private static final Map<Byte, Class<? extends DeviceResponse>> b = new HashMap();
  private static final Map<Byte, Class<? extends DeviceResponse>> c = new HashMap();
  private static final Map<Byte, Class<? extends DeviceResponse>> d = new HashMap();
  private static ResponseFactory e;

  public static void register(byte deviceId, byte commandId, Class<? extends DeviceResponse> responseClass) {
    if(deviceId == DeviceId.ROBOT.getValue()) {
      b.put(Byte.valueOf(commandId), responseClass);
    } else if(deviceId == DeviceId.CORE.getValue()) {
      c.put(Byte.valueOf(commandId), responseClass);
    } else {
      d.put(Byte.valueOf(commandId), responseClass);
    }

  }

  private ResponseFactory() {
  }

  public static synchronized ResponseFactory getInstance() {
    if(e == null) {
      e = new ResponseFactory();
    }

    return e;
  }

  @NonNull
  public DeviceResponse responseFromRawPacket(byte[] packet, DeviceCommand command) {
    if(command == null) {
      DLog.v("Command could not be found for sequence number: %02X", new Object[]{Byte.valueOf(packet[3])});
    }

    if(packet == null) {
      throw new IllegalArgumentException("Argument \"packet\" cannot be null");
    } else if(command != null && !command.getClass().equals(DeviceCommand.class)) {
      if(command.getClass().equals(ImmutableCommand.class)) {
        return new ByteResponse(packet, command);
      } else {
        Class var3 = this.a(command);
        return this.a(var3, packet, command);
      }
    } else {
      Log.v("obx-rfactory", "Base device command response created");
      return new ByteResponse(packet, command);
    }
  }

  @Nullable
  private Class<? extends DeviceResponse> a(@NonNull DeviceCommand var1) {
    Class var2 = null;
    byte var3 = var1.getDeviceId();
    if(var3 == DeviceId.ROBOT.getValue()) {
      var2 = (Class)b.get(Byte.valueOf(var1.getCommandId()));
    } else if(var3 == DeviceId.CORE.getValue()) {
      var2 = (Class)c.get(Byte.valueOf(var1.getCommandId()));
    } else if(var3 == DeviceId.BOOTLOADER.getValue()) {
      var2 = (Class)d.get(Byte.valueOf(var1.getCommandId()));
    }

    return var2;
  }

  @NonNull
  private DeviceResponse a(@Nullable Class<? extends DeviceResponse> var1, @NonNull byte[] var2, @NonNull DeviceCommand var3) {
    if(var1 == null) {
      DLog.w("Response class is null, creating a byte response...");
      return new ByteResponse(var2, var3);
    } else {
      Constructor var4 = this.a(var1);
      if(var4 == null) {
        DLog.w("Constructor was null for response, creating a byte response...");
        return new ByteResponse(var2, var3);
      } else {
        DeviceResponse var5 = this.a(var4, var2, var3);
        if(var5 == null) {
          DLog.w("Could not create a response from the constructor, creating a byte response...");
          return new ByteResponse(var2, var3);
        } else {
          return var5;
        }
      }
    }
  }

  @Nullable
  private Constructor<? extends DeviceResponse> a(@NonNull Class<? extends DeviceResponse> var1) {
    Constructor var2 = null;

    try {
      var2 = var1.getDeclaredConstructor(new Class[]{byte[].class, DeviceCommand.class});
    } catch (NoSuchMethodException var4) {
      DLog.e("Could not get declared constructor of response class: %s. Reason: %s", new Object[]{var1, var4.getMessage()});
    }

    return var2;
  }

  @Nullable
  private DeviceResponse a(@NonNull Constructor<? extends DeviceResponse> var1, @NonNull byte[] var2, @NonNull DeviceCommand var3) {
    DeviceResponse var4 = null;

    try {
      var4 = (DeviceResponse)var1.newInstance(new Object[]{var2, var3});
    } catch (InvocationTargetException var8) {
      DLog.e("Could not build response for command %s. Reason: %s", new Object[]{var3, var8.getMessage()});
    } catch (InstantiationException var9) {
      DLog.e("Could not build response for command %s. Reason: %s", new Object[]{var3, var9.getMessage()});
    } catch (IllegalAccessException var10) {
      if(!var1.isAccessible()) {
        var1.setAccessible(true);

        try {
          var4 = (DeviceResponse)var1.newInstance(new Object[]{var2, var3});
        } catch (Exception var7) {
          DLog.e("Could not instantiate a response after changing access level");
        }
      }
    }

    return var4;
  }

  static {
    register(DeviceId.BOOTLOADER.getValue(), 4, JumpToMainResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.JUMP_TO_BOOTLOADER.getValue(), JumpToBootloaderResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.PING.getValue(), PingResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.VERSIONING.getValue(), VersioningResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.SLEEP.getValue(), SleepResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.GET_BLUETOOTH_INFO.getValue(), GetBluetoothInfoResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.SET_BLUETOOTH_NAME.getValue(), SetRobotNameResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.LEVEL_1_DIAGNOSTICS.getValue(), Level1DiagnosticsResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.POLL_PACKET_TIMES.getValue(), PollPacketTimesResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.GET_POWER_STATE.getValue(), GetPowerStateResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.SET_POWER_NOTIFICATION.getValue(), SetPowerNotificationResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.SET_INACTIVITY_TIMEOUT.getValue(), SetInactivityTimeoutResponse.class);
    register(DeviceId.CORE.getValue(), CoreCommandId.GET_CHARGER_STATE.getValue(), GetChargerStateResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.BOOST.getValue(), BoostResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.BACK_LED_OUTPUT.getValue(), BackLEDOutputResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.SET_HEADING.getValue(), SetHeadingResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.RGB_LED_OUTPUT.getValue(), RGBLEDOutputResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.ROLL.getValue(), RollResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.ROTATION_RATE.getValue(), RotationRateResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.STABILIZATION.getValue(), StabilizationResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.RUN_MACRO.getValue(), RunMacroResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.SAVE_TEMPORARY_MACRO.getValue(), SaveTemporaryMacroResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.ABORT_MACRO.getValue(), AbortMacroResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.RAW_MOTOR.getValue(), RawMotorResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.SAVE_MACRO.getValue(), SaveMacroResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.SET_DATA_STREAMING.getValue(), SetDataStreamingResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.SAVE_TEMPORARY_MACRO_CHUNK.getValue(), SaveTemporaryMacroChunkResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.INIT_MACRO_EXECUTIVE.getValue(), InitMacroExecutiveResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.CONFIGURE_COLLISION_DETECTION.getValue(), ConfigureCollisionDetectionResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.CONFIGURE_LOCATOR.getValue(), ConfigureLocatorResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.GET_LED_COLOR.getValue(), GetUserRGBColorResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.GET_DEVICE_MODE.getValue(), GetDeviceModeResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.SET_DEVICE_MODE.getValue(), SetUserHackModeResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.SELF_LEVEL.getValue(), SelfLevelResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.SET_MOTION_TIMEOUT.getValue(), SetMotionTimeoutResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.ORB_BASIC_ERASE_STORAGE.getValue(), OrbBasicEraseStorageResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.ORB_BASIC_APPEND_FRAGMENT.getValue(), OrbBasicAppendFragmentResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.ORB_BASIC_EXECUTE_PROGRAM.getValue(), OrbBasicExecuteProgramResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.ORB_BASIC_ABORT_PROGRAM.getValue(), OrbBasicAbortProgramResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.GET_ODOMETER.getValue(), GetOdometerResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.GET_SKU.getValue(), GetSkuResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.GET_CHASSIS_ID.getValue(), GetChassisIdResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.GET_OPTION_FLAGS.getValue(), GetOptionFlagsResponse.class);
    register(DeviceId.ROBOT.getValue(), RobotCommandId.SET_OPTION_FLAGS.getValue(), SetOptionFlagsResponse.class);
    register(DeviceId.ROBOT.getValue(), 55, NonPersistentSetOptionFlagsResponse.class);
    register(DeviceId.ROBOT.getValue(), 56, NonPersistentGetOptionFlagsResponse.class);
  }
}
