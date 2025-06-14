/*
Kruskal's Algorithm Flow:
1. Sort all edges in non-decreasing order of their weight.
2. Initialize a disjoint-set for all vertices.
3. Pick the smallest edge. If it doesn't form a cycle, add it to the result.
4. Repeat step 3 until there are (V-1) edges in the result.

*/

// Disjoint Set (Union-Find)
class DSU {
    int[] parent, rank;
    DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i; // self parent
    }
    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]); // path compression
        return parent[x];
    }
    void union(int x, int y) {
        int xr = find(x), yr = find(y);
        if (xr == yr) return;
        if (rank[xr] < rank[yr]) parent[xr] = yr;
        else if (rank[xr] > rank[yr]) parent[yr] = xr;
        else { parent[yr] = xr; rank[xr]++; }
    }
}

public class kruskalAlgo {
    // Make Edge a static nested class
    public static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int s, int d, int w) { src = s; dest = d; weight = w; }
        @Override
        public int compareTo(Edge other) { return this.weight - other.weight; } // sort by weight
    }

    public static void main(String[] args) {
        int V = 4; // number of vertices
        Edge[] edges = {
            new Edge(0, 1, 10), // edge 0-1 weight 10
            new Edge(0, 2, 6),  // edge 0-2 weight 6
            new Edge(0, 3, 5),  // edge 0-3 weight 5
            new Edge(1, 3, 15), // edge 1-3 weight 15
            new Edge(2, 3, 4)   // edge 2-3 weight 4
        };

        java.util.Arrays.sort(edges); // sort edges by weight
        DSU dsu = new DSU(V); // create DSU for V vertices

        int mstWeight = 0; // total weight of MST
        java.util.List<Edge> mst = new java.util.ArrayList<>(); // store MST edges

        for (Edge e : edges) { // for each edge
            int uSet = dsu.find(e.src); // find set of src
            int vSet = dsu.find(e.dest); // find set of dest
            if (uSet != vSet) { // if not in same set (no cycle)
                mst.add(e); // add edge to MST
                mstWeight += e.weight; // add weight
                dsu.union(uSet, vSet); // union sets
            }
            if (mst.size() == V - 1) break; // MST complete
        }

        System.out.println("Edges in MST:");
        for (Edge e : mst)
            System.out.println(e.src + " - " + e.dest + " : " + e.weight); // print MST edges
        System.out.println("Total weight: " + mstWeight); // print total weight
    }
}
// Dry Run :
// Vertices: 0, 1, 2, 3
// Edges: (0-1, 10), (0-2, 6), (0-3, 5), (1-3, 15), (2-3, 4)
// Sorted: (2-3,4), (0-3,5), (0-2,6), (0-1,10), (1-3,15)
// Pick (2-3,4), (0-3,5), (0-2,6) => MST weight = 4+5+6=15