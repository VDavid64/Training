package com.company;


public abstract class Train_Element {


    protected Rail actPos;
    protected Rail prevPos;
    protected boolean isVisible;
    protected Train_Element nextTrainElement;
    protected boolean isEmpty;




    public Rail getActPos() {
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


    public boolean isEmpty() {
        return true;
    }

    public void setEmpty(boolean b) {
        isEmpty = b;
    }


    public Train_Element getNextTrainElement() {
        return nextTrainElement;
    }



    public void move(Rail nextRail) {

        Rail tempPrevRail = prevPos;
        prevPos = actPos;
        actPos = nextRail;

        /*
        if (! (actPos == null))
            System.out.println("kocsi új pozíciója: " + actPos.name);
        */

        // ha van még kocsi, és az előző körben ez a kocsi már
        // a pályán volt, akkor léptetjük a következőt is
        if (nextTrainElement != null && prevPos != null)
            nextTrainElement.move(prevPos);
    }

    public Color getColor() {
        return null;
    }


}
