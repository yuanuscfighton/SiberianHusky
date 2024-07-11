package com.laioffer;

import com.laioffer.network.BuildConfig;

/**
 * 类描述: 常量
 * <p>
 * IgnoreConstants 隐藏 API_KEY
 * <p>
 * created by 春夏秋冬在中南 on 2024/3/12 22:45
 */
public class Constants {

  public static final String BASE_URL = "https://apis.tianapi.com";

  public static final String KEJI = "/kepi/index";

  public static final String PUBLIC_API_KEY = BuildConfig.isPublic ? "YOUR_API_KEY..." : IgnoreConstants.API_KEY;

}
