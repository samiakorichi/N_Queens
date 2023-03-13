package N_Queens;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("hello");
        int n = 0;

        while (true) {
            // lire le nombre de reines
            Scanner scan = new Scanner(System.in);

            System.out.print("Nombre de reines:");

            while (true) {

                try {
                    n = Integer.parseInt(scan.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println(".....?");
                }
            }

            Node initial = new Node(n);
            Node initialh = new Node(n);
            Node result;
            String showTree = "";

            

            while (true) {
                System.out.println("Vous souhaitez effectuer un dfs ou un bfs ou A* [dfs|bfs|heur]");
                String op = scan.nextLine().toLowerCase();
                
                
                
                
                if (op.contains("dfs")) {
                    //execution time calculation
                    long startTime = System.nanoTime();
                    DFS dfs_visitor = new DFS(initial);
                    result = dfs_visitor.runDfs();
                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime);
                    System.out.println("Execution time: " + duration + "ns");
                    showTree = dfs_visitor.getTreePath();
                    break;
                } else if (op.contains("bfs")) {
                    //execution time calculation
                    long startTime2 = System.nanoTime();
                    BFS bfs_visitor = new BFS(initial); // realize a BFS
                    result = bfs_visitor.runBfs(); // enregistre le nœud objectif trouvé ou null s'il n'y a pas de
                                                   // solution
                    long endTime2 = System.nanoTime();
                    long duration = (endTime2 - startTime2);
                    System.out.println("Execution time: " + duration + "ns");
                    showTree = bfs_visitor.getTreePath(); // enregistre l'espace de recherche dans une chaîne de
                                                          // caractères
                    break;
                }
                else if(op.contains("heur")){
                    A_Etoile h_visitor = new A_Etoile(initialh); //realize a heuristique
                    result = h_visitor.aStar(initialh);       //enregistre le nœud objectif trouvé ou null s'il n'y a pas de solution
                    showTree = h_visitor.getTreePath(); //enregistre l'espace de recherche dans une chaîne de caractères
                    break;
            }
            
            }
            if (result == null) {
                // npas de solution
                System.out.println("Désolé, il n'y a pas de solution pour n" + n);
            } else {
                // affiche le chemin du nœud de départ au nœud d'arrivée, ainsi que les voisins
                // de chaque nœud.
                System.out.println(result.showPathNeighbors());

                System.out.println("Press ENTER to see solution one as a board.");
                scan.nextLine().toLowerCase();

                // affiche le chemin du nœud de départ au nœud d'arrivée, mais en utilisant la
                // représentation du tableau n x n
                System.out.println(result.showPath(true));

                System.out.println("PAppuyez sur ENTER pour voir l'ensemble de l'arbre généré.");
                scan.nextLine().toLowerCase();

                // affiche tous les nœuds générés au cours de la recherche, ainsi que les
                // voisins de chaque nœud
                System.out.println(showTree);

            }
            
            //System.out.println("Appuyer sur ENTER pour recommencer");
            //scan.nextLine();
        
    }
}
}