package com.example.tuan4

import android.content.Context
import android.widget.TextView
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONException
import android.util.Log


class VolleyFn1 {
    var strJSON=""
//ham doc du lieu tu ΑΡΙ

    fun getAllData(context: Context, textview: TextView)
    {
        //1. Tao request
        val queue = Volley.newRequestQueue(context)
        //2. truyen url
        val url="https://hungnttg.github.io/array_json_new.json";
        //3. goi request
        //mang cua cac doi tuong -> goi mang truoc, doi tuong sau
        //JsonArray Request (url, thanhcong, thatbai);
        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val person = response.getJSONObject(i)
                        val id = person.getString("id")
                        val name = person.getString("name")
                        val email = person.getString("email")

                        // Ghi dữ liệu vào chuỗi
                        strJSON += "Id: $id\n"
                        strJSON += "Name: $name\n"
                        strJSON += "Email: $email\n"
                    }
                    textview.text = strJSON
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                textview.text = error.message ?: "An error occurred"
            }
        )
        // Thực thi request
        queue.add(request)
    }
}