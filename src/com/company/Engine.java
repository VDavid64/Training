package com.company;


import java.util.HashMap;
import java.util.List;

/**
 * Class that represents the Engine in the train.
 */
public class Engine extends Train_Element {

    /**
     * Constructor.
     * @param startPos the starting position where the train depart from.
     * @param numberOfCars the number of cars the train has.
     * @param name the name of the train.
     */
    public Engine(Rail startPos, int numberOfCars, String name) {
        this.name = name;
        actPos = startPos;
        System.out.print("\"" + name + "\" at: \"" + actPos.name + "\" with cars: ");
        prevPos = null;
        nextTrainElement = random.nextBoolean() ? new Car(numberOfCars, 1) : new Cargo(numberOfCars, 1);
    }


    /**
     * Constructor, used when the train is loaded from an XML map.
     * @param name the name of the train.
     * @param numberOfTrainElement the number of cars the train has.
     * @param trainElements a HashMap containing the train elements, read from the XML.
     */
    public Engine(String name, int numberOfTrainElement, HashMap<Integer, String> trainElements) {
        this.name = name;
        this.actPos = null;

        // ha kocsit kell példányosítanunk
        if (!trainElements.get(1).equals(""))
            nextTrainElement = new Car(numberOfTrainElement, trainElements, 1, name);
        else
            nextTrainElement = new Cargo(numberOfTrainElement, trainElements, 1, name);
    }

    /**
     * Function responsible of returning the first not empty car.
     * @return returns the first empty car, if there is none, return null.
     */
    public Car getFirstNotEmptyCar() {
        Train_Element next = nextTrainElement;
        while ( next != null ) {
            if (!next.isEmpty()) {
                return (Car) next;
            }
            else {
                next = next.getNextTrainElement();
            }
        }
        return null;
    }


    /**
     * Function responsible of moving the engine.
     * @param counter the number of steps you want the engine to move.
     */
    public void moveEngine(int counter) {
        // Vonat léptetése
    	Rail nextPos = actPos.getNextRail(prevPos, this);
    	move(nextPos, counter);
    	
        if (actPos != null)
            System.out.println( "<" + this.name + " at " + actPos.name + ">");

    }

    /**
     * Refreshes drawable list. Replaces the element at the specified position in objects list with the specified Draw_Car element.
     */
    public void refreshDrawable() {
        for(Drawable d : Scene.objects) {
            if(d.getName().equals(this.name)) {
                Scene.objects.set(Scene.objects.indexOf(d), new Draw_Engine(this));
            }
        }
    }
}
