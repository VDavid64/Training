package com.company;

import java.awt.geom.Rectangle2D;

/**
 * A DoubleRectangle class, which is a subclass of Rectangle2D, but takes doubles as contructor arguments
 */
public class DoubleRectangle extends Rectangle2D {
    /**
     * Coordinates
     */
    double _x;
    double _y;
    double _w;
    double _h;

    /**
     * Setting the coordinates, which are the following
     * @param x
     * @param y
     * @param w
     * @param h
     */
    @Override
    public void setRect(double x, double y, double w, double h) {
        _x = x;
        _y = y;
        _w = w;
        _h = h;
    }

    /**
     * Constructor, with the following arguments
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public DoubleRectangle(double x, double y, double w, double h){
        _x = x;
        _y = y;
        _w = w;
        _h = h;
    }

    /**
     * Checks, whether the object contains a coordinate-pair
     * @param x horizontal
     * @param y vertical
     * @return
     */
    public boolean contains(int x, int y) {
        if(( x >= _x && x <= _x + _w) && ( y >= _y && y <= _y + _h ))
            return true;
        return false;
    }

    /**
     * Returns null.
     * @param x horizontal
     * @param y vertical
     * @return
     */
    @Override
    public int outcode(double x, double y) {
        return 0;
    }

    /**
     * Intersecting
     * @param r as a Rectangle2D
     * @return
     */
    @Override
    public Rectangle2D createIntersection(Rectangle2D r) {
        return null;
    }

    /**
     * Union
     * @param r as a Rectangle2D
     * @return
     */
    @Override
    public Rectangle2D createUnion(Rectangle2D r) {
        return null;
    }

    /**
     * Getter for x coordinate
     * @return the x coordinate
     */
    @Override
    public double getX() {
        return _x;
    }
    /**
     * Getter for y coordinate
     * @return the y coordinate
     */
    @Override
    public double getY() {
        return _y;
    }
    /**
     * Getter for width
     * @return the width
     */
    @Override
    public double getWidth() {
        return 0;
    }
    /**
     * Getter for height
     * @return the height
     */
    @Override
    public double getHeight() {
        return 0;
    }
    /**
     * Getter for checking emptiness
     * @return false, because its not empty
     */
    @Override
    public boolean isEmpty() {
        return false;
    }
}
