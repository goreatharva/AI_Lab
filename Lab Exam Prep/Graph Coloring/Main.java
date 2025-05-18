import java.util.*;

public class Main {
    static int V; // Number of vertices
    static int[][] graph;
    static int[] color;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices and edges
        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        graph = new int[V][V];
        color = new int[V];

        // Input edges
        System.out.println("Enter each edge (format: u v) where u and v are 0-based vertex indices:");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        // Input number of colors
        System.out.print("Enter the number of colors: ");
        int m = sc.nextInt();

        System.out.println("\nSolving Graph Coloring...");

        if (graphColoring(m)) {
            System.out.println("Coloring found:");
            for (int i = 0; i < V; i++) {
                System.out.println("Vertex " + i + " ---> Color " + color[i]);
            }
        } else {
            System.out.println("No coloring possible with " + m + " colors.");
        }
    }

    static boolean graphColoring(int m) {
        return solve(0, m);
    }

    static boolean solve(int v, int m) {
        if (v == V)
            return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, c)) {
                color[v] = c;

                if (solve(v + 1, m))
                    return true;

                color[v] = 0; // Backtrack
            }
        }
        return false;
    }

    static boolean isSafe(int v, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c)
                return false;
        }
        return true;
    }
}

/*Input1:
3
3
0 1
1 2
2 0
2
 */

 /*Input 2:
3
3
0 1
1 2
2 0
3

  
  */