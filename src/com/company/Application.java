package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main Application class, responsible for running the game
 * and loading the map.
 *
 * @author i_did_iit team
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {


        /**
         * Initializing the Game object.
         */
        Game game = new Game();

        /**
         * Initializing counter for generating trains.
         */
        int counter = 0;

        /**
         * Initializing variable which tracks the map number.
         */
        int mapNumber = 1;

        /**
         * Loading the first map.
         */
        game.loadMap(mapNumber);

        /**
         * Main while cycle which handles the menu.
         */
        while (true) {
            /**
             * Printing out the main menu.
             */
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


            //TODO: 1-7
            int command;
            Scanner input = new Scanner(System.in);
            try {
                /**
                 * Reading input integer.
                 */
                command = input.nextInt();

                /**
                 * Calling correlating method to the integer and printing to the console.
                 */
                switch (command) {
                    case 1:
                    	System.out.println("[Application].Main():");
                    	Switch sw = new Switch();
                    	System.out.println("1.1.    Van a váltón vonat? (I / N)");
                    	String cmd;
                    	Scanner inp = new Scanner(System.in);
                    	cmd = inp.nextLine();
                        if (cmd.equals("I"))
                    		System.out.println("Semmi nem történik!");
                        else if (cmd.equals("N")){
                    	sw.changeDir();
                        }
                        else System.out.println("Invalid input");
                        break;
                    case 2:
                    	System.out.println("[Application].Main():");
                    	Map m = new Map(0);
                        m.controlTunnel(new Tunnel(), 1);
                        break;
                    case 3:
                        System.out.println("[Application].Main():");
                        Map map = new Map(0);
                        map.controlTunnel(new Tunnel(), 3);
                        break;
                    case 4:
                        System.out.println("[Application].Main():");
                        game.generateTrain();
                        break;
                    case 5:
                        System.out.println("[Application].Main():");
                        game.moveTrains();
                        break;
                    case 6:
                        System.out.println("[Application].Main():");
                        game.emptyCars();
                        break;
                    case 7:
                        System.out.println("[Application].Main():");
                        game.crashDetection();
                        break;
                    case 8:
                        System.out.println("[Application].Main():");
                        game.isWon();
                        break;
                    case 9:
                        System.exit(0);
                        break;
                    default:
                        throw new InputMismatchException();
                }
            }
            /**
             * Catching possible exceptions.
             */
            catch (Exception e) {
                System.out.println("Invalid input");
            }



        }
    }
}
