package com.justlearn.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object SharedPreferencesManager {
    private const val PREFS_NAME = "MyPrefs"
    private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    private const val KEY_USER_ID = "userId"
    private const val KEY_FACEBOOK_ACCESS_TOKEN = "facebookAccessToken"

//    private const val KEY_GOOGLE_DISPLAY_NAME = "googleDisplayName"
    private const val KEY_GOOGLE_EMAIL = "googleEmail"
    private const val KEY_GOOGLE_ID_TOKEN = "googleIdToken"

    fun saveFacebookLoginInfo(context: Context, userId: String, accessToken: String) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_USER_ID, userId)
        editor.putString(KEY_FACEBOOK_ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun saveGoogleLoginInfo(context: Context, email: String, idToken: String) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_GOOGLE_EMAIL, email)
        editor.putString(KEY_GOOGLE_ID_TOKEN, idToken)
        editor.apply()
    }

    fun clearLoginInfo(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        Log.d("shared pref", "cleared shared pref")
    }

    fun isLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getUserId(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    fun getFacebookAccessToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_FACEBOOK_ACCESS_TOKEN, null)
    }
}
