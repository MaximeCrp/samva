package com.example.utilisateur.samva;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Travel implements Serializable {

    private String title;
    private String sam;
    private int nbPlaces;
    private String departPlace;
    private String returnPlace;
    private Date departTime;
    private Date returnTime;
    private ArrayList<String> passengers ;

    Travel() {}

    Travel(String title, String sam, int nb, Date departTime, String departPlace, Date returnTime, String returnPlace, ArrayList<String> passengers) {
        this.title = title;
        this.sam = sam;
        this.nbPlaces = nb;
        this.departTime = departTime;
        this.departPlace = departPlace;
        this.returnTime = returnTime;
        this.returnPlace = returnPlace;
        this.passengers = passengers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSam() {
        return sam;
    }

    public void setSam(String sam) {
        this.sam = sam;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
        passengers = new ArrayList<String>(nbPlaces);
    }

    public String getDepartPlace() {
        return departPlace;
    }

    public void setDepartPlace(String departPlace) {
        this.departPlace = departPlace;
    }

    public String getReturnPlace() {
        return returnPlace;
    }

    public void setReturnPlace(String returnPlace) {
        this.returnPlace = returnPlace;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public void setPassengers(ArrayList<String> passengers) { this.passengers = passengers;}

    public boolean addPassenger(String name) {
        if(nbPlaces>0) {
            passengers.add(name);
            nbPlaces--;
            return true;
        }
        else
            return false;

    }

    public ArrayList<String> getPassengers() {
        return this.passengers;
    }
}
