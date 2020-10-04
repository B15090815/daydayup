package classic;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Medium {
//    https://leetcode-cn.com/problems/one-away-lcci/
    public boolean oneEditAway(String first, String second) {
        int diff = first.length() - second.length();
        boolean ans = true;
        if (diff == 0) {
            // change one word or do nothing
            int count = 0;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i))
                    count++;

                ans = count < 2;
            }
        } else if (diff == -1 || diff == 1) {
            // insert or delete one word
            if (first.length() == 0 || second.length() == 0)
                ans = true;
            else {
                int count = 0;
                int i = 0, j = 0;
                while (true) {
                    if (first.charAt(i) == second.charAt(j)) {
                        i++;
                        j++;
                    } else {
                        count++;
                        if (diff == -1) {
                            j++;
                        }

                        if (diff == 1) {
                            i++;
                        }
                    }
                    if (i == first.length() || j == second.length() || count > 1)
                        break;
                }
                ans = count < 2;
            }

        } else  {
            ans = false;
        }

        return ans;
    }

    public static void main(String[] args) {
//        Medium solution = new Medium();
        MedianFinder a = new MedianFinder();
        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            int n = s.nextInt();
            a.addNum(n);
            System.out.println(a.findMedian());
        }
//        int[] nums = {1,2,3,4,5,6,7,8};
//        for (int num:nums) {
//            a.addNum(num);
//        }

    }
}

// https://leetcode-cn.com/problems/continuous-median-lcci/
class MedianFinder {
    static Comparator<Integer> maxQcmp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    };

    int count;
    PriorityQueue<Integer> minQue;
    PriorityQueue<Integer> maxQue;


    /** initialize your data structure here. */
    public MedianFinder() {
        count = 0;
        minQue = new PriorityQueue<>();
        maxQue = new PriorityQueue<>(maxQcmp);
    }

    void check() {
        if (!minQue.isEmpty() && !maxQue.isEmpty()) {
            if (minQue.peek() < maxQue.peek()) {
                int a = minQue.poll();
                int b = maxQue.poll();
                minQue.add(b);
                maxQue.add(a);
            }
        }
    }
    public void addNum(int num) {
        if ((count & 1) == 0) {
            // even
            maxQue.add(num);
        } else {
            // odd
            minQue.add(num);
        }
        check();
        count++;
    }

    public double findMedian() {
        double ans = 0.0;
        if ((count & 1) == 0) {
            ans = (minQue.peek() + maxQue.peek()) / 2.0;
        } else {
            ans = (double) maxQue.peek();
        }
        return ans;
    }
}
