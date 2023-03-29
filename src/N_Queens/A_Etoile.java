package N_Queens;


import java.util.*;

public class A_Etoile{

    private Node objectiveNode; // nœud cible que l'algorithme a trouvé
    private Node initial; // nœud où la recherche sera lancée
    private String treePath; // string avec tous les nœuds visités dans l'ordre et leurs voisins
    private int treePathSize;
    private int nodesGenerated;
    private int heur;

    public A_Etoile (Node initial) {
        this.initial = initial;
        this.objectiveNode = null;
        this.treePath = "empty!";
        this.treePathSize = 0;
        this.nodesGenerated = 0;
        
    }

    public Node runAStar(int heur) {
        _aStar(heur);
        return objectiveNode;
    }

    private void _aStar(int heur) {

        PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>() {
            // The priority function combines the cost and heuristic values of each node
            public int compare(Node node1, Node node2) {
            	
            	if(heur==1) {
                int f1 = node1.getCost() + node1.getHeur1();
                int f2 = node2.getCost() + node2.getHeur1();
                return Integer.compare(f1, f2);}
            	else if(heur==2) {
                    int f1 = node1.getCost() + node1.getHeur2();
                    int f2 = node2.getCost() + node2.getHeur2();
                    return Integer.compare(f1, f2);}
            	else {
                    // add a default return statement
                    return 0;
                }
            }
        });

        queue.add(this.initial);

        this.treePath = "Grimper aux arbres:\n";
        while (!queue.isEmpty()) {
            Node v = queue.poll();

            v.generateNeighborhoods();
            this.treePath += v.toStringNeighbors() + "\n";
            this.treePathSize++;

            if (v.isObjective()) {
                objectiveNode = v;
                this.treePath += "Recherche A* terminée avec succès ! Nombre de liens parcourus: "
                        + this.treePathSize + ".\n";
                return;
            }
            for (Node u : v.getNeighbors()) {
                queue.add(u);
                nodesGenerated++;
            }
        }
        this.treePath = "empty!";
    }
    public int getNodesGenerated() {
        return this.nodesGenerated;
    }
    public int getTreePathSize() {
        return this.treePathSize;
    }
    public String getTreePath() {
        return treePath;
    }
    
}
