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
		if (c.actPos != null) {
			pos = new Point((int) c.actPos.pos.getX(), (int) c.actPos.pos.getY());
		} else
			pos = null;
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
		if (this.myCar.actPos != null) {
			int x = (int) pos.getX();
			int y = (int) (pos.getY());
			String color = myCar.getColor().toString();
			if (color == "YELLOW")
			g.setColor(java.awt.Color.yellow);
			if (color == "GREEN")
			g.setColor(java.awt.Color.green);
			if (color == "RED")
			g.setColor(java.awt.Color.red);
			if (this.myCar.actPos.vertical == true) {
				g.fillRect(x, y, 10, 40);
			} else {
				g.fillRect(x, y, 40, 10);
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
