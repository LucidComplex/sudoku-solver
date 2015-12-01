package contrib;

import base.Individual;
import base.Recombinator;

import java.util.*;

/**
 * Created by tan on 11/30/15.
 */
public class NPointCrossover implements Recombinator {
    Random rand;

    public NPointCrossover(Random random) {
        rand = random;
    }
    @Override
    public List<Individual> recombine(List<Individual> parents) {
        int[] points = new int[2];
        int geneSize = parents.get(0).getGenotype().length();
        points[0] = rand.nextInt(geneSize - 1);
        points[1] = points[0] + rand.nextInt(geneSize - points[0]);
        String parent1 = parents.get(0).getGenotype();
        String parent2 = parents.get(1).getGenotype();
        String mid1 = parent1.substring(points[0], points[1]);
        String mid2 = parent2.substring(points[0], points[1]);
        String first1 = parent1.substring(0, points[0]);
        String first2 = parent2.substring(0, points[0]);
        String end1 = parent1.substring(points[1]);
        String end2 = parent2.substring(points[1]);
        String child1 = first2 + mid1 + end2;
        String child2 = first1 + mid2 + end1;
        List<Individual> children = new ArrayList<>();
        children.add(new Individual(child1));
        children.add(new Individual(child2));
        return children;
    }
}
