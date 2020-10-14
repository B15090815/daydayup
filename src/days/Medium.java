package days;
import common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Medium {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2;
        int c = 0, d;
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (p != null && q != null) {
            d = p.val + q.val + c;
            c = d / 10;
            tail.next = new ListNode(d % 10);
            tail = tail.next;
            p = p.next;
            q = q.next;
        }

        ListNode remain;
        if (p == null) {
            remain = q;
        } else {
            remain = p;
        }

        while (remain != null) {
            d = c + remain.val;
            c = d / 10;
            tail.next = new ListNode(d % 10);
            tail = tail.next;
            remain = remain.next;
        }
        if (c !=0 ){
            tail.next = new ListNode(c);
        }

        return dummy.next;

    }

//    https://leetcode-cn.com/problems/4sum/
    void dfs(int depth, int index, int[] temp, List<List<Integer>> ans, int[] nums, int target) {
        if (index == 4) {
            int sum = 0;
            for (int a:temp
            ) {
                sum += a;
            }
            if (sum == target) {
                List<Integer> copy = new ArrayList<>();
                for (int a :
                        temp) {
                    copy.add(a);
                }
                ans.add(copy);
            }
            return;
        }

        if (depth == nums.length || index > 4) {
            return;
        }

        for (int i = depth; i < nums.length; i++) {
            if (i != depth && nums[i] == nums[i-1])
                continue;
            temp[index] = nums[i];
            dfs(i+1, index+1, temp, ans, nums, target);
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int[] temp = new int[4];
        dfs(0, 0, temp, ans, nums, target);
        return ans;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int L = nums.length;
        for (int i = 0; i < L - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            for (int j = i + 1; j < L - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j-1])
                    continue;

                int k = j + 1, x = L - 1;
                if (nums[i] + nums[j] + nums[k] + nums[k-1] > target)
                    break;
                if (nums[i] + nums[j] + nums[x-1] + nums[x] < target)
                    continue;

                while (k < x) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[x];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[x]));
                        while (k < x && nums[k] == nums[++k]);
                        while (k < x && nums[x] == nums[--x]);
                    } else if(sum < target) {
                        while (k < x && nums[k] == nums[++k]);
                    } else {
                        while (k < x && nums[x] == nums[--x]);
                    }
                }
            }
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/sum-of-distances-in-tree/
    int bfs(int start, int N, boolean[][] map) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        int depth = 0;
        int sum = 0;
        boolean[] visited = new boolean[N];
        visited[start] = true;
        while (!que.isEmpty()) {
            int L = que.size();
            sum += depth * L;
            for (int j = 0; j < L; j++) {
                int s = que.poll();
                for (int i = 0; i < N; i++) {
                    if (map[s][i] && !visited[i]) {
                        que.add(i);
                        visited[i] = true;
                    }
                }
            }
            depth++;
        }
        return sum;
    }

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        int[] ans = new int[N];
        boolean[][] map = new boolean[N][N];
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            map[u][v] = true;
            map[v][u] = true;
        }

        for (int i = 0; i < N; i++) {
            ans[i] = bfs(i, N, map);
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/sort-colors/
    void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors(int[] nums) {
        int k = 0, j = nums.length - 1;
        int i = 0;
        while (k < j && i <= j ) {
            while (i <= j && k <= j && nums[i] != 1) {
                if (nums[i] == 0) {
                    if (i > k)
                        swap(i, k, nums);
                    else
                       i++;
                    k++;
                } else {
                    if (i < j)
                        swap(i, j, nums);
                    else
                        i++;
                    j--;
                }
            }
            i++;
        }
    }

//    https://leetcode-cn.com/problems/first-common-ancestor-lcci/
    TreeNode LCS(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = LCS(root.left, p, q);
        TreeNode right = LCS(root.right, p, q);

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        if (left != right)
            return root;

        return left;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return LCS(root, p, q);
    }

//    https://leetcode-cn.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null) {
            fast = fast.next;
            if (fast == slow)
                break;

            if (fast != null) {
                fast = fast.next;
                if (fast == slow)
                    break;
            }
            slow = slow.next;
        }

        if (fast == null)
            return null;

        ListNode entry = head;
        boolean found = false;
        while (!found) {
            ListNode p = slow;
            do{
                if (p == entry) {
                    found = true;
                    break;
                }
                p = p.next;
            }while (p != slow);


            if (!found)
                entry = entry.next;
        }

        return entry;
    }

    public ListNode detectCycle2(ListNode head) {
        if (head == null)
            return null;

        ListNode fast = head, slow = head;
        while (fast != null) {
            if (fast.next == null)
                break;

            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }

        return null;
    }

//    https://leetcode-cn.com/problems/partition-equal-subset-sum/
    // dfs can not solve this problem!
    boolean dfs(int depth, int sum, int target, int[] nums) {
        if (sum == target)
            return true;
        if (depth >= nums.length || sum > target)
            return false;
        boolean ans = dfs(depth+1, sum+nums[depth], target, nums);
        if (ans)
            return ans;
        return dfs(depth+1, sum, target, nums);
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int maxNum = -1;
        for (int a: nums) {
            sum += a;
            maxNum = maxNum < a ? a : maxNum;
        }
        int target = sum >> 1;
        if ((sum & 1) == 1 || maxNum > target) {
            return false;
        }

        int N = nums.length;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        if (nums[0] <= target)
            dp[nums[0]] = true;
        for (int i = 0; i < N; i++) {
            for (int j = target; j >= nums[i] ; j--) {
                if (dp[target])
                    return true;
                dp[j] = dp[j - nums[i]] || dp[j];
            }
        }

        return dp[target];
    }

//    https://leetcode-cn.com/problems/swap-nodes-in-pairs/
    public ListNode swapPairs(ListNode head) {
        ListNode slow, fast, cur = head;
        ListNode dummy = new ListNode(-1);
        ListNode p =dummy;
        while (cur !=null) {
            slow = cur;
            fast = cur.next;
            if (fast != null) {
                p.next = fast;
                p = p.next;
                cur = fast.next;
            } else {
                cur = null;
            }
            p.next = slow;
            p = p.next;
        }
        p.next = null;
        return dummy.next;
    }

//    https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        ListNode fake = new ListNode(-1);
        ListNode p = head, cur = dummy, tail, next;
        int count;
        while (p != null) {
            count = k;
            tail = p;
            while (count > 0 && p != null) {
                next = p.next;
                p.next = fake.next;
                fake.next = p;
                p = next;
                count--;
            }

            if (count == 0) {
                cur.next = fake.next;
                cur = tail;
                fake.next = null;
            } else {
                for(p = fake.next; p!=null; p = next) {
                    next = p.next;
                    p.next = cur.next;
                    cur.next = p;
                }
                break;
            }
        }
        return dummy.next;
    }

//    https://leetcode-cn.com/problems/find-common-characters/
    public List<String> commonChars(String[] A) {
        int[][] map = new int[A.length][26];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length(); j++) {
                int k = (int)(A[i].charAt(j) - 'a');
                map[i][k]++;
            }
        }

        int target = 0;
        List<String> ans = new ArrayList<>();
        for (int j = 0; j < 26; j++) {
            target = Integer.MAX_VALUE;
            for (int i = 0; i < A.length; i++) {
                target = Math.min(target, map[i][j]);

            }

            if (target !=0) {
                for (int i = 0; i < target; i++) {
                    char ch = (char)('a' + j);
                    ans.add(String.valueOf(ch));
                }
            }
        }
        return ans;
    }

}


