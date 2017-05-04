package com.company;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Draw_Switch implements Drawable {

    public Point2D pos;
    private Switch mySwitch;
    
    public Draw_Switch(Switch s){
    	mySwitch = s;
    }

    @Override
    public void drawElement(Graphics2D g) {

    }
}