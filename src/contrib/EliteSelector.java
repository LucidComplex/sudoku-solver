package contrib;

import base.FitnessComparator;
import base.Individual;
import base.SurvivorSelector;

import java.util.*;

/**
 * Created by tan on 11/29/15.
 */
public class EliteSelector implements SurvivorSelector{

    @Override
    public List<Individual> selectSurvivors(List<Individual> population, float survivalRate) {
        Collections.sort(population, new FitnessComparator());
        // take elites
        int individualsToTake = Math.round(survivalRate * population.size());
        return new ArrayList<>(population.subList(0, individualsToTake));
    }
}
