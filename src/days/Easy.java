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
}

