package com.laioffer.demo3;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.laioffer.demo1.Fragment1;
import com.laioffer.demo1.Fragment2;
import com.laioffer.fragment.fragment.R;

/**
 * 类的描述: demo2:动态添加fragment
 * Created by 春夏秋冬在中南 on 2023/6/10 02:25
 */
public class Demo3Activity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo3);

    Button btn1 = findViewById(R.id.btn_show_fragment1);
    btn1.setOnClickListener(v -> {
      // 将Fragment1添加到Activity的container中
      Fragment1 fragment1 = new Fragment1();
      addFragment(fragment1, "fragment1");
    });

    Button btn2 = findViewById(R.id.btn_show_fragment2);
    btn2.setOnClickListener(v -> {
      // 将Fragment2添加到Activity的container中
      Fragment2 fragment2 = new Fragment2();
      addFragment(fragment2, "fragment2");
    });

    Button btn3 = findViewById(R.id.btn_remove_fragment2);
    btn3.setOnClickListener(v -> {
      // 移出Fragment2
      removeFragment2();
    });

    Button btn4 = findViewById(R.id.btn_replace_fragment1);
    btn4.setOnClickListener(v -> {
      // 用Fragment2替换掉Fragment1
      replaceFragment1();
    });
  }

  /**
   * 添加fragment到Activity中
   *
   * @param fragment 将要被添加的fragment实例
   * @param tag      和该fragment关联起来，当通过findFragmentByTag()的时候，就可以根据该tag找到这个Fragment实例
   */
  private void addFragment(@NonNull Fragment fragment, @NonNull String tag) {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.add(R.id.fragment_container, fragment, tag);
    ft.commit();
  }

  private void removeFragment2() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentByTag("fragment2");
    if (fragment != null) {
      FragmentTransaction transaction = fm.beginTransaction();
      transaction.remove(fragment);
      transaction.commit();
    }
  }

  private void replaceFragment1() {
    FragmentManager fm = getSupportFragmentManager();
    Fragment2 fragment2 = new Fragment2();
    FragmentTransaction ft = fm.beginTransaction();
    // replace操作会把这个container中所有fragment清空，然后再把fragment2添加进去
    ft.replace(R.id.fragment_container, fragment2);
    ft.commit();
  }
}
