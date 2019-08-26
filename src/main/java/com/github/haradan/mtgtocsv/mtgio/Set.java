package com.github.haradan.mtgtocsv.mtgio;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Set {

  private final String name;
  private final String code;
  private final boolean isCore;
  private final boolean isExpansion;

  public boolean isMainRelease() {
    return isCore || isExpansion;
  }

}
