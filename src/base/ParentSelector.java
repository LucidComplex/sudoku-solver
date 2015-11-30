package base;

import java.util.Collection;

/**
 * Created by tan on 11/29/15.
 */
public interface ParentSelector {
    public Collection<Individual> selectParents(Collection<Individual> population);
}
