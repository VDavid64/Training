package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class Draw_Car implements Drawable {

    public Point2D pos;
    public String name;
    private Car myCar;

    
    public Draw_Car(Car c){
    	//TODO: pozíciók helyes megadása minden Draw_ classhoz
		pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
    	myCar = c;
    	name = c.name;
    }

	@Override
	public void drawElement(Graphics2D g) {
//	    int x = (int)pos.getX();
//	    int y = (int)(pos.getY());
//	    java.awt.Color color = java.awt.Color.getColor(myCar.getColor().toString());
//	    g.setColor(color);
//	    g.fillRect(x,y, 40, 20);
	}

	@Override
	public String getName(){ return this.name; }
}
