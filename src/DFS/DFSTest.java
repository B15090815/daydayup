package DFS;

import org.junit.Test;


public class DFSTest {
    public static Medium solution = new Medium();
    @Test
    public void pondSizes() {
        int[][] land = {{0,2,1,0},{0,1,0,1},{1,1,0,1},{0,1,0,1}};
        int[] ans = solution.pondSizes(land);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }

    @Test
    public void decodeString() {
        String s = "3[a2[c]]";
        String ans = solution.decodeString(s);
        System.out.println(ans);
    }
}