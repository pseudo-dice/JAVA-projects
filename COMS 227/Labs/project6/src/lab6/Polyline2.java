package lab6;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import plotter.Plotter;
import plotter.Polyline;

public class Polyline2 {
	
	private static Polyline parseOneLine(String line) {
		   Scanner sc = new Scanner(line);
		    int thickness = 1; // Default thickness value
		    String color =null;

		    if (sc.hasNextInt()) {
		        thickness = sc.nextInt(); // If the next token is an integer, it's thickness
		        color = sc.next(); // Read the color after thickness
		    } else {
		        color = sc.next(); // If the first value is not an integer, it's assumed to be color
		    }

		    Polyline pl = new Polyline(color,thickness);
		    while (sc.hasNextInt()) {
		        int x = sc.nextInt();
		        int y = sc.nextInt();
		        pl.addPoint(new Point(x,y));
		    }
		    sc.close(); // Close the scanner to avoid resource leaks

		    return pl;
		}

//	 public static void main(String[] args)
//	  {
//	    Plotter plotter = new Plotter();
//	    Polyline p = parseOneLine("red 100 100 200 100 200 200 100 200 100 100");
//	    plotter.plot(p);
//	    
//	    p = parseOneLine("2 blue 250 100 400 350 100 350 250 100");
//	    plotter.plot(p);
//	  }
	 private static ArrayList<Polyline> readFile(String filename)
		      throws FileNotFoundException
		  {
		 File file = new File(filename);
	        Scanner sc = new Scanner(file);
	        ArrayList<Polyline> polylines = new ArrayList<>();
	        while (sc.hasNextLine()) {
	            String line = sc.nextLine().trim();
	            if (!line.isEmpty() && !line.startsWith("#")) {
	                polylines.add(parseOneLine(line));
	            }
	        }
	        return polylines;
}
	 public static void main(String[] args) throws FileNotFoundException
	  {
	    ArrayList<Polyline> list = readFile("hello.txt");
	    Plotter plotter = new Plotter();

	    for (Polyline p : list)
	    {
	      plotter.plot(p);
	    }
	  }

}
