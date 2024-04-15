package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import api.BodySegment;
import api.Cell;
import api.Exit;
import api.Wall;

/**
 * Utility class with static methods for loading game files.
 */
public class GameFileUtil {
	/**
	 * Loads the file at the given file path into the given game object. When the
	 * method returns the game object has been modified to represent the loaded
	 * game.
	 * 
	 * @param filePath the path of the file to load
	 * @param game     the game to modify
	 */
	public static void load(String filePath, LizardGame game) {
		// TODO: method stub
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
			String line = reader.readLine();

			// Parse grid dimensions
			String[] dimensions = line.split("x");
			int rows = Integer.parseInt(dimensions[0]);
			int cols = Integer.parseInt(dimensions[1]);

			// Initialize grid
			game.resetGrid(rows, cols);

			// Parse grid cells
			for (int i = 0; i < rows; i++) {
				line = reader.readLine();
				for (int j = 0; j < cols; j++) {
					char cell = line.charAt(j);
					if (cell == 'W') {
						game.addWall(new Wall(new Cell(i, j)));
					} else if (cell == 'E') {
						game.addExit(new Exit(new Cell(i, j)));
					}
				}
			}
			line = reader.readLine();
			while (line != null) {
				String[] parts = line.split(" ");
				for (int i = 1; i < parts.length; i++) {
					String[] coordinates = parts[i].split(",");
					int row = Integer.parseInt(coordinates[0]);
					int col = Integer.parseInt(coordinates[1]);
					game.addLizard(new Lizard());
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			// Ignoring IOException, not recommended
		}
	}
}
