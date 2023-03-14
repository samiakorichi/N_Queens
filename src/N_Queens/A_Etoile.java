package N_Queens;


import java.util.*;

public class A_Etoile{
	private Node objectiveNode; // nœud cible que l'algorithme a trouvé
	private Node initial; // nœud où la recherche sera lancée
	private String treePath; // string avec tous les nœuds visités dans l'ordre et leurs voisins
	private int treePathSize;
	private static int nodesGenerated;
	
public A_Etoile(Node initial) {
    this.initial = initial;
    this.objectiveNode = null;
    this.treePath = "empty!";
    this.treePathSize = 0;
    this.nodesGenerated = 0;
}

public Node runA1() {
    _A_Etoile();
    return objectiveNode;
     
    
}

private void _A_Etoile() {

    Queue queue = new Queue(); // creer la file
    queue.push(this.initial); // insere le noeud dans la file

    this.treePath = "Grimper aux arbres:\n";
    while (!queue.empty()) { // test si la ligne est vide
                             // si ce n'est pas le cas, nous prenons le nœud V qui se trouve à l'avant de la
                             // file d'attente.
        Node v = queue.front();
        queue.pop();

        ArrayList<Node> level=  v.generateNeighborhoodsA(); // génère tous les voisins de V
        Node u=new Node(Node.getN());
        int i=0;
        while (level.contains(u)&& i<level.size()&& u.getCost()==0) { // recherche tous les voisins U des sommets actuels V
        		 queue.push(u); // ajoute le voisin U à la file d'attente
                 nodesGenerated++; // increment nodesGenerated
                 i++;
  
        }
        this.treePath += v.toStringNeighbors() + "\n"; // sauvegarde le noeud V et ses voisins dans le String
        
        
        // treePath
        this.treePathSize++;

        if (v.isObjective()) {
            objectiveNode = v;
            this.treePath += "Recherche de diffusion terminée avec succès ! Nombre de liens parcourus: "
                    + this.treePathSize + ".\n";
            return;
        }
        /*ArrayList<Node> level2=  v.generateNeighborhoodsA();
        Node u1=new Node(Node.getN());
        int i1=0;
        while (level.contains(u)&& i1<level2.size()&& u1.getCost()==0) { // recherche tous les voisins U des sommets actuels V
        		 queue.push(u1); // ajoute le voisin U à la file d'attente
                 nodesGenerated++; // increment nodesGenerated
                 i1++;
  
        }*/
        // Le fait que l'espace de recherche généré soit un graphe dirigé acyclique
        // indique que nous n'avons pas besoin de contrôler la redondance
    }
    this.treePath = "empty!";
}
public static int getNodesGenerated() {
    return nodesGenerated;
}
}