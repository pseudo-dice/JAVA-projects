package hw3;

import static api.Direction.*;

import java.util.ArrayList;

import api.BodySegment;
import api.Cell;
import api.Direction;


/**
 * Represents a Lizard as a collection of body segments.
 */
public class Lizard {
	 private int age;
	private ArrayList<BodySegment> segments;
	
	/**
	 * Constructs a Lizard object.
	 */
	public Lizard() {
		// TODO: method stub
		this.age = 0;
	}

	/**
	 * Sets the segments of the lizard. Segments should be ordered from tail to
	 * head.
	 * 
	 * @param segments list of segments ordered from tail to head
	 */
	public void setSegments(ArrayList<BodySegment> segments) {
		// TODO: method stub
		this.segments = segments;
	}

	/**
	 * Gets the segments of the lizard. Segments are ordered from tail to head.
	 * 
	 * @return a list of segments ordered from tail to head
	 */
	public ArrayList<BodySegment> getSegments() {
		// TODO: method stub
		return this.segments;
	}

	/**
	 * Gets the head segment of the lizard. Returns null if the segments have not
	 * been initialized or there are no segments.
	 * 
	 * @return the head segment
	 */
	public BodySegment getHeadSegment() {
		// TODO: method stub
		if (this.segments == null || this.segments.isEmpty()) {
	        return null;
	    } else {
	        return this.segments.get(this.segments.size() - 1);
	    }
	}

	/**
	 * Gets the tail segment of the lizard. Returns null if the segments have not
	 * been initialized or there are no segments.
	 * 
	 * @return the tail segment
	 */
	public BodySegment getTailSegment() {
		 if (this.segments == null || this.segments.isEmpty()) {
		        return null;
		    } else {
		        return this.segments.get(0);
		    }
	}

	/**
	 * Gets the segment that is located at a given cell or null if there is no
	 * segment at that cell.
	 * 
	 * @param cell to look for lizard
	 * @return the segment that is on the cell or null if there is none
	 */
	public BodySegment getSegmentAt(Cell cell) {
		    for (BodySegment segment : this.segments) {
		        if (segment.getCell().equals(cell)) {
		            return segment;
		        }
		    }
		
		return null;
	}

	/**
	 * Get the segment that is in front of (closer to the head segment than) the
	 * given segment. Returns null if there is no segment ahead.
	 * 
	 * @param segment the starting segment
	 * @return the segment in front of the given segment or null
	 */
	public BodySegment getSegmentAhead(BodySegment segment) {
		 int index = segments.indexOf(segment);
		    if (index != -1 && index < segments.size() - 1) {
		        return segments.get(index + 1);
		    }
		return null;
	}

	/**
	 * Get the segment that is behind (closer to the tail segment than) the given
	 * segment. Returns null if there is not segment behind.
	 * 
	 * @param segment the starting segment
	 * @return the segment behind of the given segment or null
	 */
	public BodySegment getSegmentBehind(BodySegment segment) {
		int index = segments.indexOf(segment);
	    if (index > 0) {
	        return segments.get(index - 1);
	    }
		return null;
	}

	/**
	 * Gets the direction from the perspective of the given segment point to the
	 * segment ahead (in front of) of it. Returns null if there is no segment ahead
	 * of the given segment.
	 * 
	 * @param segment the starting segment
	 * @return the direction to the segment ahead of the given segment or null
	 */
	public Direction getDirectionToSegmentAhead(BodySegment segment) {
		int index = segments.indexOf(segment);
		if (index != -1 && index < segments.size() - 1) {
			Cell currentCell = segment.getCell();
			Cell aheadCell = segments.get(index + 1).getCell();
	
			// Calculate the difference in the x and y coordinates
			int dx = aheadCell.getCol() - currentCell.getCol();
			int dy = aheadCell.getRow() - currentCell.getRow();
	
			// Determine the direction based on the difference
			if (dx > 0) return Direction.RIGHT; // Moving right
			if (dx < 0) return Direction.LEFT;  // Moving left
			if (dy > 0) return Direction.DOWN;  // Moving down
			if (dy < 0) return Direction.UP;    // Moving up
		}
		return null;
	}

	/**
	 * Gets the direction from the perspective of the given segment point to the
	 * segment behind it. Returns null if there is no segment behind of the given
	 * segment.
	 * 
	 * @param segment the starting segment
	 * @return the direction to the segment behind of the given segment or null
	 */
	public Direction getDirectionToSegmentBehind(BodySegment segment) {
	    int index = segments.indexOf(segment);
	    if (index > 0) {
	        Cell currentCell = segment.getCell();
	        Cell behindCell = segments.get(index - 1).getCell();

	        int dCol = currentCell.getCol() - behindCell.getCol();
	        int dRow = currentCell.getRow() - behindCell.getRow();

	        if (dCol > 0) return Direction.LEFT;
	        if (dCol < 0) return Direction.RIGHT;
	        if (dRow > 0) return Direction.UP;
	        if (dRow < 0) return Direction.DOWN;
	    }
	    return null;
	}

	/**
	 * Gets the direction in which the head segment is pointing. This is the
	 * direction formed by going from the segment behind the head segment to the
	 * head segment. A lizard that does not have more than one segment has no
	 * defined head direction and returns null.
	 * 
	 * @return the direction in which the head segment is pointing or null
	 */
	public Direction getHeadDirection() {
	    if (segments.size() > 1) {
	        Cell headCell = segments.get(segments.size() - 1).getCell(); // Head segment
	        Cell behindHeadCell = segments.get(segments.size() - 2).getCell(); // Segment behind the head

	        // Calculate the difference in the col and row
	        int dCol = headCell.getCol() - behindHeadCell.getCol();
	        int dRow = headCell.getRow() - behindHeadCell.getRow();

	        // Determine the direction based on the difference
	        if (dCol > 0) return Direction.RIGHT;
	        if (dCol < 0) return Direction.LEFT;
	        if (dRow > 0) return Direction.DOWN; // Moving down means an increase in row value
	        if (dRow < 0) return Direction.UP; // Moving up means a decrease in row value
	    }
	    return null;
	}

	/**
	 * Gets the direction in which the tail segment is pointing. This is the
	 * direction formed by going from the segment ahead of the tail segment to the
	 * tail segment. A lizard that does not have more than one segment has no
	 * defined tail direction and returns null.
	 * 
	 * @return the direction in which the tail segment is pointing or null
	 */
	public Direction getTailDirection() {
	    if (segments.size() > 1) {
	        Cell tailCell = segments.get(0).getCell();
	        Cell aheadOfTailCell = segments.get(1).getCell();

	        int dCol = aheadOfTailCell.getCol() - tailCell.getCol();
	        int dRow = aheadOfTailCell.getRow() - tailCell.getRow();

	        if (dCol > 0) return Direction.LEFT;
	        if (dCol < 0) return Direction.RIGHT;
	        if (dRow > 0) return Direction.UP;
	        if (dRow < 0) return Direction.DOWN;
		}
		return null;
	}

	@Override
	public String toString() {
		String result = "";
		for (BodySegment seg : getSegments()) {
			result += seg + " ";
		}
		return result;
	}
}
