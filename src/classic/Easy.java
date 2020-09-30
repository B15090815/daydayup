package classic;


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

    public static void main(String[] args) {
        Easy solution = new Easy();
    }

}
