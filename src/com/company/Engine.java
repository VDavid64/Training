package com.company;


public class Engine extends Train_Element {


    private Rail actPos;

    @Override
    void setActPos(Rail newPos) {
        actPos = newPos;
    }

    @Override
    Rail getActPos() {
        return null;
    }

}
