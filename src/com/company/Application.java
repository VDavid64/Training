package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    /**
     *   Defines from where we get the commands
     *   false, if from console, true if from text
     */
    public static boolean inputMethod = false;
    public static BufferedWriter output = null;

    public static void main(String[] args) throws InterruptedException {


        // TODO: maps + tests
        // TODO: random működése, pl: train generálásnál csak Car-ok példányosodnak
        // TODO: xml-be a station-ok színét is le kell kötni
        // TODO: kiírások lekezelése: console vagy file

        //////////////////////////////////////////////////////////
        // init:
        Game game = new Game();                     // game példányosítása
        boolean gameIsOn = true;                    // játék állapotát rögzítő bool (true, ha megy a játék)
        boolean gameHasWon = false;
        boolean mapLoaded = false;
        int counter = 1;                            // számláló (vonat generálásához) és a map
        boolean random = false;                     // véletlenszerűséget állító kapcsoló
        final String inputFileName;                 // ha parancssori indítás esetén a parancsokat tartalmazó txt file
        final String outputFileName;                // kimenetet tartalmazó file
        List<String> commands = new ArrayList<>();  // parancsokat tartalmazó lista
        //////////////////////////////////////////////////////////


        // Control input/output method
        if (args.length != 0) {                                 // if args is not empty, we check the two parameters if valid
            try {
                inputMethod = true;
                inputFileName = args[0];                                    // Get the filenames from args
                outputFileName = args[1];                                   // if the program is called with less than two parameters we get error
                String[] partsInput = inputFileName.split("\\.");           // Checking filetype
                String[] partsOutput = outputFileName.split("\\.");
                if ( !partsInput[1].equals("txt") || !partsOutput[1].equals("txt"))
                    throw new IllegalArgumentException();

                // Load commands from file into a String array
                String path = System.getProperty("user.dir");
                commands = Files.readAllLines(Paths.get( path + "\\test\\" + inputFileName), StandardCharsets.UTF_8);

                // Create output file
                File file = new File(path + "\\test\\" + outputFileName);
                output = new BufferedWriter(new FileWriter(file));          // write to output: "output.write(string)"
            }
            // Error for not valid filetype
            catch (IllegalArgumentException ie) {
                ie.printStackTrace();
            }
            // Error for not valid number of arguments
            catch (ArrayIndexOutOfBoundsException ae) {
                ae.printStackTrace();
            }
            // Error for file actions
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {                                                  // if args is empty, the program will read the commands from the console
            inputMethod=false;
        }


        if (inputMethod == false) {
            //////////////////////////////////////////////////////////
            // Welcoming text for console:
            System.out.println();
            System.out.println("-------------------------------");
            System.out.println("Start of the Program.");
            System.out.println("First, use \"LoadMap map_name\" to load a map from XML file");
            System.out.println("Then use command {Step, SetRandom, SetTunnel, SetSwitch, TunnelState, StationState, ListEngine, ListTrains},");
            System.out.println("to manipulate the program.");
            System.out.println("See command \"Help\" to list the valid commands and their required parameters.");
            System.out.println("-------------------------------");
        }


        // variables for processing the commands
        String command;
        String[] inputArray;
        int command_counter=0;

        // main cycle
        while (true) {

            // Processing the commands
            try {
                if (inputMethod == false) {

                    // Read command from console
                    Scanner scanInput = new Scanner(System.in);
                    String inputString = scanInput.nextLine();
                    inputArray = inputString.split(" ");
                    if (inputArray[0].equals("") || inputArray.length > 2) {
                        System.out.println("Format must be: \"command param(opt)\". See \"Help\" for list of commands.");
                        continue;
                    }
                    command = inputArray[0].toUpperCase();
                }

                else {

                    // Read command from text (commands list)
                    command = commands.get(command_counter);
                    inputArray = command.split(" ");
                    if (inputArray[0].equals("") || inputArray.length > 2) {
                        System.out.println("Format must be: \"command param(opt)\". See \"Help\" for list of commands.");
                        continue;
                    }
                    command = inputArray[0].toUpperCase();
                    command_counter++;
                }



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
                                if (Application.inputMethod) {
                                    Application.output.write("-------------------------------\n");
                                    Application.output.write("<round: " + counter + ">\n");
                                }
                                else {
                                    System.out.println("-------------------------------");
                                    System.out.println("<round: " + counter + ">");
                                }
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
                            if (Application.inputMethod) {
                                if (gameHasWon) {
                                    Application.output.write("<Congratulations! You Won!>\n");
                                    Application.output.write("-------------------------------");
                                } else {
                                    Application.output.write("<Game over!>\n");
                                    Application.output.write("-------------------------------\n");
                                }
                            }
                            else {
                                if (gameHasWon) {
                                    System.out.println("<Congratulations! You Won!>");
                                    System.out.println("-------------------------------");
                                } else {
                                    System.out.println("<Game over!>");
                                    System.out.println("-------------------------------");
                                }
                            }
                            mapLoaded = false;
                            game.deleteTrains();
                            counter = 1;
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

                        if (Application.inputMethod) {
                            Application.output.write("<Exit>");
                            output.close();
                        }
                        else {
                            System.out.println("<Exit>");
                        }

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
                            e.printStackTrace();
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
                            if (random) {
                                random = false;
                                if (Application.inputMethod) {
                                    Application.output.write("<Random off>");
                                    output.close();
                                }
                                else {
                                    System.out.println("<Random off>");
                                }
                            }
                            else {
                                random= true;
                                if (Application.inputMethod) {
                                    Application.output.write("<Random on>");
                                    output.close();
                                }
                                else {
                                    System.out.println("<Random on>");
                                }
                            }
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
