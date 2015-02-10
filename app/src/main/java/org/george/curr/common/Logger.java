package org.george.curr.common;

import android.util.Log;

public class Logger {

    public static void log(String context, String method) {
        log(context, method, null);
    }

    public static void log(String context, String method, String additionalInfo) {
        if (additionalInfo != null) {
            method += ": " + additionalInfo;
        }
        Log.i(context, method);
    }
}