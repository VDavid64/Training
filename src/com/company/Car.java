package com.company;



public class Car extends Train_Element {

    private Color color;
    private boolean canEmpty;

    // Konstruktor
    public Car(int numberOfCars, int i) {
        // color = Color.getRandomColor();
        this.color = Color.GREEN;
        this.name = "car_"+ i;
        this.isEmpty = false;
        this.canEmpty = true;

        System.out.format(" %s ", color);

        if (numberOfCars > 1)
            nextTrainElement = new Car(numberOfCars-1, ++i);
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


        // Felszabadítjuk a sínt, melyről továbblépünk
        if (nextTrainElement == null && prevPos != null)
            prevPos.occupied=false;

        // Lépés
        prevPos = actPos;
        actPos = nextRail;


        // Felszállás:
        //      Ha nem üres a kocsi, azonosak a színek és van utas az állomáson
        if (this.isEmpty() && actPos.getColor() == this.color && actPos.getPassenger() > 0 ) {
            this.setEmpty(false);
            actPos.setPassenger();
            System.out.println("    <Felszállás történt: " + this.name + " >");                     // Kiírás sak debug célra, majd törölni kell
            canEmpty = false;
        }


        // ha van még kocsi, és az előző körben ez a kocsi már
        // a pályán volt, akkor léptetjük a következőt is
        if (nextTrainElement != null && prevPos != null)
            nextTrainElement.move(prevPos);

    }


}
