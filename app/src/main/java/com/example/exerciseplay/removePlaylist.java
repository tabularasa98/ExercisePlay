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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class removePlaylist extends AppCompatActivity {
    removePlaylist remove = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_playlist);

        DatabaseReference pdatabase = FirebaseDatabase.getInstance().getReference("playlist");
        List<Playlist> playlists = new ArrayList<>();

        Button back_button = findViewById(R.id.remove_back_button);
        ListView remove_list = findViewById(R.id.remove_playlist_list);

        pdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playlists.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    playlists.add(iter.next().getValue(Playlist.class));
                }
                ArrayAdapter<Playlist> adapter = new ArrayAdapter<Playlist>(remove.getApplicationContext(), android.R.layout.simple_list_item_1, playlists);
                remove_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        remove_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playlists.remove(position);
                pdatabase.setValue(playlists);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}