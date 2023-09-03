package com.laioffer.demo3_mvp.presenter;

import com.laioffer.my_mvx.demo3_mvp.model.Board;
import com.laioffer.my_mvx.demo3_mvp.model.Player;
import com.laioffer.my_mvx.demo3_mvp.view.IGameView;

/**
 * 版本3: mvp
 * presenter操作view，需要持有activity，但为了避免presenter持有activity，
 * 在mvp中定义了一个接口IView，presenter通过接口才可以操作view
 * presenter是controller角色，相当于mvc中的GameActivity
 */
public class GamePresenter {

  private final IGameView mIView;
  private final Board model;

  public GamePresenter(IGameView view) {
    this.mIView = view;
    this.model = new Board();
  }

  public void onButtonSelected(int row, int col) {
    Player playerThatMoved = model.mark(row, col);

    if (playerThatMoved != null) {
      mIView.setButtonText(row, col, playerThatMoved.toString());

      if (model.getWinner() != null) {
        mIView.showWinner(playerThatMoved.toString());
      }
    }
  }

  public void onResetSelected() {
    model.restart();
    mIView.clearWinnerDisplay();
    mIView.clearButtons();
  }
}
