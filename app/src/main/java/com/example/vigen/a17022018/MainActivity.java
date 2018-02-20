package com.example.vigen.a17022018;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                "https://raw.githubusercontent.com/lsv/fifa-worldcup-2018/master/data.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            StringBuilder builder = new StringBuilder();
                            JSONArray jsonArray = jsonObject.getJSONArray("stadiums");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String name = jsonObject1.getString("name");
                                String city = jsonObject1.getString("city");
                                builder.append(name + "\n");
                                builder.append(city + "\n"+"\n");

                            }
                            textView.setText(builder.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);


    }
}
