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

public class managePlaylists extends AppCompatActivity {
    final managePlaylists manage = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_playlists);

        DatabaseReference pdatabase = FirebaseDatabase.getInstance().getReference("playlist");
        List<Playlist> playlists = new ArrayList<>();

        EditText playlist_name = findViewById(R.id.playlist_name);
        Button quit_button = findViewById(R.id.playlist_quit_button);
        Button add_button = findViewById(R.id.add_playlist);
        Button remove_button = findViewById(R.id.remove_playlist);
        ListView playlist_list = findViewById(R.id.playlist_list);


        pdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playlists.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    playlists.add(iter.next().getValue(Playlist.class));
                }
                ArrayAdapter<Playlist> adapter = new ArrayAdapter<Playlist>(manage.getApplicationContext(), android.R.layout.simple_list_item_1, playlists);
                playlist_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        playlist_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(manage, viewPlaylist.class);
                intent.putExtra("position", position);
                startActivity(intent);
                pdatabase.setValue(playlists);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playlist_name.getText().length() != 0){
                    Playlist temp = new Playlist();
                    temp.setName(playlist_name.getText().toString());
                    playlists.add(temp);
                    pdatabase.setValue(playlists);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_LONG).show();
                }
            }
        });
        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(manage, removePlaylist.class);
                startActivity(intent);
                pdatabase.setValue(playlists);
            }
        });

    }
}