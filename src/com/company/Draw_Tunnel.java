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
    	 int x = (int)pos.getX();
   	    int y = (int)(pos.getY());
   	    g.setColor(java.awt.Color.black);
   	    if (myTunnel.nextRail != null && myTunnel.prevRail != null){
   	    if ((myTunnel.nextRail.pos.getX() == x && myTunnel.nextRail.pos.getY() == y+1)
   	    		&& (myTunnel.prevRail.pos.getX() == x && myTunnel.prevRail.pos.getY() == y-1)
   	    		||
   	    		(myTunnel.prevRail.pos.getX() == x && myTunnel.prevRail.pos.getY() == y+1)
   	    		&& (myTunnel.nextRail.pos.getX() == x && myTunnel.nextRail.pos.getY() == y-1))
   	    { //függőleges téglalap
   	    	g.fillRect(64*x+17, 64*y, 30, 64);
   	    	g.setColor(myTunnel.isActive() ? java.awt.Color.blue : java.awt.Color.orange);
   	    	g.fillOval(x*64+32-13, y*64+32-13, 26, 26);
   	    }
   	    else if ((myTunnel.nextRail.pos.getX() == x+1 && myTunnel.nextRail.pos.getY() == y)
   	    		&& (myTunnel.prevRail.pos.getX() == x && myTunnel.prevRail.pos.getY() == y-1)
   	    		||
   	    		(myTunnel.prevRail.pos.getX() == x+1 && myTunnel.prevRail.pos.getY() == y)
   	    		&& (myTunnel.nextRail.pos.getX() == x && myTunnel.nextRail.pos.getY() == y-1))
   	    		{
   	    			// draw GeneralPath (polygon)
   	    			int x1Points[] = {x*64+17, x*64+17+30, x*64+64, x*64+64, x*64+64-12, x*64+17};
   	    			int y1Points[] = {y*64+0, y*64+0, y*64+17, y*64+17+30, y*64+17+30, y*64+12};
   	    			g.setColor(java.awt.Color.black);
   	    			g.fillPolygon(x1Points, y1Points, 6);
   	    			g.setColor(myTunnel.isActive() ? java.awt.Color.blue : java.awt.Color.orange);
   	    			g.fillOval(x*64+32+16-10, y*64+32-16-10, 26, 26);
   	    		}
   	    //ESZAKNYUGAT
   	    else if ((myTunnel.nextRail.pos.getX() == x-1 && myTunnel.nextRail.pos.getY() == y)
   	    		&& (myTunnel.prevRail.pos.getX() == x && myTunnel.prevRail.pos.getY() == y-1)
   	    		||
   	    		(myTunnel.prevRail.pos.getX() == x-1 && myTunnel.prevRail.pos.getY() == y)
   	    		&& (myTunnel.nextRail.pos.getX() == x && myTunnel.nextRail.pos.getY() == y-1)){
   	    			int x1Points[] = {x*64+17, x*64+17+30, x*64+17+30, x*64+12, x*64+0, x*64+0};
   	    			int y1Points[] = {y*64+0, y*64+0, y*64+12, y*64+17+30, y*64+17+30, y*64+17};
   	    			g.setColor(java.awt.Color.black);
   	    			g.fillPolygon(x1Points, y1Points, 6);
   	    			g.setColor(myTunnel.isActive() ? java.awt.Color.blue : java.awt.Color.orange);
   	    			g.fillOval(x*64+32+16-10, y*64+32-16-10, 26, 26);
   	    		}
   	    //delkelet
   	    else if ((myTunnel.nextRail.pos.getX() == x+1 && myTunnel.nextRail.pos.getY() == y)
       		&& (myTunnel.prevRail.pos.getX() == x && myTunnel.prevRail.pos.getY() == y+1)
       		||
       		(myTunnel.prevRail.pos.getX() == x+1 && myTunnel.prevRail.pos.getY() == y)
       		&& (myTunnel.nextRail.pos.getX() == x && myTunnel.nextRail.pos.getY() == y+1)){
   	    			// draw GeneralPath (polygon)
   	    			int x1Points[] = {x*64+64-12, x*64+64, x*64+64, x*64+64-17, x*64+17, x*64+17};
   	    			int y1Points[] = {y*64+17, y*64+17, y*64+17+30, y*64+64, y*64+64, y*64+(64-12)};
   	    			g.setColor(java.awt.Color.black);
   	    			g.fillPolygon(x1Points, y1Points, 6);
   	    			g.setColor(myTunnel.isActive() ? java.awt.Color.blue : java.awt.Color.orange);
   	    			g.fillOval(x*64+32+16-10, y*64+32-16-10, 26, 26);
   	    		}
   	    		
   	    else if ((myTunnel.nextRail.pos.getX() == x && myTunnel.nextRail.pos.getY() == y+1)
   	    		&& (myTunnel.prevRail.pos.getX() == x-1 && myTunnel.prevRail.pos.getY() == y)
   	    		||
   	    		(myTunnel.prevRail.pos.getX() == x && myTunnel.prevRail.pos.getY() == y+1)
   	    		&& (myTunnel.nextRail.pos.getX() == x-1 && myTunnel.nextRail.pos.getY() == y)){
   	    			// draw GeneralPath (polygon)
   	    			int x1Points[] = {x*64+0, x*64+12, x*64+17+30, x*64+17+30, x*64+17, x*64+0};
   	    			int y1Points[] = {y*64+17, y*64+17, y*64+(64-12), y*64+64, y*64+64, y*64+(64-17)};
   	    			g.setColor(java.awt.Color.black);
   	    			g.fillPolygon(x1Points, y1Points, 6);
   	    			g.setColor(myTunnel.isActive() ? java.awt.Color.blue : java.awt.Color.orange);
   	    			g.fillOval(x*64+32+16-10, y*64+32-16-10, 26, 26);
   	    }
   	    
   	    else {
   	    	g.setColor(java.awt.Color.black);
   	    	g.fillRect(x*64,y*64+17, 64, 30); //vízszintes téglalap
   	    	g.setColor(myTunnel.isActive() ? java.awt.Color.blue : java.awt.Color.orange);
   	    	g.fillOval(x*64+32-13, y*64+32-13, 26, 26);
   	    }}
    }

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
    @Override
    public String getName(){ return this.name; }
}