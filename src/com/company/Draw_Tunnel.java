package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for drawing Tunnel(s). Implements Drawable interface.
 * @author i_did_iit team
 *
 */
public class Draw_Tunnel implements Drawable {

    /**
     * Position of shape what need to draw.
     */
    public Point2D pos;
    /**
     * Name of Draw_Tunnel object.
     */
    public String name;
    /**
     * Tunnel object what need to draw.
     */
    private Tunnel myTunnel;
    
    /**
     * Constructor of Draw_Tunnel class. Sets myTunnel,name and pos attributes.
     * @param t
     * 			Tunnel object what need to draw.
     */
    public Draw_Tunnel(Tunnel t){
    	myTunnel = t;
        pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
        name = t.name;
    }
	/* 
	 * Overridden drawing function. Draws a Tunnel point.
	 * @param g
	 * 			Graphics2D object
	 */
    @Override
    public void drawElement(Graphics2D g) {
        if(myTunnel.isActive()) {
            // rajz ha true
        } else {
            //ha false
        }

    }

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
    @Override
    public String getName(){ return this.name; }
}