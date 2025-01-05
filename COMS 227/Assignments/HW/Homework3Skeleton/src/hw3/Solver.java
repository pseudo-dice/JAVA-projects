package hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import api.Move;

/**
 * A puzzle solver for the the Block Slider game.
 * <p>
 * THE ONLY METHOD YOU ARE CHANGING IN THIS CLASS IS solve().
 * 
 * @author Abhaya Neupane
 */
public class Solver {
	/**
	 * Maximum number of moves allowed in the search.
	 */
	private int maxMoves;

	/**
	 * Associates a string representation of a grid with the move count required to
	 * reach that grid layout.
	 */
	private Map<String, Integer> seen = new HashMap<String, Integer>();

	/**
	 * All solutions found in this search.
	 */
	private ArrayList<ArrayList<Move>> solutions = new ArrayList<ArrayList<Move>>();

	/**
	 * Constructs a solver with the given maximum number of moves.
	 * 
	 * @param givenMaxMoves maximum number of moves
	 */
	public Solver(int givenMaxMoves) {
		maxMoves = givenMaxMoves;
		solutions = new ArrayList<ArrayList<Move>>();
	}

	/**
	 * Returns all solutions found in the search. Each solution is a list of moves.
	 * 
	 * @return list of all solutions
	 */
	public ArrayList<ArrayList<Move>> getSolutions() {
		return solutions;
	}

	/**
	 * Prints all solutions found in the search.
	 */
	public void printSolutions() {
		for (ArrayList<Move> moves : solutions) {
			System.out.println("Solution:");
			for (Move move : moves) {
				System.out.println(move);
			}
			System.out.println();
		}
	}

	/**
	 * EXTRA CREDIT 15 POINTS
	 * <p>
	 * Recursively search for solutions to the given board instance according to the
	 * algorithm described in the assignment pdf. This method does not return
	 * anything its purpose is to update the instance variable solutions with every
	 * solution found.
	 * 
	 * @param board any instance of Board
	 */
	public void solve(Board board) {
		solve(board, new ArrayList<>(), 0);
	}

	/**
	 * Helper method for recursive solving of the puzzle.
	 * 
	 * @param board        current state of the board
	 * @param currentMoves list of moves made so far
	 * @param moveCount    number of moves made so far
	 */
	private void solve(Board board, ArrayList<Move> currentMoves, int moveCount) {
		// Base case: Check if the game is over (winning state)
		if (board.isGameOver()) {
			// Create a deep copy of the current moves before adding to solutions
			ArrayList<Move> solution = new ArrayList<>(currentMoves);
			solutions.add(solution);
			return;
		}

		// Check if we've exceeded the maximum number of moves
		if (moveCount >= maxMoves) {
			return;
		}

		// Get the current board state
		String boardString = board.toString();

		// Check if we've seen this board state before with a lower or equal move count
		if (seen.containsKey(boardString) && seen.get(boardString) <= moveCount) {
			return;
		}

		// Mark this board state as seen
		seen.put(boardString, moveCount);

		// Get all possible moves before modifying the board
		ArrayList<Move> possibleMoves = board.getAllPossibleMoves();

		// Try each possible move
		for (Move move : possibleMoves) {
			// Grab the block before moving
			board.grabBlockAtCell(move.getBlock().getFirstRow(), move.getBlock().getFirstCol());

			// Apply the move
			board.moveGrabbedBlock(move.getDirection());
			currentMoves.add(move);

			// Recursively solve from this new state
			solve(board, currentMoves, moveCount + 1);

			// Undo the move (backtrack)
			board.undoMove();
			currentMoves.remove(currentMoves.size() - 1);
		}
	}
}
