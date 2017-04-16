package com.company;


import java.util.HashMap;

public class Cargo extends Train_Element {


    // Konstruktor randomhoz
    public Cargo(int numberOfCars, int i) {

        System.out.println("Cargo");
        isEmpty = false;
        this.name = "cargo_"+ i;
        if (numberOfCars > 1)
            nextTrainElement = random.nextBoolean() ? new Car(numberOfCars-1, i++) : new Cargo(numberOfCars-1, i++);
        else {
            nextTrainElement = null;
            System.out.println();
        }
    }


    // Konstruktor xml-hez
    public Cargo(int numberOfTrainElement, HashMap<Integer, String> trainElements, int counter) {
        this.name = "cargo_"+counter;
        this.isEmpty = false;

        counter++;

        // ha nem kell több elemet generálni
        if (numberOfTrainElement == 1) {
            nextTrainElement = null;
        }


        // ha kocsit kell generálni:
        else if (numberOfTrainElement > 1 && !trainElements.get(counter).equals("")) {
            nextTrainElement = new Car(numberOfTrainElement-1, trainElements, counter);
        }

        // ha teherkocsit:
        else {
            nextTrainElement = new Cargo(numberOfTrainElement-1, trainElements, counter);
        }
    }
}
