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








}
