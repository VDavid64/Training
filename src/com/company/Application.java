package com.company;

import java.util.Scanner;

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





        while (true) {

            /////// Menü
            System.out.println("\n A szkeleton választható funkciói:");
            System.out.println("-------------------------\n");
            System.out.println("1 - Váltó állítása");
            System.out.println("2 - Alagút építése");
            System.out.println("3 - Alagút törlése");
            System.out.println("4 - Vonat generálása");
            System.out.println("5 - Vonat léptetése");
            System.out.println("6 - Kocsi kiürítése");
            System.out.println("7 - Ütközés");
            System.out.println("8 - Pálya megnyerése");
            System.out.println("9 - Kilépés");


            int command;
            Scanner input = new Scanner(System.in);
            try {
                command = input.nextInt();
                switch (command) {
                    case 1:
                        // Perform "original number" case.
                        break;
                    case 2:
                        // Perform "encrypt number" case.
                        break;
                    case 3:
                        // Perform "decrypt number" case.
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        System.out.println("[Application].Main():");
                        break;
                    case 8:
                        System.out.println("[Application].Main():");
                        game.isWon();
                        break;
                    case 9:
                        System.exit(0);
                        break;
                    default:
                        // The user input an unexpected choice.
                }
            }
            catch (Exception e) {}


        }

    /*

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

*/


    }
}
