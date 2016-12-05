import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class WordUnscrambler {

	static LinkedList<String> list = new LinkedList<String>();
	static String scrambledWord = "";
	
	public static void main(String[] args){
		
		String str;
		
		// If no argument provided, ask for one
		if (args.length == 0) {
			Scanner kbd = new Scanner(System.in);
			System.out.print("Enter letters to unscramble: ");
			str = kbd.next();
		}
		else {
			str = args[0];
		}
		
		scrambledWord = str;
		
		System.out.println("\n\n");
		System.out.println("  _____       _     _____         _           ");
		System.out.println(" | __  |___ _| |___|     |___ ___| |_ ___ _ _ ");
		System.out.println(" | __ -| .'| . |___| | | | . |   | '_| -_| | |");
		System.out.println(" |_____|__,|___|   |_|_|_|___|_|_|_,_|___|_  |");
		System.out.println("                                         |___|");
		System.out.println("\n====================================================================");
		System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - \n");
		
		permutation(str);
		
		if (list.isEmpty()){
				System.out.println("\nNo words were found from the input string: " +scrambledWord+"\n\n");
		}
		
		System.out.println("\n\n\n-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  - ");
		System.out.println("====================================================================\n\n");

	}

	public static void permutation(String str) { 
		permutation("", str); 
	}

	private static void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0)
			search(prefix);
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
		}
	}


	public static void search(String word) {
		
		try {
			// Open the file c:\test.txt as a buffered reader
			BufferedReader bf = new BufferedReader(new FileReader("C:/Users/Jason/Desktop/Programming Projects/Java/WordUnscrambler/dictionary.txt"));

			// Start a line count and declare a string to hold our current line.
			int linecount = 0;
			String line;

			// Let the user know what we are searching for
			//System.out.println("Searching for " + word + " in file...");

			// Loop through each line, stashing the line into our line variable.
			while (( line = bf.readLine()) != null)
			{
			
				// Increment the count and find the index of the word
				linecount++;
				int indexfound = line.indexOf(word);

				// If greater than -1, means we found the word
				if (word.equals(line)) {
					if (!list.contains(word)){
						list.add(word);
						if (list.peekFirst().equals(list.peekLast())){
							System.out.println("\nThe following are possible words created from the input string: " + scrambledWord+"\n\n");
						}
						System.out.print(word +" ("+linecount+")\t\t");
					}
				}
			}
			
			// Close the file after done searching
			bf.close();
		}
		catch (IOException e) {
			System.out.println("IO Error Occurred: " + e.toString());
		}

	}
}