package com.psi.nusaku.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.psi.nusaku.R
import com.psi.nusaku.Utils.SharedPref

class LoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth;
    lateinit var mDatabase: FirebaseDatabase
    lateinit var etEmail: EditText;
    lateinit var etPassword: EditText;
    lateinit var btnLogin: Button
    lateinit var btnLoginGoogle: Button
    lateinit var preferences: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btn_login)
        btnLoginGoogle = findViewById(R.id.btn_loginWithGoogle)
        etEmail = findViewById(R.id.et_inputEmail)
        etPassword = findViewById(R.id.et_inputPassword)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
        preferences = SharedPref(this)

        btnLogin.setOnClickListener {
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Sign In Sucessful", Toast.LENGTH_SHORT)
                    onAuthSuccess(it.result!!.user)
                } else {
                    Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT)
                }
            }
        }
    }

    fun onAuthSuccess(user: FirebaseUser?){
        if (user != null) {
            preferences.SaveUser(user)
            startActivity(Intent(this, DashboardActivity::class.java))
        };
    }
}