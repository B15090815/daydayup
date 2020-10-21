package days;


import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Easy {
    // https://leetcode-cn.com/problems/reverse-string/
    public void reverseString(char[] s) {
        int low = 0, high = s.length - 1;
        while (low < high) {
            char tmp = s[low];
            s[low] = s[high];
            s[high] = tmp;
            low++;
            high--;
        }
    }

//    https://leetcode-cn.com/problems/maximum-binary-tree/
    TreeNode construct(int low, int high, int[] nums) {
        if (low > high)
            return null;

        int k = low;
        for (int i = low + 1; i <= high; i++) {
            if (nums[i] > nums[k]) {
                k = i;
            }
        }
        TreeNode root = new TreeNode(nums[k]);
        root.left = construct(low, k-1, nums);
        root.right = construct(k+1, high, nums);
        return root;
    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(0, nums.length-1, nums);
    }

//    https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "[]";

        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        StringBuilder serial = new StringBuilder();
        serial.append('[');
        TreeNode blank = new TreeNode(0);

        while (!que.isEmpty()) {
            TreeNode f = que.poll();
            if (f == blank) {
                serial.append("null");
                serial.append(",");
                continue;
            } else {
                serial.append(f.val);
                serial.append(',');
            }

            if (f.left == null) {
                que.add(blank);
            } else {
                que.add(f.left);
            }

            if (f.right == null) {
                que.add(blank);
            } else {
                que.add(f.right);
            }
        }

        serial.deleteCharAt(serial.length()-1);
        serial.append(']');
        return serial.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() <= 2)
            return null;
        String[] nums = data.substring(1, data.length()-1).split(",");
        Queue<TreeNode> que = new LinkedList<>();
        TreeNode root;

        int k = 0;
        root = new TreeNode(Integer.parseInt(nums[k]));
        que.add(root);
        String blank = "null";

        while (!que.isEmpty()) {
            TreeNode tmp = que.poll();
            if (++k < nums.length && !blank.equals(nums[k])) {
                TreeNode l = new TreeNode(Integer.parseInt(nums[k]));
                tmp.left = l;
                que.add(l);
            }

            if (++k < nums.length && !blank.equals(nums[k])) {
                TreeNode r = new TreeNode(Integer.parseInt(nums[k]));
                tmp.right = r;
                que.add(r);
            }
        }
        return root;
    }

//    https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
    TreeNode pre530;
    int ans530;
    void dfs(TreeNode root) {
        if (root == null)
            return;
        dfs(root.left);
        if (pre530 != null) {
            ans530 = Math.min(Math.abs(root.val - pre530.val), ans530);
        }
        pre530 = root;
        dfs(root.right);
    }

    public int getMinimumDifference(TreeNode root) {
        this.pre530 = null;
        this.ans530 =Integer.MAX_VALUE;
        dfs(root);
        return ans530;
    }

//    https://leetcode-cn.com/problems/backspace-string-compare/
    String delete(char[] str) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '#') {
                for (int j = i-1; j >=0 ; j--) {
                    if (str[j] != '.') {
                        str[j] = '.';
                        break;
                    }
                }
                str[i] = '.';
            }
        }
        int k = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != '.') {
                str[k] = str[i];
                k++;
            }
        }

        return new String(str, 0, k);
    }
    public boolean backspaceCompare(String S, String T) {
        String a = delete(S.toCharArray());
        String b = delete(T.toCharArray());
        return a.equals(b);
    }

//    https://leetcode-cn.com/problems/long-pressed-name/
    public boolean isLongPressedName(String name, String typed) {
        if (name.length() == 0)
            return false;
        boolean ans = true;
        int i = 0, j = 0;
        while (i < name.length() && j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else {
                int tmp = j;
                while(i> 0 && j< typed.length() && typed.charAt(j) == name.charAt(i-1)) j++;
                if ( tmp == j) {
                    ans = false;
                    break;
                }
            }
        }

        if (i == name.length()) {
            while (j < typed.length() && typed.charAt(j) == name.charAt(i-1)) j++;
            if (j != typed.length())
                ans = false;
        } else {
            ans = false;
        }
        return ans;
    }


}

