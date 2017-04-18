package com.company;

/**
 * Represents station.
 * 
 * @author i_did_iit team
 *
 */
public class Station  extends Rail {
	/**
	 * Color of station.
	 */
    private Color color;
    
    /**
     *Number of passengers who are waiting.  
     */
    private int passenger;


   

	/**
	 * Constructs station and sets a color, sets name which was given in parameter, sets number of passengers random between 0 and 3
	 */
    public Station(String name) {
        this.name = name;
        passenger = (int) (Math.random() * (4));
        // color = Color.getRandomColor();
        //color = Color.GREEN;
    }


	/**
	 * Overridden getter of color. Returns the color of station.
	 * 
	 * @return 
	 * 			Color of station.
	 */
    @Override
    public Color getColor() {
        return color;
    }
    
    
    /**
     * Overridden setter of color attribute.
     * 
     * @param color
     * 			Color to set.
     */
    @Override
    public void setColor(String color){
    	this.color = Color.valueOf(color);
    }

	/**
	 * Overridden getter of passengers attribute. Returns the number of them.
	 * 
	 * @return 
	 * 			Number of passengers
	 */
    @Override
    public int getPassenger() {
        return passenger;
    }

	/**
	 * Overridden setter of passengers attribute. Decrease number of passengers by one.
	 * 
	 */
    @Override
    public void setPassenger() {
        passenger = passenger-1;
    }

}
