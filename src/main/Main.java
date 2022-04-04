package main;

import java.io.*;
import java.util.Scanner;

import controller.IntelligentBuildings;
import model.Building;
import model.HashTable;
import model.Person;

public class Main {
    public static IntelligentBuildings intelligentBuildings;

    public static String operateElevatorInBuilding(String buildingCode) {
        String operation = "";
        Building buildingToOperate = intelligentBuildings.getBuilding(buildingCode);

        operation += "\n-------------------------------------------------" +
                "\n\nOperations of Building " + buildingCode + ":\n\n";
        operation += buildingToOperate.operate();

        return operation;
    }

    public static void printResults(String output) {
        String finalResults = "\n\n ****************** FINAL RESULTS ******************";;

        Building[] buildings = intelligentBuildings.getBuildings();

        for (int i = 0; i < buildings.length; i++) {
            Building currBuilding = buildings[i];
            HashTable officeMap = currBuilding.getOfficeMap();
            finalResults += "\n\nFinal State of the building " + currBuilding.getCode() + ":\n";

            for (int j = 1; j <= currBuilding.getOfficesPerFloor() * currBuilding.getFloors(); j++) {
                if(officeMap.get(j) != null) {
                    finalResults += officeMap.get(j) + "  ";
                }
            }
        }

        System.out.println(output + finalResults);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String line = reader.readLine();

        String output = "";
        int numBuildings = Integer.parseInt(line);

        intelligentBuildings = new IntelligentBuildings(numBuildings);

        for (int i = 0; i < numBuildings; i++) {
            HashTable<String, Integer> offices = new HashTable<>();

            line = reader.readLine();
            String[] inputsBuilding = line.split(" ");

            String buildingCode = inputsBuilding[0];
            int numPeople = Integer.parseInt(inputsBuilding[1]);
            int numFloors = Integer.parseInt(inputsBuilding[2]);
            int numOffice = Integer.parseInt(inputsBuilding[3]);

            intelligentBuildings.addBuilding(buildingCode, numFloors, numOffice, numPeople);
            Building currBuilding = intelligentBuildings.getBuilding(buildingCode);

            for (int j = 0; j < numPeople; j++) {
                line = reader.readLine();
                String[] personArr = line.split(" ");

                String personName = personArr[0];
                int personFloor = Integer.parseInt(personArr[1]);
                int personDestination = Integer.parseInt(personArr[2]);

                currBuilding.queuePersonInFloor(personName, personFloor, personDestination);
            }

            output += operateElevatorInBuilding(buildingCode);
        }

        printResults(output);
    }
}