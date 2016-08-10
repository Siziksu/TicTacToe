package com.siziksu.tic_tac_toe.injector;

import android.content.Context;

import com.siziksu.tic_tac_toe.game.GameLogic;
import com.siziksu.tic_tac_toe.game.IGameLogic;
import com.siziksu.tic_tac_toe.presenter.GamePresenter;
import com.siziksu.tic_tac_toe.presenter.IGamePresenter;

public class GameModule {

    private final IGamePresenter gamePresenter;
    private final IGameLogic gameLogic;

    private static GameModule instance;

    public static void init(Context context) {
        if (instance == null) {
            instance = new GameModule(context);
        }
    }

    private GameModule(Context context) {
        gamePresenter = new GamePresenter(context);
        gameLogic = new GameLogic();
    }

    public static GameModule get() {
        if (instance == null) {
            throw new RuntimeException("This class must be initialized");
        }
        return instance;
    }

    public IGamePresenter getGame() {
        return gamePresenter.setGameLogic(gameLogic);
    }
}
