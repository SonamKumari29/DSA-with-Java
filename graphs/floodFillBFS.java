import java.util.LinkedList;
import java.util.Queue;

class floodFillBFS {
    public int[][] floodFill(int[][] image, int startRow, int startCol, int newColor) {
        int originalColor = image[startRow][startCol];
        if (originalColor == newColor) return image;

        int numRows = image.length;
        int numCols = image[0].length;
        Queue<int[]> pixelsToVisit = new LinkedList<>();
        pixelsToVisit.offer(new int[]{startRow, startCol});
        image[startRow][startCol] = newColor;

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!pixelsToVisit.isEmpty()) {
            int[] currentPixel = pixelsToVisit.poll();
            int row = currentPixel[0];
            int col = currentPixel[1];

            for (int[] dir : directions) {
                int neighborRow = row + dir[0];
                int neighborCol = col + dir[1];

                if (neighborRow >= 0 && neighborRow < numRows &&
                    neighborCol >= 0 && neighborCol < numCols &&
                    image[neighborRow][neighborCol] == originalColor) {
                    image[neighborRow][neighborCol] = newColor;
                    pixelsToVisit.offer(new int[]{neighborRow, neighborCol});
                }
            }
        }

        return image;
    }

    public static void main(String[] args) {
        int[][] image =  {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };

        // startRow = 1, startCol = 1, newColor = 2       
        floodFillBFS floodFill = new floodFillBFS();
        int[][] result = floodFill.floodFill(image, 1, 1, 2);
        for (int[] row : result) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }
}

/*
BFS Flood Fill Steps:
- Get original color at (startRow, startCol).
- If originalColor == newColor, return image.
- Use a queue to process pixels.
- For each pixel, color it and enqueue neighbors with originalColor.
- Repeat until queue is empty.
- Time: O(m*n), Space: O(m*n) for m x n image.
*/
