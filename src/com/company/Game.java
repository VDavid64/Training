package com.company;

import java.util.List;

public class Game {

    private int counter;
    private Map palya;

    // vonatok tárolása
    private List<Train> trains;



    // palya tagváltózó feltöltése
    public void loadMap() {
        // fájlból betöltés
    }


    // default constructor
    public Game() {
        counter = 0;
        loadMap();
    }



    // vonatok léptetése
    public void nextStep() {
        // pl a vonatok kezdőpontját lekérjük, végignézzük a szomszédokat
        // ha megtaláltuk a következőt, beállítjuk a vonatnak

        // itt hívódhatna meg a generateTrain is a megfelelő időben, pl:
        if (counter == 0 || counter == 10)
            generateTrain();

        counter++;
    }


    

    // ütközéseket / játék végét detektáló függvény
    // True-val tér vissza, ha a játéknak vége
    public boolean crashDetection() {
        return true;
    }



    // csinálhatnánk úgy, hogy két vonat lesz egyelőre csak
    // az elsőt rögtön a játék elején indítjuk
    // a másodikat kicsivel utána
    public void generateTrain() {

        // logika, mikor indítsunk


        // lekérdezzük a lehetséges pontokat, ahol vonat jöhet be
        Tile[] lehetségeskezdőpontok = palya.getStartpositions();
        Rail statpos = new Rail(); // bullshit, csak kényelem miatt kell
        // választunk egyet közülük, ...

        // új vonat:
        Train vonatx = new Train(statpos);

        // hozzáadás
        trains.add(vonatx);

    }



}
