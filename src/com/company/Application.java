package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

	/**
	 * Defines the source of commands. False if console, true if text file. Default value is false.
	 */
	public static boolean inputMethod = false;
	/**
	 * Output for writing to file. Default value is null.
	 */
	public static BufferedWriter output = null;
	/**
	 * Switch of randomization. Default value is false.
	 */
	public static boolean random = false; 

	/**
	 * Main function.
	 * @param args Program arguments.
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		/**
		 * Creating a new game.
		 */
		Game game = new Game(); 

		/**
		 * Signals if the game is on. 
		 */
		boolean gameIsOn = true; 

		/**
		 * Signals if someone already won. 
		 */
		boolean gameHasWon = false;

		/**
		 * Signals if the map is already loaded. 
		 */
		boolean mapLoaded = false;

		/**
		 * Counter for trains. 
		 */
		int counter = 1; 

		/**
		 * String containing the input file name
		 * if it is provided.
		 */
		final String inputFileName; 

		/**
		 * String containing the output file name
		 * if it is provided.
		 */
		final String outputFileName; 
		List<String> commands = new ArrayList<>();

		Scene.InitScene(game);

		// Control input/output method
		if (args.length != 0) { // if args is not empty, we check the two
								// parameters if valid
			try {
				inputMethod = true;
				inputFileName = args[0]; // Get the filenames from args
				outputFileName = args[1]; // if the program is called with less
											// than two parameters we get error
				String[] partsInput = inputFileName.split("\\."); // Checking
																	// filetype
				String[] partsOutput = outputFileName.split("\\.");
				if (!partsInput[1].equals("txt") || !partsOutput[1].equals("txt"))
					throw new IllegalArgumentException();

				// Load commands from file into a String array
				String path = System.getProperty("user.dir");

				// If running with args should work as stated in docs, leave this in
				// this clips the last dir from the path (aka the \src dir, since the test isn't at \src\test )
				path = path.substring(0, path.lastIndexOf("\\"));

				commands = Files.readAllLines(Paths.get(path + "\\test\\" + inputFileName), StandardCharsets.UTF_8);

				// Create output file
				File file = new File(path + "\\test\\" + outputFileName);
				System.setOut(new PrintStream(file));
			}
			// Error for not valid filetype
			catch (IllegalArgumentException ie) {
				ie.printStackTrace();
			}
			// Error for not valid number of arguments
			catch (ArrayIndexOutOfBoundsException ae) {
				ae.printStackTrace();
			}
			// Error for file actions
			catch (IOException e) {
				e.printStackTrace();
			}
		} else { // if args is empty, the program will read the commands from
					// the console
			inputMethod = false;
		}

		if (inputMethod == false) {
			//////////////////////////////////////////////////////////
			// Welcoming text for console:
			System.out.println();
			System.out.println("-------------------------------");
			System.out.println("Start of the Program.");
			System.out.println("First, use \"LoadMap map_name\" to load a map from XML file");
			System.out.println(
					"Then use command {Step, SetRandom, SetTunnel, SetSwitch, TunnelState, StationState, ListEngine, ListTrains},");
			System.out.println("to manipulate the program.");
			System.out.println("See command \"Help\" to list the valid commands and their required parameters.");
			System.out.println("-------------------------------");
		}

		// variables for processing the commands
		String command;
		String[] inputArray;
		int command_counter = 0;

		// main cycle
		while(gameHasWon != true) {
		}

		/*
		while (true) {

			// Processing the commands
			try {
				if (inputMethod == false) {

					// Read command from console
					Scanner scanInput = new Scanner(System.in);
					String inputString = scanInput.nextLine();
					inputArray = inputString.split(" ");
					if (inputArray[0].equals("") || inputArray.length > 2) {
						System.out
								.println("Format must be: \"command param(opt)\". See \"Help\" for list of commands.");
						continue;
					}
					command = inputArray[0].toUpperCase();
				}

				else {

					// Read command from text (commands list)
					if (command_counter < commands.size()) {
						command = commands.get(command_counter);
						inputArray = command.split(" ");

						if (inputArray[0].equals("") || inputArray.length > 2) {
							System.out.println(
									"Format must be: \"command param(opt)\". See \"Help\" for list of commands.");
							continue;
						}
						command = inputArray[0].toUpperCase();
						command_counter++;
					} else
						return;
				}

				switch (command) {

				case ("LOADMAP"):
					try {
						String param = inputArray[1];
						game.loadMap(param);
						mapLoaded = true;
					} catch (Exception e) {
						System.out.println("Usage of LoadMap command: \"LoadMap map_name\" ");
					}
					break;

				case ("STEP"):
					try {
						String param = inputArray[1];
						int round = Integer.parseInt(param);
						if (round < 1 || round > 500)
							throw new IllegalArgumentException();
						if (!mapLoaded)
							throw new IllegalStateException();

						while (round != 0 && gameIsOn) {

							System.out.println("-------------------------------");
							System.out.println("<round: " + counter + ">");

							game.moveTrains(counter);
							game.generateTrain(counter, random);
							game.emptyCars(counter);

							if (game.crashDetection()) {
								gameIsOn = false;
							}
							if (game.isWon() && (counter >= 5)) {
								gameIsOn = false;
								gameHasWon = true;
							}

							counter++;
							round--;
						}
					} catch (IllegalStateException i) {
						i.printStackTrace();
						System.out.println("You need to load a map first. Use \"LoadMap\" command!");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(
								"Usage of Step command: \"Step round_number\". Round must be a number between 1 and 500");
					}

					// ha a játék véget ért: resetelünk, hogy újra tudjunk
					// indítani egy pályát, programból való kilépés nélkül
					if (!gameIsOn) {

						if (gameHasWon)
							System.out.println("<Congratulations! You Won!>");
						else
							System.out.println("<Game over!>");

						System.out.println("-------------------------------");

						mapLoaded = false;
						game.deleteTrains();
						counter = 1;
						gameIsOn = true;
						gameHasWon = false;
					}
					break;

				case ("SETSWITCH"):
				case ("SETTUNNEL"):
					try {
						String param = inputArray[1];
						if (!mapLoaded)
							throw new IllegalStateException();
						game.onClicked(param);
					} catch (IllegalArgumentException i) {
						System.out.println("There is no switch/tunnel by that name");
					} catch (IllegalStateException i) {
						i.printStackTrace();
						System.out.println("You need to load a map first. Use \"LoadMap\" command!");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(
								"Usage of SetSwitch/SetTunnel command: \"SetSwitch switch_name\" or \"SetTunnel tunnel_name\"");
					}
					break;

				case ("EXIT"):

					System.out.println("<Exit>");
					System.exit(0);

				case ("STATIONSTATE"):
					try {
						String param = inputArray[1];
						if (!mapLoaded)
							throw new IllegalStateException();
						game.printStationData(param);
					} catch (IllegalArgumentException i) {
						System.out.println("There is no station by that name");
					} catch (IllegalStateException i) {
						i.printStackTrace();
						System.out.println("You need to load a map first. Use \"LoadMap\" command!");
					} catch (Exception e) {
						System.out.println("Usage of StationState command: \"StationState station_name\" ");
					}
					break;

				case ("TUNNELSTATE"):
					try {
						if (!mapLoaded)
							throw new IllegalStateException();
						game.printTunnelState();
					} catch (IllegalStateException i) {
						i.printStackTrace();
						System.out.println("You need to load a map first. Use \"LoadMap\" command!");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Usage of TunnelState command: \"TunnelState\" ");
					}
					break;

				case ("LISTENGINE"):
					try {
						String param = inputArray[1];
						if (!mapLoaded)
							throw new IllegalStateException();
						game.listEngine(param);
					} catch (IllegalArgumentException i) {
						System.out.println("There is no engine by that name");
					} catch (IllegalStateException i) {
						i.printStackTrace();
						System.out.println("You need to load a map first. Use \"LoadMap\" command!");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Usage of ListEngine command: \"ListEngine engine_name\" ");
					}
					break;

				case ("LISTTRAINS"):
					try {
						if (!mapLoaded)
							throw new IllegalStateException();
						game.listTrains();
					} catch (IllegalStateException i) {
						System.out.println("You need to load a map first. Use \"LoadMap\" command!");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Usage of ListEngine command: \"ListEngine engine_name\" ");
					}
					break;

				case ("SETRANDOM"):
					try {
						if (mapLoaded)
							throw new IllegalStateException();
						random = !random;

						System.out.println("<Random " + (random ? "on" : "off") + ">");

					} catch (IllegalStateException i) {
						System.out.println("You can not set random after loading the map.");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Usage of SetRandom command: \"SetRandom\" ");
					}
					break;

				case ("HELP"):
					System.out.println("Available commands: \n");
					System.out.println("    LoadMap map_name                        Loads map from \\map.*.xml");
					System.out.println("    Step number_of_steps                    Must be between 1 and 500");
					System.out.println("    SetSwitch switch_name                   Change the direction of a switch");
					System.out.println("    StationState station_name               List data of given station");
					System.out.println("    ListTrains               				List the trains in the game");
					System.out.println(
							"    ListEngine engine_name                  List all information about an engine");
					System.out.println(
							"    TunnelState                             Print the state of the tunnel and active tunnel positions");
					System.out.println("    SetTunnel tunnel_name                   Set tunnel position activity");
					System.out.println(
							"    SetRandom                               Set the random generations of cars, stations, etc. Editable before loading the map!");
					System.out.println("    Exit               						Exit the program");
					System.out.println("-------------------------------");
					break;

				default:
					System.out.println(
							"Invalid command. Format must be: \"command param(opt)\". See \"Help\" for list of commands.");
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
	}
}
