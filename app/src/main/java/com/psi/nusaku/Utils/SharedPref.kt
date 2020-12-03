package com.psi.nusaku.Utils

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseUser
import com.psi.nusaku.Model.User

class SharedPref (context: Context) {
    val NAME = "NUSAKU-SESSION"
    val MODE = Context.MODE_PRIVATE

    val SESSION_USERNAME = "username"
    val SESSION_EMAIL = "email"
    val SESSION_ISLOGIN = "isLogin"

    private lateinit var preferences: SharedPreferences

    init{
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    fun SaveUser(user: FirebaseUser){
        val editor = preferences.edit()
        editor.putBoolean(SESSION_ISLOGIN, true)
        editor.putString(SESSION_USERNAME, user.displayName)
        editor.putString(SESSION_EMAIL, user.email)
        editor.commit()
    }

    fun GetUser(): User {
        var username = preferences.getString(SESSION_USERNAME, "")
        var email = preferences.getString(SESSION_EMAIL, "")
        return User(username, email)
    }
}