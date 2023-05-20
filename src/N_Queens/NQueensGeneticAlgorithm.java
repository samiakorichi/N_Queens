package N_Queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class NQueensGeneticAlgorithm {
    
    private static int n;
    private final int populationSize;
    private final double crossoverRate;
    private final double mutationRate;
    
    private ArrayList<State> population;
    
    public NQueensGeneticAlgorithm(int n, int populationSize, double crossoverRate, double mutationRate) {
        this.n = n;
        this.populationSize = populationSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.population = new ArrayList<State>();

        
    }
    
    
    public State solve() {
        initializePopulation();
        int numGenerations = 0;
        while (numGenerations < 1000) {
           
            for (int i = 0; i < populationSize; i++) {
                State parent1 = selectParent();
                population.add(parent1);
                int fit =calculateFitness(parent1);
                //System.out.println("the fitness is: "+fit);
                State parent2 = selectParent();
                population.add(parent2);
                fit =calculateFitness(parent2);
               // System.out.println("the fitness is: "+fit);
                State child = crossover(parent1, parent2);
                population.add(child);
                fit =calculateFitness(child);
                //System.out.println("the fitness is: "+fit);
                //parent1=child;
                State child2= mutate(child);
                population.add(child2);
                fit =calculateFitness(parent2);
                //System.out.println("the fitness is: "+fit);
          
            }
           
            numGenerations++;
           
        }
       // System.out.println("Population after " + numGenerations + "generations :");
        //for (State state : population) {
            //System.out.println(state.toString());
      //  }
      State bestSolution=bestSolution(population);
        return bestSolution;
    }

    
    private void initializePopulation() {
        for (int i = 0; i < populationSize; i++) {
            State state = generateRandomState();
            population.add(state);
        }
    }
 
    private State generateRandomState() {
        ArrayList<Integer> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int column = random.nextInt(n);
            values.add(column);
        }
        return new State(values);
    }

    
  
    private static int calculateFitness(State state) {
        ArrayList<Integer> values = state.getValues();
        int conflicts = 0;
        Set<Integer> seenValues = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (seenValues.contains(values.get(i))) {
                conflicts++;
            } else {
                seenValues.add(values.get(i));
            }
            for (int j = i + 1; j < n; j++) {
                if (values.get(i) == values.get(j) || Math.abs(values.get(i) - values.get(j)) == Math.abs(i - j)) {
                    conflicts++;
                }
            }
        }
        return n * (n - 1) / 2 - conflicts; // maximum fitness is n*(n-1)/2, minimum is 0
    }


    
    

    
    private State selectParent() {
        Random random = new Random();
        int index = random.nextInt(populationSize);
        State parent = population.get(index);
        //System.out.println("Selected parent: " + parent.getValues());
        return parent;
    }

    private State crossover(State parent1, State parent2) {
        Random random = new Random();
        if (random.nextDouble() > crossoverRate) {
            //System.out.println("No crossover performed, returning parent 1.");
            return parent1;
        }
        ArrayList<Integer> values1 = parent1.getValues();
        ArrayList<Integer> values2 = parent2.getValues();
        ArrayList<Integer> childValues = new ArrayList<>(n);

        int crossoverPoint = random.nextInt(n);
        // System.out.println("Crossover point: " + crossoverPoint);

        // Perform crossover by combining values from parent1 and parent2
        for (int i = 0; i < crossoverPoint; i++) {
            childValues.add(values1.get(i));
        }
        for (int i = crossoverPoint; i < n; i++) {
            childValues.add(values2.get(i));
        }

        // Create a new State object for the child and return it
        State child = new State(childValues);
        // System.out.println("Crossover performed. Parent 1: " + values1 + ", Parent 2: " + values2 + ", Child: " + childValues);
        return child;
    }

    private State mutate(State chromosome) {
        Random random = new Random();
        ArrayList<Integer> values = new ArrayList<>(chromosome.getValues());
        int index = random.nextInt(n);
        int newValue = random.nextInt(n);
        values.set(index, newValue);
        //System.out.println("Mutation performed. State: " + values);
        return new State(values);
    }


    public static State bestSolution(List<State> population) {
        State bestIndividual = null;
        int bestFitness = Integer.MIN_VALUE;

        for (State individual : population) {
            int fitness = calculateFitness(individual);

           
            if (fitness > bestFitness) {
                bestIndividual = individual;
                bestFitness = fitness;
            }

        }

        //System.out.println("Best fitness: " + bestFitness);
        return bestIndividual;
    }
        
}     

