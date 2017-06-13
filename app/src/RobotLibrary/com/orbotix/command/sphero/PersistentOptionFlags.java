package com.orbotix.command.sphero;

import com.orbotix.common.utilities.binary.Maskable;
















public enum PersistentOptionFlags
  implements Maskable
{
  private final long a;
  
  private PersistentOptionFlags(long flag)
  {
    a = flag;
  }
  
  public long longValue()
  {
    return a;
  }
}
