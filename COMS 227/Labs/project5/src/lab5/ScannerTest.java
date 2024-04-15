package lab5;

import java.util.Scanner;

public class ScannerTest {
	public static void main(String[] args)
	  {
	    int result = sumFromConsole();//sumMany("7 12 42 100 8 16 17 -9 55 2 2 2 1055 -99 0 3");//sumThree("42 237 5");
	    System.out.println(result);
	  }
	  
	  public static int sumThree(String text)
	  {
	    Scanner in = new Scanner(text);
	    int total = 0;
	    int num = in.nextInt();
	    total = total + num;
	    num = in.nextInt();
	    total = total + num;
	    num = in.nextInt();
	    total = total + num;
	    return total;
	  }
	  public static int sumMany(String text)
	  {
	    Scanner in = new Scanner(text);
	    int total = 0;
	    while (in.hasNextInt())
	    {
	      int num = in.nextInt();
	      total = total + num;     
	    }
	    return total;
	  }
	  public static int sumFromConsole()
	  {
	    Scanner in = new Scanner(System.in);
	    int total = 0;
	    while (in.hasNextInt())
	    {
	      System.out.print("Enter a number: ");
	      int num = in.nextInt();
	      total = total + num;     
	    }
	    return total;
	  }
	}

