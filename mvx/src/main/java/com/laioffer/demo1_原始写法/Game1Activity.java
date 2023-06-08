package com.laioffer.demo1_原始写法;

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

import com.laioffer.mvx.R;

/**
 * 类的描述: 版本1:一个Activity打天下
 * Created by 春夏秋冬在中南 on 2023/4/26 23:15
 */
public class Game1Activity extends AppCompatActivity {
  private static final String TAG = Game1Activity.class.getSimpleName();

  ////////////////////////////////////////////////////////
  // 以下是model部分
  public enum Player {X, O}

  public static class Cell {

    private Player value;

    public Player getValue() {
      return value;
    }

    public void setValue(Player value) {
      this.value = value;
    }
  }

  private final Cell[][] cells = new Cell[3][3];
  private Player winner;
  private GameState state;
  private Player currentTurn;

  private enum GameState {IN_PROGRESS, FINISHED}
  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////

  ////////////////////////////////////////////////////////
  // View
  private ViewGroup mGrids;
  private View mResultContainer;
  private TextView mWinnerLabel;
  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game_layout);
    mWinnerLabel = findViewById(R.id.winner_label);
    mResultContainer = findViewById(R.id.result_container);
    mGrids = findViewById(R.id.grids);
    restart();
  }

  ////////////////////////////////////////////////////////
  // View
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_game, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_reset) {
      restart();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////

  ////////////////////////////////////////////////////////
  // view+数据 <== 这里需要拆分
  public void onCellClicked(View v) {
    // 1.view，取出view
    Button button = (Button)v;
    String tag = button.getTag().toString();
    int row = Integer.parseInt(tag.substring(0, 1));
    int col = Integer.parseInt(tag.substring(1, 2));
    Log.i(TAG, "Click Row: [" + row + "," + col + "]");

    // 2.model，标记行&列
    Player playerThatMoved = mark(row, col);
    if (playerThatMoved != null) {
      button.setText(playerThatMoved.toString());
      if (getWinner() != null) {
        mWinnerLabel.setText(playerThatMoved.toString());
        mResultContainer.setVisibility(View.VISIBLE);
      }
    }
  }
  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////

  /**
   * 初始化数据，清除计分板和状态
   */
  public void restart() {
    ///////////////////////////////////////////////////////
    // 重置数据，reset model
    clearCells();
    winner = null;
    currentTurn = Player.X;
    state = GameState.IN_PROGRESS;
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////
    // 重置视图，reset view
    mResultContainer.setVisibility(View.GONE);
    mWinnerLabel.setText("");

    for (int i = 0; i < mGrids.getChildCount(); i++) {
      ((Button)mGrids.getChildAt(i)).setText("");
    }
    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
  }

  /**
   * 标记当前的选手选择了哪行哪列
   * 如果不是在没有选中的9个格子里面点击将视作无效；
   * 另外，如果游戏已经结束，本次标记忽略
   *
   * @return 返回当前选手，如果点击无效发挥为null
   */
  public Player mark(int row, int col) {
    Player playerThatMoved = null;

    if (isValid(row, col)) {
      cells[row][col].setValue(currentTurn);
      playerThatMoved = currentTurn;
      if (isWinningMoveByPlayer(currentTurn, row, col)) {
        state = GameState.FINISHED;
        winner = currentTurn;
      } else {
        // 切换到另外一起棋手，继续
        flipCurrentTurn();
      }
    }
    return playerThatMoved;
  }

  public Player getWinner() {
    return winner;
  }

  private void clearCells() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        cells[i][j] = new Cell();
      }
    }
  }

  private boolean isValid(int row, int col) {
    if (state == GameState.FINISHED) {
      return false;
    } else return !isCellValueAlreadySet(row, col);
  }

  private boolean isCellValueAlreadySet(int row, int col) {
    return cells[row][col].getValue() != null;
  }

  /**
   * @return 如果当前行、当前列、或者两天对角线为同一位棋手下的棋子返回为真
   */
  private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {

    return (cells[currentRow][0].getValue() == player         // 3-行
        && cells[currentRow][1].getValue() == player
        && cells[currentRow][2].getValue() == player
        || cells[0][currentCol].getValue() == player      // 3-列
        && cells[1][currentCol].getValue() == player
        && cells[2][currentCol].getValue() == player
        || currentRow == currentCol            // 3-对角线
        && cells[0][0].getValue() == player
        && cells[1][1].getValue() == player
        && cells[2][2].getValue() == player
        || currentRow + currentCol == 2    // 3-反对角线
        && cells[0][2].getValue() == player
        && cells[1][1].getValue() == player
        && cells[2][0].getValue() == player);
  }

  private void flipCurrentTurn() {
    currentTurn = currentTurn == Player.X ? Player.O : Player.X;
  }
}
