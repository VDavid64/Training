package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class Draw_Cargo implements Drawable {

    public Point2D pos;
    public String name;
    private Cargo myCargo;

    
    public Draw_Cargo(Cargo c){
        pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
        myCargo = c;
        name = c.name;
    }
    
    @Override
    public void drawElement(Graphics2D g) {
//    	int x = (int)pos.getX();
//  	    int y = (int)(pos.getY());
//  	    g.setColor(java.awt.Color.GRAY);
//  	    g.fillRect(x,y, 40, 20);
    }

    @Override
    public String getName(){ return this.name; }
}