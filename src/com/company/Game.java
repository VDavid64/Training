package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {

    // számláló (vonat generálásához) és a map
    private int counter;
    private Map palya;

    // vonatok tárolása
    private ArrayList<Train> trains = new ArrayList<>();



    // default constructor
    public Game() {
        counter = 0;
    }




    // Map konstruktorát hívja meg
    // ez fájlból vagy bedrótozva betölt egy pályát
    public void loadMap(int mapNumber) {
        palya = new Map(mapNumber);
    }




    // vonatok léptetése
    public void nextStep() {
        // pl a vonatok kezdőpontját lekérjük, végignézzük a szomszédokat
        // ha megtaláltuk a következőt, beállítjuk a vonatnak

        // itt hívódhatna meg a generateTrain is a megfelelő időben, pl:
        if (counter == 0 || counter == 11)
            generateTrain();

        // leszállást végrehajtó függvény
        emptyCars();

        // for ciklusban vonatokon végiggyaloglunk, a következő
        // rail-t megkeresve, majd beállítjuk az engine actPos-nak

        counter++;
    }




    // ütközéseket / játék végét detektáló függvény
    // True-val tér vissza, ha a játéknak vége
    public boolean crashDetection() {
        return false;
    }



    // csinálhatnánk úgy, hogy két vonat lesz egyelőre csak
    // az elsőt rögtön a játék elején indítjuk
    // a másodikat kicsivel utána
    public void generateTrain() {

        // lekérdezzük a lehetséges pontokat, ahol vonat jöhet be
        ArrayList<Tile> startPositions = palya.getStartPositions();
        Tile startTile = startPositions.get(counter % 2);               // változó ponton generál vonatot
        Rail startRail = startTile.getRail();
        Train nextTrain = new Train(startRail);
        setNextTrain(nextTrain);                                        // majd hozzáadjuk a trains listánkhoz

    }

    public void setNextTrain(Train t) {
        trains.add(t);
    }


    // utasok leszállítását végrehajtó függvény
    public void emptyCars() {

    }

    // vonatok üressgégének vizsgálata
    public boolean isWon() {
        return false;
    }

}
