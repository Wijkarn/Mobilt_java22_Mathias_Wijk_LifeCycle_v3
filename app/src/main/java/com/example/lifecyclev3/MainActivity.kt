package com.example.lifecyclev3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginActivityFun()

    }

    private fun loginActivityFun() {
        val loginActivity = Intent(this, LoginActivity::class.java)

        //intent.putExtra("key", "Hello from MyActivity")

        startActivity(loginActivity)
    }
}