package com.company;


public class Tunnel extends Rail {

    private boolean isActive;
    private Rail tunnelRail;
    private String dir;



    public Tunnel(String name) {
        this.name = name;
        this.tunnelRail = null;
        this.isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public void setTunnelRail(Rail r, String d) {
        this.tunnelRail = r;
        this.dir = d;
    }


    // TODO: változtatás volt, párhuzamos síneket használunk már - irányt kell megoldani
    // Rail esetén megadjuk az előző pozíciót
    // ezt összehasonlítva a két szomszéddal, tudjuk mivel kell visszatérni
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

