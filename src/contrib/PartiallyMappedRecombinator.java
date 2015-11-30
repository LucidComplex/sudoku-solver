package contrib;

import base.Individual;
import base.Recombinator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by tan on 11/30/15.
 */
public class PartiallyMappedRecombinator implements Recombinator {
    @Override
    public List<Individual> recombine(List<Individual> parents) {
        int[] points = new int[2];
        Random random = new Random(System.currentTimeMillis());
        points[0] = random.nextInt(parents.get(0).getGenotype().length() - 1);
        points[1] = points[0] + random.nextInt(parents.get(0).getGenotype().length() - points[0]);
        String mid1 = parents.get(0).getGenotype().substring(points[0], points[1]);
        String rest1 = parents.get(0).getGenotype().substring(0, points[0]) + parents.get(0).getGenotype().substring(points[1]);
        String mid2 = parents.get(1).getGenotype().substring(points[0], points[1]);
        String rest2 = parents.get(1).getGenotype().substring(0, points[0]) + parents.get(1).getGenotype().substring(points[1]);
        String childOne = 

        return null;
    }
}
