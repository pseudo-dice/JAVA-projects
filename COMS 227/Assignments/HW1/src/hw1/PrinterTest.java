package hw1;



public class PrinterTest {
	public static void main(String[] args) {
        // Create a printer with a tray capacity of 10
        Printer printer = new Printer(10);

        // Add 8 sheets to the printer
        printer.addPaper(8);
        System.out.println("Sheets available after adding 8 sheets: " + printer.getSheetsAvailable() + " (Expected: 8)");

        // Remove the tray
        printer.removeTray();
        System.out.println("Sheets available after removing the tray: " + printer.getSheetsAvailable() + " (Expected: 0)");

        // Add 2 sheets to the printer
        printer.addPaper(2);
        System.out.println("Sheets available after adding 2 sheets: " + printer.getSheetsAvailable() + " (Expected: 2)");

        // Remove 3 sheets from the printer
        printer.removePaper(3);
        System.out.println("Sheets available after removing 3 sheets: " + printer.getSheetsAvailable() + " (Expected: 0)");

        // Replace the tray
        printer.replaceTray();
        System.out.println("Sheets available after replacing the tray: " + printer.getSheetsAvailable() + " (Expected: 7)");
    }
}
