package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Controls user interactions(clicks). Implements MouseListener interface.
 * @author i_did_iit team
 *
 */
public class Controller implements MouseListener, ActionListener, MenuListener{
	int x,y;
	public Game game;
	public static int mapLoaded = 0;
	public JMenuItem start;
	public Scene scene;

	/**
	 * Implements the mouse click on Start menu item
	 * @param e as an ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("START")){
			if (mapLoaded%2 == 1){
				Scene.f.dispose();
			}
			game.loadMap("gridmap_1");
			mapLoaded ++;
			start.setText("Restart");
			game.timer.start();
		}
		
	}

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Implements the mouse click on Start menu item
	 * @param arg0 as a MenuEvent
	 */
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

	/**
	 * The mouse click event handling in the gameboard
	 * @param e as a MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		String clickedElementName = Scene.getClickedElement(x, y);
		if(!clickedElementName.equals("")) {
			game.getMap().onMouseClickedEvent(clickedElementName);
			scene.revalidate();
			scene.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
