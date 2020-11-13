package DFS;

import java.util.*;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}

public class Medium {
//    https://leetcode-cn.com/problems/deepest-leaves-sum/
    int height (TreeNode root) {
        if (root == null)
            return 0;
        int left = height(root.left);
        int right = height(root.right);

        return left > right ? left + 1 : right + 1;
    }
    void dfs(TreeNode root, int level, int height, int[] sum) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            if (level == height)
                sum[0] += root.val;
            return;
        }
        dfs(root.left, level+1, height,sum);
        dfs(root.right, level+1, height,sum);
    }
    public int deepestLeavesSum(TreeNode root) {
        int[] sum = {0};
        int h = height(root);
        dfs(root,1, h, sum);
        return sum[0];
    }

//    https://leetcode-cn.com/problems/pond-sizes-lcci/
    int n;
    int m;

    boolean valid(int i, int j) {
        return i>=0 && i < n && j >=0 && j < m;
    }

    int dfs(int i, int j, int[][] land ,boolean[][] visited) {
        visited[i][j] = true;
        int ans = 1;
        int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
        int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
        for (int k = 0; k < 8; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (valid(x,y) && land[x][y] == 0 && !visited[x][y]) {
                ans += dfs(x, y, land, visited);
            }
        }
        return ans;
    }

    public int[] pondSizes(int[][] land) {
        n = land.length;
        m = land[0].length;

        boolean[][] visited = new boolean[n][m];
        List<Integer> ans = new ArrayList<>();
        int tmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 0 && !visited[i][j]) {
                    tmp = dfs(i,j,land, visited);
                    ans.add(tmp);
                }
            }
        }

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        Arrays.sort(result);
        return result;
    }

//    https://leetcode-cn.com/problems/decode-string/

        int index;
        boolean isNum(char ch) {
            return '0'<= ch && ch <='9';
        }

        String dfs(StringBuilder s) {
            StringBuilder sb = new StringBuilder();
            char ch;
            int dup = 0;
            for (; index < s.length(); index++) {
                ch = s.charAt(index);
                if (ch == '[') {
                    index++;
                String tmp = dfs(s);
                for (int i = 0; i < dup; i++) {
                    sb.append(tmp);
                }
                dup = 0;
            } else if (ch == ']') {
                return sb.toString();
            } else if (isNum(ch)) {
                dup = dup * 10 + ch - '0';
            } else {
                sb.append(ch);
            }

        }
        return sb.toString();
    }
    public String decodeString(String s) {
        this.index = 0;
        StringBuilder str = new StringBuilder(s);
        return dfs(str);
    }

//    https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
    int global_length;
    boolean isValid(int i, int j, int m, int n) {
        return i >= 0 && j >= 0 && i < m && j < n;
    }

    void dfs(int i, int j, int len, boolean[][] visited, int[][] matrix, int direction) {
        this.global_length = Math.max(this.global_length, len);
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int x, y;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int k = 0; k < 4; k++) {
            x = i + dx[k];
            y = j + dy[k];
            if (isValid(x, y, m, n) && !visited[x][y]) {
                if (direction == 0) {
                    if (matrix[x][y] > matrix[i][j]) {
                        visited[x][y] = true;
                        dfs(x, y, len + 1, visited, matrix, direction);
                        visited[x][y] = false;
                    }
                } else {
                    if (matrix[x][y] < matrix[i][j]) {
                        visited[x][y] = true;
                        dfs(x, y, len + 1, visited, matrix, direction);
                        visited[x][y] = false;
                    }
                }
            }
        }
    }
    public int longestIncreasingPath_(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        this.global_length = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, 1, visited, matrix, 0);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, 1, visited, matrix, 1);
            }
        }
        return this.global_length;
    }


    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int rows, columns;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        columns = matrix[0].length;
        int[][] memo = new int[rows][columns];
        int ans = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }

    public int dfs(int[][] matrix, int row, int column, int[][] memo) {
        if (memo[row][column] != 0) {
            return memo[row][column];
        }
        ++memo[row][column];
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newColumn = column + dir[1];
            if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[row][column]) {
                memo[row][column] = Math.max(memo[row][column], dfs(matrix, newRow, newColumn, memo) + 1);
            }
        }
        return memo[row][column];
    }


    public int leastInterval1(char[] tasks, int n) {
        int[] hash = new int[26];
        for (char ch: tasks) {
            int index = (int)(ch - 'A');
            hash[index]++;
        }
        Arrays.sort(hash);
        int ans = 0;
        while (hash[25] > 0) {
            int i = 0;
            while (i<=n) {
                if(i<26 && hash[25-i] > 0) {
                    hash[25-i]--;
             }
                ans++;
                if (hash[25] == 0)
                    break;
                i++;
            }

            Arrays.sort(hash);
        }

        return ans;
    }

    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int idleSlot = (map[25] - 1) * n;
        for (int i = 24; i >=0 && map[i] > 0; i--) {
            idleSlot -= Math.min(map[i], map[25]-1);
        }
        return idleSlot > 0 ?  idleSlot + tasks.length : tasks.length;
    }

}



