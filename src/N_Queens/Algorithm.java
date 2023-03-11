package N_Queens;

public class Algorithm {
  public static Node execute(int size, String algorithm) {
    Node initial = new Node(size);
    Node result = new Node(size);

    switch (algorithm) {
      case "dfs":
        DFS dfs_visitor = new DFS(initial);
        result = dfs_visitor.runDfs();
        break;

      case "bfs":
        BFS bfs_visitor = new BFS(initial);
        result = bfs_visitor.runBfs();
        break;
    }
    return result;
  }
}
