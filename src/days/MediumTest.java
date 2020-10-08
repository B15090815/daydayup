package days;
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

}