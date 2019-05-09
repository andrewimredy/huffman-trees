import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.*;
public class Assig4 {

	public static void main(String[] args) throws IOException {
		
		//Set up input scanner
		Scanner userInput = new Scanner(System.in);
		int response = 0;
		
		//read in text file
		File fileIn = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(fileIn));
		
		//build tree
		BST myTree = new BST();
		myTree.recBuildTree(br, br.readLine(), 0);
		myTree.huffCode();//fills table with codes
		
		//While loop until quit
		while(response != 3) {
		
			//Prompt user for action
			System.out.println("\nPlease choose from the following:");
			System.out.println("1) Encode a text string\r\n" + 
					"2) Decode a Huffman string\r\n" + 
					"3) Quit");
			response = userInput.nextInt();
			userInput.nextLine();
		
			//Case 1: Encode text
			if(response == 1) {
				System.out.println("Enter a String from the following characters: \r\n" + Arrays.toString(myTree.allChars()));
				String input = userInput.nextLine();
				//check validity of the string. inefficient, i know
				boolean validInput = true;
				boolean isInArray = false;
				for(int i = 0; i < input.length(); i++) {
					for(int j = 0; j < myTree.allChars().length; j++) {
						if(input.charAt(i) == myTree.allChars()[j]) {
							isInArray = true;
							break;
						}
					}
					if(!isInArray) {
						validInput = false;
						System.out.println("Invalid input");
						break;
					}
				}
				if(validInput)//do our operation
					System.out.println(myTree.encode(input));
				}
			//Case 2: Decode Huffman string
			else if(response == 2) {
				System.out.println("Here is the encoding table:");
				myTree.printTable();
				System.out.println("Please enter a Huffman string (one line, no spaces)");
				String input = userInput.nextLine();
				//check input
				boolean validInput = true;
				for(int i = 0; i < input.length(); i++) {
					if(input.charAt(i) != '1' && input.charAt(i) != '0') {
						System.out.println("Invalid input");
						validInput = false;
						break;
					}
				}
				if(validInput)// do our operation
					System.out.println("Text String: \n" + myTree.decode(input));
			}
		}
		System.out.println("Goodbye");
	}
}
