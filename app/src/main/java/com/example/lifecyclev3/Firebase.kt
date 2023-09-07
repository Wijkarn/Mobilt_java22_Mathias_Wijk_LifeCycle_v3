package com.example.lifecyclev3

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.nio.charset.Charset

class Firebase {
    fun firebasePost(context: Context, user: User) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://private-4c0e8-simplestapi3.apiary-mock.com/message"

        val requestBody = "id=1" + "&msg=test_msg"
        val stringReq: StringRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
                // response
                val strResp = response.toString()
                Log.d("API", strResp)
            },
            Response.ErrorListener { error ->
                Log.d("API", "error => $error")
            }
        ) {
            override fun getBody(): ByteArray {
                return requestBody.toByteArray(Charset.defaultCharset())
            }
        }
        queue.add(stringReq)
    }

    fun firebaseGet(context: Context) {

        val rq: RequestQueue = Volley.newRequestQueue(context)
        val url = "https://lifecyclev3-555e8-default-rtdb.europe-west1.firebasedatabase.app/users/0202020022.json"

        val jsonObjReq = JsonObjectRequest(url,
            { response -> Log.d("Wijk", response.get("email").toString()) },
            { error -> Log.d("Wijk", error.toString()) }
        )

        rq.add(jsonObjReq)
    }
}