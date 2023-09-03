package com.laioffer.demo4_mvvm.viewmodel;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableField;

import com.laioffer.demo4_mvvm.model.Board;
import com.laioffer.demo4_mvvm.model.Player;

public class GameViewModel {

  private final Board model;

  // 当cells变化的时候，xml中的text内容字段变化
  public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
  public final ObservableField<String> winner = new ObservableField<>();

  public GameViewModel() {
    model = new Board();
  }

  public void onResetSelected() {
    model.restart();
    winner.set(null);
    cells.clear();
  }

  // 这里有点击事件，能自动把row和col数据带过来
  public void onClickedCellAt(int row, int col) {
    Player playerThatMoved = model.mark(row, col);
    if (playerThatMoved != null) {
      // 数据更新
      cells.put("" + row + col, playerThatMoved.toString());
      winner.set(model.getWinner() == null ? null : model.getWinner().toString());
    }
  }
}
