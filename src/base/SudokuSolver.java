package base;

import contrib.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

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
    private Mutator mutator;
    private float recombinationProb;
    private float mutationProb;
    private float survivalRate;
    private List<Individual> population;

    public SudokuSolver() {
        population = new ArrayList<>();
        populationSize = 10;
        survivalRate = 0.7f;
        survivorSelector = new EliteSelector();
        parentSelector = new TournamentSelector();
        recombinator = new OnePointCrossoverRecombinator();
        recombinationProb = 0.7f;
        mutationProb = 0.15f;
        mutator = new CreepMutator();
        representer = new IntegerRepresenter();
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
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
        int iterations = 0;
        while (true) {
            iterations++;
            calculateFitness(sudoku, population);
            for (Individual ind : population) {
                if (ind.fitness == 0) {
                    System.out.println("Solution: " + ind.genotype);
                    System.out.println("Iteration count " + iterations);
                    System.exit(0);
                }
            }
            List<Individual> survivors = survivorSelector.selectSurvivors(population, survivalRate);
            List<Individual> parents = generateParents(population);
            List<Individual> children = generateChildren(parents);
            population = new ArrayList<>();
            population.addAll(survivors);
            population.addAll(children);
            mutatePopulation(population, sudoku.size);
            calculateFitness(sudoku, population);
            System.out.println(overallFitness(population));
        }
    }

    private void mutatePopulation(List<Individual> population, int puzzleSize) {
        for (Individual ind : population) {
            if (Math.random() <= mutationProb) {
                mutator.mutate(ind, puzzleSize);
            }
        }
    }

    private int overallFitness(List<Individual> list) {
        int total = 0;
        for (Individual i : list) {
            total += i.fitness;
        }
        return total;
    }

    private List<Individual> generateChildren(List<Individual> parents) {
        int childrenToGenerate = Math.round((1 - survivalRate) * populationSize);
        List<Individual> children = new ArrayList<>();
        for (int i = 0; i <= childrenToGenerate / 2; i++) {
            if (recombinationProb >= Math.random()) {
                children.addAll(recombinator.recombine(new ArrayList<>(parents.subList(i * 2, i * 2 + 2))));
            } else {
                children.addAll(new ArrayList<>(parents.subList(i * 2, i * 2 + 2)));
            }
        }
        if (childrenToGenerate % 2 == 1) {
            children.remove(childrenToGenerate);
        }
        return children;
    }

    private List<Individual> generateParents(List<Individual> population) {
        int childrenToGenerate = Math.round((1 - survivalRate) * populationSize);
        int pairsToGenerate =  childrenToGenerate / 2 + childrenToGenerate % 2 ;
        List<Individual> parents = new ArrayList<>();
        for (int i = 0; i < pairsToGenerate; i++) {
            parents.addAll(parentSelector.selectParents(population));
        }
        return parents;
    }

    private void initializePopulation(int puzzleSize, int individualSize) {
        for (int i = 0; i < populationSize; i++) {
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
    private void calculateFitness(Sudoku sudoku, List<Individual> list) {
        for (Individual ind : list) {
            ind.setFitness(sudoku.calculateFitness(ind));
        }
    }
}
