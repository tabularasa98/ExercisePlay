package com.example.exerciseplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class viewPlaylist extends AppCompatActivity {
    final viewPlaylist view = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_playlist);

        DatabaseReference pdatabase = FirebaseDatabase.getInstance().getReference("playlist");

        List<Playlist> playlists = new ArrayList<>();

        int pposition = getIntent().getExtras().getInt("position");
        getIntent().removeExtra("position");

        TextView view_name = findViewById(R.id.view_playlist_name);
        TextView view_duration = findViewById(R.id.view_playlist_dur);
        ListView view_songs = findViewById(R.id.view_songs);
        Button quit_button = findViewById(R.id.view_quit);
        Button add_songs = findViewById(R.id.view_add_songs);
        Button shuffle_songs = findViewById(R.id.shuffle_button);

        pdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playlists.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    playlists.add(iter.next().getValue(Playlist.class));
                }
                if(!playlists.isEmpty()){
                    view_name.setText("Name: " + playlists.get(pposition).getName());
                    view_duration.setText("Duration: " + String.valueOf(playlists.get(pposition).getDuration()));
                    ArrayAdapter<Song> adapter = new ArrayAdapter<Song>(view.getApplicationContext(), android.R.layout.simple_list_item_1, playlists.get(pposition).getSongs());
                    view_songs.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        view_songs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playlists.get(pposition).removeSong(position);
                playlists.get(pposition).updateDuration();
                pdatabase.setValue(playlists);
            }
        });

        add_songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(view, addSongs.class);
                intent.putExtra("pposition", pposition);
                startActivity(intent);
            }
        });

        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shuffle_songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Song> temp = new ArrayList<>();
                temp =  playlists.get(pposition).getSongs();
                Collections.shuffle(temp);
                playlists.get(pposition).setSongs(temp);
                pdatabase.setValue(playlists);
            }
        });
    }
}