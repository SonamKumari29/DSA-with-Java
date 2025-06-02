public class floodFillDFS {
    public int[][] floodFill(int[][] image, int startRow, int startCol, int newColor) {
        int oldColor = image[startRow][startCol];
        if (oldColor == newColor) return image;
        fill(image, startRow, startCol, oldColor, newColor);
        return image;
    }

    private void fill(int[][] image, int row, int col, int oldColor, int newColor) {
        if (row < 0 || col < 0 || row >= image.length || col >= image[0].length || image[row][col] != oldColor)
            return;

        image[row][col] = newColor;
        fill(image, row + 1, col, oldColor, newColor);
        fill(image, row - 1, col, oldColor, newColor);
        fill(image, row, col + 1, oldColor, newColor);
        fill(image, row, col - 1, oldColor, newColor);
    }

    public static void main(String[] args) {
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };

        final floodFillDFS obj = new floodFillDFS();
        int[][] result = obj.floodFill(image, 1, 1, 2);
        for (int[] row : result) {
            for (int pixel : row)
                System.out.print(pixel + " ");
            System.out.println();
        }
    }
}
