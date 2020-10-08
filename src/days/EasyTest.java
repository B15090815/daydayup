package days;

import org.junit.Test;

public class EasyTest {
    public static Easy solution = new Easy();

    @Test
    public void reverseString() {
        String s = "hello";
        solution.reverseString(s.toCharArray());
    }

    @Test
    public void serialize() {
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(4);
        root.left = b;
        root.right = a;
        a.left = c;
        String ans = solution.serialize(root);
        System.out.println(ans);
        solution.deserialize(ans);
    }
}