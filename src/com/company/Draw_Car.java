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

    
    /**
     * Constructor of Draw_Car class. Sets myCar,name and pos attributes.
     * @param c
     * 			Car object what need to draw.
     */
    public Draw_Car(Car c){
    	//TODO: pozíciók helyes megadása minden Draw_ classhoz
		pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
    	myCar = c;
    	name = c.name;
    }

	/* 
	 * Overridden drawing function. Draws a Car.
	 * @param g
	 * 			Graphics2D object
	 */
	@Override
	public void drawElement(Graphics2D g) {
//	    int x = (int)pos.getX();
//	    int y = (int)(pos.getY());
//	    java.awt.Color color = java.awt.Color.getColor(myCar.getColor().toString());
//	    g.setColor(color);
//	    g.fillRect(x,y, 40, 20);
	}

	/**
	 * Overridden getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
	@Override
	public String getName(){ return this.name; }
}
