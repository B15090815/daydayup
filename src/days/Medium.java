package days;
import common.ListNode;

import java.util.*;

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
    public static void main(String[] args) {
        Medium solution = new Medium();
        int[] nums = {2,1};
        solution.sortColors(nums);
        for (int a :
                nums) {
            System.out.println(a);
        }
    }
}
