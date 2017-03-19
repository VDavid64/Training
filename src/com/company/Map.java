package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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
	private ArrayList<Rail> startPositions = new ArrayList<>();
	/**
	 * Positions where the player can build tunnel.
	 */
	private ArrayList<Tunnel> tunnelPositions = new ArrayList<>();
	/**
	 * Positions where passengers can get off.
	 */
	private ArrayList<Station> stations = new ArrayList<>();

	/**
	 * isActiveTunnel whether there is an active tunnel. ,
	 */
	private static boolean isActiveTunnel;
	/**
	 * isTrainInTunnel whether there is a train in tunnel.
	 */
	private static boolean isTrainInTunnel;
	/**
	 * isDerailing whether two parts of any train is on the same rail.
	 */
	private static boolean isDerailing;
	/**
	 * Positions where tunnel point is active.
	 */
	private ArrayList<Tunnel> activeTunnelPositions = new ArrayList<>();

	/**
	 * Getter of start positions. Returns with ArrayList of Rail objects.
	 * 
	 * @return The positions where trains can start moving.
	 */
	public ArrayList<Rail> getStartPositions() {
		System.out.println("        -> [Map].getStartPositions()");

		System.out.println("        <- [Map].getStartPositions(List<Rails>)");

		return startPositions;
	}

	/**
	 * Getter of stations. Return with ArrayList of Station objects.
	 * 
	 * @return The positions of stations where passengers can get off.
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
	 * Constructs a map and load a field from file.
	 * 
	 * @param mapNumber
	 *            the number of fields to be loaded
	 */
	public Map(int mapNumber) {
		isActiveTunnel = false;
		// mapNumbertől függően töltjük be az adott pályát
		if (mapNumber == 1) {

		} else {

		}
	}

	/**
	 * Control of a tunnel point.
	 * 
	 * @param t
	 *            Tunnel point what map controls after user modified it.
	 * @param seq
	 *            Number of sequence where we call this function. It is
	 *            important because of tabulators.
	 * @throws InputMismatchException
	 *             If an invalid input is occurred.
	 */
	public void controlTunnel(Tunnel t, int seq) throws InputMismatchException {
		System.out.println("	-> [Map].controlTunnel(t)");
		// 1: alagút építése
		if (seq == 1) {
			System.out.println("2.1 Van már megépült alagút? (I / N)");
			System.out.println("		-> [Map].getisActiveTunnel()");
			Scanner input = new Scanner(System.in);
			String command = input.nextLine();
			// van alagút, szóval alagút törlése következik
			if (command.equals("I")) {
				System.out.println("		<- [Map].getisActiveTunnel():true");
			}
			// nincs alagút, további kérdések
			else if (command.equals("N")) {
				System.out.println("		<- [Map].getisActiveTunnel():false");
				System.out.println("2.2 Inaktív alagútszájra kattintottunk? (I/N)");
				System.out.println("			-> [Tunnel].isActive()");
				command = input.nextLine();
				// inaktív az alagútszáj, további kérdés
				if (command.equals("I")) {
					System.out.println("			<- [Tunnel].isActive():false");
					System.out.println("2.2.1 Van már egy aktív alagútszáj? (I/N)");
					System.out.println("					-> [Map].activeTunnelPositions.isEmpty()");
					command = input.nextLine();
					// van már aktív alagútszáj, további kérdés
					if (command.equals("I")) {
						System.out.println("					<- [Map].activeTunnelPositions.isEmpty():false");
						System.out.println("2.2.1.1. Folytonos a két pont közötti sínszakasz? (I/N");
						System.out.println(
								"						-> [Map]. checkList(t, [Map].activeTunnelPositions.get(0))");
						command = input.nextLine();
						// bejárható a sínszakasz, szóval alagutat építünk
						if (command.equals("I")) {
							System.out.println(
									"						<- [Map]. checkList(t, [Map].activeTunnelPositions.get(0)):true");
							t.setActive(true, 1);
							System.out.println("							-> [Map].activeTunnelPositions.add(t)");
							System.out.println("							<- [Map].activeTunnelPositions.add(t)");
							System.out.println("							-> setIsActiveTunnel(true)");
							setIsActiveTunnel(true);

						}
						// nem járható be a sínszakasz, nem történik semmi
						else if (command.equals("N")) {
							System.out.println(
									"						<- [Map]. checkList(t, [Map].activeTunnelPositions.get(0)):false");
						} else if (!command.equals("N"))
							throw new InputMismatchException();
					}
					// alagútpont aktiválása
					else if (command.equals("N")) {
						System.out.println("					<- [Map].activeTunnelPositions.isEmpty():true");
						t.setActive(true, 2);
						System.out.println("						-> [Map].activeTunnelPositions.add(t)");
						System.out.println("						<- [Map].activeTunnelPositions.add(t)");
					} else if (!command.equals("N"))
						throw new InputMismatchException();
				}
				// aktív az alagútszáj, szóval lebontjuk
				else if (command.equals("N")) {
					System.out.println("			<- [Tunnel].isActive():true");
					t.setActive(false, 4);
					this.activeTunnelPositions.remove(t);
					System.out.println("				->  [Map].activeTunnelPositions.remove(t)");
					System.out.println("				<-  [Map].activeTunnelPositions.remove(t)");
				} else if (!command.equals("N"))
					throw new InputMismatchException();
			} else if (!command.equals("N"))
				throw new InputMismatchException();
		}
		// 3: alagút törlése
		if (seq == 3) {

			System.out.println("3.1 Van megépült alagút?");
			Scanner input = new Scanner(System.in);
			String command = input.nextLine();

			// ha van alagút, vizsgáljuk a további feltételeket
			if (command.equals("I")) {
				System.out.println("3.2 Aktív alagútszájra kattintottunk?");
				command = input.nextLine();
				// ha aktív alagútszájra kattintottunk és nincs bent vonat,
				// akkor törölhető az alagútszáj
				if (command.equals("I") && !Game.getIsTrainInTunnel(3)) {
					t.setActive(false, 3);
				} else if (!command.equals("N"))
					throw new InputMismatchException();
			} else if (!command.equals("N"))
				throw new InputMismatchException();
		}
		System.out.println("	<- [Map].controlTunnel(t)");
		return;

	}

	/**
	 * Check the route between two tunnel points. Returns the result of
	 * check.True weather checking was successful.
	 * 
	 * @param startPos
	 *            Tunnel point where checking starts
	 * @param tunnel
	 *            Tunnel point where need to arrive.
	 * @return Result of check.
	 */
	private boolean checkList(Tunnel startPos, Tunnel tunnel) {

		return false;
	}

	// // TODO
	// // felhasználó interakcióját megvalósító függvény
	// // külön kezelei az esetek attól függően, hogy mire kattintott
	// public void onMouseClickedEvent() {
	// // ha tunnel-re, a controlTunnel() hívódik meg
	// // ha váltóra, akkor annak az állítása történik meg
	// }

	/**
	 * Getter of isActiveTunnel. Returns true whether there is an active tunnel.
	 * 
	 * @return isActiveTunnel
	 */
	static public boolean getIsActiveTunnel() {
		return isActiveTunnel;
	}

	/**
	 * Setter of isActiveTunnel.
	 * 
	 * @param b
	 *            True whether there is an active tunnel.
	 */
	static public void setIsActiveTunnel(boolean b) {
		System.out.println("							<- setIsActiveTunnel()");
		isActiveTunnel = b;
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
	 * Getter of isDerailing. Returns true if whether two parts of any train is
	 * on the same rail.
	 * 
	 * @return True whether there is a derailing.
	 */
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

}
