package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
    List<Rail> tunnel = new ArrayList<>();
    private ArrayList<Station> stations = new ArrayList<>();
    private List<Rail> rails = new ArrayList<>();
    public static boolean isActiveTunnel;                                      // számontartja, van-e megépülve alagút
    public static boolean isTrainInTunnel;
    private ArrayList<Tunnel> activeTunnelPositions = new ArrayList<>();        // tároljuk, hogy mely két pont között van aktív alagút
    public static boolean isDerailing;                                         // volt-e kisiklás - váltó állítja



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
                System.out.println("Alagútszáj aktiválva");
            }

            // ha már van egy aktív tunnel  - vagy ugyanaz, vagy új tunnel
            else if (activeTunnelPositions.size()== 1){

                // ugyanaz a tunnel -> active átállítása, pontok törlése
                if (setThisTunnel == activeTunnelPositions.get(0)) {
                    activeTunnelPositions.clear();
                    setThisTunnel.setActive(false);
                    System.out.println("Alagútszáj deaktiválva");
                }

                // új tunnel-re kattintottt a felhasználó  - ha az első pontból elérhető a másik, megépül az alagút
                // ha nem érhető el, nem történik semmi,
                else {

                    // Alagút építése
                    // ha elérhető
                    if (checkList(setThisTunnel)) {
                        System.out.println("Alagút megépítve");
                    }

                    //  bejárásnak nem eleme az adott tunnel -> nem épül alagút, tunnel active tagváltozója nem változik
                    // azaz semmi nem történik
                    else {
                        System.out.println("Bejárás nem volt sikeres");
                    }
                }
            }
        }


        // ha már van megépült alagút
        else {

            // Alagút törlése
            // Ha már aktív alagútszájra kattintottunk, és nincs vonat az alagútban
            if (activeTunnelPositions.contains(setThisTunnel) && !isTrainInTunnel) {
                                                                                    // Alagút törlése:
                rails.removeAll(tunnel);                                            // töröljük rails-ből
                activeTunnelPositions.get(0).setTunnelRail(null, null);             // két alagútszáj elvarrása
                activeTunnelPositions.get(1).setTunnelRail(null, null);
                tunnel.clear();                                                     // tunnel kiürítése
                isActiveTunnel = false;                                             // nincs megépült alagút
                activeTunnelPositions.remove(setThisTunnel);                        // aktív pontok listájából törölni kell
                setThisTunnel.setActive(false);                                     // alagútszáj aktivításának törlése
                System.out.println("Alagút törölve");
            }

            // amúgy semmi nem történik
            else {
                System.out.println("Hatástalan interakció");
            }
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




    // Segédfüggvény - bejárhatóság
    // startPos-ból megpróbálja elérni tunnel-t, true ha sikerül
    private boolean checkList(Tunnel newTunnel) {

        int lengthA = 0;
        int lengthB = 0;
        boolean dirAsuccess = false;
        boolean dirBsuccess = false;
        Tunnel startTunnel = activeTunnelPositions.get(0);
        Rail nextRail = startTunnel.nextRail;
        Rail prevRail = startTunnel.prevRail;


        // Bejárás a két irányból (váltóknál szakadás):
        while (nextRail != null && nextRail.getThirdRail() == false) {
            if (newTunnel == nextRail) {
                dirAsuccess = true;
                break;
            }
            lengthA++;
            nextRail = nextRail.nextRail;
        }

        while (prevRail != null && prevRail.getThirdRail() == false) {
            if (newTunnel == prevRail) {
                dirBsuccess = true;
                break;
            }
            lengthB++;
            prevRail = prevRail.prevRail;
        }


        // Ha az alagút megépülhet:
        if (dirAsuccess || dirBsuccess) {

            // Alagút megépítése:
            //      beállítás, hogy van megépült alagút
            //      aktív alagútszájak listájához hozzáadás
            //      aktivitás beállítása
            //      majd tunnel lista megépítése és összekötése, hozzáadás a rails-hez
            isActiveTunnel = true;
            activeTunnelPositions.add(newTunnel);
            newTunnel.setActive(true);

            if (dirAsuccess)
                createTunnel(lengthA, "next");
            else
                createTunnel(lengthB, "prev");

            return true;
        }

        // ha nem volt sikeres a bejárás
        else
            return false;
    }


    // Alagút megépítése
    // megadott mennyiségű railt példányosít,
    // majd összeköti a két aktív tunnel-el és berakja a rails-be
    private void createTunnel(int length, String dir) {

        // sínek példányosítása
        for (int i = 0; i < length; i++) {
            Rail r = new Rail(null, null, "tunnelRail_" + i);
            tunnel.add(r);
        }

        // sínek összekötése
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                // az első elem összekötése
                tunnel.get(0).setPrevRail(activeTunnelPositions.get(0));
                tunnel.get(0).setNextRail(tunnel.get(1));
                activeTunnelPositions.get(0).setTunnelRail(tunnel.get(0), dir);
            }
            else if (i == length-1) {
                // az utolsó elem összekötése
                tunnel.get(i).setNextRail(activeTunnelPositions.get(1));
                tunnel.get(i).setPrevRail(tunnel.get(i-1));
                if (dir.equals("next"))
                    activeTunnelPositions.get(1).setTunnelRail(tunnel.get(i), "prev");
                else
                    activeTunnelPositions.get(1).setTunnelRail(tunnel.get(i), "next");
            }
            else {
                tunnel.get(i).setPrevRail(tunnel.get(i-1));
                tunnel.get(i).setNextRail(tunnel.get(i+1));
            }
        }


        // hozzáadás a sínekhez
        rails.addAll(tunnel);

    }



    // A felhasználó interakcióját megvalósító függvény:
    //      külön kezelei az esetek attól függően, hogy mire kattintottunk
    //      proto-hoz átalakítás: String paramétert kap
    public void onMouseClickedEvent(String name) {

        // Ha van ilyen nevű elemünk a sínek listájában
        if ( getIndexByName(name) != -1) {

            // váltó esetén váltunk
            if (name.contains("switch")) {
                System.out.print("<" + name + "> ");
                rails.get(getIndexByName(name)).changeDir();
            }

            // alagútszáj esetén az alagútkezelő függvényt hívjuk meg
            if (name.contains("tunnel")) {
                System.out.println("<" + name + "> ");
                if (rails.get(getIndexByName(name)).occupied) {
                    System.out.println("<Tunnel \"" + name + "\" occupied> ");
                    return;
                }
                else
                    controlTunnel( (Tunnel) rails.get(getIndexByName(name)));
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
        isTrainInTunnel = b;
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


    public void printTunnelState() {

        // Ha van megépült alagút:
        //  print state + alagútszájak
        if (getIsActiveTunnel()) {
            System.out.println("<There is one active tunnel, with tunnel positions: >");
            System.out.println("    <" + activeTunnelPositions.get(0).name + ">");
            System.out.println("    <" + activeTunnelPositions.get(1).name + ">");
        }


        else if (activeTunnelPositions.size()==1) {
            System.out.println("<There is one active tunnel position: >");
            System.out.println("    <" + activeTunnelPositions.get(0).name + ">");
        }

        else
            System.out.println("<There is no tunnel or active tunnelposition>");
    }
}
