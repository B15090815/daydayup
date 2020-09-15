public class SolveSudoku_37 {

    boolean check(char candidate, int row, int col, char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == candidate || board[i][col] == candidate) {
                return false;
            }
        }

        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r+i][c+j] == candidate){
                    return false;
                }
            }
        }
        return true;
    }

    boolean dfs(int row, int col, char[][] board) {
        if (row == 9)
            return true;

        boolean found = false;
        int i = row, j = col;

        while (i < 9) {
            while (j < 9) {
                if (board[i][j] == '.') {
                    found = true;
                    break;
                }
                j++;
            }

            if (found)
                break;
            i++;
            j = 0;
        }

        if (i==9)
            return true;


        int r = j < 8 ? i : i + 1;
        int c = (j + 1) % 9;


        boolean flag = false;
        for (int k = 1; k < 10; k++) {
            char candidate = (char) (k + '0');
            if (check(candidate, i, j, board)) {
                board[i][j] = candidate;
                flag = dfs(r, c, board);
                if (flag) {
                    break;
                }
            }
        }
        if (!flag) {
            board[i][j] = '.';
        }

        return flag;
    }


    public void solveSudoku(char[][] board) {
        dfs(0, 0, board);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        SolveSudoku_37 s = new SolveSudoku_37();
        s.solveSudoku(board);
    }
}
