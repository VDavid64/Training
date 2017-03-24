package com.company;



public class Car extends Train_Element {

    private Color color;


    // TODO
    // Konstruktor
    public Car(int numberOfCars) {
        color = Color.getRandomColor();
        System.out.format(" %s ", color);
        isEmpty = false;
        if (numberOfCars > 1)
            nextTrainElement = new Car(numberOfCars-1);
        else {
            nextTrainElement = null;
            System.out.println();
        }
    }


    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }


    @Override
    public void move(Rail nextRail) {

        prevPos = actPos;
        actPos = nextRail;


        // felszállás: ha a kocsi nem üres, azonosak a színek és van utas az állomáson
        if (this.isEmpty() && actPos.getColor() == this.color && actPos.getPassenger() > 0) {
            this.setEmpty(false);
            actPos.setPassenger();
        }

        /*
        if (! (actPos == null))
            System.out.println("kocsi új pozíciója: " + actPos.name);
        */

        // ha van még kocsi, és az előző körben ez a kocsi már
        // a pályán volt, akkor léptetjük a következőt is
        if (nextTrainElement != null && prevPos != null)
            nextTrainElement.move(prevPos);
    }


}
