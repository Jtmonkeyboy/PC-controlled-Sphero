//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.orbotix.macro;

import com.orbotix.async.MacroEmitMarker;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.macro.AbortMacroCommand;
import com.orbotix.macro.InitMacroExecutiveCommand;
import com.orbotix.macro.InitMacroExecutiveResponse;
import com.orbotix.macro.RunMacroCommand;
import com.orbotix.macro.SaveMacroCommand;
import com.orbotix.macro.SaveTemporaryMacroChunkCommand;
import com.orbotix.macro.SaveTemporaryMacroChunkResponse;
import com.orbotix.macro.SaveTemporaryMacroCommand;
import com.orbotix.macro.cmd.Comment;
import com.orbotix.macro.cmd.Emit;
import com.orbotix.macro.cmd.MacroCommand;
import com.orbotix.macro.cmd.MacroCommandCreationException;
import com.orbotix.macro.cmd.MacroCommandFactory;
import com.orbotix.macro.cmd.Roll;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.util.ByteArrayBuffer;

public class MacroObject implements ResponseListener, Serializable {
  public static final int CALLBACK_EMIT_ID = 2;
  private ArrayList<MacroCommand> a = new ArrayList();
  private ArrayList<MacroCommand> b = new ArrayList();
  private ArrayList<Integer> c = new ArrayList();
  private List<SaveTemporaryMacroChunkCommand> d = new ArrayList();
  private MacroObject.MacroObjectMode e;
  private volatile boolean f;
  private boolean g;
  private MacroObject.OnMacroUploadedListener h;
  private Robot i;
  private Runnable j;
  private MacroObject.OnEmitCallback k;

  public void handleResponse(DeviceResponse response, Robot robot) {
    if(this.e == MacroObject.MacroObjectMode.Chunky && (response instanceof SaveTemporaryMacroChunkResponse || response instanceof InitMacroExecutiveResponse)) {
      if(this.d.size() > 0) {
        SaveTemporaryMacroChunkCommand var3 = (SaveTemporaryMacroChunkCommand)this.d.get(0);
        this.i.sendCommand(var3);
        this.d.remove(var3);
      } else {
        if(this.h != null) {
          this.h.onMacroUploaded(this.i);
        }

        if(this.g) {
          this.i.sendCommand(new RunMacroCommand(-1));
        }
      }
    }

  }

  public void handleStringResponse(String stringResponse, Robot robot) {
  }

  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot) {
    if(asyncMessage.getClass() == MacroEmitMarker.class) {
      MacroEmitMarker var3 = (MacroEmitMarker)asyncMessage;
      if(var3.getMarkerId() == 1) {
        if(this.c.size() > 0) {
          this.c.remove(0);
        }

        this.c();
      } else if(this.j != null && var3.getMarkerId() == 2) {
        this.j.run();
      }

      if(this.k != null) {
        this.k.onEmit(var3.getMarkerId());
      }
    }

  }

  public MacroObject() {
    this.e = MacroObject.MacroObjectMode.Normal;
    this.f = false;
    this.g = true;
    this.j = null;
    this.k = null;
  }

  public MacroObject(byte[] bytes) throws MacroCommandCreationException {
    this.e = MacroObject.MacroObjectMode.Normal;
    this.f = false;
    this.g = true;
    this.j = null;
    this.k = null;

    byte[] var5;
    for(byte[] var2 = bytes; var2.length > 0 && var2[0] != 0; var2 = var5) {
      MacroCommand var3 = MacroCommandFactory.createFromBytes(var2);
      this.a.add(var3);
      int var4 = var2.length - var3.getLength();
      if(var4 <= 0) {
        break;
      }

      var5 = new byte[var4];

      for(int var6 = 0; var6 < var4; ++var6) {
        var5[var6] = var2[var6 + var3.getLength()];
      }
    }

  }

  public MacroObject.MacroObjectMode getMode() {
    return this.e;
  }

  public void setMode(MacroObject.MacroObjectMode _mode) {
    this.e = _mode;
  }

  public void setRunAfterLoading(boolean run) {
    this.g = run;
  }

  public ArrayList<MacroCommand> getCommands() {
    return this.a;
  }

  public void addCommand(MacroCommand command) {
    this.a.add(command);
  }

  public void addCommand(int index, MacroCommand cmd) {
    this.a.add(index, cmd);
  }

  public void addCommands(ArrayList<MacroCommand> addCommands) {
    Iterator var2 = addCommands.iterator();

    while(var2.hasNext()) {
      MacroCommand var3 = (MacroCommand)var2.next();
      this.a.add(var3);
    }

  }

  public MacroCommand setCommand(int index, MacroCommand cmd) {
    return (MacroCommand)this.a.set(index, cmd);
  }

  public void removeCommand(int index) {
    this.a.remove(index);
  }

  public void moveCommand(int from, int to) {
    MacroCommand var3 = (MacroCommand)this.a.get(from);
    this.a.remove(from);
    this.a.add(to, var3);
  }

  public int size() {
    return this.a.size();
  }

  public void setOnEmitCallback(Runnable callback) {
    this.j = callback;
  }

  public void setOnEmitCallback(MacroObject.OnEmitCallback callback) {
    this.k = callback;
  }

  public void setOnMacroUploadedListener(MacroObject.OnMacroUploadedListener listener) {
    this.h = listener;
  }

  public void setRobot(Robot robot) {
    this.i = robot;
  }

  public void playMacro() {
    if(this.e == MacroObject.MacroObjectMode.Normal) {
      if(this.i == null) {
        return;
      }

      this.i.sendCommand(new SaveTemporaryMacroCommand(1, this.generateMacroData()));
      this.i.sendCommand(new RunMacroCommand(-1));
    } else if(this.e == MacroObject.MacroObjectMode.CachedStreaming) {
      if(this.a.size() == 0) {
        this.a.addAll(this.b);
      } else {
        this.b.clear();
        this.b.addAll(this.a);
      }

      this.c();
      if(!this.f) {
        this.f = true;
        this.a();
        if(this.i != null) {
          this.i.sendCommand(new RunMacroCommand(-2));
        }
      }
    } else if(this.e == MacroObject.MacroObjectMode.Chunky) {
      if(this.i == null) {
        return;
      }

      if(!this.f) {
        this.a();
        this.d.clear();

        SaveTemporaryMacroChunkCommand var3;
        for(Iterator var1 = this.a.iterator(); var1.hasNext(); this.d.add(var3)) {
          MacroCommand var2 = (MacroCommand)var1.next();
          var3 = new SaveTemporaryMacroChunkCommand(0, var2.getByteRepresentation());
          if(this.d.size() == 0) {
            var3.setIsFirst(true);
          }
        }

        this.i.sendCommand(new InitMacroExecutiveCommand());
      }
    }

  }

  public void registerAsyncDataListener() {
    if(this.i != null) {
      this.i.addResponseListener(this);
    }
  }

  private synchronized void a() {
    this.i.addResponseListener(this);
  }

  public void stopMacro() {
    if(this.i != null) {
      this.i.sendCommand(new AbortMacroCommand());
      this.a.clear();
      this.c.clear();
      this.f = false;
      this.i.removeResponseListener(this);
    }
  }

  private Integer b() {
    Integer var1 = Integer.valueOf(0);

    for(Iterator var2 = this.c.iterator(); var2.hasNext(); var1 = Integer.valueOf(var1.intValue() + ((Integer)var2.next()).intValue())) {
      ;
    }

    Integer var3 = Integer.valueOf(1000 - var1.intValue());
    return var3;
  }

  private void c() {
    if(this.a.size() != 0) {
      int var1 = this.b().intValue();
      if(var1 >= 128) {
        if(var1 > 250) {
          var1 = 250;
        }

        int var2 = 0;
        int var3 = 0;
        ArrayList var4 = new ArrayList();
        ArrayList var5 = new ArrayList();
        Iterator var6 = this.a.iterator();

        MacroCommand var7;
        while(var6.hasNext()) {
          var7 = (MacroCommand)var6.next();
          if(var7 instanceof Comment) {
            var5.add(var7);
          }
        }

        this.a.removeAll(var5);
        var6 = this.a.iterator();

        while(var6.hasNext()) {
          var7 = (MacroCommand)var6.next();
          if(var3 + var7.getLength() >= var1) {
            break;
          }

          var3 += var7.getLength();
          ++var2;
          var4.add(var7);
        }

        var4.add(new Emit(1));
        var3 += 2;
        ByteArrayBuffer var9 = new ByteArrayBuffer(var3);
        Iterator var10 = var4.iterator();

        while(var10.hasNext()) {
          MacroCommand var8 = (MacroCommand)var10.next();
          var9.append(var8.getByteRepresentation(), 0, var8.getLength());
        }

        this.c.add(new Integer(var3));
        if(this.i != null) {
          this.i.sendCommand(new SaveMacroCommand(1, var9.toByteArray(), -2));
        }

        this.a.removeAll(var4);
        if(this.b().intValue() > 128 && this.a.size() > 0) {
          this.c();
        }

      }
    }
  }

  public byte[] generateMacroData() {
    ByteArrayBuffer var1 = new ByteArrayBuffer(256);
    Integer var2 = Integer.valueOf(0);

    MacroCommand var4;
    for(Iterator var3 = this.a.iterator(); var3.hasNext(); var2 = Integer.valueOf(var2.intValue() + var4.getLength())) {
      if(var2.intValue() > 248) {
        Roll var5 = new Roll(0.0F, 0, 0);
        var1.append(var5.getByteRepresentation(), 0, var5.getLength());
        break;
      }

      var4 = (MacroCommand)var3.next();
      var1.append(var4.getByteRepresentation(), 0, var4.getLength());
    }

    var1.append(0);
    return var1.toByteArray();
  }

  public byte[] getBytes() {
    int var1 = 0;

    MacroCommand var3;
    for(Iterator var2 = this.a.iterator(); var2.hasNext(); var1 += var3.getLength()) {
      var3 = (MacroCommand)var2.next();
    }

    ByteArrayBuffer var5 = new ByteArrayBuffer(var1 + 1);
    Iterator var6 = this.a.iterator();

    while(var6.hasNext()) {
      MacroCommand var4 = (MacroCommand)var6.next();
      var5.append(var4.getByteRepresentation(), 0, var4.getLength());
    }

    var5.append(0);
    return var5.toByteArray();
  }

  public boolean isRunning() {
    return this.f;
  }

  public interface OnMacroUploadedListener {
    void onMacroUploaded(Robot var1);
  }

  public interface OnEmitCallback {
    void onEmit(int var1);
  }

  public static enum MacroObjectMode {
    Normal,
    CachedStreaming,
    Chunky;

    private MacroObjectMode() {
    }
  }
}
