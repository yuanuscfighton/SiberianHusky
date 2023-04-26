package com.laioffer.viewpage2.demo1;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.fragment.viewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的描述: 数据适配器
 * Created by 春夏秋冬在中南 on 2023/4/26 07:45
 */
public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {

  private final List<Integer> mColorsList = new ArrayList<>();

  {
    mColorsList.add(android.R.color.holo_red_light);
    mColorsList.add(android.R.color.holo_purple);
    mColorsList.add(android.R.color.holo_blue_dark);
    mColorsList.add(android.R.color.holo_green_light);
  }

  @NonNull
  @Override
  public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(
            R.layout.vp2_item_page,
            parent,
            false);
    return new ViewPagerViewHolder(itemView);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
    holder.mContainer.setBackgroundResource(mColorsList.get(position));
    holder.mTitleTextView.setText("item " + position);
  }

  @Override
  public int getItemCount() {
    return mColorsList.size();
  }

  static class ViewPagerViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout mContainer;
    private TextView mTitleTextView;

    public ViewPagerViewHolder(@NonNull View itemView) {
      super(itemView);
      mContainer = itemView.findViewById(R.id.vp2_demo1_container);
      mTitleTextView = itemView.findViewById(R.id.vp2_demo1_title_text_view);
    }
  }
}
