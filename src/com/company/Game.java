package com.company;

//import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the game control. Responsible for moving, generating, emptying and deleting trains, loading map, checking crash and winning.
 * 
 * @author i_did_iit team
 *
 */
public class Game {


    
    /**
     * Storing the current map.
     */
    private Map map;
    /**
     * isLastGame whether it is the last game.
     */
    private boolean isLastGame;
    /**
     * Maximum number of trains.
     */
    private final int maxTrainNumber = 3;
    /**
     * List of engines.
     */
    private ArrayList<Engine> engines = new ArrayList<>();              // vonatok tárolása



    /**
     * Default construstor of Game class. Set isLastGame true.
     */
    public Game() {
        isLastGame = true;
    }


    /**
     * Getter of isLastGame. Check that this is the last map. Returns with boolean type.
     * @return isLastGame.
     */
    public boolean getIsLastGame() {
        return isLastGame;
    }

    
    /**
     * Setter of isLastGame. Set isLastGame value of parameter.
     * @param lastGame
     *					 True whether it is the last game.
     */
    public void setIsLastGame(boolean lastGame) {
        isLastGame = lastGame;
    }


    /**
     * Loads map. Calls default constructor and loadMap function of Map class. 
     * @param mapName
     * 					Name of map.
     */
    public void loadMap(String mapName) {
        map = new Map();
        map.loadMap(mapName);
    }


    /**
     * Controls click. Calls onMouseClickedEvent function of Map class.
     * @param name
     * 				Name of object what was clicked.
     */
    public void onClicked(String name) {
        map.onMouseClickedEvent(name);
    }

    /**
     * Generates trains. Gets possible start position and set it to new engine. Checks the emptiness of start position.
     * If random is true, than generates new train, else loads it from xml file.
     * 
     * @param round
     * 				Number of rounds.
     * @param random
     * 				Activity of random.
     */
    public void generateTrain(int round, boolean random) {


        // Törölhető, ha úgy döntünk felesleges (pálya széléről lehajtanánk amúgy)
        // Többi generáláskor már ellenőrizni kell, üres-e a startRail
        Set<Rail> positions = new HashSet<>();
        boolean empty = true;
        for (Engine e : engines) {
            if (e.getActPos() == map.getStartPosition())
                empty = false;
        }


        // ha a véletlenszerűség nincs bekapcsolva, az xml-ből betöltött vonatokat használjuk
        if (!random) {
            /*
            if (Arrays.asList(map.engineStartTimes).contains(round)) {
                //Arrays.asList(map.engineStartTimes).contains(round);
                int index = engines.size();
                engines.add(map.mapEngines.get(index));
                engines.get(index).actPos = map.getStartPosition();
                System.out.format("New engine at startPosition");

            }*/

            for (int i = 0; i < map.engineStartTimes.length; i++) {
                if (round == map.engineStartTimes[i]) {
                    int index = engines.size();
                    engines.add(map.mapEngines.get(index));
                    engines.get(index).actPos = map.getStartPosition();
                    System.out.println("New engine at startPosition");
                }
            }

        }

        // amúgy generálunk újat
        else {
            // első körben generálunk
            if ((round == 1 || round == 10) && empty) {
                int numberOfCars = (int) (Math.random() * (6 - 2)) + 2;
                System.out.format("New engine with %d cars: ", numberOfCars);
                Engine newEngine = new Engine(map.getStartPosition(), numberOfCars, "engine_" + round);
                engines.add(newEngine);
                return;
            }
        }
    }



    /**
     * Moves trains. Calls moveEngine function of all engines.
     * @param counter
     */
    public void moveTrains(int counter) {
        for (Engine e: engines) {
            e.moveEngine(counter);
        }
    }


    /**
     * Checks winning. Checks the conditions of winning. These are empty cars and empty stations. 
     * @return
     * 			True whether there is a winning.
     */
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


    /**
     * Deletes trains whether there is a new map. Calls clear function of list of engines. 
     */
    public void deleteTrains() {
        engines.clear();
    }

    /**
     * Detects crash. If there is derailing then write it out and returns true.
     * If we move out of map, write it out and returns true.
     * If there is a crash, write it out and returns true.
     * Else returns false. 
     * @return
     * 			Result of checking.
     */
    public boolean crashDetection() {

        ///// kisiklás
        if (Map.getIsDerailing()) {
            System.out.println("Train derailed!");
            return true;
        }


        // terepasztal szélére ért egy engine -> enginnek null az actpos-ja
        for (Engine e: engines) {
            if (e.getActPos() == null) {
                System.out.println("The train has left the map!");
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
        if (!isNotDuplicateRail) System.out.println("Train crashed!");
        return !isNotDuplicateRail;
    }



    /**
     * Delivery of passengers. If first not empty car is on station and station's color equals with car's color then passengers get off and write it out.
     * @param counter
     */
    public void emptyCars(int counter) {
        for (Engine e: engines) {
            Car car = e.getFirstNotEmptyCar();
            if (car != null) {                                                              // az első nem üres kocsi és állomáson vagyunk
                if ((car.getActPos() != null)) {
                    if ((car.getActPos().getColor() != null)) {
                        if (car.roundLastEmpty != counter) {                                // Kizárjuk az egy körben történő felszállást majd leszállást
                            if ((car.getActPos()).getColor() == car.getColor()) {           // ha egyezik a szín, kiürítjük a kocsit
                                car.setEmpty(true);
                                System.out.println("    <Passangers left the train: " + car.getActPos().name + ", " + car.name + " >");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Writes out name, color and number of passengers of all stations. 
     * @param param
     * 				Name of object. It should contains "station" string.
     */
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

    /**
     * Writes out the state off tunnel. Calls printTunnelState function of Map class.
     */
    public void printTunnelState() {
        map.printTunnelState();
    }

    /**
     * Lists one train. Name of engine is given in parameter. 
     * Write out number and position of engine, number, position, color and emptiness of other train element(s) if there is any.
     * @param param
     * 				Name of engine.
     */
    public void listEngine(String param) {
        for (Engine e : engines
             ) {
            if (e.name.equals(param)) {
                System.out.println("<Name: " + e.name + "><Position: " + e.actPos.name + " >");
                Train_Element te = e.nextTrainElement;
                while (te != null) {
                    if (te.getActPos() == null)
                        break;
                    System.out.println("<Name: " + te.name + " ><Position: " + te.actPos.name + " ><Color: " + te.getColor() + ">< empty: " +te.isEmpty() + " >");
                    te = te.getNextTrainElement();
                }
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Lists trains. If there is not any trains, write it. Write out number and position of engines, number, position, color and emptiness of other train element(s) if there is any.  
     */
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
