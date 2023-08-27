package com.laioffer.youtube.v5_method_inject;

import javax.inject.Inject;

public class Remote {

  @Inject
  public Remote() {
  }

  public void setListener(Car car) {
    // Log.d(Dagger2LogTag.CAR5_LOG_TAG, "Remote connected");
  }
}
