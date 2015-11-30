package base;

import java.util.Collection;
import java.util.List;

/**
 * Created by tan on 11/29/15.
 */
public interface SurvivorSelector {
    public abstract Collection<Individual> selectSurvivors(Collection<Individual> population, float survivalRate);
}
