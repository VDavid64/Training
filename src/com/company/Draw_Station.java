package com.company;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Draw_Station implements Drawable {

    public Point2D pos;
    private Station myStation;

    public Draw_Station(Station s){
    	myStation = s;
    }
    
    @Override
    public void drawElement(Graphics2D g) {
	    int x = (int)pos.getX();
	    int y = (int)(pos.getY());
	    g.setColor(java.awt.Color.black);
	    g.fillRect(x,y, 40, 10);
	    java.awt.Color color = java.awt.Color.getColor(myStation.getColor().toString());
	    g.setColor(color);
	    g.fillRect(x,y-15, 40, 10);
    }
}