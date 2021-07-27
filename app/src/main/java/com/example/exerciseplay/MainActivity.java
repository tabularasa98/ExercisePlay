package com.example.exerciseplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    final MainActivity main = this;
    DatabaseReference sdatabase;

    public void makeRequest(String t_name){
        List<Song> results = new ArrayList<>();
        sdatabase = FirebaseDatabase.getInstance().getReference("songs");

        AndroidNetworking.get("https://api.deezer.com/search/track")
                .addQueryParameter("q", t_name)
                .setExecutor(Executors.newSingleThreadExecutor())
                .setPriority(Priority.HIGH)
                .build()
            .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
             public void onResponse(JSONObject jsonObject) {
                try{

                    JSONArray data = jsonObject.getJSONArray("data");
                    for(int i = 0; i < data.length(); i++){
                        JSONObject t = data.getJSONObject(i);
                        Song cur_song =  new Song(t.getString("title"),t.getInt("duration"), t.getBoolean("explicit_lyrics"), t.getString("preview"), t.getString("md5_image"), t.getJSONObject("artist").getString("name"),t.getJSONObject("album").getString("title") );
                        results.add(cur_song);
                    }
                    sdatabase.setValue(results);
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

        List<Song> slist = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sdatabase = FirebaseDatabase.getInstance().getReference("songs");
        sdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                slist.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    slist.add(iter.next().getValue(Song.class));
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        Button select_playlist = findViewById(R.id.create_playlists);
        select_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main, managePlaylists.class);
                startActivity(intent);
            }
        });

        Button select_workout = findViewById(R.id.workout_menu);
        select_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main, ManageWorkouts.class);
                startActivity(intent);
            }
        });
    }
}