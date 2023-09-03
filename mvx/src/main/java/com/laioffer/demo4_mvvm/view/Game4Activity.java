package com.laioffer.demo4_mvvm.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.laioffer.my_mvx.R;
import com.laioffer.my_mvx.databinding.GameLayoutMvvmBinding;
import com.laioffer.demo4_mvvm.viewmodel.GameViewModel;

public class Game4Activity extends AppCompatActivity {

  GameViewModel viewModel = new GameViewModel();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GameLayoutMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.game_layout_mvvm);
    binding.setViewModel(viewModel);
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
      viewModel.onResetSelected();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
