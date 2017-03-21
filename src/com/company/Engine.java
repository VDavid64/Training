package com.company;


public class Engine extends Train_Element {

    private Car firstCar;


    // A vonat léterhozásáért felelős konstruktor
    // ráállítja a startPos-ra az Engine-t, és beállítja az első kocsit
    public Engine(Rail startPos, int numberOfCars) {
        actPos = startPos;
        System.out.print(" Engine at " + actPos.name + " with cars: ");
        prevPos = null;
        firstCar = new Car(numberOfCars);
    }



    /////////////
    // TO-DO: Tunnel és Switch lekezelése
    public void move() {


        // Vonat léptetése
        Rail tempPrevRail = prevPos;
        Rail tempActRail = actPos;
        this.setPrevPos(actPos);
        this.setActPos(tempActRail.getNextRail(tempPrevRail, this));

        if (! (actPos == null))
            System.out.println("Engine új pozíciója: " + actPos.name);

        // végül pedig meghívjuk a fistcar move-ját
        firstCar.move(tempActRail);


    }


    // TODO
    // az első nem üres kocsival tér vissza, ha null, mindegyik kocsi üres
    public Car getFirstNotEmptyCar() {
        if (firstCar != null) {
            if (firstCar.isEmpty()) {
                return firstCar;
            }
            else {
                Car c = firstCar.getNextCar();
                while ( c != null ) {
                    if (c.isEmpty()) {
                        return c;
                    }
                    else {
                        c = c.getNextCar();
                    }
                }
                return null;
            }
        }
        else
            return null;
    }


    public Car getFirstCar() {
        return firstCar;
    }

}
