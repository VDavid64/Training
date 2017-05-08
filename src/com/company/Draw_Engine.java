package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for drawing Engine(s). Implements Drawable interface.
 * 
 * @author i_did_iit team
 *
 */
public class Draw_Engine implements Drawable {

	/**
	 * Position of shape what need to draw.
	 */
	public Point2D pos;
	/**
	 * Name of Draw_Engine object.
	 */
	public String name;
	/**
	 * Engine object what need to draw.
	 */
	private Engine myEngine;
	private Rail myRail;

	/**
	 * Constructor of Draw_Engine class. Sets myEngine,name and pos attributes.
	 * 
	 * @param e
	 *            Engine object what need to draw.
	 */
	public Draw_Engine(Engine e) {
		if (e.actPos != null) {
			pos = new Point((int) e.actPos.pos.getX(), (int) e.actPos.pos.getY());
		} else
			pos = null;
		myEngine = e;
		name = e.name;
		myRail = e.actPos;
	}

	/*
	 * Overridden drawing function. Draws an Engine.
	 * 
	 * @param g Graphics2D object
	 */
	@Override
	public void drawElement(Graphics2D g) {
		//if (!myEngine.isVisible) return;
		if (this.myEngine.actPos != null) {
		    int x = (int)pos.getX();
		    int y = (int)(pos.getY());
		    g.setColor(java.awt.Color.white);
		    if (myRail.nextRail != null && myRail.prevRail != null){
		    if ((myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y+1)
		    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
		    		||
		    		(myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y+1)
		    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1))
		    { //függőleges téglalap
		    	g.fillRect( x*64+17+15-8, y*64+32-18, 16, 37);
		        
		    }
		    //eszakkelet
		    else if ((myRail.nextRail.pos.getX() == x+1 && myRail.nextRail.pos.getY() == y)
		    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
		    		||
		    		(myRail.prevRail.pos.getX() == x+1 && myRail.prevRail.pos.getY() == y)
		    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1))
		    		{
		    	Graphics2D copy = (Graphics2D) g.create();
		    	Rectangle rect2 = new Rectangle(64*x+17+20, 64*y, 37, 16);     
		        copy.rotate(Math.toRadians(45), 64*x+17+20, 64*y);
		        copy.fill(rect2);
		        copy.dispose();
		    		}
		    //ESZAKNYUGAT
		    else if ((myRail.nextRail.pos.getX() == x-1 && myRail.nextRail.pos.getY() == y)
		    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
		    		||
		    		(myRail.prevRail.pos.getX() == x-1 && myRail.prevRail.pos.getY() == y)
		    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1)){
		    	Graphics2D copy = (Graphics2D) g.create();
		    	Rectangle rect2 = new Rectangle(64*x, 64*y+17+10, 37, 16);     
		        copy.rotate(Math.toRadians(-45), 64*x, 64*y+17+10);
		        copy.fill(rect2);
		        copy.dispose();
		    		}
		    //delkelet
		    else if ((myRail.nextRail.pos.getX() == x+1 && myRail.nextRail.pos.getY() == y)
	    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y+1)
	    		||
	    		(myRail.prevRail.pos.getX() == x+1 && myRail.prevRail.pos.getY() == y)
	    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y+1)){
		    	Graphics2D copy = (Graphics2D) g.create();
		    	Rectangle rect2 = new Rectangle(64*x+64, 64*y+17+30-10, 37, 16);     
		        copy.rotate(Math.toRadians(135), 64*x+64, 64*y+17+30-10);
		        copy.fill(rect2);
		        copy.dispose();
		    }
		    //DELNYUGAT
		    else if ((myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y+1)
		    		&& (myRail.prevRail.pos.getX() == x-1 && myRail.prevRail.pos.getY() == y)
		    		||
		    		(myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y+1)
		    		&& (myRail.nextRail.pos.getX() == x-1 && myRail.nextRail.pos.getY() == y)){
		    	Graphics2D copy = (Graphics2D) g.create();
		    	Rectangle rect2 = new Rectangle(64*x, 64*y+17+30-10, 16, 37);     
		        copy.rotate(Math.toRadians(-45), 64*x, 64*y+17+30-10);
		        copy.fill(rect2);
		        copy.dispose();
		    }
		    
		    else {
		    	g.fillRect(x*64+32-18, y*64+17+15-8, 37, 16); //vízszintes téglalap
		    }
			}
			}
	}

	/**
	 * Overridden getter of drawable element's name.
	 * 
	 * @return Name of drawable element.
	 */
	@Override
	public String getName() {
		return this.name;
	}
}
