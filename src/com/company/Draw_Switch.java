package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for drawing Switch(es). Implements Drawable interface.
 * @author i_did_iit team
 *
 */
public class Draw_Switch implements Drawable {

    /**
     * Position of shape what need to draw.
     */
    public Point2D pos;
    /**
     * Name of Draw_Switch object.
     */
    public String name;
    /**
     * Switch object what need to draw.
     */
    private Switch mySwitch;
    
    /**
     * Constructor of Draw_Switch class. Sets mySwitch,name and pos attributes.
     * @param s
     * 			Switch object what need to draw.
     */
    public Draw_Switch(Switch s){
    	mySwitch = s;
        pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
        name = s.name;
    }

	/* 
	 * Overridden drawing function. Draws a Switch.
	 * @param g
	 * 			Graphics2D object
	 */
    @Override
    public void drawElement(Graphics2D g) {

    }

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
    @Override
    public String getName(){ return this.name; }
}