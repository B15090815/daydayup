package classic;

import org.junit.Test;


public class EasyTest {
    static Easy solution = new Easy();

    @Test
    public void myPow() {
        double x = -2.0;
        int p = -2147483648;
        double ans = solution.myPow(x, p);
        System.out.println(ans);
    }

    @Test
    public void hIndex() {
        int[] h = {0,5,15,20,25,30,35};
        int a = solution.hIndex(h);
        System.out.println(a);
    }

    @Test
    public void reverseBits() {
        int n = 0x0000ffff;
        System.out.println(solution.reverseBits(n));
    }
}