package com.example.exerciseplay;

public class Exercise {
    private String name;
    private int weight;
    private int rep;
    private int set;
    private int duration;
    public Exercise(){
        this.name = "";
        this.weight =  0;
        this.rep =  0;
        this.set =  0;
        this.duration = 0;
    }

    public Exercise(String name, int weight, int rep, int set, int duration){
        this.name = name;
        this.weight =   weight;
        this.rep =  rep;
        this.set =  set;
        this.duration = duration;
    }

    public String toString(){
        String whole;
        whole = "Name: " + name +'\n';
        whole += "Weight: " + weight + '\n';
        whole += "Reps: " + rep + '\n';
        whole += "Sets: " + set + '\n';
        whole += "Duration: " + duration + '\n';
        return whole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}


