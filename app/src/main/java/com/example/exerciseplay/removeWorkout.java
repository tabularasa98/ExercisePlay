package com.example.exerciseplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class removeWorkout extends AppCompatActivity {
    removeWorkout remove = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_workout);

        DatabaseReference WorkoutDatabase = FirebaseDatabase.getInstance().getReference("workout");
        List<Workout> workouts = new ArrayList<>();

        Button back_button = findViewById(R.id.remove_workout_back_button);
        ListView remove_list = findViewById(R.id.remove_workout_list);

        WorkoutDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                workouts.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    workouts.add(iter.next().getValue(Workout.class));
                }
                ArrayAdapter<Workout> adapter = new ArrayAdapter<Workout>(remove.getApplicationContext(), android.R.layout.simple_list_item_1, workouts);
                remove_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        remove_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                workouts.remove(position);
                WorkoutDatabase.setValue(workouts);
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