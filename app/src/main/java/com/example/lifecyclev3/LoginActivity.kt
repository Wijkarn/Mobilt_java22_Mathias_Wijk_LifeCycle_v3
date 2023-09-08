package com.example.lifecyclev3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val regBtn = findViewById<Button>(R.id.loginRegisterBtn)

        regBtn.setOnClickListener {
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
        }

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            checkLogin()
        }
    }

    private fun checkLogin() {
        val personnummerInput = findViewById<View>(R.id.loginPersonnummer) as EditText
        val personnummer: String = personnummerInput.text.toString()

        val passwordInput = findViewById<View>(R.id.loginPassword) as EditText
        val password: String = passwordInput.text.toString()

        var user = User()
        user.personnummer = "199405062222" //199405062222
        user.password = "DuaFan123" //DuaFan123
        //user.personnummer = personnummer //199405062222
        //user.password = password //DuaFan123

        user = Firebase().firebaseGet(this, user)
        if (user.personnummer.isNotEmpty()) {
            Log.d("Wijk", "Success")
            val sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", true)
            editor.apply()
            super.onBackPressed()
        } else {
            Log.d("Wijk", "Failed")
        }
    }
}