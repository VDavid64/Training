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
    	System.out.println("    -> [Map].controlTunnel(t)");
    	//1: alagút építése
    	if (seq == 1) {
    		 System.out.println("2.1 Van már megépült alagút? (I / N)");
    		 System.out.println("-> [Map].getisActiveTunnel()");
             Scanner input = new Scanner(System.in);
             String command = input.nextLine();
            //van alagút, szóval alagút törlése következik
             if (command.equals("I")) { 
            	 System.out.println("<- [Map].getisActiveTunnel():true");
            	 System.out.println("    <- [Map].controlTunnel(t)");
            	 this.controlTunnel(new Tunnel(),3);
             }
             //nincs alagút, további kérdések
             else if (command.equals("N")) { 
            	 System.out.println("<- [Map].getisActiveTunnel():false");
            	 System.out.println("2.2 Inaktív alagútszájra kattintottunk? (I/N)");
            	 System.out.println("-> [Tunnel].isActive()");
            	 command = input.nextLine();
            	 //inaktív az alagútszáj, további kérdés
            	 if (command.equals("I")){
            		 System.out.println("<- [Tunnel].isActive():false");
            		 System.out.println("2.2.1 Van már egy aktív alagútszáj? (I/N)");
            		 System.out.println("<- [Map].activeTunnelPositions.isEmpty()");
            		 command = input.nextLine();
            		 //van már aktív alagútszáj, további kérdés
            		 if (command.equals("I")){
            			 System.out.println("<- [Map].activeTunnelPositions.isEmpty():false");
            			 System.out.println("2.2.1.1. Folytonos a két pont közötti sínszakasz? (I/N");
            			 System.out.println("-> [Map]. checkList(t, [Map].activeTunnelPositions.get(0))");
            			 command = input.nextLine();
            			 //bejárható a sínszakasz, szóval alagutat építünk
            			 if(command.equals("I")){
            				 System.out.println("-> [Map]. checkList(t, [Map].activeTunnelPositions.get(0)):true");
            				 t.setActive(true);
                			 System.out.println("    -> [Map].activeTunnelPositions.add(t)");
                			 System.out.println("    <- [Map].activeTunnelPositions.add(t)");
                			 System.out.println("    -> setIsActiveTunnel(true)");
                			 setIsActiveTunnel(true);
                			 
                			 
            			 }
            			 //nem járható be a sínszakasz, nem történik semmi
            			 else if (command.equals("N")){
            				 System.out.println("<- [Map]. checkList(t, [Map].activeTunnelPositions.get(0)):false"); 
            			 }
            			 else if(!command.equals("N")) throw new InputMismatchException();
            		 }
            		 //alagútpont aktiválása
            		 else if (command.equals("N")){
            			 System.out.println("<- [Map].activeTunnelPositions.isEmpty():true");
            			 t.setActive(true);
            			 System.out.println("    -> [Map].activeTunnelPositions.add(t)");
            			 System.out.println("    <- [Map].activeTunnelPositions.add(t)");
            		 }
            		 else if(!command.equals("N")) throw new InputMismatchException();
            	 }
            	 //aktív az alagútszáj, szóval lebontjuk
            	 else if (command.equals("N")){
            		 System.out.println("<- [Tunnel].isActive():true");
            		 t.setActive(false);
            		 this.activeTunnelPositions.remove(t);
            		 System.out.println("    ->  [Map].activeTunnelPositions.remove(t)");
            		 System.out.println("    <-  [Map].activeTunnelPositions.remove(t)");
            	 }
            	 else if(!command.equals("N")) throw new InputMismatchException();
             }
             else if(!command.equals("N")) throw new InputMismatchException();
    	}
        //3: alagút törlése
        if (seq == 3) {
       

            System.out.println("3.1 Van megépült alagút?");
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();

            //ha van alagút, vizsgáljuk a további feltételeket
            if (command.equals("I")) {
                System.out.println("3.2 Aktív alagútszájra kattintottunk?");
                command = input.nextLine();
                //ha aktív alagútszájra kattintottunk és nincs bent vonat, akkor törölhető az alagútszáj
                if (command.equals("I") && !Game.getIsTrainInTunnel(3)) {
                    t.setActive(false);
                } else if (!command.equals("N")) throw new InputMismatchException();
            } else if (!command.equals("N")) throw new InputMismatchException();
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

    static public void setIsActiveTunnel(boolean b) {
    		System.out.println("    <- setIsActiveTunnel()");
        isActiveTunnel=b;
    }
    
    static public boolean getIsTrainInTunnel() {
        return isTrainInTunnel;
    }


    // kisiklás ellenőrzése static változóval
    static public boolean getIsDerailing(){
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
        isTrainInTunnel = b;
    }
}
