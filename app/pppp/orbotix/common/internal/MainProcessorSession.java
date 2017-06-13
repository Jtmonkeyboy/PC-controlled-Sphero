package com.orbotix.common.internal;

import android.util.SparseArray;
import com.orbotix.async.AsyncMessageFactory;
import com.orbotix.command.ResponseFactory;
import com.orbotix.common.DLog;
import com.orbotix.common.utilities.binary.ByteUtil;


public class MainProcessorSession
{
  private static final String a = "OBX-MPS";
  private static final int b = 5;
  private static final int c = 6;
  private static final byte d = -1;
  private static final byte e = -1;
  private static final byte f = -2;
  private static final byte g = -4;
  private static final int h = 5;
  private final SparseArray<DeviceCommand> i = new SparseArray();
  private final Object j = new Object();
  
  private static byte k = -1;
  
  private byte l;
  private byte[] m = new byte[0];
  private MainProcessorSessionDelegate n;
  private AsyncMessageFactory o;
  private ResponseFactory p;
  
  public MainProcessorSession(MainProcessorSessionDelegate sessionDelegate) {
    n = sessionDelegate;
    o = AsyncMessageFactory.getInstance();
    p = ResponseFactory.getInstance();
    reset();
  }
  
  public void reset() {
    synchronized (i) {
      i.clear();
    }
    m = new byte[0];
    l = 0;
  }
  
  public void processRawData(byte[] inval) {
    synchronized (this) {
      m = ByteUtil.concatenateByteArrays(m, inval);
      byte[] arrayOfByte1 = (byte[])m.clone();
      while (arrayOfByte1.length >= 1) {
        if (arrayOfByte1[0] == -1)
        {
          if (arrayOfByte1.length < 2) break;
          int i2; Object localObject1; if (arrayOfByte1[1] == -1)
          {

            if (arrayOfByte1.length < 5) break;
            byte b1 = arrayOfByte1[3];
            i2 = arrayOfByte1[4];
            int i3 = (i2 & 0xFF) + 5;
            if (arrayOfByte1.length < i3) break;
            localObject1 = ByteUtil.readBytesFromFront(arrayOfByte1, i3);
            arrayOfByte1 = ByteUtil.removeBytesFromFront(arrayOfByte1, i3);
            
            DeviceCommand localDeviceCommand = popCommandForSequence(b1);
            DeviceResponse localDeviceResponse = p.responseFromRawPacket((byte[])localObject1, localDeviceCommand);
            n.handleResponseCreated(localDeviceResponse);


          }
          else if (arrayOfByte1[1] == -2)
          {

            if (arrayOfByte1.length < 5) break;
            int i1 = (arrayOfByte1[3] & 0xFF) << 8;
            i1 += (arrayOfByte1[4] & 0xFF);
            i2 = i1 + 5;
            if (i2 > 2048)
            {
              DLog.e("buffer issue.  removing -------------------------------------------------------------------");
              arrayOfByte1 = new byte[0];
            }
            if (arrayOfByte1.length < i2) break;
            byte[] arrayOfByte2 = ByteUtil.readBytesFromFront(arrayOfByte1, i2);
            localObject1 = o.dataFromPacket(arrayOfByte2);
            n.handleAsyncMessageCreated((AsyncMessage)localObject1);
            arrayOfByte1 = ByteUtil.removeBytesFromFront(arrayOfByte1, i2);

          }
          else
          {
            DLog.e("Consuming extra FF!");
            arrayOfByte1 = ByteUtil.removeBytesFromFront(arrayOfByte1, 1);
          }
        } else {
          StringBuilder localStringBuilder = new StringBuilder();
          while ((arrayOfByte1.length >= 1) && (arrayOfByte1[0] != -1)) {
            localStringBuilder.append((char)arrayOfByte1[0]);
            arrayOfByte1 = ByteUtil.removeBytesFromFront(arrayOfByte1, 1);
          }
          if (localStringBuilder.length() > 0) {
            n.handleStringResponseCreated(localStringBuilder.toString());
          }
        }
      }
      m = arrayOfByte1;
    }
  }
  
  public byte[] packetForCommand(DeviceCommand command) {
    if (command == null) {
      DLog.w("Handed a bad Command...");
      return null;
    }
    
    byte[] arrayOfByte = a(command);
    if (arrayOfByte == null) {
      DLog.w("Retrieved a bad Packet...");
      return null;
    }
    

    synchronized (i) {
      if (command.isResponseRequested()) {
        i.put(command.getSequenceNumber(), command);
      }
    }
    
    return arrayOfByte;
  }
  
  public DeviceCommand popCommandForSequence(byte sequenceNum)
  {
    synchronized (i) {
      DeviceCommand localDeviceCommand = (DeviceCommand)i.get(sequenceNum);
      if (localDeviceCommand == null) {
        DLog.w("Command for sequence %02X could not be found!", new Object[] { Byte.valueOf(sequenceNum) });
      }
      i.delete(sequenceNum);
      return localDeviceCommand;
    }
  }
  






  private byte[] a(DeviceCommand paramDeviceCommand)
  {
    if ((paramDeviceCommand instanceof ImmutableCommand)) {
      return paramDeviceCommand.getPacket();
    }
    
    byte[] arrayOfByte1 = paramDeviceCommand.getData();
    



    int i1 = paramDeviceCommand.getDLEN();
    int i2 = arrayOfByte1.length + 6 + 1;
    
    if (i2 < 6) {
      DLog.w("Retrieved Packet has fewer then 6 bytes...");
      return null;
    }
    
    byte[] arrayOfByte2 = new byte[i2];
    int i3 = 0;
    
    arrayOfByte2[0] = -1;
    arrayOfByte2[1] = -4;
    if (paramDeviceCommand.isResponseRequested()) {
      arrayOfByte2[1] = ((byte)(0x1 | arrayOfByte2[1]));
    }
    if (paramDeviceCommand.isKeepAlive()) {
      arrayOfByte2[1] = ((byte)(0x2 | arrayOfByte2[1]));
    }
    
    int i4 = paramDeviceCommand.getDeviceId();
    i3 = (byte)(i3 + i4);
    arrayOfByte2[2] = i4;
    
    int i5 = paramDeviceCommand.getCommandId();
    i3 = (byte)(i3 + i5);
    arrayOfByte2[3] = i5;
    
    int i6 = a();
    i3 = (byte)(i3 + i6);
    arrayOfByte2[4] = i6;
    paramDeviceCommand.setSequenceNumber(i6);
    
    i3 = (byte)(i3 + i1);
    arrayOfByte2[5] = ((byte)i1);
    
    for (int i7 = 0; i7 < arrayOfByte1.length; i7++) {
      arrayOfByte2[(i7 + 6)] = arrayOfByte1[i7];
      i3 = (byte)(i3 + arrayOfByte1[i7]);
    }
    
    arrayOfByte2[(i2 - 1)] = ((byte)(i3 ^ 0xFFFFFFFF));
    paramDeviceCommand.setPacket(arrayOfByte2);
    
    return arrayOfByte2;
  }
  
  private byte a() {
    byte b1;
    synchronized (j) {
      b1 = l;
      if (((this.l = (byte)(l + 1)) & 0xFF) > (k & 0xFF)) {
        l = 0;
      }
    }
    return b1;
  }
  
  public static void setMaxSequenceNumber(byte sequenceNumber) {
    k = sequenceNumber;
  }
  
  public static abstract interface MainProcessorSessionDelegate
  {
    public abstract void handleResponseCreated(DeviceResponse paramDeviceResponse);
    
    public abstract void handleAsyncMessageCreated(AsyncMessage paramAsyncMessage);
    
    public abstract void handleStringResponseCreated(String paramString);
  }
}
