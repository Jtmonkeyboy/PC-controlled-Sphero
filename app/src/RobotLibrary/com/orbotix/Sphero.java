package com.orbotix;

import com.orbotix.common.Robot;

public class Sphero extends ConvenienceRobot {
  public Sphero(Robot robot) {
    super(robot);
  }
  
  public void disconnect()
  {
    _robot.disconnect();
  }
}
