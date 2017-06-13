package com.orbotix.command;

import com.orbotix.common.utilities.binary.Maskable;











public enum NonPersistentOptionFlags
  implements Maskable
{
  private final long a;
  
  private NonPersistentOptionFlags(long flag)
  {
    a = flag;
  }
  
  public long longValue() {
    return a;
  }
}
