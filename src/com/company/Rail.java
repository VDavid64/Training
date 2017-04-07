package com.company;


public class Rail {


    protected Rail nextRail;
    protected Rail prevRail;
    public String name;
    protected boolean isTunnelUnderRail;
    protected boolean occupied = false;


    // konstruktor
    public Rail(Rail r1, Rail r2, String name) {
        isTunnelUnderRail = false;
        nextRail = r1;
        prevRail = r2;
        this.name = name;
    }

    // default konstruktor
    public Rail() {}


    // Rail esetén megadjuk az előző pozíciót
    // ezt összehasonlítva a két szomszéddal, tudjuk mivel kell visszatérni
    // Null esetén kisiklás történt
    public Rail getNextRail(Rail previus, Train_Element t) {
        if (previus == prevRail)
            return nextRail;
        else
            return prevRail;
    }

    // Station osztályhoz szükséges függvény
    public Color getColor() {
        return null;
    }

    public void setNextRail(Rail nextRail) {
        this.nextRail = nextRail;
    }

    public void setPrevRail(Rail prevRail) {
    this.prevRail = prevRail;
}

    public int getPassenger() {
        return 0;
    }

    public void setPassenger() {}

    public void changeDir() {}

    public void setOtherCrossRails(Rail r2_nextrail, Rail r2_prevrail) {}

    public void setSwitchThirdRail(Rail r3) {}

    public boolean getThirdRail() {
        return false;
    }

    public void setTunnelRail(Rail r, String d) {}
}
