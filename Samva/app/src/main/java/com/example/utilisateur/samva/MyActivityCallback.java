package com.example.utilisateur.samva;

public interface MyActivityCallback {

    public void newTravel();
    public void addTravel(Travel travel);
    public void travelDetails(Travel travel, int position);
    public void passengerAdded(Travel travel);


}
