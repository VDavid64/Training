package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game {


    //////////////////////
    private Map map;
    private boolean isLastGame;
    private final int maxTrainNumber = 3;
    private ArrayList<Engine> engines = new ArrayList<>();              // vonatok tárolása
    //////////////////////



    // Függvények:
    ///////////////////////////
    // default constructor
    public Game() {
        isLastGame = true;
    }


    ////////////////
    // nyílván kell tartani, hogy az utolsó pályát játszuk e
    public boolean getIsLastGame() {
        return isLastGame;
    }

    public void setIsLastGame(boolean lastGame) {
        isLastGame = lastGame;
    }


    ////////////////    Done
    // Map konstruktorát hívja meg, beállítva a ref-t
    // az pedig fájlból vagy bedrótozva betölt egy pályát
    public void loadMap(String mapName) {
        map = new Map();
        map.loadMap(mapName);
    }


    public void onClicked(String name) {
        map.onMouseClickedEvent(name);
    }


    ////////////////
    // Vonat generálása:
    //          Lekérdezzük a lehetséges kezdőpozíciót, majd azt beállítjuk az új engine-nek
    //          Második paraméterben a kocsik számát adjuk meg - random between 2 and 5
    //          Generáláskor ellenőrizni kell, üres-e a startPos
    public void generateTrain(int round) {


        // Törölhető, ha úgy döntünk felesleges (pálya széléről lehajtanánk amúgy)
        // Többi generáláskor már ellenőrizni kell, üres-e a startRail
        Set<Rail> positions = new HashSet<>();
        boolean empty = true;
        for (Engine e : engines) {
            if (e.getActPos() == map.getStartPosition())
                empty = false;
        }


        // első körben generálunk
        if ((round == 1 || round == 10) && empty) {
            int numberOfCars = (int) (Math.random() * (6 - 2)) + 2;
            System.out.format("New engine with %d cars: ", numberOfCars);
            Engine newEngine = new Engine(map.getStartPosition(), numberOfCars, "engine_" + round);
            engines.add(newEngine);
            return;
        }
    }


    ////////////////    Done
    // Vonatok léptetése
    public void moveTrains(int counter) {
        for (Engine e: engines) {
            e.moveEngine(counter);
        }
    }


    /////////////////   Done
    // Nyerés ellenőrzése - true, ha megnyertük a játékot
    // Megnyerés feltétele: minden kocsi és állomás üres
    public boolean isWon() {

        /*
        // ha nem az utolsó pálya
        if (!getIsLastGame())
            return false;
        */

        for (Engine e: engines) {
            if (e.getFirstNotEmptyCar() != null) {
                return false;
            }
        }

        for (Station s: map.getStations()
                ) {
            if (s.getPassenger() != 0)
                return false;
        }

        return true;
    }


    /////////////       Done
    // vonatok törlése új pálya betöltésénél
    public void deleteTrains() {
        engines.clear();
    }


    /////////////       Done
    // Játék végének ellenőrzése: true, ha volt ütközés, kisiklás, terepasztal szélére hajtás
    // ütközéseket / játék végét detektáló függvény
    // A vonatok pozícióját egy halmazba gyűjti. Ha volt duplikátum, tudjuk, hogy ütközés volt
    public boolean crashDetection() {

        ///// kisiklás
        if (Map.getIsDerailing()) {
            System.out.println("Kisiklás történt!");
            return true;
        }


        // terepasztal szélére ért egy engine -> enginnek null az actpos-ja
        for (Engine e: engines) {
            if (e.getActPos() == null) {
                System.out.println("Lehajtottunk a pályáról!");
                return true;
            }
        }


        ///// ütközés
        Set<Rail> train_pos = new HashSet<>();
        boolean isNotDuplicateRail = true;
        for (Engine e: engines) {
            isNotDuplicateRail = train_pos.add(e.getActPos());
            Train_Element c = e.getNextTrainElement();
            while ( c != null && c.getActPos() != null && isNotDuplicateRail) {
                isNotDuplicateRail = train_pos.add(c.getActPos());
                c = c.getNextTrainElement();
            }
        }
        if (!isNotDuplicateRail) System.out.println("Ütközés történt!");
        return !isNotDuplicateRail;
    }



    /////////////// Done
    // Kocsik kiürítése
    // utasok leszállítását végrehajtó függvény
    public void emptyCars(int counter) {
        for (Engine e: engines) {
            Car car = e.getFirstNotEmptyCar();
            if (car != null) {                                                              // az első nem üres kocsi és állomáson vagyunk
                if ((car.getActPos() != null)) {
                    if ((car.getActPos().getColor() != null)) {
                        if (car.roundLastEmpty != counter) {                                // Kizárjuk az egy körben történő felszállást majd leszállást
                            if ((car.getActPos()).getColor() == car.getColor()) {           // ha egyezik a szín, kiürítjük a kocsit
                                car.setEmpty(true);
                                System.out.println("    <Leszállás történt: " + car.getActPos().name + ", " + car.name + " >");
                            }
                        }
                    }
                }
            }
        }
    }

    public void printStationData(String param) {

        if (param.contains("station")) {
            for (Station s: map.getStations()
                ) {
                if (s.name.equals(param)) {
                    System.out.println("<" + s.name + " " + s.getColor() + " " + s.getPassenger() + ">");
                    return;
                }
            }
        }

        throw new IllegalArgumentException();
    }

    public void printTunnelState() {
        map.printTunnelState();
    }

    public void listEngine(String param) {
        for (Engine e : engines
             ) {
            if (e.name.equals(param)) {
                System.out.println("<Name: " + e.name + "><Position: " + e.actPos.name + " >");
                Train_Element te = e.nextTrainElement;
                while (te != null) {
                    System.out.println("<Name: " + te.name + " ><Position: " + te.actPos.name + " ><Color: " + te.getColor() + ">< empty: " +te.isEmpty() + " >");
                    te = te.getNextTrainElement();
                }
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public void listTrains() {
        if (engines.isEmpty()) {
            System.out.println("<There is no train to list.>");
            return;
        }

        for (int i = 0; i < engines.size(); i++) {
            Engine e = engines.get(i);
            System.out.println("Number " + i + " train:");
            System.out.println("    <Name: " + e.name + "><Position: " + e.actPos.name + " >");
            if (e.getNextTrainElement() != null) {
                Train_Element te = e.getNextTrainElement();
                while (te != null) {
                    if (te.getActPos() == null) {
                        break;
                    }
                    System.out.println("        <Name: " + te.name + " ><Position: " + te.actPos.name + " ><Color: " + te.getColor() + ">< empty: " +te.isEmpty() + " >");
                    te = te.getNextTrainElement();
                }
            }
        }
    }
}
