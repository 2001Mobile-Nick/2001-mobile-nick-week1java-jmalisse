package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CustomException extends Exception // TODO research warning
{
	CustomException(String message)
	{
		super(message);
	}
}

public class EvaluationService {

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		// Create an instance of the evaluation service class for testing implementation
		EvaluationService eval = new EvaluationService();
		int choice = -1;
		String inputLine = "";
		boolean exit = false;

		do {
			// User read message for instruction
			System.out.println("Question number to test (1-20 inclusive) \"e\" to exit: ");

			// Read line from console to string
			inputLine = scan.nextLine();

			// if exit condition requested, break program
			if (inputLine.equals("e"))
			{
				exit = true;
				break;
			}

			// Attempt to set integer for question switch to input value
			try
			{
				// Set the input received to an Integer to compare to the question number
				choice = Integer.parseInt(inputLine);
				// If the choice is larger than 20 throw a CustomException (caught below,
				// practice)
				if ((choice > 20) || (choice < 1))
					throw new CustomException("Invalid input. Please use a number from 1-20.");
			}
			// If invalid input (characters or other type mismatch), catch exceptions
			catch (InputMismatchException | NumberFormatException ex)
			{
				System.out.println("Input registered: " + inputLine);
				System.out.println("Invalid input. Please use a number from 1-20.");
			}
			// Custom exception handled for input too large
			catch (CustomException ex) {
				System.out.println(ex.getMessage());
			}

			System.out.println();

			switch (choice) 
			{
			case 1:
				// Call evalService Question 1
				System.out.println("Input string for Question 1 (reversal): ");
				eval.reverse(scan.nextLine());
				break;
			case 2:
				// Call evalService Question 2
				System.out.println("Input string for Question 2 (tokenizer): ");
				eval.acronym(scan.nextLine());
				break;
			case 3:
				// Call evalService Question 3
				System.out.println("Set three test sides for comparator.");

				// Init new triangle object
				Triangle tri = new Triangle();
				// Attempt to read in three double values, one into each of the sides
				try {
					tri.setAllSidesABC(scan.nextDouble(), scan.nextDouble(), scan.nextDouble());
				}
				// Catch invalid input
				catch (InputMismatchException ex) {
					System.out.println("Invalid input. Limit input to double values (decimal numbers).");
					// Flush scanner
					scan.nextLine();
					// Break and retry
					break;
				}

				// Flush scanner (wasn't done at this successful point in code - better location
				// for it I'm sure)
				scan.nextLine();

				// After setting sides successfully attempt each of the three comparators and
				// output the results
				System.out.println("Equilateral -> " + (tri.isEquilateral() ? "YES" : "NO"));
				System.out.println("Isosceles -> " + (tri.isIsosceles() ? "YES" : "NO"));
				System.out.println("Scalene -> " + (tri.isScalene() ? "YES" : "NO"));

				break;
			case 4:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			case 5:
				// Call evalService Question 5
				System.out.println("Input phone number to clean: ");
				eval.cleanPhoneNumber(scan.nextLine());
				break;
			case 6:
				// Call evalService Question 6
				eval.wordCount(scan.nextLine());
				System.out.println("Input string to MAP: ");
				break;
			case 7:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			case 8:
				// Call evalService Question 8
				System.out.println("Phrase to convert to pig Latin: ");
				eval.toPigLatin(scan.nextLine());
				break;
			case 9:
				// Call evalService Question 9
				System.out.println("Input number to check if Armstrong number: ");
				eval.isArmstrongNumber(scan.nextInt());
				break;
			case 10:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			case 11:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			case 12:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			case 13:
				// Call evalService Question 13 encode
				System.out.println("Input string to encode: ");
				AtbashCipher.encode(scan.nextLine());
				break;
			case 14:
				// Call evalService Question 14 decode
				System.out.println("Input string to decode: ");
				AtbashCipher.decode(scan.nextLine());
				break;
			case 15:
				// TODO implement question call
				System.out.println("Input number set to test is valid ISBN format: ");
				eval.isValidIsbn(scan.nextLine());
				break;
			case 16:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			case 17:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			case 18:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			case 19:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			case 20:
				// TODO implement question call
				System.out.println("Question not yet implemented.");
				break;
			}
		} while (!exit);

	}

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		// Init character array to size of passed string param length
		char[] reversed = new char[string.length()];

		/*
		 * For Loop - each iteration take the last letter of the string and place it at
		 * the next index of the reversed char[] Then increment index and decrement
		 * string param next char location
		 */
		for (int i = reversed.length - 1, j = 0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}

		// Init a new String object with a char[] passed to the constructor
		String reverseString = new String(reversed);
		// Output reversed word for user to see
		System.out.println(reverseString);
		// Return appropriately reserved word for Unit Tests
		return reverseString;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		System.out.println("Input string to tokenize:");

		// Initialize new StringTokenizer with param phrase
		StringTokenizer tokenLine = new StringTokenizer(phrase, " \t\n\r\f,.:;?![]'-", false);

		// Inform user of number of tokens found (user verification given known
		// delimiters)
		System.out.println("Number of tokens in given input: " + tokenLine.countTokens());

		// Initialize StringBuilder to an empty string
		StringBuilder completed = new StringBuilder("");

		// Loop, while any tokens remain in the tokenizer
		// Tokenizer maintains own index of current tokens and it's place within them
		while (tokenLine.hasMoreTokens()) {
			// Append the first character, as an uppercase letter, to the completed Acronym
			completed.append(tokenLine.nextToken().substring(0, 1).toUpperCase());
		}

		// Output result to view
		System.out.println("Acronym output: " + completed);

		// Return completed check as a String object
		return completed.toString();

		// Can also implement a version that uses String.split if more time is available
		// before submission
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			// Set a boolean to true
			boolean equal = true;

			// so long as no side is unequal it returns true. Otherwise, immediately return
			// false
			if (this.getSideOne() != this.getSideTwo())
				return false;
			if (this.getSideTwo() != this.getSideThree())
				return false;
			if (this.getSideOne() != this.getSideThree())
				return false;

			return equal;
		}

		public boolean isIsosceles() {

			// If any of the sides are the same then at least 2 sides are the same
			// By the preferred definition of this instruction that means Isosceles
			// So, any comparator returning true = affirmative
			if (this.getSideOne() == this.getSideTwo())
				return true;

			if (this.getSideTwo() == this.getSideThree())
				return true;

			if (this.getSideOne() == this.getSideThree())
				return true;

			return false;
		}

		public boolean isScalene() {
			// No sides can be the same size
			// If any are immediately return false, otherwise return true
			if (this.getSideOne() == this.getSideTwo())
				return false;

			if (this.getSideTwo() == this.getSideThree())
				return false;

			if (this.getSideOne() == this.getSideThree())
				return false;

			return true;
		}

		// Test method for menu input of sides
		public void setAllSidesABC(double a, double b, double c) {
			this.setSideOne(a);
			this.setSideTwo(b);
			this.setSideThree(c);
			System.out.println("Sides set.");
		}
	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// TODO Write an implementation for this method declaration
		
		// note that the expected input may contain capital letters, which need to be recieved as lowercase .tolowercase()
		
		StringBuilder input = new StringBuilder(string.toLowerCase());
		
		String onePoint = "aeioulnrst";
		String twoPoint = "dg";
		String threePoint = "bcmp";
		String fourPoint = "fhvwy";
		String fivePoint = "k";
		String eightPoint = "jx";
		String tenPoint = "qz";
		
		int pointTotal = 0;
		
		for(int index = 0; index < input.length(); index++)
		{
			String check = input.toString().substring(index, index + 1);
			// for each letter in the input
			
			// Easily readable version of the comparison but wastes lines.
			if(onePoint.contains(check))
			{
				pointTotal += 1;
				continue;
			}
			else if(twoPoint.contains(check))
			{
				pointTotal += 2;
				continue;
			}
			else if(threePoint.contains(check))
			{
				pointTotal += 3;
				continue;
			}
			else if(fourPoint.contains(check))
			{
				pointTotal += 4;
				continue;
			}
			else if(fivePoint.contains(check))
			{
				pointTotal += 5;
				continue;
			}
			else if(eightPoint.contains(check))
			{
				pointTotal += 8;
				continue;
			}
			else if(tenPoint.contains(check))
			{
				pointTotal += 10;
				continue;
			}
		}
		// break input word down to set of characters
		// compare each character against set of character values
		// dont need map, just if statement values
		// add to sum total value on match
		
		// switch fastest/easiest?
		
		return pointTotal;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		
		// change input string to remove all punctuation
		String numbers = string.replaceAll("\\p{Punct}|\\s","");
				
		System.out.println("Numbers received: [" + numbers + "]");
		
		// expected outcome is either a successfully formatted string of numbers that is 11 or 10 digits long
		if(numbers.length() > 11)
		{
			System.out.println("throw on too many after removals");
			throw new IllegalArgumentException();
		}
		// or there are non-digit characters which need to throw an exception				
		if(Pattern.matches("[0-9]+", numbers) == false)
		{
			throw new IllegalArgumentException();
		}

		return numbers;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// expects a map of <string, ints> where the string is the word to count the
		// occurrences of, then the int is the NUMBER of occurrences for that word within the phrase
		Map<String, Integer> received = new HashMap<String, Integer>();
		String[] strings = string.split("\\W+");
		
		for(String word : strings)
		{
			// check if NOT already there
			if(!(received.containsKey(word)))
			{
				System.out.println("Word [" + word + "] NOT already in the Map. Placing into map.");
				received.put(word, 1);
			}
			else
			{
				// IS already there
				System.out.println("Word [" + word + "] already found in map.");
				// need to find the value stored for the key (word)
				// and increment by 1
				int numberAlreadyContained = received.get(word);
				System.out.println("Current value: " + numberAlreadyContained);
				received.replace(word, numberAlreadyContained + 1);
				System.out.println("Incremented value by one.");
			}

		}

		return received;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			// TODO Write an implementation for this method declaration

			// expected test does not seem to include any number more than once so a basic
			// binary sort tree is sufficient

			// expected answer seems to be a sorted list of values of type T, as the given
			// type during the function call
			// that said, all of the text examples are sorting integers - so only focus on
			// that aspect right now
			return 0;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}
	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		// example "Wikipedia" would become "Ikipediaway"
		
		// vowels a,e,i,o,u - sometimes Y -> special rule
		// anything else is considered a consonant
		// only exception to the rule is when the entire word contains no vowels BUT
		// contains y---
		// --- in which case y is then treated as a vowel for that word ( these examples
		// never have y at the
		// start of the words
		
		// string.split method
		
		// split the phrase string into a string array
		// for every index in that array
		// check if the word is entirely consonants - for this purpose alone treat y as
		// a consonant
		// if the word is entirely consonants - THEN we treat y as a vowel in this
		// instance
		// otherwise
		// from the first letter as far through the string check if it begins with a
		// vowel
		// if it BEGINS with a vowel - simply add "ay" to the end
		// if it DOES NOT begin with a vowel - substring the consonant cluster to the
		// end, and
		// then append "ay" -> bike - ike-b-ay -> ikebay
		// floor - oor-fl-ay
		
		String[] baseWords = string.split("\\W+");
		String[] adjustedWords = new String[baseWords.length];
		
		StringBuilder adjustedWord = new StringBuilder("");
		
		char[] consonants = "bcdfghjklmnpqrstvxz".toCharArray();
		char[] vowels = "aeiouy".toCharArray();
		
		boolean resolved = false;
		int adjustedWordsIndex = 0;
		
		// Checking all words
		for(String word : baseWords)
		{
			resolved = false;
			System.out.println("Number of letters in this word: " + word.length());
			
			System.out.println();
			// check each single word
			for(int vowelIndex = 0; vowelIndex < vowels.length; vowelIndex++)
			{
				// Check Y as first letter (treated as a consonant)
				if(word.charAt(0) == vowels[5])
				{
					// first letter of word is vowel
					adjustedWord.append(word.substring(1));
					adjustedWord.append(word.substring(0,1) + "ay");
					resolved = true;
					break;
				}
				// Check Qu as first letter pair
				if((word.charAt(0) == consonants[12]) && (word.length() > 1) && (word.charAt(1) == vowels[4]))
				{
					// Qu edge case
					adjustedWord.append(word.substring(2));
					adjustedWord.append(word.substring(0,2) + "ay");
					resolved = true;
					break;
				}
				
				// Check other vowels
				for(Character vowel : vowels)
				{
					if(word.charAt(0) == vowel)
					{
						adjustedWord.append(word + "ay");
						resolved = true;
						break;
					}
				}
				
				if(resolved) break;
				
				// Check consonants
				int consonantClusterLength = 0;
				
				// For each letter in the word to check
				for(int letterIndex = 0; letterIndex < word.length(); letterIndex++)
				{
					// For each consonant to compare it to
					for(int consonantIndex = 0; consonantIndex < consonants.length; consonantIndex++)
					{
						// If the letter is a consonant - which the FIRST letter should be at this point
						if(word.charAt(letterIndex) == consonants[consonantIndex])
						{
							consonantClusterLength++;
							System.out.println("Consonant found!");
							
							// Consonant identified - break current letter loop
							break;
							// TODO check the remainder of letters until a vowel is reached - at which point stop incrementing the consonantClusterLength - then substring the string param from beginning to the length of the cluster to obtain the starting cluster, then proceed like normal
						}
					}
					
					// For each vowel to compare it to
					for(int secondVowelIndex = 0; secondVowelIndex < vowels.length; secondVowelIndex++)
					{
						// After a consonant is found, there may be more consonants
						// Incrementing the consonantClusterLength will suffice to count the string.substring for finding that cluster
						// And when there are no more consonants the first vowel found will be the end index for the substring
						if(word.charAt(letterIndex) == vowels[secondVowelIndex])
						{
							adjustedWord.append(word.substring(consonantClusterLength));
							adjustedWord.append(word.substring(0, consonantClusterLength) + "ay");
							resolved = true;
							break;
						}
					}
					if(resolved) break;
				}
				
				// comment for commit, user credentials cleaned?
				
				if(resolved) break;
			}
			
			
			System.out.println("adjusted word: " + adjustedWord);
			
			// Add adjustedWord to the array and increment index counter
			adjustedWords[adjustedWordsIndex] = adjustedWord.toString();
			adjustedWordsIndex++;
			
			// "reset" the adjustedWord SB
			adjustedWord.setLength(0);
		}
		
		// Assemble final word for submission
		StringBuilder finalWord = new StringBuilder();
		
		int wordCount = adjustedWords.length;
		int countMod = 0;
		for(String adjusted : adjustedWords)
		{
			// Add the word
			finalWord.append(adjusted.toString());
			
			// If the count is currently not >= the countMod
			if((countMod) < (wordCount - 1))
			{
				countMod++;
				finalWord.append(" ");
			}
			else
				break;

		}
		
		return finalWord.toString();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		// first is to determine length of the substring of numbers entered
		// then, take each number in the integer - stored as a substring, and convert to
		// its own int variable
		// raise each to the value of (itself) ^ (number of digits) and add to the total
		// sum
		// if total sum == entered number then number is armstrong number

		String numberAsString = String.valueOf(input);
		int numberCount = numberAsString.length();
		int totalSum = 0;
		
		for(int i = 0; i < numberAsString.length(); i++) 
		{
			Double num = (double) Integer.parseInt(numberAsString.substring(i,i+1));
			System.out.println("parsed num: " + num.intValue() + "\n");
			num = Math.pow(num, numberCount);
			totalSum += num;
			System.out.println(num.intValue() + " added to totalSum.");
			System.out.println("totalSum now: " + totalSum + "\n");
		}
		
		System.out.println("Input number: " + input + "\n");
		System.out.println("Calculated number: " + totalSum + "\n");

		return (totalSum == input) ? true : false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// arraylist to store factors
		List<Long> factorList = new ArrayList<Long>();
		
		long num = l;
		
		// check each number through to "l" - skip 0 and 1 for obvious reasons
		for(int i = 2; i <= num; i++)
		{
			// check if the current num is divisble by 2 (if it is it cannot be a 
			// prime, bc it is divisible by something other than 1 and itself
			// (2 is the only "even" prime number applicable)
			while(num % i == 0)
			{
				// dont entirely understand the math going on at this bit without visual representation
				// but i dont necessarily need to understand the math to know what it is doing
				factorList.add((long) i);
				num /= i;
			}
		}
		
		return factorList;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		
		public static final String ALPHABETLC = "abcdefghijklmnopqrstuvwxyz";
		public static final String ALPHABETUC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			// take in the string and use a stringbuilder to adjust individual letters
			// increment through stringbuilder index, one character at a time, and ROT the character
			// HOWEVER, if the character matches either any digit [0-9] or | any \\p{Punct} then skip the digit
			StringBuilder resultString = new StringBuilder("");
			
			Pattern pattern = Pattern.compile("\\p{Punct}|[0-9]|\\s");
			Matcher matcher;
			
			// iterate through each character in the string
			for(int i = 0; i < string.length(); i++)
			{
				matcher = pattern.matcher("" + string.charAt(i));
				// check first if the current character is punctuation
				// if so, simply append it to the stringbuilder and continue loop
				if(matcher.find())
				{
					resultString.append(string.charAt(i));
					continue;
				}
				
				// find current character and check uppercase or lowercase
				if(Character.isUpperCase(string.charAt(i)))
				{
					// find the current character position in the alphabet (uppercase)
					int characterPos = ALPHABETUC.indexOf(string.charAt(i));
					// find the new value that the letter would be after the shift
					// modulo will return the remainder which is the same as wrapping around the alphabet (but the string isnt infinitely wrapping)
					int keyValue = (this.key + characterPos) % 26;
					// set a character to this new letter value
					char newLetter = ALPHABETUC.charAt(keyValue);
					// append that letter to the stringbuilder
					resultString.append(newLetter);
				}
				else
				{
					// find the current character position in the alphabet (uppercase)
					int characterPos = ALPHABETLC.indexOf(string.charAt(i));
					// find the new value that the letter would be after the shift
					// modulo will return the remainder which is the same as wrapping around the alphabet (but the string isnt infinitely wrapping)
					int keyValue = (this.key + characterPos) % 26;
					// set a character to this new letter value
					char newLetter = ALPHABETLC.charAt(keyValue);
					// append that letter to the stringbuilder
					resultString.append(newLetter);
				}
				
			}

			return resultString.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		// TODO Write an implementation for this method declaration
		int MAX_SIZE = 105923;
		
		if(i == 0)
		{
			throw new IllegalArgumentException();
		}
		
		// sieve of eratosthenes is easiest method i understand
		// which uses a boolean array whose entries will only be true at the end of the run for entries whose index' represent prime numbers
		// using multiples/squares to discern which index' cannot be primes because they are multiples of the iteration int in the loop
		
		boolean[] primes = new boolean[MAX_SIZE];
		List<Integer> resultPrimes = new ArrayList<Integer>();
		
		// set all to true
		for(int j = 0; j < MAX_SIZE; j++)
		{
			primes[j] = true;
		}
		
		for(int j = 2; j * j < MAX_SIZE; j++)
		{
			// change primes[index of j] to false if multiple factor found after all iterations
			// need to retest on each loop because we are testing both the prime factors and the squares
			if(primes[j] == true)
			{
				for(int k = j * j; k < MAX_SIZE; k += j)
				{
					// cannot be prime because this location is essentially a square of the index value used
					// being a square means it has multiple factors beyond 1 and itself
					primes[k] = false;
				}
			}
		}
		
		// obtain list of primes based on bool array index' that are still true at this point
		for(int j = 2; j < MAX_SIZE; j++)
		{
			if(primes[j] == true) resultPrimes.add(j);
		}
		
		// search list for Nth prime
		
		
		return resultPrimes.get(i-1);
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {
		
		private static Map<String, String> encodeConversionTable = new HashMap<String, String>();
		// load frontTable with forwardConversion strings
		
		private static void loadEncodeTable()
		{ 	
			encodeConversionTable.put("a" , "z"); encodeConversionTable.put("b" , "y"); encodeConversionTable.put("c" , "x"); 
			encodeConversionTable.put("d" , "w"); encodeConversionTable.put("e" , "v"); encodeConversionTable.put("f" , "u"); encodeConversionTable.put("g" , "t");
			encodeConversionTable.put("h" , "s"); encodeConversionTable.put("i" , "r"); encodeConversionTable.put("j" , "q"); encodeConversionTable.put("k" , "p"); 
			encodeConversionTable.put("l" , "o"); encodeConversionTable.put("m" , "n"); encodeConversionTable.put("n" , "m"); encodeConversionTable.put("o" , "l"); encodeConversionTable.put("p" , "k"); 
			encodeConversionTable.put("q" , "j"); encodeConversionTable.put("r" , "i"); encodeConversionTable.put("s" , "h"); encodeConversionTable.put("t" , "g"); encodeConversionTable.put("u" , "f"); 
			encodeConversionTable.put("v" , "e"); encodeConversionTable.put("w" , "d"); encodeConversionTable.put("x" , "c"); encodeConversionTable.put("y" , "b"); encodeConversionTable.put("z" , "a");
		}
		
		private static Map<String, String> decodeConversionTable = new HashMap<String, String>();
		// load backTable with reverseConversion strings
		private static void loadDecodeTable()
		{ 	
			decodeConversionTable.put("z" , "a"); decodeConversionTable.put("y" , "b"); decodeConversionTable.put("x" , "c"); 
			decodeConversionTable.put("w" , "d"); decodeConversionTable.put("v" , "e"); decodeConversionTable.put("u" , "f"); decodeConversionTable.put("t" , "g");
			decodeConversionTable.put("s" , "h"); decodeConversionTable.put("r" , "i"); decodeConversionTable.put("q" , "j"); decodeConversionTable.put("p" , "k"); 
			decodeConversionTable.put("o" , "l"); decodeConversionTable.put("n" , "m"); decodeConversionTable.put("m" , "n"); decodeConversionTable.put("l" , "o"); decodeConversionTable.put("k" , "p"); 
			decodeConversionTable.put("j" , "q"); decodeConversionTable.put("i" , "r"); decodeConversionTable.put("h" , "s"); decodeConversionTable.put("g" , "t"); decodeConversionTable.put("f" , "u"); 
			decodeConversionTable.put("e" , "v"); decodeConversionTable.put("d" , "w"); decodeConversionTable.put("c" , "x"); decodeConversionTable.put("b" , "y"); decodeConversionTable.put("a" , "z");
		}
		
		

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			
			loadEncodeTable();
			// set all to lowercase, remove whitespace, and remove punctuation from input string
			String stringLC = string.toLowerCase();
			StringBuilder adjusted = new StringBuilder(stringLC.replaceAll("\\p{Punct}|\\s", ""));
			StringBuilder encoded = new StringBuilder("");
			
			for(int i = 1; i <= adjusted.length(); i++)
			{
				// If digit found
				if(adjusted.substring(i-1, i).matches("[0-9]"))
				{
					encoded.append(adjusted.substring(i-1, i));
					if(i % 5 == 0)
					{
						encoded.append(" ");
					}
					continue;
				}
				
				String start = adjusted.substring(i-1,i);
				String finish = encodeConversionTable.get(start);
				encoded.append(finish);
				if((i % 5 == 0) && (i + 1 < adjusted.length()))
				{
					encoded.append(" ");
				}
			}
			
			return encoded.toString();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			loadDecodeTable();
						
			// remove whitespace
			StringBuilder adjusted = new StringBuilder(string.replaceAll("\\s",""));
			// then transpose
			StringBuilder decoded = new StringBuilder("");
			for(int i = 0; i < adjusted.length(); i++)
			{
				if(adjusted.substring(i, i+1).matches("[0-9]"))
				{
					decoded.append(adjusted.substring(i, i+1));
					continue;
				}
				String start = adjusted.substring(i,i+1);
				String finish = decodeConversionTable.get(start);
				decoded.append(finish);
			}
			
			return decoded.toString();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// remove punctuation
		StringBuilder adjusted = new StringBuilder(string.replaceAll("\\s|\\p{Punct}",""));
		// count digits = exactly 10
		if(adjusted.length() != 10)
		{
			throw new IllegalArgumentException();
		}
		// multiply each digit in order of 1st-10,2nd-9,3rd-8, etc
		// loop with multiple args
		Integer formulaSum = 0;
		
		System.out.println("Adjusted: " + adjusted.toString());
		
		String lastDigit = adjusted.substring(adjusted.length() - 1, adjusted.length());
		
		// check last digit for invalid input
		if(!(lastDigit.matches("[0-9]+"))) 
		{	
			if(!(lastDigit.equals("X".toString())))
			{
				return false;
			}
		}
		
		for(int i = 0, j = 10; i < adjusted.length(); i++, j--)
		{
			int charac = adjusted.lastIndexOf("X");

			String currentDigit = adjusted.substring(i, i + 1);
			
			if(currentDigit.matches("[^0-9]"))
			{
				System.out.println("Invalid character detected.");
				return false;
			}
			
			if(i == charac)
			{
				formulaSum += Integer.valueOf(10 * j);
			}
			else
			{
				formulaSum += Integer.valueOf(adjusted.substring(i, i+1)) * j;	
			}
		}
		
		if((formulaSum % 11) == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// TODO Write an implementation for this method declaration
		return null;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// TODO Write an implementation for this method declaration
		
		// subtract 9? what is that and how to use with luhn formula?
		
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

}
