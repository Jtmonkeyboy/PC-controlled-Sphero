//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.macro.cmd;

import com.orbotix.macro.cmd.BackLED;
import com.orbotix.macro.cmd.Calibrate;
import com.orbotix.macro.cmd.Comment;
import com.orbotix.macro.cmd.Delay;
import com.orbotix.macro.cmd.Emit;
import com.orbotix.macro.cmd.Fade;
import com.orbotix.macro.cmd.GoTo;
import com.orbotix.macro.cmd.LoopEnd;
import com.orbotix.macro.cmd.LoopStart;
import com.orbotix.macro.cmd.MacroCommand;
import com.orbotix.macro.cmd.MacroCommandCreationException;
import com.orbotix.macro.cmd.RGB;
import com.orbotix.macro.cmd.RGBSD2;
import com.orbotix.macro.cmd.RawMotor;
import com.orbotix.macro.cmd.Roll;
import com.orbotix.macro.cmd.RollSD1;
import com.orbotix.macro.cmd.RollSD1SPD1;
import com.orbotix.macro.cmd.RollSD1SPD2;
import com.orbotix.macro.cmd.RotateOverTime;
import com.orbotix.macro.cmd.RotateOverTimeSD1;
import com.orbotix.macro.cmd.RotateOverTimeSD2;
import com.orbotix.macro.cmd.RotationRate;
import com.orbotix.macro.cmd.SD1;
import com.orbotix.macro.cmd.SD2;
import com.orbotix.macro.cmd.SPD1;
import com.orbotix.macro.cmd.SPD2;
import com.orbotix.macro.cmd.Sleep;
import com.orbotix.macro.cmd.Stabilization;
import com.orbotix.macro.cmd.Stop;
import com.orbotix.macro.cmd.WaitUntilStop;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MacroCommandFactory {
  private static final Map<Byte, Class<? extends MacroCommand>> a = new HashMap();

  public MacroCommandFactory() {
  }

  public static MacroCommand createFromBytes(byte[] bytes) throws MacroCommandCreationException {
    MacroCommand var1 = null;
    byte var2 = bytes[0];
    Class var3 = (Class)a.get(Byte.valueOf(var2));
    if(var3 != null) {
      try {
        Constructor var4 = var3.getConstructor(new Class[]{byte[].class});
        var1 = (MacroCommand)var4.newInstance(new Object[]{bytes});
        return var1;
      } catch (NoSuchMethodException var5) {
        throw new MacroCommandCreationException("Failed to create command.", var5);
      } catch (InvocationTargetException var6) {
        throw new MacroCommandCreationException("Failed to create command.", var6);
      } catch (InstantiationException var7) {
        throw new MacroCommandCreationException("Failed to create command.", var7);
      } catch (IllegalAccessException var8) {
        throw new MacroCommandCreationException("Failed to create command.", var8);
      }
    } else {
      throw new MacroCommandCreationException("Failed to create command. No command found with the ID " + var2 + "");
    }
  }

  static {
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
    a.put(Byte.valueOf(29), Roll.class);
  }
}
