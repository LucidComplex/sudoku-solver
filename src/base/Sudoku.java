package base;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by tan on 11/29/15.
 */
public class Sudoku {
    String genotype;
    int size;

    public Sudoku(String in, int size) {
        genotype = in;
        this.size = size;
    }

    public int calculateFitness(Individual individual) {
        String genotypeCopy = genotype;
        for (int i = 0; i < individual.genotype.length(); i++) {
            genotypeCopy = genotypeCopy.replaceFirst("0", individual.genotype.substring(i, i + 1));
        }

        int score = 0;

        // horizontal
        for (int i = 0; i < genotypeCopy.length(); i += size) {
            score += duplicatesCount(genotypeCopy.substring(i, i + size));
        }
        for (int i = 0; i < size; i ++) {
            // construct new string of vertical component
            String vert = getColumn(genotypeCopy, i);
            score += duplicatesCount(vert);
        }
        return score;
    }

    private String getColumn(String string, int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(string.charAt(index + i * size));
        }
        return builder.toString();
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
