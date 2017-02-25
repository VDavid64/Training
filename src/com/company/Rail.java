package com.company;




public class Rail {

    // az elem típusa, pl:  1, ha sima sín
    //                      2, ha váltó
    //                      3, ha alagút
    private int type;
    private int x, y;


    public int getType() {
        return type;
    }


    public int[] getPos() {
        int[] pos = new int[2];
        pos[0]=x; pos[1] = y;
        return pos; }



    public Rail( int i, int k, int type) {
        x = i;
        y = k;
        type = 1;
    }



}
