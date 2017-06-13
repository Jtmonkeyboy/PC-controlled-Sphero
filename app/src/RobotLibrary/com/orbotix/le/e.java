package com.orbotix.le;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Handler;
import com.orbotix.command.SleepCommand;
import com.orbotix.common.DLog;
import com.orbotix.common.internal.AdHocCommand;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.AsyncMessage.Type;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.MainProcessorSession.MainProcessorSessionDelegate;
import com.orbotix.common.internal.MainProcessorState;
import com.orbotix.common.internal.RadioConnectionState;
import com.orbotix.common.internal.RadioLink;
import com.orbotix.common.utilities.binary.ByteUtil;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;







class e
  extends RadioLink
  implements MainProcessorSession.MainProcessorSessionDelegate, LeLinkInterface
{
  private static final boolean a = false;
  private static final boolean b = false;
  private static final String c = "011i3";
  private static final int d = 2;
  private static final int e = 0;
  private static final int f = 133;
  private BluetoothGattCharacteristic g;
  private BluetoothGattCharacteristic h;
  private BluetoothGattCharacteristic i;
  private BluetoothGattCharacteristic j;
  private BluetoothGattCharacteristic k;
  private BluetoothGattCharacteristic l;
  private BluetoothGattCharacteristic m;
  private BluetoothGatt n;
  private int o;
  private Integer p = Integer.valueOf(-98);
  
  private ExecutorService q = Executors.newSingleThreadExecutor();
  private final a r;
  private final LeLinkRadioACKListener s;
  private String t;
  private String u;
  private boolean v = false;
  private RobotRadioDescriptor w;
  private boolean x = false;
  private final Handler y;
  private boolean z = false;
  private boolean A = true;
  
  public e(BluetoothDevice paramBluetoothDevice, RobotLE paramRobotLE, RobotRadioDescriptor paramRobotRadioDescriptor, Handler paramHandler) {
    super(paramBluetoothDevice, paramRobotLE, paramRobotLE);
    w = paramRobotRadioDescriptor;
    s = paramRobotLE;
    DLog.i(String.format("Creating LeLink for %s", new Object[] { paramBluetoothDevice.getAddress() }));
    
    a.b local1 = new a.b()
    {
      public void a(final BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic, final byte[] paramAnonymousArrayOfByte, final int paramAnonymousInt) {
        e.b(e.this).execute(new Runnable()
        {
          public void run() {
            if ((e.a(e.this) == null) || (paramAnonymousBluetoothGattCharacteristic == null)) {
              return;
            }
            



            paramAnonymousBluetoothGattCharacteristic.setWriteType(paramAnonymousInt);
            



            paramAnonymousBluetoothGattCharacteristic.setValue(paramAnonymousArrayOfByte);
            e.a(e.this, paramAnonymousInt == 2);
            e.a(e.this).writeCharacteristic(paramAnonymousBluetoothGattCharacteristic);
          }
        });
      }
    };
    r = new a(local1);
    y = paramHandler;
  }
  
  public void open()
  {
    a(false);
  }
  
  void a(final boolean paramBoolean) {
    super.open();
    if ((BluetoothAdapter.getDefaultAdapter().getState() == 10) || (BluetoothAdapter.getDefaultAdapter().getState() == 13)) {
      return;
    }
    if ((isConnected()) && (a())) {
      DLog.v("Radio link is already connected, waking main");
      c();
      return;
    }
    setMpState(MainProcessorState.Offline);
    handleConnectionInitiated();
    
    y.post(new Runnable()
    {
      public void run() {
        e.d(e.this).connectGatt(DiscoveryAgentLE.getInstance().getContext(), paramBoolean, e.c(e.this));
      }
    });
  }
  
  public void b(boolean paramBoolean) {
    A = paramBoolean;
  }
  
  public boolean a() {
    return A;
  }
  
  public void close()
  {
    handleConnectionEnding();
    if (n != null) {
      DLog.i("gatt.disconnect() requested");
      n.disconnect();
    }
  }
  
  public String getRadioFirmwareRevision()
  {
    return u;
  }
  


  public void a(short paramShort)
  {
    if ((k == null) || (n == null)) {
      DLog.e("TX power characteristic or gatt is null and cannot write power");
      return;
    }
    DLog.i("Writing TX Power: " + paramShort);
    byte[] arrayOfByte = { (byte)paramShort };
    r.a(k, arrayOfByte);
  }
  
  public void b() {
    if (getMpState() == MainProcessorState.InMainApp) {
      sendCommand(new SleepCommand(65535, 0));
    }
    else {
      r.a(m, "011i3".getBytes());
    }
  }
  
  protected void sendCommandInternal(DeviceCommand command)
  {
    r.a(g, command);
  }
  
  private void a(BluetoothGatt paramBluetoothGatt) {
    try {
      Method localMethod = paramBluetoothGatt.getClass().getMethod("refresh", new Class[0]);
      if (localMethod != null) {
        localMethod.invoke(paramBluetoothGatt, new Object[0]);
        DLog.v("Refresh completed successfully!");
      }
    }
    catch (Exception localException) {
      DLog.e("An exception occurred while refreshing device");
    }
    n.discoverServices();
  }
  
  private void g() {
    r.a(i, "011i3".getBytes());
  }
  
  protected void c() {
    byte[] arrayOfByte = { 1 };
    setMpState(MainProcessorState.PowerOnRequested);
    r.a(j, arrayOfByte);
  }
  
  private void h() {
    DLog.v("Enable Notify on Response Characteristic");
    if (n.setCharacteristicNotification(h, true)) {
      b(n);
    }
  }
  
  private void i() {
    setMpState(MainProcessorState.PoweredOn);
    try {
      Thread.sleep(100L);
    } catch (InterruptedException localInterruptedException) {
      localInterruptedException.printStackTrace();
    }
    sendCommand(new AdHocCommand(1, 4));
  }
  
  private void b(BluetoothGatt paramBluetoothGatt) {
    BluetoothGattService localBluetoothGattService = paramBluetoothGatt.getService(w.getUUIDDeviceInformationService());
    BluetoothGattCharacteristic localBluetoothGattCharacteristic = localBluetoothGattService.getCharacteristic(w.getUUIDModelNumberCharacteristic());
    paramBluetoothGatt.readCharacteristic(localBluetoothGattCharacteristic);
  }
  



  private BluetoothGattCallback B = new BluetoothGattCallback()
  {
    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState)
    {
      DLog.v(getName() + " Connection State Changed to " + newState + " status: " + status);
      if (newState == 2) {
        if (e.a(e.this) != null) {
          e.a(e.this).disconnect();
          e.a(e.this).close();
        }
        e.a(e.this, gatt);
        e.e(e.this).a();
        e.a(e.this).discoverServices();
      } else if (newState == 0) {
        int i = 0;
        if (status == 133) {
          DLog.e("GATT could not be attached. Retrying.");
          i = 1;
        }
        else {
          e.a(e.this).close();
        }
        if (getRfState() == RadioConnectionState.Connecting) {
          DLog.v("Failed to connect - reconnecting " + getName());
          e.f(e.this);
          i = 1;
        }
        else if (getRfState() == RadioConnectionState.Connected) {
          DLog.v("Connection Dropped - reconnecting " + getName());
          i = 1;
        }
        else if (e.g(e.this)) {
          DLog.v("Maintain in background is set - reconnecting " + getName());
          i = 1;
        }
        else {
          DLog.v("Not in a state for reconnection");
        }
        
        e.h(e.this);
        if (i != 0) {
          open();
        }
      }
    }
    
    public void onServicesDiscovered(BluetoothGatt gatt, int status)
    {
      if (!e.a(e.this).equals(gatt)) {
        DLog.e("Mismatch GATT - see vves");
        return;
      }
      DLog.v(String.format("onServicesDiscovered count=%d status=%d", new Object[] { Integer.valueOf(gatt.getServices().size()), Integer.valueOf(status) }));
      
      BluetoothGattService localBluetoothGattService1 = gatt.getService(e.i(e.this).getUUIDRadioService());
      if ((!a) && (localBluetoothGattService1 == null)) throw new AssertionError();
      e.a(e.this, localBluetoothGattService1.getCharacteristic(e.i(e.this).getUUIDTxPowerCharacteristic()));
      e.b(e.this, localBluetoothGattService1.getCharacteristic(e.i(e.this).getUUIDAntiDOSCharacteristic()));
      e.c(e.this, localBluetoothGattService1.getCharacteristic(e.i(e.this).getUUIDAntiDOSTimeoutCharactertistic()));
      e.d(e.this, localBluetoothGattService1.getCharacteristic(e.i(e.this).getUUIDDeepSleepCharacteristic()));
      

      e.j(e.this);
      
      BluetoothGattService localBluetoothGattService2 = gatt.getService(e.i(e.this).getUUIDRobotService());
      if ((!a) && (localBluetoothGattService2 == null)) throw new AssertionError();
      e.e(e.this, localBluetoothGattService2.getCharacteristic(e.i(e.this).getUUIDResponseCharacteristic()));
      e.f(e.this, localBluetoothGattService1.getCharacteristic(e.i(e.this).getUUIDWakecharacteristic()));
      e.g(e.this, localBluetoothGattService2.getCharacteristic(e.i(e.this).getUUIDControlCharacteristic()));
      
      e.k(e.this);
    }
    
    public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status)
    {
      super.onCharacteristicWrite(gatt, characteristic, status);
      if (e.l(e.this)) {
        e.m(e.this).didACK();
        e.a(e.this, false);
        if (e.n(e.this).equals(characteristic)) {
          e.o(e.this);
        }
      }
      
      if (e.p(e.this).equals(characteristic)) {
        DLog.v("Wrote wake");
        e.q(e.this);
      }
      

      if (e.r(e.this).equals(characteristic)) {
        DLog.v("TX Power Set");
        if (getMpState() == MainProcessorState.Offline) {
          c();
        }
      }
      
      if (e.s(e.this).equals(characteristic)) {
        DLog.v("DOS ACK OK");
        a((short)7);
      }
      
      e.e(e.this).b();
    }
    


    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic)
    {
      super.onCharacteristicChanged(gatt, characteristic);
      if (e.t(e.this).equals(characteristic)) {
        byte[] arrayOfByte = characteristic.getValue();
        


        if ((arrayOfByte.length == 1) && (!e.u(e.this))) {
          DLog.w("");
          DLog.w("WARNING: Single byte value will be dropped %s possibly breaking all the things!", new Object[] { ByteUtil.byteArrayToHex(arrayOfByte) });
          DLog.w("");
        }
        else {
          e.a(e.this, arrayOfByte);
        }
        e.b(e.this, true);
      }
    }
    
    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status)
    {
      super.onCharacteristicRead(gatt, characteristic, status);
      

      if (characteristic.getUuid().equals(e.i(e.this).getUUIDModelNumberCharacteristic())) {
        e.a(e.this, characteristic.getStringValue(0));
        gatt.readCharacteristic(characteristic.getService().getCharacteristic(e.i(e.this).getUUIDRadioFirmwareVersionCharacteristic()));
      } else if (characteristic.getUuid().equals(e.i(e.this).getUUIDRadioFirmwareVersionCharacteristic())) {
        e.b(e.this, characteristic.getStringValue(0));
        gatt.readCharacteristic(characteristic.getService().getCharacteristic(e.i(e.this).getUUIDSerialNumberCharacteristic()));
      } else if (characteristic.getUuid().equals(e.i(e.this).getUUIDSerialNumberCharacteristic())) {
        String str = characteristic.getStringValue(0);
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
          DLog.e("Detected garbage serial number, refreshing device...");
          e.b(e.this, gatt);
          return;
        }
        e.c(e.this, characteristic.getStringValue(0));
        DLog.v("Read all aux characteristics, jumping to main");
        e.v(e.this);
      } else {
        DLog.w("Unhandled characteristic read: " + characteristic.getUuid().toString());
      }
    }
  };
  




  public void handleAsyncMessageCreated(AsyncMessage asyncMessage)
  {
    if (asyncMessage.getType() == AsyncMessage.Type.DidSleepAsyncMessage) {
      if (v) {
        DLog.v("Skipping async sleep disconnect: maintain background connection is set");
      }
      else {
        close();
      }
    }
    super.handleAsyncMessageCreated(asyncMessage);
  }
  


  public String toString()
  {
    return "<LeLink " + getAddress() + " rf" + getRfState() + " mp" + getMpState();
  }
  


  public void c(boolean paramBoolean)
  {
    byte[] arrayOfByte = { 10 };
    if (paramBoolean) {
      arrayOfByte[0] = 0;
    }
    DLog.w("Setting Developer Mode");
    r.a(l, arrayOfByte);
  }
  
  public Integer d() {
    return p;
  }
  
  public void a(Integer paramInteger) {
    if (paramInteger.intValue() > -5) {
      return;
    }
    
    if (p == null) {
      p = paramInteger;
      o = p.intValue();
    }
    p = Integer.valueOf((o + p.intValue() + paramInteger.intValue()) / 3);
    o = paramInteger.intValue();
  }
  
  public void d(boolean paramBoolean) {
    v = paramBoolean;
  }
  
  public boolean e() {
    return v;
  }
  
  public void sendRaw(byte[] data) {
    r.a(g, data);
  }
  
  public boolean f() {
    return v;
  }
  
  protected void handleSleepResponse() {}
}
