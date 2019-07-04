package com.example.alcotest.entities;

public class Drink {
    private long id = -1;
    private String name;
    private int iconId;
    private int colorFilterId;
    private int alcInterest = 0;
    private int isChoosen = 0;



    public Drink() {
    }




    public Drink(String name, int iconId, int colorFilterId) {
        this.name = name;
        this.iconId = iconId;
        this.colorFilterId = colorFilterId;

    }

    public Drink(String name, int iconId, int alcInterest, int colorFilter) {
        this.name = name;
        this.iconId = iconId;
        this.alcInterest = alcInterest;
        this.colorFilterId = colorFilter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getColorFilterId() {
        return colorFilterId;
    }

    public void setColorFilterId(int colorFilterId) {
        this.colorFilterId = colorFilterId;
    }

    public int getAlcInterest() {
        return alcInterest;
    }

    public void setAlcInterest(int alcInterest) {
        this.alcInterest = alcInterest;
    }

    public int isChoosen() {
        return isChoosen;
    }

    public void setChoosen(int isChoosen) {
        this.isChoosen = isChoosen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
