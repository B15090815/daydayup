package greedy;

import org.junit.Test;

import java.util.Scanner;


public class FooTest {
    static Foo solution = new Foo();

    @Test
    public void minAddToMakeValid() {
        String s = ")))";
        int ans = solution.minAddToMakeValid(s);
        System.out.println(ans);
    }

    @Test
    public void minSetSize() {
        int[] arr = {1000,1000,3,7};
        int ans = solution.minSetSize(arr);
        System.out.println(ans);
    }

}