package base;

import java.util.List;

/**
 * Created by tan on 11/29/15.
 */
public interface SurvivorSelector {
    public abstract List<Individual> selectSurvivors(List<Individual> population, float survivalRate);
}
