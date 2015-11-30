package base;

import contrib.EliteSelector;
import contrib.IntegerRepresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tan on 11/28/15.
 */
public class SudokuSolver {
    private Representer representer;
    private int populationSize;
    private int maxGenerations;
    private Recombinator recombinator;
    private ParentSelector parentSelector;
    private SurvivorSelector survivorSelector;
    private float recombinationProb;
    private float mutationProb;
    private float survivalRate;
    private Collection<Individual> population;

    public SudokuSolver() {
        population = new LinkedList<>();
        populationSize = 30;
        survivalRate = 0.7f;
        survivorSelector = new EliteSelector();
        representer = new IntegerRepresenter();
    }

    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.err.println("Missing filename.\nUsage:\n\tjava SudokuSolver <puzzle>");
        }
        SudokuSolver solver = new SudokuSolver();
        solver.solve(args[0]);
    }

    public void solve(String fileName) throws IOException {
        Sudoku sudoku;
        sudoku = SudokuParser.parse(fileName);
        int individualSize = getIndividualSize(sudoku);
        initializePopulation(sudoku.size, individualSize);
        calculateFitness(sudoku, population);
        Collection<Individual> survivors = survivorSelector.selectSurvivors(population, survivalRate);
        Collection<Individual> parents = generateParents(population);
        Collection<Individual> children = generateChildren(parents);
    }

    private Collection<Individual> generateChildren(Collection<Individual> parents) {
        return null;
    }

    private Collection<Individual> generateParents(Collection<Individual> population) {
        int pairsToGenerate = populationSize - Math.round(survivalRate * populationSize);
        Collection<Individual> parents = new ArrayList<>();
        for (int i = 0; i < pairsToGenerate; i++) {
            parents.addAll(parentSelector.selectParents(population));
        }
        return parents;
    }

    private void initializePopulation(int puzzleSize, int individualSize) {
        for (int i = 0; i < populationSize; i++ ) {
            population.add(representer.randomIndividual(puzzleSize, individualSize));
        }
    }

    private int getIndividualSize(Sudoku sudoku) {
        int size = 0;
        for (int i = 0; i < sudoku.genotype.length(); i++) {
            if (sudoku.genotype.charAt(i) == '0') {
                size++;
            }
        }
        return size;
    }

    /*
    Given a sudoku and a list of individuals, calculate their fitnesses
     */
    private void calculateFitness(Sudoku sudoku, Collection<Individual> list)  {
        for (Individual ind : list) {
            ind.fitness = sudoku.calculateFitness(ind);
        }
    }
}
