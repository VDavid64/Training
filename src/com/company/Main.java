package com.company;

public class Main  {

    public static void main(String[] args) throws InterruptedException {

        // init:
        // játék állapotát rögzítő bool (true, ha megy a játék)
        boolean GameIsOn = true;
        // szál és game példányosítása
        Thread t = new Thread();
        Game game = new Game();




        // játék fő ciklusa
        while (GameIsOn) {

            // léptetést végrehajtó függvény
            game.nextStep();

            // ütközéseket / játék végét detektáló függvény
            if (game.crashdetection())
                GameIsOn = false;


            // játékos kezelése
            game.userInteraction();


            // várakozás
            t.sleep(100);
        }



        // játék vége rész



    }
}
