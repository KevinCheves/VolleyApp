package com.appmovil.volleyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    EditText txtResult, txtId;
;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        txtResult = (EditText) findViewById(R.id.txtResult);
        txtId = (EditText) findViewById(R.id.txtId);
    }

    public void btnEnviar(View view) {
        if(txtId.length()>0){
             url = "http://jsonplaceholder.typicode.com/comments?postId="
                    +txtId.getText().toString();
            obtenerDatos();
        }else{
            Snackbar.make(view,"llene todos los campos",Snackbar.LENGTH_SHORT).show();
        }
    }

    private void obtenerDatos() {
        int c =0;
        StringRequest JsonArrayRequest2 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String lstComment = "";
                String resp = response.replace("\n","");
                try {
                    JSONArray JSONlista = new JSONArray(resp);
                    for (int i = 0; i < JSONlista.length(); i++)
                    {
                        JSONObject comment = JSONlista.getJSONObject(i);
                        lstComment = lstComment + "\nAsunto:" + comment.getString("name").toString()
                                + "\nEmail: " + comment.getString("email").toString()
                                + "\nComentario: " + comment.getString("body").toString()
                                +"\n";
                    }
                    int x = 0;
                } catch (JSONException e) {
                    e.printStackTrace();
                    int y=0;
                }

                txtResult.setText("Respuesta WS; \n" + lstComment);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(JsonArrayRequest2);
    }

}


  /*private void obtenerDatos() {
        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String lstComment = "";
                int n = response.length();
                for (int i = 0; i < n; i++) {
                    try {

                        JSONObject comment = new JSONObject(response.get(i).toString());
                        lstComment = lstComment + "\nAsunto:" + comment.getString("id").toString();
                        int x = 0;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        int x = 0;
                    }
                }

                txtResult.setText("Respuesta WS; \n" + lstComment);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(JsonArrayRequest);
    }*/