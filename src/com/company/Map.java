package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.plaf.PanelUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Map {



    ///////////////////////
    // Tagváltozók:
    private Rail startPosition = new Rail();
    private ArrayList<Tunnel> tunnelPositions = new ArrayList<>();
    private ArrayList<Station> stations = new ArrayList<>();
    private List<Rail> rails = new ArrayList<>();
    private static boolean isActiveTunnel;                                      // számontartja, van-e megépülve alagút
    private static boolean isTrainInTunnel;
    private ArrayList<Tunnel> activeTunnelPositions = new ArrayList<>();        // tároljuk, hogy mely két pont között van aktív alagút
    private static boolean isDerailing;                                         // volt-e kisiklás - váltó állítja






    ///////////////////////
    // Függvények:
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


    // Done
    public Map() {
        isActiveTunnel = false;
        isDerailing = false;
        isTrainInTunnel = false;
        activeTunnelPositions.clear();
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



    // betölti XML-ből a pályát
    public void loadMap(String mapName) {
        try {

            String path = System.getProperty("user.dir");
            File fXmlFile = new File(path + "\\maps\\" + mapName + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList elementList = doc.getElementsByTagName("element");

            // TODO: Switchek listája?
            for (int i = 0; i < elementList.getLength(); i++) {
                Node nNode = elementList.item(i);
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("type").equals("rail")) {
                    Rail rail = new Rail(null, null, eElement.getAttribute("name"));
                    rails.add(rail);
                    if (eElement.getAttribute("name").equals("startpos"))
                        startPosition = rail;
                } else if (eElement.getAttribute("type").equals("switch")) {
                    Switch sw = new Switch(null, null, null, eElement.getAttribute("name"));
                    rails.add(sw);
                } else if (eElement.getAttribute("type").equals("tunnel")) {
                    Tunnel tunnel = new Tunnel(eElement.getAttribute("name"));
                    rails.add(tunnel);
                    tunnelPositions.add(tunnel);
                } else if (eElement.getAttribute("type").equals("crossRail")) {
                    CrossRail crossRail = new CrossRail(null, null, null, null, eElement.getAttribute("name"));
                    rails.add(crossRail);
                } else if (eElement.getAttribute("type").equals("station")) {
                    Station station = new Station(eElement.getAttribute("name"));
                    rails.add(station);
                    stations.add(station);
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
                else if (eElement.getAttribute("type").equals("crossRail")) {
                    if (!eElement.getAttribute("nextRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setNextRail(rails.get(getIndexByName(eElement.getAttribute("nextRail"))));
                    if (!eElement.getAttribute("prevRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setPrevRail(rails.get(getIndexByName(eElement.getAttribute("prevRail"))));
                    if (!eElement.getAttribute("otherprevRail").equals("null") || !eElement.getAttribute("othernextRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setOtherCrossRails(
                                rails.get(getIndexByName(eElement.getAttribute("othernextRail"))), rails.get(getIndexByName(eElement.getAttribute("otherprevRail"))));
                }

                // váltó
                else if (eElement.getAttribute("type").equals("switch")) {
                    if (!eElement.getAttribute("nextRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setNextRail(rails.get(getIndexByName(eElement.getAttribute("nextRail"))));
                    if (!eElement.getAttribute("prevRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setPrevRail(rails.get(getIndexByName(eElement.getAttribute("prevRail"))));
                    if (!eElement.getAttribute("thirdRail").equals("null"))
                        rails.get(getIndexByName(eElement.getAttribute("name"))).setSwitchThirdRail(rails.get(getIndexByName(eElement.getAttribute("thirdRail"))));
                }

            }

            System.out.println("<Map loaded successfully: " +mapName +">");

        }

        catch (FileNotFoundException e){
            System.out.println("Invalid file name");
            return;
        }
        catch (Exception e) {
            System.out.println("Error occurred");
            e.printStackTrace();
            return;
        }
    }



    // TODO
    // Segédfüggvény - bejárhatóság
    // startPos-ból megpróbálja elérni tunnel-t, true ha sikerül
    private boolean checkList(Tunnel startPos, Tunnel tunnel) {

        return false;
    }


    // TODO
    // A felhasználó interakcióját megvalósító függvény: külön kezelei az esetek attól függően, hogy milyen típusra kattintottunk
    // Proto-hoz átalakítás: String paramétert kap
    public void onMouseClickedEvent(String name) {

        // Ha van ilyen nevű elemünk a sínek listájában
        if ( getIndexByName(name) != -1) {
            if (name.contains("switch")) {
                System.out.print("<" + name + "> ");
                rails.get(getIndexByName(name)).changeDir();
            }
        }
        else throw new IllegalArgumentException();
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
    private int getIndexByName(String name) {
        for (int i = 0; i < rails.size(); i++) {
            if (rails.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
