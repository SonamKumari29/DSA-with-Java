import java.util.LinkedList;
import java.util.Queue;
class RottenOranges {
    public int orangesRotting(int[][] grid) {
        int r=grid.length;
        int c=grid[0].length;
        Queue<int[]> queue=new LinkedList<>();
        int freshCount=0;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(grid[i][j]==2){
                    queue.add(new int[]{i,j});
                }
                else if(grid[i][j]==1){
                    freshCount++;
                }
            }
        }
        if(freshCount==0) return 0;
        int minutes=-1;
        int[][] directions={{0,1},{1,0},{0,-1},{-1,0}};
        while(!queue.isEmpty()){
            int size=queue.size();
            minutes++;
            for(int i=0;i<size;i++){
                int[] pos=queue.poll();
                for(int[] dir:directions){
                    int newR=pos[0]+dir[0];
                    int newC=pos[1]+dir[1];
                    if(newR>=0 && newR<r && newC>=0 && newC<c && grid[newR][newC]==1){
                        grid[newR][newC]=2;
                        freshCount--;
                        queue.add(new int[]{newR,newC});
                    }
                }
            }
        }
        return (freshCount==0)?minutes:-1;
    }
    public static void main(String args[])
    {
        int arr[][]={ {2,1,1} , {1,1,0} , {0,1,1} };
        RottenOranges ro = new RottenOranges();
        int rotting = ro.orangesRotting(arr);
        System.out.println("Minimum Number of Minutes Required "+rotting);
    }
}

/*
Algorithm Flow:
- Add all rotten oranges to a queue.
- Count all fresh oranges.
- Run BFS level by level (each level = 1 minute).
- For each rotten orange, rot adjacent fresh ones.
- Track time and decrease fresh count.
- If fresh count becomes 0, return time; else return -1.

Time Complexity: O(m * n)
- Each cell is processed once.

Space Complexity: O(m * n)
- Queue can store all cells in worst case.
*/
