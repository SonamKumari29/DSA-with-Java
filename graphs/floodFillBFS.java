/*
BFS Flood Fill Steps:
- Get original color at (startRow, startCol).
- If originalColor == newColor, return image.
- Use a queue to process pixels.
- For each pixel, color it and enqueue neighbors with originalColor.
- Repeat until queue is empty.
- Time: O(m*n), Space: O(m*n) for m x n image.
*/

import java.util.LinkedList;
import java.util.Queue;

class floodFillBFS {
    // Fill connected area with new color
    public int[][] fill(int[][] pic, int sr, int sc, int newCol) {
        int oldCol = pic[sr][sc]; // old color
        if (oldCol == newCol) return pic; // If same color, do nothing

        int rows = pic.length; // rows count
        int cols = pic[0].length; // cols count
        Queue<int[]> q = new LinkedList<>(); // queue for BFS
        q.offer(new int[]{sr, sc}); // start point
        pic[sr][sc] = newCol; // color start

        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // down, up, right, left directions

        // BFS loop
        while (!q.isEmpty()) {
            int[] cell = q.poll(); // get cell
            int r = cell[0], c = cell[1];

            for (int[] d : dir) { // check 4 sides
                int nr = r + d[0];// new row
                int nc = c + d[1];// new column
                // in bounds and same color
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && pic[nr][nc] == oldCol) {
                    pic[nr][nc] = newCol; // color it
                    q.offer(new int[]{nr, nc}); // add to queue
                }
            }
        }
        return pic;
    }

    public static void main(String[] args) {
        int[][] pic = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        // sr=1, sc=1, newCol=2
        floodFillBFS ff = new floodFillBFS();
        int[][] res = ff.fill(pic, 1, 1, 2);

        for (int[] row : res) {
            for (int v : row)
                System.out.print(v + " ");
            System.out.println();
        }
        // Output:
        // 2 2 2 
        // 2 2 0 
        // 2 0 1 
    }
}

