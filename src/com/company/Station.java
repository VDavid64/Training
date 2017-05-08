package com.company;

import java.awt.Point;

/**
 * Represents station.
 * 
 * @author i_did_iit team
 *
 */
public class Station extends Rail {
	/**
	 * Color of station.
	 */
	private Color color;

	/**
	 * Number of passengers who are waiting.
	 */
	private int passenger = 3;

	/**
	 * Constructs station and sets a color, sets name which was given in
	 * parameter, sets number of passengers random between 0 and 3
	 */
	public Station(String name, int x, int y, boolean v, String s) {
		this.name = name;
		this.pos = new Point(x, y);
		this.vertical = v;
		passenger = (int) (Math.random() * (4));
		if (s == "GREEN")
			color = Color.GREEN;
		if (s == "RED")
			color = Color.RED;
		if (s == "YELLOW")
			color = Color.YELLOW;
	}

	/**
	 * Overridden getter of color. Returns the color of station.
	 * 
	 * @return Color of station.
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Overridden setter of color attribute.
	 * 
	 * @param color
	 *            Color to set.
	 */
	@Override
	public void setColor(String color) {
		this.color = Color.valueOf(color);
	}

	/**
	 * Overridden getter of passengers attribute. Returns the number of them.
	 * 
	 * @return Number of passengers
	 */
	@Override
	public int getPassenger() {
		return passenger;
	}

	/**
	 * Overridden setter of passengers attribute. Decrease number of passengers
	 * by one.
	 * 
	 */
	@Override
	public void setPassenger() {
		passenger = passenger - 1;
		this.refreshDrawable();
	}

	@Override
	public void refreshDrawable() {
		for (Drawable d : Scene.objects) {
			if (d.getName().equals(this.name)) {
				Scene.objects.set(Scene.objects.indexOf(d), new Draw_Station(this));
			}
		}
	}
}
