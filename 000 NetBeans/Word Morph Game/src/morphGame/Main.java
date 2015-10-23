package morphGame;
/** --------------------------------------------- 
 * Dictionary read/search placeholder code..
 * ----------------------------------------------
 */

// Import libraries needed by the program
import java.util.Scanner;         // used for console input
// The following libraries are used to implement reading from a dictionary
import java.io.IOException;       // for IOException
import java.util.ArrayList;       // Used to create ArrayLists dictionary use
import java.io.*;                 // Used for File


/*
 * This class contains the main method, hash functions,
 * dictionary readers, and letter changing checking functions.
 */
public class Main
{   
    // Fields that can be accessed anywhere in the class go here
    Scanner keyboard = new Scanner( System.in);     // used to read user input
    // Declare a dynamically allocated ArrayList of Strings for the dictionary.
    // The dictionary can hold any number of words.
    ArrayList<String> dictionary; // This will be instantiated with
                                  // the contents of the dictionary file
    ArrayList<String> sEL; // Stands for Start / End List
    String[] hashTable; // This is the dictionary hash table
                        // Items will be tored quadratically
    /*
     * Main method instantiates all the ArrayLists and the hash table.
     * It contains the main puzzle game loop.
     */
    public static void main(String[] args) {
        System.out.println("Title: Word Morph\nAuthor: Timothy Swan\n" +
                "Date: May 2\nClass: Computer Science 201\n" +
                "Professor: Theys\nnetid: tswan2\nSex: Male\n" +
                "\nProgram Description: This game is a word puzzle\n" +
                "   played by morphing words by changing one letter\n" +
                "   at a time.");
        System.out.println("Loading Dictionary...");
        Main main = new Main();
        try {
            main.doIt(); // doIt() instantiates all the ArrayLists
        } catch (IOException e) {
        }
        main.hashTable = new String[main.dictionary.size() * 2];
        for(int i = 0; i < main.dictionary.size(); i++) {
            String word = main.dictionary.get(i);
            main.addHash(word);
        }
        // The following code was rendered useless for this program
//        int i;
//        for(i = 0; i < main.dictionary.size(); i++) {
//            String line = (String) main.dictionary.get(i);
//            String word = "";
//            for(int j = 0; j < line.length(); j++) {
//                if (line.charAt(j) == ' ')
//                    if (!word.equals("")) {
//                        main.dictionary.add(word);
//                        System.out.println(word);
//                    }
//                else
//                    word += line.charAt(j);
//            }
//            main.dictionary.add(line);
//        }
//        for(int k = 0; k < i; k++)
//            main.dictionary.remove(0);
//        for(i = 0; i < main.dictionary.size(); i++) {
//            System.out.println(main.dictionary.get(i));
//        }
        for(int i = 0; i < main.sEL.size(); i++) {
            String sE = main.sEL.get(i);
            String beginning = "";
            String end;
            int j;
            for(j = 0; j < sE.length(); j++) {
                beginning += sE.charAt(j);
                if (sE.charAt(j + 1) == ' ')
                    break;
            }
            end = sE.substring(j + 2);
            System.out.println("Challenge #" + (i + 1) + ": ");
            System.out.println("Type \"1\" for undo or \"2\" for redo.");
            System.out.println("To: " + end);
            System.out.println("From: " + beginning);
           // System.out.println();
            ArrayList<String> guesses = new ArrayList<String>();
            guesses.add(beginning);
            int current = 0;
            int max = 0;
            while (!guesses.get(current).equals(end)) {
               // System.out.println();
                String input = Keyboard.readString();
                if (input.equals("1")) {
                    if (current == 0)
                        System.out.println("Cannot undo beginning word");
                    else
                        current--; // Moves back a move in the ArrayList
                    System.out.println(guesses.get(current));
                } else if (input.equals("2")) {
                    if (current == max)
                        System.out.println("Cannot redo beyond latest word");
                    else
                        current++; // Moves forward a move in the ArrayList
                    System.out.println(guesses.get(current));
                } else if (checkChange(guesses.get(current), input) && main.wordExists(input)) {
                    current++; // Creates a move in ArrayList
                    if (current < guesses.size())
                        guesses.set(current, input);
                    else {
                        max = current;
                        guesses.add(input);
                    }
                } else {
                    System.out.println(input + " does not contain exactly one" +
                            " change from " + guesses.get(current) + " or " +
                            "is not in the dictionary.");
                    System.out.println(guesses.get(current));
                }
            } // End guessing loop
            System.out.println("Congratulations! You changed from\n" +
                    beginning + " to " + end + "\n" +
                    "in " + current + "moves!");
            System.out.println("Here's a reprint:");
            for(int k = 0; k < current + 1; k++)
                System.out.println(guesses.get(k));
        } // End challenges loop
        main.keyboard.nextLine();
    }
//    public Letter[] toLetters(String inStr) { 
//        Letter[] letters = new Letter[inStr.length()];
//        for(int i = 0; i < inStr.length(); i++) { 
//            letters[i] = new Letter(inStr.charAt(i));
//            if (i != 0)
//                letters[i - 1].next = letters[i];
//        }
//        return letters;
//    }
    public static boolean checkChange(String first, String second) {
        int insert;
        boolean change = false;
        if (first.length() == second.length() + 1) // First is longer
            insert = -1;
        else if (first.length() == second.length()) // Both the same
            insert = 0;
        else if (first.length() == second.length() - 1) // Second is longer
            insert = 1;
        else
            return false; // The difference in length > 1    
        int i = 0;
        int offset = 0;
        while(i < Math.min(first.length(), second.length())) {
            if (first.charAt(i) != second.charAt(i + offset))
                if (change == false) {
                    change = true;
                    i += -insert; // i gets bigger if the first word is longer
                    offset = insert; // offset gets smaller if the first word
                                     // is longer
                } else
                    return false;
            i++;
        }
        if (insert != 0)
            return true;
        return change;
    }
//    private class Letter {
//        char letter;
//        Letter next, future;
//        public Letter(char inLetter) {
//            letter = inLetter;
//        }
//        public String toString() {
//            return letter + "";
//        }
//    }
    //-------------------------------------------------------------------------
    // doIt() - display identifying information and run main loop with menu
    //      The words "throws IOException" have to do with dictionary error 
    //      handling when reading from a file
    //    
    void doIt() throws IOException 
    {   
        // Take care of creating and initializing the dictionary
        // Define the instance of the dictionary ArrayList
        dictionary = new ArrayList<String>();
        sEL = new ArrayList<String>();
        // Now fill the dictionary array list with words from the dictionary file
        readInDictionaryWords();
        
    }//end method doIt()
    
    
    // ************** Don't change the methods below this line **************
    
    // Read in the words to create the dictionary.
    // It throws an IOException, which is a way to gracefully handle errors
    // should there be a problem reading from the input.
    public void readInDictionaryWords() throws IOException
    {
        // Define a Scanner to read from an input file.  Note that the name of
        // the file given in the code below MUST match the actual filename of 
        // the dictionary file.  This file should be in the same directory
        // as the source code for WordCross.java
        File dictionaryFile = new File("words.txt");    // declare the file
        // ensure file exists and is in the correct directory
        if( ! dictionaryFile.exists()) {
            System.out.println("*** Error *** \n" +
                               "Your dictionary file has the wrong name or is " +
                               "in the wrong directory.  \n" +
                               "Aborting program...\n\n");
            System.exit( -1);    // Terminate the program
        }
        Scanner inputFile = new Scanner( dictionaryFile);
        
        // while there are words in the input file, add them to the dictionary
        while( inputFile.hasNext()) {
            dictionary.add( inputFile.nextLine() );
        }
        
        File startEndFile = new File("startendlist.txt");    // declare the file
        // ensure file exists and is in the correct directory
        if( ! startEndFile.exists()) {
            System.out.println("*** Error *** \n" +
                               "Your start end list has the incorrect name or is " +
                               "in the wrong directory.  \n" +
                               "Aborting program...\n\n");
            System.exit( -1);    // Terminate the program
        }
        inputFile = new Scanner( startEndFile);
        
        // while there are words in the input file, add them to the dictionary
        while( inputFile.hasNext()) {
            sEL.add( inputFile.nextLine() );
        }
    }//end createDictionary()
    
    
    // Allow looking up a word in dictionary, returning a value of true or false
    public boolean wordExists( String wordToLookup)
    {
        if( checkHash( wordToLookup)) {
            return true;    // words was found in dictionary
        }
        else {
            return false;   // word was not found in dictionary    
        }
    }//end wordExists
    public void addHash(String word) {
        int j = getHashCode(word);
        int i = 0;
        while (!(hashTable[j] == null) && !hashTable[j].equals(word)) {
            i++;
            j += i * i;
            if (j >= hashTable.length)
                j -= hashTable.length;
        }
        if (hashTable[j] == null)
            hashTable[j] = word;
    }
    public boolean checkHash(String word) {
        int j = getHashCode(word);
        int t = j;
        int i = 0;
        for( ; ; ) {
            if (hashTable[j] == null)
                return false;
            if (hashTable[j].equals(word))
                return true;
            if (i > 0 && j == t)
                return false;
            i++;
            j += i * i; // Items stored quadratically
            if (j >= hashTable.length)
                j -= hashTable.length;
        }
    }
    public int getHashCode(String word) {
        int i = 0;
        for(int j = 0; j < word.length(); j++) {
            i += j * (int) word.charAt(j);
            i += j + (int) word.charAt(j);
            i += j / (int) word.charAt(j);
        }
        if (i < 0)
            i = -i;
        i %= hashTable.length;
        //System.out.println("word " + word + " code " + i);
        return i;
    }
}//end class MustBeInAClass
