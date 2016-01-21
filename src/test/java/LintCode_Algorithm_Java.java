import org.junit.Test;

import java.util.*;

/**
 * Created by Marcus_Chang on 2015/12/31.
 */
public class LintCode_Algorithm_Java {

    /**
    Chapter 1 : http://www.lintcode.com/zh-cn/problem/strstr/
    */
    public int strStr(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }

        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            int j = 0;
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
            }
            // finished loop, target found
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }


    /**
     *Chapter 1 : http://www.lintcode.com/zh-cn/problem/subsets-ii/
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
        // write your code here

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int pos = 0;
        if (S == null || S.size() == 0) {
            return result;
        }

        Collections.sort(S);

        subsetsWithDupHelper(result, list, S, pos);

        return result;
    }

    public ArrayList<ArrayList<Integer>> subsetsWithDupHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, ArrayList<Integer> S, int pos) {

        result.add(new ArrayList<Integer> (list));

        for (int i = pos; i < S.size(); i++) {
            if (i != pos && S.get(i) == S.get(i-1)) {
                continue;
            }
            list.add(S.get(i));
            subsetsWithDupHelper(result, list, S, i + 1);
            list.remove(list.size() - 1);
        }

        return result;

    }


    /**
     * Chapter 1 : http://www.lintcode.com/zh-cn/problem/subsets/
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int pos = 0;
        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        subsetsHelper(result, list, nums, pos);

        return result;
    }

    public void subsetsHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int[] nums, int pos){

        result.add(new ArrayList<Integer>(list));
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            subsetsHelper(result, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/permutations-ii/
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        // write your code here

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new  ArrayList<Integer>();
        int[] visited = new int[nums.size()];


        if (nums == null || nums.size() == 0) {
            return result;
        }

        Collections.sort(nums);

        permuteUniqueHelper(result, list, visited, nums);

        return result;
    }


    public ArrayList<ArrayList<Integer>> permuteUniqueHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int[] visited, ArrayList<Integer> nums) {

        if (list.size() == nums.size()) {
            result.add(new ArrayList<Integer> (list));
            return result;
        }

        for (int i = 0; i < nums.size(); i++) {

            if (visited[i] == 1 || (i != 0 && nums.get(i) == nums.get(i - 1) && visited[i - 1] == 0)) {
                continue;
            }

            /*
            上面的判断其实并不影响最终结果，目的是为了让dfs能更快
            上面这一连串判断条件，重点在于要能理解!visited.contains(i-1)
            要理解这个，首先要明白i作为数组内序号，i是唯一的
            给出一个排好序的数组，[1,2,2]
            第一层递归            第二层递归            第三层递归
            [1]                    [1,2]                [1,2,2]
            序号:[0]                 [0,1]            [0,1,2]
            这种都是OK的，但当第二层递归i扫到的是第二个"2"，情况就不一样了
            [1]                    [1,2]                [1,2,2]
            序号:[0]                [0,2]                [0,2,1]
            所以这边判断的时候!visited.contains(0)就变成了true，不会再继续递归下去，跳出循环
            步主要就是为了去除连续重复存在的，很神奇反正 = =||
            */

            visited[i] = 1;
            list.add(nums.get(i));
            permuteUniqueHelper(result, list, visited, nums);
            list.remove(list.size() - 1);
            visited[i] = 0;

        }

        return result;
    }



    /**
     * http://www.lintcode.com/zh-cn/problem/permutations/
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        // write your code here

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new  ArrayList<Integer>();

        if (nums == null || nums.size() == 0) {
            return result;
        }

        permuteHelper(result, list, nums);

        return result;
    }


    public ArrayList<ArrayList<Integer>> permuteHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, ArrayList<Integer> nums) {

        if (list.size() == nums.size()) {
            result.add(new ArrayList<Integer> (list));
            return result;
        }

        for (int i = 0; i < nums.size(); i++) {

            if (list.contains(nums.get(i))) {
                continue;
            }

            list.add(nums.get(i));
            permuteHelper(result, list, nums);
            list.remove(list.size() - 1);

        }

        return result;
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/search-insert-position/
     * param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0, end = A.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (A[start] >= target) {
            return start;
        } else if (A[end] >= target) {
            return end;
        } else {
            return end + 1;
        }
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/search-a-2d-matrix/
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length, column = matrix[0].length;
        int start = 0, end = row * column - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int number = matrix[mid / column][mid % column];
            if (number == target) {
                return true;
            } else if (number < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (matrix[start / column][start % column] == target) {
            return true;
        } else if (matrix[end / column][end % column] == target) {
            return true;
        }

        return false;
    }



    /**
     * http://www.lintcode.com/zh-cn/problem/first-position-of-target/
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        //write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;

    }




    /**
     * http://www.lintcode.com/zh-cn/problem/search-in-a-big-sorted-array/
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
        // write your code here
        int index = 1;
        while (reader.get(index - 1) < target && reader.get(index - 1) != -1) {
            index = index * 2;
        }

        int start = 0, end = index - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) < target && reader.get(mid) != 1) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (reader.get(start) == target) {
            return start;
        }

        if (reader.get(end) == target) {
            return end;
        }

        return -1;
    }

    class ArrayReader {
        // get the number at index, return -1 if not exists.
        public int get(int index) {
            return index;
        }
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/find-minimum-in-rotated-sorted-array/
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {

        if (num == null || num.length == 0) {
            return -1;
        }

        int start = 0, end = num.length - 1;
        int target = num[num.length -1];

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (num[mid] <= target) {
                end = mid;
            }else {
                start = mid;
            }
        }

        if (start <= target) {
            return num[start];
        }else {
            return num[end];
        }

    }




    /**
     * http://www.lintcode.com/zh-cn/problem/find-peak-element/
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {

        if (A == null || A.length == 0) {
            return -1;
        }

        int start = 1, end = A.length - 2;

        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (A[mid] < A[mid - 1]) {
                end = mid;
            }else if (A[mid] < A [mid + 1]) {
                start = mid;
            }else {
                end = mid;
            }
        }

        if (A[start] < A[end]) {
            return end;
        }else {
            return start;
        }

    }



    /**
     * http://www.lintcode.com/zh-cn/problem/first-bad-version/
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {

        int start = 1, end = n;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (isBadVersion(start)) {
            return start;
        }
        return end;
    }

    public boolean isBadVersion(int k) {
        //TODO
        return true;
    }



    /**
     * http://www.lintcode.com/zh-cn/problem/search-in-rotated-sorted-array/#
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    public int search(int[] A, int target) {

        if (A == null || A.length == 0) {
            return -1;
        }

        int start = 0, end = A.length - 1;
        int mid;

        while (start + 1 < end) {

            mid = start + (end - start) / 2;

            if (A[mid] == target) {
                return mid;
            }

            if (A[start] < A[mid]) {

                if (A[start] <= target && target <= A[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {

              if (A[mid] <= target && target <= A[end]) {
                  start = mid;
              } else {
                  end = mid;
              }
            }
        }

        if (A[start] == target) {
            return start;
        } else {
            return end;
        }

    }





    /**
     * http://www.lintcode.com/zh-cn/problem/search-for-a-range/
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {

        if (A == null || A.length == 0) {
            return new int[]{-1, -1};
        }

        int start, end , mid;
        int[] bound = new int[2];

        //search for the left bound
        start = 0;
        end = A.length - 1;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                end = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (A[start] == target) {
            bound[0] = start;
        } else if (A[end] == target) {
            bound[0] = end;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }


        // search for right bound
        start = 0;
        end = A.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                start = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[end] == target) {
            bound[1] = end;
        } else if (A[start] == target) {
            bound[1] = start;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }

        return bound;


    }




    /*
    * JiuZhang Chapter 3 begins !
    * */


    public static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    /**
     * http://www.lintcode.com/zh-cn/problem/maximum-depth-of-binary-tree/
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;

    }



    /**
     * http://www.lintcode.com/zh-cn/problem/binary-tree-preorder-traversal/
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<Integer>();

        if (root == null) {
            return result;
        }

        //Divide
        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);

        //Conquer
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;

    }





    /**
     * http://www.lintcode.com/zh-cn/problem/binary-tree-maximum-path-sum/
     * http://www.lintcode.com/zh-cn/problem/binary-tree-maximum-path-sum-ii/
     * @param root the root of binary tree.
     * @return an integer
     */
    public int maxPathSum2(TreeNode root) {
        ResultType result = helper(root);
        return result.singlePath;
    }

    private class ResultType {
        // singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        // maxPath: 从树中任意到任意点的最大路径，这条路径至少包含一个点
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        if (Math.max(singlePath, 0) != 0) {
            singlePath = Math.max(singlePath, 0);
        }

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/inorder-successor-in-binary-search-tree/
     * @param root the root of binary tree.
     * @param p the given node
     * @return successor
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        TreeNode successor = null;
        while (root != null && root.val != p.val) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        if (root == null) {
            return null;
        }

        if (root.right == null) {
            return successor;
        }

        root = root.right;
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }





    private int lastVal = Integer.MIN_VALUE;
    private boolean firstNode = true;
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (!firstNode && lastVal >= root.val) {
            return false;
        }
        firstNode = false;
        lastVal = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }





    /**
     * http://www.lintcode.com/zh-cn/problem/balanced-binary-tree/
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        return maxDepth_Balanced(root) != -1;
    }

    public int maxDepth_Balanced(TreeNode root) {
        if (root == null) {
            return 0;
        }

        //Divide
        int left = maxDepth_Balanced(root.left);
        int right = maxDepth_Balanced(root.right);

        //Conquer
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;

    }


    @Test
    public void isBalanced_Test() {

        TreeNode root = new TreeNode(3);
        TreeNode left_1 = new TreeNode(9);
        TreeNode right_1 = new TreeNode(20);
        TreeNode right_1_left_2 = new TreeNode(15);
        TreeNode right_1_right_2 = new TreeNode(7);

        root.left = left_1;
        root.right = right_1;
        right_1.left = right_1_left_2;
        right_1.right = right_1_right_2;

        boolean result = isBalanced(root);
        System.out.println(result);

    }




    /**
     * http://www.lintcode.com/zh-cn/problem/lowest-common-ancestor/#
     * @param root: The root of the binary search tree.
     * @param node1 and node2: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        /*
         在root为根的二叉树中找A,B的LCA:
         如果找到了就返回这个LCA
         如果只碰到A，就返回A
         如果只碰到B，就返回B
         如果都没有，就返回null
         */

        if (root == null || root == node1 || root == node2) {
            return root;
        }

        //Divide
        TreeNode left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode right = lowestCommonAncestor(root.right, node1, node2);

        //Conquer
        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }

        if (right != null) {
            return right;
        }

        return null;

    }





    /**
     * http://www.lintcode.com/zh-cn/problem/binary-tree-level-order-traversal/
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {

        ArrayList result = new ArrayList();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
                result.add(level);
            }
        }

        return result;
    }



     /*
    * JiuZhang Chapter 4 begins !
    * */

    /**
     * http://www.lintcode.com/zh-cn/problem/unique-paths-ii/
     * @param obstacleGrid: A list of lists of integers.
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] paths = new int[n][m];

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0] != 1) {
                paths[i][0] = 1;
            } else {
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[0][i] != 1) {
                paths[0][i] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] != 1) {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                } else {
                    paths[i][j] = 0;
                }
            }
        }

        return paths[n - 1][m - 1];
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/unique-paths/
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            sum[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            sum[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
            }
        }
        return sum[m - 1][n - 1];
    }



    /**
     * http://www.lintcode.com/zh-cn/problem/climbing-stairs/
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        // write your code here
        if (n <= 1) {
            return 1;
        }
        int last = 1, lastlast = 1;
        int now = 0;
        for (int i = 2; i <= n; i++) {
            now = last + lastlast;
            lastlast = last;
            last = now;
        }
        return now;
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/minimum-path-sum/
     * @param grid: a list of lists of integers.
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int[][] sum = new int[M][N];

        sum[0][0] = grid[0][0];

        for (int i = 1; i < M; i++) {
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < N; i++) {
            sum[0][i] = sum[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }

        return sum[M - 1][N - 1];
    }





    /**
     * http://www.lintcode.com/zh-cn/problem/triangle/
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
        if (triangle == null || triangle.length == 0) {
            return -1;
        }
        if (triangle[0] == null || triangle[0].length == 0) {
            return -1;
        }

        // state: f[x][y] = minimum path value from 0,0 to x,y
        int n = triangle.length;
        int[][] f = new int[n][n];

        // initialize
        f[0][0] = triangle[0][0];
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + triangle[i][0];
            f[i][i] = f[i - 1][i - 1] + triangle[i][i];
        }

        // top down
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i - 1][j - 1]) + triangle[i][j];
            }
        }

        // answer
        int best = f[n - 1][0];
        for (int i = 1; i < n; i++) {
            best = Math.min(best, f[n - 1][i]);
        }
        return best;
    }





    /**
     * http://www.lintcode.com/zh-cn/problem/jump-game/
     * @param A: A list of integers
     * @return: The boolean answer
     */
    public boolean canJump(int[] A) {
        // wirte your code here
        boolean[] can = new boolean[A.length];
        can[0] = true;

        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (can[j] && j + A[j] >= i) {
                    can[i] = true;
                    break;
                }
            }
        }

        return can[A.length - 1];
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/jump-game-ii/
     * @param A: A list of lists of integers
     * @return: An integer
     */
    public int jump(int[] A) {
        // write your code here
        int[] steps = new int[A.length];

        steps[0] = 0;
        for (int i = 1; i < A.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    steps[i] = steps[j] + 1;
                    break;
                }
            }
        }

        return steps[A.length - 1];
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/longest-increasing-subsequence/
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        int []f = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    f[i] = f[i] > f[j] + 1 ? f[i] : f[j] + 1;
                }
            }
            if (f[i] > max) {
                max = f[i];
            }
        }
        return max;
    }


     /*
    * JiuZhang Chapter 5 begins !
    * */


    /**
     * http://www.lintcode.com/zh-cn/problem/edit-distance/
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];
        for(int i=0; i< m+1; i++){
            dp[0][i] = i;
        }
        for(int i=0; i<n+1; i++){
            dp[i][0] = i;
        }


        for(int i = 1; i<n+1; i++){
            for(int j=1; j<m+1; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[n][m];
    }





    /**
     * http://www.lintcode.com/zh-cn/problem/distinct-subsequences/
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        // write your code here
        if (S == null || T == null) {
            return 0;
        }

        int[][] nums = new int[S.length() + 1][T.length() + 1];

        for (int i = 0; i < S.length(); i++) {
            nums[i][0] = 1;
        }
        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                nums[i][j] = nums[i - 1][j];
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    nums[i][j] += nums[i - 1][j - 1];
                }
            }
        }
        return nums[S.length()][T.length()];
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/longest-common-substring/#
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        // write your code here
        int maxlen = 0;
        int xlen = A.length();
        int ylen = B.length();
        for(int i = 0; i < xlen; ++i)
        {
            for(int j = 0; j < ylen; ++j)
            {
                int len = 0;
                while (i + len < xlen && j + len < ylen &&
                        A.charAt(i + len) == B.charAt(j + len))
                    len ++;
                if(len > maxlen)
                    maxlen = len;
            }
        }
        return maxlen;
    }



    /**
     * http://www.lintcode.com/zh-cn/problem/longest-common-subsequence/
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        int n = A.length();
        int m = B.length();
        int f[][] = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if(A.charAt(i - 1) == B.charAt(j - 1))
                    f[i][j] = f[i - 1][j - 1] + 1;
            }
        }
        return f[n][m];
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/interleaving-string/
     * Determine whether s3 is formed by interleaving of s1 and s2.
     * @param s1, s2, s3: As description.
     * @return: true or false.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean [][] interleaved = new boolean[s1.length() + 1][s2.length() + 1];
        interleaved[0][0] = true;

        for (int i = 1; i <= s1.length(); i++) {
            if(s3.charAt(i - 1) == s1.charAt(i - 1) && interleaved[i - 1][0])
                interleaved[i][0] = true;
        }

        for (int j = 1; j <= s2.length(); j++) {
            if(s3.charAt(j - 1) == s2.charAt(j - 1) && interleaved[0][j - 1])
                interleaved[0][j] = true;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(((s3.charAt(i + j - 1) == s1.charAt(i - 1) && interleaved[i - 1][j]))
                        || ((s3.charAt(i + j - 1)) == s2.charAt(j - 1) && interleaved[i][j - 1]))
                    interleaved[i][j] = true;
            }
        }

        return interleaved[s1.length()][s2.length()];
    }



     /*
    * JiuZhang Chapter 6 begins !
    * */


    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if (n <= 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode preDelete = dummy;
        for (int i = 0; i < n; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }
        while (head != null) {
            head = head.next;
            preDelete = preDelete.next;
        }
        preDelete.next = preDelete.next.next;
        return dummy.next;
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/partition-list/
     * @param head: The first node of linked list.
     * @param x: an integer
     * @return: a ListNode
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here

        if (head == null) {
            return null;
        }

        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode left = leftDummy, right = rightDummy;

        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = head;
            } else {
                right.next = head;
                right = head;
            }
            head = head.next;
        }

        right.next = null;
        left.next = rightDummy.next;
        return leftDummy.next;

    }


    /**
     * http://www.lintcode.com/zh-cn/problem/remove-duplicates-from-sorted-list-ii/
     * @param head head is the head of the linked list
     * @return: ListNode head of the linked list
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if(head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                int val = head.next.val;
                while (head.next != null && head.next.val == val) {
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }

        return dummy.next;
    }



    /**
     * http://www.lintcode.com/zh-cn/problem/copy-list-with-random-pointer/
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy, newNode;
        while (head != null) {
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }
            pre.next = newNode;

            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }

            pre = newNode;
            head = head.next;
        }

        return dummy.next;
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/merge-k-sorted-lists/
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size() == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.size() - 1);
    }

    private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }

        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        return dummy.next;
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/reverse-linked-list-ii/
     * @param head head is the head of the linked list
     * @oaram m and n
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int m , int n) {
        // write your code
        if (m >= n || head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;

        for (int i = 1; i < m; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }

        ListNode premNode = head;
        ListNode mNode = head.next;
        ListNode nNode = mNode, postnNode = mNode.next;
        for (int i = m; i < n; i++) {
            if (postnNode == null) {
                return null;
            }
            ListNode temp = postnNode.next;
            postnNode.next = nNode;
            nNode = postnNode;
            postnNode = temp;
        }
        mNode.next = postnNode;
        premNode.next = nNode;

        return dummy.next;
    }



    /**
     * http://www.lintcode.com/zh-cn/problem/linked-list-cycle-ii/
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins.
     *           if there is no cycle, return null
     */
    public ListNode detectCycle(ListNode head) {
        // write your code here
        if (head == null || head.next==null) {
            return null;
        }

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }



     /*
    * JiuZhang Chapter 7 begins !
    * */

    /**
     * http://www.lintcode.com/zh-cn/problem/subarray-sum/
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here

        int len = nums.length;

        ArrayList<Integer> ans = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, -1);

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];

            if (map.containsKey(sum)) {
                ans.add(map.get(sum) + 1);
                ans.add(i);
                return ans;
            }

            map.put(sum, i);
        }

        return ans;
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/merge-sorted-array/
     * @param A: sorted integer array A which has m elements,
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        int i = m-1, j = n-1, index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index--] = A[i--];
            } else {
                A[index--] = B[j--];
            }
        }
        while (i >= 0) {
            A[index--] = A[i--];
        }
        while (j >= 0) {
            A[index--] = B[j--];
        }
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/maximum-subarray/
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }

        return max;
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/sort-colors/
     * @param a: A list of integer which is 0, 1 or 2
     * @return: nothing
     */
    public void sortColors(int[] a) {
        // write your code here
        if(a == null || a.length <= 1)
            return;

        int pl = 0;
        int pr = a.length - 1;
        int i = 0;
        while(i <= pr){
            if(a[i] == 0){
                swap(a, pl, i);
                pl++;
                i++;
            }else if(a[i] == 1){
                i++;
            }else{
                swap(a, pr, i);
                pr--;
            }
        }
    }

    private void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }






    /**
     *http://www.lintcode.com/zh-cn/problem/subarray-sum-closest/
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer> ();
        if (nums == null || nums.length == 0) {
            return arraylistToArray(res);
        }

        int len = nums.length;
        if(len == 1) {
            res.add(0);
            res.add(0);
            return arraylistToArray(res);
        }
        Pair[] sums = new Pair[len+1];
        int prev = 0;
        sums[0] = new Pair(0, 0);
        for (int i = 1; i <= len; i++) {
            sums[i] = new Pair(prev + nums[i-1], i);
            prev = sums[i].sum;
        }
        Arrays.sort(sums, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.sum - b.sum;
            }
        });
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) {

            if (ans > sums[i].sum - sums[i-1].sum) {
                ans = sums[i].sum - sums[i-1].sum;
                res.clear();
                int[] temp = new int[]{sums[i].index - 1, sums[i - 1].index - 1};
                Arrays.sort(temp);
                res.add(temp[0] + 1);
                res.add(temp[1]);
            }
        }

        return arraylistToArray(res);
    }

    class Pair {
        int sum;
        int index;
        public Pair(int s, int i) {
            sum = s;
            index = i;
        }
    }

    public int[] arraylistToArray (ArrayList<Integer> arrayList) {
        int ret[]=new int[arrayList.size()];
        for(int i=0;i<arrayList.size();i++) {
            ret[i]=((Integer)arrayList.get(i)).intValue();
        }
        return ret;
    }



    /**
     * http://www.lintcode.com/zh-cn/problem/3sum-closest/
     * @param num: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] num ,int target) {
        // write your code here
        // Note: The Solution object is instantiated only once and is reused by
        // each test case.
        if (num == null || num.length < 3) {
            return Integer.MAX_VALUE;
        }
        Arrays.sort(num);
        int closet = Integer.MAX_VALUE / 2; // otherwise it will overflow for opeartion (closet-target)'
        for (int i = 0; i < num.length - 2; i++) {
            int left = i + 1;
            int right = num.length - 1;
            while (left < right) {
                int sum = num[i] + num[left] + num[right];
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
                closet = Math.abs(sum - target) < Math.abs(closet - target) ? sum : closet;
            }
        }
        return closet;
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/two-sum/
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
        if(numbers == null || numbers.length < 2) {
            return null;
        }
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
        for(int i=0; i<numbers.length; i++){
            hs.put(numbers[i], i+1);
        }

        int[] a = new int[2];

        for(int i=0; i<numbers.length ; i++){
            if ( hs.containsKey( target - numbers[i] )){
                int index1 = i+1;
                int index2 = hs.get(target - numbers[i]);
                if (index1 == index2){
                    continue;
                }
                a[0] = index1;
                a[1] = index2;
                return a;
            }
        }
        return a;
    }


    /**
     * http://www.lintcode.com/zh-cn/problem/maximum-subarray-ii/
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        int size = nums.size();
        int[] left = new int[size];
        int[] right = new int[size];
        int sum = 0;
        int minSum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < size; i++){
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            left[i] = max;
        }
        sum = 0;
        minSum = 0;
        max = Integer.MIN_VALUE;
        for(int i = size - 1; i >= 0; i--){
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            right[i] = max;
        }
        max = Integer.MIN_VALUE;
        for(int i = 0; i < size - 1; i++){
            max = Math.max(max, left[i] + right[i + 1]);
        }
        return max;
    }



    /**
     * http://www.lintcode.com/zh-cn/problem/partition-array/
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        //write your code here
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            while (i <= j && nums[i] < k) {
                i++;
            }

            while (i <= j && nums[j] >= k) {
                j--;
            }

            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/median-of-two-sorted-arrays/
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        int len = A.length + B.length;
        if (len % 2 == 1) {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
        return (
                findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)
        ) / 2.0;
    }

    // find kth number of two sorted array
    public static int findKth(int[] A, int A_start,
                              int[] B, int B_start,
                              int k){
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }

        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }

        int A_key = A_start + k / 2 - 1 < A.length
                ? A[A_start + k / 2 - 1]
                : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length
                ? B[B_start + k / 2 - 1]
                : Integer.MAX_VALUE;

        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }



     /*
    * JiuZhang Chapter 8 begins !
    * */



















}

