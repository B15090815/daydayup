package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
