package com.orbotix.ovalcompiler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.orbotix.command.ResponseFactory;
import com.orbotix.common.DLog;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.AsyncMessage.Type;
import com.orbotix.common.internal.DeviceId;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.RobotCommandId;
import com.orbotix.common.utilities.jni.ByteArrayReferenceContainer;
import com.orbotix.common.utilities.jni.CharArrayReferenceContainer;
import com.orbotix.common.utilities.jni.IntArrayReferenceContainer;
import com.orbotix.ovalcompiler.response.async.OvalDeviceBroadcast;
import com.orbotix.ovalcompiler.response.async.OvalErrorBroadcast;

public class OvalControl implements com.orbotix.common.ResponseListener, OvalCommunicator.OvalCommunicatorListener, OvalCommunicator.OvalProgramBatchDidSendListener
{
  private final Robot a;
  private final OvalControlListener b;
  private final OvalCommunicator c;
  private boolean d;
  
  static
  {
    System.loadLibrary("oval_compiler");
  }
  





  public OvalControl(@NonNull Robot robot, @Nullable OvalControlListener listener)
  {
    a = robot;
    b = listener;
    c = new OvalCommunicator(robot, this);
    a();
    a.addResponseListener(this);
  }
  
  public void resetOvm(boolean alsoResetLibrary) {
    _ovalResetLinker();
    a.sendCommand(new com.orbotix.ovalcompiler.command.ResetOvmCommand(alsoResetLibrary));
  }
  
  public void sendOval(String program) {
    sendOvalPrograms(new String[] { program });
  }
  
  public void sendOvalPrograms(String[] programs) {
    a[] arrayOfA = a(programs);
    if (arrayOfA == null) {
      DLog.e("Skipping send since one or more programs did not compile");
      return;
    }
    b localB = new b(arrayOfA);
    c.a(localB, this);
  }
  
  private void a() {
    ResponseFactory.register(DeviceId.ROBOT.getValue(), RobotCommandId.RESET_OVM.getValue(), com.orbotix.ovalcompiler.response.ResetOvmResponse.class);
    ResponseFactory.register(DeviceId.ROBOT.getValue(), RobotCommandId.APPEND_OVAL_COMPLETE.getValue(), com.orbotix.ovalcompiler.response.AppendCompleteOvalResponse.class);
    ResponseFactory.register(DeviceId.ROBOT.getValue(), RobotCommandId.APPEND_OVAL_FRAGMENT.getValue(), com.orbotix.ovalcompiler.response.AppendFragmentOvalResponse.class);
    com.orbotix.async.AsyncMessageFactory.register(AsyncMessage.Type.OvalErrorBroadcast, OvalErrorBroadcast.class);
    com.orbotix.async.AsyncMessageFactory.register(AsyncMessage.Type.OvalDeviceBroadcast, OvalDeviceBroadcast.class);
  }
  
  @Nullable
  public a compileOval(@NonNull String program) { ByteArrayReferenceContainer localByteArrayReferenceContainer = new ByteArrayReferenceContainer();
    IntArrayReferenceContainer localIntArrayReferenceContainer = new IntArrayReferenceContainer();
    CharArrayReferenceContainer localCharArrayReferenceContainer = new CharArrayReferenceContainer();
    CompiledOvalLengths localCompiledOvalLengths = _ovalCompileCompressAndPacketize(program, localByteArrayReferenceContainer, localIntArrayReferenceContainer, localCharArrayReferenceContainer);
    String str = new String(localCharArrayReferenceContainer.getCharArray());
    if (localCompiledOvalLengths.getErrorOccurred() == 1)
    {


      DLog.e(" #######  ##     ##    ###    ##");
      DLog.e("##     ## ##     ##   ## ##   ##");
      DLog.e("##     ## ##     ##  ##   ##  ##");
      DLog.e("##     ## ##     ## ##     ## ##");
      DLog.e("##     ##  ##   ##  ######### ##");
      DLog.e("##     ##   ## ##   ##     ## ##");
      DLog.e(" #######     ###    ##     ## ########");
      DLog.e("Compile Failure!");
      DLog.e("Message: " + str);
      
      if (b != null) {
        b.onProgramFailedToSend(this, str);
      }
      return null;
    }
    
    if (d) {
      DLog.v("Compile success report: " + str);
    }
    

    byte[][] arrayOfByte = new byte[localCompiledOvalLengths.getSizesLength()][];
    int i = 0;
    for (int j = 0; j < localCompiledOvalLengths.getSizesLength(); j++) {
      int k = localIntArrayReferenceContainer.getIntArray()[j];
      byte[] arrayOfByte1 = new byte[k];
      System.arraycopy(localByteArrayReferenceContainer.getByteArray(), i, arrayOfByte1, 0, k);
      arrayOfByte[j] = arrayOfByte1;
      i += k;
    }
    return new a(arrayOfByte);
  }
  
  @Nullable
  private a[] a(@NonNull String[] paramArrayOfString) { a[] arrayOfA = new a[paramArrayOfString.length];
    
    for (int i = 0; i < paramArrayOfString.length; i++) {
      a localA = compileOval(paramArrayOfString[i]);
      if (localA == null) {
        DLog.e("One or more oval programs did not compile. Stopping list compile.");
        return null;
      }
      arrayOfA[i] = localA;
    }
    return arrayOfA;
  }
  



  public void handleResponse(DeviceResponse response, Robot robot)
  {
    if (!robot.getIdentifier().equals(a.getIdentifier())) {
      return;
    }
    if (((response instanceof com.orbotix.ovalcompiler.response.ResetOvmResponse)) && 
      (b != null)) {
      b.onOvmReset(this);
    }
  }
  






  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot)
  {
    if (!robot.getIdentifier().equals(a.getIdentifier())) {
      return;
    }
    if (b != null) {
      if ((asyncMessage instanceof OvalErrorBroadcast)) {
        b.onOvmRuntimeErrorReceived(this, (OvalErrorBroadcast)asyncMessage);
      }
      else if ((asyncMessage instanceof OvalDeviceBroadcast)) {
        b.onOvalNotificationReceived(this, (OvalDeviceBroadcast)asyncMessage);
      }
    }
  }
  



  public void onCommunicatorEmptiedQueue(OvalCommunicator communicator)
  {
    if (b != null) {
      b.onOvalQueueEmptied(this);
    }
  }
  
  public boolean isCompileReportEnabled() {
    return d;
  }
  
  public void enableCompileReport(boolean enable) {
    d = enable;
  }
  












  public void onOvalProgramBatchDidSend()
  {
    if (b != null) {
      b.onProgramSentSuccessfully(this);
    }
  }
  
  public void handleStringResponse(String stringResponse, Robot robot) {}
  
  private static native CompiledOvalLengths _ovalCompileCompressAndPacketize(String paramString, ByteArrayReferenceContainer paramByteArrayReferenceContainer, IntArrayReferenceContainer paramIntArrayReferenceContainer, CharArrayReferenceContainer paramCharArrayReferenceContainer);
  
  private static native void _ovalResetLinker();
  
  public static abstract interface OvalControlListener
  {
    public abstract void onProgramFailedToSend(OvalControl paramOvalControl, String paramString);
    
    public abstract void onProgramSentSuccessfully(OvalControl paramOvalControl);
    
    public abstract void onOvmReset(OvalControl paramOvalControl);
    
    public abstract void onOvalNotificationReceived(OvalControl paramOvalControl, OvalDeviceBroadcast paramOvalDeviceBroadcast);
    
    public abstract void onOvmRuntimeErrorReceived(OvalControl paramOvalControl, OvalErrorBroadcast paramOvalErrorBroadcast);
    
    public abstract void onOvalQueueEmptied(OvalControl paramOvalControl);
  }
}
