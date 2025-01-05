package hw3;

import static api.Direction.*;
import static api.Orientation.*;

import java.util.ArrayList;

import api.Cell;
import api.Direction;
import api.Move;

/**
 * Represents a board in the Block Slider game. A board contains a 2D grid of
 * cells and a list of blocks that slide over the cells.
 * 
 * @author Abhaya Neupane
 */
public class Board {
	/**
	 * 2D array of cells, the indexes signify (row, column) with (0, 0) representing
	 * the upper-left corner of the board.
	 */
	private Cell[][] grid;

	/**
	 * A list of blocks that are positioned on the board.
	 */
	private ArrayList<Block> blocks;

	/**
	 * A list of moves that have been made in order to get to the current position
	 * of blocks on the board.
	 */
	private ArrayList<Move> moveHistory;
	/**
	 * The block currently grabbed by the user.
	 */
	private Block grabbedBlock;
	/**
	 * The cell currently grabbed by the user.
	 */
	private Cell grabbedCell;
	/**
	 * The number of moves made so far in the game.
	 */
	private int moveCount;

	/**
	 * Constructs a new board from a given 2D array of cells and list of blocks. The
	 * cells of the grid should be updated to indicate which cells have blocks
	 * placed over them (i.e., setBlock() method of Cell). The move history should
	 * be initialized as empty.
	 * 
	 * @param grid   a 2D array of cells which is expected to be a rectangular shape
	 * @param blocks list of blocks already containing row-column position which
	 *               should be placed on the board
	 */
	public Board(Cell[][] grid, ArrayList<Block> blocks) {
		this.grid = grid;
		this.blocks = blocks;
		this.moveHistory = new ArrayList<>();
		this.moveCount = 0;

		// Update cells to indicate which have blocks
		for (Block block : blocks) {
			updateCellsWithBlock(block);
		}
	}

	/**
	 * Constructs a new board from a given 2D array of String descriptions.
	 * <p>
	 * DO NOT MODIFY THIS CONSTRUCTOR
	 * 
	 * @param desc 2D array of descriptions
	 */
	public Board(String[][] desc) {
		this(GridUtil.createGrid(desc), GridUtil.findBlocks(desc));
	}

	/**
	 * Models the user grabbing a block over the given row and column. The purpose
	 * of grabbing a block is for the user to be able to drag the block to a new
	 * position, which is performed by calling moveGrabbedBlock(). This method
	 * records two things: the block that has been grabbed and the cell at which it
	 * was grabbed.
	 * 
	 * @param row row to grab the block from
	 * @param col column to grab the block from
	 */
	public void grabBlockAtCell(int row, int col) {
		grabbedCell = grid[row][col];
		grabbedBlock = grabbedCell.getBlock();
	}

	/**
	 * Set the currently grabbed block to null.
	 */
	public void releaseBlock() {
		grabbedBlock = null;
		grabbedCell = null;
	}

	/**
	 * Returns the currently grabbed block.
	 * 
	 * @return the current block
	 */
	public Block getGrabbedBlock() {
		return grabbedBlock;
	}

	/**
	 * Returns the currently grabbed cell.
	 * 
	 * @return the current cell
	 */
	public Cell getGrabbedCell() {
		return grabbedCell;
	}

	/**
	 * Returns true if the cell at the given row and column is available for a block
	 * to be placed over it. Blocks can only be placed over floors and exits. A
	 * block cannot be placed over a cell that is occupied by another block.
	 * 
	 * @param row row location of the cell
	 * @param col column location of the cell
	 * @return true if the cell is available for a block, otherwise false
	 */
	public boolean canPlaceBlock(int row, int col) {
		Cell cell = grid[row][col];
		return (cell.isFloor() || cell.isExit()) && cell.getBlock() == null;
	}

	/**
	 * Returns the number of moves made so far in the game.
	 * 
	 * @return the number of moves
	 */
	public int getMoveCount() {
		return moveCount;
	}

	/**
	 * Returns the number of rows of the board.
	 * 
	 * @return number of rows
	 */
	public int getRowSize() {
		return grid.length;
	}

	/**
	 * Returns the number of columns of the board.
	 * 
	 * @return number of columns
	 */
	public int getColSize() {
		return grid[0].length;
	}

	/**
	 * Returns the cell located at a given row and column.
	 * 
	 * @param row the given row
	 * @param col the given column
	 * @return the cell at the specified location
	 */
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}

	/**
	 * Returns a list of all blocks on the board.
	 * 
	 * @return a list of all blocks
	 */
	public ArrayList<Block> getBlocks() {
		return new ArrayList<>(blocks);
	}

	/**
	 * Returns true if the player has completed the puzzle by positioning a block
	 * over an exit, false otherwise.
	 * 
	 * @return true if the game is over
	 */
	public boolean isGameOver() {
		for (Block block : blocks) {
			int row = block.getFirstRow();
			int col = block.getFirstCol();
			int length = block.getLength();

			// Check each cell the block occupies
			for (int i = 0; i < length; i++) {
				Cell cellToCheck;
				if (block.getOrientation() == HORIZONTAL) {
					cellToCheck = grid[row][col + i];
				} else {
					cellToCheck = grid[row + i][col];
				}

				// If any part of the block is over an exit, game is over
				if (cellToCheck.isExit()) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Moves the currently grabbed block by one cell in the given direction. A
	 * horizontal block is only allowed to move right and left and a vertical block
	 * is only allowed to move up and down. A block can only move over a cell that
	 * is a floor or exit and is not already occupied by another block. The method
	 * does nothing under any of the following conditions:
	 * <ul>
	 * <li>The game is over.</li>
	 * <li>No block is currently grabbed by the user.</li>
	 * <li>A block is currently grabbed by the user, but the block is not allowed to
	 * move in the given direction.</li>
	 * </ul>
	 * If none of the above conditions are meet, the method does the following:
	 * <ul>
	 * <li>Moves the block object by calling its move method.</li>
	 * <li>Sets the block for the grid cell that the block is being moved into.</li>
	 * <li>For the grid cell that the block is being moved out of, sets the block to
	 * null.</li>
	 * <li>Moves the currently grabbed cell by one cell in the same moved direction.
	 * The purpose of this is to make the currently grabbed cell move with the block
	 * as it is being dragged by the user.</li>
	 * <li>Adds the move to the end of the moveHistory list.</li>
	 * <li>Increment the count of total moves made in the game.</li>
	 * </ul>
	 * 
	 * @param dir the direction to move
	 */
	public void moveGrabbedBlock(Direction dir) {
		// Early returns remain the same
		if (isGameOver() || grabbedBlock == null)
			return;

		if ((grabbedBlock.getOrientation() == HORIZONTAL && (dir == UP || dir == DOWN))
				|| (grabbedBlock.getOrientation() == VERTICAL && (dir == LEFT || dir == RIGHT))) {
			return;
		}

		// Calculate new position
		int oldRow = grabbedBlock.getFirstRow();
		int oldCol = grabbedBlock.getFirstCol();
		int newRow = oldRow + (dir == DOWN ? 1 : dir == UP ? -1 : 0);
		int newCol = oldCol + (dir == RIGHT ? 1 : dir == LEFT ? -1 : 0);

		// Check if new position is valid
		int length = grabbedBlock.getLength();
		for (int i = 0; i < length; i++) {
			int checkRow = newRow + (grabbedBlock.getOrientation() == VERTICAL ? i : 0);
			int checkCol = newCol + (grabbedBlock.getOrientation() == HORIZONTAL ? i : 0);

			if (checkRow < 0 || checkRow >= getRowSize() || checkCol < 0 || checkCol >= getColSize()
					|| (!grid[checkRow][checkCol].isFloor() && !grid[checkRow][checkCol].isExit())
					|| (grid[checkRow][checkCol].getBlock() != null
							&& grid[checkRow][checkCol].getBlock() != grabbedBlock)) {
				return;
			}
		}

		// Clear old position first
		for (int i = 0; i < length; i++) {
			if (grabbedBlock.getOrientation() == HORIZONTAL) {
				grid[oldRow][oldCol + i].setBlock(null);
			} else {
				grid[oldRow + i][oldCol].setBlock(null);
			}
		}

		// Move the block
		grabbedBlock.move(dir);

		// Set new position
		for (int i = 0; i < length; i++) {
			if (grabbedBlock.getOrientation() == HORIZONTAL) {
				grid[newRow][newCol + i].setBlock(grabbedBlock);
			} else {
				grid[newRow + i][newCol].setBlock(grabbedBlock);
			}
		}

		// Update grabbed cell to new position
		grabbedCell = grid[newRow][newCol];

		// Update move history and count
		moveHistory.add(new Move(grabbedBlock, dir));
		moveCount++;

	}

	/**
	 * Resets the state of the game back to the start, which includes the move
	 * count, the move history, and whether the game is over. The method calls the
	 * reset method of each block object. It also updates each grid cells by calling
	 * their setBlock method to either set a block if one is located over the cell
	 * or set null if no block is located over the cell.
	 */
	public void reset() {
		moveCount = 0;
		moveHistory.clear();

		for (Block block : blocks) {
			updateCellsWithBlock(block, null);
			block.reset();
			updateCellsWithBlock(block);
		}

		releaseBlock();
	}

	/**
	 * Returns a list of all legal moves that can be made by any block on the
	 * current board. If the game is over there are no legal moves.
	 * 
	 * @return a list of legal moves
	 */
	public ArrayList<Move> getAllPossibleMoves() {
		ArrayList<Move> possibleMoves = new ArrayList<>();

		if (isGameOver()) {
			return possibleMoves;
		}

		for (Block block : blocks) {
			// Store original position
			int originalRow = block.getFirstRow();
			int originalCol = block.getFirstCol();

			// Try each direction
			for (Direction dir : Direction.values()) {
				// Skip invalid directions based on orientation
				if (block.getOrientation() == HORIZONTAL && (dir == UP || dir == DOWN)) {
					continue;
				}
				if (block.getOrientation() == VERTICAL && (dir == LEFT || dir == RIGHT)) {
					continue;
				}

				// Calculate new position
				int newRow = originalRow;
				int newCol = originalCol;

				switch (dir) {
				case UP:
					newRow--;
					break;
				case DOWN:
					newRow++;
					break;
				case LEFT:
					newCol--;
					break;
				case RIGHT:
					newCol++;
					break;
				}

				// Check if move is valid
				boolean validMove = true;

				// Check bounds and availability for all cells the block would occupy
				for (int i = 0; i < block.getLength(); i++) {
					int checkRow = newRow;
					int checkCol = newCol;

					if (block.getOrientation() == HORIZONTAL) {
						checkCol += i;
					} else {
						checkRow += i;
					}

					// Check if out of bounds
					if (checkRow < 0 || checkRow >= getRowSize() || checkCol < 0 || checkCol >= getColSize()) {
						validMove = false;
						break;
					}

					// Check if cell is occupied by another block
					Cell cell = grid[checkRow][checkCol];
					if (!cell.isFloor() && !cell.isExit()) {
						validMove = false;
						break;
					}

					// If cell has a block, make sure it's not our current block
					if (cell.getBlock() != null && cell.getBlock() != block) {
						validMove = false;
						break;
					}
				}

				if (validMove) {
					possibleMoves.add(new Move(block, dir));
				}
			}
		}

		return possibleMoves;

	}

	/**
	 * Gets the list of all moves performed to get to the current position on the
	 * board.
	 * 
	 * @return a list of moves performed to get to the current position
	 */
	public ArrayList<Move> getMoveHistory() {
		return new ArrayList<>(moveHistory);
	}

	/**
	 * EXTRA CREDIT 5 POINTS
	 * <p>
	 * This method is only used by the Solver.
	 * <p>
	 * Undo the previous move. The method gets the last move on the moveHistory list
	 * and performs the opposite actions of that move, which are the following:
	 * <ul>
	 * <li>grabs the moved block and calls moveGrabbedBlock passing the opposite
	 * direction</li>
	 * <li>decreases the total move count by two to undo the effect of calling
	 * moveGrabbedBlock twice</li>
	 * <li>if required, sets is game over to false</li>
	 * <li>removes the move from the moveHistory list</li>
	 * </ul>
	 * If the moveHistory list is empty this method does nothing.
	 */
	public void undoMove() {

		if (moveHistory.isEmpty()) {
			return;
		}

		// Get and remove the last move
		Move lastMove = moveHistory.remove(moveHistory.size() - 1);
		Block movedBlock = lastMove.getBlock();
		Direction oppositeDir = getOppositeDirection(lastMove.getDirection());

		// Update cells directly
		// Clear old position
		updateCellsWithBlock(movedBlock, null);

		// Move the block
		movedBlock.move(oppositeDir);

		// Update new position
		updateCellsWithBlock(movedBlock, movedBlock);

		// Decrement move count
		moveCount--;

	}

	/**
	 * Returns the opposite direction of the given direction.
	 *
	 * @param dir the input direction
	 * @return the opposite direction
	 * @throws IllegalArgumentException if an unknown direction is provided
	 */

	private Direction getOppositeDirection(Direction dir) {
		switch (dir) {
		case UP:
			return Direction.DOWN;
		case DOWN:
			return Direction.UP;
		case LEFT:
			return Direction.RIGHT;
		case RIGHT:
			return Direction.LEFT;
		default:
			throw new IllegalArgumentException("Unknown direction: " + dir);
		}
	}

	/**
	 * Updates the cells occupied by a block with the block itself.
	 *
	 * @param block the block to update cells with
	 */
	private void updateCellsWithBlock(Block block) {
		updateCellsWithBlock(block, block);
	}

	/**
	 * Updates the cells occupied by a block with a specified value.
	 *
	 * @param block the block defining the cells to update
	 * @param value the value to set for the cells (can be null to clear the cells)
	 */
	private void updateCellsWithBlock(Block block, Block value) {
		int row = block.getFirstRow();
		int col = block.getFirstCol();
		int length = block.getLength();

		for (int i = 0; i < length; i++) {
			if (block.getOrientation() == HORIZONTAL) {
				grid[row][col + i].setBlock(value);
			} else {
				grid[row + i][col].setBlock(value);
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		boolean first = true;
		for (Cell row[] : grid) {
			if (!first) {
				buff.append("\n");
			} else {
				first = false;
			}
			for (Cell cell : row) {
				buff.append(cell.toString());
				buff.append(" ");
			}
		}
		return buff.toString();
	}
}
