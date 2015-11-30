package base;

import java.util.List;

/**
 * Created by tan on 11/29/15.
 */
public interface ParentSelector {
    public List<Individual> selectParents(List<Individual> population);
}
