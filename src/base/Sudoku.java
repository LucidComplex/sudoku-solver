package base;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by tan on 11/29/15.
 */
public class Sudoku {
    String genotype;

    public Sudoku(String in) {
        genotype = in;
    }

    public int calculateFitness(Individual individual) {
        int individualLength = individual.genotype.length();
        int rowLength;
        if (individualLength < 9) {
            rowLength = individualLength / 2;
        } else {
            rowLength = individualLength / 3;
        }
        int score = 0;
        score += duplicatesCount(individual.genotype);
        for (int start = 0, end = rowLength; end <= individualLength; start += rowLength, end += rowLength) {
            score += duplicatesCount(individual.genotype.substring(start, end));
        }
        return score;
    }

    private int duplicatesCount(String string) {
        int duplicateCount = 0;
        Vector<Character> vector = new Vector<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (vector.contains(c)) {
                duplicateCount++;
            } else {
                vector.add(c);
            }
        }
        return duplicateCount;
    }
}
