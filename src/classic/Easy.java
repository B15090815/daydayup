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
            deleted = null;
        }

        DNode cur = new DNode(key, value);
        addFirst(cur);
        lru.put(key, cur);
    }
}

