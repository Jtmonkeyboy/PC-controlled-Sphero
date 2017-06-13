package com.orbotix.macro.cmd;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;





public class MacroCommandFactory
{
  private static final Map<Byte, Class<? extends MacroCommand>> a = new HashMap();
  
  static
  {
    a.put(Byte.valueOf(Delay.getCommandID()), Delay.class);
    a.put(Byte.valueOf(Emit.getCommandID()), Emit.class);
    a.put(Byte.valueOf(Calibrate.getCommandID()), Calibrate.class);
    a.put(Byte.valueOf(RGB.getCommandID()), RGB.class);
    a.put(Byte.valueOf(RGBSD2.getCommandID()), RGBSD2.class);
    a.put(Byte.valueOf(Roll.getCommandID()), Roll.class);
    a.put(Byte.valueOf(RollSD1.getCommandID()), RollSD1.class);
    a.put(Byte.valueOf(RollSD1SPD1.getCommandID()), RollSD1SPD1.class);
    a.put(Byte.valueOf(RollSD1SPD2.getCommandID()), RollSD1SPD2.class);
    a.put(Byte.valueOf(RotationRate.getCommandID()), RotationRate.class);
    a.put(Byte.valueOf(SD1.getCommandID()), SD1.class);
    a.put(Byte.valueOf(SD2.getCommandID()), SD2.class);
    a.put(Byte.valueOf(SPD1.getCommandID()), SPD1.class);
    a.put(Byte.valueOf(SPD2.getCommandID()), SPD2.class);
    a.put(Byte.valueOf(WaitUntilStop.getCommandID()), WaitUntilStop.class);
    a.put(Byte.valueOf(BackLED.getCommandID()), BackLED.class);
    a.put(Byte.valueOf(Fade.getCommandID()), Fade.class);
    a.put(Byte.valueOf(GoTo.getCommandID()), GoTo.class);
    a.put(Byte.valueOf(Stabilization.getCommandID()), Stabilization.class);
    a.put(Byte.valueOf(RawMotor.getCommandID()), RawMotor.class);
    a.put(Byte.valueOf(Sleep.getCommandID()), Sleep.class);
    a.put(Byte.valueOf(LoopStart.getCommandID()), LoopStart.class);
    a.put(Byte.valueOf(LoopEnd.getCommandID()), LoopEnd.class);
    a.put(Byte.valueOf(Comment.getCommandID()), Comment.class);
    a.put(Byte.valueOf(RotateOverTime.getCommandID()), RotateOverTime.class);
    a.put(Byte.valueOf(RotateOverTimeSD2.getCommandID()), RotateOverTimeSD2.class);
    a.put(Byte.valueOf(RotateOverTimeSD1.getCommandID()), RotateOverTimeSD1.class);
    a.put(Byte.valueOf(Stop.getCommandID()), Stop.class);
    

    a.put(Byte.valueOf((byte)29), Roll.class);
  }
  
  public static MacroCommand createFromBytes(byte[] bytes)
    throws MacroCommandCreationException
  {
    MacroCommand localMacroCommand = null;
    byte b = bytes[0];
    
    Class localClass = (Class)a.get(Byte.valueOf(b));
    
    if (localClass != null) {
      try
      {
        Constructor localConstructor = localClass.getConstructor(new Class[] { [B.class });
        localMacroCommand = (MacroCommand)localConstructor.newInstance(new Object[] { bytes });
      }
      catch (NoSuchMethodException localNoSuchMethodException1) {
        throw new MacroCommandCreationException("Failed to create command.", localNoSuchMethodException1);
      } catch (InvocationTargetException localInvocationTargetException1) {
        throw new MacroCommandCreationException("Failed to create command.", localInvocationTargetException1);
      } catch (InstantiationException localInstantiationException1) {
        throw new MacroCommandCreationException("Failed to create command.", localInstantiationException1);
      } catch (IllegalAccessException localIllegalAccessException1) {
        throw new MacroCommandCreationException("Failed to create command.", localIllegalAccessException1);
      }
    } else {
      throw new MacroCommandCreationException("Failed to create command. No command found with the ID " + b + "");
    }
    return localMacroCommand;
  }
  
  public MacroCommandFactory() {}
}
