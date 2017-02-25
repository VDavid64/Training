package com.company;

public class Main  {

    public static void main(String[] args) throws InterruptedException {

        // init:
        // szál és game példányosítása
        Thread t = new Thread();
        Game game = new Game();
        game.loadMap(1);            // első pálya betöltése

        // játék állapotát rögzítő bool (true, ha megy a játék)
        boolean GameIsOn = true;


        // játék fő ciklusa
        while (GameIsOn) {

            // léptetést végrehajtó függvény
            game.nextStep();

            // ütközéseket / játék végét detektáló függvény
            // a true-val tér vissza, ha a játék valamilyen oknál fogva véget ér
            if (game.crashDetection())
                GameIsOn = false;

            // Minden kör végén a játék megnyerését vizsgáló függvény.
            // Ha minden vonat minden kocsija üres átugrunk a következő pályára
            if (game.isWon()) {
                game.loadMap(2);
            }


            // várakozás
            t.sleep(1000);
        }






    }
}
