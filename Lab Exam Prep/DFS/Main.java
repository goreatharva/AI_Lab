
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take number of vertices
        System.out.print("Enter number of vertices: ");
        int numVertices = sc.nextInt();

        // Initialize adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Take number of edges
        System.out.print("Enter number of edges: ");
        int numEdges = sc.nextInt();

        // Take edge inputs
        System.out.println("Enter edges (from to):");
        for (int i = 0; i < numEdges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u); // Comment this line for directed graph
        }

        // Take starting node
        System.out.print("Enter starting node: ");
        int startNode = sc.nextInt();

        // Perform DFS
        boolean[] visited = new boolean[numVertices];
        System.out.println("DFS traversal starting from node " + startNode + ":");
        dfs(graph, startNode, visited);

        sc.close();
    }

    static void dfs(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }
    }
}

/*Input:
5  
4  
0 1  
0 2  
1 3  
1 4  
0
 */