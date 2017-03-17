package com.company;


public class Station  extends Rail {

    private Color color;


    public Station() {
        color = Color.getRandomColor();
    }


    public Color getColor() {
        System.out.println("        -> [Station].getColor()");
        System.out.println("        <- [Station].getColor()");
        return color;
    }


}
