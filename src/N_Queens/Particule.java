package N_Queens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import N_Queens.State;

public class Particule {

	
	
	private static int length; 
	 private State position;
		private ArrayList<Integer> velocity;
		 private State personalBest;
		 
	 
	 public Particule() {	
		}
	 public Particule(Particule initialState) {
		
	}

	 public Particule(ArrayList<Integer> velocity, State position) {
		    this.velocity = new ArrayList<Integer>(velocity);
		    this.position = position;
		    this.personalBest = new State(position.getValues());
		}



	public State getPersonalBest() {
		return personalBest;
	}



	public void setPersonalBest(State personalBest) {
		this.personalBest = personalBest;
	}
	 
	 public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}



	public  State getPosition() {
		return position;
	}



	public void setPosition(State position) {
		this.position = position;
	}



	public ArrayList<Integer> getVelocity() {
		return velocity;
	}



	public void setVelocity(ArrayList<Integer> velocity2) {
		this.velocity = velocity2;
	}

	 
	 
	 
	public Particule(int n) {
	    length = n;
	    ArrayList<Integer> positionValues = new ArrayList<Integer>();
	    velocity = new ArrayList<Integer>(n);
	    for (int i = 0; i < n; i++) {
	        positionValues.add((int) (Math.random() * n)); // Initialize with random values
	        velocity.add(i, (int) (Math.random() * (2 * n + 1) - n - 1));
	    }
	    position = new State(positionValues);
	}

	 
	
	
	



	
	 
	 public void update_velocity(double w, double c1, double c2, State global_best) {
	        ArrayList<Integer> currentValues = position.getValues();
	        ArrayList<Integer> personalBestValues = personalBest.getValues();
	        ArrayList<Integer> globalBestValues = global_best.getValues();
	        
	        ArrayList<Integer> newVelocity = new  ArrayList<Integer>(length);
	        for (int i = 0; i < length; i++) {
	            int velComponent1 = (int) (w * velocity.get(i));
	            int velComponent2 = (int) (c1 * Math.random() * (personalBestValues.get(i) - currentValues.get(i)));
	            int velComponent3 = (int) (c2 * Math.random() * (globalBestValues.get(i) - currentValues.get(i)));
	            newVelocity.set(i, velComponent1 + velComponent2 + velComponent3) ;
	        }
	        velocity = newVelocity;
	    }
	 
	 
	 public void updatePosition() {
		    ArrayList<Integer> newPosValues = new ArrayList<Integer>();
		    for (int i = 0; i < length; i++) {
		        newPosValues.add(position.getValues().get(i) + velocity.get(i));
		    }
		    State newPosition = new State(newPosValues);
		    position = newPosition;
		}
	 static int calculateFitness(State state) {
		    int conflicts = 0;
		    ArrayList<Integer> positions = state.getValues();
		    for (int i = 0; i < positions.size(); i++) {
		        for (int j = i + 1; j < positions.size(); j++) {
		            if (positions.get(i).equals(positions.get(j))) {
		                conflicts++;
		            }
		            int offset = j - i;
		            if (positions.get(i) == positions.get(j) - offset || positions.get(i) == positions.get(j) + offset) {
		                conflicts++;
		            }
		        }
		    }
		    return conflicts ;
		}




}
