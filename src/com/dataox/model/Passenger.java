package com.dataox.model;

public class Passenger {
    private int goToFloor;

    public Passenger(int goToFloor) {
        this.goToFloor = goToFloor;
    }

    public int getGoToFloor() {
        return goToFloor;
    }

    public void setGoToFloor(int goToFloor) {
        this.goToFloor = goToFloor;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "goToFloor=" + goToFloor +
                '}';
    }
}
