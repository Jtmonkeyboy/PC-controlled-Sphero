package com.orbotix.le.connectstrategy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.orbotix.common.Robot;
import java.lang.ref.WeakReference;
import java.util.List;





public class FavoriteRobotConnectStrategy
  implements ConnectStrategy
{
  private final String a = "shared_preferences";
  private final String b = "favorite_robot_serial";
  

  private WeakReference<Context> c;
  

  public FavoriteRobotConnectStrategy(@NonNull Context context)
  {
    c = new WeakReference(context);
  }
  
  @Nullable
  public Robot getRobotToConnectFromAvailableNodes(List<Robot> availableRobots, Robot latestRobot)
  {
    String str = getFavorite();
    
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    
    return a(str, availableRobots);
  }
  
  @Nullable
  private Robot a(@NonNull String paramString, @NonNull List<Robot> paramList)
  {
    for (Robot localRobot : paramList) {
      if (paramString.equalsIgnoreCase(localRobot.getSerialNumber())) {
        return localRobot;
      }
    }
    
    return null;
  }
  



  public void setFavorite(String favoriteSerial)
  {
    if ((c == null) || (c.get() == null)) {
      return;
    }
    SharedPreferences localSharedPreferences = ((Context)c.get()).getSharedPreferences("shared_preferences", 0);
    if (localSharedPreferences != null) {
      if (TextUtils.isEmpty(favoriteSerial)) {
        localSharedPreferences.edit().remove("favorite_robot_serial").commit();
      } else {
        localSharedPreferences.edit().putString("favorite_robot_serial", favoriteSerial).commit();
      }
    }
  }
  



  @Nullable
  public String getFavorite()
  {
    if ((c == null) || (c.get() == null)) {
      return null;
    }
    SharedPreferences localSharedPreferences = ((Context)c.get()).getSharedPreferences("shared_preferences", 0);
    if ((localSharedPreferences != null) && (localSharedPreferences.contains("favorite_robot_serial"))) {
      return localSharedPreferences.getString("favorite_robot_serial", null);
    }
    return null;
  }
}
