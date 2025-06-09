public class floodFillDFS {
    public int[][] floodFill(int[][] picture, int startX, int startY, int newColor) {
        int oldColor = picture[startX][startY]; 
        if (oldColor == newColor) return picture; // If same color, do nothing
        fillColor(picture, startX, startY, oldColor, newColor); // Start filling
        return picture; // Return changed picture
    }

    // Helper function to fill color recursively
    private void fillColor(int[][] picture, int x, int y, int oldColor, int newColor) {
        //  out of bounds or not the old color
        if (x < 0 || y < 0 || x >= picture.length || y >= picture[0].length || picture[x][y] != oldColor)
            return;

        picture[x][y] = newColor; // Change color

        fillColor(picture, x + 1, y, oldColor, newColor); // Down
        fillColor(picture, x - 1, y, oldColor, newColor); // Up
        fillColor(picture, x, y + 1, oldColor, newColor); // Right
        fillColor(picture, x, y - 1, oldColor, newColor); // Left
    }

    public static void main(String[] args) {
        int[][] picture = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };

        floodFillDFS tool = new floodFillDFS(); // Create object
        int[][] result = tool.floodFill(picture, 1, 1, 2); // Fill from (1,1) with color 2

        for (int[] row : result) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}

/*
Dry run:
Input picture:
1 1 1
1 1 0
1 0 1

Start at (1,1), oldColor=1, newColor=2

Step 1: Change (1,1) to 2
Step 2: Fill (2,1) - not 1, skip
Step 3: Fill (0,1) - change to 2
Step 4: Fill (1,2) - not 1, skip
Step 5: Fill (1,0) - change to 2
Continue recursively...

Final picture:
2 2 2
2 2 0
2 0 1
*/
/*
Flood Fill DFS:
- Start from (startRow, startCol), store oldColor.
- If oldColor == newColor, return.
- Recursively fill 4 directions if color matches oldColor.
- Time: O(m*n), Space: O(m*n) (recursion stack).
*/
