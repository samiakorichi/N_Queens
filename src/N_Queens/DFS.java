package N_Queens;

import N_Queens.Node;

public class DFS {
	
	//DFSvisitor performs a depth search given a start node

    private Node objectiveNode;     //all objective nodes that the algorithm has found
    private Node initial;           //node where the search will start
    private String treePath;        //string with all visited nodes in order and their neighbors
    private int treePathSize;       //the number of nodes visited during the search
    private int nodesGenerated;
 
    public DFS(Node inicial){
        this.initial = inicial;
        this.objectiveNode = null;
        this.treePath = "";
        this.treePathSize = 0;
        this.nodesGenerated=0;
    }

    public Node runDfs(){    //execute le parcours en profondeur
        _dfs(initial);                 //la pile est utilisée implicitement par les appels récursifs
        return objectiveNode;
    }

    private boolean _dfs(Node v){
        v.generateNeighborhoods();                      //génère tous les voisins du nœud actuel
        nodesGenerated += v.getNeighbors().size(); 
        this.treePath += v.toStringNeighbors() + "\n"; //sauvegarde le nœud V et ses voisins dans la chaîne treePath
        this.treePathSize++;

        if(v.isObjective()){            
            objectiveNode = v;         //Si le noeud est objectif il est ajouté aux nœuds objectifs.
            this.treePath += "\nDeep Search performed successfully! Number of traversed nodes: " + this.treePathSize + ".\n";
            return true;
        }else{
            for(Node u : v.getNeighbors()){        //searches all U neighbors of the current node
                boolean foundSolution = _dfs(u); //calls the recursive function to the neighbor
                if(foundSolution)
                    return true;
            }
            return false;
        }
        //Le fait que l'espace de recherche généré soit un graphe dirigé acyclique
        //indique que nous n'avons pas besoin de contrôler la redondance
    }

    public String getTreePath() {
        return treePath;
    }
    public int getNodesGenerated() {
        return this.nodesGenerated;
    }
    public int getTreePathSize() {
        return this.treePathSize;
    }

}

