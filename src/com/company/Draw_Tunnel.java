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
        pos = t.pos;
        name = t.name;
    }
	/* 
	 * Overridden drawing function. Draws a Tunnel point.
	 * @param g
	 * 			Graphics2D object
	 */
    @Override
    public void drawElement(Graphics2D g) {
    	//csak a beolvasás kipróbáláshoz
	    int x = (int)pos.getX();
	    int y = (int)(pos.getY());
	    g.setColor(java.awt.Color.blue);
	    g.fillRect(x,y, 10, 40);
//    	if(myTunnel.isActive()) {
//            // rajz ha true
//        } else {
//            //ha false
//        }

    }

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
    @Override
    public String getName(){ return this.name; }
}