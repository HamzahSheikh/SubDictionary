import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SubDictionaryCreator 
{
	public static void main(String[] args) 
	{
		/*
		 * Created a Array List x to take in all the words inside the text file
		 */
		ArrayList<String> x = new ArrayList<String>();
		
		/*
		 *	Welcome message and prompt to user to input a text name
		 */
		System.out.println("\t\tWelcome to the Sub-Dictionary Creator!\n\nPlease enter the name of the file you wish to create a Sub-Dictionary for:");
		
		/*
		 * Scanner for user keyboard input
		 */
		Scanner user = new Scanner(System.in);
		String input = user.next();
		user.close();
		
		try 
		{
			/*
			 * Scanner y which reads the words inside the file
			 */
			Scanner y = new Scanner(new FileInputStream(input));
			
			/*
			 * Uses delimiter of blank space, skipped line and commas
			 */
			y.useDelimiter(" |\\n|,");
			
			/*
			 * Keeps looping until there is no more words in the document
			 */
			while(y.hasNext()) 
			{
				String adder = y.next().trim();
	
				/*
				 * Checks if input is just a blank space before adding, followed by replacing any punctuation with nothing,
				 * trimming the word of any blank space & putting it to upper case
				 */
				if(!adder.equalsIgnoreCase("\\s"))
					x.add(adder.trim().toUpperCase());
			}
			
			/*
			 * Closing the scanner since it is no longer needed to be used
			 */
			y.close();
			
			/*
			 * Loops until every word in x has been processed
			 */
			for(int i =0; i<x.size();i++) 
			{
				/*
				 * Loops until ever letter of a String has been processed
				 */
				for(int o = 0; o<x.get(i).length();o++)
				{
					/*
					 * Takes the char at a o position
					 */
					char t = new Character(x.get(i).charAt(o));
					
					/*
					 * First checks if there is any digit in the word and also if the String is 1 letter (unless A or I)
					 * If any of these conditions are met, it replaces the string with a blank
					 */
					if(Character.getType(t) == 24 || Character.getType(t) == 30)
					{
						x.set(i, x.get(i).substring(0, o));
					}
					else if((!Character.isAlphabetic(t) && Character.getType(t) != 20 && !x.get(i).equalsIgnoreCase("MC²") ) || 
						(x.get(i).length() <= 1 && !x.get(i).equalsIgnoreCase("a")&& !x.get(i).equalsIgnoreCase("i"))) 
					{
						x.remove(i);
					}
					
				}
				
			}
		
			/*
			 * New array list which will organize the words in alphabetical, each letter being in its own array list
			 */
			ArrayList<ArrayList<String>> org = new ArrayList<ArrayList<String>>(26);
			
			/*
			 * Counter to know which letter the loop is at
			 */
			char counter = 'A';
			
			/*
			 * Loops until all the alphabet has been covered
			 */
			for(int i =0; i<26;i++)
			{
				/*
				 * ArrayList letter which created to insert all strings starting with a certain letter
				 */
				ArrayList<String> letter = new ArrayList<String>();
				
				/*
				 * Determines what letter is being used to insert strings in letter
				 */
				char insert = (char)(counter +i);
				
				/*
				 * Loops for the complete size of ArrayList x
				 */
				for(int o =0; o<x.size();o++)
				{
					/*
					 * Checks if the contents is empty
					 */
					if(x.get(o).length()>0) 
					{
						/*
						 * char checking records the letter the String starts with
						 */
						char checking = (char)x.get(o).charAt(0); 
						
						/*
						 * Determines if the word starts with the same letter the one being added
						 */
						if(insert == checking)
						{
							/*
							 * Only adds if word is not contained
							 */
							if(!letter.contains(x.get(o)))
							{
								letter.add(x.get(o));
							}
						}
					}
				}
	
				/*
				 * Sorts the array of a specific letter in alphabetical order
				 */
				letter.sort(String::compareToIgnoreCase);
				/*
				 * Adds the ArrayList letter to ArrayList org
				 */
				org.add(letter);
			}	
			
			/*
			 * int entries determines how many words are recorded.
			 * The size is compiled in a for loop which adds the size of every arraylist contained
			 * in org.
			 */
			int entries = 0;
			for(int i =0; i<26;i++)
				entries += org.get(i).size();
			
			//Resets the counter for another use
			counter = 'A';
			
			try 
			{
					/*
					 * Creates a PrintWriter u to add the words into a txt file
					 */
					PrintWriter u = new PrintWriter(new FileOutputStream("SubDictionary.txt"));
					
					//Prints how many words are recorded
					u.println("This document produced a sub-dictionary, which includes "+entries+" entries\n\n");
					u.println();
					
					/*
					 * Loops untill all of org has been processed
					 */
					for(int i =0; i<26;i++)
					{
						char insert = (char)(counter +i);
						
						//Only prints if the array contains values
						if(org.get(i).size()>0)
						{
							u.println(insert+"\r\n======================\n");
							
							//Prints all words in the array to the file
							for(int r =0; r<org.get(i).size();r++) 
							{
								u.println(org.get(i).get(r));
								u.flush();
							}
							
							u.println();
						}
					}
					
					u.close();
					
					System.out.print("\nFile SubDictionary.txt has been created for File "+input+"!");
					System.exit(0);
					
			}
			//Keep Compiler Happy
			catch(FileNotFoundException e) {
				System.out.print("Could not Create File!");
				System.exit(0);
			}
	
		}
		//Lets user know file of the name entered wasnt found
		catch(FileNotFoundException e) {
			System.out.print("\nThis File does not seem to exist! Program will now close! Bye!");
			System.exit(0);
		}
	
	}
	
	
	
	
	
	
	
	
}
