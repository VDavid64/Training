package com.company;


public abstract class Train_Element {


    protected Rail actPos;
    protected Rail prevPos;
    protected boolean isVisible;


    public Rail getActPos() {
        System.out.println("        -> [TrainElement].getActPos()");
        System.out.println("        <- [TrainElement].getActPos(Rail)");

        return actPos;
    }

    public void setActPos(Rail actPos) {
        this.actPos = actPos;
    }



    public Rail getPrevPos() {
        return prevPos;
    }

    public void setPrevPos(Rail prevPos) {
        this.prevPos = prevPos;
    }


    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }




}
