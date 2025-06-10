/*
Algorithm (DFS):

1. From all 'O's on the border, run DFS to mark connected 'O's as temporary character 'T'.

2. After that, iterate over the board:
    - Convert all remaining 'O's (surrounded ones) to 'X'.
    - Convert 'T's back to 'O' (these are border-connected).

Time Complexity: O(m * n)
Space Complexity: O(m * n) in worst case due to recursion stack.
*/
class surroundedRegions {
    public void solve(char[][] board) {
        int m = board.length; // number of rows
        int n = board[0].length; // number of columns

        // Mark border 'O's and their connected 'O's
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0); // left border
            if (board[i][n - 1] == 'O') dfs(board, i, n - 1); // right border
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j); // top border
            if (board[m - 1][j] == 'O') dfs(board, m - 1, j); // bottom border
        }

        // Flip surrounded 'O's to 'X', revert 'T' to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X'; // surrounded region
                else if (board[i][j] == 'T') board[i][j] = 'O'; // border-connected region
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        int m = board.length; // rows
        int n = board[0].length; // cols

        // Out of bounds or not 'O'
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') return;

        board[i][j] = 'T'; // Mark as visited (temporary)

        dfs(board, i + 1, j); // down
        dfs(board, i - 1, j); // up
        dfs(board, i, j + 1); // right
        dfs(board, i, j - 1); // left
    }

    public static void main(String[] args) {
        surroundedRegions sol = new surroundedRegions();

        char[][] board = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };

        sol.solve(board);

        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " "); 
            }
            System.out.println();
        }
    }
}

