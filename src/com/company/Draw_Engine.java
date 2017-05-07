package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class Draw_Engine implements Drawable {

    public Point2D pos;
    public String name;
    private Engine myEngine;
    
    public Draw_Engine(Engine e){
        pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1), ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
        myEngine = e;
        name = e.name;
    }

    @Override
    public void drawElement(Graphics2D g) {
        int x = (int)pos.getX();
        int y = (int)(pos.getY());
        g.setColor(java.awt.Color.red);
        g.fillRect(x,y, 5, 20);
    }

    @Override
    public String getName(){ return this.name; }
}