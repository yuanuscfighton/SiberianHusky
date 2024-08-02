package com.laioffer.l2_网络请求.use1_网络请求.api;


import com.laioffer.l2_网络请求.use1_网络请求.bean.ProjectBean;
import com.laioffer.l2_网络请求.use1_网络请求.bean.ProjectItem;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 类的描述: 客户端api
 * Created by 春夏秋冬在中南 on 2023/7/29 21:25
 */
public interface WanAndroidApi {

  /**
   * 获取总数据
   * <p>
   * 1.接口的返回值类型是Observable，作为起点
   * 2.ProjectBean就是事件
   */
  @GET("project/tree/json")
  Observable<ProjectBean> getProject();  // 异步线程 耗时操作

  // Item数据
  @GET("project/list/{pageIndex}/json")
  // ?cid=294
  Observable<ProjectItem> getProjectItem(
      @Path("pageIndex") int pageIndex,
      @Query("cid") int cid);  // 异步线程 耗时操作
}

/*
服务器api接口:
    (1) 项目为包含一个分类，该接口返回整个分类:  https://www.wanandroid.com/project/tree/json
    (2) 某一个分类下项目列表数据:             https://www.wanandroid.com/project/list/1/json?cid=294

https://www.wanandroid.com 这部分是服务器的域名，作为BASE_URL
 */