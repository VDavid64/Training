package com.company;

/**
 * Class representing a cross-rail
 * (two rails crossing each other without a switch present).
 */
public class CrossRail extends Rail {

    /**
     * The next a rail in the other direction.
     * The previous a rail in the other direction.
     */
    private Rail otherRailA, otherRailB;

    /**
     * Constructor for the class.
     * @param r1_nextrail the next rail in one direction.
     * @param r1_prevrail the previous rail in one direction.
     * @param r2_nextrail the next rail in the other direction.
     * @param r2_prevrail the previous rail in the other direction.
     * @param name the name of the cross-rail.
     */
    public CrossRail(Rail r1_nextrail, Rail r1_prevrail, Rail r2_nextrail, Rail r2_prevrail, String name) {
        this.nextRail = r1_nextrail;
        this.prevRail = r1_prevrail;
        this.otherRailA = r2_nextrail;
        this.otherRailB = r2_prevrail;
        this.name = name;
    }


    /**
     * Returns the next rail depending on the direction where the train is coming from.
     * @param previus the previous rail, where the train is coming from.
     * @param t the train element which is moved.
     * @return the next rail the train-element will move to.
     */
    @Override
    public Rail getNextRail(Rail previus, Train_Element t) {
        if (previus == prevRail)
            return nextRail;
        else if (previus == nextRail)
            return prevRail;
        else if (previus == otherRailA)
            return otherRailB;
        else
            return otherRailA;
    }

    /**
     * Set the corring rail's previous and next rails.
     * @param r2_nextrail the next rail to the crossing rail.
     * @param r2_prevrail the previous rail to the crossing rail.
     */
    @Override
    public void setOtherCrossRails( Rail r2_nextrail, Rail r2_prevrail) {
        this.otherRailA = r2_nextrail;
        this.otherRailB = r2_prevrail;
    }
}
