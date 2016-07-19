package com.siziksu.tic_tac_toe.common;

import android.util.Log;

public class Logger {

    private static final String TAG = "LOGGER";
    private static boolean log;

    public static void enableLog(boolean log) {
        Logger.log = log;
    }

    public static void println(Object o) {
        if (log) {
            print(String.valueOf(o));
        }
    }

    public static void printErrorLn(Object o) {
        if (log) {
            printError(String.valueOf(o));
        }
    }

    private static synchronized void print(String str) {
        str = str == null ? "null" : str;
        Log.d(TAG, str);
    }

    private static synchronized void printError(String str) {
        str = str == null ? "null" : str;
        Log.wtf(TAG, str);
    }
}