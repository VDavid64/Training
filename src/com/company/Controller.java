package com.company;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controls user interactions(clicks). Implements MouseListener interface.
 * @author i_did_iit team
 *
 */
public class Controller implements MouseListener{
	int x,y;

	/* 
	 * Overridden function. Invoked when the mouse button has been clicked (pressed and released) on a component.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
	}

	/* 
	 * Overridden function. Invoked when the mouse enters a component.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * Overridden function. Invoked when the mouse exits a component.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * Overridden function. Invoked when a mouse button has been pressed on a component.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* 
	 * Overridden function. Invoked when a mouse button has been released on a component.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
