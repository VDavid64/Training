package com.company;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws InterruptedException {


        // TODO: tunnel, random, engine from xml, felszállás és leszállás egyszerre, maps

        //////////////////////////////////////////////////////////
        // init:
        Thread t = new Thread();            // szál és game példányosítása
        Game game = new Game();
        boolean gameIsOn = true;            // játék állapotát rögzítő bool (true, ha megy a játék)
        boolean gameHasWon = false;
        boolean mapLoaded = false;
        int counter = 1;                    // számláló (vonat generálásához) és a map
        //////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////
        // Üdvözlő szöveg:
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("Start of the Program.");
        System.out.println("First, use \"LoadMap map_name\" to load a map from XML file");
        System.out.println("Then use command {Step, SetRandom, SetTunnel, SetSwitch, TunnelState, StationState, ListEngine, ListTrains},");
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
                    System.out.println("Format must be: \"command param(opt)\". See \"Help\" for list of commands.");
                    continue; }
                String command = inputArray[0].toUpperCase();


                switch (command) {

                    case ("LOADMAP"):
                        try {
                            String param = inputArray[1];
                            game.loadMap(param);
                            mapLoaded = true;
                        }
                        catch (Exception e) {
                            System.out.println("Useage of LoadMap command: \"LoadMap map_name\" "); }
                        break;



                    case ("STEP"):
                        try {
                            String param = inputArray[1];
                            int round = Integer.parseInt(param);
                            if (round < 1 || round > 500) throw new IllegalArgumentException();
                            if (!mapLoaded) throw new IllegalStateException();

                            while (round != 0 && gameIsOn) {
                                System.out.println("-------------------------------");
                                System.out.println("<round: " + counter + ">");
                                game.moveTrains();
                                game.generateTrain(counter);
                                game.emptyCars();

                                if (game.crashDetection()) {
                                    gameIsOn = false;
                                }
                                if (game.isWon() && (counter >= 5)) {
                                    gameIsOn = false;
                                    gameHasWon = true;
                                }

                                counter++;
                                round--;
                            }
                        }
                        catch (IllegalStateException i) {
                            i.printStackTrace();
                            System.out.println("You need to load a map first. Use \"LoadMap\" command!");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Useage of Step command: \"Step round_number\". Rund must be a number between 1 and 500");
                        }

                        // ha a játék véget ért: resetelünk, hogy újra tudjunk indítani egy pályát, programból való kilépés nélkül
                        if (!gameIsOn) {
                            if (gameHasWon) {
                                System.out.println("Congratulations! You Won!");
                                System.out.println("-------------------------------");
                            }
                            else {
                                System.out.println("Game over");
                                System.out.println("-------------------------------");
                            }
                            mapLoaded = false;
                            game.deleteTrains();
                            counter = 0;
                            gameIsOn = true;
                            gameHasWon = false;
                        }

                        break;



                    case ("SETSWITCH"):
                        try {
                            String param = inputArray[1];
                            if (!mapLoaded) throw new IllegalStateException();
                            game.onClicked(param);
                        }
                        catch (IllegalArgumentException i) {
                            System.out.println("There is no switch by that name");
                        }
                        catch (IllegalStateException i) {
                            i.printStackTrace();
                            System.out.println("You need to load a map first. Use \"LoadMap\" command!");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Useage of SetSwitch command: \"SetSwitch switch_name\" "); }
                        break;

                    case ("EXIT"):
                        System.exit(0);



                    case ("STATIONSTATE"):
                        try {
                            String param = inputArray[1];
                            if (!mapLoaded) throw new IllegalStateException();
                            game.printStationData(param);
                        }
                        catch (IllegalArgumentException i) {
                            System.out.println("There is no station by that name");
                        }
                        catch (IllegalStateException i) {
                            i.printStackTrace();
                            System.out.println("You need to load a map first. Use \"LoadMap\" command!");
                        }
                        catch (Exception e) {
                            System.out.println("Useage of StationState command: \"StationState station_name\" "); }
                        break;



                    case ("LISTENGINE"):
                        try {
                            String param = inputArray[1];
                            if (!mapLoaded) throw new IllegalStateException();
                            game.listEngine(param);
                        }
                        catch (IllegalArgumentException i) {
                            System.out.println("There is no engine by that name");
                        }
                        catch (IllegalStateException i) {
                            i.printStackTrace();
                            System.out.println("You need to load a map first. Use \"LoadMap\" command!");
                        }
                        catch (Exception e) {
                            System.out.println("Useage of ListEngine command: \"ListEngine engine_name\" "); }
                        break;


                    case ("LISTTRAINS"):
                        try {
                            if (!mapLoaded) throw new IllegalStateException();
                            game.listTrains();
                        }
                        catch (IllegalStateException i) {
                            System.out.println("You need to load a map first. Use \"LoadMap\" command!");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Useage of ListEngine command: \"ListEngine engine_name\" "); }
                        break;



                    case ("HELP"):
                        System.out.println("Available commands: \n");
                        System.out.println("    Loadmap map_name                        Loads map from \\map.*.xml");
                        System.out.println("    Step number_of_steps                    Must be between 1 and 500");
                        System.out.println("    SetSwitch switch_name                   Change the direction of a switch");
                        System.out.println("    StationState station_name               List data of given station");
                        System.out.println("    ListEngine engine_name                  List all information about an engine");
                        break;



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
