package com.siziksu.tic_tac_toe.presenter;

import android.support.v7.app.AppCompatActivity;

public interface MainPresenter {

  /**
   * Initializes the game setting the board images and the click listener
   *
   * @param activity activity
   */
  void init(AppCompatActivity activity);

  void registerGenericView(GenericView view);

  void unregisterGenericView();

  String getStatistics();

  void resetStatistics();

  void replay();
}
