package hw1;

/**
 * A Printer class that simulates a simple printer's behavior, including adding
 * and removing paper, printing pages, and managing the tray. The printer can
 * track the total number of pages printed and handle limited tray capacity.
 * 
 * The class allows starting print jobs, tracking the next page to be printed,
 * and managing the number of available sheets in the tray.
 * 
 * @author Abhaya Neupane
 */

public class Printer {

	/**
	 * The current number of sheets in the printer tray.
	 * 
	 * This value decreases as pages are printed and cannot go below zero.
	 */
	private int sheets;

	/**
	 * The maximum number of sheets the tray can hold.
	 * 
	 * This value defines the upper limit for the sheets field.
	 */
	private int trayCapacity;

	/**
	 * The total number of pages printed by the printer since its construction.
	 * 
	 * This value is cumulative and increases every time a page is successfully
	 * printed.
	 */
	private int totalPages;

	/**
	 * The next page number to be printed from the document.
	 * 
	 * This value cycles through the total number of pages in the document,
	 * resetting to the first page after the last one is printed.
	 */
	private int nextPage;

	/**
	 * The total number of pages in the current document being printed.
	 * 
	 * This value is used to determine the cycle of the nextPage field.
	 */
	private int documentPages;

	/**
	 * The number of sheets available to be loaded back into the tray when replaced.
	 * 
	 * This value represents how many sheets are ready to be reloaded into the tray
	 * after removing or adding paper.
	 */
	private int availableSheets;

	/**
	 * Constructs a new Printer with the specified maximum tray capacity.
	 * 
	 * The tray is initially empty, and the printer has not printed any pages.
	 *
	 * @param trayCapacity the maximum number of paper sheets the tray can hold
	 */
	public Printer(int trayCapacity) {
		this.trayCapacity = trayCapacity;
		this.totalPages = 0;
		this.sheets = 0;
		this.availableSheets = 0;
	}

	/**
	 * Starts a new print job to make copies of a document with the specified page
	 * length.
	 * 
	 * This method resets the nextPage to 0, which represents the first page of the
	 * document.
	 *
	 *
	 * @param documentPages the number of pages in the document to be printed
	 */

	public void startPrintJob(int documentPages) {
		this.documentPages = documentPages;
		this.nextPage = 0;

	}

	/**
	 * Returns the number of sheets currently available in the tray for printing.
	 *
	 * @return the number of sheets available for printing
	 */
	public int getSheetsAvailable() {
		return sheets;
	}

	/**
	 * Returns the next page number of the document that will be printed.
	 *
	 * This method returns the index of the next page to be printed, where the first
	 * page is represented by 0.
	 * 
	 *
	 * @return the next page number of the document to be printed
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * Returns the total number of pages printed by the printer since its
	 * construction.
	 *
	 * This value is cumulative and reflects all pages printed by the printer over
	 * its lifetime.
	 *
	 * @return the total number of pages printed by the printer since its
	 *         construction
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * Simulates printing a page from the document if paper is available. It updates
	 * the total number of printed pages, the page number for the next page to be
	 * printed, and the number of available sheets.
	 *
	 * If there are no available sheets, the method does not print any pages. It
	 * ensures that at most one page is printed during each call, even if multiple
	 * sheets are available.
	 * 
	 */
	public void printPage() {
		int sheetsToPrint = Math.min(sheets, 1);
		sheets = Math.max(sheets - sheetsToPrint, 0);
		totalPages += sheetsToPrint;
		nextPage = (nextPage + sheetsToPrint) % documentPages;
	}

	/**
	 * Removes the paper tray from the printer, making the number of available
	 * sheets zero.
	 *
	 * The current number of sheets in the tray is stored in availableSheets before
	 * the tray is removed, and sheets is set to 0, indicating the tray is empty.
	 *
	 */
	public void removeTray() {
		this.availableSheets = this.sheets;
		sheets = 0;
	}

	/**
	 * Replaces the tray in the printer, restoring the number of available sheets.
	 *
	 * The number of sheets in the tray is restored to either the previous amount of
	 * available sheets or the tray capacity, whichever is smaller.
	 *
	 */
	public void replaceTray() {
		this.sheets = Math.min(availableSheets, trayCapacity);
	}

	/**
	 * Simulates removing the tray, adding the specified number of sheets (up to the
	 * maximum capacity of the tray), and replacing the tray in the printer.
	 *
	 * The tray can only hold a limited number of sheets, so the method adds the
	 * given number of sheets but ensures the total number does not exceed the
	 * tray's capacity.
	 *
	 *
	 * @param sheets the number of sheets to add to the tray
	 */
	public void addPaper(int sheets) {

		this.sheets = Math.min(trayCapacity, availableSheets + sheets);
		this.availableSheets = this.sheets;
		replaceTray();
	}

	/**
	 * Simulates removing the tray, subtracting the specified number of sheets
	 * (without allowing the total to fall below zero), and replacing the tray in
	 * the printer.
	 *
	 * If more sheets are removed than are available, the sheet count will be set to
	 * zero.
	 *
	 *
	 * @param sheets the number of sheets to remove from the tray
	 */
	public void removePaper(int sheets) {
		this.sheets = Math.max(this.availableSheets - sheets, 0);
		this.availableSheets = this.sheets;
		replaceTray();
	}

}
