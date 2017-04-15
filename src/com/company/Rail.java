package com.company;

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
     * Name of Rail object.
     */
    public String name;
    
	/**
	 * True if there is a tunnel under the rail.
	 */
    protected boolean isTunnelUnderRail;
    
    /**
     * True if there is a train element on rail.
     */
    protected boolean occupied = false;


    
    /**
     * Constructor of Rail class. Sets isTunnelUnderRail false.
     * Sets name, nextRail and prevRail. Values are given in parameters.
     * @param r1
     * 			Next Rail object.
     * @param r2
     * 			Previous Rail object.
     * @param name
     * 				Name of object.
     */
    public Rail(Rail r1, Rail r2, String name) {
        isTunnelUnderRail = false;
        nextRail = r1;
        prevRail = r2;
        this.name = name;
    }

    
    /**
     *Default constructor of Rail class. 
     */
    public Rail() {}


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
    public Rail getNextRail(Rail prev, Train_Element t) {
        if (prev == prevRail)
            return nextRail;
        else
            return prevRail;
    }

	/**
	 * Getter of color. Station class need this function.
	 * 
	 * @return null
	 */
    public Color getColor() {
        return null;
    }

    /**
     * Setter of nextRail attribute. Sets value what was given in parameter.
     * @param prevRail
     */
    public void setNextRail(Rail nextRail) {
        this.nextRail = nextRail;
    }

    /**
     * Setter of prevRail attribute. Sets value what was given in parameter.
     * @param prevRail
     */
    public void setPrevRail(Rail prevRail) {
    this.prevRail = prevRail;
}

    /**
     * Getter or passengers attribute of Station class.
     * @return
     * 			Number of passengers.
     */
    public int getPassenger() {
        return 0;
    }

    /**
     * Setter of passenger attribute. Station class need it.
     */
    public void setPassenger() {}

    /**
     * Change the direction of switch. Switch class need it.
     */
    public void changeDir() {}

    /**
     * Sets otherRailA and otherRailB in CrossRail class. Values were given in two parameters.
     * @param r2_nextrail
     * @param r2_prevrail
     */
    public void setOtherCrossRails(Rail r2_nextrail, Rail r2_prevrail) {}

    /**
     * Setter of third rail. Switch class need it. Sets value what was given in parameter.
     * @param r3
     */
    public void setSwitchThirdRail(Rail r3) {}

    /**
     * Getter of third rail. Switch class need it.
     * @return
     * 			False.
     */
    public boolean getThirdRail() {
        return false;
    }

    /**
     * Setter of tunnelRail and dir attributes of Tunnel class.
     * @param r
     * 			Rail object what we want to set
     * @param d
     * 			Name of Tunnel object
     */
    public void setTunnelRail(Rail r, String d) {}
}
