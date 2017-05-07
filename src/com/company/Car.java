package com.company;


import java.util.HashMap;
import java.util.List;

/**
 * Class representing a passenger car.
 */
public class Car extends Train_Element {

    /**
     * The color of the car.
     */
    private Color color;

    /**
     * Shows when was car empty last time.
     */
    public int roundLastEmpty;


    /**
     * Cunstructor, used if random is on.
     * @param numberOfCars the number of cars yet to be generated.
     * @param i the number of the train car.
     */
    public Car(int numberOfCars, int i) {
    	
    	//színkezelés
    	int colorIndex;
    	String colorName;
    	
    	if (!Game.trainColors.isEmpty()){
    	colorIndex = (int)Math.random() * (Game.trainColors.size() - 1);
    	colorName = Game.trainColors.get(colorIndex);
    	Game.trainColors.remove(colorIndex);
    	}
    	
    	else {
    		colorIndex = (int)Math.random() * (Map.stationColors.size() - 1);
    		colorName = Map.stationColors.get(colorIndex);
    	}
    
    	this.color = Color.valueOf(colorName);
    	
        this.name = "car_"+ i;
        this.isEmpty = false;
        this.roundLastEmpty = 0;

        System.out.format(" %s ", color);

        if (numberOfCars > 1)
        	if (Game.trainColors.isEmpty())
            nextTrainElement = random.nextBoolean() ? new Car(numberOfCars-1, i++) : new Cargo(numberOfCars-1, i++);
            else nextTrainElement = new Car(numberOfCars-1, i++);  //Ha van még létrehozandó szín, akkor biztosan kocsi lesz
        else {
            nextTrainElement = null;
            System.out.println();
        }
    }


    /**
     * Constructor, used when loading from XML.
     * @param numberOfTrainElement the number of train elements yet to be generated.
     * @param trainElements a hashmap containing the train elements, read from XML.
     * @param counter the number of the train car.
     */
    public Car(int numberOfTrainElement, HashMap<Integer, String> trainElements, int counter) {
        this.name = "car_"+counter;
        this.color = Color.valueOf(trainElements.get(counter));
        this.isEmpty = false;
        this.roundLastEmpty = 0;

        counter++;

        // ha nem kell több elemet generálni
        if (numberOfTrainElement == 1) {
            nextTrainElement = null;
        }


        // ha kocsit kell generálni:
        else if (numberOfTrainElement > 1 && !trainElements.get(counter).equals("")) {
            nextTrainElement = new Car(numberOfTrainElement-1, trainElements, counter);
        }

        // ha teherkocsit:
        else {
            nextTrainElement = new Cargo(numberOfTrainElement-1, trainElements, counter);
        }
        Scene.addDrawable(new Draw_Car(this));
    }

    /**
     * Function responsible of returning the color of the car.
     * @return returns the color.
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Function responsible of returning whether the car is empty.
     * @return returns whether the car is empty.
     */
    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Function responsible of moving the car.
     */
    @Override
    public void move(Rail nextRail, int counter) {
        // Utolsó kocsi felelőssége: felszabadítjuk a sínt, amelyről továbblépünk
        if (nextTrainElement == null && actPos != null) {
            actPos.occupied=false;
        }

        // Lépés
        prevPos = actPos;
        actPos = nextRail;


        // Felszállás:
        //      Ha üres a kocsi, azonosak a színek és van utas az állomáson
        if (this.isEmpty() && actPos.getColor() == this.color && actPos.getPassenger() > 0 ) {
            this.setEmpty(false);
            actPos.setPassenger();
            System.out.println("    <Passengers got on the train: " + this.getActPos().name + ", "+ this.name + " >");                     // Kiírás sak debug célra, majd törölni kell
            roundLastEmpty = counter;
        }

        // ha van még kocsi, és az előző körben ez a kocsi már
        // a pályán volt, akkor léptetjük a következőt is
        if (nextTrainElement != null && prevPos != null)
            nextTrainElement.move(prevPos, counter);
        this.refreshDrawable();
    }

    public void refreshDrawable() {
        for(Drawable d : Scene.objects) {
            if(d.getName().equals(this.name)) {
                Scene.objects.set(Scene.objects.indexOf(d), new Draw_Car(this));
            }
        }
    }


}
