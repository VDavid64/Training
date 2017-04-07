package com.company;

/**
 * Represents switch between rails. It has two possible positions.
 *
 * @author i_did_iit team
 *
 */
public class Switch extends Rail {

    /**
     * Direction of switch. dir whether it is in first position.
     */
    private boolean dir;
    /**
     * Second possible out of switch.
     */
    private Rail otherRail;



    // konstruktor
    public Switch(Rail r1, Rail r2, Rail r3, String name) {
        dir = true;
        nextRail = r1;
        prevRail = r2;
        otherRail = r3;
        this.name = name;
    }


    // a három lehetséges bemenetre kell megfelelő rail-t visszaadnia
    @Override
    public Rail getNextRail(Rail previus, Train_Element t) {

        // ha a fő irányból közelítünk, irányfüggő, merre megyünk tovább
        if (previus == prevRail) {
            if (dir == true) {
                return nextRail;
            }
            else
                return otherRail;
        }

        // ha az A irányból közelítünk
        else if (previus == nextRail) {
            if (dir == true) {
                return prevRail;
            }
            else {
                // kisiklás történt:
                Map.setIsDerailing(true);
                return null;
            }
        }

        // ha B irányból közelítünk
        else {
            if (dir == false) {
                return prevRail;
            }
            else {
                // kisiklás történt:
                Map.setIsDerailing(true);
                return null;
            }
        }

    }


    @Override
    public void changeDir() {
        if (this.occupied) {
            System.out.println("<Can not change direction: there is a train on the switch>");
            return;
        }
        else if (dir == true) {
            dir = false;
            System.out.print("<State: " + dir +">\n");
        }
        else {
            dir = true;
            System.out.print("<State: " + dir +">\n");
        }
    }


    @Override
    public boolean getThirdRail() {
        return true;
    }

    @Override
    public void setSwitchThirdRail(Rail r3) {
        this.otherRail = r3;
    }

}
