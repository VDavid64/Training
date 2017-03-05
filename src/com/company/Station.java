package com.company;


public class Station  extends Rail {

    private Color color;


    public Station() {
        color = Color.getRandomColor();
    }


    public Color getColor() {
        return color;
    }

    public int getType() {
        return 4;
    }
}
