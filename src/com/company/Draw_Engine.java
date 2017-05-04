package com.company;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Draw_Engine implements Drawable {

    public Point2D pos;
    private Engine myEngine;
    
    public Draw_Engine(Engine e){
    	myEngine = e;
    }

    @Override
    public void drawElement(Graphics2D g) {

    }
}