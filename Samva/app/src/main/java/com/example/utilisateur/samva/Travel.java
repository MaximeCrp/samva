package com.example.utilisateur.samva;

import java.io.Serializable;
import java.util.Date;


public class Travel implements Serializable {

    private String title;
    private String sam;
    private int nbPlaces;
    private String departPlace;
    private String returnPlace;
    private Date departTime;
    private Date returnTime;
    private String[] passengers;

    Travel() {}

    Travel(String title, String sam, int nb, Date departTime, String departPlace, Date returnTime, String returnPlace) {
        this.title = title;
        this.sam = sam;
        this.nbPlaces = nb;
        this.departTime = departTime;
        this.departPlace = departPlace;
        this.returnTime = returnTime;
        this.returnPlace = returnPlace;
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

    public boolean addPassenger(String name) {
        if(nbPlaces>0) {
            passengers[nbPlaces] = name;
            nbPlaces--;
            return true;
        }
        else
            return false;

    }

}
