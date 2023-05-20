package N_Queens;

public class Algorithm {
  public static Node execute(int size, String algorithm) {
    Node initial = new Node(size);
    Node result = new Node(size);
    State solution;

    switch (algorithm) {
      case "DFS":
        DFS dfs_visitor = new DFS(initial);
        result = dfs_visitor.runDfs();
        break;

      case "BFS":
        BFS bfs_visitor = new BFS(initial);
        result = bfs_visitor.runBfs();
        break;

      case "A* heuristic 1":
        A_Etoile h1_visitor = new A_Etoile(initial); // realize a heuristique
          //result = h1_visitor.runAStar(); 
        break;

      case "A* heuristic 2":
        A_Etoile h2_visitor = new A_Etoile(initial); // realize a heuristique
         // result = h2_visitor.runAStar(); 
        break;

      case "Genetic":
        NQueensGeneticAlgorithm solver = new NQueensGeneticAlgorithm(size, 200, 0.9, 0.05); // create instance of NQueensGeneticAlgorithm
          solution = solver.solve();
          result = new Node(solution, result, size);
         
        break;
      case "pso":
       
            int population = 50;
            int maxIterations = 100;
            double c1 = 1.496;
            double c2 = 1.496;
            double inertiaWeight = 0.7298;
        PSO pso = new PSO(population, maxIterations, c1, c2, inertiaWeight, size);
            solution = pso.solve();
          result = new Node(solution, result, size);
         
        break;
    }
    return result;
  }
}