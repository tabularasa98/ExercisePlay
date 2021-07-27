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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class viewWorkout extends AppCompatActivity {
    final viewWorkout view = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);

        DatabaseReference WorkoutDatabase = FirebaseDatabase.getInstance().getReference("workout");

        List<Workout> workouts = new ArrayList<>();

        int pposition = getIntent().getExtras().getInt("position");
        getIntent().removeExtra("position");

        TextView view_name = findViewById(R.id.view_workout_name);
        TextView view_duration = findViewById(R.id.view_workout_dur);
        ListView view_exercises = findViewById(R.id.view_exercise);
        Button quit_button = findViewById(R.id.view_workout_quit);
        Button add_exercise = findViewById(R.id.view_add_exercise);
        Button shuffle_workout = findViewById(R.id.shuffle_exercise_button);

        WorkoutDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                workouts.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    workouts.add(iter.next().getValue(Workout.class));
                }
                if(!workouts.isEmpty()){
                    view_name.setText("Name: " + workouts.get(pposition).getName());
                    view_duration.setText("Duration: " + String.valueOf(workouts.get(pposition).getDuration()));
                    ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(view.getApplicationContext(), android.R.layout.simple_list_item_1, workouts.get(pposition).getExercises());
                    view_exercises.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        view_exercises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                workouts.get(pposition).removeExercise(position);
                workouts.get(pposition).updateDuration();
                WorkoutDatabase.setValue(workouts);
            }
        });

        add_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(view, addExercises.class);
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

        shuffle_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Exercise> temp = new ArrayList<>();
                temp =  workouts.get(pposition).getExercises();
                Collections.shuffle(temp);
                workouts.get(pposition).setExercises(temp);
                WorkoutDatabase.setValue(workouts);
            }
        });
    }
}