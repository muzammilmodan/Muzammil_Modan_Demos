package com.applocum_mm_demo.utils.SessionManager

import android.content.Context


object SessionManager {
    private val PREFS_NAME = "App Preference"

    private val PARAM_EMAIL = "email"
    private val KEY_SHARED_ISLOGGEDIN = "logIn"
    private val PARAM_PROFILE="image"


    //.........................
    fun setIsUserLoggedin(context: Context, isSelected: Boolean) {
        try {
            val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putBoolean(KEY_SHARED_ISLOGGEDIN, isSelected)
            editor.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getIsUserLoggedin(context: Context): Boolean {
        val preferences = context
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(KEY_SHARED_ISLOGGEDIN, false)
    }





    fun clearAppSession(context: Context) {
        try {
            val preferences =
                context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }


    fun setUserEmail(context: Context, strEmail: String) {
        try {
            val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

            val editor = preferences.edit()
            editor.putString(PARAM_EMAIL, strEmail)
            editor.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getUserEMail(context: Context): String? {
        val preferences = context
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return preferences.getString(PARAM_EMAIL, "")
    }




}
