package com.company;

public class Application {

    public static void main(String[] args) throws InterruptedException {


        //////////////////////////////////////////////////////////
        // init:
        Thread t = new Thread();            // szál és game példányosítása
        Game game = new Game();
        boolean gameIsOn = true;            // játék állapotát rögzítő bool (true, ha megy a játék)
        int counter = 0;                    // számláló (vonat generálásához) és a map
        int mapNumber = 1;                  // hányadik pályán járunk
        game.loadMap(mapNumber);            // első pálya betöltése
        //////////////////////////////////////////////////////////



        //////////////////////////////////////////////////////////
        // játék fő ciklusa:
        // a példányosított játék függvényeit körönként, egy ciklusban hívjuk meg
        while (gameIsOn) {

            // körszámláló kiírása
            System.out.format("Round: %d \n", counter);


            /////////////
            // léptetést végrehajtó függvény
            game.moveTrains();


            /////////////
            // A megfelelő körökben vonatokat generálunk
            game.generateTrain(counter);




            /////////////
            // Utasok leszállítását végrehajtó függvény
            game.emptyCars();


            /////////////
            // ütközéseket / játék végét detektáló függvény
            // a true-val tér vissza, ha a játék valamilyen oknál fogva véget ér
            if (game.crashDetection())
                gameIsOn = false;


            /////////////
            // Minden kör végén a játék megnyerését vizsgáló függvény
            // Ha minden vonat minden kocsija üres, átugrunk a következő pályára
            // Hogy az elején ne nyerjük meg rögtön, körszámlálót is ellenőrizzük
            if (game.isWon() && (counter >= 5)) {

                // Ha az utolsó pályát nyertük meg, vége a játéknak
                if ( game.getIsLastGame())
                    gameIsOn = false;

                // Amúgy csak a köv pályát töltjük be
                else {
                    game.deleteTrains();
                    game.loadMap(mapNumber++);
                    game.setIsLastGame(true);
                    counter = 0;
                }
            }



            /////////////
            // várakozás és a körszámláló növelése
            t.sleep(500);
            counter++;

        }
        //////////////////////////////////////////////////////////


        System.out.println("Game over");

    }
}
