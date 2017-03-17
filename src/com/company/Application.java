package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws InterruptedException {


        //////////////////////////////////////////////////////////
        // init:
        Game game = new Game();
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


            //TODO: 1-7
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
            catch ( Exception e) {
                System.out.println("Invalid input");
            }



        }
    }
}
