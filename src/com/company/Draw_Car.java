package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for drawing Car(s). Implements Drawable interface.
 * @author i_did_iit team
 *
 */
public class Draw_Car implements Drawable {

    /**
     * Position of shape what need to draw.
     */
    public Point2D pos;
    /**
     * Name of Draw_Car object.
     */
    public String name;
    /**
     * Car object what need to draw.
     */
    private Car myCar;
    
    private Rail myRail;

    
    /**
     * Constructor of Draw_Car class. Sets myCar,name and pos attributes.
     * @param c
     * 			Car object what need to draw.
     */
    public Draw_Car(Car c){
		if (c.actPos != null) {
			pos = new Point((int) c.actPos.pos.getX(), (int) c.actPos.pos.getY());
		} else
			pos = null;
    	myCar = c;
    	name = c.name;
    	myRail = c.actPos;
    }

	/* 
	 * Overridden drawing function. Draws a Car.
	 * @param g
	 * 			Graphics2D object
	 */
    
	@Override
	public void drawElement(Graphics2D g) {
		if (this.myCar.actPos != null) {
	    int x = (int)pos.getX();
	    int y = (int)(pos.getY());
	    if (myCar.getColor() == Color.GREEN)
	    g.setColor(java.awt.Color.green);
	    if (myCar.getColor() == Color.RED)
		    g.setColor(java.awt.Color.red);
	    if (myCar.getColor() == Color.YELLOW)
		    g.setColor(java.awt.Color.yellow);
	    if (myRail.nextRail != null && myRail.prevRail != null){
	    if ((myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y+1)
	    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
	    		||
	    		(myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y+1)
	    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1))
	    { //függőleges téglalap
	    	g.fillRect( x*64+17+15-8, y*64+32-18, 16, 37);
	        
	    }
	    //eszakkelet
	    else if ((myRail.nextRail.pos.getX() == x+1 && myRail.nextRail.pos.getY() == y)
	    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
	    		||
	    		(myRail.prevRail.pos.getX() == x+1 && myRail.prevRail.pos.getY() == y)
	    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1))
	    		{
	    	Rectangle rect2 = new Rectangle(64*x+64, 64*y+17+30-10, 37, 16);     
	        g.rotate(-135, 64*x+64, 64*y+17+30-10);
	        g.rotate(-90, x*54+32, y*64+32);
	        g.draw(rect2);
	        g.fill(rect2);
	    		}
	    //ESZAKNYUGAT
	    else if ((myRail.nextRail.pos.getX() == x-1 && myRail.nextRail.pos.getY() == y)
	    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
	    		||
	    		(myRail.prevRail.pos.getX() == x-1 && myRail.prevRail.pos.getY() == y)
	    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1)){
	    	Rectangle rect2 = new Rectangle(64*x+64, 64*y+17+30-10, 37, 16);     
	        g.rotate(-135, 64*x+64, 64*y+17+30-10);
	        g.draw(rect2);
	        g.fill(rect2);
	    		}
	    //delkelet
	    else if ((myRail.nextRail.pos.getX() == x+1 && myRail.nextRail.pos.getY() == y)
    		&& (myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y+1)
    		||
    		(myRail.prevRail.pos.getX() == x+1 && myRail.prevRail.pos.getY() == y)
    		&& (myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y+1)){
	    	Rectangle rect2 = new Rectangle(64*x+64, 64*y+17+30-10, 37, 16);     
	        g.rotate(-135, 64*x+64, 64*y+17+30-10);
	        g.rotate(180, x*54+32, y*64+32);
	        g.draw(rect2);
	        g.fill(rect2);
	    }
	    //DELNYUGAT
	    else if ((myRail.nextRail.pos.getX() == x && myRail.nextRail.pos.getY() == y-1)
	    		&& (myRail.prevRail.pos.getX() == x-1 && myRail.prevRail.pos.getY() == y)
	    		||
	    		(myRail.prevRail.pos.getX() == x && myRail.prevRail.pos.getY() == y-1)
	    		&& (myRail.nextRail.pos.getX() == x-1 && myRail.nextRail.pos.getY() == y)){
	    			Rectangle rect2 = new Rectangle(64*x+64, 64*y+17+30-10, 37, 16);     
	    	        g.rotate(-135, 64*x+64, 64*y+17+30-10);
	    	        g.rotate(90, x*54+32, y*64+32);
	    	        g.draw(rect2);
	    	        g.fill(rect2);
	    }
	    
	    else {
	    	g.fillRect(x*64+32-18, y*64+17+15-8, 37, 16); //vízszintes téglalap
	    }
		}
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
