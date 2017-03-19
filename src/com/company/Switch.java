package com.company;

import java.util.Scanner;

/**
 * Represents switch between rails. It has two possible positions.
 * 
 * @author i_did_iit team
 *
 */
public class Switch extends Rail {

	/**
	 * Direction of switch. dir whether it is in first position.
	 */
	private boolean dir;
	/**
	 * Second possible out of switch.
	 */
	private Rail otherRail;

	/**
	 * Constructs a switch and set dir true.
	 */
	public Switch() {
		dir = true;
	}

	/**
	 * Get the next rail. Returns a Rail object what is the next one.
	 * 
	 * @param Previous
	 *            Rail object where train element was.
	 * @param Train
	 *            element which does move.
	 * @return Next rail object.
	 */
	public Rail getNextRail(Rail previus, Train_Element t) {

		// ha a fő irányból közelítünk, irányfüggő, merre megyünk tovább
		if (previus == prevRail) {
			if (dir == true) {
				return nextRail;
			} else
				return otherRail;
		}

		// ha az A irányból közelítünk
		else if (previus == nextRail) {
			if (dir == true) {
				return prevRail;
			} else
				return null;
		}

		// ha B irányból közelítünk
		else {
			if (dir == false) {
				return prevRail;
			} else
				return null;
		}

	}

	/**
	 * Change the direction.
	 */
	public void changeDir() {
		System.out.println("	->[Switch].changeDir()");
		if (dir == true) {
			dir = false;
		} else {
			dir = true;
		}
		System.out.println("	<-[Switch].changeDir()");
	}

}
