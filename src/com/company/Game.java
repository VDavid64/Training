package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    public boolean isWon() {
        boolean allIsEmpty = true;
        while (allIsEmpty) {
            for (Engine e: engines
                    ) {
                if (e.getFirstNotEmptyCar() != null) {
                    return false;
                }
            }
            allIsEmpty = true;
        }
        return allIsEmpty;
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
    //Set.Add() hamissal tér vissza, ha van már olyan elem a halmazban
        Set<Rail> positions = new HashSet<>();
        for(int i = 0; i < engines.length() && bool notDuplicate; i++)
        {   
            e = engines.get(i);
            notDuplicate = positions.add(e.getActPos());
            Car c = e.getFirstCar();
            while (c != null && notDuplicate) {
                notDuplicate = positions.add(c.getActPos());
                c = c.getNextCar();
            }
        }
        return !notDuplicate;
    }


    ///////////////
    // utasok leszállítását végrehajtó függvény
    public void emptyCars() {
        for (Engine e: engines
                ) {
            Car car = e.getFirstNotEmptyCar();

            // az első nem üres kocsi és állomáson vagyunk
            if (car != null ) {

                // ha egyezik a szín, kiürítjük a kocsit
                if ( ((Station) car.getActPos()).getColor() == car.getColor())
                    car.setEmpty();
            }

        }
    }

}
