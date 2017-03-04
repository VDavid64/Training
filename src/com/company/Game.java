package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game {

    //////////////////////
    private Map map;
    private boolean isLastGame;
    // vonatok tárolása
    private ArrayList<Engine> engines = new ArrayList<>();
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
    // TO-DO: Kérdés, hogy car.getActPos tunnel-t is visszaad-e
    // utasok leszállítását végrehajtó függvény
    public void emptyCars() {
        for (Engine e: engines
                ) {
            Car car = e.getFirstNotEmptyCar();

            // az első nem üres kocsi
            if (car != null ) {

                // lekérjük, hogy actpos benne van-e az állomások között
                int index = map.getStations().indexOf(car.getActPos());
                // ha nincs, kilépünk
                if (index == -1) {
                    return;
                }
                // amúgy meg ha szín is passzol, kiürítjük a kocsit
                else {
                    Station station = map.getStations().get(index);
                    if ( station.getColor() == car.getColor())
                        car.setEmpty();
                }
            }

            // ha van ilyen kocsi, és pont station felett van és a színük is megegyezik
            if (car != null && car.getActPos().getType()== 4 /* && car.getColor() == car.getActPos().getColor() */ ) {
                car.setEmpty();
            }
        }
    }

}
