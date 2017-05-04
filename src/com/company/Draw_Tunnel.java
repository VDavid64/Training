package com.company;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Draw_Tunnel implements Drawable {

    public Point2D pos;
    private Tunnel myTunnel;
    
    public Draw_Tunnel(Tunnel t){
    	myTunnel = t;
    }

    @Override
    public void drawElement(Graphics2D g) {

    }
}