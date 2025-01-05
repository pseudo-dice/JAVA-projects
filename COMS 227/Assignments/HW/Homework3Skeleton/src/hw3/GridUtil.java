package hw3;

import static api.Orientation.*;
import static api.CellType.*;

import java.util.ArrayList;

import api.Cell;

/**
 * Utilities for parsing string descriptions of a grid.
 * 
 * @author Abhaya Neupane
 */
public class GridUtil {

	/**
	 * Constructs a 2D grid of Cell objects given a 2D array of cell descriptions.
	 * String descriptions are a single character and have the following meaning.
	 * <ul>
	 * <li>"*" represents a wall.</li>
	 * <li>"e" represents an exit.</li>
	 * <li>"." represents a floor.</li>
	 * <li>"[", "]", "^", "v", or "#" represent a part of a block. A block is not a
	 * type of cell, it is something placed on a cell floor. For these descriptions
	 * a cell is created with CellType of FLOOR. This method does not create any
	 * blocks or set blocks on cells.</li>
	 * </ul>
	 * The method only creates cells and not blocks. See the other utility method
	 * findBlocks which is used to create the blocks.
	 * 
	 * @param desc a 2D array of strings describing the grid
	 * @return a 2D array of cells the represent the grid without any blocks present
	 */
	public static Cell[][] createGrid(String[][] desc) {
		int numRows = desc.length;
		int numCols = desc[0].length;
		Cell[][] grid = new Cell[numRows][numCols];

		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				char symbol = desc[r][c].charAt(0);
				switch (symbol) {
				case '*':
					grid[r][c] = new Cell(WALL, r, c);
					break;
				case 'e':
					grid[r][c] = new Cell(EXIT, r, c);
					break;
				case '.':
				case '[':
				case ']':
				case '^':
				case 'v':
				case '#':
					grid[r][c] = new Cell(FLOOR, r, c);
					break;
				default:
					throw new IllegalArgumentException("Invalid cell description: " + symbol);
				}
			}
		}

		return grid;
	}

	/**
	 * Returns a list of blocks that are constructed from a given 2D array of cell
	 * descriptions. String descriptions are a single character and have the
	 * following meanings.
	 * <ul>
	 * <li>"[" the start (left most column) of a horizontal block</li>
	 * <li>"]" the end (right most column) of a horizontal block</li>
	 * <li>"^" the start (top most row) of a vertical block</li>
	 * <li>"v" the end (bottom most column) of a vertical block</li>
	 * <li>"#" inner segments of a block, these are always placed between the start
	 * and end of the block</li>
	 * <li>"*", ".", and "e" symbols that describe cell types, meaning there is not
	 * block currently over the cell</li>
	 * </ul>
	 * 
	 * @param desc a 2D array of strings describing the grid
	 * @return a list of blocks found in the given grid description
	 */
	public static ArrayList<Block> findBlocks(String[][] desc) {
		ArrayList<Block> blocks = new ArrayList<>();
		int numRows = desc.length;
		int numCols = desc[0].length;
		boolean[][] visited = new boolean[numRows][numCols];

		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				if (!visited[r][c]) {
					char symbol = desc[r][c].charAt(0);
					if (symbol == '[') {
						int startCol = c;
						int endCol = c;
						while (endCol < numCols && desc[r][endCol].charAt(0) != ']') {
							visited[r][endCol] = true;
							endCol++;
						}
						endCol = Math.min(endCol, numCols - 1);
						blocks.add(new Block(r, startCol, endCol - startCol + 1, HORIZONTAL));
					} else if (symbol == '^') {
						int startRow = r;
						int endRow = r;
						while (endRow < numRows && desc[endRow][c].charAt(0) != 'v') {
							visited[endRow][c] = true;
							endRow++;
						}
						endRow = Math.min(endRow, numRows - 1);
						blocks.add(new Block(startRow, c, endRow - startRow + 1, VERTICAL));
					}
				}
			}
		}

		return blocks;
	}
}
