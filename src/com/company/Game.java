package com.company;

import java.util.*;

public class Game {

    //////////////////////
    private Map map;
    private boolean isLastGame;
    private ArrayList<Engine> engines = new ArrayList<>();              // vonatok tárolása
    //////////////////////




    // Függvények:
    ///////////////////////////
    // default constructor
    public Game() {
        isLastGame = false;
        engines.add(new Engine(new Rail(), 2));
    }


    ////////////////
    // nyilván kell tartani, hogy az utolsó pályát játszuk e
    public boolean getIsLastGame() {
        return isLastGame;
    }


    ////////////////
    // Map konstruktorát hívja meg, beállítva a ref-t
    // az pedig fájlból vagy bedrótozva betölt egy pályát
    public void loadMap(int mapNumber) {
        map = new Map(mapNumber);
    }


    ////////////////
    // Vonat generálása:
    //          Lekérdezzük a lehetséges kezdőpozíciót, majd azt beállítjuk az új engine-nek
    //          Második paraméterben a kocsik számát adjuk meg
    public void generateTrain() {
        Engine newEngine = new Engine(map.getStartPositions().get(0), 5);
        engines.add(newEngine);
    }

    public void setIsLastGame(boolean lastGame) {
        isLastGame = lastGame;
    }

    ////////////////
    // vonatok léptetése
    public void moveTrains() {
        for (Engine e: engines
             ) {
            e.move();
        }
    }


    /////////////////
    // vonatok üressgégének vizsgálata
    // ha minden engine minden kocsija üres, true-val térünk vissza
    public void isWon() {

        System.out.println("    -> [Game].isWon()");

        System.out.println("8.1: Utolsó pályán vagyunk? ");

        String command;
        Scanner input = new Scanner(System.in);
        command = input.nextLine();

        if (command.equals("I"))
            engines.get(0).getFirstNotEmptyCar();
        else if (command.equals("N"))
            System.out.println("    <-[Game].isWon()");

        System.out.println("    <- [Game].isWon()");
    }


    /////////////
    // vonatok törlése új pálya betöltésénél
    public void deleteTrains() {
        engines.clear();
    }


    /////////////
    // ütközéseket / játék végét detektáló függvény
    // True-val tér vissza, ha a játéknak vége
    public boolean crashDetection() {
        ArrayList<Rail> result1 = new ArrayList<>();
        for (Engine e: engines
             ) {
            result1.add(e.getActPos());
            Car c = e.getFirstCar();
            while (c != null) {
                result1.add(c.getActPos());
                c = c.getNextCar();
            }
        }

        // Ha van duplikátum, halmazban az kiszűrődik és kisebb lesz a mérete
        Set<Rail> result2 = new HashSet<>(result1);
        if (result2.size() < result1.size()){
            return true;
        }
        return false;

    }


    ///////////////
    // utasok leszállítását végrehajtó függvény
    public void emptyCars() {
        for (Engine e: engines
                ) {
            //Car car = e.getFirstNotEmptyCar();
/*
            // az első nem üres kocsi és állomáson vagyunk
            if (car != null ) {

                // ha egyezik a szín, kiürítjük a kocsit
                if ( ((Station) car.getActPos()).getColor() == car.getColor())
                    car.setEmpty();
            }
            */

        }
    }

}
