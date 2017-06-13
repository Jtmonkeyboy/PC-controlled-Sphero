package com.orbotix.common.stat;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.orbotix.command.GetChassisIdCommand;
import com.orbotix.command.GetChassisIdResponse;
import com.orbotix.command.GetFactoryConfigBlockCRCCommand;
import com.orbotix.command.GetFactoryConfigBlockCRCResponse;
import com.orbotix.command.GetSkuCommand;
import com.orbotix.command.GetSkuResponse;
import com.orbotix.command.ResponseFactory;
import com.orbotix.command.VersioningCommand;
import com.orbotix.command.VersioningResponse;
import com.orbotix.common.DLog;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;
import com.orbotix.common.internal.RobotVersion;
import java.util.ArrayList;
import java.util.List;


class ProfileStatGenerator
  implements ResponseListener
{
  private static final long a = 1500L;
  private static final int b = 2;
  private Robot c;
  private ProfileStatGeneratorListener d;
  private Stat e;
  private List<ProfileStep> f;
  private int g;
  private int h;
  private Handler i = new Handler();
  
  public ProfileStatGenerator(@NonNull Robot robot) {
    c = robot;
    e = new Stat(Stat.StatKey.ROBOT_PROFILE, robot.getSerialNumber());
    e.addData(Stat.StatDataKey.NAME, c.getName());
    e.addData(Stat.StatDataKey.SERIAL_NUMBER, Stat.filterMac(c.getSerialNumber()));
    f = a();
  }
  
  public void a(@NonNull ProfileStatGeneratorListener paramProfileStatGeneratorListener) {
    d = paramProfileStatGeneratorListener;
    c.addResponseListener(this);
    
    g = -1;
    b();
  }
  



  private List<ProfileStep> a()
  {
    ArrayList localArrayList = new ArrayList();
    
    localArrayList.add(new ProfileStep(new VersioningCommand(), VersioningResponse.class, j));
    localArrayList.add(new ProfileStep(new GetSkuCommand(), GetSkuResponse.class, k));
    localArrayList.add(new ProfileStep(new GetChassisIdCommand(), GetChassisIdResponse.class, l));
    
    return localArrayList;
  }
  
  private ProfileStep.ProfileStepHandler j = new ProfileStep.ProfileStepHandler()
  {
    public void handleProfileStepResponse(DeviceResponse response) {
      if (response.getResponseCode() == ResponseCode.OK) {
        VersioningResponse localVersioningResponse = (VersioningResponse)response;
        ProfileStatGenerator.a(ProfileStatGenerator.this).addData(Stat.StatDataKey.MODEL, String.valueOf(localVersioningResponse.getVersion().getModelNumber()));
        ProfileStatGenerator.a(ProfileStatGenerator.this).addData(Stat.StatDataKey.MAIN_APP, localVersioningResponse.getVersion().getMainApplicationVersion());
        ProfileStatGenerator.a(ProfileStatGenerator.this).addData(Stat.StatDataKey.BOOTLOADER, localVersioningResponse.getVersion().getBootloaderVersion());
        ProfileStatGenerator.a(ProfileStatGenerator.this).addData(Stat.StatDataKey.RADIO_FIRMWARE, ProfileStatGenerator.b(ProfileStatGenerator.this).getRadioFirmwareRevision());
        if (localVersioningResponse.getVersion().getModelNumber() == 30) {
          GetFactoryConfigBlockCRCCommand localGetFactoryConfigBlockCRCCommand = new GetFactoryConfigBlockCRCCommand();
          ResponseFactory.register(localGetFactoryConfigBlockCRCCommand.getDeviceId(), localGetFactoryConfigBlockCRCCommand.getCommandId(), GetFactoryConfigBlockCRCResponse.class);
          ProfileStatGenerator.a(ProfileStatGenerator.this, new ProfileStep(new GetFactoryConfigBlockCRCCommand(), GetFactoryConfigBlockCRCResponse.class, ProfileStatGenerator.c(ProfileStatGenerator.this)));
        }
      }
    }
  };
  
  private ProfileStep.ProfileStepHandler k = new ProfileStep.ProfileStepHandler()
  {
    public void handleProfileStepResponse(DeviceResponse response) {
      if (response.getResponseCode() == ResponseCode.OK) {
        GetSkuResponse localGetSkuResponse = (GetSkuResponse)response;
        ProfileStatGenerator.a(ProfileStatGenerator.this).addData(Stat.StatDataKey.SKU, localGetSkuResponse.getSku());
      }
    }
  };
  
  private ProfileStep.ProfileStepHandler l = new ProfileStep.ProfileStepHandler()
  {
    public void handleProfileStepResponse(DeviceResponse response) {
      if (response.getResponseCode() == ResponseCode.OK) {
        GetChassisIdResponse localGetChassisIdResponse = (GetChassisIdResponse)response;
        ProfileStatGenerator.a(ProfileStatGenerator.this).addData(Stat.StatDataKey.CHASSIS_ID, String.valueOf(localGetChassisIdResponse.getChassisId()));
      }
    }
  };
  
  private ProfileStep.ProfileStepHandler m = new ProfileStep.ProfileStepHandler()
  {
    public void handleProfileStepResponse(DeviceResponse response) {
      if (response.getCode() == ResponseCode.OK) {
        GetFactoryConfigBlockCRCResponse localGetFactoryConfigBlockCRCResponse = (GetFactoryConfigBlockCRCResponse)response;
        ProfileStatGenerator.a(ProfileStatGenerator.this).addData(Stat.StatDataKey.FACTORY_CONFIG_BLOCK_CRC, localGetFactoryConfigBlockCRCResponse.getFactoryConfigBlockCRC());
      }
    }
  };
  



  private void a(ProfileStep paramProfileStep)
  {
    synchronized (this) {
      f.add(paramProfileStep);
    }
  }
  
  private void b() {
    synchronized (this) {
      g += 1;
    }
    if (c()) {
      c.removeResponseListener(this);
      d.handleProfileStatGenerated(e);
      SLog.log("Profile stat generated: " + e);
    }
    else {
      ??? = d().a();
      c.sendCommand((DeviceCommand)???);
      h = 0;
      i.postDelayed(new Runnable()
      {

        public void run() { ProfileStatGenerator.a(ProfileStatGenerator.this, a); } }, 1500L);
    }
  }
  

  private void a(final DeviceCommand paramDeviceCommand)
  {
    SLog.log("Command %s timed out", new Object[] { paramDeviceCommand.toString() });
    h += 1;
    if (h > 2) {
      DLog.w("Command %s failed multiple times, aborting profile stat generation process", new Object[] { paramDeviceCommand });
      return;
    }
    c.sendCommand(paramDeviceCommand);
    i.postDelayed(new Runnable()
    {

      public void run() { ProfileStatGenerator.a(ProfileStatGenerator.this, paramDeviceCommand); } }, 1500L);
  }
  

  private boolean c()
  {
    synchronized (this) {
      return g >= f.size();
    }
  }
  
  private ProfileStep d() {
    synchronized (this) {
      return (ProfileStep)f.get(g);
    }
  }
  
  private boolean a(DeviceResponse paramDeviceResponse, Robot paramRobot) {
    if (c()) {
      return true;
    }
    
    if (!paramRobot.getIdentifier().equals(c.getIdentifier())) {
      return true;
    }
    
    ProfileStep localProfileStep = d();
    if (paramDeviceResponse.getClass() != localProfileStep.b()) {
      return true;
    }
    
    if (paramDeviceResponse.getSequenceNumber() != localProfileStep.a().getSequenceNumber()) {
      SLog.log("Sequence number mismatch on profile stat, not using this response. Offending class: %s, expected sequence: %d, received sequence: %d", new Object[] {paramDeviceResponse
        .getClass(), Byte.valueOf(localProfileStep.a().getSequenceNumber()), Byte.valueOf(paramDeviceResponse.getSequenceNumber()) });
      return true;
    }
    return false;
  }
  








  public void handleResponse(DeviceResponse response, Robot robot)
  {
    if (a(response, robot)) {
      return;
    }
    i.removeCallbacksAndMessages(null);
    d().c().handleProfileStepResponse(response);
    b();
  }
  
  public void handleStringResponse(String stringResponse, Robot robot) {}
  
  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot) {}
  
  public static abstract interface ProfileStatGeneratorListener
  {
    public abstract void handleProfileStatGenerated(Stat paramStat);
  }
}
