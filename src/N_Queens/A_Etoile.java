package N_Queens;


import java.util.*;

public class A_Etoile {
    private static Node objectiveNode;
	private Node initial;
	private int treePathSize;
	private String treePath;     
	
	public A_Etoile(Node initial) {
		this.initial = initial;
        A_Etoile.objectiveNode = null;
        this.treePath = "empty!";
        this.treePathSize = 0;

	}

  /* public Node runh(){ 
    	 return  aStar(initial);
    }*/
	 
	
	
     public Node aStar(Node start) {
    	 start.setState();
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(n -> n.getCost()));
        Set<Node> visited = new HashSet<>();
       // List<Node> treePath = new ArrayList<>(); // new variable to store tree path
        frontier.add(start);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.isObjective()) {
            	objectiveNode=current;
                return objectiveNode;
            }
            
            visited.add(current);
            this.treePath += current.toStringNeighbors();
            this.treePathSize++;
            for (Node neighbor : current.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    neighbor.setParent(current);
                    frontier.add(neighbor);
                }
            }
        }
        return null;
    }




	public String getTreePath() {
		  return treePath;
	}


}


