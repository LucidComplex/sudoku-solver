package contrib;

import base.Individual;
import base.Recombinator;
import base.Sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tan on 11/30/15.
 */
public class OnePointCrossoverRecombinator implements Recombinator {
    Random rand;

    public OnePointCrossoverRecombinator(Random random) {
        rand = random;
    }

    @Override
    public List<Individual> recombine(List<Individual> parents) {
        List<Individual> newChildren = new ArrayList<>();
        int crossOverPoint = rand.nextInt(parents.get(0).getGenotype().length());
        String first = parents.get(0).getGenotype();
        String second = parents.get(1).getGenotype();
        String newFirst = first.substring(0, crossOverPoint) + second.substring(crossOverPoint);
        String newSecond = second.substring(0, crossOverPoint) + first.substring(crossOverPoint);
        newChildren.add(new Individual(newFirst));
        newChildren.add(new Individual(newSecond));
        return newChildren;
    }

}
