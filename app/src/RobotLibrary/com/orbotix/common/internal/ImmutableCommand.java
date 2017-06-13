package com.orbotix.common.internal;





public class ImmutableCommand
  extends DeviceCommand
{
  public ImmutableCommand(byte[] packet)
  {
    if (packet.length < 7) {
      throw new IllegalArgumentException("Minimum well-formed main processor packet is 7 bytes");
    }
    int i = 6 + packet[5];
    if (packet.length < 6 + packet[5]) {
      throw new IllegalArgumentException("Packet specifies " + i + "length, but is " + packet.length);
    }
    setPacket(packet);
  }
  

  public boolean isKeepAlive() { return (a(1) & 0x2) != 0; }
  public boolean isNACK() { return (a(1) & 0x1) == 0; }
  public boolean isACK() { return (a(1) & 0x1) == 1; }
  
  public byte getDeviceId()
  {
    return a(2);
  }
  
  public byte getCommandId() { return a(3); }
  

  public byte getSequenceNumber() { return a(4); }
  
  public byte getDataLength() { return a(5); }
  

  public void setSequenceNumber(byte b)
  {
    throw new UnsupportedOperationException("ByteCommands are Immutable - use AdHocCommand");
  }
  
  public void setKeepAlive(boolean keepAlive)
  {
    throw new UnsupportedOperationException("ByteCommands are Immutable - use AdHocCommand");
  }
  
  public void setResponseRequested(boolean responseRequested)
  {
    throw new UnsupportedOperationException("ByteCommands are Immutable - use AdHocCommand");
  }
  
  public boolean isResponseRequested()
  {
    return (a(1) & 0x1) == 1;
  }
  
  private byte a(int paramInt) {
    return getPacket()[paramInt];
  }
}
