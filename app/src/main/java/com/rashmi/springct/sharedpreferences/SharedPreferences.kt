package com.rashmi.springct.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class AppSharedPreferences(context: Context) {

    val FILE_NAME = "MySharedPref"

    val sp: SharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sp.edit()

    fun saveBoolean(key: String, value: Boolean) {
        Log.d(TAG, "saveBoolean: $key - $value")
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getBoolean(key: String): Boolean {
        return sp.getBoolean(key, false)
    }

    companion object {
        const val TAG = "SharedPreferences"
    }
}