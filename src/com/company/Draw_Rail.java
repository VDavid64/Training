package com.company;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

public class Draw_Rail implements Drawable {

    public Point2D pos;
    private Rail myRail;

    public Draw_Rail(){
    	pos = new Point(3,2); //csak proba
    }

    @Override
    public void drawElement(Graphics2D g) {
	    int x = (int)pos.getX();
	    int y = (int)(pos.getY());
	    g.setColor(java.awt.Color.black);
	    g.fillRect(x,y, 40, 10);
	}
    

}