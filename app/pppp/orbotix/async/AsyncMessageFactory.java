//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.async;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.orbotix.async.CollisionDetectedAsyncData;
import com.orbotix.async.DeviceSensorAsyncMessage;
import com.orbotix.async.GyroLimitsExceededAsyncData;
import com.orbotix.async.Level1DiagnosticsAsyncData;
import com.orbotix.async.MacroEmitMarker;
import com.orbotix.async.PowerNotificationAsyncData;
import com.orbotix.async.SelfLevelCompleteAsyncData;
import com.orbotix.async.SleepDidOccurMessage;
import com.orbotix.async.SleepWillOccurMessage;
import com.orbotix.common.DLog;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.AsyncMessage.Type;
import com.orbotix.orbbasic.OrbBasicErrorASCIIAsyncData;
import com.orbotix.orbbasic.OrbBasicErrorBinaryAsyncData;
import com.orbotix.orbbasic.OrbBasicPrintMessageAsyncData;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class AsyncMessageFactory {
  private static final Map<Type, Class<? extends AsyncMessage>> a = new HashMap();
  private static AsyncMessageFactory b;

  public static void register(Type dataType, Class<? extends AsyncMessage> asyncDataClass) {
    a.put(dataType, asyncDataClass);
  }

  private AsyncMessageFactory() {
  }

  public static synchronized AsyncMessageFactory getInstance() {
    if(b == null) {
      b = new AsyncMessageFactory();
    }

    return b;
  }

  @NonNull
  public AsyncMessage dataFromPacket(byte[] packet) {
    byte var2 = packet[2];
    Class var3 = this.a(var2);
    return this.a(var3, packet);
  }

  @Nullable
  private Class<? extends AsyncMessage> a(byte var1) {
    Type var2 = Type.fromByte(var1);
    return (Class)a.get(var2);
  }

  @NonNull
  private AsyncMessage a(@Nullable Class<? extends AsyncMessage> var1, @NonNull byte[] var2) {
    if(var1 == null) {
      return new AsyncMessage(var2);
    } else {
      Constructor var3 = this.a(var1);
      if(var3 == null) {
        DLog.w("Constructor was null for async message, creating base AsyncMessage...");
        return new AsyncMessage(var2);
      } else {
        AsyncMessage var4 = this.a(var3, var2);
        if(var4 == null) {
          DLog.w("Could not create an async message from the constructor, creating base AsyncMessage...");
          return new AsyncMessage(var2);
        } else {
          return var4;
        }
      }
    }
  }

  @Nullable
  private Constructor<? extends AsyncMessage> a(@NonNull Class<? extends AsyncMessage> var1) {
    Constructor var2 = null;

    try {
      var2 = var1.getDeclaredConstructor(new Class[]{byte[].class});
    } catch (NoSuchMethodException var4) {
      DLog.e("Could not get declared constructor of async message class: %s. Reason: %s", new Object[]{var1, var4.getMessage()});
    }

    return var2;
  }

  @Nullable
  private AsyncMessage a(@NonNull Constructor<? extends AsyncMessage> var1, @NonNull byte[] var2) {
    AsyncMessage var3 = null;

    try {
      var3 = (AsyncMessage)var1.newInstance(new Object[]{var2});
    } catch (InvocationTargetException var7) {
      DLog.e("Could not build async message. Reason: %s", new Object[]{var7.getMessage()});
    } catch (InstantiationException var8) {
      DLog.e("Could not build async message. Reason: %s", new Object[]{var8.getMessage()});
    } catch (IllegalAccessException var9) {
      if(!var1.isAccessible()) {
        DLog.w("Constructor of async message class is not accessible. Overriding access level and retrying...");
        var1.setAccessible(true);

        try {
          var3 = (AsyncMessage)var1.newInstance(new Object[]{var2});
        } catch (Exception var6) {
          DLog.e("Could not instantiate a response after changing access level");
        }
      }
    }

    return var3;
  }

  static {
    register(Type.MacroEmitMarker, MacroEmitMarker.class);
    register(Type.WillSleepAsyncMessage, SleepWillOccurMessage.class);
    register(Type.DidSleepAsyncMessage, SleepDidOccurMessage.class);
    register(Type.L1DiagnosticAsyncMessage, Level1DiagnosticsAsyncData.class);
    register(Type.SensorAsyncMessage, DeviceSensorAsyncMessage.class);
    register(Type.CollisionDetected, CollisionDetectedAsyncData.class);
    register(Type.SelfLevelComplete, SelfLevelCompleteAsyncData.class);
    register(Type.PowerAsyncMessage, PowerNotificationAsyncData.class);
    register(Type.OrbBasicPrint, OrbBasicPrintMessageAsyncData.class);
    register(Type.OrbBasicErrorASCII, OrbBasicErrorASCIIAsyncData.class);
    register(Type.OrbBasicErrorBinary, OrbBasicErrorBinaryAsyncData.class);
    register(Type.GyroLimitsExceeded, GyroLimitsExceededAsyncData.class);
  }
}
