package base;

import java.util.List;

/**
 * Created by tan on 11/29/15.
 */
public abstract class SurvivorSelector {
    public abstract List<String> selectSurvivors(List<String> population, float survivalRate);
}
