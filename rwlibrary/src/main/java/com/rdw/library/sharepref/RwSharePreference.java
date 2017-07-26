package com.rdw.library.sharepref;

import android.content.Context;
import android.content.SharedPreferences;

/*** Created by RAHUL D. WADHAI on 7/14/2017 ***/

@SuppressWarnings("ALL")
public class RwSharePreference {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public RwSharePreference(Context context,String PREFERENCES) {
        /***
         *  You Can change Preference Name ( RwConstant.PREFERENCES )
         */
        sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void putString(String key, String value) {
        editor.putString(key, value).commit();
    }

    public void clear() {
        editor.clear().commit();
    }
    public void remove(String key) {
        editor.remove(key).commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean putBoolean(String key, boolean value) {
        editor.putBoolean(key, value).commit();
        return value;
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }
}