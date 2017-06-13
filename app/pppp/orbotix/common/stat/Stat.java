package com.orbotix.common.stat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class Stat
{
  private static final String a = "mac";
  private static final String b = "string";
  private static final String c = "number";
  private static final String d = "data";
  private static final String e = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  private StatKey f;
  private String g;
  private String h;
  private Map<String, String> i;
  
  public static enum StatKey
  {
    private String a;
    
    @Nullable
    public static StatKey statKeyForString(@NonNull String s)
    {
      for (StatKey localStatKey : ) {
        if (localStatKey.getValue().equals(s)) {
          return localStatKey;
        }
      }
      return null;
    }
    
    private StatKey(String value) {
      a = value;
    }
    
    public String getValue() {
      return a;
    }
  }
  







  public static enum StatDataKey
  {
    private String a;
    






    private StatDataKey(String value)
    {
      a = value;
    }
    

    public String getValue() { return a; }
    
    @Nullable
    public static StatDataKey statDataKeyForString(@NonNull String s) {
      for (StatDataKey localStatDataKey : ) {
        if (localStatDataKey.getValue().equals(s)) {
          return localStatDataKey;
        }
      }
      return null;
    }
  }
  











  public Stat(StatKey key, String mac)
  {
    i = new HashMap();
    h = filterMac(mac);
    f = key;
    g = key.getValue();
    addData(StatDataKey.ID, a());
    addData(StatDataKey.TIME, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new java.util.Date()));
  }
  
  private Stat(String statData) throws JSONException {
    JSONObject localJSONObject1 = new JSONObject(statData);
    JSONObject localJSONObject2 = new JSONObject(localJSONObject1.getString("data"));
    h = localJSONObject1.getString("mac");
    g = localJSONObject1.getString("string");
    f = StatKey.statKeyForString(localJSONObject1.getString("number"));
    i = new HashMap();
    Iterator localIterator = localJSONObject2.keys();
    while (localIterator.hasNext()) {
      String str = (String)localIterator.next();
      StatDataKey localStatDataKey = StatDataKey.statDataKeyForString(str);
      addData(localStatDataKey, localJSONObject2.getString(str));
    }
  }
  
  public void addData(StatDataKey key, String value) {
    SLog.log(String.format("Stat: Adding %s: %s", new Object[] { key, value }));
    i.put(key.getValue(), value);
  }
  
  public void addData(StatDataKey key, long value) {
    addData(key, String.format("%d", new Object[] { Long.valueOf(value) }));
  }
  
  public Map<String, String> getDataMap()
  {
    return java.util.Collections.unmodifiableMap(i);
  }
  
  public String serialize() {
    String str = null;
    try {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("mac", h);
      localJSONObject1.put("string", g);
      localJSONObject1.put("number", String.valueOf(f.getValue()));
      JSONObject localJSONObject2 = new JSONObject(i);
      localJSONObject1.put("data", localJSONObject2);
      str = localJSONObject1.toString();
      SLog.log("Stat: Serialized: " + str);
    } catch (JSONException localJSONException) {
      SLog.log("Stat: Could not serialize stat because of reasons: " + localJSONException.getMessage());
      localJSONException.printStackTrace();
    }
    return str;
  }
  
  public JSONObject serializeForWeb() {
    return new JSONObject(i);
  }
  
  public static Stat deserialize(String statData) {
    Stat localStat = null;
    try {
      localStat = new Stat(statData);
      SLog.log("Stat: Deserialized stat: " + localStat);
    } catch (JSONException localJSONException) {
      SLog.log("Stat: Could not deserialize stat because of reasons: " + localJSONException.getMessage());
      localJSONException.printStackTrace();
    }
    return localStat;
  }
  
  public static String filterMac(String mac) {
    return mac.replace(":", "").replace("-", "");
  }
  
  public String toString()
  {
    return "Stat{statKey=" + f + ", stringStatKey='" + g + '\'' + ", associatedMac='" + h + '\'' + ", baseMap=" + i + '}';
  }
  



  public StatKey getStatKey()
  {
    return f;
  }
  
  public String getStringStatKey() {
    return g;
  }
  
  public String getAssociatedMac() {
    return h;
  }
  
  public Map<String, String> getBaseMap() {
    return i;
  }
  
  private static String a() {
    return UUID.randomUUID().toString();
  }
}
