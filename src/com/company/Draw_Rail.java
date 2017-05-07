package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class Draw_Rail implements Drawable {

    public Point2D pos;
    public String name;
    private Rail myRail;

    public Draw_Rail(Rail r){
    	pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
    	myRail = r;
    	name = r.name;
    }

    @Override
    public void drawElement(Graphics2D g) {
//	    int x = (int)pos.getX();
//	    int y = (int)(pos.getY());
//	    g.setColor(java.awt.Color.green);
//	    g.fillRect(x,y, 5, 20);
	}

	@Override
	public String getName(){ return this.name; }
}