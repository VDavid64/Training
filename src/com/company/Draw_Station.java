package com.company;

import java.awt.*;
import java.awt.geom.GeneralPath;
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
    	
	    int x = (int)pos.getX();
	    int y = (int)(pos.getY());
	    g.setColor(java.awt.Color.black);
	    if ((myStation.nextRail.pos.getX() == x && myStation.nextRail.pos.getY() == y+1)
	    		&& (myStation.prevRail.pos.getX() == x && myStation.prevRail.pos.getY() == y-1)
	    		||
	    		(myStation.prevRail.pos.getX() == x && myStation.prevRail.pos.getY() == y+1)
	    		&& (myStation.nextRail.pos.getX() == x && myStation.nextRail.pos.getY() == y-1))
	    { //függőleges téglalap
	    	g.fillRect(64*x+17, 64*y, 30, 64);
	    	g.fillRect(x*64+17-12,y*64+32-6, 12, 12);
	    	g.drawString(((Integer)myStation.getPassenger()).toString(), x*64+32-6,y*64+17-12);
	    }
	    else if ((myStation.nextRail.pos.getX() == x+1 && myStation.nextRail.pos.getY() == y)
	    		&& (myStation.prevRail.pos.getX() == x && myStation.prevRail.pos.getY() == y-1)
	    		||
	    		(myStation.prevRail.pos.getX() == x+1 && myStation.prevRail.pos.getY() == y)
	    		&& (myStation.nextRail.pos.getX() == x && myStation.nextRail.pos.getY() == y-1))
	    		{
	    			// draw GeneralPath (polygon)
	    			int x1Points[] = {x*64+17, x*64+17+30, x*64+64, x*64+64, x*64+64-12, x*64+17};
	    			int y1Points[] = {y*64+0, y*64+0, y*64+17, y*64+17+30, y*64+17+30, y*64+12};
	    			g.fillPolygon(x1Points, y1Points, 6);
	    			g.fillRect(x*64+32-6, y*64+32-6, 12, 12);
	    	    	g.drawString(((Integer)myStation.getPassenger()).toString(), x*64+32-6, y*64+32-6);
	    		}
	    else if ((myStation.nextRail.pos.getX() == x-1 && myStation.nextRail.pos.getY() == y)
	    		&& (myStation.prevRail.pos.getX() == x && myStation.prevRail.pos.getY() == y-1)
	    		||
	    		(myStation.prevRail.pos.getX() == x-1 && myStation.prevRail.pos.getY() == y)
	    		&& (myStation.nextRail.pos.getX() == x && myStation.nextRail.pos.getY() == y-1)){
	    			int x1Points[] = {x*64+17, x*64+17+30, x*64+17+30, x*64+12, x*64+0, x*64+0};
	    			int y1Points[] = {y*64+0, y*64+0, y*64+12, y*64+17+30, y*64+17+30, y*64+12};
	    			g.fillPolygon(x1Points, y1Points, 6);
	    			g.fillRect(x*64+32-6, y*64+32-6, 12, 12);
	    	    	g.drawString(((Integer)myStation.getPassenger()).toString(), x*64+32-6, y*64+32-6);
	    		}
	    else if ((myStation.nextRail.pos.getX() == x+1 && myStation.nextRail.pos.getY() == y)
    		&& (myStation.prevRail.pos.getX() == x && myStation.prevRail.pos.getY() == y+1)
    		||
    		(myStation.prevRail.pos.getX() == x+1 && myStation.prevRail.pos.getY() == y)
    		&& (myStation.nextRail.pos.getX() == x && myStation.nextRail.pos.getY() == y+1)){
	    			// draw GeneralPath (polygon)
	    			int x1Points[] = {x*64+64-12, x*64+64, x*64+64, x*64+64-17, x*64+17, x*64+17};
	    			int y1Points[] = {y*64+12, y*64+12, y*64+17+30, y*64+64, y*64+64, y*64+(64-12)};
	    			g.fillPolygon(x1Points, y1Points, 6);
	    			g.fillRect(x*64+32-6, y*64+32-6, 12, 12);
	    	    	g.drawString(((Integer)myStation.getPassenger()).toString(), x*64+32-6, y*64+32-6);
	    		}
	    		
	    else if ((myStation.nextRail.pos.getX() == x && myStation.nextRail.pos.getY() == y-1)
	    		&& (myStation.prevRail.pos.getX() == x-1 && myStation.prevRail.pos.getY() == y)
	    		||
	    		(myStation.prevRail.pos.getX() == x && myStation.prevRail.pos.getY() == y-1)
	    		&& (myStation.nextRail.pos.getX() == x-1 && myStation.nextRail.pos.getY() == y)){
	    			// draw GeneralPath (polygon)
	    			int x1Points[] = {x*64+0, x*64+12, x*64+17+30, x*64+17+30, x*64+17, x*64+0};
	    			int y1Points[] = {y*64+12, y*64+12, y*64+17+30, y*64+64, y*64+64, y*64+(64-12)};
	    			g.fillPolygon(x1Points, y1Points, 6);
	    			g.fillRect(x*64+32-6, y*64+32-6, 12, 12);
	    	    	g.drawString(((Integer)myStation.getPassenger()).toString(), x*64+32-6, y*64+32-6);
	    }
	    
	    else {
	    	g.fillRect(x*64,y*64+17, 64, 30); //vízszintes téglalap
	    	g.fillRect(x*64+32-6,y*64+17-12, 12, 12);
	    	g.drawString(((Integer)myStation.getPassenger()).toString(), x*64+32-6,y*64+17-12);
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