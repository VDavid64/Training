package com.company;

/**
 * Created by Veszner D on 2017. 02. 23..
 */
public class Train {



    // vonat aktuális pontja és előző (irány miatt)
    private Rail actPos;
    private Rail previousPos;




    public Train(Rail startPos) {
        setPos(startPos);
    }


    public void setPos(Rail r) {
        actPos = r;
    }

    public Rail getActPos;


}
