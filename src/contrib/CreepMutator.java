package contrib;

import base.Individual;
import base.Mutator;

import java.util.List;
import java.util.Random;

/**
 * Created by tan on 11/30/15.
 */
public class CreepMutator implements Mutator {

    @Override
    public void mutate(Individual individual, int puzzleSize) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(individual.getGenotype());
        for (int i = 0; i < individual.getGenotype().length(); i++) {
            if (Math.random() <= 0.5) {
                char newChar = builder.charAt(i);
                if (newChar == '0' + puzzleSize) {
                    newChar = '1';
                }
                newChar += 1;
                builder.setCharAt(i, newChar);
            }
        }
        individual.setGenotype(builder.toString());
    }
}
