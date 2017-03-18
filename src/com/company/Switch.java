package com.company;

import java.util.Scanner;

public class Switch extends Rail {

	private boolean dir;
	private Rail otherRail;

	public Switch() {
		dir = true;
	}

	// a három lehetséges bemenetre kell megfelelő rail-t visszaadnia
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
