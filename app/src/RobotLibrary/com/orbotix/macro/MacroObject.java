package com.orbotix.macro;

import com.orbotix.async.MacroEmitMarker;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;
import com.orbotix.common.internal.AsyncMessage;
import com.orbotix.common.internal.DeviceResponse;
import com.orbotix.macro.cmd.Comment;
import com.orbotix.macro.cmd.Emit;
import com.orbotix.macro.cmd.MacroCommand;
import com.orbotix.macro.cmd.MacroCommandCreationException;
import com.orbotix.macro.cmd.MacroCommandFactory;
import com.orbotix.macro.cmd.Roll;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.util.ByteArrayBuffer;




















































public class MacroObject
  implements ResponseListener, Serializable
{
  public static final int CALLBACK_EMIT_ID = 2;
  private ArrayList<MacroCommand> a = new ArrayList();
  
  private ArrayList<MacroCommand> b = new ArrayList();
  
  private ArrayList<Integer> c = new ArrayList();
  

  private List<SaveTemporaryMacroChunkCommand> d = new ArrayList();
  

  private MacroObjectMode e = MacroObjectMode.Normal;
  
  private volatile boolean f = false;
  

  private boolean g = true;
  
  private OnMacroUploadedListener h;
  
  private Robot i;
  
  private Runnable j = null;
  private OnEmitCallback k = null;
  



  public void handleResponse(DeviceResponse response, Robot robot)
  {
    if ((e == MacroObjectMode.Chunky) && (((response instanceof SaveTemporaryMacroChunkResponse)) || ((response instanceof InitMacroExecutiveResponse))))
    {


      if (d.size() > 0) {
        SaveTemporaryMacroChunkCommand localSaveTemporaryMacroChunkCommand = (SaveTemporaryMacroChunkCommand)d.get(0);
        i.sendCommand(localSaveTemporaryMacroChunkCommand);
        d.remove(localSaveTemporaryMacroChunkCommand);
      }
      else {
        if (h != null) {
          h.onMacroUploaded(i);
        }
        
        if (g) {
          i.sendCommand(new RunMacroCommand((byte)-1));
        }
      }
    }
  }
  


  public void handleStringResponse(String stringResponse, Robot robot) {}
  

  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot)
  {
    if (asyncMessage.getClass() == MacroEmitMarker.class) {
      MacroEmitMarker localMacroEmitMarker = (MacroEmitMarker)asyncMessage;
      if (localMacroEmitMarker.getMarkerId() == 1)
      {

        if (c.size() > 0) c.remove(0);
        c();
      }
      else if ((j != null) && (localMacroEmitMarker.getMarkerId() == 2))
      {


        j.run();
      }
      

      if (k != null) {
        k.onEmit(localMacroEmitMarker.getMarkerId());
      }
    }
  }
  






















  public MacroObject() {}
  






















  public MacroObject(byte[] bytes)
    throws MacroCommandCreationException
  {
    Object localObject = bytes;
    
    while ((localObject.length > 0) && (localObject[0] != 0))
    {
      MacroCommand localMacroCommand = MacroCommandFactory.createFromBytes((byte[])localObject);
      a.add(localMacroCommand);
      
      int m = localObject.length - localMacroCommand.getLength();
      if (m <= 0)
        break;
      byte[] arrayOfByte = new byte[m];
      for (int n = 0; n < m; n++) {
        arrayOfByte[n] = localObject[(n + localMacroCommand.getLength())];
      }
      localObject = arrayOfByte;
    }
  }
  








  public MacroObjectMode getMode()
  {
    return e;
  }
  




  public void setMode(MacroObjectMode _mode)
  {
    e = _mode;
  }
  








  public void setRunAfterLoading(boolean run)
  {
    g = run;
  }
  




  public ArrayList<MacroCommand> getCommands()
  {
    return a;
  }
  




  public void addCommand(MacroCommand command)
  {
    a.add(command);
  }
  





  public void addCommand(int index, MacroCommand cmd)
  {
    a.add(index, cmd);
  }
  




  public void addCommands(ArrayList<MacroCommand> addCommands)
  {
    for (MacroCommand localMacroCommand : addCommands) {
      a.add(localMacroCommand);
    }
  }
  






  public MacroCommand setCommand(int index, MacroCommand cmd)
  {
    return (MacroCommand)a.set(index, cmd);
  }
  




  public void removeCommand(int index)
  {
    a.remove(index);
  }
  






  public void moveCommand(int from, int to)
  {
    MacroCommand localMacroCommand = (MacroCommand)a.get(from);
    a.remove(from);
    a.add(to, localMacroCommand);
  }
  
  public int size()
  {
    return a.size();
  }
  





  public void setOnEmitCallback(Runnable callback)
  {
    j = callback;
  }
  




  public void setOnEmitCallback(OnEmitCallback callback)
  {
    k = callback;
  }
  




  public void setOnMacroUploadedListener(OnMacroUploadedListener listener)
  {
    h = listener;
  }
  
  public void setRobot(Robot robot) {
    i = robot;
  }
  
  public void playMacro() {
    if (e == MacroObjectMode.Normal) {
      if (i == null) return;
      i.sendCommand(new SaveTemporaryMacroCommand((byte)1, generateMacroData()));
      i.sendCommand(new RunMacroCommand((byte)-1));
    }
    else if (e == MacroObjectMode.CachedStreaming) {
      if (a.size() == 0)
      {
        a.addAll(b);
      }
      else
      {
        b.clear();
        b.addAll(a);
      }
      
      c();
      if (!f)
      {
        f = true;
        a();
        if (i != null) {
          i.sendCommand(new RunMacroCommand((byte)-2));
        }
      }
    }
    else if (e == MacroObjectMode.Chunky)
    {
      if (i == null) {
        return;
      }
      
      if (!f) {
        a();
        d.clear();
        
        for (MacroCommand localMacroCommand : a) {
          SaveTemporaryMacroChunkCommand localSaveTemporaryMacroChunkCommand = new SaveTemporaryMacroChunkCommand((byte)0, localMacroCommand.getByteRepresentation());
          if (d.size() == 0) {
            localSaveTemporaryMacroChunkCommand.setIsFirst(true);
          }
          d.add(localSaveTemporaryMacroChunkCommand);
        }
        i.sendCommand(new InitMacroExecutiveCommand());
      }
    }
  }
  
  public void registerAsyncDataListener() {
    if (i == null) { return;
    }
    i.addResponseListener(this);
  }
  
  private synchronized void a()
  {
    i.addResponseListener(this);
  }
  
  public void stopMacro()
  {
    if (i == null) return;
    i.sendCommand(new AbortMacroCommand());
    a.clear();
    c.clear();
    f = false;
    i.removeResponseListener(this);
  }
  





  private Integer b()
  {
    Integer localInteger = Integer.valueOf(0);
    for (Object localObject = c.iterator(); ((Iterator)localObject).hasNext();) {
      localInteger = Integer.valueOf(localInteger.intValue() + ((Integer)((Iterator)localObject).next()).intValue());
    }
    localObject = Integer.valueOf(1000 - localInteger.intValue());
    return localObject;
  }
  

  private void c()
  {
    if (a.size() == 0) { return;
    }
    
    int m = b().intValue();
    if (m < 128) {
      return;
    }
    if (m > 250) {
      m = 250;
    }
    

    int n = 0;
    int i1 = 0;
    ArrayList localArrayList1 = new ArrayList();
    

    ArrayList localArrayList2 = new ArrayList();
    for (Object localObject1 = a.iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (MacroCommand)((Iterator)localObject1).next();
      if ((localObject2 instanceof Comment)) {
        localArrayList2.add(localObject2);
      }
    }
    a.removeAll(localArrayList2);
    
    for (localObject1 = a.iterator(); ((Iterator)localObject1).hasNext();) {
      localObject2 = (MacroCommand)((Iterator)localObject1).next();
      if (i1 + ((MacroCommand)localObject2).getLength() >= m) break;
      i1 += ((MacroCommand)localObject2).getLength();
      n++;
      localArrayList1.add(localObject2);
    }
    





    localArrayList1.add(new Emit(1));
    i1 += 2;
    

    localObject1 = new ByteArrayBuffer(i1);
    for (Object localObject2 = localArrayList1.iterator(); ((Iterator)localObject2).hasNext();) {
      MacroCommand localMacroCommand = (MacroCommand)((Iterator)localObject2).next();
      ((ByteArrayBuffer)localObject1).append(localMacroCommand.getByteRepresentation(), 0, localMacroCommand.getLength());
    }
    

    c.add(new Integer(i1));
    

    if (i != null) {
      i.sendCommand(new SaveMacroCommand((byte)1, ((ByteArrayBuffer)localObject1).toByteArray(), (byte)-2));
    }
    

    a.removeAll(localArrayList1);
    

    if ((b().intValue() > 128) && (a.size() > 0)) {
      c();
    }
  }
  




  public byte[] generateMacroData()
  {
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(256);
    Integer localInteger = Integer.valueOf(0);
    for (Iterator localIterator = a.iterator(); localIterator.hasNext();) {
      if (localInteger.intValue() > 248) {
        localObject = new Roll(0.0F, 0, 0);
        localByteArrayBuffer.append(((Roll)localObject).getByteRepresentation(), 0, ((Roll)localObject).getLength());
        break;
      }
      Object localObject = (MacroCommand)localIterator.next();
      localByteArrayBuffer.append(((MacroCommand)localObject).getByteRepresentation(), 0, ((MacroCommand)localObject).getLength());
      localInteger = Integer.valueOf(localInteger.intValue() + ((MacroCommand)localObject).getLength());
    }
    localByteArrayBuffer.append(0);
    return localByteArrayBuffer.toByteArray();
  }
  






  public byte[] getBytes()
  {
    int m = 0;
    for (Object localObject1 = a.iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (MacroCommand)((Iterator)localObject1).next();
      m += ((MacroCommand)localObject2).getLength();
    }
    
    localObject1 = new ByteArrayBuffer(m + 1);
    
    for (Object localObject2 = a.iterator(); ((Iterator)localObject2).hasNext();) { MacroCommand localMacroCommand = (MacroCommand)((Iterator)localObject2).next();
      ((ByteArrayBuffer)localObject1).append(localMacroCommand.getByteRepresentation(), 0, localMacroCommand.getLength());
    }
    
    ((ByteArrayBuffer)localObject1).append(0);
    
    return ((ByteArrayBuffer)localObject1).toByteArray();
  }
  
  public boolean isRunning() {
    return f;
  }
  
  public static abstract interface OnMacroUploadedListener
  {
    public abstract void onMacroUploaded(Robot paramRobot);
  }
  
  public static abstract interface OnEmitCallback
  {
    public abstract void onEmit(int paramInt);
  }
  
  public static enum MacroObjectMode
  {
    private MacroObjectMode() {}
  }
}
