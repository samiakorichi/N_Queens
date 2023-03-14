package N_Queens;

public class Algorithm {
  public static Node execute(int size, String algorithm) {
    Node initial = new Node(size);
    Node result = new Node(size);

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
        break;
      case "A* heuristic 2":
        break;
    }
    return result;
  }
}
