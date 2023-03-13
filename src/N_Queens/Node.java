package N_Queens;

import java.util.ArrayList;

public class Node { 
 
    int n;
    private State state; // current node state
    private Node parent; // parent node that generated the current node
    private ArrayList<Node> neighbors; // all neighboring nodes

    public State getState() {
        return state;
    }

    public Node(State state, Node parent, int n) {
        this.state = state;
        this.parent = parent;
        this.neighbors = new ArrayList<>();
        this.n = n;
    }

    public Node(int n) {
        this.state = new State(new ArrayList<>());
        this.parent = null;
        this.neighbors = new ArrayList<>();
        this.n = n;
    }

    public ArrayList<Node> generateNeighborhoods() {
        // Generate all the neighbors of the current no
        // and stores it in this 'neigh' list
        ArrayList<Node> neighbors = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // If it is possible to put the integer i at the end of the vector without
            // hurting the game properties,
            // then it is added as neighbor
            if (isValidOperation(i)) {
                ArrayList<Integer> newState = new ArrayList<>(state.getValues()); // creating a copy of the current
                                                                                  // state
                newState.add(i); // adds the integer i at the end
                Node u = new Node(new State(newState), this, n); // creates the new neighbor node
                neighbors.add(u); // adds the created node to the neighborhood
            }
        }
        return this.neighbors = neighbors; // returns String that represents the state of this node
    }

    private boolean isValidOperation(int x) {
        // Tests if it is possible to add the x at the end of the vector

        int lx = state.getValues().size(); // line of part x
        int cx = x; // piece column x

        if (lx == n)
            return false; // board has already been filled by N queens
        for (int i = 0; i < state.getValues().size(); i++) {
            int li = i;
            int ci = state.getValues().get(i);
            if (ci == cx)
                return false; // teste s'il y a déjà une pièce qui occupe la colonne de la pièce x
            if (ci - li == cx - lx)
                return false; // teste s'il y a déjà une pièce sur la diagonale (principale) de la pièce x
            if (ci + li == cx + lx)
                return false; // teste s'il y a déjà une pièce sur la diagonale (secondaire) de la pièce x
        }
        return true;
    }

    public boolean isObjective() {
        return state.getValues().size() == n;
    }
    // function tests if the state of this node is a goal

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public Node getParent() {
        return parent;
    }
    
    public void setParent(Node parent) {
		this.parent = parent;
	}
    
    

    @Override
    public String toString() {
        //
        String res = "[";
        for (int i = 0; i < state.getValues().size(); i++) {
            res += this.state.getValues().get(i);
            if (i != state.getValues().size() - 1)
                res += ',';
        }
        return res + "]";
    }

    public String toString(boolean showBoard) {
        // if showBoard = true, returns a String that shows an NxN board for a better
        // visualization of the solution
        // if not, returns the default state representation
        if (!showBoard)
            return this.toString();
        String res = "";
        for (int i = 0; i < n; i++) {
            String linha;
            if (i < state.getValues().size()) {
                int col = state.getValues().get(i);
                linha = ". ".repeat(col) + "x " + ". ".repeat(n - col - 1);
            } else {
                linha = ". ".repeat(n);
            }
            res += linha;
            if (i != n - 1)
                res += '\n';
        }
        return res;
    }

    public String showPath(boolean showBoard) {
        // returns the toString of the path from the start node (empty) to the current
        // node
        String res = "";
        Node atual = this;
        while (atual != null) {
            res = atual.toString(showBoard) + "\n" + "_".repeat(this.n * 2 + 2) + "\n" + res;
            atual = atual.getParent();
        }
        return res;
    }

    public String showPathNeighbors() {
        // returns the toString of the path from the start node (empty) to the current
        // node
        String res = "Path from the start node to the solution: \n";
        res += "State | Neighbors \n";
        Node atual = this;
        ArrayList<String> stack = new ArrayList<>();

        while (atual != null) {
            String v = atual.toString() + " = {";
            for (Node u : atual.neighbors) {
                v += u + ",";
            }
            v = v + "}\n";
            stack.add(v);
            atual = atual.getParent();
        }

        for (int i = stack.size() - 1; i >= 0; i--) {
            res += stack.get(i);
        }
        return res;
    }

    public String toStringNeighbors() {
        // returns a String with the state of the node and its neighbors
        String res = this + " = {";
        for (Node u : this.neighbors) {
            u.generateNeighborhoods();
            res += u + ",";
        }
        res = res + "}";
        return res;
    }
    
/////////////////////////////////////////////////////////////
    public int getCost() {
	    int actualCost = 0;
	    Node current = this;
	    while (current.getParent() != null) {
	        actualCost++;
	        current = current.getParent();
	    }
	    return actualCost + getHeuristic();
	}

    private int getHeuristic() {
	    int heuristic = 0;
	    ArrayList<Integer> values = State.getValues();
	    for (int i = 0; i < values.size(); i++) {
	        for (int j = i + 1; j < values.size(); j++) {
	            int xi = i;
	            int yi = values.get(i);
	            int xj = j;
	            int yj = values.get(j);
	            if (yi == yj || xi + yi == xj + yj || xi - yi == xj - yj) {
	                heuristic++;
	            }
	        }
	    }
	    return heuristic;
	}


    public int getN() {
		return n;
	}


	public void setState() {
		int n= getN();
		this.state = State.generateRandomState(n);
	}

////////////////
    
    public String showTree() {
        return "";
    }

    private String _showTree(int depth) {
        return "";
    }
}
