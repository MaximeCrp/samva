package com.example.utilisateur.samva;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event implements Serializable, ClusterItem {



    private String title;
    private String timetable;
   // private Date[][] date; //tableau à 2 dimmensions : 1 ligne correspond à une occurence de l'événement
    //les deux colonnes correspondent à la date de début et celle de fin
    private String address;
    private String[][] date;
    private String image;
    private String placename;
    private ArrayList<Double> latlon;
    //private Date date;



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

    public void setTimetable(String timetable) throws ParseException {
        this.timetable = timetable;

        //putDate();

        /*
        String[] enter = timetable.split(";");
        for (int i = 0; i < enter.length; i++) {
            String[] date = enter[i].split(" ");
            this.date[i][0] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date[0]);
            this.date[i][1] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date[1]);
        }*/

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
/*
    public void putDate() throws ParseException {
        String[] enter = timetable.split(";");
        String[][] enter2 = new String[enter.length][2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm");

        for (int i = 0; i < enter.length; i++) {
            String[] date = enter[i].split(" ");
            this.date[i][0] = date[0];
            this.date[i][1] = date[1];

            //this.date[i][0] = sdf.parse(enter2[i][0]);
            //this.date[i][1] = sdf.parse(enter2[i][1]);
        }


        String date = departTime.getText().toString();
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/


}
