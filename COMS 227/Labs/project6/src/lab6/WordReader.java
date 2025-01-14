package lab6;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class WordReader {


	
	    public static void countWordsInFile(String filename) throws IOException{
	        {
	        	BufferedReader reader = new BufferedReader(new FileReader(filename));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                // Split the line into words using whitespace as delimiter
	                String[] words = line.trim().split("\\s+");
	                // Print the number of words in the line
	                System.out.println("Number of words in line: " + words.length);
	            }
	            reader.close();
	        
	        }
	    }

	    public static void main(String[] args) throws IOException {
	        countWordsInFile("story.txt");
	    }
	}


