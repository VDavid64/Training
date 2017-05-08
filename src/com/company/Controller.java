package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Controls user interactions(clicks). Implements MouseListener interface.
 * @author i_did_iit team
 *
 */
public class Controller implements ActionListener, MenuListener{
	int x,y;
	public Game game;
	public static int mapLoaded = 0;
	public JMenu start;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuSelected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		if (mapLoaded%2 == 1){
			Scene.f.dispose();
		}
		game.loadMap("gridmap_1");
		mapLoaded ++;
		start.setText("Restart");
		game.timer.start();
		
	}

}
