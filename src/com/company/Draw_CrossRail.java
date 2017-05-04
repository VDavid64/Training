package com.company;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Draw_CrossRail implements Drawable {

    public Point2D pos;
    private CrossRail myCrossRail;
    
    public Draw_CrossRail(CrossRail c){
    	myCrossRail = c;
    }

    @Override
    public void drawElement(Graphics2D g) {

    }
}