/*
 * Nicholas Rempel
 * logan sawatzky
 * testing
 */
package collaberation;

import java.util.Scanner;

public class Collaberation {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("hi");
		System.out.println("I will ask you for a number.");
		System.out.println("Pleas comply.");
		
		System.out.print("\nPick a number: ");
		System.out.println("You chose " + input.nextInt());
		
		for(int i = 0; i < 10; i++)
		{
			System.out.println(i);
		}
	}

}
