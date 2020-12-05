package greedy;

import java.util.*;

public class Foo {
//    https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/
    public int minAddToMakeValid(String S) {
        int left = 0;
        int ans = 0;
        for (int i=0;i <S.length();i++) {
            if (S.charAt(i) == '(') {
                left++;
            } else {
                left--;
            }
            if (left < 0) {
                ans++;
                left = 0;
            }
        }
        ans += left;
        return ans;
    }

//    https://leetcode-cn.com/problems/reduce-array-size-to-the-half/
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for(int a: arr) {
            Integer c = count.get(a);
            if (c == null) {
                c = 0;
            }
            count.put(a, c+1);
        }

        Integer[] countArr = new Integer[count.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            countArr[i++] = entry.getValue();
        }
        Arrays.sort(countArr, Comparator.reverseOrder());

        int sum = 0;
        int half = arr.length >> 1;
        for (i = 0; i < countArr.length; i++) {
            sum += countArr[i];
            if (sum >= half)
                break;
        }
        return i + 1;
    }

//    https://leetcode-cn.com/problems/check-if-array-pairs-are-divisible-by-k/
    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> hash = new HashMap<>();
        int complement;
        for (int a : arr) {
            complement = a % k;
            Integer count = hash.get(complement);
            if (count == null)
                count = 0;
            hash.put(complement, count + 1);
        }
        int couple = arr.length;
        int i = arr.length / 2;
        for (Integer c: hash.keySet()) {
            complement = k - c;
        }


        return couple == 0;

    }



}
