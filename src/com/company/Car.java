package com.company;



public class Car extends Train_Element {

    private boolean isEmpty;
    private Color color;
    private Car nextCar;

    
    public Car(int numberOfCars) {
        color = Color.getRandomColor();
        System.out.format(" %s ", color);
        isEmpty = false;
        if (numberOfCars > 1)
            nextCar = new Car(numberOfCars-1);
        else {
            nextCar = null;
            System.out.println();
        }
    }


    public boolean isEmpty() {
        return isEmpty;
    }


    public void setEmpty() {
        isEmpty = true;
    }


    public Car getNextCar() {
        return nextCar;
    }

    public Color getColor() {
        return color;
    }
    
    
    // Kocsi mozgatása, rekurzívan meghívja a következő kocsi move függvényét
    public void move(Rail nextRail) {

        Rail tempPrevRail = prevPos;
        prevPos = actPos;
        actPos = nextRail;

        // ha van még kocsi, és az előző körben ez a kocsi már
        // a pályán volt, akkor léptetjük a következőt is
        if (nextCar != null && prevPos != null)
            nextCar.move(prevPos);
    }
}
