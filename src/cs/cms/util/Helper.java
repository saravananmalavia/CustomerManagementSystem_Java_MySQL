/**
 * This class offers functionality to read different types of data from console
 * 
 * read valid int  -- getI
 * read valid double  -- getD
 * read String  meets "[a-zA-Z\\s]+"  -- getS
 * read int between -- getIBetween
 * read char - getC
 * get the current date -- getCurrentDate
 * 
 */

package cs.cms.util;

import java.time.LocalDate;
import java.util.Scanner;

public class Helper {

	public static double getD() {
		Scanner sc = new Scanner(System.in);
		double value = 0;
		boolean isOk = true;

		do {
			try {
				value = sc.nextDouble();
				isOk = false;
			} catch (Exception e) {
				System.out.println("Wrong input, please eneter a valid double");
				System.out.println(e.getMessage());
				sc.nextLine();
			}

		} while (isOk);

		return value;
	}

	public static int getI() {
		Scanner sc = new Scanner(System.in);
		int value = 0;
		boolean isOk = true;

		do {
			try {
				value = sc.nextInt();
				isOk = false;
			} catch (Exception e) {
				System.out.println("Wrong input, please eneter a valid integer");
				System.out.println(e.getMessage());
				sc.nextLine();
			}

		} while (isOk);

		return value;
	}

	public static String getAlphabet() {

		Scanner sc = new Scanner(System.in);

		boolean flag = true;
		String character = null;

		try {
			while (flag) {
				String value = sc.nextLine();

				if (value.matches("[a-zA-Z\\s]+")) {

					character = value;
					flag = false;
					break;

				}

				else {
					System.out.println("wrong input, numbers and symbols not allowed");

					// break;

				}
			}

		} catch (Exception e) {
			System.out.println(e);

		}

		return character;

	}
	
	public static String getS() {

		Scanner sc = new Scanner(System.in);

	
		String value = "";
		try {
			
				 value = sc.nextLine();

					

		} catch (Exception e) {
			System.out.println(e);

		}

		
		return value;

	}

	public static int getIBetween(int a, int b) {

		Scanner sc = new Scanner(System.in);
		// System.out.println("Enter the two numbers");
		int value;

		
		while (true) {
			//System.out.println("Enter the numbers between " + a + "and " + b);
			value = sc.nextInt();
			if (value >= a && value <= b) {
				break;
			} else {
				System.out.println("wrong input, try again choose any of the below option between " + a + " to " + b);
			}
		}
		return value;

	}
	
	public static char getC() {
		Scanner sc = new Scanner(System.in);
		char value = 0;
		boolean isOk = true;

		do {
			try {
				value = sc.next().charAt(0);
				isOk = false;
			} catch (Exception e) {
				System.out.println("Wrong input, please eneter a valid character");
				System.out.println(e.getMessage());
			//	sc.nextLine();
			}

		} while (isOk);

		return value;
	}
	
	public static LocalDate getCurrentDate() {
	
		LocalDate localDate = LocalDate.now();
		
		return localDate;
		
	}
	
	public static String generateBlankSpace(int numberOfSpaces, String type) {
		String  blankSpace = ""; 
		int noOfSpace =0;
		if(type.equals("endColoumn")) {
			noOfSpace = numberOfSpaces-2;
		}else {
			noOfSpace = numberOfSpaces-1;
		}
		
		for(int i = 1; i<noOfSpace; i++) {
			blankSpace += " ";
			
		}
		
		return blankSpace;
	}
	
	
	public static String generateLine(int length) {
		String line = "";
		
		for(int i =1; i<= length; i++) {
			line += "~";
		}
			return line;
	}
}
