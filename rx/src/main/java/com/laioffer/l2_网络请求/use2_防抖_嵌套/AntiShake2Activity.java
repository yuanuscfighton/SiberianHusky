package com.laioffer.l2_网络请求.use2_防抖_嵌套;

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

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;

/**
 * 类的描述: Rx使用场景2: 功能防抖 + 网络嵌套
 * Created by 春夏秋冬在中南 on 2023/7/29 22:41
 */
public class AntiShake2Activity extends AppCompatActivity {

  private final static String TAG = AntiShake2Activity.class.getSimpleName();

  private WanAndroidApi api;
  private Disposable mGetProjectDisposable;
  private Disposable mGetItemDisposable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout22);

    // 初始化
    api = HttpUtil.getOnlineCookieRetrofit().create(WanAndroidApi.class);

    // 功能防抖 + 网络嵌套 <-- 反面例子 🌰
    // antiShakeNestRequest();
    antiShakeNestRequestUpdate();
  }

  /**
   * RxBinding 防抖
   * <p>
   * 功能防抖 + 网络嵌套请求
   *
   * @noinspection ResultOfMethodCallIgnored
   */
  @SuppressLint("CheckResult")
  private void antiShakeNestRequest() {
    // 注意：（项目分类）查询的id，通过此id再去查询(项目列表数据)

    // 对那个控件防抖动
    Button bt_anti_shake = findViewById(R.id.bt_anti_shake);

    RxView.clicks(bt_anti_shake) // 起点是把Button传进来，Button就是事件
        .throttleFirst(2000, TimeUnit.MILLISECONDS) // 2秒钟之内，只响应一次
        .subscribe(new Consumer<Unit>() {
          @Override
          public void accept(Unit ignore) {
            // 查询主数据
            api.getProject()
                .compose(RxUtils.rxud())
                .subscribe(new Consumer<ProjectBean>() {
                  @Override
                  public void accept(ProjectBean projectBean) {
                    for (ProjectBean.DataBean dataBean : projectBean.getData()) {
                      // 查询 item 数据
                      api.getProjectItem(1, dataBean.getId())
                          .compose(RxUtils.rxud())
                          .subscribe(new Consumer<ProjectItem>() {
                            @Override
                            public void accept(ProjectItem projectItem) {
                              Log.e(TAG, "accept: " + projectBean);
                            }
                          });
                    }
                  }
                });
          }
        });
  }


  /**
   * 功能防抖 + 网络嵌套 <== 使用flatMap，解决嵌套的问题
   *
   * @noinspection ResultOfMethodCallIgnored
   */
  @SuppressLint("CheckResult")
  private void antiShakeNestRequestUpdate() {
    // 注意：项目分类查询的id，通过此id再去查询(项目列表数据)

    // 对 bt_anti_shake 控件做防抖动处理
    Button bt_anti_shake = findViewById(R.id.bt_anti_shake);

    // 防抖是在主线程的
    //noinspection ResultOfMethodCallIgnored
    RxView.clicks(bt_anti_shake)
        .throttleFirst(2000, TimeUnit.MILLISECONDS) // 2秒钟之内 响应你一次

        // 只给下面 切换 异步
        .observeOn(Schedulers.io())
        .flatMap(new Function<Unit, ObservableSource<ProjectBean>>() {
          @Override
          public ObservableSource<ProjectBean> apply(Unit ignore) {
            return api.getProject(); // 主数据
          }
        })
        .subscribe(new Consumer<ProjectBean>() {
          @Override
          public void accept(ProjectBean projectBean) {
            // ...
          }
        });
  }
}
