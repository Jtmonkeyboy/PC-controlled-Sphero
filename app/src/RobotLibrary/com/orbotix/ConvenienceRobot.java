package com.orbotix;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.orbotix.classic.RobotClassic;
import com.orbotix.command.BackLEDOutputCommand;
import com.orbotix.command.RGBLEDOutputCommand;
import com.orbotix.command.RawMotorCommand;
import com.orbotix.command.RawMotorCommand.MotorMode;
import com.orbotix.command.RollCommand;
import com.orbotix.command.RollCommand.State;
import com.orbotix.command.SetHeadingCommand;
import com.orbotix.command.StabilizationCommand;
import com.orbotix.command.VersioningResponse;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.RobotVersion;
import com.orbotix.common.utilities.math.Value;
import com.orbotix.le.RobotLE;
import com.orbotix.macro.AbortMacroCommand;
import com.orbotix.macro.MacroObject;
import com.orbotix.macro.RunMacroCommand;
import com.orbotix.macro.SaveMacroCommand;
import com.orbotix.macro.SaveTemporaryMacroCommand;
import com.orbotix.orbbasic.OrbBasicAbortProgramCommand;
import com.orbotix.orbbasic.OrbBasicAppendFragmentCommand;
import com.orbotix.orbbasic.OrbBasicEraseStorageCommand;
import com.orbotix.orbbasic.OrbBasicExecuteProgramCommand;
import com.orbotix.subsystem.SensorControl;
import com.orbotix.subsystem.SensorControl.StreamingRate;

public class ConvenienceRobot implements Driveable, ResponseListener
{
  protected Robot _robot;
  private float a;
  private SensorControl b;
  private RobotVersion c;
  private MacroObject d;
  
  public ConvenienceRobot(Robot robot)
  {
    _robot = robot;
    _robot.addResponseListener(this);
    b = new SensorControl(this);
  }
  
  public void disconnect() {
    if (_robot == null) {
      return;
    }
    
    if ((_robot instanceof RobotClassic)) {
      _robot.disconnect();
    } else if ((_robot instanceof RobotLE)) {
      _robot.sleep();
    }
  }
  
  public Robot getRobot() {
    return _robot;
  }
  
  public boolean isConnected() { return _robot.isConnected(); }
  
  public void sendCommand(DeviceCommand command) {
    _robot.sendCommand(command);
  }
  
  public void drive(float heading, float velocity) {
    drive(heading, velocity, false);
  }
  
  public void drive(float heading, float velocity, boolean reverse) {
    RollCommand localRollCommand = new RollCommand(heading, velocity, RollCommand.State.GO);
    
    a = heading;
    _robot.sendCommand(localRollCommand);
  }
  
  public void setRawMotors(RawMotorCommand.MotorMode leftMode, int leftPower, RawMotorCommand.MotorMode rightMode, int rightPower) {
    _robot.sendCommand(new RawMotorCommand(leftMode, leftPower, rightMode, rightPower));
  }
  
  public void rotate(float heading) {
    a = heading;
    RollCommand localRollCommand = new RollCommand(heading, 0.0F, RollCommand.State.CALIBRATE);
    _robot.sendCommand(localRollCommand);
  }
  
  public void stop() {
    RollCommand localRollCommand = new RollCommand(a, 0.0F, RollCommand.State.STOP);
    _robot.sendCommand(localRollCommand);
  }
  
  public void setLed(float red, float green, float blue) {
    a(red, green, blue, false);
  }
  
  public void setLedDefault(float red, float green, float blue) {
    a(red, green, blue, true);
  }
  
  private void a(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean) {
    int i = (int)(Value.clamp(paramFloat1, 0.0F, 1.0F) * 255.0F);
    int j = (int)(Value.clamp(paramFloat2, 0.0F, 1.0F) * 255.0F);
    int k = (int)(Value.clamp(paramFloat3, 0.0F, 1.0F) * 255.0F);
    
    RGBLEDOutputCommand localRGBLEDOutputCommand = new RGBLEDOutputCommand(i, j, k, paramBoolean);
    _robot.sendCommand(localRGBLEDOutputCommand);
  }
  
  public void setBackLedBrightness(float brightness) {
    _robot.sendCommand(new BackLEDOutputCommand(brightness));
  }
  
  public void setZeroHeading() {
    _robot.sendCommand(new SetHeadingCommand(0.0F));
  }
  
  public void enableStabilization(boolean on) {
    _robot.sendCommand(new StabilizationCommand(on));
  }
  
  public void sleep() {
    _robot.sleep();
  }
  
  public void calibrating(boolean calibrating) {
    if (calibrating) {
      _robot.sendCommand(new BackLEDOutputCommand(1.0F));
    } else {
      _robot.sendCommand(new BackLEDOutputCommand(0.0F));
      _robot.sendCommand(new SetHeadingCommand(0.0F));
    }
  }
  
  public void enableCollisions(boolean enable) {
    b.enableCollisions(enable);
  }
  
  public void enableLocator(boolean enable) {
    b.enableLocator(enable);
  }
  
  public void enableSensors(long flags, SensorControl.StreamingRate rate) {
    b.enableSensors(flags, rate);
  }
  
  public void disableSensors() {
    b.disableSensors();
  }
  
  public void runMacroAtId(int id) {
    _robot.sendCommand(new RunMacroCommand((byte)id));
  }
  
  public void abortMacro() {
    _robot.sendCommand(new AbortMacroCommand());
  }
  
  public void macroSaveTemporary(byte[] macroData) {
    _robot.sendCommand(new SaveTemporaryMacroCommand((byte)1, macroData));
  }
  
  public void resetOrbBasicMemory() {
    _robot.sendCommand(new OrbBasicEraseStorageCommand(true));
  }
  
  public void appendOrbBasicProgram(byte[] data) {
    _robot.sendCommand(new OrbBasicAppendFragmentCommand(true, data));
  }
  
  public void executeOrbBasicProgramAtLine(int line) {
    _robot.sendCommand(new OrbBasicExecuteProgramCommand(true, line));
  }
  
  public void abortOrbBasic() {
    _robot.sendCommand(new OrbBasicAbortProgramCommand());
  }
  
  public void addResponseListener(ResponseListener listener) {
    _robot.addResponseListener(listener);
  }
  
  public void removeResponseListener(ResponseListener listener) {
    _robot.removeResponseListener(listener);
  }
  
  public SensorControl getSensorControl() {
    return b;
  }
  
  public RobotVersion getLastVersioning() {
    return c;
  }
  
  public void handleResponse(DeviceResponse response, Robot robot)
  {
    if ((response instanceof VersioningResponse)) {
      c = ((VersioningResponse)response).getVersion();
    }
  }
  


  public void handleStringResponse(String stringResponse, Robot robot) {}
  


  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot) {}
  

  public float getLastHeading()
  {
    return a;
  }
  
  public void playMacro(@NonNull MacroObject macro) {
    loadMacro(macro);
    d.playMacro();
  }
  
  public void playMacro() {
    if (d != null) {
      d.playMacro();
    }
  }
  
  public void loadMacro(@NonNull MacroObject macro) {
    d = macro;
    d.setRobot(getRobot());
  }
  
  @Nullable
  public MacroObject getMacro() {
    return d;
  }
  
  public void loadMacroToId(MacroObject macro, @IntRange(from=32L, to=253L) int id) {
    _robot.sendCommand(new SaveMacroCommand((byte)1, macro.getBytes(), (byte)id));
  }
}
