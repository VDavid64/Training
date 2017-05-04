package com.company;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Draw_Cargo implements Drawable {

    public Point2D pos;
    private Cargo myCargo;
    
    public Draw_Cargo(Cargo c){
    	myCargo = c;
    }
    
    @Override
    public void drawElement(Graphics2D g) {
    	int x = (int)pos.getX();
  	    int y = (int)(pos.getY());
  	    g.setColor(java.awt.Color.GRAY);
  	    g.fillRect(x,y, 40, 20);
    }
}