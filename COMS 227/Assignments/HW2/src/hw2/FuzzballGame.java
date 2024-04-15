
package hw2;

/**
 * Models a simplified baseball-like game called Fuzzball.
 * 
 * @author Abhaya Neupane
 */
public class FuzzballGame {
	/**
	 * Number of strikes causing a player to be out.
	 */
	public static final int MAX_STRIKES = 2;

	/**
	 * Number of balls causing a player to walk.
	 */
	public static final int MAX_BALLS = 5;

	/**
	 * Number of outs before the teams switch.
	 */
	public static final int MAX_OUTS = 3;

	// TODO: EVERTHING ELSE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.
	private int inning;
	private boolean topOfInning;
	private int team0Score;
	private int team1Score;
	private int ballCount;
	private int calledStrikes;
	private int currentOuts;
	private int bases;// Represents players on base
	private boolean base1 = false;
	private boolean base2 = false;
	private boolean base3 = false;

	public FuzzballGame(int initialInning) {
		initialInning = 1;
		this.inning = initialInning;
		this.topOfInning = true;
	}

// The methods below are provided for you and you should not modify them.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.
	/**
	 * Returns a three-character string representing the players on base, in the
	 * order first, second, and third, where 'X' indicates a player is present and
	 * 'o' indicates no player. For example, the string "oXX" means that there are
	 * players on second and third but not on first.
	 * 
	 * @return three-character string showing players on base
	 */
	public String getBases() {

		return (runnerOnBase(1) ? "X" : "o") + (runnerOnBase(2) ? "X" : "o") + (runnerOnBase(3) ? "X" : "o");
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * 
	 * <pre>
	 *      ooo Inning:1 [T] Score:0-0 Balls:0 Strikes:0 Outs:0
	 * </pre>
	 * 
	 * The first three characters represent the players on base as returned by the
	 * <code>getBases()</code> method. The 'T' after the inning number indicates
	 * it's the top of the inning, and a 'B' would indicate the bottom. The score
	 * always shows team 0 first.
	 * 
	 * @return a single line string representation of the state of the game
	 */
	public String toString() {
		String bases = getBases();
		String topOrBottom = (isTopOfInning() ? "T" : "B");
		String fmt = "%s Inning:%d [%s] Score:%d-%d Balls:%d Strikes:%d Outs:%d";
		return String.format(fmt, bases, whichInning(), topOrBottom, getTeam0Score(), getTeam1Score(), getBallCount(),
				getCalledStrikes(), getCurrentOuts());
	}

	/**
	 * Gets the current score of Team 1.
	 *
	 * @return The current score of Team 1.
	 */
	public int getTeam1Score() {
		return team1Score;
	}

	/**
	 * Gets the number of called strikes.
	 *
	 * @return The number of called strikes.
	 */
	public int getCalledStrikes() {
		return calledStrikes;
	}

	/**
	 * Gets the current number of outs.
	 *
	 * @return The current number of outs.
	 */
	public int getCurrentOuts() {
		return currentOuts;
	}

	/**
	 * Gets the current inning.
	 *
	 * @return The current inning.
	 */
	public int whichInning() {
		return inning;
	}

	/**
	 * Records a strike based on whether the batter swung. Increments the number of
	 * outs if the maximum number of strikes is reached. Automatically switches
	 * teams if the maximum number of outs is reached.
	 *
	 * @param swung True if the batter swung, false otherwise.
	 */
	public void strike(boolean swung) {
		if (gameEnded()) {
			return; // Exit the method
		}
		if (swung || calledStrikes == MAX_STRIKES - 1) {
			calledStrikes = 0; // Reset strikes
			ballCount = 0; // Reset ball count

			if (currentOuts < MAX_OUTS) { // Ensure outs don't exceed maximum
				currentOuts++; // Increment the number of outs if it's less than maximum
			} // Increment the number of outs
			if (currentOuts >= MAX_OUTS) {
				switchTeams();
			}

		} else {
			calledStrikes++;
		}

	}

	/**
	 * Records a caught fly. Increments the number of outs and resets the strike
	 * count to 0. Automatically switches teams if the maximum number of outs is
	 * reached.
	 */
	public void caughtFly() {
		currentOuts++;
		
		if (currentOuts >= MAX_OUTS) {
			switchTeams();
		}
		ballCount = 0;// Reset ball count after a hit
		calledStrikes = 0; // Reset strikes to 0
	}

	/**
	 * Records a hit with the given distance. Determines the number of bases and
	 * shifts runners accordingly. Increments the number of outs if the hit results
	 * in an out. Automatically switches teams if the maximum number of outs is
	 * reached.
	 *
	 * @param distance The distance of the hit.
	 */

	public void hit(int distance) {
		if (gameEnded()) {
			return;
		}

		if (distance < 15) {
			currentOuts++;
			if (currentOuts >= MAX_OUTS) {
				switchTeams();
			}
			return;
		}
		ballCount = 0;// Reset ball count after a hit
		calledStrikes = 0; // Reset strikes to 0

		// Determine the number of bases based on the hit distance
		int numBases = determineNumBases(distance);
		shiftRunners(numBases);
	}

	/**
	 * Determines the number of bases based on the hit distance.
	 *
	 * @param distance The distance of the hit.
	 * @return The number of bases earned from the hit.
	 */

	private int determineNumBases(int distance) {

		if (distance >= 250) {
			return 4; // Home run
		} else if (distance >= 200) {
			return 3; // Triple
		} else if (distance >= 150) {
			return 2; // Double
		} else if (distance >= 15) {
			return 1; // Single
		} else {
			return 0; // This should be treated as an out
		}
	}

	/**
	 * Shifts the baserunners based on the number of bases earned from a hit,
	 * calculates runs scored, and updates the game state accordingly.
	 *
	 * @param numBases The number of bases earned from a hit.
	 */
	private void shiftRunners(int numBases) {
		if (gameEnded()) {
			return;
		}
		ballCount = 0;// Reset ball count after a hit
		calledStrikes = 0; // Reset strikes to 0
		if (bases % 2 == 1)
			base1 = true;
		if ((bases / 2) % 2 == 1)
			base2 = true;
		if ((bases / 4) % 2 == 1)
			base3 = true;

		int runsScored = 0;
		// Shift the bases and calculate runs scored
		for (int i = 0; i < numBases; ++i) {
			if (base3) { // Check if there's a runner on third base
				runsScored++;
				base3 = false;
			}
			// Advance runners
			base3 = base2;
			base2 = base1;
			base1 = false;
		}

		// Place the hitter on the correct base
		if (numBases == 1)
			base1 = true;
		if (numBases == 2)
			base2 = true;
		if (numBases == 3)
			base3 = true;

		// Update the score based on which inning it is
		if (topOfInning) {
			team0Score += runsScored;
		} else {
			team1Score += runsScored;
		}

		// Handle home run separately to add an additional run for the hitter
		if (numBases == 4) {
			if (topOfInning) {
				team0Score++;
			} else {
				team1Score++;
			}
			clearBases(); // Clear the bases after a home run
		}
	}

	/**
	 * Records a ball thrown by the pitcher. Increments the ball count and shifts
	 * baserunners if the maximum number of balls is reached.
	 */
	public void ball() {
		if (gameEnded()) {
			return; // Exit the method
		}
		ballCount++;
		if (ballCount == MAX_BALLS) {
			ballCount = 0;
			shiftRunnersForWalk();
		}

	}

	/**
	 * Gets the current count of balls.
	 *
	 * @return The current count of balls.
	 */
	public int getBallCount() {
		return ballCount;
	}

	/**
	 * Gets the current score of Team 0.
	 *
	 * @return The current score of Team 0.
	 */
	public int getTeam0Score() {
		return team0Score;
	}

	/**
	 * Checks if it's the top of the inning.
	 *
	 * @return True if it's the top of the inning, false otherwise.
	 */
	public boolean isTopOfInning() {
		return topOfInning;
	}

	/**
	 * Checks if there is a runner on the specified base.
	 *
	 * @param base The base to check (1, 2, or 3).
	 * @return True if there is a runner on the specified base, false otherwise.
	 */

	public boolean runnerOnBase(int base) {
		switch (base) {
		case 1:
			return base1;
		case 2:
			return base2;
		case 3:
			return base3;
		default:
			return false; // Invalid base number
		}
	}

	/**
	 * Shifts baserunners after a walk and updates the game state accordingly.
	 */
	private void shiftRunnersForWalk() {
		if (gameEnded()) {
			return;
		}
		ballCount = 0;// Reset ball count after a hit
		calledStrikes = 0; // Reset strikes to 0

		if (base3 && base2 && base1) { // If all bases are occupied
			// If there's a runner on third base, increment the score
			if (topOfInning) {
				team0Score++;
			} else {
				team1Score++;
			}
			base3 = base2;
		}

		if (base2 && base1) { // If the first and second bases are occupied
			base3 = base2;
		}

		if (base1) { // If the first base is occupied
			base2 = base1;
		}

		base1 = true; // The batter is placed on base1 after the walk
	}

	/**
	 * Switches the teams at the end of an inning and updates the game state
	 * accordingly.
	 */
	private void switchTeams() {
		if (gameEnded()) {
			return;
		}

		currentOuts = 0;
		
		if (!topOfInning) {
			inning++;
		}
		clearBases();
		topOfInning = !topOfInning;
		ballCount = 0;
		calledStrikes = 0;
	}

	/**
	 * Clears all baserunners.
	 */
	private void clearBases() {
		ballCount = 0;// Reset ball count after a hit
		calledStrikes = 0; // Reset strike count after a hit
		base1 = false;
		base2 = false;
		base3 = false;
	}

	/**
	 * Checks if the game has ended.
	 *
	 * @return True if the game has ended, false otherwise.
	 */
	public boolean gameEnded() {

		return inning > 2;
	}
}
