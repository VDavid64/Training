package com.company;


import javafx.geometry.Point3D;

public class Rail {


    protected Rail nextRail;
    protected Rail prevRail;
    private Point3D position;
    protected boolean isTunnelUnderRail;


    // konstruktor
    public Rail(Rail r1, Rail r2) {
        isTunnelUnderRail = false;
        nextRail = r1;
        prevRail = r2;
    }

    // default konstruktor
    public Rail() {}


    public Point3D getPosition() {
        return position;
    }


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

}
