package logicopr;
import java.util.Scanner;

public class lgcoperators {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		int temp = scanner.nextInt();
		if (temp>30) {
			System.out.println("It is hot outside");
		}
		else if (temp>=20 && temp<= 30) {
			System.out.println("It is warm outside");
		}
		else {
			System.out.println("It is cold outside");
		}
		
		

	}

}
