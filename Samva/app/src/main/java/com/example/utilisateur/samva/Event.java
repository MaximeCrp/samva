package com.example.utilisateur.samva;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event implements Serializable {



    private String title;
    private String timetable;
    private Date[][] date; //tableau à 2 dimmensions : 1 ligne correspond à une occurence de l'événement
    //les deux colonnes correspondent à la date de début et celle de fin
    private String address;
    private String image;
    private String placename;
    private ArrayList<Double> latlon;
    //private Date date;



    Event() {}

    public String getTitle() {
        return title;
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

    public Date[][] getDate() {
        return date;
    }

    public void setDate(Date date[][]) {

        this.date = date;
    }

    public void putDate() throws ParseException {
        String[] enter = timetable.split(";");
        String[][] enter2 = new String[enter.length][2];
        for (int i = 0; i < enter.length; i++) {
            String[] date = enter[i].split(" ");
            enter2[i][0] = date[0];
            enter2[i][1] = date[1];
            this.date[i][0] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(enter2[i][0]);
            this.date[i][1] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(enter2[i][1]);
        }
    }


}
