package com.laioffer.pkgx_享元模式;

import java.util.HashMap;
import java.util.Map;

public class WebSiteFactory {

  // 聚合 WebSite
  private final Map<String, WebSite> mPool = new HashMap<>();

  public WebSite getWebSiteCategory(String type) {
    if (!mPool.containsKey(type)) {
      mPool.put(type, new ConcreteWebSite(type));
    }

    return mPool.get(type);
  }

  public int getWebSiteCount() {
    return mPool.size();
  }
}
