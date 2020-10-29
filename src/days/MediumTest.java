package days;
import common.ListNode;
import org.junit.Test;

import java.util.List;

public class MediumTest {
    static Medium solution = new Medium();

    @Test
    public void sortColors() {
        int[] nums = {2,1};
        solution.sortColors(nums);
        for (int a :
                nums) {
            System.out.println(a);
        }
    }

    @Test
    public void canPartition() {
        int[] nums = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        boolean ans = solution.canPartition(nums);
        System.out.println(ans);
    }

    @Test
    public void reverseKGroup() {
        int[] nums = {1,2,3,4,5};
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for (int i = 0; i < nums.length; i++) {
            p.next = new ListNode(nums[i]);
            p = p.next;
        }

        solution.reverseKGroup(dummy.next, 8);
    }

    @Test
    public void findDuplicate() {
        int[] nums = {1,2,3,3,4,5,6};
        int a = solution.findDuplicate(nums);
        System.out.println(a);
    }

    @Test
    public void longestMountain() {
        int[] nums = {0,2,2};
        System.out.println(solution.longestMountain(nums));
    }

    @Test
    public void totalNQueens() {
        int n = 4;
        int ans = solution.totalNQueens(n);
        System.out.println(ans);
    }

    @Test
    public void reorderList() {
        int[] nums = {1,2,3,4,5,6,7,8};

        ListNode head = constructList(nums);
        printList(head);
        solution.reorderList(head);
        printList(head);
    }

    public ListNode constructList(int[] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for (int i = 0; i < nums.length; i++) {
            ListNode tmp = new ListNode(nums[i]);
            p.next = tmp;
            p = p.next;
        }
        return dummy.next;
    }

    public void printList(ListNode head) {
        for (ListNode p = head; p!=null;p=p.next) {
            System.out.print(p.val + "  ");
        }
        System.out.println();
    }

    @Test
    public void partitionLabels() {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> ans = solution.partitionLabels(s);
        for (Integer a: ans
             ) {
            System.out.print(a + "  ");
        }
    }


    @Test
    public void videoStitching() {
        int[][] nums = {{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
        System.out.println(solution.videoStitching(nums, 9));
    }

    @Test
    public void numSquares() {
        System.out.println(solution.numSquares(17));
    }

    @Test
    public void topKFrequent() {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        int[] ans = solution.topKFrequent(nums, k);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
    @Test public void longestValidParentheses() {
        String s = ")()())";
        int ans = solution.longestValidParentheses(s);
        System.out.println(ans);
    }
}