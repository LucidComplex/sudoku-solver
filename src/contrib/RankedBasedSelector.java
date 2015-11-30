package contrib;

import base.FitnessComparator;
import base.Individual;
import base.ParentSelector;
import base.SurvivorSelector;

import java.util.*;

/**
 * Created by tan on 11/30/15.
 */
public class RankedBasedSelector implements SurvivorSelector {
    @Override
    public List<Individual> selectSurvivors(List<Individual> population, float survivalRate) {
        int selectionSize = Math.round(population.size() * survivalRate);
        List<Individual> temp = new ArrayList<>(population);
        Collections.sort(temp, new FitnessComparator());
        Collections.reverse(temp);

        Map<Float, List<Individual>> ranks = new HashMap<>();
        for (int i = 0, fitness = -1, rank = 0, startRank = -1; i < temp.size(); i++) {
            if (fitness < 0) {
                fitness = temp.get(i).getFitness();
                startRank = i;
            }
            if (fitness != temp.get(i).getFitness()) {
                i--;
                // calculate rank
                float averageRank = 0;
                int count = 0;
                List<Individual> individuals = new ArrayList<>();
                while (startRank <= i) {
                    individuals.add(temp.get(startRank));
                    averageRank += startRank++;
                    count++;
                }
                averageRank /= count;
                ranks.put(averageRank, individuals);
                fitness = -1;
            } else if (i + 1 == temp.size()) {
                // calculate rank
                float averageRank = 0;
                int count = 0;
                List<Individual> individuals = new ArrayList<>();
                while (startRank <= i) {
                    individuals.add(temp.get(startRank));
                    averageRank += startRank++;
                    count++;
                }
                averageRank /= count;
                ranks.put(averageRank, individuals);
            }
        }
        return null;
    }

}

class Ranks {
    List<Individual> individuals;
    float rank;

    public Ranks(List<Individual> individuals, float rank) {
        this.individuals = individuals;
        this.rank = rank;
    }
}
