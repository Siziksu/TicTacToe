package com.siziksu.tic_tac_toe.presenter;

import android.app.Activity;

import com.siziksu.tic_tac_toe.game.IGameLogic;

public interface IGamePresenter {

    IGamePresenter setGameLogic(IGameLogic IGameLogic);

    void init(Activity activity);

    void register(IGameView view);

    void unregister();

    void resetStatistics();

    void replay();

    void printStatistics();
}
