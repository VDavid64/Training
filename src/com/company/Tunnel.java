package com.company;

/**
 * Represents tunnel point.
 * 
 * @author i_did_iit team
 *
 */
public class Tunnel extends Rail {

	/**
	 * isActive whether tunnel point is active.
	 */
    private boolean isActive;
    
    /**
     * Represents rail in tunnel.
     */
    private Rail tunnelRail;
    
    
    /**
     * Direction.
     */
    private String dir;



    /**
     * Constructor of Tunnel class.
     * Sets name what was given in parameter.
     * Sets tunnelRail attribute null.
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
     * 				boolean what we want to set
     */
    public void setActive(boolean active) {
        isActive = active;
    }


    /* 
     * Sets tunnelRail and dir attributes. Values were given in parameters.
	 * @param r
     * 			Rail what we want to set.
     * @param d
     * 			Direction what we want to set.
     */
    public void setTunnelRail(Rail r, String d) {
        this.tunnelRail = r;
        this.dir = d;
    }


	/**
	 * Get the next rail. Returns a Rail object what is the next one.
	 * If there is a problem with tunnel entrance write it out.
	 * @param previous
	 *            Rail object where train element was.
	 * @param train
	 *            element which does move.
	 * @return 
	 * 			Next rail object.
	 */
    public Rail getNextRail(Rail previous, Train_Element t) {

        // TODO: változtatás volt, párhuzamos síneket használunk már - irányt kell megoldani
        // Rail esetén megadjuk az előző pozíciót
        // ezt összehasonlítva a két szomszéddal, tudjuk mivel kell visszatérni

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
                    //Map.setIsTrainInTunnel(true);                       //                    map-nek jelezzük static beállításával
                    Map.isTrainInTunnel = true;
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

