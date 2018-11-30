package com.example.utilisateur.samva;

import java.io.Serializable;
import java.util.ArrayList;

public class TravelList implements Serializable {

    private String title;
    private ArrayList<Travel> list = new ArrayList<>();

    TravelList(){}

    TravelList(String title, ArrayList<Travel> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Travel> getList() {
        return list;
    }

    public void setList(ArrayList<Travel> list) {
        this.list = list;
    }

    public void addTravel(Travel travel) {
        this.list.add(travel);
    }
}