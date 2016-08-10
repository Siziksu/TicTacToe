package com.siziksu.tic_tac_toe.game;

import com.siziksu.tic_tac_toe.R;
import com.siziksu.tic_tac_toe.common.Logger;
import com.siziksu.tic_tac_toe.common.Maths;
import com.siziksu.tic_tac_toe.common.Preferences;

public class GameLogic implements IGameLogic {

    private static final String KEY_WINNER_X = "winner_x";
    private static final String KEY_WINNER_O = "winner_o";
    private static final String KEY_GAMES = "games";

    private int winner;
    public static final int DRAW = 0;
    public static final int X_PLAYER = 1;
    public static final int O_PLAYER = 2;

    private int[][] board = new int[3][3];

    private int turn;
    private static final int X_TURN = 0;
    private static final int O_TURN = 1;

    private static final int X_VALUE = 1;
    private static final int O_VALUE = 9;

    private static final int X_WIN = X_VALUE * 3;
    private static final int O_WIN = O_VALUE * 3;

    private int w1, w2, w3, w4, w5, w6, w7, w8, moves;

    public GameLogic() {
        winner = DRAW;
        turn = X_TURN;
    }

    @Override
    public float[] statistics() {
        int xWins = Preferences.get().getInt(KEY_WINNER_X, 0);
        int oWins = Preferences.get().getInt(KEY_WINNER_O, 0);
        int games = Preferences.get().getInt(KEY_GAMES, 0);
        return new float[]
                {
                        games,
                        xWins,
                        (float) Maths.get().round(xWins * 100f / games),
                        oWins,
                        (float) Maths.get().round(oWins * 100f / games)
                };
    }

    @Override
    public void resetStatistics() {
        Preferences.get().setInt(KEY_WINNER_X, 0);
        Preferences.get().setInt(KEY_WINNER_O, 0);
        Preferences.get().setInt(KEY_GAMES, 0);
    }

    @Override
    public void writeWinner() {
        String key = (winner == X_PLAYER ? KEY_WINNER_X : KEY_WINNER_O);
        Preferences.get().setInt(key, Preferences.get().getInt(key, 0) + 1);
    }

    @Override
    public void addAnotherGamePlayed() {
        Preferences.get().setInt(KEY_GAMES, Preferences.get().getInt(KEY_GAMES, 0) + 1);
    }

    @Override
    public void resetBoard() {
        turn = X_TURN;
        winner = DRAW;
        moves = 0;
        for (int i = 0; i < board.length; i++) {
            for (int x = 0; x < board[i].length; x++) {
                board[i][x] = 0;
            }
        }
    }

    @Override
    public void addMove() {
        moves++;
    }

    @Override
    public int getValueOfTheMove() {
        return turn == X_TURN ? X_VALUE : O_VALUE;
    }

    @Override
    public void setValueForTheCell(int cell, int value) {
        switch (cell) {
            case R.id.cell_100:
                board[0][0] = value;
                break;
            case R.id.cell_010:
                board[0][1] = value;
                break;
            case R.id.cell_001:
                board[0][2] = value;
                break;
            case R.id.cell_200:
                board[1][0] = value;
                break;
            case R.id.cell_020:
                board[1][1] = value;
                break;
            case R.id.cell_002:
                board[1][2] = value;
                break;
            case R.id.cell_300:
                board[2][0] = value;
                break;
            case R.id.cell_030:
                board[2][1] = value;
                break;
            case R.id.cell_003:
                board[2][2] = value;
                break;
        }
    }

    @Override
    public void setTurn() {
        turn = turn == X_TURN ? O_TURN : X_TURN;
    }

    @Override
    public boolean isTurnForX() {
        return turn == X_TURN;
    }

    @Override
    public int getMoves() {
        return moves;
    }

    @Override
    public void checkAllPossibleWins() {
        w1 = board[0][0] + board[0][1] + board[0][2];
        w2 = board[1][0] + board[1][1] + board[1][2];
        w3 = board[2][0] + board[2][1] + board[2][2];
        w4 = board[0][0] + board[1][0] + board[2][0];
        w5 = board[0][1] + board[1][1] + board[2][1];
        w6 = board[0][2] + board[1][2] + board[2][2];
        w7 = board[0][0] + board[1][1] + board[2][2];
        w8 = board[0][2] + board[1][1] + board[2][0];
    }

    @Override
    public boolean winsX() {
        return w1 == X_WIN ||
               w2 == X_WIN ||
               w3 == X_WIN ||
               w4 == X_WIN ||
               w5 == X_WIN ||
               w6 == X_WIN ||
               w7 == X_WIN ||
               w8 == X_WIN;
    }

    @Override
    public boolean winsO() {
        return w1 == O_WIN ||
               w2 == O_WIN ||
               w3 == O_WIN ||
               w4 == O_WIN ||
               w5 == O_WIN ||
               w6 == O_WIN ||
               w7 == O_WIN ||
               w8 == O_WIN;
    }

    @Override
    public void setGameWonByX() {
        winner = X_PLAYER;
    }

    @Override
    public void setGameWonByO() {
        winner = O_PLAYER;
    }

    @Override
    public void setGameDraw() {
        winner = DRAW;
    }

    @Override
    public boolean isNotDrawGame() {
        return winner != DRAW;
    }

    @Override
    public boolean xIsTheWinner() {
        return winner == X_PLAYER;
    }

    @Override
    public void printBoard() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for (int x = 0; x < board.length; x++) {
            builder.append(x == 0 ? "  " : "");
            builder.append(board[0][x]);
            builder.append(x < board.length - 1 ? "," : "");
        }
        builder.append("\n");
        for (int x = 0; x < board.length; x++) {
            builder.append(x == 0 ? "  " : "");
            builder.append(board[1][x]);
            builder.append(x < board.length - 1 ? "," : "");
        }
        builder.append("\n");
        for (int x = 0; x < board.length; x++) {
            builder.append(x == 0 ? "  " : "");
            builder.append(board[2][x]);
            builder.append(x < board.length - 1 ? "," : "");
        }
        builder.append("\n}");
        Logger.println(builder.toString());
    }

    @Override
    public boolean isCell100Winner() {
        return w1 == X_WIN || w1 == O_WIN || w4 == X_WIN || w4 == O_WIN || w7 == X_WIN || w7 == O_WIN;
    }

    @Override
    public boolean isCell010Winner() {
        return w1 == X_WIN || w1 == O_WIN || w5 == X_WIN || w5 == O_WIN;
    }

    @Override
    public boolean isCell001Winner() {
        return w1 == X_WIN || w1 == O_WIN || w6 == X_WIN || w6 == O_WIN || w8 == X_WIN || w8 == O_WIN;
    }

    @Override
    public boolean isCell200Winner() {
        return w2 == X_WIN || w2 == O_WIN || w4 == X_WIN || w4 == O_WIN;
    }

    @Override
    public boolean isCell020Winner() {
        return w2 == X_WIN || w2 == O_WIN || w5 == X_WIN || w5 == O_WIN || w7 == X_WIN || w7 == O_WIN || w8 == X_WIN || w8 == O_WIN;
    }

    @Override
    public boolean isCell002Winner() {
        return w2 == X_WIN || w2 == O_WIN || w6 == X_WIN || w6 == O_WIN;
    }

    @Override
    public boolean isCell300Winner() {
        return w3 == X_WIN || w3 == O_WIN || w4 == X_WIN || w4 == O_WIN || w8 == X_WIN || w8 == O_WIN;
    }

    @Override
    public boolean isCell030Winner() {
        return w3 == X_WIN || w3 == O_WIN || w5 == X_WIN || w5 == O_WIN;
    }

    @Override
    public boolean isCell003Winner() {
        return w3 == X_WIN || w3 == O_WIN || w6 == X_WIN || w6 == O_WIN || w7 == X_WIN || w7 == O_WIN;
    }
}
