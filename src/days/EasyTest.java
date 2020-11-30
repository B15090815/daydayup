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

    @Test
    public void backspaceCompare() {
        String a = "ab#c";
        String b = "ad#c";
        boolean ans = solution.backspaceCompare(a, b);
        System.out.println(ans);
    }

    @Test
    public void isLongPressedName() {
        String a = "kikcxmvzi";
        String b = "kiikcxxmmvvzz";
        System.out.println(solution.isLongPressedName(a, b));

    }

    @Test
    public void longestMountain() {
        int[] nums = {875,884,239,731,723,685};
        int ans = solution.longestMountain(nums);
        System.out.println(ans);
    }

    @Test
    public void uniqueOccurrences() {
        int[] nums = {1,2,2,1,1,3};
        boolean ans = solution.uniqueOccurrences(nums);
        System.out.println(ans);
    }

    @Test
    public void reorganizeString() {
        String str = "abcdaaasssoiidl";
        System.out.println(solution.reorganizeString(str));
    }

}