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
	 * Getter of isActive. Returns true whether tunnel point is active.
	 * 
	 * @return isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Setter of isActive. Set value of parameter to isActive attribute.
	 * 
	 * @param active
	 *            True whether tunnel point is active.
	 * @param seq
	 *            Number of sequence. Important because of tabulators.
	 */
	public void setActive(boolean active, int seq) {
		if (seq == 1) {
			System.out.println("							-> [Tunnel].setActive(true)");
			System.out.println("							<- [Tunnel].setActive(true)");
		} else if (seq == 2) {
			System.out.println("						-> [Tunnel].setActive(true)");
			System.out.println("						<- [Tunnel].setActive(true)");
		} else if (seq == 3) {
			System.out.println("        -> [Tunnel].setActive(false)");
			System.out.println("        <- [Tunnel].setActive(false)");
		} else if (seq == 2) {
			System.out.println("				-> [Tunnel].setActive(false)");
			System.out.println("				<- [Tunnel].setActive(false)");
		}
		isActive = active;
		return;
	}

	/**
	 * Get the next rail. Returns a Rail object what is the next one.
	 * 
	 * @param Previous
	 *            Rail object where train element was.
	 * @param Train
	 *            element which does move.
	 * @return Next rail object.
	 */
	public Rail getNextRail(Rail previous, Train_Element t) {
		if (previous == prevRail) {
			// ha aktív a tunnel és a következő rail alatt megy alagút, és még
			// nincs vonat az alagútban
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
