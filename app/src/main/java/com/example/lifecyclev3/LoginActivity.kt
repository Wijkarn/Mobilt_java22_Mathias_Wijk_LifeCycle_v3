package com.example.lifecyclev3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.loginRegisterBtn)

        button.setOnClickListener {
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
        }
    }
}