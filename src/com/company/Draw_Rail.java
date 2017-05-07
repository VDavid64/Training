package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for drawing Rail(s). Implements Drawable interface.
 * @author i_did_iit team
 *
 */
public class Draw_Rail implements Drawable {

    /**
     * Position of shape what need to draw.
     */
    public Point2D pos;
    /**
     * Name of Draw_Rail object.
     */
    public String name;
    /**
     * Rail object what need to draw.
     */
    private Rail myRail;

    /**
     * Constructor of Draw_Rail class. Sets myRail,name and pos attributes.
     * @param r
     * 			Rail object what need to draw.
     */
    public Draw_Rail(Rail r){
    	pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
    	myRail = r;
    	name = r.name;
    }
	/* 
	 * Overridden drawing function. Draws a Rail.
	 * @param g
	 * 			Graphics2D object
	 */
    @Override
    public void drawElement(Graphics2D g) {
//	    int x = (int)pos.getX();
//	    int y = (int)(pos.getY());
//	    g.setColor(java.awt.Color.green);
//	    g.fillRect(x,y, 5, 20);
	}

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
	@Override
	public String getName(){ return this.name; }
}