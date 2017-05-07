package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class Draw_Switch implements Drawable {

    public Point2D pos;
    public String name;
    private Switch mySwitch;
    
    public Draw_Switch(Switch s){
    	mySwitch = s;
        pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
        name = s.name;
    }

    @Override
    public void drawElement(Graphics2D g) {

    }

    @Override
    public String getName(){ return this.name; }
}