package com.company;

import javafx.geometry.Point3D;

/**
 * Represents rail.
 * 
 * @author i_did_iit team
 *
 */
public class Rail {

	/**
	 * Next rail object.
	 */
	protected Rail nextRail;
	/**
	 * Previous rail object.
	 */
	protected Rail prevRail;
	/**
	 * 3D Position of rail.
	 */
	private Point3D position;
	/**
	 * isTunnelUnderRail whether there is a tunnel under rail.
	 */
	protected boolean isTunnelUnderRail;

	/**
	 * Getter of position. Returns a 3D point.
	 * 
	 * @return Position of rail.
	 */
	public Point3D getPosition() {
		return position;
	}

	/**
	 * Get the next rail. Returns a Rail object what is the next one.
	 * 
	 * @param previous
	 *            Rail object where train element was.
	 * @param t
	 *            Element which does move.
	 * @return Rail object where train element will be at next step. If it is
	 *         null, there is a derailing.
	 */
	public Rail getNextRail(Rail previous, Train_Element t) {
		System.out.println("            -> [Rail].getNextRail()");
		if (previous == prevRail){
			System.out.println("            <- [Rail].getNextRail()");
			return nextRail;}
		else {
			System.out.println("            <- [Rail].getNextRail()");
			return prevRail;
		}
	}

	/**
	 * Getter of color. Station class need this function.
	 * 
	 * @return null
	 */
	public Color getColor() {
		return null;
	}

}
