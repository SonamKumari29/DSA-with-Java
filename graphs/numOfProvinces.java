import java.util.ArrayList;

class numOfProvinces {
    // Standard static DFS function (renamed to match OG DFS function naming)
    static void dfsOfGraph(int node, ArrayList<ArrayList<Integer>> adjLs, int vis[]) {
        vis[node] = 1; 
        for (int neighbor : adjLs.get(node)) {
            if (vis[neighbor] == 0) {
                dfsOfGraph(neighbor, adjLs, vis);
            }
        }
    }

    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>(); 
        for (int i = 0; i < V; i++) {
            adjLs.add(new ArrayList<>()); 
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adj.get(i).get(j) == 1 && i != j) {
                    adjLs.get(i).add(j); 
                }
            }
        }
        int vis[] = new int[V]; 
        int cnt = 0; 
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                cnt++;
                dfsOfGraph(i, adjLs, vis); 
            }
        }
        return cnt; 
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(0).add(0);
        adj.get(0).add(1);

        adj.add(new ArrayList<>());
        adj.get(1).add(0);
        adj.get(1).add(1);
        adj.get(1).add(0);

        adj.add(new ArrayList<>());
        adj.get(2).add(1);
        adj.get(2).add(0);
        adj.get(2).add(1);

        System.out.println(numOfProvinces.numProvinces(adj, 3));
    }
}
