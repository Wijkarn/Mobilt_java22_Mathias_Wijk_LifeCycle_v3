package com.example.lifecyclev3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import java.util.Locale

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val personnummer = sharedPreferences.getString("personnummer", "name")
        val nameText = findViewById<TextView>(R.id.userName)
        nameText.text = personnummer

        val editBtn = findViewById<Button>(R.id.editBtn)

        editBtn.setOnClickListener {
            updateInfo()
        }
    }

    private fun updateInfo() {
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val checkedRadioButtonId = radioGroup.checkedRadioButtonId

        if (checkedRadioButtonId != -1) {
            val checkedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
            val checkedText = checkedRadioButton.text.toString()

            val inputText = findViewById<View>(R.id.editInput) as EditText
            val input: String = inputText.text.toString()

            val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
            val personnummer = sharedPreferences.getString("personnummer", "")
            val email = sharedPreferences.getString("email", "")
            val phone = sharedPreferences.getString("phone", "")
            val address = sharedPreferences.getString("address", "")
            val name = sharedPreferences.getString("name", "")
            val password = sharedPreferences.getString("password", "")

            val user = User()

            if (personnummer != null) user.personnummer = personnummer
            if (email != null) user.email = email
            if (phone != null) user.phone = phone
            if (address != null) user.address = address
            if (name != null) user.name = name
            if (password != null) user.password = password

            when (checkedText) {
                "Address" -> {
                    if (address != null) user.address = input
                }

                "Email" -> {
                    if (email != null) user.email = input
                }

                "Name" -> {
                    if (name != null) user.name = input
                }

                "Password" -> {
                    if (password != null) user.password = input
                }

                "Personnummer" -> {
                    if (personnummer != null) user.personnummer = input
                }

                "Phone" -> {
                    if (phone != null) user.phone = input
                }
            }
            Log.d("Wijk", user.email)
            Firebase().firebasePUT(this, user)
        } else {
            Toast.makeText(this, "Please select an option!", Toast.LENGTH_LONG).show()
        }
    }
}