package com.orbotix.command;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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







public class ResponseFactory
{
  private static final String a = "obx-rfactory";
  private static final Map<Byte, Class<? extends DeviceResponse>> b = new HashMap();
  

  private static final Map<Byte, Class<? extends DeviceResponse>> c = new HashMap();
  

  private static final Map<Byte, Class<? extends DeviceResponse>> d = new HashMap();
  private static ResponseFactory e;
  
  static
  {
    register(DeviceId.BOOTLOADER.getValue(), (byte)4, JumpToMainResponse.class);
    
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
    register(DeviceId.ROBOT.getValue(), (byte)55, NonPersistentSetOptionFlagsResponse.class);
    register(DeviceId.ROBOT.getValue(), (byte)56, NonPersistentGetOptionFlagsResponse.class);
  }
  
  public static void register(byte deviceId, byte commandId, Class<? extends DeviceResponse> responseClass) {
    if (deviceId == DeviceId.ROBOT.getValue()) {
      b.put(Byte.valueOf(commandId), responseClass);
    } else if (deviceId == DeviceId.CORE.getValue()) {
      c.put(Byte.valueOf(commandId), responseClass);
    } else {
      d.put(Byte.valueOf(commandId), responseClass);
    }
  }
  




  public static synchronized ResponseFactory getInstance()
  {
    if (e == null) {
      e = new ResponseFactory();
    }
    return e;
  }
  
  @NonNull
  public DeviceResponse responseFromRawPacket(byte[] packet, DeviceCommand command) { if (command == null) {
      DLog.v("Command could not be found for sequence number: %02X", new Object[] { Byte.valueOf(packet[3]) });
    }
    if (packet == null) {
      throw new IllegalArgumentException("Argument \"packet\" cannot be null");
    }
    

    if ((command == null) || (command.getClass().equals(DeviceCommand.class))) {
      Log.v("obx-rfactory", "Base device command response created");
      return new ByteResponse(packet, command);
    }
    if (command.getClass().equals(ImmutableCommand.class)) {
      return new ByteResponse(packet, command);
    }
    
    Class localClass = a(command);
    
    return a(localClass, packet, command);
  }
  
  @Nullable
  private Class<? extends DeviceResponse> a(@NonNull DeviceCommand paramDeviceCommand) { Class localClass = null;
    int i = paramDeviceCommand.getDeviceId();
    
    if (i == DeviceId.ROBOT.getValue()) {
      localClass = (Class)b.get(Byte.valueOf(paramDeviceCommand.getCommandId()));
    }
    else if (i == DeviceId.CORE.getValue()) {
      localClass = (Class)c.get(Byte.valueOf(paramDeviceCommand.getCommandId()));
    }
    else if (i == DeviceId.BOOTLOADER.getValue()) {
      localClass = (Class)d.get(Byte.valueOf(paramDeviceCommand.getCommandId()));
    }
    return localClass;
  }
  
  @NonNull
  private DeviceResponse a(@Nullable Class<? extends DeviceResponse> paramClass, @NonNull byte[] paramArrayOfByte, @NonNull DeviceCommand paramDeviceCommand) { if (paramClass == null) {
      DLog.w("Response class is null, creating a byte response...");
      return new ByteResponse(paramArrayOfByte, paramDeviceCommand);
    }
    Constructor localConstructor = a(paramClass);
    if (localConstructor == null) {
      DLog.w("Constructor was null for response, creating a byte response...");
      return new ByteResponse(paramArrayOfByte, paramDeviceCommand);
    }
    DeviceResponse localDeviceResponse = a(localConstructor, paramArrayOfByte, paramDeviceCommand);
    if (localDeviceResponse == null) {
      DLog.w("Could not create a response from the constructor, creating a byte response...");
      return new ByteResponse(paramArrayOfByte, paramDeviceCommand);
    }
    return localDeviceResponse;
  }
  
  @Nullable
  private Constructor<? extends DeviceResponse> a(@NonNull Class<? extends DeviceResponse> paramClass) { Constructor localConstructor = null;
    try {
      localConstructor = paramClass.getDeclaredConstructor(new Class[] { [B.class, DeviceCommand.class });
    } catch (NoSuchMethodException localNoSuchMethodException) {
      DLog.e("Could not get declared constructor of response class: %s. Reason: %s", new Object[] { paramClass, localNoSuchMethodException.getMessage() });
    }
    return localConstructor;
  }
  
  @Nullable
  private DeviceResponse a(@NonNull Constructor<? extends DeviceResponse> paramConstructor, @NonNull byte[] paramArrayOfByte, @NonNull DeviceCommand paramDeviceCommand) { DeviceResponse localDeviceResponse = null;
    try {
      localDeviceResponse = (DeviceResponse)paramConstructor.newInstance(new Object[] { paramArrayOfByte, paramDeviceCommand });
    } catch (InvocationTargetException localInvocationTargetException) {
      DLog.e("Could not build response for command %s. Reason: %s", new Object[] { paramDeviceCommand, localInvocationTargetException.getMessage() });
    } catch (InstantiationException localInstantiationException) {
      DLog.e("Could not build response for command %s. Reason: %s", new Object[] { paramDeviceCommand, localInstantiationException.getMessage() });
    } catch (IllegalAccessException localIllegalAccessException) {
      if (!paramConstructor.isAccessible()) {
        paramConstructor.setAccessible(true);
        try {
          localDeviceResponse = (DeviceResponse)paramConstructor.newInstance(new Object[] { paramArrayOfByte, paramDeviceCommand });
        } catch (Exception localException) {
          DLog.e("Could not instantiate a response after changing access level");
        }
      }
    }
    return localDeviceResponse;
  }
  
  private ResponseFactory() {}
}
