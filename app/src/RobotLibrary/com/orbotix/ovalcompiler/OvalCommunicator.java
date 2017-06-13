package com.orbotix.ovalcompiler;

import android.support.annotation.NonNull;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceCommand;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.ovalcompiler.response.AppendCompleteOvalResponse;
import com.orbotix.ovalcompiler.response.AppendFragmentOvalResponse;
import java.util.ArrayList;
import java.util.List;

class OvalCommunicator
  implements ResponseListener
{
  private Robot a;
  private OvalCommunicatorListener b;
  private List<a> c = new ArrayList();
  private boolean d;
  
  public OvalCommunicator(@NonNull Robot robot, @NonNull OvalCommunicatorListener listener) {
    a = robot;
    b = listener;
  }
  
  public void a(b paramB, OvalProgramBatchDidSendListener paramOvalProgramBatchDidSendListener) {
    synchronized (this) {
      c.add(new a(paramB, paramOvalProgramBatchDidSendListener));
      b();
    }
  }
  
  public void a() {
    synchronized (this) {
      c.clear();
      c();
    }
  }
  
  private void b() {
    synchronized (this) {
      if (!d) {
        d = true;
        a.addResponseListener(this);
        d();
      }
    }
  }
  
  private void c() {
    synchronized (this) {
      if (d) {
        d = false;
        a.removeResponseListener(this);
      }
    }
  }
  
  private void d() {
    synchronized (this) {
      if (c.size() < 1) {
        c();
        b.onCommunicatorEmptiedQueue(this);
        return;
      }
      a localA = (a)c.get(0);
      b localB = localA.a();
      DeviceCommand localDeviceCommand = localB.a();
      if (localDeviceCommand != null) {
        a.sendCommand(localDeviceCommand);
      }
    }
  }
  



  public void handleResponse(DeviceResponse response, Robot robot)
  {
    if (!robot.getIdentifier().equals(a.getIdentifier())) {
      return;
    }
    if (((response instanceof AppendFragmentOvalResponse)) || ((response instanceof AppendCompleteOvalResponse)))
    {
      synchronized (this) {
        a localA = (a)c.get(0);
        b localB = localA.a();
        localB.b();
        if (localB.c()) {
          c.remove(0);
          OvalProgramBatchDidSendListener localOvalProgramBatchDidSendListener = localA.b();
          if (localOvalProgramBatchDidSendListener != null) {
            localOvalProgramBatchDidSendListener.onOvalProgramBatchDidSend();
          }
        }
        d();
      }
    }
  }
  



  public void handleStringResponse(String stringResponse, Robot robot) {}
  



  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot) {}
  


  private class a
  {
    private b b;
    

    private OvalCommunicator.OvalProgramBatchDidSendListener c;
    


    public a(b paramB, OvalCommunicator.OvalProgramBatchDidSendListener paramOvalProgramBatchDidSendListener)
    {
      b = paramB;
      c = paramOvalProgramBatchDidSendListener;
    }
    
    public b a() {
      return b;
    }
    
    public OvalCommunicator.OvalProgramBatchDidSendListener b() {
      return c;
    }
  }
  
  public static abstract interface OvalProgramBatchDidSendListener
  {
    public abstract void onOvalProgramBatchDidSend();
  }
  
  public static abstract interface OvalCommunicatorListener
  {
    public abstract void onCommunicatorEmptiedQueue(OvalCommunicator paramOvalCommunicator);
  }
}
