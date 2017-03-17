package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Map {
    ///////////
    // lehetséges kezdőpontok tömbjei
    private ArrayList<Rail> startPositions = new ArrayList<>();
    private ArrayList<Tunnel> tunnelPositions = new ArrayList<>();
    private ArrayList<Station> stations = new ArrayList<>();


    // tunnel karbantartásához szükséges változók
    private static boolean isActiveTunnel;                                      // számontartja, van-e megépülve alagút
    private static boolean isTrainInTunnel;
    private static boolean isDerailing;
    private ArrayList<Tunnel> activeTunnelPositions = new ArrayList<>();        // tároljuk, hogy mely két pont között van aktív alagút


    ////////////
    public ArrayList<Rail> getStartPositions() {
        System.out.println("        -> [Map].getStartPositions()");

        System.out.println("        <- [Map].getStartPositions(List<Rails>)");

        return startPositions;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Tunnel> getTunnelPositions() {
        return tunnelPositions;
    }

    public boolean isActiveTunnel() {
        return isActiveTunnel;
    }


    // Fájlból vagy bedrótozva betöltünk egy játékot
    public Map(int mapNumber) {

        isActiveTunnel = false;

        // mapNumbertől függően töltjük be az adott pályát
        if (mapNumber == 1) {

        } else {

        }
    }

    ///////////// TO-Do: mouseClicked eventre majd beregisztrálni
    // Az alagútak kezelését végrehajtó függvény, ami paraméterben
    // egy Tunnel-t kap (ezt módosította a felhasználó)
    public void controlTunnel(Tunnel t, int seq) throws InputMismatchException {
        //3: alagút törlése
        if (seq == 3) {
            System.out.println("    -> [Map].controlTunnel(t)");

            System.out.println("3.1 Van megépült alagút?");
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();

            //ha van alagút, vizsgáljuk a további feltételeket
            if (command.equals("I")) {
                System.out.println("3.2 Aktív alagútszájra kattintottunk?");
                command = input.nextLine();
                //ha aktív alagútszájra kattintottunk és nincs bent vonat, akkor törölhető az alagútszáj
                if (command.equals("I") && !Game.getIsTrainInTunnel(3)) {
                    t.setActive(false, 3);
                } else if (!command.equals("H")) throw new InputMismatchException();
            } else if (!command.equals("H")) throw new InputMismatchException();
        }
        System.out.println("    <- [Map].controlTunnel(t)");
        return;

    }


    // TO-DO
    ///// segédfüggvény - bejárhatóság
    // startPos-ból megpróbálja elérni tunnel-t, true ha sikerül
    private boolean checkList(Tunnel startPos, Tunnel tunnel) {

        return false;
    }


    // TODO
    // felhasználó interakcióját megvalósító függvény
    // külön kezelei az esetek attól függően, hogy mire kattintott
    public void onMouseClickedEvent() {
        // ha tunnel-re, a controlTunnel() hívódik meg
        // ha váltóra, akkor annak az állítása történik meg
    }

    static public boolean getIsActiveTunnel() {
        return isActiveTunnel;
    }

    static public boolean getIsTrainInTunnel() {
        return isTrainInTunnel;
    }


    // kisiklás ellenőrzése static változóval
    static public boolean getIsDerailing() {
        System.out.println("        -> [Map].getIsDerailing()");

        System.out.println("7.1: Kisiklottunk? ");

        String command;
        Scanner input = new Scanner(System.in);
        command = input.nextLine();

        if (command.equals("I")) {
            System.out.println("        <-[Map].getIsDerailing(true)");
            return true;
        } else if (!command.equals("N")) {
            throw new IllegalArgumentException();
        } else
            System.out.println("        <-[Map].getIsDerailing(false)");
        return false;
    }


    static public void setIsTrainInTunnel(boolean b) {
        isActiveTunnel = b;
    }
}
