package com.company;


public class Station  extends Rail {

    private Color color;
    private int passenger;


    // TODO: állomások színének randomságát kiiktatni. Biztosítani kell hogy ha legyen olyan színű állomás, mint kocsi
    public Station(String name) {
        this.name = name;
        passenger = (int) (Math.random() * (4));
        // color = Color.getRandomColor();
        color = Color.GREEN;
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
