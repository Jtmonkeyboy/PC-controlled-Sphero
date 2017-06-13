package com.orbotix;

import com.orbotix.common.Robot;

public class Ollie extends ConvenienceRobot {
  public Ollie(Robot robot) {
    super(robot);
  }
  
  public void disconnect()
  {
    _robot.sleep();
  }
}
