package com.company;

import java.util.ArrayList;

public class Game {

    //////////////////////
    private Map map;
    private boolean isLastGame;
    // vonatok tárolása
    private ArrayList<Engine> engines = new ArrayList<>();
    //////////////////////



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
    public void generateTrain() {
        Engine newEngine = new Engine(map.getStartPositions().get(0), 10);
        engines.add(newEngine);
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
        boolean empty = true;
        while (empty) {
            for (Engine e: engines
                    ) {
                if (e.getFirstNotEmptyCar() != null) {
                    return false;
                }
            }
            empty = true;
        }
        return empty;
    }






    // TO-DO: ha car-nak lekérdezzük egy pozícióját, az egy railt ad vissza, és hiába tunnel, getColor-t nem tudjuk meghívni rajta
    // utasok leszállítását végrehajtó függvény
    public void emptyCars() {
        for (Engine e: engines
                ) {
            Car car = e.getFirstNotEmptyCar();

            // ha van ilyen kocsi, és pont station felett van és a színük is megegyezik
            if (car != null && car.getActPos().getType()== 4 /* && car.getColor() == car.getActPos().getColor() */ ) {
                car.setEmpty();
            }
        }
    }



    // TO-DO
    // ütközéseket / játék végét detektáló függvény
    // True-val tér vissza, ha a játéknak vége
    public boolean crashDetection() {
        return false;
    }






}
