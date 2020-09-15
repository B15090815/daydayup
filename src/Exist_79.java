public class Exist_79 {
    boolean dfs(int row, int col, int index, char[][] board, boolean[][] visited, String word) {
        if (index == word.length()) {
            return true;
        }

        if (row >= board.length || row < 0 || col >= board[0].length || col < 0) {
            return false;
        }

        if (visited[row][col]) {
            return false;
        }

        boolean ans = false;
        if (board[row][col] == word.charAt(index)) {
            visited[row][col] = true;
            ans = dfs(row, col+1, index+1, board, visited, word) ||
                    dfs(row+1, col, index+1, board, visited, word) ||
                    dfs(row, col - 1, index+1, board, visited, word) ||
                    dfs(row - 1, col, index+1, board, visited, word);

            if (!ans)
                visited[row][col] = false;
        }
        return ans;
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!visited[i][j]) {
                    boolean ans = dfs(i, j,0, board, visited, word);
                    if (ans) {
                        return ans;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Exist_79 s = new Exist_79();
        char[][] board = {
                        {'A','B','C','E'},
                        {'S','F','C','S'},
                        {'A','D','E','E'}
                };
//        String word = "ABCCED";
//        String word = "SEE";
        String word = "ABCB";
        System.out.println(s.exist(board, word));
    }
}
