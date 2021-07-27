package com.example.exerciseplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class addSongs extends AppCompatActivity {
    final addSongs add_songs = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_songs);

        DatabaseReference sdatabase = FirebaseDatabase.getInstance().getReference("songs");
        DatabaseReference pdatabase = FirebaseDatabase.getInstance().getReference("playlist");

        List<Song> slist = new ArrayList<>();
        List<Playlist> playlists = new ArrayList<>();

        int pposition = getIntent().getExtras().getInt("pposition");

        ListView song_list =  findViewById(R.id.search_results);
        EditText search_name = findViewById(R.id.search_name);
        Button search_button = findViewById(R.id.search_button);
        Button quit_button = findViewById(R.id.quit_button);

        sdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    slist.add(iter.next().getValue(Song.class));
                }
                ArrayAdapter<Song> adapter = new ArrayAdapter<Song>(add_songs.getApplicationContext(), android.R.layout.simple_list_item_1, slist);
                song_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playlists.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    playlists.add(iter.next().getValue(Playlist.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        song_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playlists.get(pposition).addSong(slist.get(position));
                playlists.get(pposition).updateDuration();
                pdatabase.setValue(playlists);
                Toast.makeText(add_songs.getApplicationContext(), "Song was added to playlist", Toast.LENGTH_LONG).show();
            }
        });
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = new MainActivity();
                String search_text = search_name.getText().toString();
                main.makeRequest(search_text);
                ArrayAdapter<Song> adapter = new ArrayAdapter<Song>(add_songs.getApplicationContext(), android.R.layout.simple_list_item_1, slist);
                song_list.setAdapter(adapter);
            }
        });
        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}