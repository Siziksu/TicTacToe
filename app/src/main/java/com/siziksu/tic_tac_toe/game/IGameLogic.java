package com.siziksu.tic_tac_toe.game;

public interface IGameLogic {

    float[] statistics();

    void resetStatistics();

    void writeWinner();

    void addAnotherGamePlayed();

    void resetBoard();

    void addMove();

    int getValueOfTheMove();

    void setValueForTheCell(int cell, int value);

    void setTurn();

    boolean isTurnForX();

    int getMoves();

    void checkAllPossibleWins();

    boolean winsX();

    boolean winsO();

    void setGameWonByX();

    void setGameWonByO();

    void setGameDraw();

    boolean isNotDrawGame();

    boolean xIsTheWinner();

    void printBoard();

    boolean isCell100Winner();

    boolean isCell010Winner();

    boolean isCell001Winner();

    boolean isCell200Winner();

    boolean isCell020Winner();

    boolean isCell002Winner();

    boolean isCell300Winner();

    boolean isCell030Winner();

    boolean isCell003Winner();
}
