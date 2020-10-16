package dynamic;

import java.util.HashMap;

public class DoublePointer {
//    https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1)
            return 0;

        HashMap<Character, Integer> mp = new HashMap<>();
        int i=0,j =1;
        mp.put(s.charAt(i), 1);
        int ans = -1;
        while (j<s.length()) {
            char ch = s.charAt(j);
            if (!mp.containsKey(ch) || mp.get(ch) == 0) {
                mp.put(s.charAt(j), 1);
                j++;
            } else {
                ans = Math.max(ans, j - i);
                char tail = s.charAt(i);
                mp.put(tail, 0);
                i++;
                if (i == j) {
                    mp.put(s.charAt(j), 1);
                    j++;
                }
            }
        }
        ans = Math.max(ans, j - i);
        return ans;
    }
}
