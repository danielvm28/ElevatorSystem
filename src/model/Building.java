package model;

import java.util.ArrayList;

public class Building {
    // Attributes
    private String code;
    private int floors;
    private int officesPerFloor;
    private int people;
    private ArrayList<Person> withoutOffice;

    // Relations
    private Queue<Person>[] floorQueues;
    private HashTable<Integer, String> officeMap;
    private Stack<Person> elevator;

    public Building(String code, int floors, int officesPerFloor, int people) {
        this.code = code;
        this.floors = floors;
        this.officesPerFloor = officesPerFloor;
        this.people = people;
        elevator = new Stack<>();
        floorQueues = new Queue[floors];
        officeMap = new HashTable<>();
        withoutOffice = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getOfficesPerFloor() {
        return officesPerFloor;
    }

    public void setOfficesPerFloor(int officesPerFloor) {
        this.officesPerFloor = officesPerFloor;
    }

    public Queue<Person>[] getFloorQueues() {
        return floorQueues;
    }

    public HashTable<Integer, String> getOfficeMap() {
        return officeMap;
    }

    public Stack<Person> getElevator() {
        return elevator;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public ArrayList<Person> getWithoutOffice() {
        return withoutOffice;
    }

    public void setWithoutOffice(ArrayList<Person> withoutOffice) {
        this.withoutOffice = withoutOffice;
    }

    public void queuePersonInFloor(String name, int floor, int destination) {
        Person p = new Person(name, floor, destination);

        if (floorQueues[floor-1] != null) {
            floorQueues[floor-1].add(p);
        } else {
            Queue<Person> q = new Queue<>();
            q.add(p);
            floorQueues[floor-1] = q;
        }
    }

    public String operate() {
        String operations = "";
        ElevatorQueue<Integer> elevatorQueue = new ElevatorQueue<>();
        int totalOffices = officesPerFloor * floors;
        int peopleWaiting = people;

        // Fills the elevator queue with the total given floors
        for (int i = floors; i > 0; i--) {
            elevatorQueue.add(i);
        }

        while (peopleWaiting > 0) {
            // Gets the current floor from the elevator queue
            int currFloor = elevatorQueue.operate();

            // Gets the people off the elevator according to the office of destination
            Stack<Person> temp = new Stack<>();

            if (elevator.size() != 0) {
                while (elevator.size() > 0){
                    Person currPerson = elevator.peek();

                    // Calculate the floor of destination
                    int officeDestination = currPerson.getDestination();
                    int floorDestination = ((totalOffices - officeDestination) / officesPerFloor) + 1;

                    if (officeDestination > totalOffices) {
                        withoutOffice.add(elevator.pop());
                        peopleWaiting--;

                        operations += currPerson.getName() + " tried to go to a non-existing office " + "\n";
                    } else {
                        if (floorDestination == currFloor) {
                            // Get the person to its office or mark the person as without office if
                            // the office is already occupied
                            if (officeMap.get(officeDestination) == null) {
                                elevator.pop();
                                officeMap.put(officeDestination, currPerson.getName());
                                peopleWaiting--;

                                operations += currPerson.getName() + " went to the floor number " + currFloor + ", and to" +
                                        " the office number " + officeDestination + "\n";
                            } else {
                                withoutOffice.add(elevator.pop());
                                peopleWaiting--;

                                operations += currPerson.getName() + " went to the floor number " + currFloor + ", and tried to" +
                                        " get to the office number " + officeDestination + " but it was occupied by " +
                                        officeMap.get(officeDestination) + "\n";
                            }
                        } else {
                            // Temporarily get the people out of the elevator to keep looking
                            temp.add(elevator.pop());
                        }
                    }
                }

                // Get the people back into the elevator
                while (temp.size() > 0) {
                    elevator.add(temp.pop());
                }
            }

            // Fills the elevator with the people that will come in
            // This order of operations considers that the people that come into the elevator, will not stay in the same floor
            while (floorQueues[currFloor - 1] != null && floorQueues[currFloor-1].size() > 0) {
                elevator.add(floorQueues[currFloor-1].pop());
            }
        }

        return operations;
    }
}
