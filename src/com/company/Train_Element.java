package com.company;
import java.util.Random;

/**
 * Abstract class for train elements.
 * 
 * @author i_did_iit team
 *
 */
public abstract class Train_Element {


	/**
	 * Position of rail where is train element now.
	 */
    protected Rail actPos;
    /**
	 * Position of rail where was train element at last step. 
	 */
    protected Rail prevPos;
    /**
	 * isVisible whether train element is visible.
	 */
    protected boolean isVisible;
    /**
     * Stores next train element.
     */
    protected Train_Element nextTrainElement;
    /**
     * isEmpty whether train element is empty.
     */
    protected boolean isEmpty;
    /**
     * Name of object.
     */
    protected String name;
    /**
     * Static Random to avoid identical trains.
     */
    protected static Random random = new Random();


	/**
	 * Getter of actPos. Returns the position of rail where is train element
	 * now.
	 * 
	 * @return Actual position
	 */
    public Rail getActPos() {
        return actPos;
    }
    
	/**
	 * Setter of actPos. Set value of actPos which was added in parameter.
	 * 
	 * @param actPos
	 *            Actual position.
	 */
    public void setActPos(Rail actPos) {
        this.actPos = actPos;
    }
    
	/**
	 * Getter of prevPos. Returns position of rail where was train element at
	 * last step.
	 * 
	 * @return Previous position.
	 */
    public Rail getPrevPos() {
        return prevPos;
    }
	/**
	 * Setter of prevPos. Sets value of prevPos which was added in parameter.
	 * 
	 * @param prevPos
	 *            Previous position.
	 */
    public void setPrevPos(Rail prevPos) {
        this.prevPos = prevPos;
    }

	/**
	 * Getter of isVisible. Returns true whether train element is visible.
	 * 
	 * @return 
	 * 			Value of isVisible.
	 */
    public boolean isVisible() {
        return isVisible;
    }
    
	/**
	 * Setter of isVisible. Set value of isVisible which was added in parameter.
	 * 
	 * @param visible
	 *            True whether train element is visible.
	 */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    
    /**
     * Return true whether train element is empty.
     * @return true
     */
    public boolean isEmpty() {
        return true;
    }


    /**
     *Setter of isEmpty attribute. Sets value which was given in parameter.
     * @param b
     * 			boolean what we want to set
     */
    public void setEmpty(boolean b) {
        isEmpty = b;
    }


    /**
     * Getter of nextTrainElement attribute.
     * @return
     * 			value of nextTrainElement
     */
    public Train_Element getNextTrainElement() {
        return nextTrainElement;
    }



    /**
     * Moving of train element(s). If there is next element than call its move function recursively.
     * @param nextRail
     * 					Rail where train element will be move.
     * @param i
     * 			counter
     */
    public void move(Rail nextRail, int i) {

        prevPos = actPos;
        actPos = nextRail;
        
        if (actPos != null) actPos.occupied = true;

        /*
        if (! (actPos == null))
            System.out.println("kocsi új pozíciója: " + actPos.name);
        */

        // ha van még kocsi, és az előző körben ez a kocsi már
        // a pályán volt, akkor léptetjük a következőt is
        if (nextTrainElement != null && prevPos != null)
            nextTrainElement.move(prevPos, i);
    }

    /**
     * Gets color of train element.
     * @return null
     */
    public Color getColor() {
        return null;
    }


}
