package com.company;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Timer;

import javafx.event.ActionEvent;

/**
 * Responsible for the whole Application. Contains main function.
 * @author i_did_iit team
 *
 */
public class Application {
	/**
	 * Timer.
	 */
	static public Timer timer;
	/**
	 * Game object to play.
	 */
	static private Game game;
	
	/**
	 * gameIsOn whether game is on. Default value is true.
	 */
	static boolean gameIsOn = true; 
	
	/**
	 * Main function.
	 * @param args
	 * 				Program arguments.
	 * @throws InterruptedException
	 * @throws NullPointerException
	 */
	public static void main(String[] args) throws InterruptedException, NullPointerException {
		//Creating a new game.
		game = new Game(); 

		// Signals if player already won. 		 
		boolean gameHasWon = false;

		// Signals if the map is already loaded. 
		boolean mapLoaded = false;

		// Counter for trains. 		 
		int counter = 1; 

		Scene.InitScene(game);
		mapLoaded = true;
		
		/*try {
			round = 0;
			while (true) {
				if (Scene.mapLoaded % 2 == 1) {
					gameIsOn = game.step(round++);
				
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

			}
		}
		catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(new JFrame(),
					"You lost!",
					"Game over!",
					JOptionPane.ERROR_MESSAGE);		}*/
	}

}
