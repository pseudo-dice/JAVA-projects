package ifelse;
import java.util.Scanner;


public class ifstmnt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter your age:");
		Scanner scanner = new Scanner(System.in);
		
		int age = scanner.nextInt();
		
		if (age >= 75) {
			System.out.println("Ok Boomer!");
		}
		else if (age  >= 18) {
			System.out.println("You are an adult!");
		}
		else if (age >= 13) {
			System.out.println("You are a teenager!");
		}
		else {
			System.out.println("You are not an adult!");
		}

	}

}
