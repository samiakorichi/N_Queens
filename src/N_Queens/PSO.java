package N_Queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class PSO {

    private final int population;
    private final int maxIterations;
    private final double c1;
    private final double c2;
    private final double inertiaWeight;
    private final int n;
    private ArrayList<Particule> particles;

    public PSO(int swarmSize, int maxIterations, double c1, double c2, double inertiaWeight, int n) {
        this.population = swarmSize;
        this.maxIterations = maxIterations;
        this.c1 = c1;
        this.c2 = c2;
        this.inertiaWeight = inertiaWeight;
        this.n = n;
        particles = new ArrayList<>();
        initializeParticles();
    }

    private ArrayList<Particule> initializeParticles() {
        ArrayList<Particule> initialStates = new ArrayList<>();

        for (int i = 0; i < population; i++) {
            ArrayList<Integer> velocity = new ArrayList<>();
            State position = new State(n);
            System.out.println(position);
            // Initialize velocity using a linearly decreasing sequence
            for (int j = 0; j < n; j++) {
                velocity.add(n - j);
            }

            Particule initialState = new Particule(velocity, position);
            initialStates.add(initialState);
        }

        particles = new ArrayList<>(initialStates);
       /* for (particule particle : particles) {
            System.out.println(particle.getPosition());
        }*/
        return initialStates;
    }



    private State getBestPosition(ArrayList<Particule> initialStates) {
        State bestPosition = initialStates.get(0).getPosition(); // initialize to the first particle's position
        
        int bestFitness = initialStates.get(0).calculateFitness(bestPosition);
        
        for (Particule particle : initialStates) {
            State currentPosition = particle.getPosition();
            int fitness = particle.calculateFitness(currentPosition);
            System.out.println("fitness\t"+fitness);
            if (fitness < bestFitness) {
                bestFitness = fitness;
                if (particle.getPersonalBest() != null) {
                    bestPosition = particle.getPersonalBest();
                } else {
                    bestPosition = currentPosition;
                }
            }
        }
        return bestPosition;
    }



    public State solve() {
        int iteration = 0;
       ArrayList<Particule> particles = initializeParticles();
        while (iteration < maxIterations) {
            for (Particule particle : particles) {
                State currentPosition = particle.getPosition();
                
               
                //System.out.println(particle.getPosition());
                ArrayList<Integer> currentVelocity = particle.getVelocity();
                ArrayList<Integer> newVelocity = new ArrayList<>(Collections.nCopies(n, 0));
                int[] newPosition = new int[n];
                
                
                State personalBestPosition = particle.getPersonalBest();

              System.out.println("personalBestPosition\t"+personalBestPosition);
              System.out.println("\n");
                State globalBestPosition = getBestPosition(particles);
                System.out.println("globalBestPosition\t"+globalBestPosition);
                
                ArrayList<Integer> currentValues = currentPosition.getValues();
               
                
                for (int i = 0; i < n; i++) {
                	
                    newVelocity.set(i, (int) (inertiaWeight * currentVelocity.get(i)
                            + c1 * Math.random() * (personalBestPosition.getValues().get(i) - currentValues.get(i))
                            + c2 * Math.random() * (globalBestPosition.getValues().get(i) - currentValues.get(i))));
                    newPosition[i] = currentValues.get(i) + newVelocity.get(i);
                    if (newPosition[i] < 0) {
                        newPosition[i] = 0;
                    } else if (newPosition[i] >= n) {
                        newPosition[i] = n - 1;
                    }
                 
                }
                
                ArrayList<Integer> newPositionList = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    newPositionList.add(newPosition[i]);
                }
                particle.setPosition(new State(newPositionList));
                particle.setVelocity(newVelocity);
            }
            iteration++;
           
            
        }
        State solution = getBestPosition(particles);
        return solution;
        
    }

    public static void main(String[] args) {
        int population = 50;
        int maxIterations = 100;
        double c1 = 1.496;
        double c2 = 1.496;
        double inertiaWeight = 0.7298;
        
        PSO pso = new PSO(population, maxIterations, c1, c2, inertiaWeight, 5);
        State solution = pso.solve();
        
        System.out.println("Solution found: " + solution);
        System.out.println(solution.toString(5));
        Particule p = new Particule(new ArrayList<Integer>(), new State(5)); // create an instance of the particule class
        int conflicts = (int) p.calculateFitness(solution); // call calculateFitness on the instance
        System.out.println("Number of conflicts: " + conflicts);
    }

    }
