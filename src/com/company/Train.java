package com.company;

import java.util.ArrayList;


public class Train {

    private Engine engine;
    private ArrayList<Car> cars = new ArrayList<>();

    private int maxCarNumber = 5;


    // új vonat létrehozása:
    // az engine a vonat első eleme, ezért neki állítjuk be a startpos-t
    // majd feltöltjük a cars listát (szintén a startppos-t adjuk be neki egyelőre)
    // TO-Do: car-ok indítására kitalálni valamit
    public Train(Rail startPos) {
        engine = new Engine(startPos);
        ArrayList<Car> tempcars = new ArrayList<>();
        for (int i = 0; i < maxCarNumber; i++)
            tempcars.add(new Car(startPos));
        setCars(tempcars);
    }



    // visszatér az első nem üres kocsival
    // null, ha mindegyik üres
    public Car getFirstCar() {
        for (int i = 0; i < maxCarNumber; i++) {
            if (cars.get(i).isEmpty()) {
                return cars.get(i);
            }
        }
        return null;
    }

    public void setCars(ArrayList<Car> tempcars) {
        cars = tempcars;
    }


}
