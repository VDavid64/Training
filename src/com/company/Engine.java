package com.company;


public class Engine extends Train_Element {

    private Car firstCar;


    // A vonat léterhozásáért felelős konstruktor
    // ráállítja a startPos-ra az Engine-t, és beállítja az első kocsit
    public Engine(Rail startPos, int numberOfCars) {
        actPos = startPos;
        prevPos = null;
        firstCar = new Car(numberOfCars);
    }



    public void move() {

        Rail actRail = getActPos();

        Rail railNext = actPos.getNextRail();
        Rail railPrev = actPos.getPrevRail();

        // Elinduláskor probléma - prev és valamelyik rail is null
        // Azaz ha a prev pos még null, akkor még nem léptünk
        // ekkor a következő nem null rail-t választjuk
        if (prevPos == null && railNext != null) {
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
        else if (railNext == prevPos) {
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
    public Car getFirstNotEmptyCar() {
        if (firstCar != null)
            return firstCar.getFirstNotEmptyCar();
        else
            return null;
    }




}
