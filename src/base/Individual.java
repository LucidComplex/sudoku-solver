package base;

/**
 * Created by tan on 11/29/15.
 */
public class Individual {
    String genotype;
    int fitness;

    public Individual(String in) {
        genotype = in;
        fitness = -1;
    }

    public int getFitness() {
        return fitness;
    }

    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}