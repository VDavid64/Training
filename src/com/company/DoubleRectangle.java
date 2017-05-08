package com.company;

import java.awt.geom.Rectangle2D;

/**
 * Created by Bandan on 5/9/2017.
 */
public class DoubleRectangle extends Rectangle2D {
    double _x;
    double _y;
    double _w;
    double _h;

    @Override
    public void setRect(double x, double y, double w, double h) {
        _x = x;
        _y = y;
        _w = w;
        _h = h;
    }

    public DoubleRectangle(double x, double y, double w, double h){
        _x = x;
        _y = y;
        _w = w;
        _h = h;
    }

    public boolean contains(int x, int y) {
        if(( x >= _x && x <= _x + _w) && ( y >= _y && y <= _y + _h ))
            return true;
        return false;
    }

    @Override
    public int outcode(double x, double y) {
        return 0;
    }

    @Override
    public Rectangle2D createIntersection(Rectangle2D r) {
        return null;
    }

    @Override
    public Rectangle2D createUnion(Rectangle2D r) {
        return null;
    }

    @Override
    public double getX() {
        return _x;
    }

    @Override
    public double getY() {
        return _y;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
