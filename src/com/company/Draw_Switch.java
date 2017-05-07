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
    	 int x = (int)pos.getX();
 	    int y = (int)(pos.getY());
 	    g.setColor(java.awt.Color.magenta);
 	    if ((mySwitch.nextRail.pos.getX() == x && mySwitch.nextRail.pos.getY() == y+1)
 	    		&& (mySwitch.prevRail.pos.getX() == x && mySwitch.prevRail.pos.getY() == y-1)
 	    		||
 	    		(mySwitch.prevRail.pos.getX() == x && mySwitch.prevRail.pos.getY() == y+1)
 	    		&& (mySwitch.nextRail.pos.getX() == x && mySwitch.nextRail.pos.getY() == y-1))
 	    { //függőleges téglalap
 	    	g.fillRect(64*x+17, 64*y, 30, 64);
 	    }
 	    else if ((mySwitch.nextRail.pos.getX() == x+1 && mySwitch.nextRail.pos.getY() == y)
 	    		&& (mySwitch.prevRail.pos.getX() == x && mySwitch.prevRail.pos.getY() == y-1)
 	    		||
 	    		(mySwitch.prevRail.pos.getX() == x+1 && mySwitch.prevRail.pos.getY() == y)
 	    		&& (mySwitch.nextRail.pos.getX() == x && mySwitch.nextRail.pos.getY() == y-1))
 	    		{
 	    			// draw GeneralPath (polygon)
 	    			int x1Points[] = {x*64+17, x*64+17+30, x*64+64, x*64+64, x*64+64-12, x*64+17};
 	    			int y1Points[] = {y*64+0, y*64+0, y*64+17, y*64+17+30, y*64+17+30, y*64+12};
 	    			g.fillPolygon(x1Points, y1Points, 6);
 	    		}
 	    else if ((mySwitch.nextRail.pos.getX() == x-1 && mySwitch.nextRail.pos.getY() == y)
 	    		&& (mySwitch.prevRail.pos.getX() == x && mySwitch.prevRail.pos.getY() == y-1)
 	    		||
 	    		(mySwitch.prevRail.pos.getX() == x-1 && mySwitch.prevRail.pos.getY() == y)
 	    		&& (mySwitch.nextRail.pos.getX() == x && mySwitch.nextRail.pos.getY() == y-1)){
 	    			int x1Points[] = {x*64+17, x*64+17+30, x*64+17+30, x*64+12, x*64+0, x*64+0};
 	    			int y1Points[] = {y*64+0, y*64+0, y*64+12, y*64+17+30, y*64+17+30, y*64+12};
 	    			g.fillPolygon(x1Points, y1Points, 6);
 	    		}
 	    else if ((mySwitch.nextRail.pos.getX() == x+1 && mySwitch.nextRail.pos.getY() == y)
     		&& (mySwitch.prevRail.pos.getX() == x && mySwitch.prevRail.pos.getY() == y+1)
     		||
     		(mySwitch.prevRail.pos.getX() == x+1 && mySwitch.prevRail.pos.getY() == y)
     		&& (mySwitch.nextRail.pos.getX() == x && mySwitch.nextRail.pos.getY() == y+1)){
 	    			// draw GeneralPath (polygon)
 	    			int x1Points[] = {x*64+64-12, x*64+64, x*64+64, x*64+64-17, x*64+17, x*64+17};
 	    			int y1Points[] = {y*64+12, y*64+12, y*64+17+30, y*64+64, y*64+64, y*64+(64-12)};
 	    			g.fillPolygon(x1Points, y1Points, 6);
 	    		}
 	    		
 	    else if ((mySwitch.nextRail.pos.getX() == x && mySwitch.nextRail.pos.getY() == y-1)
 	    		&& (mySwitch.prevRail.pos.getX() == x-1 && mySwitch.prevRail.pos.getY() == y)
 	    		||
 	    		(mySwitch.prevRail.pos.getX() == x && mySwitch.prevRail.pos.getY() == y-1)
 	    		&& (mySwitch.nextRail.pos.getX() == x-1 && mySwitch.nextRail.pos.getY() == y)){
 	    			// draw GeneralPath (polygon)
 	    			int x1Points[] = {x*64+0, x*64+12, x*64+17+30, x*64+17+30, x*64+17, x*64+0};
 	    			int y1Points[] = {y*64+12, y*64+12, y*64+17+30, y*64+64, y*64+64, y*64+(64-12)};
 	    			g.fillPolygon(x1Points, y1Points, 6);
 	    }
 	    
 	    else {
 	    	g.fillRect(x*64,y*64+17, 64, 30); //vízszintes téglalap
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