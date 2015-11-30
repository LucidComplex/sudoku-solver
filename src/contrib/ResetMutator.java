package contrib;

import base.Individual;
import base.Mutator;

import java.util.Random;

/**
 * Created by tan on 11/30/15.
 */
public class ResetMutator implements Mutator {
    @Override
    public void mutate(Individual individual, int puzzleSize) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(individual.getGenotype());
        for (int i = 0; i < individual.getGenotype().length(); i++) {
            if (Math.random() <= 0.5) {
                char newChar = (char) ('0' + random.nextInt(puzzleSize) + 1);
                builder.setCharAt(i, newChar);
            }
        }
        individual.setGenotype(builder.toString());
    }
}
