package com.company;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Draw_Car implements Drawable {

    public Point2D pos;
    private Car myCar;
    
    public Draw_Car(Car c){
    	myCar = c;
    }

	@Override
	public void drawElement(Graphics2D g) {
	    int x = (int)pos.getX();
	    int y = (int)(pos.getY());
	    java.awt.Color color = java.awt.Color.getColor(myCar.getColor().toString());
	    g.setColor(color);
	    g.fillRect(x,y, 40, 20);
		
	}
}
