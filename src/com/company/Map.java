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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Represents the game board. Controls tunnels, stations, derailing and start
 * positions.
 * 
 * @author i_did_iit team 
 *
 */
public class Map {


	/**
	 * Positions where trains can start moving.
	 */
    private Rail startPosition = new Rail();                                            // a vonatok kezdőpozíciója generálásukkor
    
    /**
	 * Positions where the player can build tunnel.
	 */
    private ArrayList<Tunnel> tunnelPositions = new ArrayList<>();
    
    
    /**
     *List of tunnel points. 
     */
    List<Rail> tunnel = new ArrayList<>();
    
	/**
	 * Positions where passengers can get off and get on.
	 */
    private ArrayList<Station> stations = new ArrayList<>();
    
    
    /**
     * List of rails under the ground.
     */
    private List<Rail> rails = new ArrayList<>();                                       
    
    /**
	 * isTrainInTunnel whether there is a train in tunnel.
	 */
    public static boolean isActiveTunnel;                                              
    
    /**
	 * isTrainInTunnel whether there is a train in tunnel.
	 */
    public static boolean isTrainInTunnel;                                              
    
    /**
	 * Positions where tunnel point is active.
	 */
    private ArrayList<Tunnel> activeTunnelPositions = new ArrayList<>();               
    
	/**
	 * isDerailing whether two parts of any train is on the same rail.
	 */
    public static boolean isDerailing;                                                  
    
    /**
     *List of start times of engines.
     */
    public int[] engineStartTimes;                                                   
    
    /**
     * List of engines.
     */
    public ArrayList<Engine> mapEngines = new ArrayList<>();
    
    /**
     * List of all colors had by at least one station.
     */
    public static ArrayList<String> stationColors = new ArrayList<String>();


	/**
	 * Getter of start positions. Returns with ArrayList of Rail objects.
	 * 
	 * @return The positions where trains can start moving.
	 */
    public Rail getStartPosition() {
        return startPosition;
    }

	/**
	 * Getter of stations. Return with ArrayList of Station objects.
	 * 
	 * @return The positions of stations where passengers can get off and get on.
	 */
    public ArrayList<Station> getStations() {
        return stations;
    }

	/**
	 * Getter of tunnel points. Return with ArrayList of Tunnel objects.
	 * 
	 * @return The positions of tunnel points where player can build tunnel.
	 */
    public ArrayList<Tunnel> getTunnelPositions() {
        return tunnelPositions;
    }

	/**
	 * Getter of isActiveTunnel. Returns true whether there is an active tunnel.
	 * 
	 * @return isActiveTunnel
	 */
    public boolean isActiveTunnel() { return isActiveTunnel; }

	/**
	 * Getter of isDerailing. Returns true if whether there is derailing.
	 * 
	 * @return True whether there is a derailing.
	 */
    public static boolean getIsDerailing() {
        return isDerailing;
    }

    /**
     * Setter of isDerailing. Sets isDerailing value what was given in parameter.
     * @param isDerailing
     * 					True is there is a derailing.
     */
    public static void setIsDerailing(boolean isDerailing) {
        Map.isDerailing = isDerailing;
    }

    /**
     * Default constructor of Map class.
     * Sets isActiveTunnel, isDerailing and isTrainInTunnel false.
     * Clears activeTunnelPositions.
     */

    Scene mainscene;

    public Map() {
        isActiveTunnel = false;
        isDerailing = false;
        isTrainInTunnel = false;
        activeTunnelPositions.clear();
    }


    /**
     * Control of a tunnel point. Writes out what happened.
     * @param setThisTunnel
     * 						Tunnel point to be modified.
     */
    public void controlTunnel(Tunnel setThisTunnel) {


        // ha még nincs megépülve alagút
        if (isActiveTunnel == false) {

            // ha egyetlen tunnel sem aktív -  a kérést biztosan ki lehet szolgálni
            if (activeTunnelPositions.size() == 0) {
                setThisTunnel.setActive(true);
                activeTunnelPositions.add(setThisTunnel);
                System.out.println("<Tunnel position set to active>");
            }

            // ha már van egy aktív tunnel  - vagy ugyanaz, vagy új tunnel
            else if (activeTunnelPositions.size() == 1){

                // ugyanaz a tunnel -> active átállítása, pontok törlése
                if (setThisTunnel == activeTunnelPositions.get(0)) {
                    activeTunnelPositions.clear();
                    setThisTunnel.setActive(false);
                    System.out.println("<Tunnel position set to inactive>");
                }

                // új tunnelre kattintott a felhasználó -- ha az első pontból elérhető a másik, megépül az alagút
                // ha nem érhető el, nem történik semmi,
                else {

                    // Alagút építése
                    // ha elérhető
                    if (checkList(setThisTunnel)) {
                        System.out.println("<Tunnel constructed>");
                    }

                    //  bejárásnak nem eleme az adott tunnel -> nem épül alagút, tunnel active tagváltozója nem változik
                    // azaz semmi nem történik
                    else {
                        System.out.println("<Can not build tunnel between the two tunnel positions>");
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
                System.out.println("<Tunnel demolished>");
            }

            // amúgy semmi nem történik
            else {
                System.out.println("<Can not demolish tunnel>");
            }
        }
    }


    /**
     * Loads map from xml file. 
     * @param mapName
     * 					Name of map.
     */
    public void loadMap(String mapName) {
        try {

            String path = System.getProperty("user.dir");

            // If running with args should work as stated in docs, leave this in
            // this clips the last dir from the path (aka the \src dir, since the test isn't at \src\test )
            //path = path.substring(0, path.lastIndexOf("\\"));

            File fXmlFile = new File(path + "\\maps\\" + mapName + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList elementList = doc.getElementsByTagName("element");

            for (int i = 0; i < elementList.getLength(); i++) {
                Node nNode = elementList.item(i);
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("type").equals("rail")) {
                    Rail rail = new Rail(null, null, eElement.getAttribute("name"));
                    rails.add(rail);
                    int x = Integer.valueOf(eElement.getAttribute("x"));
                    int y = Integer.valueOf(eElement.getAttribute("y"));
                    boolean vertical = false;
                    if (eElement.getAttribute("vertical").equals("y")){
                    	vertical = true;
                    }
                    Scene.addDrawable(new Draw_Rail(rail,x,y,vertical));
                    if (eElement.getAttribute("name").equals("startpos"))
                        startPosition = rail;
                } else if (eElement.getAttribute("type").equals("switch")) {
                    Switch sw = new Switch(null, null, null, eElement.getAttribute("name"));
                    rails.add(sw);
                    Scene.addDrawable(new Draw_Switch(sw));
                } else if (eElement.getAttribute("type").equals("tunnel")) {
                    Tunnel tunnel = new Tunnel(eElement.getAttribute("name"));
                    rails.add(tunnel);
                    Scene.addDrawable(new Draw_Tunnel(tunnel));
                    tunnelPositions.add(tunnel);
                } else if (eElement.getAttribute("type").equals("crossRail")) {
                    CrossRail crossRail = new CrossRail(null, null, null, null, eElement.getAttribute("name"));
                    rails.add(crossRail);
                    Scene.addDrawable(new Draw_CrossRail(crossRail));
                } else if (eElement.getAttribute("type").equals("station")) {
                    Station station = new Station(eElement.getAttribute("name"));
                    rails.add(station);
                    Scene.addDrawable(new Draw_Station(station));
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
                    if (eElement.hasAttribute("color"))
                    	rails.get(getIndexByName(eElement.getAttribute("name"))).setColor(eElement.getAttribute("color"));
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



            // Betöltjük a vonatokat
            NodeList engineList = doc.getElementsByTagName("engine");
            engineStartTimes = new int[engineList.getLength()];
            for (int i = 0; i < engineList.getLength(); i++) {

                Node nNode = engineList.item(i);
                Element eElement = (Element) nNode;

                engineStartTimes[i] = Integer.parseInt(eElement.getAttribute("startRound"));
                int numberOfTrainElement = Integer.parseInt(eElement.getAttribute("length"));

                HashMap<Integer, String> trainElements = new HashMap<Integer, String>();

                // hashmap-et építünk az elemekből és azok színéből
                for (int k = 1; k < numberOfTrainElement+1; k++) {
                    String e = eElement.getElementsByTagName("trainElement_"+k).item(0).getTextContent();
                    trainElements.put(k, e);
                }

                Engine e = new Engine(eElement.getAttribute("name"), numberOfTrainElement, trainElements);
                mapEngines.add(e);
                Scene.addDrawable(new Draw_Engine(e));
            }
            
            //létező állomásszínek eltárolása
            NodeList colors = doc.getElementsByTagName("color");
            for (int i = 0; i < colors.getLength(); i++) stationColors.add(((Element)colors.item(i)).getAttribute("value"));
            

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



    /**
     * Builds a tunnel if there's a valid route between the active tunnel position and the parameter.
     * @param newTunnel
     * 					Second end of the tunnel to be built.
     * @return Returns 'true' if the tunnel has been built.
     */
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

    /**
     * Builds tunnel. Creates a doubly linked list of Rail objects between the active tunnel points.
     * @param length
     * 				Length of tunnel.
     * @param dir
     * 			  Direction of tunnel.
     */
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



    /**
     * Represents user interactions. Writes out what we clicked and depending of type executes the necessary steps. 
     * @param name
     * 				Name of object we clicked.
     */
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


	/**
	 * Getter of isActiveTunnel. Returns true whether there is an active tunnel.
	 * 
	 * @return isActiveTunnel
	 */
    static public boolean getIsActiveTunnel() {
        return isActiveTunnel;
    }

	/**
	 * Getter of isTrainInTunnel.
	 * 
	 * @return True whether there is a train in tunnel.
	 */
    static public boolean getIsTrainInTunnel() {
        return isTrainInTunnel;
    }

	/**
	 * Setter of isTrainInTunnel.
	 * 
	 * @param b
	 *            True whether there is a train in tunnel.
	 */
    static public void setIsTrainInTunnel(boolean b) {
        isTrainInTunnel = b;
    }

    /**
     * Gets index by name. It helps working with XML.
     * @param name
     * 			Name of Rail object.
     * @return
     * 			Number of index we need.
     */
    private int getIndexByName(String name) {
        for (int i = 0; i < rails.size(); i++) {
            if (rails.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Writes out the state off tunnel.
     */
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
