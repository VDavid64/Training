package com.company;

import java.util.List;


public class Train {

    private Engine engine;
    private List<Car> cars;

    private int maxCarNumber = 5;


    // új vonat létrehozása:
    // az engine a vonat első eleme, ezért neki állítjuk be a startpos-t
    public Train(Rail startPos) {
        Engine e = new Engine(startPos);
        for (int i = 0; i < maxCarNumber; i++)
            cars.add(new Car());
    }



    // visszatér az első nem üres kocsival
    public Car getFirstCar() {
        return new Car();
    }


}
