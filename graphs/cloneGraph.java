/*
Flow:
1. Use BFS to clone each node and its neighbors.
2. Use a HashMap to map original nodes to their clones.
3. Return the clone of the starting node.
*/
import java.util.*;

class Node {
    int val;
    List<Node> neighbors;
    Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }
}


public class cloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) return null; // if input is null
        Map<Node, Node> map = new HashMap<>(); // map original to clone
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        map.put(node, new Node(node.val)); // clone first node

        while (!queue.isEmpty()) {
            Node curr = queue.poll(); // get node
            for (Node neighbor : curr.neighbors) { // for each neighbor
                if (!map.containsKey(neighbor)) { // if not cloned
                    map.put(neighbor, new Node(neighbor.val)); // clone neighbor
                    queue.add(neighbor); // add to queue
                }
                map.get(curr).neighbors.add(map.get(neighbor)); // add neighbor to clone
            }
        }
        return map.get(node); // return clone of start node
    }

    public void printGraph(Node node) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print("Node " + curr.val + " neighbors: ");
            for (Node n : curr.neighbors) {
                System.out.print(n.val + " ");
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.add(n);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //  1--2
        //  |  |
        //  4--3
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.neighbors.add(n2); n1.neighbors.add(n4);
        n2.neighbors.add(n1); n2.neighbors.add(n3);
        n3.neighbors.add(n2); n3.neighbors.add(n4);
        n4.neighbors.add(n1); n4.neighbors.add(n3);

        cloneGraph cg = new cloneGraph();
        System.out.println("Original graph:");
        cg.printGraph(n1);

        Node cloned = cg.cloneGraph(n1);
        System.out.println("Cloned graph:");
        cg.printGraph(cloned);
    }
}

/*
Dry Run:
- Start with node 1, clone it.
- Visit neighbors 2 and 4, clone them, add to queue.
- For node 2, visit neighbors 1 (already cloned), 3 (clone and add to queue).
- For node 4, visit neighbors 1 (already cloned), 3 (already cloned).
- For node 3, visit neighbors 2 and 4 (already cloned).
- All nodes and edges are cloned.
*/