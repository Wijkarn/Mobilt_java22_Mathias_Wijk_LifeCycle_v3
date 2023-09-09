package com.example.lifecyclev3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        val user = User()
        user.personnummer = personnummer //199405062222
        user.password = password //DuaFan123

        val user2 = Firebase().firebaseGet(this, user)
        Log.d("checkLogin", user2.toString())
        if (user2.personnummer.isNotEmpty() && user2.password.isNotEmpty()) {
            //storeLocally(user2)

            Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show()
            super.onBackPressed()
        } else {
            Log.d("Wijk", "Failed")
            Toast.makeText(this, "Login Failed!", Toast.LENGTH_LONG).show()
        }
    }

    private fun storeLocally(user: User) {
        val sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.putString("personnummer", user.personnummer)
        editor.putString("email", user.email)
        editor.putString("name", user.name)
        editor.putString("password", user.password)
        editor.putString("address", user.address)
        editor.putString("phone", user.phone)
        editor.putBoolean("driverslicense", user.driverslicense)
        editor.apply()
    }
}