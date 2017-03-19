package com.company;

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
	 * Getter of actPos. Returns the position of rail where is train element
	 * now.
	 * 
	 * @return Actual position
	 */
	public Rail getActPos() {
		System.out.println("        -> [TrainElement].getActPos()");
		System.out.println("        <- [TrainElement].getActPos() : Rail");
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
	 * Setter of prevPos. Set value of prevPos which was added in parameter.
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
	 * @return Vaule of isVisible.
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

}
