package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for drawing Engine(s). Implements Drawable interface.
 * 
 * @author i_did_iit team
 *
 */
public class Draw_Engine implements Drawable {

	/**
	 * Position of shape what need to draw.
	 */
	public Point2D pos;
	/**
	 * Name of Draw_Engine object.
	 */
	public String name;
	/**
	 * Engine object what need to draw.
	 */
	private Engine myEngine;

	/**
	 * Constructor of Draw_Engine class. Sets myEngine,name and pos attributes.
	 * 
	 * @param e
	 *            Engine object what need to draw.
	 */
	public Draw_Engine(Engine e) {
		if (e.actPos != null) {
			pos = new Point((int) e.actPos.pos.getX(), (int) e.actPos.pos.getY());
		} else
			pos = null;
		myEngine = e;
		name = e.name;
	}

	/*
	 * Overridden drawing function. Draws an Engine.
	 * 
	 * @param g Graphics2D object
	 */
	@Override
	public void drawElement(Graphics2D g) {
		if (this.myEngine.actPos != null) {
			int x = (int) pos.getX();
			int y = (int) (pos.getY());
			g.setColor(java.awt.Color.red);
			if (this.myEngine.actPos.vertical == true) {
				g.fillRect(x, y, 10, 40);
			} else {
				g.fillRect(x, y, 40, 10);
			}
		}
	}

	/**
	 * Overridden getter of drawable element's name.
	 * 
	 * @return Name of drawable element.
	 */
	@Override
	public String getName() {
		return this.name;
	}
}