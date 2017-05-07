package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for drawing Cargo(s). Implements Drawable interface.
 * @author i_did_iit team
 *
 */
public class Draw_Cargo implements Drawable {

    /**
     * Position of shape what need to draw.
     */
    public Point2D pos;
    /**
     * Name of Draw_Cargo object.
     */
    public String name;
    /**
     * Cargo object what need to draw.
     */
    private Cargo myCargo;

    /**
     * Constructor of Draw_Cargo class. Sets myCargo,name and pos attributes.
     * @param c
     * 			Cargo object what need to draw.
     */
    public Draw_Cargo(Cargo c){
        pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
        myCargo = c;
        name = c.name;
    }
    
	/* 
	 * Overridden drawing function. Draws a Cargo.
	 * @param g
	 * 			Graphics2D object
	 */
    @Override
    public void drawElement(Graphics2D g) {
//    	int x = (int)pos.getX();
//  	    int y = (int)(pos.getY());
//  	    g.setColor(java.awt.Color.GRAY);
//  	    g.fillRect(x,y, 40, 20);
    }

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
    @Override
    public String getName(){ return this.name; }
}