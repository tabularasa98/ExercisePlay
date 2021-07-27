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

public class ManageWorkouts extends AppCompatActivity {
    final ManageWorkouts manage = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_workouts);

        DatabaseReference WorkoutDatabase = FirebaseDatabase.getInstance().getReference("workout");
        List<Workout> workouts = new ArrayList<>();

        EditText workout_name = findViewById(R.id.workout_name);
        Button quit_button = findViewById(R.id.workout_quit_button);
        Button add_button = findViewById(R.id.add_workout);
        Button remove_button = findViewById(R.id.remove_workout);
        ListView workout_list = findViewById(R.id.workout_list);

        WorkoutDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                workouts.clear();
                Iterator<DataSnapshot> iter = snapshot.getChildren().iterator();
                while(iter.hasNext()){
                    workouts.add(iter.next().getValue(Workout.class));
                }
                ArrayAdapter<Workout> adapter = new ArrayAdapter<Workout>(manage.getApplicationContext(), android.R.layout.simple_list_item_1, workouts);
                workout_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        workout_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(manage, viewWorkout.class);
                intent.putExtra("position", position);
                startActivity(intent);
                WorkoutDatabase.setValue(workouts);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(workout_name.getText().length() != 0){
                    Workout temp = new Workout();
                    temp.setName(workout_name.getText().toString());
                    workouts.add(temp);
                    WorkoutDatabase.setValue(workouts);
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
                Intent intent =  new Intent(manage, removeWorkout.class);
                startActivity(intent);
                WorkoutDatabase.setValue(workouts);
            }
        });

    }
}