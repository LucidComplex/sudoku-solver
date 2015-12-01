package contrib;

import base.FitnessComparator;
import base.Individual;
import base.ParentSelector;
import base.Sudoku;

import java.util.*;
import java.util.List;

/**
 * Created by tan on 11/30/15.
 */
public class TournamentSelector implements ParentSelector {
    Random rand;

    public TournamentSelector(Random random) {
        rand = random;
    }
    @Override
    public List<Individual> selectParents(List<Individual> population) {
        List<Individual> parents = new ArrayList<>();

        List<Individual> participants;
        for (int i = 0; i < 2; i++) {
            // randomly select 3 individuals
            participants = selectParticipants(population);
            // get best
            Collections.sort(participants, new FitnessComparator());
            parents.add(participants.get(0));
        }
        return parents;
    }

    private List<Individual> selectParticipants(List<Individual> population) {
        int k = 3;
        List<Individual> selected = new ArrayList<>();
        int randIndex;
        for (int i = 0; i < k; i++) {
            randIndex = rand.nextInt(population.size());
            selected.add(population.get(randIndex));
        }
        return selected;
    }
}
