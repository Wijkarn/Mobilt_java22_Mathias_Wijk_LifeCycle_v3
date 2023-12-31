package com.example.lifecyclev3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val regBtn = findViewById<View>(R.id.regButton) as Button

        regBtn.setOnClickListener {
            val nameInput = findViewById<View>(R.id.regInputName) as EditText
            val name: String = nameInput.text.toString()

            val passwordInput = findViewById<View>(R.id.regInputPassword) as EditText
            val password: String = passwordInput.text.toString()

            val addressInput = findViewById<View>(R.id.regInputAddress) as EditText
            val address: String = addressInput.text.toString()

            val personnummerInput = findViewById<View>(R.id.regInputPersonnummer) as EditText
            val personnummer: String = personnummerInput.text.toString()

            val phoneInput = findViewById<View>(R.id.regInputPhone) as EditText
            val phone: String = phoneInput.text.toString()

            val emailInput = findViewById<View>(R.id.regInputEmail) as EditText
            val email: String = emailInput.text.toString()

            val dLRadioGroup: RadioGroup = findViewById(R.id.radioGroup)
            val dLCheckedRadioButtonId = dLRadioGroup.checkedRadioButtonId

            if (name.isNotEmpty() && password.isNotEmpty() && address.isNotEmpty() && personnummer.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && dLCheckedRadioButtonId != -1) {
                val checkedRadioButton = findViewById<RadioButton>(dLCheckedRadioButtonId)
                val checkedText = checkedRadioButton.text.toString()
                val license = checkedText == "yes"

                Firebase().firebasePUT(
                    this,
                    User(personnummer, password, email, address, phone, name, license)
                )
            } else {
                Toast.makeText(this, "Please fill out every field!", Toast.LENGTH_LONG).show()
                Log.d("Wijk", "Empty field!")
            }
        }
    }
}