package com.dataox.model;

import java.util.List;

public class Lift {
    private List<Passenger> passengerList;
    private int currentFloorNumber;
    private static final int MAX_CAPACITY = 5;
    private Step step;

    public Lift(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public int getCurrentFloorNumber() {
        return currentFloorNumber;
    }

    public void setCurrentFloorNumber(int currentFloorNumber) {
        this.currentFloorNumber = currentFloorNumber;
    }

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "Lift{" +
                "passengerList=" + passengerList +
                ", currentFloorNumber=" + currentFloorNumber +
                ", step=" + step +
                '}';
    }
}