package contrib;

import base.Individual;
import base.ParentSelector;
import base.Sudoku;

import java.util.Collection;

/**
 * Created by tan on 11/30/15.
 */
public class TournamentSelector implements ParentSelector {

    @Override
    public Collection<Individual> selectParents(Collection<Individual> population) {
        int k = 3;
        return null;
    }
}
