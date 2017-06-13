package com.orbotix.async;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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



public class AsyncMessageFactory
{
  private static final Map<AsyncMessage.Type, Class<? extends AsyncMessage>> a = new HashMap();
  private static AsyncMessageFactory b;
  
  static {
    register(AsyncMessage.Type.MacroEmitMarker, MacroEmitMarker.class);
    register(AsyncMessage.Type.WillSleepAsyncMessage, SleepWillOccurMessage.class);
    register(AsyncMessage.Type.DidSleepAsyncMessage, SleepDidOccurMessage.class);
    register(AsyncMessage.Type.L1DiagnosticAsyncMessage, Level1DiagnosticsAsyncData.class);
    register(AsyncMessage.Type.SensorAsyncMessage, DeviceSensorAsyncMessage.class);
    register(AsyncMessage.Type.CollisionDetected, CollisionDetectedAsyncData.class);
    register(AsyncMessage.Type.SelfLevelComplete, SelfLevelCompleteAsyncData.class);
    register(AsyncMessage.Type.PowerAsyncMessage, PowerNotificationAsyncData.class);
    register(AsyncMessage.Type.OrbBasicPrint, OrbBasicPrintMessageAsyncData.class);
    register(AsyncMessage.Type.OrbBasicErrorASCII, OrbBasicErrorASCIIAsyncData.class);
    register(AsyncMessage.Type.OrbBasicErrorBinary, OrbBasicErrorBinaryAsyncData.class);
    register(AsyncMessage.Type.GyroLimitsExceeded, GyroLimitsExceededAsyncData.class);
  }
  






  public static void register(AsyncMessage.Type dataType, Class<? extends AsyncMessage> asyncDataClass)
  {
    a.put(dataType, asyncDataClass);
  }
  




  public static synchronized AsyncMessageFactory getInstance()
  {
    if (b == null) {
      b = new AsyncMessageFactory();
    }
    return b;
  }
  
  @NonNull
  public AsyncMessage dataFromPacket(byte[] packet) { byte b1 = packet[2];
    
    Class localClass = a(b1);
    
    return a(localClass, packet);
  }
  
  @Nullable
  private Class<? extends AsyncMessage> a(byte paramByte) { AsyncMessage.Type localType = AsyncMessage.Type.fromByte(paramByte);
    return (Class)a.get(localType);
  }
  
  @NonNull
  private AsyncMessage a(@Nullable Class<? extends AsyncMessage> paramClass, @NonNull byte[] paramArrayOfByte) { if (paramClass == null) {
      return new AsyncMessage(paramArrayOfByte);
    }
    Constructor localConstructor = a(paramClass);
    if (localConstructor == null) {
      DLog.w("Constructor was null for async message, creating base AsyncMessage...");
      return new AsyncMessage(paramArrayOfByte);
    }
    AsyncMessage localAsyncMessage = a(localConstructor, paramArrayOfByte);
    if (localAsyncMessage == null) {
      DLog.w("Could not create an async message from the constructor, creating base AsyncMessage...");
      return new AsyncMessage(paramArrayOfByte);
    }
    return localAsyncMessage;
  }
  
  @Nullable
  private Constructor<? extends AsyncMessage> a(@NonNull Class<? extends AsyncMessage> paramClass) { Constructor localConstructor = null;
    try {
      localConstructor = paramClass.getDeclaredConstructor(new Class[] { [B.class });
    } catch (NoSuchMethodException localNoSuchMethodException) {
      DLog.e("Could not get declared constructor of async message class: %s. Reason: %s", new Object[] { paramClass, localNoSuchMethodException.getMessage() });
    }
    return localConstructor;
  }
  
  @Nullable
  private AsyncMessage a(@NonNull Constructor<? extends AsyncMessage> paramConstructor, @NonNull byte[] paramArrayOfByte) { AsyncMessage localAsyncMessage = null;
    try {
      localAsyncMessage = (AsyncMessage)paramConstructor.newInstance(new Object[] { paramArrayOfByte });
    } catch (InvocationTargetException localInvocationTargetException) {
      DLog.e("Could not build async message. Reason: %s", new Object[] { localInvocationTargetException.getMessage() });
    } catch (InstantiationException localInstantiationException) {
      DLog.e("Could not build async message. Reason: %s", new Object[] { localInstantiationException.getMessage() });
    } catch (IllegalAccessException localIllegalAccessException) {
      if (!paramConstructor.isAccessible()) {
        DLog.w("Constructor of async message class is not accessible. Overriding access level and retrying...");
        paramConstructor.setAccessible(true);
        try {
          localAsyncMessage = (AsyncMessage)paramConstructor.newInstance(new Object[] { paramArrayOfByte });
        } catch (Exception localException) {
          DLog.e("Could not instantiate a response after changing access level");
        }
      }
    }
    return localAsyncMessage;
  }
  
  private AsyncMessageFactory() {}
}
