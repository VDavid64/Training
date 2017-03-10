package com.company;

import java.util.ArrayList;

public class Map {

    ///////////
    // lehetséges kezdőpontok tömbjei
    private ArrayList<Rail> startPositions = new ArrayList<>();
    private ArrayList<Tunnel> tunnelPositions = new ArrayList<>();
    private ArrayList<Station> stations = new ArrayList<>();


    // tunnel karbantartásához szükséges változók
    private static boolean isActiveTunnel;                                      // számontartja, van-e megépülve alagút
    private static boolean isTrainInTunnel;
    private ArrayList<Tunnel> activeTunnelPositions = new ArrayList<>();        // tároljuk, hogy mely két pont között van aktív alagút


    ////////////
    public ArrayList<Rail> getStartPositions() {
        return startPositions;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Tunnel> getTunnelPositions() {
        return tunnelPositions;
    }

    public boolean isActiveTunnel() { return isActiveTunnel; }



    // Fájlból vagy bedrótozva betöltünk egy játékot
    public Map(int mapNumber) {

        isActiveTunnel = false;

        // mapNumbertől függően töltjük be az adott pályát
        if(mapNumber == 1) {

        }

        else {

        }
    }

    ///////////// TO-Do: mouseClicked eventre majd beregisztrálni
    // Az alagútak kezelését végrehajtó függvény, ami paraméterben
    // egy Tunnel-t kap (ezt módosította a felhasználó)
    public void controlTunnel(Tunnel setThisTunnel) {

        // ha még nincs megépülve alagút
        if (isActiveTunnel == false) {

            // ha egyetlen tunnel sem aktív -  a kérést biztosan ki lehet szolgálni
            if (activeTunnelPositions.size()==0) {
                setThisTunnel.setActive(true);
                activeTunnelPositions.add(setThisTunnel);
            }

            // ha már van egy aktív tunnel  - vagy ugyanaz, vagy tunnel
            else {
                // ugyanaz a tunnel -> active átállítása, pontok törlése
                if (setThisTunnel == activeTunnelPositions.get(0)) {
                    activeTunnelPositions.clear();
                    setThisTunnel.setActive(false);
                }

                // új tunnel-re kattintottt a felhasználó  - ha az első pontból elérhető a másik, megépül az alagút
                // ha nem érhető el, nem történik semmi,
                else {

                    // ha elérhető
                    if (checkList(activeTunnelPositions.get(0), setThisTunnel)) {
                        isActiveTunnel = true;
                        activeTunnelPositions.add(setThisTunnel);
                        setThisTunnel.setActive(true);
                    }

                    //  bejárásnak nem eleme az adott tunnel -> nem épül alagút, tunnel active tagváltozója nem változik
                    // azaz semmi nem történik
                    else {}
                }
            }
        }

        // ha már van megépült alagút
        else {

            // TO-DO: ellenőrizni kell, hogy nincs-e bent vonat
            // ha olyanra kattintottunk, ami aktív már - rombolunk
            if (activeTunnelPositions.contains(setThisTunnel)) {
                isActiveTunnel = false;
                activeTunnelPositions.remove(setThisTunnel);
                setThisTunnel.setActive(false);
            }

            // amúgy semmi nem történik
            else {}

        }


    }





    // TO-DO
    ///// segédfüggvény - bejárhatóság
    // startPos-ból megpróbálja elérni tunnel-t, true ha sikerül
    private boolean checkList(Tunnel startPos, Tunnel tunnel) {

        return false;
    }


    // TO-DO
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

    static public void setIsTrainInTunnel( boolean b) {
        isActiveTunnel = b;
    }
}
