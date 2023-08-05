package com.laioffer.lesson1.use5_封装线程调度;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.tools.RxUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

/**
 * 类的描述: 【RxJava思维编程】版本5: 封装线程调度操作
 * Created by 春夏秋冬在中南 on 2023/6/20 00:05
 */
public class RxUse5Activity extends AppCompatActivity {

  public static final String TAG = RxUse5Activity.class.getSimpleName();
  private final static String PATH = "http://pic1.win4000.com/wallpaper/c/53cdd1f7c1f21.jpg";

  private ProgressDialog mProgressDialog;

  public void download() {
    // 第2步: 分发事件
    // 起点: (Observable，被观察者)
    Observable.just(PATH)

        // 第3步: 将String转成Bitmap
        // Function类中，
        // T: 因为上层是PATH(String类型)，所以这里是String类型
        // R: 因为下面我们要获得Bitmap类型，所以这里是Bitmap类型，另外Observer中的泛型也改为Bitmap类型
        .map(new Function<String, Bitmap>() {
          @Override
          public Bitmap apply(String s) throws Exception {
            Bitmap bitmap = null;
            URL url = new URL(PATH);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(5000);
            int resCode = connection.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
              InputStream is = connection.getInputStream();
              bitmap = BitmapFactory.decodeStream(is);
            }
            return bitmap;
          }
        })

        // 绘制水印
        .map(new Function<Bitmap, Bitmap>() {
          @Override
          public Bitmap apply(Bitmap bitmap) throws Exception {
            Paint paint = new Paint();
            paint.setTextSize(88);
            paint.setColor(Color.RED);
            return drawTextToBitmap(bitmap, "大家好", paint, 88, 88);
          }
        })

        .compose(RxUtils.rxud())

        // subscribe: 将「起点」和「终点」订阅起来
        .subscribe(new Observer<Bitmap>() { // 终点(Observer，观察者)

          // 第1步: 预备: 订阅的开始，准备分发事件。(第2步才是分发事件)
          // 一订阅，就弹出loading弹窗
          @Override
          public void onSubscribe(Disposable d) {
            mProgressDialog = new ProgressDialog(RxUse5Activity.this);
            mProgressDialog.show();
          }

          // 第4步: 拿到事件，显示UI
          @Override
          public void onNext(Bitmap bitmap) {
            // setBitmap(bitmap);
          }

          // 错误事件
          @Override
          public void onError(Throwable e) {

          }

          // 第5步: 完成事件. 表示起点到终点的这一流程真正完成.
          @Override
          public void onComplete() {
            if (mProgressDialog != null) {
              mProgressDialog.dismiss();
            }
          }
        });
  }

  // 图片上绘制文字 加水印
  private Bitmap drawTextToBitmap(
      Bitmap bitmap,
      String text,
      Paint paint,
      int paddingLeft,
      int paddingTop) {
    Bitmap.Config bitmapConfig = bitmap.getConfig();

    paint.setDither(true); // 获取跟清晰的图像采样
    paint.setFilterBitmap(true);// 过滤一些
    if (bitmapConfig == null) {
      bitmapConfig = Bitmap.Config.ARGB_8888;
    }
    bitmap = bitmap.copy(bitmapConfig, true);
    Canvas canvas = new Canvas(bitmap);

    canvas.drawText(text, paddingLeft, paddingTop, paint);
    return bitmap;
  }
}
