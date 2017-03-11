package com.company;


import java.util.Scanner;

public class Engine extends Train_Element {

    private Car firstCar;


    // A vonat léterhozásáért felelős konstruktor
    // ráállítja a startPos-ra az Engine-t, és beállítja az első kocsit
    public Engine(Rail startPos, int numberOfCars) {
        actPos = startPos;
        prevPos = null;
        firstCar = new Car(numberOfCars);
    }



    /////////////
    // TO-DO: Tunnel és Switch lekezelése
    public void move() {


        // Lehetséges pontok lekérése
        Rail actRail = getActPos();
        Rail railNext = actPos.getNextRail();
        Rail railPrev = actPos.getPrevRail();

        // Elinduláskor probléma - prevPos és az actPos valamelyik Rail szomszédja is null
        // Azaz ha a prev pos még null, akkor még nem léptünk
        // Ekkor a következő nem null rail-t választjuk
        if (this.getPrevPos() == null && railNext != null) {
            if (railNext != null) {
                setPrevPos(actPos);
                setActPos(railNext);
            }
            else {
                setPrevPos(actPos);
                setActPos(railPrev);
            }
        }

        // ha a naxtrail-en voltunk az előbb, akkor a rail2 a következő
        else if (railNext == this.getPrevPos()) {
            setPrevPos(actPos);
            setActPos(railPrev);
        }

        // ha prevRail-ben, akkor a rail1 a következő
        else {
            setPrevPos(actPos);
            setActPos(railNext);
        }

        // végül pedig meghívjuk a fistcar move-ját
        if (firstCar != null)
            firstCar.move(actRail);


    }


    // az első nem üres kocsival tér vissza, ha null, mindegyik kocsi üres
    public void getFirstNotEmptyCar() {

        System.out.println("        -> [Engine].getFirstNotEmptyCar()");

        firstCar.getFirstNotEmptyCar();

        System.out.println("        <- [Engine].getFirstNotEmptyCar()");
    }


    public Car getFirstCar() {
        return firstCar;
    }

}
