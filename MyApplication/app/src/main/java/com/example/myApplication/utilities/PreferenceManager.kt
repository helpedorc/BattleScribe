package com.example.myApplication.utilities

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(val activity: Activity) {

    private lateinit var preferences: SharedPreferences

    fun initialize() {
        preferences = activity.getPreferences(Context.MODE_PRIVATE)
    }

    fun save(key: String, value: String) {

        val editor: SharedPreferences.Editor = preferences.edit()


        editor.putString(key, value)
        editor.apply()
    }

    fun load(key: String): String? {
        return preferences.getString(key, "[]")
    }
}
