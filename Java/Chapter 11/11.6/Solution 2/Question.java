/*Question 11.7 A circus is designing a tower routine consisting of people standing atop one another's shoulders.
For practical and aesthetic reasons, each person must be both shorter and lighter than the person below him or her.
Given the heights and weights of each person in the circus, write a method to compute the largest possible number of people in such a tower.*/


/*
Answer:
We can sort the height of these people and then use the way to find the longest increasing subsequence of weights in the corresponding array.
 */

import java.util.*;


public class Question {
    // Returns longer sequence
    private static ArrayList<HtWt> seqWithMaxLength(ArrayList<HtWt> seq1, ArrayList<HtWt> seq2) {
        if (seq1 == null) {
            return seq2;
        } else if (seq2 == null) {
            return seq1;
        }
        return seq1.size() > seq2.size() ? seq1 : seq2;
    }

    private static void longestIncreasingSubsequence(ArrayList<HtWt> array, ArrayList<HtWt>[] solutions, int current_index) {
        if (current_index >= array.size() || current_index < 0) {
            return;
        }
        HtWt current_element = array.get(current_index);

        // Find longest sequence that we can append current_element to
        ArrayList<HtWt> best_sequence = null;
        for (int i = 0; i < current_index; i++) {
            if (array.get(i).isBefore(current_element)) { // If current_element is bigger than list tail
                best_sequence = seqWithMaxLength(best_sequence, solutions[i]); // Set best_sequence to our new max
            }
        }

        // Append current_element
        ArrayList<HtWt> new_solution = new ArrayList<HtWt>();
        if (best_sequence != null) {
            new_solution.addAll(best_sequence);
        }
        new_solution.add(current_element);

        // Add to list and recurse
        solutions[current_index] = new_solution;
        longestIncreasingSubsequence(array, solutions, current_index + 1);
    }

    private static ArrayList<HtWt> longestIncreasingSubsequence(ArrayList<HtWt> array) {
        ArrayList<HtWt>[] solutions = new ArrayList[array.size()];
        longestIncreasingSubsequence(array, solutions, 0);

        ArrayList<HtWt> best_sequence = null;
        for (int i = 0; i < array.size(); i++) {
            best_sequence = seqWithMaxLength(best_sequence, solutions[i]);
        }

        return best_sequence;
    }

    public static ArrayList<HtWt> initialize() {
        ArrayList<HtWt> items = new ArrayList<HtWt>();

        HtWt item = new HtWt(65, 60);
        items.add(item);

        item = new HtWt(70, 150);
        items.add(item);

        item = new HtWt(56, 90);
        items.add(item);

        item = new HtWt(75, 190);
        items.add(item);

        item = new HtWt(60, 95);
        items.add(item);

        item = new HtWt(68, 110);
        items.add(item);

        item = new HtWt(35, 65);
        items.add(item);

        item = new HtWt(40, 60);
        items.add(item);

        item = new HtWt(45, 63);
        items.add(item);

        return items;
    }

    public static void printList(ArrayList<HtWt> list) {
        for (HtWt item : list) {
            System.out.println(item.toString() + " ");
        }
    }

    public static ArrayList<HtWt> getIncreasingSequence(ArrayList<HtWt> items) {
        Collections.sort(items);
        return longestIncreasingSubsequence(items);
    }

    public static void main(String[] args) {
        ArrayList<HtWt> items = initialize();
        ArrayList<HtWt> solution = getIncreasingSequence(items);
        printList(solution);
    }
}