package com.example.lifecyclev3

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.nio.charset.Charset

class Firebase {
    fun firebasePUT(context: Context, user: User) {
        /*
        val queue = Volley.newRequestQueue(context)

        val url =
            "https://lifecyclev3-555e8-default-rtdb.europe-west1.firebasedatabase.app/users/${user.personnummer}.json"
        // Create a JSONObject and put the user data into it
        val jsonBody = JSONObject()
        jsonBody.put("personnummer", user.personnummer)
        jsonBody.put("name", user.name)
        jsonBody.put("address", user.address)
        jsonBody.put("password", user.password)
        jsonBody.put("email", user.email)
        jsonBody.put("phone", user.phone)
        jsonBody.put("driverslicense", user.driverslicense)

        Log.d("Wijk", url)

        val stringReq: StringRequest = object : StringRequest(Method.PUT, url,
            Response.Listener { response ->
                val strResp = response.toString()
                Log.d("Wijk", "response $strResp")
                Toast.makeText(context, "Register Successful!", Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                Log.d("Wijk", "error => $error")
            }
        ) {
            override fun getBody(): ByteArray {
                return jsonBody.toString().toByteArray(Charset.defaultCharset())
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }
        queue.add(stringReq)
         */
        storeLocally(context, user)
    }

    fun firebaseGet(context: Context, user: User): User {
        /*
                val rq: RequestQueue = Volley.newRequestQueue(context)
                val url =
                    "https://lifecyclev3-555e8-default-rtdb.europe-west1.firebasedatabase.app/users/${user.personnummer}.json"

                val jsonObjReq = JsonObjectRequest(url,
                    { response ->
                        //Log.d("Wijk", response.get("email").toString())
                        Log.d("Response", response.toString())

                        if (user.password == response.get("password").toString()) {
                            user.email = response.get("email").toString()
                            user.phone = response.get("phone").toString()
                            user.address = response.get("address").toString()
                            user.name = response.get("name").toString()
                            user.driverslicense = response.get("driverslicense") as Boolean
                        } else {
                            user.personnummer = ""
                            user.password = ""
                        }
                    },
                    { error -> Log.d("Wijk", error.toString()) }
                )

                Log.d("Firebase user", "${user.email}, ${user.personnummer}, ${user.name}, ${user.password}, ${user.address}, ${user.phone}, ${user.driverslicense}")
                rq.add(jsonObjReq)
                */

        user.email = "dualipa@gmail.com"
        user.phone = "2847264963"
        user.address = "Dua st"
        user.name = "Dua Lipa"
        user.driverslicense = true
        storeLocally(context, user)

        return (user)
    }

    private fun storeLocally(context: Context, user: User) {
        val sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
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