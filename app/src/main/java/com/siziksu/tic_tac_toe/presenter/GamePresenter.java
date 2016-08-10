package com.siziksu.tic_tac_toe.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.siziksu.tic_tac_toe.R;
import com.siziksu.tic_tac_toe.game.IGameLogic;

import java.util.HashMap;
import java.util.Map;

public class GamePresenter implements IGamePresenter, View.OnClickListener {

    private static final int X_IMAGE = R.drawable.cell_x;
    private static final int O_IMAGE = R.drawable.cell_o;
    private static final int X_IMAGE_WIN = R.drawable.cell_x_win;
    private static final int O_IMAGE_WIN = R.drawable.cell_o_win;

    private final Context context;

    private IGameView view;
    private Map<Integer, ImageView> imageViewMap;
    private IGameLogic gameLogic;

    public GamePresenter(Context context) {
        this.context = context;
        imageViewMap = new HashMap<>();
    }

    @Override
    public GamePresenter setGameLogic(IGameLogic gameLogic) {
        this.gameLogic = gameLogic;
        return this;
    }

    @Override
    public void init(Activity activity) {
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
    public void register(IGameView view) {
        this.view = view;
    }

    @Override
    public void unregister() {
        this.view = null;
    }

    @Override
    public void resetStatistics() {
        gameLogic.resetStatistics();
        view.printStatistics(getStatistics());
    }

    @Override
    public void replay() {
        for (ImageView imageView : imageViewMap.values()) {
            imageView.setEnabled(true);
        }
        view.showBlocker(false);
        resetBoard();
    }

    @Override
    public void printStatistics() {
        if (view != null) {
            view.printStatistics(getStatistics());
        }
    }

    private void resetBoard() {
        for (ImageView imageView : imageViewMap.values()) {
            imageView.setImageDrawable(ContextCompat.getDrawable(context, android.R.color.transparent));
        }
        gameLogic.resetBoard();
    }

    @Override
    public void onClick(View v) {
        click(v);
    }

    private void click(View view) {
        int cell = view.getId();
        gameLogic.addMove();
        int value = gameLogic.getValueOfTheMove();
        int drawable = gameLogic.isTurnForX() ? X_IMAGE : O_IMAGE;
        gameLogic.setValueForTheCell(cell, value);
        imageViewMap.get(cell).setImageDrawable(ContextCompat.getDrawable(context, drawable));
        gameLogic.setTurn();
        view.setEnabled(false);
        checkGameOver();
    }

    private void checkGameOver() {
        gameLogic.printBoard();
        boolean over = gameOver() || gameLogic.getMoves() == 9;
        if (over) {
            blockGame();
            setImages();
            gameLogic.addAnotherGamePlayed();
            view.printStatistics(getStatistics());
        }
    }

    private boolean gameOver() {
        gameLogic.checkAllPossibleWins();
        if (gameLogic.winsX()) {
            gameLogic.setGameWonByX();
            gameLogic.writeWinner();
            return true;
        }
        if (gameLogic.winsO()) {
            gameLogic.setGameWonByO();
            gameLogic.writeWinner();
            return true;
        }
        gameLogic.setGameDraw();
        return false;
    }

    private void blockGame() {
        for (ImageView imageView : imageViewMap.values()) {
            imageView.setEnabled(false);
        }
        view.showBlocker(true);
    }

    private String getStatistics() {
        float[] stats = gameLogic.statistics();
        return "TOTAL GAMES PLAYED: " + (int) stats[0] + "\n"
               + "PLAYER 1 WINS: " + (int) stats[1] + " (" + stats[2] + "%" + ")" + "\n"
               + "PLAYER 2 WINS: " + (int) stats[3] + " (" + stats[4] + "%" + ")";
    }

    private void setImages() {
        if (gameLogic.isNotDrawGame()) {
            int image = gameLogic.xIsTheWinner() ? X_IMAGE_WIN : O_IMAGE_WIN;
            for (ImageView imageView : imageViewMap.values()) {
                switch (imageView.getId()) {
                    case R.id.cell_100:
                        if (gameLogic.isCell100Winner()) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
                        }
                        break;
                    case R.id.cell_010:
                        if (gameLogic.isCell010Winner()) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
                        }
                        break;
                    case R.id.cell_001:
                        if (gameLogic.isCell001Winner()) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
                        }
                        break;
                    case R.id.cell_200:
                        if (gameLogic.isCell200Winner()) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
                        }
                        break;
                    case R.id.cell_020:
                        if (gameLogic.isCell020Winner()) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
                        }
                        break;
                    case R.id.cell_002:
                        if (gameLogic.isCell002Winner()) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
                        }
                        break;
                    case R.id.cell_300:
                        if (gameLogic.isCell300Winner()) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
                        }
                        break;
                    case R.id.cell_030:
                        if (gameLogic.isCell030Winner()) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
                        }
                        break;
                    case R.id.cell_003:
                        if (gameLogic.isCell003Winner()) {
                            imageView.setImageDrawable(ContextCompat.getDrawable(context, image));
                        }
                        break;
                }
            }
        }
    }
}
