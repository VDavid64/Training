package com.company;


public class Station  extends Rail {

    private Color color;
    private int passenger;


    public Station(Rail r1, Rail r2) {
        color = Color.getRandomColor();
        nextRail = r1;
        prevRail = r2;
        passenger = (int) (Math.random() * (4));
    }

    @Override
    public Color getColor() {
        return color;
    }


    @Override
    public int getPassenger() {
        return passenger;
    }

    @Override
    public void setPassenger() {
        passenger = passenger-1;
    }

}
