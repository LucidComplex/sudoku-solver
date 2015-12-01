package contrib;

import base.Individual;
import base.Mutator;

import java.util.Random;

/**
 * Created by tan on 11/30/15.
 */
public class ResetMutator implements Mutator {
    Random rand;

    public ResetMutator(Random random) {
        rand = random;
    }

    @Override
    public void mutate(Individual individual, int puzzleSize) {
        StringBuilder builder = new StringBuilder(individual.getGenotype());
        for (int i = 0; i < individual.getGenotype().length(); i++) {
                char newChar = (char) ('0' + rand.nextInt(puzzleSize) + 1);
                builder.setCharAt(i, newChar);
        }
        individual.setGenotype(builder.toString());
    }
}
