package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for drawing Station(s). Implements Drawable interface.
 * @author i_did_iit team
 *
 */
public class Draw_Station implements Drawable {

    /**
     * Position of shape what need to draw.
     */
    public Point2D pos;
    /**
     * Name of Draw_Station object.
     */
    public String name;
    /**
     * Station object what need to draw.
     */
    private Station myStation;

    /**
     * Constructor of Draw_Station class. Sets myStation,name and pos attributes.
     * @param s
     * 			Station object what need to draw.
     */
    public Draw_Station(Station s){
    	myStation = s;
		pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
		name = s.name;
    }
    
	/* 
	 * Overridden drawing function. Draws a Station next to Rail.
	 * @param g
	 * 			Graphics2D object
	 */
    @Override
    public void drawElement(Graphics2D g) {
//	    int x = (int)pos.getX();
//	    int y = (int)(pos.getY());
//	    g.setColor(java.awt.Color.black);
//	    g.fillRect(x,y, 40, 10);
//	    java.awt.Color color = java.awt.Color.getColor(myStation.getColor().toString());
//	    g.setColor(color);
//	    g.fillRect(x,y-15, 40, 10);
    }

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
	@Override
	public String getName(){ return this.name; }
}