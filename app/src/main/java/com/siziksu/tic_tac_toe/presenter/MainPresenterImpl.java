package com.siziksu.tic_tac_toe.presenter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.siziksu.tic_tac_toe.R;
import com.siziksu.tic_tac_toe.game.GameLogic;

import java.util.HashMap;
import java.util.Map;

public class MainPresenterImpl implements MainPresenter, View.OnClickListener {

  private static final int X_IMAGE = R.drawable.cell_x;
  private static final int O_IMAGE = R.drawable.cell_o;
  private static final int X_IMAGE_WIN = R.drawable.cell_x_win;
  private static final int O_IMAGE_WIN = R.drawable.cell_o_win;

  private final Context context;

  private GenericView genericView;

  private Map<Integer, ImageView> imageViewMap;

  private GameLogic logic;

  public MainPresenterImpl(Context context) {
    this.context = context;
    this.logic = new GameLogic();
    imageViewMap = new HashMap<Integer, ImageView>();
  }

  /**
   * Initializes the game setting the board images and the click listener
   *
   * @param activity activity
   */
  @Override
  public void init(AppCompatActivity activity) {
    imageViewMap.put(R.id.cell_100, ((ImageView) activity.findViewById(R.id.cell_100)));
    imageViewMap.put(R.id.cell_010, ((ImageView) activity.findViewById(R.id.cell_010)));
    imageViewMap.put(R.id.cell_001, ((ImageView) activity.findViewById(R.id.cell_001)));
    imageViewMap.put(R.id.cell_200, ((ImageView) activity.findViewById(R.id.cell_200)));
    imageViewMap.put(R.id.cell_020, ((ImageView) activity.findViewById(R.id.cell_020)));
    imageViewMap.put(R.id.cell_002, ((ImageView) activity.findViewById(R.id.cell_002)));
    imageViewMap.put(R.id.cell_300, ((ImageView) activity.findViewById(R.id.cell_300)));
    imageViewMap.put(R.id.cell_030, ((ImageView) activity.findViewById(R.id.cell_030)));
    imageViewMap.put(R.id.cell_003, ((ImageView) activity.findViewById(R.id.cell_003)));
    for (ImageView imageView : imageViewMap.values()) {
      imageView.setOnClickListener(this);
    }
  }

  @Override
  public void registerGenericView(GenericView view) {
    this.genericView = view;
  }

  @Override
  public void unregisterGenericView() {
    this.genericView = null;
  }

  @Override
  public String getStatistics() {
    float[] stats = logic.statistics(context);
    return "TOTAL GAMES PLAYED: " + (int) stats[0] + "\n"
        + "PLAYER 1 WINS: " + (int) stats[1] + " (" + stats[2] + "%" + ")" + "\n"
        + "PLAYER 2 WINS: " + (int) stats[3] + " (" + stats[4] + "%" + ")";
  }

  /**
   * Resets the statistics and prints
   */
  @Override
  public void resetStatistics() {
    logic.resetStatistics(context);
    genericView.printStatistics();
  }

  @Override
  public void replay() {
    for (ImageView imageView : imageViewMap.values()) {
      imageView.setEnabled(true);
    }
    genericView.hideBlocker();
    resetBoard();
  }

  private void resetBoard() {
    for (ImageView imageView : imageViewMap.values()) {
      imageView.setImageDrawable(ContextCompat.getDrawable(context, android.R.color.transparent));
    }
    logic.resetBoard();
  }

  @Override
  public void onClick(View v) {
    click(v);
  }

  private void click(View view) {
    int cell = view.getId();
    logic.addMove();
    int value = logic.getValueOfTheMove();
    int drawable = logic.isTurnForX() ? X_IMAGE : O_IMAGE;
    logic.setValueForTheCell(cell, value);
    imageViewMap.get(cell).setImageDrawable(
        ContextCompat.getDrawable(context, drawable)
    );
    logic.setTurn();
    view.setEnabled(false);
    checkGameOver();
  }

  private void checkGameOver() {
    logic.printBoard();
    boolean over = gameOver() || logic.getMoves() == 9;
    if (over) {
      blockGame();
      setImages();
      logic.addAnotherGamePlayed(context);
      genericView.printStatistics();
    }
  }

  private boolean gameOver() {
    logic.checkAllPossibleWins();
    if (logic.winsX()) {
      logic.setGameWonByX();
      logic.writeWinner(context);
      return true;
    }
    if (logic.winsO()) {
      logic.setGameWonByO();
      logic.writeWinner(context);
      return true;
    }
    logic.setGameDraw();
    return false;
  }

  private void blockGame() {
    for (ImageView imageView : imageViewMap.values()) {
      imageView.setEnabled(false);
    }
    genericView.showBlocker();
  }

  private void setImages() {
    if (logic.isNotDrawGame()) {
      int image = logic.xIsTheWinner() ? X_IMAGE_WIN : O_IMAGE_WIN;
      for (ImageView imageView : imageViewMap.values()) {
        switch (imageView.getId()) {
          case R.id.cell_100:
            if (logic.isCell100Winner()) {
              imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
            }
            break;
          case R.id.cell_010:
            if (logic.isCell010Winner()) {
              imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
            }
            break;
          case R.id.cell_001:
            if (logic.isCell001Winner()) {
              imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
            }
            break;
          case R.id.cell_200:
            if (logic.isCell200Winner()) {
              imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
            }
            break;
          case R.id.cell_020:
            if (logic.isCell020Winner()) {
              imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
            }
            break;
          case R.id.cell_002:
            if (logic.isCell002Winner()) {
              imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
            }
            break;
          case R.id.cell_300:
            if (logic.isCell300Winner()) {
              imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
            }
            break;
          case R.id.cell_030:
            if (logic.isCell030Winner()) {
              imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
            }
            break;
          case R.id.cell_003:
            if (logic.isCell003Winner()) {
              imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
            }
            break;
        }
      }
    }
  }
}
