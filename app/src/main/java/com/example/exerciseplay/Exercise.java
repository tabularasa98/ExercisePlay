package com.example.exerciseplay;

public class Exercise {
    private String name;
    private int weight;
    private int rep;
    private int set;
    private int duration;

    public Exercise(String name, int weight, int rep, int set, int duration){
        this.name = name;
        this.weight =   weight;
        this.rep =  rep;
        this.set =  set;
        this.duration = duration;
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


