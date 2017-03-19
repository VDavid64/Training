package com.company;

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
	 * Constructs station and set a random color.s
	 */
	public Station() {
		color = Color.getRandomColor();
	}

	/**
	 * Getter of color. Returns the color of station.
	 * 
	 * @return Color of station.
	 */
	public Color getColor() {
		System.out.println("        -> [Station].getColor()");
		System.out.println("        <- [Station].getColor()");
		return color;
	}

}
