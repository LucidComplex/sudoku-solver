package contrib;

import base.Individual;
import base.SurvivorSelector;

import java.util.*;

/**
 * Created by tan on 11/29/15.
 */
public class EliteSelector implements SurvivorSelector{

    @Override
    public Collection<Individual> selectSurvivors(Collection<Individual> population, float survivalRate) {
        int selectionSize = Math.round(population.size() * survivalRate);
        List<Individual> populationList = new ArrayList<>(population);
        Collections.sort(populationList, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println(populationList.get(0).getFitness());
        Collections.reverse(populationList);
        System.out.println(populationList.get(0).getFitness());
        return null;
    }
}
