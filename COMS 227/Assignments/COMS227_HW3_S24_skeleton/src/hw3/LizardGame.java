package hw3;

import static api.Direction.*;

import java.io.IOException;
import java.util.ArrayList;

import api.BodySegment;
import api.Cell;
import api.Direction;
import api.Exit;
import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Wall;

/**
 * Class that models a game.
 */
public class LizardGame {
	private ShowDialogListener dialogListener;
	private ScoreUpdateListener scoreListener;
	 private int width;
	 private int height;
	 private Cell[][] grid;

	/**
	 * Constructs a new LizardGame object with given grid dimensions.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	public LizardGame(int width, int height) {
		// TODO: method stub
		 this.width = width;
	        this.height = height;
	        this.grid = new Cell[height][width];

	        // Initialize the grid with new cells
	        for (int i = 0; i < height; i++) {
	            for (int j = 0; j < width; j++) {
	                this.grid[i][j] = new Cell(i, j);
	            }
	        }
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width of the grid
	 */
	public int getWidth() {
		// TODO: method stub
		return this.width;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height of the grid
	 */
	public int getHeight() {
		// TODO: method stub
		return this.height;
	}

	/**
	 * Adds a wall to the grid.
	 * 
	 * @param wall to add
	 */
	public void addWall(Wall wall) {
		// TODO: method stub
		Cell cell = wall.getCell();
	    int row = cell.getRow();
	    int col = cell.getCol();
	    if (row >= 0 && row < height && col >= 0 && col < width) {
	        grid[row][col].placeWall(wall);
	    }
	}

	/**
	 * Adds an exit to the grid.
	 * 
	 * @param exit to add
	 */
	public void addExit(Exit exit) {
		// TODO: method stub
		 Cell cell = exit.getCell();
		    int row = cell.getRow();
		    int col = cell.getCol();
		    if (row >= 0 && row < height && col >= 0 && col < width) {
		        grid[row][col].placeExit(exit);
		    }
	}

	public ArrayList<Lizard> getLizards() {
		ArrayList<Lizard> lizards = new ArrayList<>();
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            Lizard lizard = grid[i][j].getLizard();
	            if (lizard != null) {
	                lizards.add(lizard);
	            }
	        }
	    }
	    return lizards;
	}
	

	/**
	 * Adds the given lizard to the grid.
	 * <p>
	 * The scoreListener to should be updated with the number of lizards.
	 * 
	 * @param lizard to add
	 */
	public void addLizard(Lizard lizard) {
		boolean placed = false;
	    for (int row = 0; row < height && !placed; row++) {
	        for (int col = 0; col < width && !placed; col++) {
	            if (grid[row][col].getLizard() == null) {
	                grid[row][col].placeLizard(lizard);
	                if (scoreListener != null) {
	                    scoreListener.updateScore(getLizards().size());
	                }
	                placed = true;
	            }
	        }
	    }

	}

	/**
	 * Removes the given lizard from the grid. Be aware that each cell object knows
	 * about a lizard that is placed on top of it. It is expected that this method
	 * updates all cells that the lizard used to be on, so that they now have no
	 * lizard placed on them.
	 * <p>
	 * The scoreListener to should be updated with the number of lizards using
	 * updateScore().
	 * 
	 * @param lizard to remove
	 */
	public void removeLizard(Lizard lizard) {
		for (int row = 0; row < height; row++) {
	        for (int col = 0; col < width; col++) {
	            if (grid[row][col].getLizard() == lizard) {
	                grid[row][col].removeLizard();
	                if (scoreListener != null) {
	                    scoreListener.updateScore(getLizards().size());
	                }
	                return;
	            }
	        }
	    }
	}

	/**
	 * Gets the cell for the given column and row.
	 * <p>
	 * If the column or row are outside of the boundaries of the grid the method
	 * returns null.
	 * 
	 * @param col column of the cell
	 * @param row of the cell
	 * @return the cell or null
	 */
	public Cell getCell(int col, int row) {
		// TODO: method stub
		 if (row >= 0 && row < height && col >= 0 && col < width) {
		        return grid[row][col];
		    } else {
		        return null;
		    }
	}

	/**
	 * Gets the cell that is adjacent to (one over from) the given column and row,
	 * when moving in the given direction. For example (1, 4, UP) returns the cell
	 * at (1, 3).
	 * <p>
	 * If the adjacent cell is outside of the boundaries of the grid, the method
	 * returns null.
	 * 
	 * @param col the given column
	 * @param row the given row
	 * @param dir the direction from the given column and row to the adjacent cell
	 * @return the adjacent cell or null
	 */
	public Cell getAdjacentCell(int col, int row, Direction dir) {
		// TODO: method stub
		switch (dir) {
        case UP:
            row--;
            break;
        case DOWN:
            row++;
            break;
        case LEFT:
            col--;
            break;
        case RIGHT:
            col++;
            break;
    }
    if (row >= 0 && row < height && col >= 0 && col < width) {
        return grid[row][col];
    } else {
        return null;
    }
	}

	/**
	 * Resets the grid. After calling this method the game should have a grid of
	 * size width x height containing all empty cells. Empty means cells with no
	 * walls, exits, etc.
	 * <p>
	 * All lizards should also be removed from the grid.
	 * 
	 * @param width  number of columns of the resized grid
	 * @param height number of rows of the resized grid
	 */
	public void resetGrid(int width, int height) {
		// TODO: method stub
		this.grid = new Cell[height][width];
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            this.grid[i][j] = new Cell(i, j);
	        }
	    }
	}
	

	/**
	 * Returns true if a given cell location (col, row) is available for a lizard to
	 * move into. Specifically the cell cannot contain a wall or a lizard. Any other
	 * type of cell, including an exit is available.
	 * 
	 * @param row of the cell being tested
	 * @param col of the cell being tested
	 * @return true if the cell is available, false otherwise
	 */
	public boolean isAvailable(int col, int row) {
		// TODO: method stub
		 if (row >= 0 && row < height && col >= 0 && col < width) {
		        Cell cell = grid[row][col];
		        return cell.getWall() == null && cell.getLizard() == null;
		    } else {
		        return false;
		    }
	}

	/**
	 * Move the lizard specified by its body segment at the given position (col,
	 * row) one cell in the given direction. The entire body of the lizard must move
	 * in a snake like fashion, in other words, each body segment pushes and pulls
	 * the segments it is connected to forward or backward in the path of the
	 * lizard's body. The given direction may result in the lizard moving its body
	 * either forward or backward by one cell.
	 * <p>
	 * The segments of a lizard's body are linked together and movement must always
	 * be "in-line" with the body. It is allowed to implement movement by either
	 * shifting every body segment one cell over or by creating a new head or tail
	 * segment and removing an existing head or tail segment to achieve the same
	 * effect of movement in the forward or backward direction.
	 * <p>
	 * If any segment of the lizard moves over an exit cell, the lizard should be
	 * removed from the grid.
	 * <p>
	 * If there are no lizards left on the grid the player has won the puzzle the
	 * the dialog listener should be used to display (see showDialog) the message
	 * "You win!".
	 * <p>
	 * It is possible that the given direction is not in-line with the body of the
	 * lizard (as described above), in that case this method should do nothing.
	 * <p>
	 * It is possible that the given column and row are outside the bounds of the
	 * grid, in that case this method should do nothing.
	 * <p>
	 * It is possible that there is no lizard at the given column and row, in that
	 * case this method should do nothing.
	 * <p>
	 * It is possible that the lizard is blocked and cannot move in the requested
	 * direction, in that case this method should do nothing.
	 * <p>
	 * <b>Developer's note: You may have noticed that there are a lot of details
	 * that need to be considered when implement this method method. It is highly
	 * recommend to explore how you can use the public API methods of this class,
	 * Grid and Lizard (hint: there are many helpful methods in those classes that
	 * will simplify your logic here) and also create your own private helper
	 * methods. Break the problem into smaller parts are work on each part
	 * individually.</b>
	 * 
	 * @param col the given column of a selected segment
	 * @param row the given row of a selected segment
	 * @param dir the given direction to move the selected segment
	 */
	public void move(int col, int row, Direction dir) {
		// Check if the initial position is within the grid bounds
	    if (row < 0 || row >= height || col < 0 || col >= width) {
	        return; // Out of bounds, do nothing
	    }

	    Cell cell = grid[row][col];
	    Lizard lizard = cell.getLizard();

	    // Check if there's a lizard at the specified position
	    if (lizard == null) {
	        return; // No lizard at the given position, do nothing
	    }

	    // Calculate the new position based on the direction
	    int newRow = row + (dir == Direction.UP ? -1 : (dir == Direction.DOWN ? 1 : 0));
	    int newCol = col + (dir == Direction.LEFT ? -1 : (dir == Direction.RIGHT ? 1 : 0));

	    // Check if the new position is within the grid bounds
	    if (newRow < 0 || newRow >= height || newCol < 0 || newCol >= width) {
	        return; // New position out of bounds, do nothing
	    }

	    Cell newCell = grid[newRow][newCol];

	    // Check if the new position is available (not blocked by a wall or another lizard)
	    if (!isAvailable(newCol, newRow)) {
	        return; // New position not available, do nothing
	    }

	    // Move the lizard to the new position
	    newCell.placeLizard(lizard);
	    cell.removeLizard();

	    // If the new position is an exit, remove the lizard from the game
	    if (newCell.getExit() != null) {
	        removeLizard(lizard);
	    }

	    // Check if there are no more lizards left on the grid
	    if (getLizards().isEmpty() && dialogListener != null) {
	        dialogListener.showDialog("You win!");
	    }
		}
	

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 * @throws IOException 
	 */
	public void load(String filePath) {
		GameFileUtil.load(filePath, this);
	}

	@Override
	public String toString() {
		String str = "---------- GRID ----------\n";
		str += "Dimensions:\n";
		str += getWidth() + " " + getHeight() + "\n";
		str += "Layout:\n";
		for (int y = 0; y < getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			for (int x = 0; x < getWidth(); x++) {
				str += getCell(x, y);
			}
		}
		str += "\nLizards:\n";
		for (Lizard l : getLizards()) {
			str += l;
		}
		str += "\n--------------------------\n";
		return str;
	}
}
