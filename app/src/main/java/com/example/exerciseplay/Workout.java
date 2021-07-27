package com.example.exerciseplay;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String name;
    private int duration;
    private List<Exercise> exercises;

    public Workout(String name, int duration, List<Exercise> exercises){
        this.name = name;
        this.duration = duration;
        this.exercises =exercises;
    }

    public Workout(){
        this.name = "";
        this.duration = 0;
        this.exercises = new ArrayList<>();
    }

    @NonNull
    public String toString(){
        String whole;
        whole = "Name: " + name +'\n';
        whole += "Duration: " + duration + '\n';
        whole += "Exercises: " + exercises.size() + '\n';
        return whole;
    }

    public void updateDuration(){
        this.duration = 0;
        for(Exercise e : exercises){
            this.duration += e.getDuration();
        }
    }
    public void addExercise(Exercise s){
        this.exercises.add(s);
    }
    public void removeExercise(int pos){
        if(pos < exercises.size()){
            exercises.remove(pos);
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
