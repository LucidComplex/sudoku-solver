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
        System.out.println(puzzleSize);
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int rand = Math.abs(random.nextInt()) % puzzleSize + 1;
            sb.append(rand);
        }
        return new Individual(sb.toString());
    }
}
