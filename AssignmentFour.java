package assignmentfour;

import java.io.*;
import java.util.*;

/**
 * <class>AssignmentFour</class> contains two methods that compare the words of
 * an ebook to a dictionary of linked lists
 *
 * @author Greg Becker
 */
public class AssignmentFour {

    String wordToBeSearched = "";
    long wordsFound = 0;
    long wordsNotFound = 0;
    long compsFound = 0;
    long compsNotFound = 0;
    int[] count = new int[1];
    String words[];
    MyLinkedList[] dictionary = new MyLinkedList[26];

    /**
     * compileDictionary: reads random_dictionary.txt and assigns contents to
     * linked lists by first letter. Apostrophes are removed during process.
     */
    public void compileDictionary() {
        String next = "";

        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new MyLinkedList<String>();
        } // for

        try {
            File f = new File("random_dictionary.txt");
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                next = input.nextLine().toLowerCase().replaceAll("'", "");
                dictionary[next.charAt(0) - 'a'].add(next);
            } // while

        } // try
        catch (IOException e) {
            e.printStackTrace();
        } // catch
    } //compileDictionary

    /**
     * readSearchOliver: reads oliver.txt, splits its contents into words and
     * searches to see the words are contained in a linked list. Hyphenated
     * words are treated as two separate words.
     */
    public void readSearchOliver() {
        try {
            File f = new File("oliver.txt");
            Scanner input = new Scanner(f);
            String t = input.nextLine();
            while (input.hasNext()) {
                String[] lines = input.nextLine().toLowerCase().split("[ -]");
                for (int i = 0; i < lines.length; i++) {
                    String s = "";
                    for (int j = 0; j < lines[i].length(); j++) {
                        if (lines[i].charAt(j) >= 'a' && lines[i].charAt(j) <= 'z') {
                            s += lines[i].charAt(j);
                        } // if
                    } // for
                    if (!s.isEmpty()) {
                        wordToBeSearched = s;
                        if (dictionary[wordToBeSearched.charAt(0) - 97].contains(wordToBeSearched, count)) {
                            wordsFound++;
                            compsFound += count[0];
                        } // if 
                        else {
                            wordsNotFound++;
                            compsNotFound += count[0];
                        } // else
                    } // if
                } // for
            }//while
        } // try
        catch (IOException e) {
            e.printStackTrace();
        } // catch

    } // readSearchOliver

    /**
     * main: runs the two methods above, calculates the average comparisons for
     * words found and not found and prints the averages on screen.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AssignmentFour x = new AssignmentFour();
        x.compileDictionary();
        x.readSearchOliver();
        long avgCompsFound = x.compsFound / x.wordsFound;
        long avgCompsNotFound = x.compsNotFound / x.wordsNotFound;
        System.out.println("The average number of comparisons made for words found:\n" + avgCompsFound);
        System.out.println("The average number of comparisons made for words not found:\n" + avgCompsNotFound);
    } //main

} //class
