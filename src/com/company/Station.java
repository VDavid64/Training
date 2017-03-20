package com.company;


public class Station  extends Rail {

    private Color color;


    public Station(Rail r1, Rail r2) {
        color = Color.getRandomColor();
        nextRail = r1;
        prevRail = r2;
    }


    public Color getColor() {
        return color;
    }


}
