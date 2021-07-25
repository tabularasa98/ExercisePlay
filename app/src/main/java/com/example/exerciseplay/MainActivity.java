package com.example.exerciseplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private void makeRequest(String t_name){
        ANRequest req = AndroidNetworking.get("https://api.deezer.com/search/track")
                .addQueryParameter("q", "party in the USA")
                .build();
        req.getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try{
                    JSONArray data = jsonObject.getJSONArray("data");
                    for(int i = 0; i < data.length(); i++){
                        JSONObject t = data.getJSONObject(i);
                        ((TextView) findViewById(R.id.textView)).setText(t.getString("title"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(getApplicationContext(),String.valueOf(anError.getMessage()) , Toast.LENGTH_LONG).show();
                Log.d("error", anError.getMessage());
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button test = findViewById(R.id.button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest("test");
            }
        });
    }
}