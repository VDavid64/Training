package com.company;

import java.awt.Point;

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
	 * Constructs a switch and sets dir true. 
	 * Sets object's name which was  given in parameter. 
	 * Sets nextRail, prevRail and otherRail values what were given in parameters.
	 */
    public Switch(Rail r1, Rail r2, Rail r3, String name,int x, int y) {
        dir = true;
        nextRail = r1;
        prevRail = r2;
        otherRail = r3;
        this.name = name;
        this.pos = new Point(x,y);
    }


	/**
	 * Gets the next rail. Returns a Rail object what is the next one. There are three possible solutions.
	 * 
	 * @param previous
	 *            Rail object where train element was.
	 * @param t
	 *            element which does move.
	 * @return Next rail object.
	 */
    @Override
    public Rail getNextRail(Rail previous, Train_Element t) {

        // ha a fő irányból közelítünk, irányfüggő, merre megyünk tovább
        if (previous == prevRail) {
            if (dir == true) {
                return nextRail;
            }
            else
                return otherRail;
        }

        // ha az A irányból közelítünk
        else if (previous == nextRail) {
            if (dir == true) {
                return prevRail;
            }
            else {
                // kisiklás történt:
                Map.setIsDerailing(true);
                return null;
            }
        }

        // ha B irányból közelítünk
        else {
            if (dir == false) {
                return prevRail;
            }
            else {
                // kisiklás történt:
                Map.setIsDerailing(true);
                return null;
            }
        }

    }

	/**
	 * Change the direction of switch. 
	 * If there is a train on switch write it out and nothing happening.
	 * Else changes the direction and writes out the state.
	 */
    @Override
    public void changeDir() {
        if (this.occupied) {
            System.out.println("<Can not change direction: there is a train on the switch>");
            return;
        }
        else if (dir == true) {
            dir = false;
            System.out.print("<State: " + dir +">\n");
        }
        else {
            dir = true;
            System.out.print("<State: " + dir +">\n");
        }
        this.refreshDrawable();
    }


    /*
     * Return true, because there is a third rail.
     */
    @Override
    public boolean getThirdRail() {
        return true;
    }

    /* 
     * Sets otherRail value which was given in parameter.
     * 
     * @param r3
	 *            Rail object which will be set.
     */
    @Override
    public void setSwitchThirdRail(Rail r3) {
        this.otherRail = r3;
    }

    /**
     * Refreshes drawable list. Replaces the element at the specified position in objects list with the specified Draw_Car element.
     */
    @Override
    public void refreshDrawable() {
        for(Drawable d : Scene.objects) {
            if(d.getName().equals(this.name)) {
                Scene.objects.set(Scene.objects.indexOf(d), new Draw_Switch(this));
            }
        }
    }
}
