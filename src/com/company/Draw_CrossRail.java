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

        pos = c.pos;
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
    	
    	//csak a beolvasás kipróbáláshoz
	    int x = (int)pos.getX();
	    int y = (int)(pos.getY());
	    g.setColor(java.awt.Color.red);
	    g.fillRect(x,y, 10, 40);
    }

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
    @Override
    public String getName(){ return this.name; }
}