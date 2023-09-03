package com.laioffer.demo3_mvp.view;

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
import com.laioffer.demo3_mvp.presenter.GamePresenter;

// mvp中Game3Activity是view的角色
public class Game3Activity extends AppCompatActivity implements IGameView {

  private static final String TAG = Game3Activity.class.getSimpleName();

  private ViewGroup mGrids;
  private View mResultContainer;
  private TextView mWinnerLabel;

  GamePresenter mPresenter = new GamePresenter(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game_layout);
    mGrids = findViewById(R.id.grids);
    mWinnerLabel = findViewById(R.id.winner_label);
    mResultContainer = findViewById(R.id.result_container);
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
      /**
       * 1.改进后: view响应点击事件，点击按钮后做什么事情，直接传给presenter，view和model之间的交互交给presenter来完成。
       * 2.对比mvc: 在mvc中既操作model，又操作view，
       * 因为{@link com.laioffer.my_mvx.demo2_mvc.controller.Game2Activity#onOptionsItemSelected(MenuItem)}
       * 既持有model，又持有view。
       * ==> 但在mvp中，Game3Activity是view，只负责接收事件，接收事件后做什么操作，交给presenter完成。
       */
      mPresenter.onResetSelected();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void onCellClicked(View v) {
    Button button = (Button)v;
    String tag = button.getTag().toString();
    int row = Integer.parseInt(tag.substring(0, 1));
    int col = Integer.parseInt(tag.substring(1, 2));
    Log.i(TAG, "Click Row: [" + row + "," + col + "]");
    // 只接收(响应)事件，不关心事件怎么处理的
    mPresenter.onButtonSelected(row, col);
  }

  @Override
  public void setButtonText(int row, int col, String text) {
    Button btn = mGrids.findViewWithTag("" + row + col);
    if (btn != null) {
      btn.setText(text);
    }
  }

  public void clearButtons() {
    for (int i = 0; i < mGrids.getChildCount(); i++) {
      ((Button)mGrids.getChildAt(i)).setText("");
    }
  }

  public void showWinner(String winningPlayerDisplayLabel) {
    mWinnerLabel.setText(winningPlayerDisplayLabel);
    mResultContainer.setVisibility(View.VISIBLE);
  }

  public void clearWinnerDisplay() {
    mResultContainer.setVisibility(View.GONE);
    mWinnerLabel.setText("");
  }
}
