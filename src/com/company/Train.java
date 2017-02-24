package com.company;

import java.util.List;

/**
 * Created by Veszner D on 2017. 02. 23..
 */
public class Train {

    private Engine engine;
    private List<Car> cars;

    private int maxCarNumber = 5;


    public Train(Rail startPos) {
        Engine e = new Engine(startPos);
        for (int i = 0; i < maxCarNumber; i++)
            cars.add(new Car());
    }



}
