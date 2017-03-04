package com.company;

public class Application {

    public static void main(String[] args) throws InterruptedException {


        //////////////////////////////////////////////////////////
        // init:
        // szál és game példányosítása
        Thread t = new Thread();
        Game game = new Game();
        // első pálya betöltése
        game.loadMap(1);
        // játék állapotát rögzítő bool (true, ha megy a játék)
        boolean gameIsOn = true;
        // számláló (vonat generálásához) és a map
        int counter = 0;
        //////////////////////////////////////////////////////////



        //////////////////////////////////////////////////////////
        // játék fő ciklusa:
        // a példányosított játék függvényeit körönként, egy ciklusban hívjuk meg
        while (gameIsOn) {

            /////////////
            // A megfelelő körökben vonatokat generálunk
            if (counter == 0 || counter == 11)
                game.generateTrain();


            /////////////
            // léptetést végrehajtó függvény
            game.moveTrains();

            game.emptyCars();

            /////////////
            // ütközéseket / játék végét detektáló függvény
            // a true-val tér vissza, ha a játék valamilyen oknál fogva véget ér
            if (game.crashDetection())
                gameIsOn = false;


            /////////////
            // Minden kör végén a játék megnyerését vizsgáló függvény
            // Ha minden vonat minden kocsija üres átugrunk a következő pályára
            // Hogy az elején ne nyerjük meg rögtön, körszámlálót is ellenőrizzük
            if (game.isWon() && counter > 5) {

                // Ha az utolsó pályát nyertük meg, vége a játéknak
                if ( game.getIsLastGame())
                    gameIsOn = false;
                // Amúgy csak a köv pályát töltjük be
                else {
                    game.deleteTrains();
                    game.loadMap(2);
                }
            }


            /////////////
            // várakozás és a körszámláló növelése
            t.sleep(1000);
            counter++;

        }
        //////////////////////////////////////////////////////////




    }
}
