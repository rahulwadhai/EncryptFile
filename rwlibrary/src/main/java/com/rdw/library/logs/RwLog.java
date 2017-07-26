package com.rdw.library.logs;

import android.util.Log;
/*** Created by RAHUL D. WADHAI on 7/14/2017 ***/
public class RwLog {

    /**
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param message The message you would like logged.
     */
    public static void LogE(String tag,String message){
        Log.e(tag,message);
    }

    public static void LogV(String tag,String message){
        Log.v(tag,message);
    }

    public static void LogI(String tag,String message){
        Log.i(tag,message);
    }

    public static void LogD(String tag,String message){
        Log.d(tag,message);
    }
}