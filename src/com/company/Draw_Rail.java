package com.company;

import java.awt.*;
import java.awt.geom.GeneralPath;
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
	 * vertical whether rail is vertical
	 */
	public boolean vertical;

    /**
     * Constructor of Draw_Rail class. Sets myRail,name and pos attributes.
     * @param r
     * 			Rail object what need to draw.
     */
    public Draw_Rail(Rail r){
    	pos = r.pos;
    	//pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
    	myRail = r;
    	name = r.name;
    	vertical = r.vertical;
    }
	/* 
	 * Overridden drawing function. Draws a Rail.
	 * @param g
	 * 			Graphics2D object
	 */
    @Override
    public void drawElement(Graphics2D g) {
	    int x = (int)pos.getX();
	    int y = (int)(pos.getY());
	    g.setColor(java.awt.Color.black);
	    if (myRail.nextRail != null && myRail.prevRail != null){
	    if ((myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y+1)
	    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
	    		||
	    		(myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y+1)
	    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1))
	    { //függőleges téglalap
	    	g.fillRect(64*x+17, 64*y, 30, 64);
	    }
	    else if ((myRail.nextRail.pos.getX() == x+1 && myRail.nextRail.pos.getY() == y)
	    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
	    		||
	    		(myRail.prevRail.pos.getX() == x+1 && myRail.prevRail.pos.getY() == y)
	    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1))
	    		{
	    			// draw GeneralPath (polygon)
	    			int x1Points[] = {x*64+17, x*64+17+30, x*64+64, x*64+64, x*64+64-12, x*64+17};
	    			int y1Points[] = {y*64+0, y*64+0, y*64+17, y*64+17+30, y*64+17+30, y*64+12};
	    			g.fillPolygon(x1Points, y1Points, 6);
	    		}
	    //ESZAKNYUGAT
	    else if ((myRail.nextRail.pos.getX() == x-1 && myRail.nextRail.pos.getY() == y)
	    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
	    		||
	    		(myRail.prevRail.pos.getX() == x-1 && myRail.prevRail.pos.getY() == y)
	    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1)){
	    			int x1Points[] = {x*64+17, x*64+17+30, x*64+17+30, x*64+12, x*64+0, x*64+0};
	    			int y1Points[] = {y*64+0, y*64+0, y*64+12, y*64+17+30, y*64+17+30, y*64+17};
	    			g.fillPolygon(x1Points, y1Points, 6);
	    		}
	    //delkelet
	    else if ((myRail.nextRail.pos.getX() == x+1 && myRail.nextRail.pos.getY() == y)
    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y+1)
    		||
    		(myRail.prevRail.pos.getX() == x+1 && myRail.prevRail.pos.getY() == y)
    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y+1)){
	    			// draw GeneralPath (polygon)
	    			int x1Points[] = {x*64+64-12, x*64+64, x*64+64, x*64+64-17, x*64+17, x*64+17};
	    			int y1Points[] = {y*64+17, y*64+17, y*64+17+30, y*64+64, y*64+64, y*64+(64-12)};
	    			g.fillPolygon(x1Points, y1Points, 6);
	    		}
	    		
	    else if ((myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y+1)
	    		&& (myRail.prevRail.pos.getX() == x-1 && myRail.prevRail.pos.getY() == y)
	    		||
	    		(myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y+1)
	    		&& (myRail.nextRail.pos.getX() == x-1 && myRail.nextRail.pos.getY() == y)){
	    			// draw GeneralPath (polygon)
	    			int x1Points[] = {x*64+0, x*64+12, x*64+17+30, x*64+17+30, x*64+17, x*64+0};
	    			int y1Points[] = {y*64+17, y*64+17, y*64+(64-12), y*64+64, y*64+64, y*64+(64-17)};
	    			g.fillPolygon(x1Points, y1Points, 6);
	    }
	    
	    else {
	    	g.fillRect(x*64,y*64+17, 64, 30); //vízszintes téglalap
	    }
	}}

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
	@Override
	public String getName(){ return this.name; }
}
