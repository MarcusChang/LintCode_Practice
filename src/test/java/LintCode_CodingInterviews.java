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







}
