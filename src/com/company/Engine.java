package com.company;


import java.util.HashMap;
import java.util.List;

public class Engine extends Train_Element {



    // A vonat léterhozásáért felelős konstruktor
    // ráállítja a startPos-ra az Engine-t, és beállítja az első kocsit
    public Engine(Rail startPos, int numberOfCars, String name) {
        this.name = name;
        actPos = startPos;
        System.out.print("\"" + name + "\" at: \"" + actPos.name + "\" with cars: ");
        prevPos = null;
        nextTrainElement = new Car(numberOfCars, 1);
    }


    // Konstruktor a map-nek xml-ből való betöltéshez
    public Engine(String name, int numberOfTrainElement, HashMap<Integer, String> trainElements) {
        this.name = name;
        this.actPos = null;

        // ha kocsit kell példányosítanunk

        if (!trainElements.get(1).equals(""))
            nextTrainElement = new Car(numberOfTrainElement, trainElements, 1);
        else
            nextTrainElement = new Cargo(numberOfTrainElement, trainElements, 1);
    }


    // Az első nem üres kocsival tér vissza:
    //      csak a Car tud true-val visszatérni
    //      Null, ha mindegyik kocsi üres
    public Car getFirstNotEmptyCar() {
        Train_Element next = nextTrainElement;
        while ( next != null ) {
            if (!next.isEmpty()) {
                return (Car) next;
            }
            else {
                next = next.getNextTrainElement();
            }
        }
        return null;
    }



    public void moveEngine(int counter) {

        // Vonat léptetése
    	Rail nextPos = actPos.getNextRail(prevPos, this);
    	move(nextPos, counter);
    	
        if (actPos != null)
            System.out.println( "<" + this.name + " at " + actPos.name + ">");

    }
}
