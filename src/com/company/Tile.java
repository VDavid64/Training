package com.company;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Tile implements MouseListener{


    // position
    private int x, y;
    // sín tagváltozó - null, ha nincs rajta semmi
    private Rail rail;


    public boolean isEmpty() {
        if (rail == null)
            return true;
        return false;
    }


    // konstruktor
    public Tile( int i, int k) {
        x = i;
        y = k;
    }


    public Rail getRail() {
        return rail;
    }

    public void createRail(int x, int y, int type) {
        rail = new Rail(x, y, type);
    }


    // Felhasználó kattintásának lekezelése
    @Override
    public void mouseClicked(MouseEvent e) {
        // ha a tile tunnelt tartalmaz -> épít vagy töröl
        // ha switch-et - megváltoztatja a dir-t
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
