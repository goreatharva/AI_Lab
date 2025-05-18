import java.util.*;

public class Main {
    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int fCost;

        Node(int vertex, int fCost) {
            this.vertex = vertex;
            this.fCost = fCost;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.fCost, other.fCost);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        // Initialize graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Input number of edges
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter edges in format: from to cost");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            graph.get(u).add(new Edge(v, cost));
            // Uncomment the next line for undirected graph
            // graph.get(v).add(new Edge(u, cost));
        }

        // Input heuristic values
        int[] h = new int[n];
        System.out.println("Enter heuristic values for each node:");
        for (int i = 0; i < n; i++) {
            System.out.print("h(" + i + "): ");
            h[i] = sc.nextInt();
        }

        // Input start and goal
        System.out.print("Enter start node: ");
        int start = sc.nextInt();
        System.out.print("Enter goal node: ");
        int goal = sc.nextInt();

        // Validate start and goal
        if (start < 0 || start >= n || goal < 0 || goal >= n) {
            System.out.println("Invalid start or goal node.");
        } else {
            aStar(graph, h, start, goal);
        }

        sc.close();
    }

    static void aStar(List<List<Edge>> graph, int[] h, int start, int goal) {
        int n = graph.size();
        int[] g = new int[n]; // Cost from start to node
        int[] f = new int[n]; // Estimated total cost
        int[] parent = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(g, Integer.MAX_VALUE);
        Arrays.fill(f, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        g[start] = 0;
        f[start] = h[start];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, f[start]));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (visited[u]) continue;
            visited[u] = true;

            // Uncomment to debug:
             System.out.println("Visiting Node " + u + " with f = " + f[u] + ", g = " + g[u]);

            if (u == goal) {
                System.out.println("Path found:");
                printPath(parent, goal);
                System.out.println("\nTotal cost: " + g[goal]);
                return;
            }

            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int cost = edge.cost;

                if (!visited[v] && g[u] + cost < g[v]) {
                    g[v] = g[u] + cost;
                    f[v] = g[v] + h[v];
                    parent[v] = u;
                    pq.add(new Node(v, f[v]));
                }
            }
        }

        System.out.println("No path found.");
    }

    static void printPath(int[] parent, int node) {
        if (node == -1) return;
        printPath(parent, parent[node]);
        System.out.print(node + " ");
    }
}


/*Input 1
4
4
0 1 1
0 2 3
1 3 5
2 3 2
4
5
2
0
0
3*/

/* Input ( No Path Found)
3
1
0 1 1
5
3
0
0
0
2
*/

/*Input 2
5
4
0 1 2
1 2 2
2 3 2
3 4 2
7
5
3
2
0
0
4
*/

/*Input 3
6
7
0 1 1
0 2 4
1 3 1
2 3 1
1 4 6
4 5 1
3 5 2
10
6
4
2
1
0
0
5
*/
