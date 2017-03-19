package com.company;


import java.util.Scanner;

/**
 * Class representing the engine of a train.
 */
public class Engine extends Train_Element {

    /**
     * Car variable representing the first car after the engine.
     */
    private Car firstCar;


    /**
     * Constructor with two required parameters.
     * @param startPos: the starting position of the Engine.
     * @param numberOfCars: the number of cars in the train.
     */
    public Engine(Rail startPos, int numberOfCars) {
        actPos = startPos;
        prevPos = null;
        firstCar = new Car(numberOfCars-1);
    }


    /**
     * Function responsible for moving the Engine,
     * and the cars connected to it.
     */
    public void move() {

        System.out.println("        -> [Engine].move()");


        /**
         * Temporary variables for storing previous and actual
         * positions.
         */
        Rail tempPrev = prevPos;
        Rail tempAct = actPos;
        /**
         * Set the previous position to the actual position.
         */
        setPrevPos(actPos);
        /**
         * Set the actual position to the next one.
         */
        setActPos(tempAct.getNextRail(tempPrev, this));

        //TODO alagút és váltó események

        /**
         * Handling printout.
         */
        System.out.println("5.1: Alagútszájra léptünk? ");
        String command;
        Scanner input = new Scanner(System.in);
        command = input.nextLine();

        /**
         * Printouts, depending on input case,
         * whether we enter a tunnel or not, or we
         * moved to a switch or not.
         */
        if (command.equals("I"))
        {
            System.out.println("5.1.1: Üres az alagút?");
            String command2;
            command2 = input.nextLine();
            /**
             * If we entered a tunnel.
             */
            if (command2.equals("I"))
            {
                //Belépünk az alagútba
            }
            /**
             * Invalid input.
             */
            else if (!command2.equals("N")) {
                throw new IllegalArgumentException();
            }
            /**
             * Normal rail.
             */
            else
            {
                //A sínen haladunk tovább
            }
        }
        else if (!command.equals("N")) {
            throw new IllegalArgumentException();
        }
        else
        {
            /**
             * If we moved on to a switch.
             */
            System.out.println("5.2: Váltóra léptünk? ");
            command = input.nextLine();

            if (command.equals("I"))
            {
                //Váltó esemény bekövetkezik
            }

            /**
             * Invalid input.
             */
            else if (!command.equals("N")) {
                throw new IllegalArgumentException();
            }

            /**
             * Normal rail.
             */
            else
            {
                //Sínen haladunk tovább
            }
        }

        /**
         * Call move function on the first car in the train.
         */
        if (firstCar != null)
            firstCar.move(tempAct);

        System.out.println("        <- [Engine].move()");
    }


    /**
     * Return the first not empty train car.
     * @param param
     * @return: return the first not empty train car, or if every
     *              train car is empty, return null.
     */
    public Car getFirstNotEmptyCar(int param) {

        System.out.println("        -> [Engine].getFirstNotEmptyCar()");


        if ( firstCar.getFirstNotEmptyCar(param) != null) {
            System.out.println("        <- [Engine].getFirstNotEmptyCar(): Car");
            return new Car();
        }
        else {
            System.out.println("        <- [Engine].getFirstNotEmptyCar(): null");
            return null;
        }

    }

    /**
     * Function responsible for returning the first car of the train.
     * @return: returns the first car of the train.
     */
    public Car getFirstCar() {
    	System.out.println("        -> [Engine].getFirstCar()");
    	System.out.println("        <- [Engine].getFirstCar()");
    	return firstCar;
    }

}
