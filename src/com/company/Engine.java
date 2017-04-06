package com.company;


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



    public void moveEngine() {
        // Vonat léptetése
        Rail tempPrevRail = this.getPrevPos();
        Rail tempActRail = this.getActPos();
        this.setPrevPos(actPos);
        Rail nextPos = tempActRail.getNextRail(tempPrevRail, this);
        this.setActPos(nextPos);
        if (nextPos != null)
            nextPos.occupied = true;

        if (! (actPos == null))
            System.out.println( "<" + this.name + " at " + actPos.name + ">");

        // Következő TrainElement mozgatása
        nextTrainElement.move(tempActRail);

    }
}
