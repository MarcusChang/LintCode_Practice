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


















}

