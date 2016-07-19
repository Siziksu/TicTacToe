package com.siziksu.tic_tac_toe.commons;

import android.util.Log;

public class Logger {

    private static final String TAG = "LOGGER";
    private static boolean debug;

    public static void setDebug(boolean debug) {
        Logger.debug = debug;
    }

    public static void println(Object o) {
        if (debug) {
            print(String.valueOf(o));
        }
    }

    public static void printErrorLn(Object o) {
        if (debug) {
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