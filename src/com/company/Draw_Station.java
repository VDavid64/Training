package com.company;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Draw_Station implements Drawable {

    public Point2D pos;

    @Override
    public void drawElement(Graphics2D g) {
	    int x = (int)pos.getX();
	    int y = (int)(pos.getY());
	    g.setColor(java.awt.Color.black);
	    g.fillRect(x,y, 40, 10);
	    g.fillRect(x,y-15, 40, 10);
    }
}