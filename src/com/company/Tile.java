package com.company;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Tile implements MouseListener{


    // position
    private int x, y;
    // sín tagváltozó - null, ha nincs rajta semmi
    private Rail rail;



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
