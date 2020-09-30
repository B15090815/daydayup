package classic;

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
        Medium solution = new Medium();

    }
}
