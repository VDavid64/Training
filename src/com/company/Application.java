package com.company;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

	/**
	 * Main function.
	 * @param args Program arguments.
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException, NullPointerException {

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

		Scene.InitScene(game);
		mapLoaded = true;

		
		try {
			int round = 0;
			while (true) {
				if (Scene.mapLoaded % 2 == 1) {
					game.generateTrain(round, false);
					game.moveTrains(1);
					round++;
					if (game.crashDetection()) {
						gameIsOn = false;
					}
				}
				if (Scene.mapLoaded % 2 == 0 && Scene.mapLoaded > 0) {
					round = 0;
					counter = 1;
					game = new Game();
					Scene.InitScene(game);
					game.loadMap("gridmap_1");
					Scene.mapLoaded = 1;
					Scene.f.getJMenuBar().getMenu(1).setText("Restart");
				}
				Thread.sleep(500);

			}
		}
		catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(new JFrame(),
					"You lost!.",
					"Game over!",
					JOptionPane.ERROR_MESSAGE);		}
	}

}
