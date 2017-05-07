package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for drawing CrossRail(s). Implements Drawable interface.
 * @author i_did_iit team
 *
 */
public class Draw_CrossRail implements Drawable {

    /**
     * Position of shape what need to draw.
     */
    public Point2D pos;
    /**
     * Name of Draw_CrossRail object.
     */
    public String name;
    /**
     * CrossRail object what need to draw.
     */
    private CrossRail myCrossRail;
    
    /**
     * Constructor of Draw_CrossRail class. Sets myCrossRail,name and pos attributes.
     * @param c
     * 			CrossRail object what need to draw.
     */
    public Draw_CrossRail(CrossRail c){

        pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
        myCrossRail = c;
        name = c.name;
    }

	/* 
	 * Overridden drawing function. Draws a CrossRail.
	 * @param g
	 * 			Graphics2D object
	 */
    @Override
    public void drawElement(Graphics2D g) {
    	int x = (int)pos.getX();
 	    int y = (int)(pos.getY());
    	g.fillRect(64*x+17, 64*y, 30, 64); //függőleges
    	g.fillRect(x*64,y*64+17, 64, 30); // vízszintes
    	

    }

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
    @Override
    public String getName(){ return this.name; }
}