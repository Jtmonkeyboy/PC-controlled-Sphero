package com.orbotix.common.internal;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import com.orbotix.common.DLog;
import com.orbotix.common.stat.StatForPacketFactory;
import com.orbotix.common.stat.StatRecorder;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public abstract class RadioLink
  implements MainProcessorSession.MainProcessorSessionDelegate
{
  private BluetoothDevice a;
  private MainProcessorState b = MainProcessorState.Offline;
  private RadioConnectionState c = RadioConnectionState.Offline;
  
  private RadioLinkSessionListener d;
  
  private RadioLinkStateChangedListener e;
  
  private MainProcessorSession f;
  
  private CommandQueueRunnable g;
  private DeviceCommand h;
  private DeviceCommand i;
  private String j;
  private Date k;
  private List<Long> l = new LinkedList();
  private Long m = Long.valueOf(0L);
  private final Object n = new Object();
  private final Object o = new Object();
  private final LinkedList<Date> p = new LinkedList();
  
  private boolean q;
  
  protected RadioLink(@NonNull BluetoothDevice device, @NonNull RadioLinkSessionListener linkSessionListener, @NonNull RadioLinkStateChangedListener stateChangedListener)
  {
    a = device;
    d = linkSessionListener;
    e = stateChangedListener;
    f = new MainProcessorSession(this);
  }
  
  public void open() {
    q = false;
    a(); }
  
  public abstract void close();
  
  public abstract String getRadioFirmwareRevision();
  
  protected abstract void sendCommandInternal(DeviceCommand paramDeviceCommand);
  
  protected abstract void handleSleepResponse();
  public final void sendCommand(@NonNull DeviceCommand command) { if (a(command)) {
      if (!a(command)) {}



    }
    else if ((b(command)) && 
      (b(command)))
    {
      return;
    }
    
    byte[] arrayOfByte = f.packetForCommand(command);
    
    StatRecorder.getInstance().recordStat(
      StatForPacketFactory.statForPacketAndIdentifier(arrayOfByte, getSerialNumber()));
    
    g.enqueue(command);
    k = new Date();
    
    c(command);
  }
  
  protected final void processRawData(byte[] data) {
    f.processRawData(data);
  }
  
  protected void handleConnectionFailed() {
    c = RadioConnectionState.Offline;
    e.handleLinkFailedToConnect();
  }
  
  protected void handleConnectionSucceeded() {
    c = RadioConnectionState.Connected;
    e.handleLinkDidConnect();
  }
  
  protected void handleConnectionInitiated() {
    c = RadioConnectionState.Connecting;
    e.handleLinkConnectionInitiated();
  }
  
  protected void handleConnectionClosed() {
    c = RadioConnectionState.Offline;
    e.handleLinkDidDisconnect();
  }
  
  protected void handleConnectionEnding() {
    c = RadioConnectionState.Disconnecting;
  }
  
  protected void handleCommandWritten() {
    b();
    synchronized (o) {
      h = null;
    }
    synchronized (n) {
      i = null;
    }
  }
  
  private void a() {
    if (g == null) {
      g = new CommandQueueRunnable(new CommandQueueRunnable.SendExecutor()
      {
        public void sendCommand(DeviceCommand command) {
          sendCommandInternal(command);
        }
      });
      Thread localThread = new Thread(g);
      localThread.start();
    }
  }
  


  private boolean a(DeviceCommand paramDeviceCommand)
  {
    int i1;
    

    if ((paramDeviceCommand instanceof ImmutableCommand)) {
      i1 = paramDeviceCommand.getPacket()[9] == 0 ? 1 : 0;
    }
    else {
      i1 = paramDeviceCommand.getData()[3] == 0 ? 1 : 0;
    }
    if (i1 != 0) {
      DLog.v("Forcing stop command");
      return false;
    }
    synchronized (o) {
      if (h == null) {
        h = paramDeviceCommand;
        return false;
      }
      h = paramDeviceCommand;
      return true;
    }
  }
  





  private boolean b(DeviceCommand paramDeviceCommand)
  {
    synchronized (n) {
      if (i == null) {
        i = paramDeviceCommand;
        return false;
      }
      i = paramDeviceCommand;
      return true;
    }
  }
  
  private void c(DeviceCommand paramDeviceCommand)
  {
    int i1 = paramDeviceCommand.getDeviceId();
    int i2 = paramDeviceCommand.getCommandId();
    
    if (i1 == DeviceId.CORE.getValue()) {
      if (i2 == CoreCommandId.JUMP_TO_BOOTLOADER.getValue()) {
        b = MainProcessorState.JumpToBootloaderRequested;
      }
      else if (i2 == CoreCommandId.SLEEP.getValue()) {
        b = MainProcessorState.OfflineRequested;
      }
    }
    else if ((i1 == DeviceId.BOOTLOADER.getValue()) && 
      (i2 == BootloaderCommandId.LEAVE_BOOTLOADER.getValue())) {
      b = MainProcessorState.JumpToMainAppRequested;
    }
  }
  
  private void b()
  {
    if (k == null) {
      return;
    }
    if (l.size() > 20) {
      l.remove(0);
    }
    Long localLong1 = Long.valueOf((System.currentTimeMillis() - k.getTime()) / 2L);
    l.add(localLong1);
    long l1 = 0L;
    for (Long localLong2 : l) {
      l1 += localLong2.longValue();
    }
    m = Long.valueOf(l1 / l.size());
  }
  
  private boolean a(DeviceMessage paramDeviceMessage) {
    return (paramDeviceMessage.getDeviceId() == 2) && (paramDeviceMessage.getCommandId() == 48);
  }
  
  private boolean b(DeviceMessage paramDeviceMessage) {
    return (paramDeviceMessage.getDeviceId() == 2) && (paramDeviceMessage.getCommandId() == 32);
  }
  



  public void handleResponseCreated(DeviceResponse response)
  {
    if ((1 == response.getDeviceId()) && (4 == response.getCommandId())) {
      b = MainProcessorState.InMainApp;
      if (!q) {
        e.handleLinkDidWake();
        q = true;
      }
    }
    if ((response.getDeviceId() == DeviceId.CORE.getValue()) && (response.getCommandId() == CoreCommandId.JUMP_TO_BOOTLOADER.getValue())) {
      b = MainProcessorState.InBootloader;
    }
    
    if ((response.getDeviceId() == DeviceId.CORE.getValue()) && (response.getCommandId() == CoreCommandId.SLEEP.getValue())) {
      handleSleepResponse();
    }
    

    d.handleResponseReceived(response);
  }
  
  public void handleAsyncMessageCreated(AsyncMessage message)
  {
    if (message.getType() == AsyncMessage.Type.DidSleepAsyncMessage) {
      e.handleLinkDidSleep();
    }
    if ((b.getValue() >= MainProcessorState.InBootloader.getValue()) && 
      (b.getValue() <= MainProcessorState.InMainApp.getValue())) {
      d.handleAsyncMessageReceived(message);
    }
  }
  
  public void handleStringResponseCreated(String stringResponse)
  {
    d.handleStringResponseReceived(stringResponse);
  }
  


  public final String getName()
  {
    return a.getName();
  }
  
  public final String getAddress() {
    return a.getAddress();
  }
  
  public final boolean isConnecting() {
    return c == RadioConnectionState.Connecting;
  }
  
  public final boolean isConnected() {
    return c.ordinal() >= RadioConnectionState.Connected.ordinal();
  }
  
  public final Long getAckLatency() {
    return m;
  }
  
  protected final BluetoothDevice getDevice() { return a; }
  
  public final RadioConnectionState getRfState() {
    return c;
  }
  
  protected final void setMpState(MainProcessorState state) {
    b = state;
  }
  
  public final MainProcessorState getMpState() {
    return b;
  }
  
  protected final void setSerialNumber(String serialNumber) {
    j = serialNumber;
  }
  
  public final String getSerialNumber() {
    if (j == null) {
      return a.getAddress();
    }
    return j;
  }
}
