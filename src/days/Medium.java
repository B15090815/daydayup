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

//    https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
    public Node connect(Node root) {
        if (root == null)
            return null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = queue.poll();
            if (pre.left != null)
                queue.add(pre.left);
            if (pre.right != null)
                queue.add(pre.right);
            Node next;
            for (int i = 1; i < size; i++) {
                next = queue.poll();
                pre.next = next;
                pre = next;
                if (next.left != null)
                    queue.add(next.left);

                if (next.right != null)
                    queue.add(next.right);
            }

        }
        return root;
    }

//    https://leetcode-cn.com/problems/squares-of-a-sorted-array/
    public int[] sortedSquares(int[] A) {
        int k = -1;
        while (++k < A.length && A[k] < 0);
        int j = k;
        k--;

        int i=0;
        int[] ans = new int[A.length];
        while (j<A.length && k>=0) {
            if (Math.abs(A[k]) < A[j]) {
                ans[i] = A[k] * A[k];
                k--;
            } else {
                ans[i] = A[j] * A[j];
                j++;
            }
            i++;
        }

        while (j < A.length) {
            ans[i++] = A[j] * A[j];
            j++;

        }

        while (k>=0) {
            ans[i++] = A[k] * A[k];
            k--;
        }

        return ans;
    }

//    https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }

        if (n == 0) {
            ListNode slow = dummy;
            ListNode pre = dummy;
            while (fast != null) {
                fast = fast.next;
                pre = slow;
                slow = slow.next;
            }
            pre.next =slow.next;
        }
        return dummy.next;
    }

//    https://leetcode-cn.com/problems/find-the-duplicate-number/
    public int findDuplicate(int[] nums) {
        boolean found = false;
        int dup = 0;
        for (int i = 0; i < nums.length && !found; i++) {
            int cur = i + 1;
            if (nums[i] == cur)
                continue;
            while (nums[i] != cur) {
                int index = nums[i] - 1;
                int temp = nums[index];
                if (temp == nums[i]) {
                    dup = temp;
                    found = true;
                    break;
                }
                nums[index] = nums[i];
                nums[i] = temp;
            }
        }
        return dup;
    }

//    https://leetcode-cn.com/problems/longest-mountain-in-array/
    public int longestMountain(int[] A) {
        int n = A.length;
        if (n <= 2)
            return 0;

        int[] diff = new int[n - 1];
        for (int i = 1; i < n; i++) {
            diff[i-1] = A[i] - A[i-1];
        }

        int ans = 0;
        for (int i = 0; i < diff.length; i++) {
            if (diff[i] < 0)
                continue;

            int k = i;
            boolean flag = false;
            while (k < diff.length && diff[k] > 0) k++;
            while (k < diff.length && diff[k] < 0) {
                k++;
                flag = true;
            }
            if (flag) {
                ans = Math.max(ans, k - i + 1);
                i = k;
            }

        }
        return ans;
     }

//    https://leetcode-cn.com/problems/n-queens-ii/
    void dfs(int depth, int[] choice, int[] ans) {
        if (depth == choice.length) {
            ans[0]++;
            return;
        }

        boolean found;
        for (int i = 0; i < choice.length; i++) {
            found = true;
            for (int j = 0; j < depth; j++) {
                if (!check(j, choice[j], depth, i)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                choice[depth] = i;
                dfs(depth+1, choice, ans);
            }
        }
    }

    boolean check(int x1, int y1, int x2, int y2) {
        if(y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2))
            return false;
        return true;
    }

    public int totalNQueens(int n) {
        int[] ans = {0};
        int[] choice = new int[n];
        dfs(0, choice, ans);
        return ans[0];
    }

//    https://leetcode-cn.com/problems/reorder-list/
    public void reorderList(ListNode head) {
        ListNode p = head;
        int n = 0;
        while (p != null) {
            n++;
            p = p.next;
        }
        if (n < 2) {
            return;
        }

        ListNode q = head;
        for (int i = (n >> 1); i > 0 ; i--) {
            q = q.next;
        }

        ListNode dummy = new ListNode(-1);
        ListNode pNext, qNext;

        qNext = q.next;
        q.next = null;
        q = qNext;

        while (q != null) {
            qNext = q.next;
            q.next = dummy.next;
            dummy.next = q;
            q = qNext;
        }

        p = head;
        q = dummy.next;

        while (q!=null) {
            pNext = p.next;
            qNext = q.next;
            p.next = q;
            q.next = pNext;
            q = qNext;
            p = pNext;
        }
    }

//    https://leetcode-cn.com/problems/partition-labels/
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        int[] hash = new int[26];
        int i, j, k;
        for (i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'a';
            hash[index] = i + 1;

        }

         for(i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'a';
            if (hash[index] > 0) {
                j = hash[index];
                for (k = i + 1; k < j; k++) {
                    int p = S.charAt(k) - 'a';
                    if (hash[p] > j)
                        j = hash[p];
                }
                ans.add(j - i);
                i = j - 1;
            }
        }

        return ans;
    }

//    https://leetcode-cn.com/problems/minimum-path-sum/
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int i = 0; i < m; i++) {
            dp[0] = grid[i][0] + dp[0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
            }
        }
        return dp[dp.length - 1];
    }

//    https://leetcode-cn.com/problems/video-stitching/
    public int videoStitching(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip :
                    clips) {
                if (clip[0] <= i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }

//    https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
    public List<Integer> preorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
//        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        if (root != null) {
            stack.push(root);
        }
        TreeNode tmp;
        while (!stack.isEmpty()) {
            tmp = stack.pop();
            ans.add(tmp.val);

            if (tmp.right != null)
                stack.push(tmp.right);

            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode cur;
        cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            ans.add(cur.val);
            cur = cur.right;
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/perfect-squares/
    public int numSquares(int n) {
        int k = (int) Math.sqrt(n);
        int[] nums = new int[k+1];
        int[] dp = new int[n+1];

        for (int i = 0; i <=k; i++) {
            nums[i] = i * i;
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
            for (int j = 1; j < nums.length && nums[j] <= i; j++) {
                int x = dp[i - nums[j]] + 1;
                dp[i] = dp[i] < x ? dp[i] : x;
            }
        }

        return dp[n];
    }

//    https://leetcode-cn.com/problems/top-k-frequent-elements/
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int t = 1;
            if ( mp.containsKey(nums[i]) ) {
                t += mp.get(nums[i]);
            }
            mp.put(nums[i], t);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> que = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        for(Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            que.add(entry);
        }

        Map.Entry<Integer, Integer> tmp;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            tmp = que.poll();
            ans[i] = tmp.getKey();
        }
        return ans;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int t = 1;
            if ( mp.containsKey(nums[i]) ) {
                t += mp.get(nums[i]);
            }
            mp.put(nums[i], t);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> que = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        for (Map.Entry<Integer, Integer> entry: mp.entrySet()) {
            if (que.size() < k) {
                que.add(entry);
            } else {
                if (entry.getValue() > que.peek().getValue()) {
                    que.poll();
                    que.add(entry);
                }
            }
        }

        int[] ans = new int[k];
        Map.Entry<Integer, Integer> tmp;
        for (int i = 0; i < k; i++) {
            tmp = que.poll();
            ans[i] = tmp.getKey();
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length ==0)
            return false;

        int row = 0;
        int col = matrix[0].length - 1;
        while (col >= 0 && row < matrix.length) {
            if (target == matrix[row][col])
                return true;
            else if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

//    https://leetcode-cn.com/problems/longest-valid-parentheses/
    public int longestValidParentheses(String s) {
        int left = 0;
        int count = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ')' && left == 0)
                continue;

            if (ch == '(')
                left++;
            else {
                if (left > 0) {
                    left--;
                    count++;
                }
                if (left == 0) {
                    ans = Math.max(ans, count);
                    count = 0;
                }
            }
        }
        ans = ans == 0 ? count : ans;
        return ans * 2;
    }

//    https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
    void dfs(TreeNode root, int pre, int[] ans) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            ans[0] += 10 * pre + root.val;
            return;
        }
        pre = pre * 10 + root.val;
        dfs(root.left, pre, ans);
        dfs(root.right, pre, ans);

    }

    public int sumNumbers(TreeNode root) {
        int[] ans = {0};
        dfs(root, 0, ans);
        return ans[0];
    }

//    https://leetcode-cn.com/problems/reconstruct-itinerary/
    public List<String> findItinerary(List<List<String>> tickets) {
        Set<String> addr = new TreeSet<>();

        for (List<String> item: tickets
             ) {
            addr.addAll(item);
        }

        Iterator<String> iter = addr.iterator();
        Map<String, Integer> code = new HashMap<>();
        Map<Integer, String> code2addr = new HashMap<>();

        int index = 0;
        while (iter.hasNext()) {
            String tmp = iter.next();
            code.put(tmp, index);
            code2addr.put(index, tmp);
            index++;
        }

        int[] map = new int[index];
        int departure = code.get("JFK");
        for (List<String> item: tickets
        ) {

//            int s = code.get(item.get(0));
//            int e = code.get(item.get(1));
//            if (e == departure || map[s] != 0)
//                continue;
//
//            map[s] = e;
        }
        List<String> ans = new ArrayList<>();

        ans.add("JFK");
        while (ans.size() < addr.size()) {
            departure = map[departure];
            ans.add(code2addr.get(departure));
        }
        return ans;
    }


//    https://leetcode-cn.com/problems/binary-search/
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    public int low_bound(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                high = mid;
            } else if (nums[mid] < target){
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        if (low == 0 && nums[0] > target) {
            return -1;
        }
        return low;
    }


    public int up_bound(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }




    public int[][] insert(int[][] intervals, int[] newInterval) {
        int start = newInterval[0];
        int end = newInterval[1];
        int in_k = -1, out_k = intervals.length;
        int in_p = -1, out_p = intervals.length;


        for (int i = 0; i < intervals.length; i++) {
            if (in_k == -1 && intervals[i][0] <= start && start <=intervals[i][1]) {
                in_k = i;
            }

            if (in_p == -1 && intervals[i][0] <= end && end <=intervals[i][1]) {
                in_p = i;
            }
            if (out_k == intervals.length && start < intervals[i][0]) {
                out_k = i;
            }

            if (out_p == intervals.length && end < intervals[i][0]) {
                out_p = i;
            }
        }

        if ( in_k == -1 && out_k == intervals.length) {
            int m = intervals.length;
            int[][] ans = new int[m+1][2];
            for (int i = 0; i < intervals.length; i++) {
                ans[i][0] = intervals[i][0];
                ans[i][1] = intervals[i][1];
            }

            ans[intervals.length][0] = newInterval[0];
            ans[intervals.length][1] = newInterval[1];
            return ans;
        }

        if (in_k != -1 ) {
            if (in_p != -1) {
                int m = intervals.length - in_p + in_k;
                int[][] ans = new int[m][2];
                int x = 0;
                for (int i = 0; i < intervals.length; i++) {
                    if (i < in_k || i > in_p) {
                        ans[x][0] = intervals[i][0];
                        ans[x][1] = intervals[i][1];
                        x++;
                        continue;
                    }

                    if (i == in_k) {
                        ans[x][0] = intervals[in_k][0];
                        ans[x][1] = intervals[in_p][1];
                        x++;
                    }
                }
                return ans;
            }

            if (out_p == intervals.length) {
                int m = in_k + 1;
                int[][] ans = new int[m][2];
                for (int i = 0; i < in_k; i++) {
                    ans[i][0] = intervals[i][0];
                    ans[i][1] = intervals[i][1];
                }
                ans[in_k][0] = intervals[in_k][0];
                ans[in_k][1] = newInterval[1];
                return ans;
            }

            // out_p != -1
            int m = intervals.length + in_k - out_p + 1;
            int[][] ans = new int[m][2];
            int x = 0;
            for (int i = 0; i < intervals.length; i++) {
                if (i < in_k || i >= out_p) {
                    ans[x][0] = intervals[i][0];
                    ans[x][1] = intervals[i][1];
                    x++;
                    continue;
                }

                if (i == in_k) {
                    ans[x][0] = intervals[i][0];
                    ans[x][1] = newInterval[1];
                    x++;
                }
            }

            return ans;

        }

        // in_k == -1 out_k != intervals.length
        if (in_p != -1) {
            int m = intervals.length - (in_p - out_k );
            int[][] ans = new int [m][2];
            int x = 0;
            for (int i = 0; i < intervals.length; i++) {
                if (i < out_k || i > in_p) {
                    ans[x][0] = intervals[i][0];
                    ans[x][1] = intervals[i][1];
                    x++;
                    continue;
                }

                if (i == out_k) {
                    ans[x][0] = newInterval[0];
                    ans[x][1] = intervals[in_p][1];
                    x++;
                }
            }
            return ans;
        }

        if (out_p == intervals.length) {
            int m = out_k + 1;
            int[][] ans = new int[m][2];
            for (int i = 0; i < out_k; i++) {
                ans[i][0] = intervals[i][0];
                ans[i][1] = intervals[i][1];
            }
            ans[out_k][0] = newInterval[0];
            ans[out_k][1] = newInterval[1];
            return ans;
        }

        if (out_k == out_p) {
            int m = intervals.length;
            int[][] ans = new int[m+1][2];
            int x = 0;
            for (int i = 0; i < out_k; i++) {
                ans[x][0] = intervals[i][0];
                ans[x][1] = intervals[i][1];
                x++;
            }
            ans[x][0] = newInterval[0];
            ans[x][1] = newInterval[1];
            x++;

            for (int i = out_k; i < m; i++) {
                ans[x][0] = intervals[i][0];
                ans[x][1] = intervals[i][1];
                x++;
            }
            return ans;
        }

        int m = intervals.length + out_k - out_p + 1;
        int[][] ans = new int [m][2];
        int x = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (i < out_k || i >= out_p) {
                ans[x][0] = intervals[i][0];
                ans[x][1] = intervals[i][1];
                x++;
                continue;
            }

            if (i == out_k) {
                ans[x][0] = newInterval[0];
                ans[x][1] = newInterval[1];
                x++;
            }
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/word-ladder/
    int diffCount(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
        }
        return count;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int m = wordList.size() + 1;
        boolean[][] diff = new boolean[m][m];
        int end = -1;
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m - 1; j++) {
                if (diffCount(wordList.get(i), wordList.get(j)) == 1) {
                    diff[i][j] = true;
                    diff[j][i] = true;
                }
            }

            if (diffCount(wordList.get(i), beginWord) == 1) {
                diff[i][m-1] = true;
                diff[m-1][i] = true;
            }

            if (wordList.get(i).equals(endWord))
                end = i;
        }

        if (end == -1)
            return 0;



        int[] dp = new int[m];
        boolean[] visited = new boolean[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[m-1] = 0;

        int cur;
        int k = 0;
        for (int i = 0; i < m-1; i++) {
            cur = Integer.MAX_VALUE;
            k = -1;
            for (int j = 0; j < m; j++) {
                if (!visited[j] && cur > dp[j]) {
                    k = j;
                    cur = dp[j];
                }
            }
            if (k == -1)
                break;

            visited[k] = true;

            for (int j = 0; j < m; j++) {
                if (!visited[j] && diff[k][j]) {
                    dp[j] = Math.min(dp[j], dp[k] + 1);
                }
            }
        }

        return dp[end] == Integer.MAX_VALUE ? 0 : dp[end] + 1;
    }

//    https://leetcode-cn.com/problems/next-permutation/

    public void nextPermutation(int[] nums) {
        int e = nums.length - 1;
        while (e > 0 && nums[e] <= nums[e-1]) e--;
        int i  = 0, j =  nums.length - 1;
        if (e > 0) {
            int k = e;
            while ( k < nums.length && nums[e - 1] < nums[k]) k++;
            swap(e-1, k-1, nums);
            i = e;
        }
        while (i < j) {
            swap(i, j, nums);
            i++;
            j--;
        }
    }
//    https://leetcode-cn.com/problems/k-closest-points-to-origin/
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> que = new PriorityQueue<>(points.length);

        for (int[] point : points) {
            que.add(new Point(point[0], point[1]));
        }

        int[][] ans = new int[K][2];
        Point tmp;
        for (int i = 0; i < K; i++) {
            tmp = que.poll();
            ans[i][0] = tmp.x;
            ans[i][1] = tmp.y;
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/remove-k-digits/
    int removeOne(char[] digits, int n) {
        int i;
        for (i = 0; i < n - 1; i++) {
            if (digits[i] > digits[i+1]) {
                break;
            }
        }
        int k = i + 1;;
        if (i == 0) {
            while (k < n && digits[k] == '0') k++;
        }

        int zeros = k - (i + 1);
        n = n-1-zeros;
        for (int j = i; j < n; j++) {
            digits[j] = digits[j+1+zeros];
        }
        return n;
    }

    public String removeKdigits(String num, int k) {
        char[] digits = num.toCharArray();
        int n = digits.length;
        while (k > 0) {
            n = removeOne(digits, n);
            if (n == 0)
                break;
            k--;
        }
        if (n == 0)
            return "0";
        char[] ans = Arrays.copyOf(digits, n);
        return new String(ans);
    }




    int[] circleCheck(int cur, char ch, String ring) {
        int m = ring.length();
        int left = (cur - 1) % m;
        int right = (cur + 1) % m;
        while (right != cur && ring.charAt(right) != ch) right = (right + 1) % m;
        while (left != cur && ring.charAt(left) != ch) left = (left - 1) % m;
        return new int[]{left, right};
    }

    public int findRotateSteps(String ring, String key) {
        int m = key.length();
        int[] dp = new int[m];
        int left_start = 0, right_start = 0;
        int distance_left, distance_right;
        char ch;
        int[] direction;
        int[] direction2;
        for (int i = 0; i < m; i++) {
            ch = key.charAt(i);
            direction = circleCheck(left_start, ch, ring);
            direction2 = circleCheck(right_start, ch, ring);

            left_start = direction[0];
            right_start = direction[1];


        }
        return 0;
    }

//    https://leetcode-cn.com/problems/queue-reconstruction-by-height/
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o2[0] - o1[0];

                return o1[1] - o2[1];
            }
        });
        int m = people.length;
        List<int[]> ans = new ArrayList<>(m);
        for(int[] p: people) {
            ans.add(p[1], p);
        }
        return ans.toArray(new int[m][2]);
    }

//    https://leetcode-cn.com/problems/flip-equivalent-binary-trees/
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                boolean left, right;
                left = flipEquiv(root1.left, root2.left);
                right = flipEquiv(root1.right, root2.right);
                if (left && right)
                    return true;
                left = flipEquiv(root1.left, root2.right);
                right = flipEquiv(root1.right, root2.left);
                return left && right;
            }
        }

        return false;
    }

//    https://leetcode-cn.com/problems/increasing-subsequences/
    List<List<Integer>> ans_491;
    void DFS(int depth, int k, int last, int[] nums, int[] tmp) {
        if (depth == nums.length) {
            if (k>=2) {
                List<Integer> a = new ArrayList<>();
                for (int i = 1; i <= k; i++) {
                    a.add(tmp[i]);
                }
                ans_491.add(a);
            }
            return;
        }


        if (nums[depth] >= last) {
            tmp[k] = nums[depth];
            DFS(depth+1,k+1,nums[depth], nums,tmp);
        }

        if (nums[depth] != last)
            DFS(depth+1, k, last, nums,tmp);
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        int m = nums.length;
        int[] tmp = new int[m];
        ans_491 = new ArrayList<>();
        DFS(0,0,Integer.MIN_VALUE, nums, tmp);
        return ans_491;
    }

//    https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0)
            return 0;

        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int ans = 1;
        int pos = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][1] > pos) {
                pos = points[i][1];
                ans++;
            }
        }
        return ans;
    }

//    https://leetcode-cn.com/problems/sqrtx/submissions/
    public int mySqrt(int x) {
        if ( x < 0)
            return 0;

        long lo = 1L;
        long hi = x;
        long mid = 1L;
        long y;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            y = mid * mid;
            if (y == (long) x)
                break;
            else if (y > (long) x) {
                hi = mid - 1;
            } else { // y  < x
                lo++;
            }
        }
        y = mid * mid;
        if (y > (long) x)
            return (int)mid - 1;
        return (int)mid;
    }

    public int mySqrt2(int x) {
        if (x <= 0)
            return 0;
        double y = 1.0;
        while (Math.abs(y * y - x) > 0.01) {
            y = (y + x / y) / 2.0;
        }
        return (int)y;
    }


}

class Point implements Comparable<Point>{
    int x;
    int y;
    int distance;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = x * x + y * y;
    }

    @Override
    public int compareTo(Point point) {
        if (this.distance != point.distance)
            return this.distance - point.distance;
        if (this.x != point.x)
            return this.x - point.x;
        return this.y - point.y;
    }
}

class AddrInfo {
    String name;
    int code;
    int next;
    AddrInfo(String name, int code, int next) {
        this.name = name;
        this.code = code;
        this.next = next;
    }
}


class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};