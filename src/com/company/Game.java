package com.company;

import java.util.*;

/**
 * Class responsible for running the game.
 *
 * @author i_did_iit team
 */
public class Game {

	/**
	 * Variables for storing the current map, if it is the last game, and the
	 * list of the engines of trains.
	 */
	private Map map;
	private boolean isLastGame;
	private ArrayList<Engine> engines = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public Game() {
		isLastGame = false;
		engines.add(new Engine(new Rail(), 2));
	}

	/**
	 * Function responsible for returning whether we are in the last game or
	 * not.
	 * 
	 * @return: returns true if it is the last game, else false.
	 */
	public boolean getIsLastGame() {
		return isLastGame;
	}

	/**
	 * Function responsible for loading the map.
	 * 
	 * @param mapNumber:
	 *            the number of the map to be loaded.
	 */
	public void loadMap(int mapNumber) {
		map = new Map(mapNumber);
	}

	/**
	 * Function responsible for generating the trains.
	 */
	public boolean generateTrain() throws InputMismatchException{
		System.out.println("    -> [Game].generateTrain()");
		
		new Map(0).getStartPositions();

		for (Engine e : engines) {
			e.getActPos();
			Car car = e.getFirstCar();
			while (car != null) {
				car.getActPos();
				car = car.getNextCar();
			}

		}
		
		System.out.println("4.1. Foglalt a kezd�pont?");
		String command;
		Scanner input = new Scanner(System.in);
		command = input.nextLine();
		if (!command.equals("I") && !command.equals("N")) throw new InputMismatchException();
		System.out.println("    <- [Game].generateTrain():bool");
		
		return input.equals("N");
	}

	/**
	 * Function responsible for setting whether the current game is the last or
	 * not.
	 * 
	 * @param lastGame:
	 *            boolean determining if it is the last game or not.
	 */
	public void setIsLastGame(boolean lastGame) {
		isLastGame = lastGame;
	}

	/**
	 * Function responsible for moving the trains. Iterates through the stored
	 * engines.
	 */
	public void moveTrains() {
		System.out.println("    -> [Game].moveTrains()");
		engines.get(0).move();

		System.out.println("    <- [Game].moveTrains()");

	}

	/**
	 * Function that determines if we won, by determining if all the cars in our
	 * train is empty.
	 */
	public void isWon() {

		System.out.println("    -> [Game].isWon()");

		System.out.println("8.1: Utolsó pályán vagyunk? ");

		/**
		 * Handling printout.
		 */
		String command;
		Scanner input = new Scanner(System.in);
		command = input.nextLine();

		if (command.equals("I"))
			if (engines.get(0).getFirstNotEmptyCar(8) != null) {
				System.out.println("    <-[Game].isWon(): false");
			} else {
				System.out.println("    <-[Game].isWon(): true");
			}

		else if (!command.equals("N")) {
			throw new IllegalArgumentException();
		} else
			System.out.println("    <-[Game].isWon(): false");

	}

	/**
	 * Function responsible for deleting trains.
	 */
	public void deleteTrains() {
		engines.clear();
	}

	/**
	 * Function responsible of detecting crashes, or if we reached the edge of
	 * the map, or derailed.
	 * 
	 * @return
	 */
	public boolean crashDetection() {

		System.out.println("    -> [Game].crashDetection()");

		/**
		 * If we derailed.
		 */
		if (Map.getIsDerailing()) {
			System.out.println("    <- [Game].crashDetection(true)");
			return true;
		}

		/**
		 * If we crashed.
		 */
		engines.get(0).getActPos();
		System.out.println("7.2: Két vonat összeütközött? ");
		String command;
		Scanner input = new Scanner(System.in);
		command = input.nextLine();

		if (command.equals("I")) {
			System.out.println("    <-[Game].crashDetection(true)");
			return true;
		} else if (!command.equals("N")) {
			throw new IllegalArgumentException();
		}

		/**
		 * If we reached a starting position, which means we reached the edge of
		 * the map.
		 */
		else {
			map.getStartPositions();
			System.out.println("7.3: A terepasztal szélére jutottunk? ");
			command = input.nextLine();
			if (command.equals("I")) {
				System.out.println("    <-[Game].crashDetection(true)");
				return true;
			} else if (!command.equals("N")) {
				throw new IllegalArgumentException();
			} else {
				System.out.println("    <-[Game].crashDetection(false)");
				return false;
			}
		}

	}

	/**
	 * Function responsible for emptying cars.
	 */
	public void emptyCars() {
		String command;
		Scanner input = new Scanner(System.in);
		System.out.println("    -> [Game].emptyCars()");

		/**
		 * For each train.
		 */
		for (Engine e : engines) {
			Car car = e.getFirstNotEmptyCar(6);
			System.out.println("6.1: Allomáson van a vonat első nem üres kocsija?");
			command = input.nextLine();
			if (command.equals("I")) {
				Rail pos = car.getActPos();
				car.getColor();
				System.out.println("6.1.1: Azonos színük?");
				String command2 = input.nextLine();
				if (command2.equals("I")) {
					car.setEmpty();
					System.out.println("    <-[Game].emptyCars()");
				} else if (!command2.equals("N")) {
					throw new IllegalArgumentException();
				} else {
					System.out.println("    <-[Game].emptyCars()");
				}
			} else if (!command.equals("N")) {
				throw new IllegalArgumentException();
			} else {
				System.out.println("    <-[Game].emptyCars()");
			}
		}

		// Car car = e.getFirstNotEmptyCar();
		/*
		 * // az első nem üres kocsi és állomáson vagyunk if (car != null )
		 * {
		 * 
		 * // ha egyezik a szín, kiürítjük a kocsit if ( ((Station)
		 * car.getActPos()).getColor() == car.getColor()) car.setEmpty(); }
		 */

	}

	/**
	 * Function responsible for determining whether if there is a train in the
	 * tunnel or not.
	 * 
	 * @param seq
	 * @return: returns true, if there is a train in the tunnel.
	 * @throws InputMismatchException
	 */
	public static boolean getIsTrainInTunnel(int seq) throws InputMismatchException {
		if (seq == 3) {
			System.out.println("        -> [Game].IsTrainInTunnel()");
			System.out.println("3.3 Üres az alagút?");
			Scanner input = new Scanner(System.in);
			String command = input.nextLine();
			if (command.equals("I")) {
				System.out.println("        <- [Game].IsTrainInTunnel(): boolean");
				return false;
			} else if (command.equals("N")) {
				System.out.println("        <- [Game].IsTrainInTunnel(): boolean");
				return true;
			} else
				throw new InputMismatchException();
		} else
			throw new RuntimeException("Unreachable line");
	}
}
