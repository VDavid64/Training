package com.company;

import java.awt.*;

/**
 * Interface for drawing elements.
 * @author i_did_iit team
 *
 */
public interface Drawable {
	/**
	 * Drawing function.
	 * @param g
	 * 			Graphics2D object
	 */
	public void drawElement(Graphics2D g);
	/**
	 * Getter of drawable element's name.
	 * @return
	 * 			Name of drawable element.
	 */
	public String getName();
}
