package com.dataox.view;

import com.dataox.model.Step;
import com.dataox.model.Building;
import com.dataox.model.Passenger;

public class BuildingView {
    private Building building;

    public BuildingView(Building building) {
        this.building = building;
    }

    public void printBuilding() {
        System.out.println();
        System.out.println("Application step");

        String drive;

        if (building.getLift().getStep() == Step.UP) {
            drive = "v";
        } else {
            drive = "^";
        }

        for (int floorNumber = 0; floorNumber < building.getListFloors().size(); floorNumber++) {
            System.out.print("Floor№-" + (floorNumber));
            for (Passenger passenger : building.getListFloors().get(floorNumber)) {
                System.out.print(" " + passenger.getGoToFloor());

            }
            if (floorNumber == building.getLift().getCurrentFloorNumber()) {
                System.out.print("| " + drive);
                for (Passenger passenger : building.getLift().getPassengerList()) {
                    System.out.print(passenger.getGoToFloor());
                }
            }
            System.out.println();
        }
    }
}