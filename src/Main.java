
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Main {

    // https://leetcode-cn.com/problems/permutations-ii/
    void dfs(int depth, String tmp, Set<String> ans, int[] nums, boolean[] visited) {
        if (depth == nums.length) {
            ans.add(tmp);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            String cur = nums[i] + ",";
            visited[i] = true;
            dfs(depth+1, tmp+cur, ans, nums, visited);
            visited[i] = false;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int[] permute = new int[nums.length];
        Set<String> ans = new HashSet<>();

        dfs(0, "", ans, nums, visited);
        List<List<Integer>> result = new ArrayList<>();
        for (String a:ans) {
            List<Integer> r = new ArrayList<>();
            for (String n:a.split(",")) {
                r.add(Integer.valueOf(n));
            }
            result.add(r);
        }
        return result;
    }

     // https://leetcode-cn.com/problems/sum-of-left-leaves/
    void dfs(TreeNode root, boolean isLeft, int[] ans) {
        if (root == null)
            return;
        if (root.left == null && root.right == null && isLeft) {
            ans[0] += root.val;
            return;
        }

        if (root.left !=null)
            dfs(root.left, true, ans);

        if (root.right != null)
            dfs(root.right, false, ans);
    }

     public int sumOfLeftLeaves(TreeNode root) {
         int[] ans = {0};
         dfs(root, true, ans);
         return ans[0];
     }

    int max_len;
    int cur;
    int pre;
    class Node {
        int val;
        int count;
        Node(int val, int count){
            this.val = val;
            this.count = count;
        }
    }
    void dfs(TreeNode root, Stack<Node> ans) {
        if (root == null) {
            return;
        }

        dfs(root.left, ans);

        if (this.pre == root.val) {
            this.cur++;
        } else {
            this.cur = 1;
        }

        if (this.cur >= this.max_len) {
            this.max_len = this.cur;
            while (!ans.isEmpty()) {
                Node tmp = ans.peek();
                if (tmp.val == root.val || tmp.count < this.max_len)
                    ans.pop();
                else
                    break;
            }
            ans.push(new Node(root.val, this.cur));
        }

        this.pre = root.val;
        dfs(root.right, ans);
    }

    public int[] findMode(TreeNode root) {
        this.max_len = 0;
        this.cur = 1;
        this.pre = -1;
        Stack<Node> st = new Stack<>();
        dfs(root, st);
        int[] ans = new int[st.size()];
        for (int i=0;!st.isEmpty(); i++) {
            Node t = st.pop();
            ans[i] = t.val;
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    TreeNode construct(int inL, int inR, int postL, int postR, int[] inorder, int[] postorder) {
        if (inL > inR)
            return null;
        int val = postorder[postR];

        int mid;
        for (mid=inL; mid<=inR; mid++) {
            if (inorder[mid] == val)
                break;
        }
        int leftLen = mid - inL;
        TreeNode root = new TreeNode(val);
        root.left = construct(inL, mid-1, postL, postL+leftLen-1, inorder, postorder);
        root.right = construct(mid+1, inR, postL+leftLen, postR-1, inorder, postorder);
        return root;
    }

//    https://leetcode-cn.com/problems/compress-string-lcci/
    public String compressString(String S) {
        if (S.length() < 2)
            return S;
        StringBuilder sb = new StringBuilder();
        char pre = S.charAt(0);
        int count = 1;
        char cur = '0';
        for (int i = 1; i < S.length(); i++) {
            cur = S.charAt(i);
            if (cur == pre) {
                count++;
            } else {
                sb.append(pre);
                sb.append(count);
                pre = cur;
                count = 1;
            }
        }
        sb.append(cur);
        sb.append(count);

        if (sb.length() >= S.length()) {
            return S;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
            Main s = new Main();
//            int[] nums = {1,1,2,4,5,5};
//            s.permuteUnique(nums);
//        TreeNode root = new TreeNode(1);
//        TreeNode a = new TreeNode(1);
//        TreeNode b = new TreeNode(2);
//        root.right = b;
//        root.left = a;
//        int[] ans = s.findMode(root);
//        for (int i = 0; i < ans.length; i++) {
//            System.out.println(ans[i]);
//
//        }

        }


    }
}
