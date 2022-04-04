package controller;

import model.Building;

public class IntelligentBuildings {
    private int numBuildings;
    private Building[] buildings;

    public IntelligentBuildings(int numBuildings) {
        this.numBuildings = numBuildings;
        buildings = new Building[numBuildings];
    }

    public int getNumBuildings() {
        return numBuildings;
    }

    public void setNumBuildings(int numBuildings) {
        this.numBuildings = numBuildings;
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public Building getBuilding(String code) {
        Building foundBuilding = null;

        for (Building b : buildings) {
            if (b.getCode().equals(code)) {
                foundBuilding = b;
                break;
            }
        }

        return foundBuilding;
    }

    public void addBuilding(String code, int floors, int officesPerFloor, int people) {
        Building newBuilding = new Building(code, floors, officesPerFloor, people);

        for (int i = 0; i < buildings.length; i++) {
            if (buildings[i] == null) {
                buildings[i] = newBuilding;
                break;
            }
        }
    }
}
