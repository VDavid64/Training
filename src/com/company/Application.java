package com.company;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws InterruptedException {


        // TODO: random, engine from xml, maps

        //////////////////////////////////////////////////////////
        // init:
        Thread t = new Thread();            // szál és game példányosítása
        Game game = new Game();
        boolean gameIsOn = true;            // játék állapotát rögzítő bool (true, ha megy a játék)
        boolean gameHasWon = false;
        boolean mapLoaded = false;
        int counter = 1;                    // számláló (vonat generálásához) és a map
        boolean random = false;             // véletlenszerűséget állító kapcsoló
        //////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////
        // Üdvözlő szöveg:
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("Start of the Program.");
        System.out.println("First, use \"LoadMap map_name\" to load a map from XML file");
        System.out.println("Then use command {Step, SetRandom, SetTunnel, SetSwitch, TunnelState, StationState, ListEngine, ListTrains},");
        System.out.println("to manipulate the program.");
        System.out.println("You can use the Help command anytime, to list the valid commands and their required parameters.");
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
                            System.out.println("Usage of LoadMap command: \"LoadMap map_name\" "); }
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
                                game.moveTrains(counter);
                                game.generateTrain(counter, random);
                                game.emptyCars(counter);

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
                            System.out.println("Usage of Step command: \"Step round_number\". Round must be a number between 1 and 500");
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
                    case ("SETTUNNEL"):
                        try {
                            String param = inputArray[1];
                            if (!mapLoaded) throw new IllegalStateException();
                            game.onClicked(param);
                        }
                        catch (IllegalArgumentException i) {
                            System.out.println("There is no switch/tunnel by that name");
                        }
                        catch (IllegalStateException i) {
                            i.printStackTrace();
                            System.out.println("You need to load a map first. Use \"LoadMap\" command!");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Usage of SetSwitch/SetTunnel command: \"SetSwitch switch_name\" or \"SetTunnel tunnel_name\""); }
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
                            System.out.println("Usage of StationState command: \"StationState station_name\" "); }
                        break;


                    case ("TUNNELSTATE"):
                        try {
                            if (!mapLoaded) throw new IllegalStateException();
                            game.printTunnelState();
                        }
                        catch (IllegalStateException i) {
                            i.printStackTrace();
                            System.out.println("You need to load a map first. Use \"LoadMap\" command!");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Usage of TunnelState command: \"TunnelState\" "); }
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
                            System.out.println("Usage of ListEngine command: \"ListEngine engine_name\" "); }
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
                            System.out.println("Usage of ListEngine command: \"ListEngine engine_name\" "); }
                        break;


                    case ("SETRANDOM"):
                        try {
                            if (mapLoaded) throw new IllegalStateException();
                            if (random) random=false;
                            else random= true;
                        }
                        catch (IllegalStateException i) {
                            System.out.println("You can not set random after loading the map.");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Usage of SetRandom command: \"SetRandom\" "); }
                        break;


                    case ("HELP"):
                        System.out.println("Available commands: \n");
                        System.out.println("    LoadMap map_name                        Loads map from \\map.*.xml");
                        System.out.println("    Step number_of_steps                    Must be between 1 and 500");
                        System.out.println("    SetSwitch switch_name                   Change the direction of a switch");
                        System.out.println("    StationState station_name               List data of given station");
                        System.out.println("    ListEngine engine_name                  List all information about an engine");
                        System.out.println("    TunnelState                             Print the state of the tunnel and active tunnel positions");
                        System.out.println("    SetTunnel tunnel_name                   Set tunnel position activity");
                        System.out.println("    SetRandom                               Set the random generations of cars, stations, etc. Editable before loading the map!");
                        System.out.println("-------------------------------");
                        break;



                    default:
                        System.out.println("Invalid command. Format must be: \"command param(opt)\". See \"Help\" for list of commands.");
                        break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
