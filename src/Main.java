
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


//    https://leetcode-cn.com/problems/friend-circles/
    public int findCircleNum(int[][] M) {
        int N = M.length;
        UFset f = new UFset(N);
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (i == j)
                    continue;
                if (M[i][j] == 1) {
                    f.Union(i, j);
                }
            }

        }

        int ans = 0;
        for (int i = 0; i < f.father.length; i++) {
            if (f.father[i] != -1)
                ans++;
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        Stack<TreeNode> st = new Stack<>();
        st.add(root);
        while (!st.isEmpty()) {
            TreeNode cur = st.pop();
            if (cur.right != null)
                st.push(cur.right);
        }

        return null;
    }

//    https://leetcode-cn.com/problems/group-anagrams-lcci/
    String calHash(String str) {
        int[] h = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int k = str.charAt(i) - 'a';
            h[k] = h[k] + 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < h[i]; j++) {
                sb.append((char)(i + 'a'));
            }
        }
        return sb.toString();
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        int g = 0;
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String sum = calHash(strs[i]);
            if (mp.containsKey(sum)) {
                ans.get(mp.get(sum)).add(strs[i]);
            } else {
                ArrayList<String> group = new ArrayList<>();
                group.add(strs[i]);
                ans.add(group);
                mp.put(sum, g);
                g++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Main s = new Main();
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(s.groupAnagrams(str));
//        System.out.println(s.calHash("ill"));
//        System.out.println(s.calHash("hud"));
    }
}


class UFset {
    int[] father;

    UFset(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = -1;
        }
    }

    int Find(int x) {
        int t = x;
        while (father[x] != -1) {
            x = father[x];
        }

        while (father[t] != -1) {
            int u = father[t];
            father[t] = x;
            t = u;
        }
        return x;
    }

    void Union(int p, int q) {
        int r1 = Find(p);
        int r2 = Find(q);
        if (r1 != r2)
            father[r1] = r2;
    }

    void Print() {
        for (int i = 0; i < father.length; i++) {
            System.out.print(father[i] + " ");
        }
    }
}
