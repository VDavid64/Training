package com.company;

import java.util.ArrayList;

public class Switch extends Rail {

    // a három rail, amit összeköt
    // a lista első eleme köthető össze a másodikkal vagy harmadikkal
    // a dir tagváltozótól függően
    private ArrayList<Rail> connects = new ArrayList<>();
    // ha dir true, akkor az első van összekötve a másodikkal
    // ha false, az első a harmadikkal
    private boolean dir;



    public int getType() {
        return 2;
    }


    public Switch() {
        dir = true;
    }


    public boolean getDirection() {
        return dir;
    }


    // a három lehetséges bemenetre kell megfelelő rail-t visszaadnia
    public Rail getNextRail(Rail incoming) {
        return new Rail();
    }



    public void changeDir() {
        if (dir == true) {
            dir = false;
        }
        else
            dir = true;
    }



}
