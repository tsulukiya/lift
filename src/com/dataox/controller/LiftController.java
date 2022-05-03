package com.dataox.controller;

import com.dataox.model.Step;
import com.dataox.model.Building;
import com.dataox.model.Lift;
import com.dataox.model.Passenger;
import com.dataox.view.BuildingView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LiftController {
    private Building building;

    public LiftController() {
    }

    private void init() {
        List<Passenger> passengersInLift = new ArrayList<>();
        List<List<Passenger>> floor = new ArrayList<>();
        Lift lift = new Lift(passengersInLift);
        this.building = new Building(lift, floor);
        this.building.getLift().setCurrentFloorNumber(0);
        this.building.setHeight(5 + (int) (Math.random() * 16));
        this.building.getLift().setStep(Step.UP);

        for (int i = 0; i < this.building.getHeight(); i++) {
            List<Passenger> passengersInFloor = new ArrayList<>();
            for (int j = 0; j < (int) (Math.random() * 11); j++) {
                passengersInFloor.add(new Passenger((int) (Math.random() * (this.building.getHeight()))));
            }
            floor.add(passengersInFloor);
        }
    }

    public void run() {
        init();

        BuildingView buildingView = new BuildingView(this.building);
        buildingView.printBuilding();

        while (isNeedContinueApp()) {
            if (isNeedLiftToStop()) {
                stopInTheFloor();
                doLiftTheNextFloor();
                buildingView.printBuilding();
            } else {
                doLiftTheNextFloor();
            }
            if ((this.building.getHeight() - 1 == building.getLift().getCurrentFloorNumber() &&
                    (this.building.getLift().getStep() == Step.UP)) ||
                    ((building.getLift().getCurrentFloorNumber() == 0) &&
                            (this.building.getLift().getStep() == Step.DOWN))) {
                reverseLiftOfTheLastOrFirstFloor();
                stopInTheFloor();
                doLiftTheNextFloor();
                buildingView.printBuilding();
            }
        }
    }

    private void doLiftTheNextFloor() {
        if (this.building.getLift().getStep() == Step.UP) {
            this.building.getLift().setCurrentFloorNumber(this.building.getLift().getCurrentFloorNumber() + 1);
        }
        if (this.building.getLift().getStep() == Step.DOWN) {
            this.building.getLift().setCurrentFloorNumber(this.building.getLift().getCurrentFloorNumber() - 1);
        }
    }

    private void reverseLiftOfTheLastOrFirstFloor() {
        if (this.building.getLift().getStep() == Step.UP) {
            this.building.getLift().setStep(Step.DOWN);
        } else if (this.building.getLift().getStep() == Step.DOWN) {
            this.building.getLift().setStep(Step.UP);
        }
    }

    private boolean isNeedLiftToStop() {
        if (this.building.getLift().getStep() == Step.UP) {
            for (Passenger passenger : this.building.getListFloors().get(this.building.getLift().getCurrentFloorNumber())) {
                if (passenger.getGoToFloor() > this.building.getLift().getCurrentFloorNumber()) {
                    return true;
                }
            }
        }
        if (this.building.getLift().getStep() == Step.DOWN) {
            for (Passenger passenger : this.building.getListFloors().get(building.getLift().getCurrentFloorNumber())) {
                if (passenger.getGoToFloor() < this.building.getLift().getCurrentFloorNumber()) {
                    return true;
                }
            }
        }
        for (Passenger passenger : this.building.getLift().getPassengerList()) {
            if (passenger.getGoToFloor() == this.building.getLift().getCurrentFloorNumber()) {
                return true;
            }
        }
        return false;
    }

    private boolean isNeedContinueApp() {
        for (int floorNumber = 0; floorNumber < this.building.getListFloors().size(); floorNumber++) {
            List<Passenger> passengers = this.building.getListFloors().get(floorNumber);
            for (Passenger passenger : passengers) {
                if (passenger.getGoToFloor() != floorNumber) {
                    return true;
                }
            }
        }
        if (!this.building.getLift().getPassengerList().isEmpty()) {
            return true;
        }
        return false;
    }

    private Lift stopInTheFloor() {
        for (Iterator<Passenger> iterator = this.building.getLift().getPassengerList().iterator(); iterator.hasNext(); ) {
            Passenger passenger = iterator.next();
            if (passenger.getGoToFloor() == this.building.getLift().getCurrentFloorNumber()) {
                this.building.getListFloors().get(this.building.getLift().getCurrentFloorNumber()).add(passenger);
                iterator.remove();
            }
        }

        if (Lift.getMaxCapacity() > this.building.getLift().getPassengerList().size()) {
            for (Iterator<Passenger> iterator = this.building.getListFloors().get(building.getLift().
                    getCurrentFloorNumber()).iterator();
                 iterator.hasNext(); ) {
                Passenger passenger = iterator.next();
                if (this.building.getLift().getStep() == Step.UP &&
                        passenger.getGoToFloor() > this.building.getLift().getCurrentFloorNumber() &&
                        Lift.getMaxCapacity() > this.building.getLift().getPassengerList().size()) {
                    this.building.getLift().getPassengerList().add(passenger);
                    iterator.remove();
                } else if (this.building.getLift().getStep() == Step.DOWN &&
                        passenger.getGoToFloor() < this.building.getLift().getCurrentFloorNumber() &&
                        Lift.getMaxCapacity() > this.building.getLift().getPassengerList().size()) {
                    this.building.getLift().getPassengerList().add(passenger);
                    iterator.remove();
                }
            }
        }
        return this.building.getLift();
    }
}