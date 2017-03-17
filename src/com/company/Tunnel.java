package com.company;


public class Tunnel extends Rail {

    private boolean isActive;


    public boolean isActive() {
        return isActive;
    }


    public void setActive(boolean active, int seq) {
        if (seq == 3){
            System.out.println("        -> [Tunnel].setActive(false)");
            System.out.println("        <- [Tunnel].setActive(false)");
        }
        return;
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
