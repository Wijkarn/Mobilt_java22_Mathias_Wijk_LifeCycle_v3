package com.example.lifecyclev3

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.nio.charset.Charset

class Firebase {
    fun firebasePost(context: Context, user: User, typeChange: String?) {
        val queue = Volley.newRequestQueue(context)
        //val url = ""

        val url = if (typeChange != null) {
            "https://lifecyclev3-555e8-default-rtdb.europe-west1.firebasedatabase.app/users/${user.personnummer}.json"
        } else {
            "https://lifecyclev3-555e8-default-rtdb.europe-west1.firebasedatabase.app/users/${user.personnummer}/${typeChange}.json"
        }

        // Create a JSONObject and put the user data into it
        val jsonBody = JSONObject()
        jsonBody.put("personnummer", user.personnummer)
        jsonBody.put("name", user.name)
        jsonBody.put("address", user.address)
        jsonBody.put("password", user.password)
        jsonBody.put("email", user.email)
        jsonBody.put("phone", user.phone)

        val stringReq: StringRequest = object : StringRequest(Method.PUT, url,
            Response.Listener { response ->
                val strResp = response.toString()
                Log.d("Wijk", strResp)
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

        /*
        Log.d("Wijk", user.personnummer)
        Log.d("Wijk", user.password)
        Log.d("Wijk", user.email)
        Log.d("Wijk", user.address)
        Log.d("Wijk", user.phone)
        Log.d("Wijk", user.name)
         */
    }

    fun firebaseGet(context: Context, user: User): User {

        val rq: RequestQueue = Volley.newRequestQueue(context)
        val url =
            "https://lifecyclev3-555e8-default-rtdb.europe-west1.firebasedatabase.app/users/${user.personnummer}.json"

        //var user = User()

        val jsonObjReq = JsonObjectRequest(url,
            { response ->
                //Log.d("Wijk", response.get("email").toString())
                //user.personnummer = response.get("personnummer").toString()

                if (user.password == response.get("password").toString()) {
                    user.email = response.get("email").toString()
                    user.phone = response.get("phone").toString()
                    user.address = response.get("address").toString()
                    user.name = response.get("name").toString()
                } else {
                    user.personnummer = ""
                    user.password = ""
                }
            },
            { error -> Log.d("Wijk", error.toString()) }
        )

        rq.add(jsonObjReq)
        return (user)
    }
}