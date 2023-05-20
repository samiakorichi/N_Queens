package PSO.PSO.PSO_Sol;

import java.util.ArrayList;

public class TesterPSO {
	ParticleSwarmOptimization pso;
	int maxRunTime;
	int MAX_LENGTH;
	long[] runtimes;
	private ArrayList<Particle> solutions = new ArrayList<Particle>();

	/*
	 * Instantiates the TesterPSO class
	 *
	 */
	public TesterPSO(ParticleSwarmOptimization pso) {
		maxRunTime = 50;
		runtimes = new long[maxRunTime];
		this.pso = pso;
	}

	public ArrayList<Particle> test() {
		
		int fail = 0;
		int success = 0;

		for (int i = 0; i < maxRunTime;) { // run 50 sucess to pass passing
											// criteria
			if (pso.algorithm()) {
		
				System.out.println("Done");
				System.out.println("run " + (i + 1));
				System.out.println("Success!");

				i++;
				success++;

				for (Particle p : pso.getSolutions()) { 
														
					solutions.add(p);
				}
			} else { 
				fail++;
				System.out.println("Fail!");
			}

			if (fail >= 100) {
				System.out.println("Cannot find solution with these params");
				break;
			}


		}

		System.out.println("Number of Success: " + success);
		System.out.println("Number of failures: " + fail);

		return solutions;
	}

}
