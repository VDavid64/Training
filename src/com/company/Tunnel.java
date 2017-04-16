package com.company;

/**
 * Represents tunnel point.
 * 
 * @author i_did_iit team
 *
 */
public class Tunnel extends Rail {

	/**
	 * True if tunnel point is active.
	 */
    private boolean isActive;
    
    /**
     * Represents adjacent rail in tunnel.
     */
    private Rail tunnelRail;
    
    
    /**
     * Value is 'next' if tunnelRail runs parallel to nextRail, else 'prev'
     */
    private String dir;



    /**
     * Constructor of Tunnel class.
     * Sets name to parameter.
     * Sets tunnelRail attribute to null.
     * Sets isActive attribute false.
     * @param name
     * 				Name of object.
     */
    public Tunnel(String name) {
        this.name = name;
        this.tunnelRail = null;
        this.isActive = false;
    }

    /**
     * Getter of isActive attribute.
     * @return
     * 			Value of isActive
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Setter of isActive attribute.
     * @param active
     * 				Value to set
     */
    public void setActive(boolean active) {
        isActive = active;
    }


    /**
     * Sets tunnelRail and dir attributes. Values are given in parameters.
	 * @param r
     * 			Rail to set.
     * @param d
     * 			Direction to set.
     */
    public void setTunnelRail(Rail r, String d) {
        this.tunnelRail = r;
        this.dir = d;
    }


	/**
	 * Get next rail for a moving trai nelement to move onto.
	 * If there is a problem with tunnel entrance write it out.
	 * @param previous
	 *            Rail object where train element was previously. 
	 * @param t
	 *            Moving element requesting its next rail
	 * @return 
	 * 			  Next rail object.
	 */
    public Rail getNextRail(Rail previous, Train_Element t) {

        // ha nincs alagút, vagy épp vonat van az alagútban, sínként funkcionál
        if (Map.getIsTrainInTunnel() || tunnelRail == null) {
            if (previous == prevRail)
                return nextRail;

            // Kilépés az alagútszájon:
            else if (previous == tunnelRail) {
                t.setVisible(true);
                Map.setIsTrainInTunnel(false);
                if (dir.equals("next")) {
                    return prevRail;
                }
                else {
                    return nextRail;
                }
            }
            else
                return prevRail;
        }


        // ha van alagút és üres is, a vonat belép
        else if (!Map.getIsTrainInTunnel() && tunnelRail != null) {

            // ha megfelelő irányból jövünk, behajtunk az alagútba
            if (previous == prevRail) {
                if (dir.equals("next")) {
                    t.setVisible(false);                                // alagútba behajtás: vonaton jelezzük a visible változóval                   
                    Map.isTrainInTunnel = true;							 // map-nek jelezzük static beállításával
                    return tunnelRail;
                } else
                    return nextRail;
            }

            else if (previous == nextRail) {
                if (dir.equals("prev")) {
                    t.setVisible(false);
                    Map.setIsTrainInTunnel(true);
                    return tunnelRail;
                } else {
                    return prevRail;
                }
            }


            // ha az alagútból hajtunk kifelé:
            else {

                t.setVisible(true);
                Map.setIsTrainInTunnel(false);
                if (dir.equals("next")) {
                    return prevRail;
                }
                else {
                    return nextRail;
                }
            }
        }
        System.out.println("Error with the tunnel entrance");
        return null;
    }
}

