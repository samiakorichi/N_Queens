package N_Queens;

public class Algorithm {
  public static Result execute(int size, String algorithm) {
    Node initial = new Node(size);
    Node result = new Node(size);
    DFS dfs_visitor;
    int nodesGenerated = 0;
    int nodesExpended = 0;
    
 
    switch (algorithm) {
      case "DFS":
        dfs_visitor = new DFS(initial);
        result = dfs_visitor.runDfs();
        nodesGenerated = dfs_visitor.getNodesGenerated();
        nodesExpended = dfs_visitor.getTreePathSize();
        
        break;

      case "BFS":
        BFS bfs_visitor = new BFS(initial);
        result = bfs_visitor.runBfs();
        nodesGenerated = bfs_visitor.getNodesGenerated();
        nodesExpended = bfs_visitor.getTreePathSize();
        break;

      case "A* heuristic 1":
    	  A_Etoile Heur1_visitor = new A_Etoile(initial);
          result = Heur1_visitor.runAStar(1);
          nodesGenerated = Heur1_visitor.getNodesGenerated();
          nodesExpended = Heur1_visitor.getTreePathSize();
        break;
      case "A* heuristic 2":
    	  A_Etoile Heur2_visitor = new A_Etoile(initial);
          result = Heur2_visitor.runAStar(2);
          nodesGenerated = Heur2_visitor.getNodesGenerated();
          nodesExpended = Heur2_visitor.getTreePathSize();
        break;
    }
    return new Result(result, nodesGenerated, nodesExpended);
  }
  public static class Result {
	    public final Node node;
	    public final int nodesGenerated;
	    public final int nodesExpended;
	    
	    public Result(Node node, int nodesGenerated, int nodesExpended) {
	      this.node = node;
	      this.nodesGenerated = nodesGenerated;
	      this.nodesExpended = nodesExpended;
	    }
	  }
}
