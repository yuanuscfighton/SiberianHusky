package com.laioffer.demo1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.laioffer.fragment.viewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的描述: ViewPager的使用
 * Created by 春夏秋冬在中南 on 2023/4/25 23:34
 */
// 可以用来实现屏幕之间的切换
public class Demo1Activity extends AppCompatActivity {

  private final List<View> mViewList = new ArrayList<>();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo1);
    ViewPager viewPager = findViewById(R.id.demo1_viewpager);

    LayoutInflater inflater = getLayoutInflater();
    View view1 = inflater.inflate(R.layout.layout1, null);
    View view2 = inflater.inflate(R.layout.layout2, null);
    View view3 = inflater.inflate(R.layout.layout3, null);

    mViewList.add(view1);
    mViewList.add(view2);
    mViewList.add(view3);

    PagerAdapter pagerAdapter = new PagerAdapter() {

      /**
       * 返回要滑动的view的个数
       */
      @Override
      public int getCount() {
        return mViewList.size();
      }

      @Override
      public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
      }

      /**
       * 从当前container中删除指定位置（position）的View
       * @param container The containing View from which the page will be removed.
       * @param position The page position to be removed.
       * @param object The same object that was returned by {@link #instantiateItem(View, int)}.
       */
      @Override
      public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
      }

      /**
       * 1.将当前视图添加到container中
       * 2.返回当前View
       * @param container The containing View in which the page will be shown.
       * @param position The page position to be instantiated.
       */
      @NonNull
      @Override
      public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
      }
    };

    viewPager.setAdapter(pagerAdapter);
  }
}
