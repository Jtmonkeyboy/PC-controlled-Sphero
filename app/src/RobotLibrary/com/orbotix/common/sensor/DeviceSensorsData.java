package com.orbotix.common.sensor;

import com.orbotix.common.utilities.binary.BitMask;








public class DeviceSensorsData
  extends a
{
  private GyroData b;
  private AccelerometerData c;
  private BackEMFData d;
  private LocatorData e;
  private QuaternionSensor f;
  AttitudeSensor a = null;
  
  public DeviceSensorsData(BitMask<SensorFlag> mask, byte[] data)
  {
    ThreeAxisSensor localThreeAxisSensor1 = null;
    ThreeAxisSensor localThreeAxisSensor2 = null;
    BackEMFSensor localBackEMFSensor = null;
    
    LocatorSensor localLocatorSensor1 = null;
    LocatorSensor localLocatorSensor2 = null;
    
    int i = 0;
    
    if (mask.isEnabled(SensorFlag.ATTITUDE)) {
      a = new AttitudeSensor();
      a.pitch = a(i, data);
      i += 2;
      a.roll = a(i, data);
      i += 2;
      a.yaw = a(i, data);
      i += 2;
    }
    
    if (mask.isEnabled(SensorFlag.ACCELEROMETER_NORMALIZED)) {
      localThreeAxisSensor1 = new ThreeAxisSensor();
      x = a(i, data);
      i += 2;
      y = a(i, data);
      i += 2;
      z = a(i, data);
      i += 2;
    }
    

    if (mask.isEnabled(SensorFlag.GYRO_NORMALIZED)) {
      localThreeAxisSensor2 = new ThreeAxisSensor();
      x = a(i, data);
      i += 2;
      y = a(i, data);
      i += 2;
      z = a(i, data);
      i += 2;
    }
    
    if (mask.isEnabled(SensorFlag.MOTOR_BACKEMF_NORMALIZED)) {
      localBackEMFSensor = new BackEMFSensor();
      rightMotorValue = a(i, data);
      i += 2;
      leftMotorValue = a(i, data);
      i += 2;
    }
    
    if (mask.isEnabled(SensorFlag.QUATERNION)) {
      f = new QuaternionSensor();
      f.q0 = QuaternionSensor.normalize(a(i, data));
      i += 2;
      f.q1 = QuaternionSensor.normalize(a(i, data));
      i += 2;
      f.q2 = QuaternionSensor.normalize(a(i, data));
      i += 2;
      f.q3 = QuaternionSensor.normalize(a(i, data));
      i += 2;
    }
    

    if (mask.isEnabled(SensorFlag.LOCATOR)) {
      localLocatorSensor1 = new LocatorSensor();
      x = a(i, data);
      i += 2;
      y = a(i, data);
      i += 2;
    }
    


    if (mask.isEnabled(SensorFlag.VELOCITY)) {
      localLocatorSensor2 = new LocatorSensor();
      x = a(i, data);
      i += 2;
      y = a(i, data);
      i += 2;
    }
    
    c = new AccelerometerData(localThreeAxisSensor1);
    b = new GyroData(localThreeAxisSensor2);
    d = new BackEMFData(localBackEMFSensor);
    e = new LocatorData(localLocatorSensor1, localLocatorSensor2);
  }
  
  private int a(int paramInt, byte[] paramArrayOfByte) {
    return (paramArrayOfByte[paramInt] << 8) + a(paramArrayOfByte[(paramInt + 1)]);
  }
  
  private int a(byte paramByte) {
    int i = paramByte;
    return i & 0xFF;
  }
  
  public GyroData getGyroData() {
    return b;
  }
  
  public AccelerometerData getAccelerometerData() {
    return c;
  }
  
  public AttitudeSensor getAttitudeData() {
    return a;
  }
  
  public BackEMFData getBackEMFData() {
    return d;
  }
  
  public LocatorData getLocatorData() {
    return e;
  }
  
  public QuaternionSensor getQuaternion() {
    return f;
  }
  
  public String toString() {
    StringBuilder localStringBuilder = new StringBuilder("[DeviceSensorsData :");
    if (c != null) localStringBuilder.append(c).append(" : ");
    if (b != null) localStringBuilder.append(b).append(" : ");
    if (a != null) localStringBuilder.append(a).append(" : ");
    if (d != null) localStringBuilder.append(d).append(" : ");
    if (e != null) localStringBuilder.append(e).append(" : ");
    if (f != null) localStringBuilder.append(f).append("]");
    return localStringBuilder.toString();
  }
}
