package classic;


import java.util.*;

public class Easy {
//    https://leetcode-cn.com/problems/string-to-url-lcci/
    public String replaceSpaces(String S, int length) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char ch = S.charAt(i);
            if (ch == ' ') {
                ans.append("%20");
            } else {
                ans.append(ch);
            }
        }
        return ans.toString();
    }


//    https://leetcode-cn.com/problems/move-zeroes/
    public void moveZeroes(int[] nums) {
        int zeros = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
                continue;
            }
            nums[i - zeros] = nums[i];
        }

        for (int i = nums.length - 1; i > nums.length - zeros ; i--) {
            nums[i] = 0;
        }
    }

//    https://leetcode-cn.com/problems/powx-n/
    public double myPow(double x, int n) {
        if (Math.abs(x) < 1e-8)
            return 0.0;

        boolean neg = n < 0;
        long N = n;
        if (neg)
            N = -N;

        boolean sign = x < 0 && (N & 1) == 1;

        double ans = 1.0;
        double b = x > 0 ? x : -x;

        while (N > 0) {
            if ((N & 1) == 1)
                ans *= b;
            b *= b;
            N = N >> 1;
        }

        if (neg)
            ans = 1.0 / ans;

        if (sign)
            return -ans;

        return ans;

    }

//    https://leetcode-cn.com/problems/h-index-ii/
    public int hIndex(int[] citations) {
        return -1;
    }


//    https://leetcode-cn.com/problems/reverse-bits/
    public int reverseBits(int n) {
        int hi = 1 << 31;
        int lo = 1;
        int m, d;
        for (int i = 0; i < 16; i++) {
            m = n & hi;
            d = n & lo;
            if ((m == 0 && d != 0) || (m !=0 && d == 0)) {
                n = n ^ hi ^ lo;
            }
            hi = hi >>> 1;
            lo = lo << 1;
        }

        return n;

    }

}

class DNode {
    int value;
    int key;
    DNode next;
    DNode pre;
    DNode(int k, int v) {
        value = v;
        key = k;
        next = null;
        pre = null;
    }


}

class LRUCache {
    Map<Integer, DNode> lru;
    DNode head;
    int capacity;
    public LRUCache(int capacity) {
        lru = new HashMap<>(capacity);
        head = new DNode(-1, -1);
        head.next = head;
        head.pre = head;
        this.capacity = capacity;
    }

    void addFirst(DNode cur) {
        cur.next = head.next;
        cur.pre = head;
        head.next.pre = cur;
        head.next = cur;
    }

    void remove(DNode cur) {
        cur.next.pre = cur.pre;
        cur.pre.next = cur.next;
    }

    public int get(int key) {
        if (lru.containsKey(key)) {
            //update
            DNode cur = lru.get(key);
            remove(cur);
            addFirst(cur);
            return cur.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (lru.containsKey(key)) {
            DNode cur = lru.get(key);
            remove(cur);
            addFirst(cur);
            cur.value = value;
            return;
        }

        if (lru.size() >= capacity) {
            DNode deleted = head.pre;
            remove(deleted);
            lru.remove(deleted.key);
        }

        DNode cur = new DNode(key, value);
        addFirst(cur);
        lru.put(key, cur);
    }
}

