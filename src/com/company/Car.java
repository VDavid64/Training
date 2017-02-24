package com.company;




public class Car extends Train_Element {

    private Rail actPos;
    private boolean isEmpty;
    private Color color;


    public Car() {
        isEmpty = false;
        // szín beállítása véletlenszerűen
    }


    @Override
    void setActPos(Rail newPos) {
        actPos = newPos;
    }

    @Override
    Rail getActPos() {
        return null;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty() {
        isEmpty = true;
    }
}
