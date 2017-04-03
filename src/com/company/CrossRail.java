package com.company;


public class CrossRail extends Rail {

    private Rail otherRailA, otherRailB;


    public CrossRail(Rail r1_nextrail, Rail r1_prevrail, Rail r2_nextrail, Rail r2_prevrail, String name) {
        this.nextRail = r1_nextrail;
        this.prevRail = r1_prevrail;
        this.otherRailA = r2_nextrail;
        this.otherRailB = r2_prevrail;
        this.name = name;
    }


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

    @Override
    public void setOtherCrossRails( Rail r2_nextrail, Rail r2_prevrail) {
        this.otherRailA = r2_nextrail;
        this.otherRailB = r2_prevrail;
    }
}
