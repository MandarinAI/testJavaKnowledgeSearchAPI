package Main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		
		String userQuery = scan.nextLine();
		
		Search.submitQueryMethod(userQuery);
		
	}

}
