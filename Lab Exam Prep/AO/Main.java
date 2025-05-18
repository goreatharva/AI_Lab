Code:


import java.util.*;

public class Main {
    static class Node {
        String name;
        boolean solved = false;
        int heuristic;
        List<Edge> children = new ArrayList<>();

        Node(String name, int heuristic) {
            this.name = name;
            this.heuristic = heuristic;
        }
    }

    static class Edge {
        List<String> subNodes; // Names of AND/OR children
        int cost;

        Edge(List<String> subNodes, int cost) {
            this.subNodes = subNodes;
            this.cost = cost;
        }
    }

    static Map<String, Node> graph = new HashMap<>();
    static Map<String, String> solution = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of nodes
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Input each node's heuristic
        System.out.println("Enter each node and its heuristic value:");
        for (int i = 0; i < n; i++) {
            System.out.print("Node name and heuristic (e.g., A 5): ");
            String[] parts = sc.nextLine().split(" ");
            graph.put(parts[0], new Node(parts[0], Integer.parseInt(parts[1])));
        }

        // Input number of edges
        System.out.print("Enter number of connections (edges): ");
        int e = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter connections in format:");
        System.out.println("Parent -> child1 [child2 ...] cost");
        System.out.println("If it's an OR node, enter only one child");
        for (int i = 0; i < e; i++) {
            System.out.print("Connection: ");
            String[] tokens = sc.nextLine().split(" ");
            String parent = tokens[0];
            List<String> subNodes = new ArrayList<>();
            int j = 2;
            while (!isInteger(tokens[j])) {
                subNodes.add(tokens[j]);
                j++;
            }
            int cost = Integer.parseInt(tokens[j]);
            graph.get(parent).children.add(new Edge(subNodes, cost));
        }

        // Input start node
        System.out.print("Enter start node: ");
        String start = sc.nextLine();

        System.out.println("\nRunning AO*...");
        aoStar(start);

        System.out.println("\nOptimal solution path:");
        printSolution(start, "");
    }

    static void aoStar(String nodeName) {
        Node node = graph.get(nodeName);
        if (node.solved) return;

        int minCost = Integer.MAX_VALUE;
        Edge bestEdge = null;

        for (Edge edge : node.children) {
            int cost = edge.cost;
            for (String child : edge.subNodes) {
                cost += graph.get(child).heuristic;
            }

            if (cost < minCost) {
                minCost = cost;
                bestEdge = edge;
            }
        }

        if (bestEdge == null) {
            node.solved = true;
            return;
        }

        node.heuristic = minCost;
        solution.put(nodeName, String.join(" ", bestEdge.subNodes));

        boolean allSolved = true;
        for (String child : bestEdge.subNodes) {
            aoStar(child);
            if (!graph.get(child).solved) {
                allSolved = false;
            }
        }

        if (allSolved) {
            node.solved = true;
        }
    }

    static void printSolution(String node, String indent) {
        System.out.println(indent + node);
        if (solution.containsKey(node)) {
            for (String sub : solution.get(node).split(" ")) {
                printSolution(sub, indent + "  ");
            }
        }
    }

    static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}



/*Input1:
4
A 10
B 5
C 0
D 0
2
A -> B C 0
B -> D 5
A
 */

 /*Input2:
3
A 5
B 0
C 10
2
A -> B 10
A -> C 5
A
 */