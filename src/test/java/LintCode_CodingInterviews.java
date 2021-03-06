import org.junit.Test;
import java.util.*;

/**
 * Created by Marcus_Chang on 2016/5/9.
 */
public class LintCode_CodingInterviews {


    /*
    * CI Chapter 2 begins !
    * */

    /**
     * http://www.lintcode.com/zh-cn/problem/fibonacci/
     * @param n: an integer
     * @return an integer f(n)
     */
    public int fibonacci(int n) {
        // write your code here
        int a = 0;
        int b = 1;
        for (int i = 0; i < n - 1; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }






    /**
     * http://www.lintcode.com/zh-cn/problem/singleton/
     * @return: The same instance of this class every time
     */
    public static LintCode_CodingInterviews instance = null;
    public static LintCode_CodingInterviews getInstance() {
        // write your code here
        if (instance == null) {
            instance = new LintCode_CodingInterviews();
        }
        return instance;
    }







    /**
     * http://www.lintcode.com/zh-cn/problem/count-1-in-binary/
     * @param num: an integer
     * @return: an integer, the number of ones in num
     */
    public int countOnes(int num) {
        // write your code here
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }





    /**
     * http://www.lintcode.com/zh-cn/problem/space-replacement/
     * @param string: An array of Char
     * @param length: The true length of the string
     * @return: The true length of new string
     */
    public int replaceBlank(char[] string, int length) {
        if(0==length) return 0;
        int num = 0;
        for(int i=0;i<length;i++){
            if(string[i] == ' ') num++;
        }

        int newLen = length + num*2;
        string[newLen] = 0;
        int j = 1;
        for(int i=length-1;i>=0;i--){
            if(string[i] != ' '){
                string[newLen - j] = string[i];
                j++;
            }
            else{
                string[newLen - j] = '0';
                j++;
                string[newLen - j] = '2';
                j++;
                string[newLen - j] = '%';
                j++;
            }
        }
        return newLen;
    }





    /**
     * http://www.jiuzhang.com/solutions/partition-array-by-odd-and-even/
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        // write your code here;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            while (start < end && nums[start] % 2 == 1) {
                start++;
            }
            while (start < end && nums[end] % 2 == 0) {
                end--;
            }
            if (start < end) {
                int temp = nums[start]; nums[start] = nums[end]; nums[end] = temp;
                start++;
                end--;
            }
        }
    }






    /**
     * http://www.lintcode.com/zh-cn/problem/subtree/
     * @param T1, T2: The roots of binary tree.
     * @return: True if T2 is a subtree of T1, or false.
     */
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        // write your code here
        if (T2 == null) {
            return true;
        }
        if (T1 == null) {
            return false;
        }

        if (isEqual(T1, T2)) {
            return true;
        }
        if (isSubtree(T1.left, T2) || isSubtree(T1.right, T2)) {
            return true;
        }
        return false;
    }

    private boolean isEqual(TreeNode T1, TreeNode T2) {
        if (T1 == null || T2 == null) {
            return T1 == T2;
        }
        if (T1.val != T2.val) {
            return false;
        }
        return isEqual(T1.left, T2.left) && isEqual(T1.right, T2.right);
    }





    /*
    * CI Chapter 3 begins !
    * */

    /**
     * http://www.lintcode.com/zh-cn/problem/print-numbers-by-recursion/
     * @param n: An integer.
     * return : An array storing 1 to the largest number with n digits.
     */
    public List<Integer> numbersByRecursion(int n) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer>();
        num(n, 0, res);
        return res;
    }

    public void num(int n, int ans,ArrayList<Integer> res){

        if(n==0){
            if(ans>0){
                res.add(ans);
            }
            return;
        }

        int i;
        for(i=0; i<=9; i++){
            num(n-1, ans*10+i, res);
        }
    }




    /*
    * CI Chapter 4 begins !
    * */

    /**
     * http://www.lintcode.com/zh-cn/problem/string-permutation/
     * @param A a string
     * @param B a string
     * @return a boolean
     */
    public boolean stringPermutation(String A, String B) {
        // Write your code here
        int[] cnt = new int[1000];
        for (int i = 0; i < A.length(); ++i)
            cnt[(int)A.charAt(i)] += 1;
        for (int i = 0; i < B.length(); ++i)
            cnt[(int)B.charAt(i)] -= 1;
        for (int i = 0; i < 1000; ++i)
            if (cnt[i] != 0)
                return false;
        return true;
    }






    /**
     * http://www.lintcode.com/zh-cn/problem/spiral-matrix/
     * @param matrix a matrix of m x n elements
     * @return an integer list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // Write your code here
        List<Integer> rst = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0)
            return rst;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;
        while(count * 2 < rows && count * 2 < cols){
            for(int i = count; i < cols-count; i++)
                rst.add(matrix[count][i]);


            for(int i = count+1; i< rows-count; i++)
                rst.add(matrix[i][cols-count-1]);

            if(rows - 2 * count == 1 || cols - 2 * count == 1)  // if only one row /col remains
                break;

            for(int i = cols-count-2; i>=count; i--)
                rst.add(matrix[rows-count-1][i]);

            for(int i = rows-count-2; i>= count+1; i--)
                rst.add(matrix[i][count]);

            count++;
        }
        return rst;
    }







/*
    * CI Chapter 5 begins !
    * */

    /**
     * http://www.lintcode.com/zh-cn/problem/reverse-pairs/
     * @param A an array
     * @return total of reverse pairs
     */
    public long reversePairs(int[] A) {
        // Write your code here
        return mergeSort(A, 0, A.length - 1);
    }

    private int mergeSort(int[] A, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = (start + end) / 2;
        int sum = 0;
        sum += mergeSort(A, start, mid);
        sum += mergeSort(A, mid+1, end);
        sum += merge(A, start, mid, end);
        return sum;
    }

    private int merge(int[] A, int start, int mid, int end) {
        int[] temp = new int[A.length];
        int leftIndex = start;
        int rightIndex = mid + 1;
        int index = start;
        int sum = 0;

        while (leftIndex <= mid && rightIndex <= end) {
            if (A[leftIndex] <= A[rightIndex]) {
                temp[index++] = A[leftIndex++];
            } else {
                temp[index++] = A[rightIndex++];
                sum += mid - leftIndex + 1;
            }
        }
        while (leftIndex <= mid) {
            temp[index++] = A[leftIndex++];
        }
        while (rightIndex <= end) {
            temp[index++] = A[rightIndex++];
        }

        for (int i = start; i <= end; i++) {
            A[i] = temp[i];
        }

        return sum;
    }





    /**
     * http://www.lintcode.com/zh-cn/problem/spiral-matrix-ii/
     * @param n an integer
     * @return a square matrix
     */
    public int[][] generateMatrix(int n) {
        // Write your code here
        if (n < 0) {
            return null;
        }

        int[][] result = new int[n][n];

        int xStart = 0;
        int yStart = 0;
        int num = 1;

        while (n > 0) {
            if (n == 1) {
                result[yStart][xStart] = num++;
                break;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart][xStart + i] = num++;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart + i][xStart + n - 1] = num++;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart + n - 1][xStart + n - 1 - i] = num++;
            }

            for (int i = 0; i < n - 1; i++) {
                result[yStart + n - 1 - i][xStart] = num++;
            }

            xStart++;
            yStart++;
            n = n - 2;
        }
        return result;
    }





    /**
     * http://www.lintcode.com/zh-cn/problem/intersection-of-two-linked-lists/
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Write your code here
        if (headA == null || headB == null) {
            return null;
        }

        // get the tail of list A.
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        node.next = headB;
        ListNode result = listCycleII(headA);
        node.next = null;
        return result;
    }

    private ListNode listCycleII(ListNode head) {
        ListNode slow = head, fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        slow = head;
        fast = fast.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }






    /**
     * http://www.lintcode.com/zh-cn/problem/kth-largest-element/
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (k <= 0) {
            return 0;
        }
        return Khelper(nums, 0, nums.length - 1, nums.length - k + 1);

    }
    public int Khelper(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        int position = partition(nums, l, r);
        if (position + 1 == k) {
            return nums[position];
        } else if (position + 1 < k) {
            return Khelper(nums, position + 1, r, k);
        }  else {
            return Khelper(nums, l, position - 1, k);
        }
    }
    public int partition(int[] nums, int l, int r) {
        // 初始化左右指针和pivot
        int left = l, right = r;
        int pivot = nums[left];

        // 进行partition
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }

        // 返还pivot点到数组里面
        nums[left] = pivot;
        return left;
    }














}
