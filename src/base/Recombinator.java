package base;

import java.util.List;

/**
 * Created by tan on 11/29/15.
 */
public interface Recombinator {
    public List<Individual> recombine(List<Individual> parents);
}
