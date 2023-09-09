package com.example.lifecyclev3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        //val editor = sharedPreferences.edit()
        //editor.putBoolean("isLoggedIn", false)
        //editor.apply()
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) loginActivityFun()
        //loginActivityFun()

        val regBtn = findViewById<Button>(R.id.editProfileBtn)

        regBtn.setOnClickListener {
            val registerActivity = Intent(this, EditProfileActivity::class.java)
            startActivity(registerActivity)
        }
    }

    override fun onResume() {
        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) loginActivityFun()
        super.onResume()
    }

    private fun loginActivityFun() {
        val loginActivity = Intent(this, LoginActivity::class.java)

        startActivity(loginActivity)
    }
}