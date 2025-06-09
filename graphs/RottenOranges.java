import java.util.LinkedList;
import java.util.Queue;
class RottenOranges {
    //2 1 1
    //1 1 0
    //0 1 1

    public int orangesRotting(int[][] grid) {
        int r=grid.length;//3
        int c=grid[0].length;//3
        Queue<int[]> queue=new LinkedList<>();//empty queue
        int freshCount=0;
        for(int i=0;i<r;i++){ //0,1,2
            for(int j=0;j<c;j++){//0,1,2
                if(grid[i][j]==2){
                    queue.add(new int[]{i,j});//2 added to queue at 0,0
                }
                else if(grid[i][j]==1){
                    freshCount++;// total=6
                }
            }
        }
        if(freshCount==0) return 0;
        int minutes=-1;
        int[][] directions={{0,1},{1,0},{0,-1},{-1,0}}; //right, down, left, up
        //bfs
        while(!queue.isEmpty()){
            int size=queue.size();
            minutes++;//-1 + 1 = 0 (starting from 0 minutes)
            for(int i=0;i<size;i++){
                int[] pos=queue.poll();//remove front of queue(0,0)
                for(int[] dir:directions){//neighbours of 0,0
                    int newR=pos[0]+dir[0]; //new row
                    int newC=pos[1]+dir[1];// new column
                    if(newR>=0 && newR<r && newC>=0 && newC<c && grid[newR][newC]==1){ //bounds check and fresh orange check
                        grid[newR][newC]=2;// rot the fresh orange
                        freshCount--;// decrease fresh count
                        queue.add(new int[]{newR,newC});// add to queue
                    }
                }
            }
        }
        return (freshCount==0)?minutes:-1;// if all fresh oranges are rotten, return minutes; else return -1
    }
    public static void main(String args[])
    {
        int arr[][]={ {2,1,1} , {1,1,0} , {0,1,1} };
        RottenOranges ro = new RottenOranges();
        int rotting = ro.orangesRotting(arr);
        System.out.println("Minimum Number of Minutes Required "+rotting); //
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
