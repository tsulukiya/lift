package com.dataox.model;

import java.util.List;


public class Building {
    private int height;
    private Lift lift;
    private List<List<Passenger>> listFloors;

    public Building(Lift lift, List<List<Passenger>> listFloors) {
        this.lift = lift;
        this.listFloors = listFloors;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    public List<List<Passenger>> getListFloors() {
        return listFloors;
    }

    public void setListFloors(List<List<Passenger>> listFloors) {
        this.listFloors = listFloors;
    }

    @Override
    public String toString() {
        return "Building{" +
                "height=" + height +
                ", lift=" + lift +
                ", listFloors=" + listFloors +
                '}';
    }
}