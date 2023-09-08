package com.example.lifecyclev3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) loginActivityFun()

    }

    private fun loginActivityFun() {
        val loginActivity = Intent(this, LoginActivity::class.java)

        //intent.putExtra("key", "Hello from MyActivity")

        startActivity(loginActivity)
    }
}