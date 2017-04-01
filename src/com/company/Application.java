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
        //int mapNumber = 1;                  // hányadik pályán járunk
        //game.loadMap("testmap");            // első pálya betöltése - paraméterben az xml fájl neve
        //////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////
        // Üdvözlő szöveg:
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("Start of the Program.");
        System.out.println("Use \"LoadMap map_name\" to load a map from XML file");
        System.out.println("Then use command {Step, SetRandom, SetTunnel, SetSwitch, TunnelState, StationState, ListEngine, ListTrains}, ");
        System.out.println("to manipulate the program.");
        System.out.println("-------------------------------");

        while (true) {

            try {

                //////////////////////////////////////////////////////////
                // Parancs beolvasása:
                Scanner scanInput = new Scanner(System.in);
                String inputString = scanInput.nextLine();
                String[] inputArray = inputString.split(" ");
                if (inputArray[0].equals("") || inputArray.length > 2) {
                    System.out.println("Format must be: \"command param(opt)\" ");
                    continue; }
                String command = inputArray[0].toUpperCase();


                switch (command) {

                    case ("LOADMAP"):
                        try {
                            String param = inputArray[1];
                            game.loadMap(param);
                        }
                        catch (Exception e) {
                            System.out.println("Useage of LoadMap command: \"LoadMap map_name\" "); }
                        break;


                    case ("STEP"):
                        try {
                            String param = inputArray[1];
                            int round = Integer.parseInt(param);
                            if (round < 1 || round > 500) throw new IllegalArgumentException();

                            while (round != 0 && gameIsOn) {
                                System.out.println("<round: " + counter + ">");
                                game.moveTrains();
                                game.generateTrain(counter);
                                game.emptyCars();

                                if (game.crashDetection()) gameIsOn = false;
                                if (game.isWon() && (counter >= 5)) {
                                    if (game.getIsLastGame())
                                        gameIsOn = false;
                                }

                                counter++;
                                round--;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Useage of Step command: \"Step round_number\". Rund must be a number between 1 and 500");
                        }

                        // ha a játék véget ért, resetelünk, hogy újra tudjunk indítani egy pályát, programból való kilépés nélkül
                        if (gameIsOn == false) {
                            System.out.println("Game over");
                            System.out.println("-------------------------------");
                            System.out.println();
                            game.deleteTrains();
                            counter = 0;
                            gameIsOn = true;
                        }

                        break;


                    case ("EXIT"):
                        System.exit(0);



                    default:
                        System.out.println("Invalid command");
                        break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        /*

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
                    //game.loadMap(mapNumber++);
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
    */
    }
}
