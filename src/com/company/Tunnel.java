package com.company;


public class Tunnel extends Rail {

    private boolean isActive;


    //TODO: tunnel konstruktor
    public Tunnel() {
        isActive = false;
    }


    public boolean isActive() {
        return isActive;
    }


    public void setActive(boolean active) {
        isActive = active;
    }


    // Rail esetén megadjuk az előző pozíciót
    // ezt összehasonlítva a két szomszéddal, tudjuk mivel kell visszatérni
    public Rail getNextRail(Rail previous, Train_Element t) {
        if (previous == prevRail) {
            // ha aktív a tunnel és a következő rail alatt megy alagút, és még nincs vonat az alagútban
            if (isActive() && nextRail.isTunnelUnderRail && !Map.getIsTrainInTunnel()) {
                t.setVisible(false);
                Map.setIsTrainInTunnel(true);
                return nextRail;
            }

            // ellenkező esetben haladunk tovább a felszínen
            else
                return nextRail;
        }


        else {
            if (this.isActive && prevRail.isTunnelUnderRail && !Map.getIsTrainInTunnel()) {
                t.setVisible(false);
                Map.setIsTrainInTunnel(true);
                return prevRail;
            }

            // ellenkező esetben haladunk tovább a felszínen
            else
                return prevRail;
        }
    }


}
