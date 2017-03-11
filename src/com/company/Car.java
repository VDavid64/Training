package com.company;


import java.util.Scanner;

public class Car extends Train_Element {

    private boolean isEmpty;
    private Color color;
    private Car nextCar;




    public Car(int numberOfCars) {
        color = Color.getRandomColor();
        isEmpty = false;
        if (numberOfCars > 0)
            nextCar = new Car(numberOfCars-1);
        else
            nextCar = null;
    }


    public void isEmpty() {
        System.out.println("                -> [Car].isEmpty()");
        System.out.println("                <- [Car].isEmpty()");
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

        Rail actRail = getActPos();

        setActPos(nextRail);
        setPrevPos(actRail);

        // ha van még kocsi, és az előző körben ez a kocsi már
        // a pályán volt, akkor léptetjük a következőt is
        if (nextCar != null && actRail != null)
            nextCar.move(actRail);
    }


    // az első nem üres kocsival tér vissza, ha null, mindegyik kocsi üres
    public void getFirstNotEmptyCar() {

        System.out.println("            -> [Car].getFirstNotEmptyCar()");

        System.out.println("8.2: Csak üres kocsikból áll? ");

        String command;
        Scanner input = new Scanner(System.in);
        command = input.nextLine();

        if (command.equals("I"))
            this.isEmpty();

        System.out.println("            <- [Car].getFirstNotEmptyCar()");

    }

}
