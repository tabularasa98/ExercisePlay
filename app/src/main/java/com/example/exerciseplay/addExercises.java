package com.example.exerciseplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

public class addExercises extends AppCompatActivity {
    final addExercises add = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercises);

        DatabaseReference WorkoutDatabase = FirebaseDatabase.getInstance().getReference("workout");

        List<Workout> workouts = new ArrayList<>();

        int pposition = getIntent().getExtras().getInt("pposition");

        EditText exercise_name = findViewById(R.id.exercise_name_edit);
        EditText exercise_weight = findViewById(R.id.exercise_weight_edit);
        EditText exercise_reps = findViewById(R.id.exercise_rep_edit);
        EditText exercise_sets = findViewById(R.id.exercise_set_edit);
        EditText exercise_dur = findViewById(R.id.exercise_dur_edit);
        Button submit_button = findViewById(R.id.exercise_submit_button);
        Button quit_button = findViewById(R.id.add_exercise_quit);

        WorkoutDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                workouts.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    workouts.add(iter.next().getValue(Workout.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workouts.get(pposition).addExercise(new Exercise(exercise_name.getText().toString(), Integer.parseInt(exercise_weight.getText().toString()),Integer.parseInt(exercise_reps.getText().toString()), Integer.parseInt(exercise_sets.getText().toString()),Integer.parseInt(exercise_dur.getText().toString()))) ;
                workouts.get(pposition).updateDuration();
                WorkoutDatabase.setValue(workouts);
                Toast.makeText(add.getApplicationContext(), "Exercise was added to Workout", Toast.LENGTH_LONG).show();
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