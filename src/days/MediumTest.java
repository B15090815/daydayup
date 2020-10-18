package days;
import common.ListNode;
import org.junit.Test;

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
}