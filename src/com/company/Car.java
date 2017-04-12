package com.company;


import java.util.HashMap;
import java.util.List;

public class Car extends Train_Element {

    private Color color;
    public int roundLastEmpty;



    // Konstruktor
    public Car(int numberOfCars, int i) {
        // color = Color.getRandomColor();
        this.color = Color.GREEN;
        this.name = "car_"+ i;
        this.isEmpty = false;
        this.roundLastEmpty = 0;

        System.out.format(" %s ", color);

        if (numberOfCars > 1)
            nextTrainElement = new Car(numberOfCars-1, ++i);
        else {
            nextTrainElement = null;
            System.out.println();
        }
    }



    public Car(int numberOfTrainElement, HashMap<Integer, String> trainElements, int counter) {
        this.name = "car_"+counter;
        this.color = Color.valueOf(trainElements.get(counter));
        this.isEmpty = false;
        this.roundLastEmpty = 0;

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


    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }


    @Override
    public void move(Rail nextRail, int counter) {


        // Utolsó kocsi felelőssége: felszabadítjuk a sínt, amelyről továbblépünk
        if (nextTrainElement == null && actPos != null)
            actPos.occupied=false;

        // Lépés
        prevPos = actPos;
        actPos = nextRail;


        // Felszállás:
        //      Ha nem üres a kocsi, azonosak a színek és van utas az állomáson
        if (this.isEmpty() && actPos.getColor() == this.color && actPos.getPassenger() > 0 ) {
            this.setEmpty(false);
            actPos.setPassenger();
            System.out.println("    <Passengers got on the train: " + this.getActPos().name + ", "+ this.name + " >");                     // Kiírás sak debug célra, majd törölni kell
            roundLastEmpty = counter;
        }


        // ha van még kocsi, és az előző körben ez a kocsi már
        // a pályán volt, akkor léptetjük a következőt is
        if (nextTrainElement != null && prevPos != null)
            nextTrainElement.move(prevPos, counter);
    }


}
