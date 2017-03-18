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

        System.out.println("        -> [Engine].move()");


        ///// TO-DO
        // Elinduláskor probléma - prevPos és az actPos valamelyik Rail szomszédja is null
        // Azaz ha a prev pos még null, akkor még nem léptünk
        // Ekkor a következő nem null rail-t választjuk
        /*
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
        */

        // Vonat léptetése
        Rail tempPrev = prevPos;
        Rail tempAct = actPos;
        setPrevPos(actPos);
        //setActPos(tempAct.getNextRail(tempPrev, this));


        // végül pedig meghívjuk a fistcar move-ját
        if (firstCar != null)
            firstCar.move(tempAct);

        //TODO alagút és váltó események

        //Alagút esemény
        System.out.println("5.1: Alagútszájra léptünk? ");
        String command;
        Scanner input = new Scanner(System.in);
        command = input.nextLine();

        if (command.equals("I"))
        {
            System.out.println("5.1.1: Üres az alagút?");
            String command2;
            command2 = input.nextLine();
            if (command2.equals("I"))
            {
                //Belépünk az alagútba
            }
            else if (!command2.equals("N")) {
                throw new IllegalArgumentException();
            }
            else
            {
                //A sínen haladunk tovább
            }
        }
        else if (!command.equals("N")) {
            throw new IllegalArgumentException();
        }
        else
        {
            //Sínen haladunk tovább vagy váltó

            //Váltó esemény
            System.out.println("5.2: Váltóra léptünk? ");
            command = input.nextLine();

            if (command.equals("I"))
            {
                //Váltó esemény bekövetkezik
            }
            else if (!command.equals("N")) {
                throw new IllegalArgumentException();
            }
            else
            {
                //Sínen haladunk tovább
            }
        }

        System.out.println("        <- [Engine].move()");
    }


    // az első nem üres kocsival tér vissza, ha null, mindegyik kocsi üres
    public Car getFirstNotEmptyCar(int param) {

        System.out.println("        -> [Engine].getFirstNotEmptyCar()");


        if ( firstCar.getFirstNotEmptyCar(param) != null) {
            System.out.println("        <- [Engine].getFirstNotEmptyCar(): Car");
            return new Car();
        }
        else {
            System.out.println("        <- [Engine].getFirstNotEmptyCar(): null");
            return null;
        }

    }


    public Car getFirstCar() {
        return firstCar;
    }

}
