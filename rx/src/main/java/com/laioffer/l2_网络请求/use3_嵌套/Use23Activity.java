package com.laioffer.l2_网络请求.use3_嵌套;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.view.RxView;
import com.laioffer.l2_网络请求.use1_网络请求.api.WanAndroidApi;
import com.laioffer.l2_网络请求.use1_网络请求.bean.ProjectBean;
import com.laioffer.l2_网络请求.use1_网络请求.bean.ProjectItem;
import com.laioffer.l2_网络请求.use1_网络请求.util.HttpUtil;
import com.laioffer.rx.R;
import com.laioffer.tools.RxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;

/**
 * 类的描述: Rx使用场景2: 功能防抖 + 网络嵌套
 * Created by 春夏秋冬在中南 on 2023/7/29 22:41
 */
public class Use23Activity extends AppCompatActivity {

  private final static String TAG = Use23Activity.class.getSimpleName();

  private WanAndroidApi api;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout22);

    // 初始化
    api = HttpUtil.getOnlineCookieRetrofit().create(WanAndroidApi.class);

    // 网络嵌套
    // nestRequest();
    antiNestRequest();
  }

  /**
   * 网络嵌套 ==> 这种是负面教程，嵌套的太厉害了
   *
   * @noinspection ResultOfMethodCallIgnored
   */
  @SuppressLint("CheckResult")
  private void nestRequest() {
    // 注意：（项目分类）查询的id，通过此id再去查询(项目列表数据)

    api.getProject() // 查询主数据
        .compose(RxUtils.rxud())
        .subscribe(new Consumer<ProjectBean>() {
          @Override
          public void accept(ProjectBean projectBean) {
            for (ProjectBean.DataBean dataBean : projectBean.getData()) { // 10
              // 查询item数据
              api.getProjectItem(1, dataBean.getId())
                  .compose(RxUtils.rxud())
                  .subscribe(new Consumer<ProjectItem>() {
                    @Override
                    public void accept(ProjectItem projectItem) {
                      Log.d(TAG, "accept: " + projectItem); // 可以UI操作
                    }
                  });
            }
          }
        });
  }


  /**
   * 网络嵌套 <== 使用flatMap，解决嵌套的问题
   *
   * @noinspection ResultOfMethodCallIgnored
   */
  @SuppressLint("CheckResult")
  private void antiNestRequest() {
    // 注意：项目分类查询的id，通过此id再去查询(项目列表数据)

    api.getProject() // 主数据
        .flatMap(new Function<ProjectBean, ObservableSource<ProjectBean.DataBean>>() {
          @Override
          public ObservableSource<ProjectBean.DataBean> apply(ProjectBean projectBean) {
            return Observable.fromIterable(projectBean.getData()); // 自己创建一个发射器，发多次
            // 和上面的for(ProjectBean.DataBean dataBean : projectBean.getData()) 等价
          }
        })
        .flatMap(new Function<ProjectBean.DataBean, ObservableSource<ProjectItem>>() {
          @Override
          public ObservableSource<ProjectItem> apply(ProjectBean.DataBean dataBean) {
            return api.getProjectItem(1, dataBean.getId());
          }
        })

        .observeOn(AndroidSchedulers.mainThread()) // 给下面切换 主线程
        .subscribe(new Consumer<ProjectItem>() {
          @Override
          public void accept(ProjectItem projectItem) {
            // 如果我要更新UI 会报错2 不会报错1
            Log.d(TAG, "accept: " + projectItem);
          }
        });
  }
}
