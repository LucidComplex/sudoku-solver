package base;

import contrib.IntegerRepresenter;

import java.io.IOException;
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
    private List<String> population;

    public SudokuSolver() {
        population = new LinkedList<>();
        populationSize = 30;
        representer = new IntegerRepresenter();
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Missing filename.\nUsage:\n\tjava SudokuSolver <puzzle>");
        }
        SudokuSolver solver = new SudokuSolver();
        solver.solve(args[0]);
    }

    public void solve(String fileName) {
        String sudoku = "";
        try {
            sudoku = SudokuParser.parse(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int individualSize = getIndividualSize(sudoku);
        initializePopulation(sudoku.length(), individualSize);
        calculateFitness(population);
    }

    private void initializePopulation(int puzzleSize, int individualSize) {
        for (int i = 0; i < populationSize; i++ ) {
            population.add(representer.randomIndividual(puzzleSize, individualSize));
        }
    }

    private int getIndividualSize(String sudoku) {
        int size = 0;
        for (int i = 0; i < sudoku.length(); i++) {
            if (sudoku.charAt(i) == '0') {
                size++;
            }
        }
        return size;
    }
}
