package PSO.PSO.PSO_Sol;


import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class ParticleSwarmOptimization {
	/*PSO PARAMETERS*/
	private int ChessBoardLength;				//N number of queens
	private int numOfParticles;			//population of particles
	private double maxVelocity; 				// Maximum velocity change allowed
	private int maxRunningTime;
	private int goal; 					//0 conflicts; Number for algorithm to find.
    private int shuffleRangeMin;		//used for initializing particles randomly
    private int shuffleRangeMax;

    private Random rand;
    private ArrayList<Particle> particles;
    private ArrayList<Particle> solutions;
    private int iterationTime;


	public ParticleSwarmOptimization(Options options) {
		ChessBoardLength = options.chessBoardLength;	
		maxVelocity = options.maxVelocity; 
		maxRunningTime = options.maxIterationTime;
		
		numOfParticles = 40;
		goal = 0;
		shuffleRangeMin = 8;
		shuffleRangeMax = 20;
		iterationTime = 0;
	}

	public boolean algorithm() {
		particles = new ArrayList<Particle>();
		solutions = new ArrayList<Particle>();
		rand = new Random();
		iterationTime = 0;
		boolean done = false;
		Particle aParticle = null;

		initialize();

		while(!done) {
			if(iterationTime < maxRunningTime) {
	            for(int i = 0; i < numOfParticles; i++)  {
	                aParticle = particles.get(i);
	                aParticle.computeConflicts();
	                if(aParticle.getConflicts() == goal){
	                    done = true;
	                }
	            } // i
	            
	            Collections.sort(particles); 					// sort particles by their conflicts scores, best to worst.
	            
	            getVelocity();
	            
	            updateParticles();
	    
				iterationTime++;
			} else {
				done = true;
			}
		}

		System.out.println("done");
		if(iterationTime == maxRunningTime) {
			System.out.println("No solution found");
			done = false;
		}
		
		for(Particle p: particles) {							//prints the solutions if found within mnc
			if(p.getConflicts() == goal) {
				System.out.println("SOLUTION");
				solutions.add(p);
                printSolution(p);
                System.out.println("conflicts:"+p.getConflicts());
			}
		}
		
		return done;
	}


	public void updateParticles() {
		Particle source = null;
		Particle destination = null;
		
		// Best is at index 0, so start from the second best.
	    for(int i = 1; i < numOfParticles; i++) {
    		// The higher the velocity score, the more changes it will need.
	    	source = particles.get(i-1);
	    	destination = particles.get(i);
	    	
	    	int changes = (int)Math.floor(Math.abs(destination.getVelocity()));
    		
        	for(int j = 0; j < changes; j++) {
        		if(new Random().nextBoolean()) { //exploration
        			randomlyArrange(i);
        		}
        		// Push it closer to it's best neighbor.
        		copyFromParticle(source, destination); //exploitation
        	} // j
	        
	        // Update conflicts value.
        	destination.computeConflicts();
	    } // i		
	}
	

	public void copyFromParticle(Particle best, Particle destination) {
		// push destination's data points closer to source's data points.
		int targetA = getRandomNumber(0, ChessBoardLength - 1); 					// random get a queen's location.
		int targetB = 0;
		int indexA = 0;
		int indexB = 0;
		int tempIndex = 0;
		
		// targetB will be source's neighbor immediately succeeding targetA (circular).
		int i = 0;
		for(; i < ChessBoardLength; i++) {          //looking for the row of target queen
			if(best.getData(i) == targetA) {
				if(i == ChessBoardLength - 1) {
					targetB = best.getData(0); 								// if end of array, take from beginning.
				} else {
					targetB = best.getData(i + 1);            //target next row for exchange
				}
				break;
			}
		}
		
		// Move targetB next to targetA by switching values.
		for(int j = 0; j < ChessBoardLength; j++) {       //找到要调换的particle中 哪两行的皇后位置等于source标记的
			if(destination.getData(j) == targetA) { 
				indexA = j;
			}
			if(destination.getData(j) == targetB) {
				indexB = j;
			}
		}
		
		// get temp index succeeding indexA.
		if(indexA == ChessBoardLength - 1){
			tempIndex = 0;
		}else{
			tempIndex = indexA + 1;
		}
		
		// Switch indexB value with tempIndex value.
		int temp = destination.getData(tempIndex);
		destination.setData(tempIndex, destination.getData(indexB));
		destination.setData(indexB, temp);
		
	}

	public void getVelocity() {
		double worstResults = 0;
		double vValue = 0.0;
		Particle aParticle = null;
		
		// after sorting, worst will be last in list.
	    worstResults = particles.get(numOfParticles - 1).getConflicts();

	    for(int i = 0; i < numOfParticles; i++) {
	    	aParticle = particles.get(i);
	        vValue = (maxVelocity * aParticle.getConflicts()) / worstResults;

	        if(vValue > maxVelocity){
	        	aParticle.setVelocity(maxVelocity);
	        }else if(vValue < 0.0){
	        	aParticle.setVelocity(0.0);
	        }else{
	        	aParticle.setVelocity(vValue);
	        }
	    }
	}


	public void initialize() {
		int newParticleIndex = 0;
		int shuffles = 0;
		
		for(int i = 0; i < numOfParticles; i++) {
	        Particle newParticle = new Particle(ChessBoardLength);
	   
	        particles.add(newParticle);
	        newParticleIndex = particles.indexOf(newParticle);
	        
	        shuffles = getRandomNumber(shuffleRangeMin, shuffleRangeMax);
	        
	        for(int j = 0; j < shuffles; j++) {
	        	randomlyArrange(newParticleIndex);
	        }
	        
	        particles.get(newParticleIndex).computeConflicts();
	    } // i		
	}


	public void randomlyArrange(int index) { //randomly swap 2 positions
		int positionA = getRandomNumber(0, ChessBoardLength - 1);
		int positionB = getExclusiveRandomNumber(ChessBoardLength - 1, positionA);
		Particle thisParticle = particles.get(index);
		int temp = thisParticle.getData(positionA);
		thisParticle.setData(positionA, thisParticle.getData(positionB));
		thisParticle.setData(positionB, temp);		
	}


    public int getRandomNumber(int low, int high) {
   		return (int)Math.round((high - low) * rand.nextDouble() + low);
    }


    public int getExclusiveRandomNumber(int high, int except) {
    	boolean done = false;
    	int getRand = 0;

    	while(!done) {
    		getRand = rand.nextInt(high);
    		if(getRand != except){
    			done = true;
    		}
    	}

        return getRand;    	
    }   


    public void printSolution(Particle solution) {
       	String board[][] = new String[ChessBoardLength][ChessBoardLength];
           
       // Clear the board.
       for(int x = 0; x < ChessBoardLength; x++) {
           for(int y = 0; y < ChessBoardLength; y++) {
               board[x][y] = "";
           }
       }

       for(int x = 0; x < ChessBoardLength; x++) {
           board[x][solution.getData(x)] = "Q";
       }

       // Display the board.
       System.out.println("Board:");
       for(int y = 0; y < ChessBoardLength; y++) {
           for(int x = 0; x < ChessBoardLength; x++) {
               if(board[x][y] == "Q") {
                   System.out.print("Q ");
               } else {
                   System.out.print(". ");
               }
           }
           System.out.print("\n");
       }
    }
    
 
	public ArrayList<Particle> getSolutions() {
		return solutions;
	}
 
	public int getEpoch() {
		return iterationTime;
	}

	public int getPopSize() {
		return particles.size();
	}

	public int getParticleCount() {
		return numOfParticles;
	}

	public double getVmax() {
		return maxVelocity;
	}


	public int getMaxEpoch() {
		return maxRunningTime;
	}

	public int getShuffleMin() {
		return shuffleRangeMin;
	}

	public int getShuffleMax() {
		return shuffleRangeMax;
	}
	
	public void setMaxEpoch(int newMaxEpochs) {
		this.maxRunningTime = newMaxEpochs;
	}


	public void setVMax(double newMaxVelocity) {
		this.maxVelocity = newMaxVelocity;
	}

	public int getChessBoardLength() {
		return ChessBoardLength;
	}
	
	
}