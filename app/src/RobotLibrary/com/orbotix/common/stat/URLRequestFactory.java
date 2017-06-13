package com.orbotix.common.stat;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class URLRequestFactory
{
  private static final String a = "sphe172c542260dd83c709eba5a449efe59a";
  private static final String b = "yKqlueWG2GLVyrAkcAn6";
  private static final String c = "jx7CBqFzb9yH7nVETUAPGcr64y8oUJfOHO0zF1wNOheEYBftbsy9K27xf7cK";
  private static final String d = "X-GO-SPHERO-SDK-TOKEN: ";
  private static final String e = "Content-Type";
  private static final String f = "application/x-www-form-urlencoded";
  private static final String g = "macAddress";
  private static final String h = "model_number";
  private static final String i = "sku";
  private static final String j = "user";
  private static final String k = "appClientId";
  private static final int l = 15;
  
  public URLRequestFactory() {}
  
  public static HttpPost requestWithURLAndStats(String url, ArrayList<Stat> stats)
  {
    HttpPost localHttpPost = new HttpPost(url);
    String str1 = a();
    String str2 = a(stats);
    SLog.log("URLRequestFactory: Using URL: " + url);
    SLog.log("URLRequestFactory: Using header: " + str1);
    SLog.log("URLRequestFactory: Using JSON: " + str2);
    if ((str1 == null) || (str1.length() == 0)) {
      return null;
    }
    String[] arrayOfString = str1.split(": ");
    if (arrayOfString.length != 2) {
      throw new RuntimeException("Improperly formatted header");
    }
    localHttpPost.addHeader(arrayOfString[0], arrayOfString[1]);
    localHttpPost.getParams().setParameter("Content-Type", "application/x-www-form-urlencoded");
    try {
      localHttpPost.setEntity(new org.apache.http.entity.StringEntity(str2));
    } catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
      SLog.log("URLRequestFactory: Encoding of JSON body is not supported");
      return null;
    }
    SLog.log("URLRequestFactory: Created request: " + localHttpPost);
    return localHttpPost;
  }
  
  private static String a() {
    long l1 = System.currentTimeMillis();
    String str1 = String.format("%d", new Object[] { Long.valueOf(l1) });
    String str2 = str1 + "jx7CBqFzb9yH7nVETUAPGcr64y8oUJfOHO0zF1wNOheEYBftbsy9K27xf7cK";
    String str3 = a(str2);
    return String.format("%s%s:%s", new Object[] { "X-GO-SPHERO-SDK-TOKEN: ", str1, str3 });
  }
  
  private static String a(ArrayList<Stat> paramArrayList) {
    if ((null == paramArrayList) || (paramArrayList.size() == 0)) {
      SLog.log("URLRequestFactory: No stats to generate JSON for!");
      return null;
    }
    Stat localStat1 = (Stat)paramArrayList.get(0);
    

    HashMap localHashMap = new HashMap();
    for (Object localObject1 = paramArrayList.iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (Stat)((Iterator)localObject1).next();
      if (!localHashMap.containsKey(((Stat)localObject2).getStringStatKey())) {
        localHashMap.put(((Stat)localObject2).getStringStatKey(), new ArrayList());
      }
      ((ArrayList)localHashMap.get(((Stat)localObject2).getStringStatKey())).add(localObject2);
    }
    Object localObject2;
    localObject1 = new JSONObject();
    try {
      ((JSONObject)localObject1).put("macAddress", localStat1.getAssociatedMac());
      ((JSONObject)localObject1).put("user", "");
      ((JSONObject)localObject1).put("appClientId", "sphe172c542260dd83c709eba5a449efe59a");
      for (localObject2 = localHashMap.keySet().iterator(); ((Iterator)localObject2).hasNext();) { String str = (String)((Iterator)localObject2).next();
        JSONArray localJSONArray = new JSONArray();
        for (Stat localStat2 : (ArrayList)localHashMap.get(str)) {
          localJSONArray.put(localStat2.serializeForWeb());
        }
        ((JSONObject)localObject1).put(str, localJSONArray);
      }
    } catch (JSONException localJSONException) {
      SLog.log("URLRequestFactory: Unable to serialize json, reason + " + localJSONException.getMessage());
      return null;
    }
    return ((JSONObject)localObject1).toString();
  }
  
  private static String a(String paramString) {
    try {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte1 = localMessageDigest.digest();
      
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i1 : arrayOfByte1) {
        String str = Integer.toHexString(0xFF & i1);
        while (str.length() < 2) {
          str = "0" + str;
        }
        localStringBuilder.append(str);
      }
      return localStringBuilder.toString();
    } catch (java.security.NoSuchAlgorithmException localNoSuchAlgorithmException) {
      SLog.log("URLRequestFactory: Cannot find the MD5 algorithm!"); }
    return null;
  }
}
