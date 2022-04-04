package model;

public class Person {
    private String name;
    private int floor;
    private int destination;

    public Person(String name, int floor, int destination) {
        this.name = name;
        this.floor = floor;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }
}
