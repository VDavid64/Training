package com.company;


public class Cargo extends Train_Element {


    // TODO
    // Konstruktor
    public Cargo(int numberOfCars) {

        System.out.println("Cargo");
        isEmpty = false;

        if (numberOfCars > 1)
            nextTrainElement = new Cargo(numberOfCars-1);
        else {
            nextTrainElement = null;
            System.out.println();
        }
    }



}
