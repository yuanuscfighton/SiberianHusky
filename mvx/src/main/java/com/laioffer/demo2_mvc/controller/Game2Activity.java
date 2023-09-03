package com.laioffer.demo2_mvc.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.my_mvx.R;
import com.laioffer.my_mvx.demo2_mvc.model.Board;
import com.laioffer.my_mvx.demo2_mvc.model.Player;

/**
 * 版本2: mvc
 * <p>
 * 在mvc中，Game2Activity充当了controller角色
 * （1）对view的操作
 * （2）view和model之间的交互
 * （3）view的初始化，都放在了activity中
 */
public class Game2Activity extends AppCompatActivity {

  private static final String TAG = Game2Activity.class.getSimpleName();

  private Board model;

  private ViewGroup mGrids;
  private View mResultContainer;
  private TextView mWinnerLabel;

  ////////////////////////////////////////////////////////////////
  // 做了一些view初始化的操作
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game_layout);
    mGrids = findViewById(R.id.grids);
    mWinnerLabel = findViewById(R.id.winner_label);
    mResultContainer = findViewById(R.id.result_container);
    model = new Board();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_game, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_reset) {
      // 相对于一个版本1，区分了重置model还是view
      // 1.重置model
      model.restart();
      // 2.重置view
      resetView();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  ////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////

  // view和model之间的交互，这才是真正的逻辑部分 ==> 属于Controller
  public void onCellClicked(View v) {
    Button button = (Button)v;
    String tag = button.getTag().toString();

    // 操作view ==> 获取点击的是哪一行哪一列
    int row = Integer.parseInt(tag.substring(0, 1));
    int col = Integer.parseInt(tag.substring(1, 2));
    Log.i(TAG, "Click Row: [" + row + "," + col + "]");

    // 操作model ==> 存储数据，调用model#mark()方法
    Player playerThatMoved = model.mark(row, col);

    if (playerThatMoved != null) {
      // 操作view ==> 根据model返回的结果，对view进行操作
      button.setText(playerThatMoved.toString());
      if (model.getWinner() != null) {
        mWinnerLabel.setText(playerThatMoved.toString());
        mResultContainer.setVisibility(View.VISIBLE);
      }
    }
  }

  ////////////////////////////////////////////////////////////////
  // 操作view
  public void resetView() {
    mResultContainer.setVisibility(View.GONE);
    mWinnerLabel.setText("");

    for (int i = 0; i < mGrids.getChildCount(); i++) {
      ((Button)mGrids.getChildAt(i)).setText("");
    }
  }
  ////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////
}
