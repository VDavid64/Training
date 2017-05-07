package com.company;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class Draw_Tunnel implements Drawable {

    public Point2D pos;
    public String name;
    private Tunnel myTunnel;
    
    public Draw_Tunnel(Tunnel t){
    	myTunnel = t;
        pos = new Point(ThreadLocalRandom.current().nextInt(0, 1000 + 1),ThreadLocalRandom.current().nextInt(0, 600 + 1)); //csak proba
        name = t.name;
    }

    @Override
    public void drawElement(Graphics2D g) {
        if(myTunnel.isActive()) {
            // rajz ha true
        } else {
            //ha false
        }

    }

    @Override
    public String getName(){ return this.name; }
}