package N_Queens;

import N_Queens.Node;
import N_Queens.Queue;

public class BFS {
	   

    private Node objectiveNode;            //nœud cible que l'algorithme a trouvé
    private Node initial;                  //nœud où la recherche sera lancée
    private String treePath;               //string avec tous les nœuds visités dans l'ordre et leurs voisins
    private int treePathSize;
    private int nodesGenerated;


    public BFS(Node initial){
        this.initial = initial;
        this.objectiveNode = null;
        this.treePath = "empty!";
        this.treePathSize = 0;
    }

    public Node runBfs(){ 
        _bfs();
        return objectiveNode;
    }

    private void _bfs(){

        Queue queue = new Queue();           //creer la file
        queue.push(this.initial);           //insere le noeud dans la file

        this.treePath = "Grimper aux arbres:\n";
        while(!queue.empty()){       //test si la ligne est vide
                                	//si ce n'est pas le cas, nous prenons le nœud V qui se trouve à l'avant de la file d'attente.
            Node v = queue.front();
            queue.pop();

            v.generateNeighborhoods();                       //génère tous les voisins de V
            this.treePath += v.toStringNeighbors() + "\n";  //sauvegarde le noeud V et ses voisins dans le String treePath
            this.treePathSize++;
           
            if(v.isObjective()){            
                objectiveNode = v;        
                this.treePath += "Recherche en largeur terminée avec succès! Nombre de noeuds parcourus: " + this.treePathSize + ".\n";
                nodesGenerated++;
                return;
            }
            for(Node u : v.getNeighbors()){    //recherche tous les voisins U des sommets actuels V
                queue.push(u);                //ajoute le voisin U à la file d'attente
            }
            //Le fait que l'espace de recherche généré soit un graphe dirigé acyclique
            //indique que nous n'avons pas besoin de contrôler la redondance
        }
        this.treePath = "empty!";
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
