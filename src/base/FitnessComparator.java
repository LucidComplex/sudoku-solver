package base;

import java.util.Comparator;

/**
 * Created by tan on 11/30/15.
 */
public class FitnessComparator implements Comparator<Individual> {

    @Override
    public int compare(Individual o1, Individual o2) {
        if (o1.getFitness() > o2.getFitness()) {
            return 1;
        } else if (o1.getFitness() < o2.getFitness()) {
            return -1;
        } else {
            return 0;
        }
    }
}
