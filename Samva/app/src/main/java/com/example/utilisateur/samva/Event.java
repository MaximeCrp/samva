package com.example.utilisateur.samva;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable, ClusterItem {



    private String title;
    private String timetable;
   // private Date[][] date; //tableau à 2 dimmensions : 1 ligne correspond à une occurence de l'événement
    //les deux colonnes correspondent à la date de début et celle de fin
    private String address;
    private String[][] date;
    private String image;
    private String placename;
    private ArrayList<Double> latlon = new ArrayList<Double>();



    Event() {}

    @Override
    public LatLng getPosition() {
        return new LatLng(latlon.get(0), latlon.get(1));
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return placename;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
        putDate(timetable);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public ArrayList<Double> getLatlon() {
        return latlon;
    }

    public void setLatlon(ArrayList<Double> latlon) {
        this.latlon = latlon;
    }

    public String[][] getDate() {
        return date;
    }

    public void setDate(String date[][]) {

        this.date = date;
    }

    public void putDate(String timetable){

        String[] enter = new String[1];
        if(timetable.contains(";")) {
            enter = timetable.split(";");
        }
        else {
            enter[0] = timetable;
        }
        String[][] enter2 = new String[enter.length][2];
        this.date = new String[enter.length][2];

        for (int i = 0; i < enter.length; i++) {
            String[] date1 = enter[i].split(" ");
            this.date[i][0] = date1[0].replace("T"," à ");
            this.date[i][1] = date1[1].replace("T"," à ");
        }
    }


}
