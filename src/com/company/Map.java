package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.plaf.PanelUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Map {

    ///////////
    // lehetséges kezdőpontok tömbjei
    private Rail startPosition = new Rail();
    private ArrayList<Tunnel> tunnelPositions = new ArrayList<>();
    private ArrayList<Station> stations = new ArrayList<>();
    private List<Rail> rails = new ArrayList<>();


    // tunnel karbantartásához szükséges változók
    private static boolean isActiveTunnel;                                      // számontartja, van-e megépülve alagút
    private static boolean isTrainInTunnel;
    private ArrayList<Tunnel> activeTunnelPositions = new ArrayList<>();        // tároljuk, hogy mely két pont között van aktív alagút
    private static boolean isDerailing;                                         // volt-e kisiklás - váltó állítja







    ////////////
    public Rail getStartPosition() {
        return startPosition;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Tunnel> getTunnelPositions() {
        return tunnelPositions;
    }

    public boolean isActiveTunnel() { return isActiveTunnel; }

    public static boolean getIsDerailing() {
        return isDerailing;
    }

    public static void setIsDerailing(boolean isDerailing) {
        Map.isDerailing = isDerailing;
    }

    // Fájlból vagy bedrótozva betöltünk egy játékot
    public Map() {

        isActiveTunnel = false;
        isDerailing = false;
        /*
        // mapNumbertől függően töltjük be az adott pályát
        // tesztpálya
        if(mapNumber == 1) {

            // startpos +5 rail, majd egy váltó, még 5 rail körben, utolsó rail a váltóra illeszkedik szintén

            Rail stp = new Rail(null, null, "Start Position");
            Rail r1 = new Rail(null, stp, "Rail 1");
            stp.setNextRail(r1);
            startPosition = stp;

            Rail r2 = new Rail(null, r1, "Rail 2");
            r1.setNextRail(r2);
            Rail r3 = new Rail(null, r2, "Rail 3");
            r2.setNextRail(r3);
            Rail r4 = new Rail(null, r3, "Rail 4");
            r3.setNextRail(r4);
            Rail r5 = new Rail(null, r4, "Rail 5");
            r4.setNextRail(r5);

            Rail r6 = new Rail(null, null, "Rail 6");
            Rail r10 = new Rail(null, null, "Rail 10");

            // váltó beállítása
            Switch sw = new Switch(r6, r5, r10, "Első váltó");
            r5.setNextRail(sw);
            r6.setPrevRail(sw);
            r10.setNextRail(sw);

            Rail r7 = new Rail(null, r6, "Rail 7");
            r6.setNextRail(r7);
            Rail r8 = new Rail(null, r7, "Rail 8");
            r7.setNextRail(r8);
            Rail r9 = new Rail(null, r8, "Rail 9");
            r8.setNextRail(r9);
            r9.setNextRail(r10);
            r10.setPrevRail(r9);

            rails.add(r1); rails.add(r2); rails.add(r3); rails.add(r4);
            rails.add(r5); rails.add(r6); rails.add(r7); rails.add(r8);
            rails.add(r9); rails.add(r10); rails.add(sw);

        }
        */
    }

    ///////////// TODO: mouseClicked eventre majd beregisztrálni
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




    public void loadMap(String mapName) {
        try {


            String path = System.getProperty("user.dir");
            File fXmlFile = new File(path + "\\maps\\" + mapName + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();


            // végigmegyünk az összes példányosításon - rail / switch / tunnel / crossRail / station
            NodeList elementList = doc.getElementsByTagName("element");

            for (int i = 0; i < elementList.getLength(); i++) {
                Node nNode = elementList.item(i);
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("type").equals("rail")) {
                    Rail rail = new Rail(null, null, eElement.getAttribute("name"));
                    rails.add(rail);
                } else if (eElement.getAttribute("type").equals("switch")) {
                    Switch sw = new Switch(null, null, null, eElement.getAttribute("name"));
                    rails.add(sw);
                } else if (eElement.getAttribute("type").equals("tunnel")) {
                    Tunnel tunnel = new Tunnel();
                    rails.add(tunnel);
                } else if (eElement.getAttribute("type").equals("crossRail")) {
                    CrossRail crossRail = new CrossRail(null, null, null, null);
                    rails.add(crossRail);
                } else if (eElement.getAttribute("type").equals("station")) {
                    Station station = new Station(null, null);
                    rails.add(station);
                }
            }



            // Terepasztal elemeinek összekapcsolása
            NodeList connectionList = doc.getElementsByTagName("connection");

            for (int i = 0; i < connectionList.getLength(); i++) {
                Node nNode = connectionList.item(i);
                Element eElement = (Element) nNode;

                // sínekre és állomásokra és alagútszájra
                if (eElement.getAttribute("type").equals("rail") || eElement.getAttribute("type").equals("station") || eElement.getAttribute("type").equals("tunnel")) {
                    if (!eElement.getAttribute("nextRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setNextRail(rails.get(getIndexByName(eElement.getAttribute("nextRail"))));
                    if (!eElement.getAttribute("prevRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setPrevRail(rails.get(getIndexByName(eElement.getAttribute("prevRail"))));
                }

                // kereszteződő sínekre
                if (eElement.getAttribute("type").equals("crossrail")) {
                    if (!eElement.getAttribute("nextRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setNextRail(rails.get(getIndexByName(eElement.getAttribute("nextRail"))));
                    if (!eElement.getAttribute("prevRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setPrevRail(rails.get(getIndexByName(eElement.getAttribute("prevRail"))));
                    if (!eElement.getAttribute("otherprevRail").equals("null") || !eElement.getAttribute("othernextRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setOtherCrossRails(
                                rails.get(getIndexByName(eElement.getAttribute("othernextRail"))), rails.get(getIndexByName(eElement.getAttribute("otherprevRail"))));
                }

                // váltó
                if (eElement.getAttribute("type").equals("switch")) {
                    if (!eElement.getAttribute("nextRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setNextRail(rails.get(getIndexByName(eElement.getAttribute("nextRail"))));
                    if (!eElement.getAttribute("prevRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setPrevRail(rails.get(getIndexByName(eElement.getAttribute("prevRail"))));
                    if (!eElement.getAttribute("thirdRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setSwitchThirdRail(rails.get(getIndexByName(eElement.getAttribute("thirdRail"))));
                }

            }

            System.out.println();
            startPosition = rails.get(0);
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }



    // TODO
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


    // segédfüggvény az xml feldolgozásához
    public int getIndexByName(String name) {
        for (int i = 0; i < rails.size(); i++) {
            if (rails.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
