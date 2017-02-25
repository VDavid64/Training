package com.company;


public class Engine extends Train_Element {


    private Rail actPos;
    private Rail previousPos;



    @Override
    void setActPos(Rail newPos) {

        actPos = newPos;
    }



    @Override
    Rail getActPos() {

        return actPos;
    }



    public Engine(Rail startPos) {
        actPos = startPos;
        previousPos = null;
    }

}
