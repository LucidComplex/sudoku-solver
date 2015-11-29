package base;

import java.util.Collection;
import java.util.Vector;

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
}