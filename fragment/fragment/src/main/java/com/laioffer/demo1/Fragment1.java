package com.laioffer.demo1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laioffer.fragment.fragment.R;

/**
 * 类的描述: Fragment基本使用
 * Created by 春夏秋冬在中南 on 2023/6/8 23:17
 */
public class Fragment1 extends Fragment {
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment1, container, false);
  }
}
