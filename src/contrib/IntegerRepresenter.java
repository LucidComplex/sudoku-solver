package contrib;

import base.Individual;
import base.Representer;

import java.util.Random;

/**
 * Created by tan on 11/29/15.
 */
public class IntegerRepresenter extends Representer {

    @Override
    public Individual randomIndividual(int puzzleSize, int size) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(Math.abs(random.nextInt()) % puzzleSize + 1);
        }
        return new Individual(sb.toString());
    }
}
