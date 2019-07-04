package com.example.alcotest.entities;

public class User {
    long id;
    int mood;
    int weight;
    int alcInfluence;

    public User() {
    }

    public User(long id, int mood, int weight, int alcInfluence) {
        this.id = id;
        this.mood = mood;
        this.weight = weight;
        this.alcInfluence = alcInfluence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAlcInfluence() {
        return alcInfluence;
    }

    public void setAlcInfluence(int alcInfluence) {
        this.alcInfluence = alcInfluence;
    }
}
