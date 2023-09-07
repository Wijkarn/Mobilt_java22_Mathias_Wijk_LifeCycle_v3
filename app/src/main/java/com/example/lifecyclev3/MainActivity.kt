package com.example.lifecyclev3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginActivityFun()

        apis()
    }

    private fun loginActivityFun() {
        val loginActivity = Intent(this, LoginActivity::class.java)

        //intent.putExtra("key", "Hello from MyActivity")

        startActivity(loginActivity)
    }

    public fun apis() {

        val rq: RequestQueue = Volley.newRequestQueue(this)
        val url = "https://lifecyclev3-555e8-default-rtdb.europe-west1.firebasedatabase.app/users/0202020022.json"

        val j: JsonObjectRequest = JsonObjectRequest(url,
            {res -> Log.d("Wijk", res.get("email").toString())},
            {err -> Log.d("Wijk", err.toString())}
        )

        //rq.add(r)
        rq.add(j)
    }
}