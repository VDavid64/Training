package com.company;


import javafx.geometry.Point3D;

public class Rail {

    // az elem típusa, pl:  1, ha sima sín
    //                      2, ha váltó
    //                      3, ha alagút
    //                      4, ha station
    private int type = 1;
    private Rail nextRail;
    private Rail prevRail;

    private Point3D position;



    public Point3D getPosition() {
        return position;
    }




    public int getType() {
        return type;
    }



    public Rail getNextRail() {
        return nextRail;
    }

    public Rail getPrevRail() {
        return prevRail;
    }

}
