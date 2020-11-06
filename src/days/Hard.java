package days;

import java.util.HashMap;
import java.util.Map;

public class Hard {

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums1.length;

        int index1 = 0;
        int index2 = 0;

        while (true) {
            if (index1 == len1) {
                return nums2[index2 + k - 1];
            }

            if(index2 == len2) {
                return nums1[index1 + k - 1];
            }

            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            int half = k / 2;
            int n1 = Math.min(index1 + half, len1) - 1;
            int n2 = Math.min(index2 + half, len2) - 1;

            if (nums1[n1]<= nums2[n2]) {
                k -= (n1 - index1 + 1);
                index1 = n1 + 1;
            } else {
                k -= (n2 - index2 + 1);
                index2 = n2 + 1;
            }
        }
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        double ans = 0.0;
        if (m > 0 && n > 0) {
        }

        return ans;
    }


}
class RandomizedCollection {
    Map<Integer, Integer> place;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        place = new HashMap<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean ret = true;
        if (place.containsKey(val)) {
            ret = false;
            int count = place.get(val) + 1;
            place.put(val, count);
        } else {
            place.put(val, 1);
        }
        return ret;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!place.containsKey(val))
            return false;
        int count = place.get(val) - 1;
        if (count == 0) {
            place.remove(val);
        } else {
            place.put(val, count);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return 0;
    }
}