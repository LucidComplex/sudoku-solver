package contrib;

import base.Individual;
import base.Representer;

import java.util.Random;

/**
 * Created by tan on 11/29/15.
 */
public class IntegerRepresenter extends Representer {
    Random rand;

    public IntegerRepresenter(Random random) {
        rand = random;
    }

    @Override
    public Individual randomIndividual(int puzzleSize, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int randDigit =  rand.nextInt(puzzleSize) + 1;
            sb.append(randDigit);
        }
        return new Individual(sb.toString());
    }
}
