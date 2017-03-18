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
        System.out.println("        -> [Car].isEmpty()");
        System.out.println("        <- [Car].isEmpty()");
    }


    public Car(){
    }

    public void setEmpty() {
        System.out.println("        -> [Car].setEmpty()");
        System.out.println("        <- [Car].setEmpty()");
        isEmpty = true;
    }


    public Car getNextCar() {
        return nextCar;
    }

    public Color getColor() {
        System.out.println("        -> [Car].getColor()");
        System.out.println("        <- [Car].getColor()");
        return color;
    }



    // Kocsi mozgatása, rekurzívan meghívja a következő kocsi move függvényét
    public void move(Rail nextRail) {
        System.out.println("            -> [Car].move()");
        System.out.println("            <- [Car].move()");

        prevPos = actPos;
        actPos = nextRail;


        // ha van még kocsi, és az előző körben ez a kocsi már
        // a pályán volt, akkor léptetjük a következőt is
        if (nextCar != null && prevPos != null)
            nextCar.move(prevPos);
    }


    // az első nem üres kocsival tér vissza, ha null, mindegyik kocsi üres
    public Car getFirstNotEmptyCar(int param) {

        System.out.println("            -> [Car].getFirstNotEmptyCar()");
        if (param == 8)
        {
            System.out.println("8.2: Csak üres kocsikból áll? ");

            String command;
            Scanner input = new Scanner(System.in);
            command = input.nextLine();

            if (command.equals("I")) {
                System.out.println("            <- [Car].getFirstNotEmptyCar(): null");
                return null;
            }
            else if (!command.equals("N")) {
                throw new IllegalArgumentException();
            }
            else {
                System.out.println("            <- [Car].getFirstNotEmptyCar(): Car");
                return new Car();
            }
        }
        if (param == 6)
        {
            System.out.println("            <- [Car].getFirstNotEmptyCar(): Car");
            return new Car();

        }
        return null;
    }

}
