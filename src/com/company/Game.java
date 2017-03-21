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
        isLastGame = false;
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
    public void loadMap(int mapNumber) {
        map = new Map(mapNumber);
    }




    //////////////// TODO:  mikor
    // Vonat generálása:
    //          Lekérdezzük a lehetséges kezdőpozíciót, majd azt beállítjuk az új engine-nek
    //          Második paraméterben a kocsik számát adjuk meg - random between 2 and 5
    //          Generáláskor ellenőrizni kell, üres-e a startPos
    public void generateTrain(int round) {

        // első körben generálunk
        if (round == 0) {
            Engine newEngine = new Engine(map.getStartPosition(), (int) (Math.random() * (6 - 2)) + 2);
            engines.add(newEngine);
            return;
        }


        // Törölhető, ha úgy döntünk felesleges (pálya széléről lehajtanánk amúgy)
        // Többi generáláskor már ellenőrizni kell, üres-e a startRail
        Set<Rail> positions = new HashSet<>();
        boolean notEmpty = false;
        for (Engine e : engines) {
            notEmpty = positions.add(e.getActPos());
            Car c = e.getFirstCar();
            while ( c != null) {
                notEmpty = positions.add(c.getActPos());
                c = c.getNextCar();
            }
        }

        if (round == 10 && !notEmpty) {
            Engine newEngine = new Engine(map.getStartPosition(), 5);
            engines.add(newEngine);
            return;
        }

        if (round == 20 && !notEmpty) {
            Engine newEngine = new Engine(map.getStartPosition(), 5);
            engines.add(newEngine);
            return;
        }


    }





    ////////////////    Done
    // Vonatok léptetése
    public void moveTrains() {
        for (Engine e: engines) {
            e.move();
        }
    }


    /////////////////   Done
    // Nyerés ellenőrzése - true, ha megnyertük a játékot
    // Megnyerés feltétele: utolsó pályán vagyunk-e és minden kocsi üres
    // Ha minden engine minden kocsija üres -> null a visszatérési érték, megnyertük
    public boolean isWon() {
        boolean allIsEmpty = true;

        // ha nem az utolsó pálya
        if (!getIsLastGame())
            return false;

        // ha utolsó, a kocsikat ellenőrizzük
        while (allIsEmpty) {
            for (Engine e: engines) {
                if (e.getFirstNotEmptyCar() != null) {
                    return false;
                }
            }
        }
        return allIsEmpty;
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
            return true;
        }


        // terepasztal szélére ért egy engine -> enginnek null az actpos-ja
        for (Engine e: engines) {
            if (e.getActPos() == null) {
                return true;
            }
        }


        ///// ütközés
        Set<Rail> train_pos = new HashSet<>();
        boolean isNotDuplicateRail = true;
        for (Engine e: engines) {
            isNotDuplicateRail = train_pos.add(e.getActPos());
            Car c = e.getFirstCar();
            while ( c != null && isNotDuplicateRail) {
                isNotDuplicateRail = train_pos.add(c.getActPos());
                c = c.getNextCar();
            }
        }
        return !isNotDuplicateRail;
    }



    ///////////////     Done
    // Kocsik kiürítése
    // utasok leszállítását végrehajtó függvény
    public void emptyCars() {
        for (Engine e: engines) {
            Car car = e.getFirstNotEmptyCar();
            // az első nem üres kocsi és állomáson vagyunk
            if (car != null && car.getActPos().getColor() != null ) {
                // ha egyezik a szín, kiürítjük a kocsit
                if ( (car.getActPos()).getColor() == car.getColor())
                    car.setEmpty();
            }
        }
    }

}
