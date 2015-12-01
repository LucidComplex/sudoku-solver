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
    private Map<Float, Float> probabilityMap;
    private Map<Float, List<Individual>> rankMap;

    public RankedBasedSelector() {
        probabilityMap = new HashMap<>();
        rankMap = new HashMap<>();
    }
    @Override
    public List<Individual> selectSurvivors(List<Individual> population, float survivalRate) {
        int selectionSize = Math.round(population.size() * survivalRate);
        List<Individual> temp = new ArrayList<>(population);
        Collections.sort(temp, new FitnessComparator());
        Collections.reverse(temp);

        // assign ranks
        mapRanks(temp);

        // assign probablities to rank
        mapProbabilities(population.size());
        // select by probability
        List<Individual> selected = new ArrayList<>();
        for (int i = 0; i < selectionSize; i++) {
            float rand = (float) Math.random();
            selected.add(map(rand));
        }

        float x = 0;
        for (Map.Entry<Float, Float> entry : probabilityMap.entrySet()) {
            x += entry.getKey();
            System.out.println(entry.getKey());
        }
        System.out.println(x);
        return selected;
    }

    private Individual map(float prob) {
        float x = 0;
        for (Map.Entry<Float, Float> entry : probabilityMap.entrySet()) {
            if (x <= prob && prob < x + entry.getKey()) {
                List<Individual> list = rankMap.get(entry.getValue());
                return list.get((int) ((Math.random() * 100) % list.size()));
            }
            x = entry.getKey();
        }
        return rankMap.values().iterator().next().get(0);
    }

    private void mapRanks(List<Individual> sortedPopulation) {
        for (int i = 0, fitness = -1, rank = 0, startRank = -1; i < sortedPopulation.size(); i++) {
            if (fitness < 0) {
                fitness = sortedPopulation.get(i).getFitness();
                startRank = i;
            }
            if (fitness != sortedPopulation.get(i).getFitness()) {
                i--;
                // calculate rank
                mapIndividuals(startRank, i, sortedPopulation);
                fitness = -1;
            } else if (i + 1 == sortedPopulation.size()) {
                // calculate rank
                mapIndividuals(startRank, i, sortedPopulation);
            }
        }
    }

    private void mapIndividuals(int startRank, int currentRank, List<Individual> sortedPopulation) {
        float averageRank = 0;
        int count = 0;
        List<Individual> individuals = new ArrayList<>();
        while (startRank <= currentRank) {
            individuals.add(sortedPopulation.get(startRank));
            averageRank += startRank++;
            count++;
        }
        averageRank /= count;
        rankMap.put(averageRank, individuals);
    }

    private void mapProbabilities(int mu) {
        float s = 1.5f;
        System.out.println(mu);
        for (Float i : rankMap.keySet()) {
            System.out.println(i + " : " + rankMap.get(i).size());

            float prob = (2 - s) / mu;
            prob += ((2 * i) * (s - 1)) / (mu * (mu - 1));
            probabilityMap.put(prob, i);
        }
    }

}