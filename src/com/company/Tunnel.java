package com.company;


public class Tunnel extends Rail {

    private boolean isActive;


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getType() {
        return 3;
    }

}
