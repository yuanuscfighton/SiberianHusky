package com.laioffer.lesson4_viewmodel.use2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.R;

/**
 * TODO 传统方式的模式
 * <p>
 * 功能越多，代码越多，下面的缺点就越大
 * <p>
 * 1.Activity 或 Fragment 是大管家，代码脓肿
 * <p>
 * 2.Activity 要处理逻辑
 * <p>
 * 3.Activity 要处理Model数据  UI 数据， 不仅管理了 却又管不好（横竖屏切换 数据丢失）
 * <p>
 * 4.Activity 要实时刷新UI，例如：更新TextView
 * <p>
 * 5.Activity 如果横竖屏切换 会丢失数据（号码数据一定会丢失的）
 * <p>
 * 6.焊死，不能灵活拆卸
 * <p>
 * ...... 等等
 */
public class Use2Activity extends AppCompatActivity implements View.OnClickListener {

  private final static String TAG = Use2Activity.class.getSimpleName();

  private TextView phone; // 显示的号码  UI的　数据
  private Button number1; // 键盘按键
  private Button number2; // 键盘按键

  private Button number3; // 键盘按键
  private Button number4;// 键盘按键
  private Button number5; // 键盘按键

  private Button number6; // 键盘按键
  private Button number7; // 键盘按键
  private Button number8; // 键盘按键

  private Button number9; // 键盘按键
  private Button numberXin; // 键盘按键 *
  private Button number0; // 键盘按键0

  private Button numberJin; // 键盘按键 #
  private Button numberMin; // 键盘按键 清空
  private ImageView numberCall; // call 拨号

  private Button numberBackspace; // 删除

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_use2);

    initView();
  }

  private void initView() {
    phone = findViewById(R.id.tv_phone);
    number1 = findViewById(R.id.bt_number1);
    number2 = findViewById(R.id.bt_number2);

    number3 = findViewById(R.id.bt_number3);
    number4 = findViewById(R.id.bt_number4);
    number5 = findViewById(R.id.bt_number5);

    number6 = findViewById(R.id.bt_number6);
    number7 = findViewById(R.id.bt_number7);
    number8 = findViewById(R.id.bt_number8);

    number9 = findViewById(R.id.bt_number9);
    numberXin = findViewById(R.id.bt_number_xin); // *
    number0 = findViewById(R.id.bt_number0);

    numberJin = findViewById(R.id.bt_number_jin);
    numberMin = findViewById(R.id.bt_number_min); // 清空
    numberCall = findViewById(R.id.iv_number_call); // 拨打

    numberBackspace = findViewById(R.id.bt_number_backspace); // 删除

    // 下面是点击事件监听
    number1.setOnClickListener(this);
    number2.setOnClickListener(this);
    number3.setOnClickListener(this);
    number4.setOnClickListener(this);
    number5.setOnClickListener(this);
    number6.setOnClickListener(this);
    number7.setOnClickListener(this);
    number8.setOnClickListener(this);
    number9.setOnClickListener(this);
    numberXin.setOnClickListener(this);
    number0.setOnClickListener(this);

    numberJin.setOnClickListener(this);
    numberMin.setOnClickListener(this);
    numberCall.setOnClickListener(this);

    numberBackspace.setOnClickListener(this);
  }

  /**
   * 点击
   */
  @Override
  public void onClick(View view) {

    if (view.getId() == R.id.bt_number1) {
      appendNumber("1");
    } else if (view.getId() == R.id.bt_number2) {
      appendNumber("2");
    } else if (view.getId() == R.id.bt_number3) {
      appendNumber("3");
    } else if (view.getId() == R.id.bt_number4) {
      appendNumber("4");
    } else if (view.getId() == R.id.bt_number5) {
      appendNumber("5");
    } else if (view.getId() == R.id.bt_number6) {
      appendNumber("6");
    } else if (view.getId() == R.id.bt_number7) {
      appendNumber("7");
    } else if (view.getId() == R.id.bt_number8) {
      appendNumber("8");
    } else if (view.getId() == R.id.bt_number9) {
      appendNumber("9");
    } else if (view.getId() == R.id.bt_number_xin) {
      appendNumber("*");
    } else if (view.getId() == R.id.bt_number0) {
      appendNumber("0");
    } else if (view.getId() == R.id.bt_number_jin) {
      appendNumber("#");
    } else if (view.getId() == R.id.bt_number_min) {
      clear();
    } else if (view.getId() == R.id.iv_number_call) {
      callPhone();
    } else if (view.getId() == R.id.bt_number_backspace) {
      backspaceNumber();
    }
  }

  // 传统方式的 数据
  private String phoneInfo = "";

  /**
   * 输入
   */
  private void appendNumber(String number) {

    if (null != phone) {
      phoneInfo += number; // 弊端 性能 sb去完成
      Log.e(TAG, "phoneInfo:" + phoneInfo);
      phone.setText(phoneInfo + "");
    }
  }

  /**
   * 删除
   */
  private void backspaceNumber() {
    int length = phoneInfo.length();
    if (length > 0) {
      phoneInfo = phoneInfo.substring(0, phoneInfo.length() - 1);
      phone.setText(phoneInfo + "");
    }
  }

  /**
   * 清空
   */
  private void clear() {
    phoneInfo = "";
    phone.setText("");
  }

  /**
   * 拨打
   */
  private void callPhone() {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_CALL);
    intent.setData(Uri.parse("tel:" + phoneInfo));
    this.startActivity(intent);
  }

  // 例如：9000行代码，代码越多 问题越大
  // ...
}
