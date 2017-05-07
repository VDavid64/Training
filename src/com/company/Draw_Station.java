package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class Draw_Station implements Drawable {

    public Point2D pos;
    public String name;
    private Station myStation;

    public Draw_Station(Station s){
    	myStation = s;
		pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
		name = s.name;
    }
    
    @Override
    public void drawElement(Graphics2D g) {
//	    int x = (int)pos.getX();
//	    int y = (int)(pos.getY());
//	    g.setColor(java.awt.Color.black);
//	    g.fillRect(x,y, 40, 10);
//	    java.awt.Color color = java.awt.Color.getColor(myStation.getColor().toString());
//	    g.setColor(color);
//	    g.fillRect(x,y-15, 40, 10);
    }

	@Override
	public String getName(){ return this.name; }
}