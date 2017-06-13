package com.orbotix.common.internal;

import com.orbotix.common.DLog;
import com.orbotix.common.ResponseListener;
import com.orbotix.common.Robot;



public class ResponseMessageLogger
  implements ResponseListener
{
  public ResponseMessageLogger() {}
  
  public void handleResponse(DeviceResponse response, Robot robot)
  {
    DLog.i("Response    " + robot.getName() + " " + response.toString());
  }
  
  public void handleStringResponse(String stringResponse, Robot robot)
  {
    DLog.i("SResponse   " + robot.getName() + " " + "StringResponse: " + stringResponse);
  }
  
  public void handleAsyncMessage(AsyncMessage asyncMessage, Robot robot)
  {
    DLog.i("AsyncMessage " + robot.getName() + " " + asyncMessage.toString());
  }
}
