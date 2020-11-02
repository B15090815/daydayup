package days;

import java.util.HashMap;
import java.util.Map;

public class Hard {

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