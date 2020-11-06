package days;


import org.junit.Test;

public class HardTest {
    static Hard solution = new Hard();

    @Test
    public void getKthElement() {
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {4,5,6,7,8};
        for (int i = 1; i <=10 ; i++) {

            int ans = solution.getKthElement(nums1, nums2, i);
            System.out.println(ans);
        }
    }
}