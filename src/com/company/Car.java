package com.company;


import java.util.Scanner;

/**
 * Class representing a train car, which can carry passengers,
 * have a color, and drop the passengers off.
 *
 * @author i_did_iit team
 */
public class Car extends Train_Element {

    /**
     * Boolean representing whether the car is empty or not.
     */
    private boolean isEmpty;

    /**
     * Color object representing the color of the car.
     */
    private Color color;

    /**
     * Car object representing the next car to this in the whole train.
     */
    private Car nextCar;

    /**
     * Constructor, requires one parameter.
     * @param numberOfCars: the number of cars after this car
     *                    in the train.
     */
    public Car(int numberOfCars) {
        color = Color.getRandomColor();
        isEmpty = false;
        if (numberOfCars > 0)
            nextCar = new Car(numberOfCars-1);
        else
            nextCar = null;
    }

    /**
     * Returns whether the train car is empty or not.
     */
    public void isEmpty() {
        System.out.println("        -> [Car].isEmpty()");
        System.out.println("        <- [Car].isEmpty()");
    }

    /**
     * Default constructor.
     */
    public Car(){
    }

    /**
     * Sets the train car in empty state.
     */
    public void setEmpty() {
        System.out.println("        -> [Car].setEmpty()");
        System.out.println("        <- [Car].setEmpty()");
        isEmpty = true;
    }

    /**
     * @return: returns the next car in the train if there is
     * one, else returns with null.
     */
    public Car getNextCar() {
        return nextCar;
    }

    public Color getColor() {
        System.out.println("        -> [Car].getColor()");
        System.out.println("        <- [Car].getColor()");
        return color;
    }


    /**
     * Function responsible for moving the train car.
     * @param nextRail: this is the Rail where this car will be
     *                moved to.
     */
    public void move(Rail nextRail) {
        System.out.println("            -> [Car].move()");
        System.out.println("            <- [Car].move()");

        /**
         * Set the previous position to the actual position
         * and the actual position to one passed in the function
         * argument.
         */
        prevPos = actPos;
        actPos = nextRail;


        /**
         * Call the move function on the next car, if there is one.
         */
        if (nextCar != null && prevPos != null)
            nextCar.move(prevPos);
    }


    /**
     * Function responsible for getting the first not empty car.
     * @param param
     * @return: return the first not empty car, if there is none
     *              returns null.
     */
    public Car getFirstNotEmptyCar(int param) {

        System.out.println("            -> [Car].getFirstNotEmptyCar()");
        if (param == 8)
        {
            System.out.println("8.2: Csak üres kocsikból áll? ");

            String command;
            Scanner input = new Scanner(System.in);
            command = input.nextLine();

            if (command.equals("I")) {
                System.out.println("            <- [Car].getFirstNotEmptyCar(): null");
                return null;
            }
            else if (!command.equals("N")) {
                throw new IllegalArgumentException();
            }
            else {
                System.out.println("            <- [Car].getFirstNotEmptyCar(): Car");
                return new Car();
            }
        }
        if (param == 6)
        {
            System.out.println("            <- [Car].getFirstNotEmptyCar(): Car");
            return new Car();

        }
        return null;
    }

}
