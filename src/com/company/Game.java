package com.company;

import java.util.*;

public class Game {

    //////////////////////
    private Map map;
    private boolean isLastGame;
    private ArrayList<Engine> engines = new ArrayList<>();              // vonatok tárolása
    //////////////////////




    // Függvények:
    ///////////////////////////
    // default constructor
    public Game() {
        isLastGame = false;
        engines.add(new Engine(new Rail(), 2));
    }


    ////////////////
    // nyilván kell tartani, hogy az utolsó pályát játszuk e
    public boolean getIsLastGame() {
        return isLastGame;
    }


    ////////////////
    // Map konstruktorát hívja meg, beállítva a ref-t
    // az pedig fájlból vagy bedrótozva betölt egy pályát
    public void loadMap(int mapNumber) {
        map = new Map(mapNumber);
    }


    ////////////////
    // Vonat generálása:
    //          Lekérdezzük a lehetséges kezdőpozíciót, majd azt beállítjuk az új engine-nek
    //          Második paraméterben a kocsik számát adjuk meg
    public void generateTrain() {
        Engine newEngine = new Engine(map.getStartPositions().get(0), 5);
        engines.add(newEngine);
    }

    public void setIsLastGame(boolean lastGame) {
        isLastGame = lastGame;
    }

    ////////////////
    // vonatok léptetése
    public void moveTrains() {
        for (Engine e: engines
             ) {
            e.move();
        }
    }


    ///////////////// 8 - Done
    // vonatok üressgégének vizsgálata
    // ha minden engine minden kocsija üres, true-val térünk vissza
    public void isWon() {

        System.out.println("    -> [Game].isWon()");

        System.out.println("8.1: Utolsó pályán vagyunk? ");

        String command;
        Scanner input = new Scanner(System.in);
        command = input.nextLine();

        if (command.equals("I"))
            if (engines.get(0).getFirstNotEmptyCar(8) != null) {
                System.out.println("    <-[Game].isWon(): false");
            }
            else {
                System.out.println("    <-[Game].isWon(): true");
            }

        else if (!command.equals("N")) {
            throw new IllegalArgumentException();
        }
        else
            System.out.println("    <-[Game].isWon(): false");

    }


    /////////////
    // vonatok törlése új pálya betöltésénél
    public void deleteTrains() {
        engines.clear();
    }


    ///////////// 7 - Done
    // ütközéseket / játék végét detektáló függvény
    // True-val tér vissza, ha a játéknak vége
    public boolean crashDetection() {

        System.out.println("    -> [Game].crashDetection()");

        // kisiklás
        if (Map.getIsDerailing()) {
            System.out.println("    <- [Game].crashDetection(true)");
            return true;
        }


        // ütközés:
        engines.get(0).getActPos();
        System.out.println("7.2: Két vonat összeütközött? ");
        String command;
        Scanner input = new Scanner(System.in);
        command = input.nextLine();

        if (command.equals("I")) {
            System.out.println("    <-[Game].crashDetection(true)");
            return true;
        }
        else if (!command.equals("N")) {
            throw new IllegalArgumentException();
        }

        // kezdőpozícióra léptünk
        else {
            map.getStartPositions();
            System.out.println("7.3: A terepasztal szélére jutottunk? ");
            command = input.nextLine();
            if (command.equals("I")) {
                System.out.println("    <-[Game].crashDetection(true)");
                return true;
            }
            else if (!command.equals("N")) {
                throw new IllegalArgumentException();
            }
            else {
                System.out.println("    <-[Game].crashDetection(false)");
                return false;
            }
        }

    }


    /////////////// 6
    // utasok leszállítását végrehajtó függvény
    public void emptyCars() {
        String command;
        Scanner input = new Scanner(System.in);
        System.out.println("    -> [Game].emptyCars()");
        for (Engine e: engines)
        {
            Car car = e.getFirstNotEmptyCar(6);
            System.out.println("6.1: Állomáson van a vonat első nem üres kocsija?");
            command = input.nextLine();
            if (command.equals("I")) {
                Rail pos = car.getActPos();
                car.getColor();
                System.out.println("6.1.1: Azonos színük?");
                String command2 = input.nextLine();
                if (command2.equals("I")) {
                       car.setEmpty();
                    System.out.println("    <-[Game].emptyCars()");
                }
                else if (!command2.equals("N")) {
                    throw new IllegalArgumentException();
                }
                else {
                    System.out.println("    <-[Game].emptyCars()");
                }
            }
            else if (!command.equals("N")) {
                throw new IllegalArgumentException();
            }
            else {
                System.out.println("    <-[Game].emptyCars()");
            }
        }


            //Car car = e.getFirstNotEmptyCar();
/*
            // az első nem üres kocsi és állomáson vagyunk
            if (car != null ) {

                // ha egyezik a szín, kiürítjük a kocsit
                if ( ((Station) car.getActPos()).getColor() == car.getColor())
                    car.setEmpty();
            }
            */


    }

    public static boolean getIsTrainInTunnel(int seq) throws InputMismatchException{
        if (seq == 3) {
            System.out.println("        -> [Game].IsTrainInTunnel()");
            System.out.println("3.3 Üres az alagút?");
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            if (command.equals('I')) {
                System.out.println("        <- [Game].IsTrainInTunnel(): boolean");
                return false;
            }
            else if (command.equals('N')) {
                System.out.println("        <- [Game].IsTrainInTunnel(): boolean");
                return true;
            } else throw new InputMismatchException();
        }
        else throw new RuntimeException("Unreachable line");
    }
}
