package com.orbotix.orbbasic;

import android.util.Log;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.common.internal.ResponseCode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;










public class OrbBasicControl
  implements ResponseListener
{
  private boolean a = false;
  
  private int b;
  
  private int c;
  
  private byte[] d;
  
  private boolean e;
  
  private int f;
  
  private static final String g = "OBX-OrbBasic";
  
  private Robot h;
  
  private HashSet<OrbBasicEventListener> i;
  
  public OrbBasicControl(Robot robot)
  {
    if (null == robot) {
      throw new IllegalArgumentException("NULL Robot Argument");
    }
    h = robot;
    i = new HashSet();
    robot.addResponseListener(this);
  }
  
  public void setProgram(byte[] program)
  {
    d = program;
  }
  


  /**
   * @deprecated
   */
  public void setOrbBasicControlEventListener(OrbBasicEventListener listener)
  {
    i.add(listener);
  }
  
  public void addEventListener(OrbBasicEventListener listener) {
    i.add(listener);
  }
  
  public void removeEventListener(OrbBasicEventListener listener) {
    i.remove(listener);
  }
  
  public void clearEventListeners() {
    i.clear();
  }
  
  public void loadProgram()
  {
    if (h == null) {
      throw new IllegalStateException("No Robot defined.");
    }
    
    Log.d("OBX-OrbBasic", "Begin Loading Program");
    
    b = 0;
    c = 0;
    e = false;
    
    String str = new String(d);
    
    f = findStartlineIntegerValue(str);
    

    Log.d(getClass().getName(), "startline " + f);
    
    h.sendCommand(new OrbBasicAppendFragmentCommand(a, a()));
  }
  
  public int findStartlineIntegerValue(String programString)
  {
    programString = programString.replaceAll("[^0-9]+", " ");
    if (Arrays.asList(programString.trim().split(" ")).get(0) == "")
    {
      return 1;
    }
    
    return Integer.parseInt((String)Arrays.asList(programString.trim().split(" ")).get(0));
  }
  

  public void eraseStorage()
  {
    if (h == null) { throw new IllegalStateException("No Robot defined.");
    }
    h.sendCommand(new OrbBasicEraseStorageCommand(a));
  }
  



  public void executeProgram()
  {
    if (h == null) throw new IllegalStateException("No Robot defined.");
    Log.d("OBX-OrbBasic", "Executing Program");
    h.sendCommand(new OrbBasicExecuteProgramCommand(a, f));
  }
  
  public void abortProgram()
  {
    if (h == null) {
      throw new IllegalStateException("No Robot defined.");
    }
    h.sendCommand(new OrbBasicAbortProgramCommand());
  }
  
  private byte[] a()
  {
    b = c;
    int j = 252;
    int k = 0;
    int m = c + 1;
    while (k == 0) {
      if (m >= d.length) {
        if (m - b > 252) {
          Log.d("OBX-OrbBasic", "Reached Max Length: ");
          
          k = 1;
        }
        else {
          Log.d("OBX-OrbBasic", "Reached Length: " + d.length);
          e = true;
          k = 1;
          c = m;
        }
        
      }
      else if (d[m] == 10) {
        if (m - b > 252) {
          Log.d("OBX-OrbBasic", "Reached Max Length: ");
          
          k = 1;
        }
        else {
          c = (m + 1);
        }
      }
      m++;
    }
    byte[] arrayOfByte = new byte[c - b];
    for (int n = b; n < c; n++) {
      arrayOfByte[(n - b)] = d[n];
    }
    Log.d("OBX-OrbBasic", "Chunk Proccessed: " + new String(arrayOfByte));
    return arrayOfByte; }
  
  public void handleResponse(DeviceResponse response, Robot robot) { Iterator localIterator;
    Object localObject;
    boolean bool;
    if ((response instanceof OrbBasicAppendFragmentResponse)) {
      if (response.getResponseCode() == ResponseCode.OK) {
        Log.d("OBX-OrbBasic", "Fragment Append Success");
        
        if (!e) {
          h.sendCommand(new OrbBasicAppendFragmentCommand(a, a()));
        }
        else {
          for (localIterator = i.iterator(); localIterator.hasNext();) { localObject = (OrbBasicEventListener)localIterator.next();
            ((OrbBasicEventListener)localObject).onLoadProgramComplete(true);
          }
        }
      }
      else {
        Log.d("OBX-OrbBasic", "Fragment Append Fail: " + response.getResponseCode());
        for (localIterator = i.iterator(); localIterator.hasNext();) { localObject = (OrbBasicEventListener)localIterator.next();
          ((OrbBasicEventListener)localObject).onLoadProgramComplete(false);
        }
      }
    }
    else if ((response instanceof OrbBasicEraseStorageResponse)) {
      bool = false;
      if (response.getResponseCode() == ResponseCode.OK) {
        bool = true;
      }
      for (localObject = i.iterator(); ((Iterator)localObject).hasNext();) { OrbBasicEventListener localOrbBasicEventListener = (OrbBasicEventListener)((Iterator)localObject).next();
        localOrbBasicEventListener.onEraseCompleted(bool);
      }
    }
  }
  
  public void handleStringResponse(String stringResponse, Robot robot) {}
  
  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot)
  {
    Iterator localIterator;
    OrbBasicEventListener localOrbBasicEventListener;
    Object localObject;
    if ((asyncMessage instanceof OrbBasicPrintMessageAsyncData)) {
      for (localIterator = i.iterator(); localIterator.hasNext();) { localOrbBasicEventListener = (OrbBasicEventListener)localIterator.next();
        localObject = (OrbBasicPrintMessageAsyncData)asyncMessage;
        localOrbBasicEventListener.onPrintMessage(((OrbBasicPrintMessageAsyncData)localObject).getMessage());
      }
      
    }
    else if ((asyncMessage instanceof OrbBasicErrorASCIIAsyncData)) {
      for (localIterator = i.iterator(); localIterator.hasNext();) { localOrbBasicEventListener = (OrbBasicEventListener)localIterator.next();
        localObject = (OrbBasicErrorASCIIAsyncData)asyncMessage;
        localOrbBasicEventListener.onErrorMessage(((OrbBasicErrorASCIIAsyncData)localObject).getErrorASCII());
      }
      
    } else if ((asyncMessage instanceof OrbBasicErrorBinaryAsyncData)) {
      for (localIterator = i.iterator(); localIterator.hasNext();) { localOrbBasicEventListener = (OrbBasicEventListener)localIterator.next();
        localObject = (OrbBasicErrorBinaryAsyncData)asyncMessage;
        localOrbBasicEventListener.onErrorByteArray(((OrbBasicErrorBinaryAsyncData)localObject).getErrorBinary());
      }
    }
  }
}
