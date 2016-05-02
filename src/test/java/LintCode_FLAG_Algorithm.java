import org.junit.Test;
import java.util.*;

/**
 * Created by Marcus_Chang on 2016/4/16.
 */
public class LintCode_FLAG_Algorithm {


    /*
    * FLAG Chapter 1 begins !
    * */

    /**
     * http://www.jiuzhang.com/solutions/two-strings-are-anagrams/
     * @param s: The first string
     * @param t: The second string
     * @return true or false
     */
    public boolean anagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[(int) s.charAt(i)]++;
        }
        for (int i = 0; i < t.length(); i++) {
            count[(int) t.charAt(i)]--;
            if (count[(int) t.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/compare-strings/
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        int[] counts = new int[26];
        for (int i = 0; i < 26; i++) {
            counts[i] = 0;
        }
        for (int i = 0; i < A.length(); i++) {
            counts[A.charAt(i) - 'A'] ++;
        }
        for (int i = 0; i < B.length(); i++) {
            counts[B.charAt(i) - 'A'] --;
            if (counts[B.charAt(i) - 'A'] < 0) {
                return false;
            }
        }
        return true;
    }





    /**
     * http://www.lintcode.com/zh-cn/problem/anagrams/
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        // write your code here
        ArrayList<String> result = new ArrayList<String>();
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }

            int hash = getHash(count);
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<String>());
            }

            map.get(hash).add(str);
        }

        for (ArrayList<String> tmp : map.values()) {
            if (tmp.size() > 1) {
                result.addAll(tmp);
            }
        }

        return result;
    }

    private int getHash(int[] count) {
        int hash = 0;
        int a = 378551;
        int b = 63689;
        for (int num : count) {
            hash = hash * a + num;
            a = a * b;
        }
        return hash;
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/longest-common-prefix/
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        // write your code here
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for(int i = 1; i < strs.length; i++) {
            int j = 0;
            while( j < strs[i].length() && j < prefix.length() && strs[i].charAt(j) == prefix.charAt(j)) {
                j++;
            }
            if( j == 0) {
                return "";
            }
            prefix = prefix.substring(0, j);
        }
        return prefix;
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/string-to-integer-ii/
     * @param str: A string
     * @return An integer
     */
    public int atoi(String str) {
        // write your code here
        if (str == null || str.length() < 1)
            return 0;

        // trim white spaces
        str = str.trim();

        char flag = '+';

        // check negative or positive
        int i = 0;
        if (str.charAt(0) == '-') {
            flag = '-';
            i++;
        } else if (str.charAt(0) == '+') {
            i++;
        }
        // use double to store result
        double result = 0;

        // calculate value
        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }

        if (flag == '-')
            result = -result;

        // handle max and min
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int) result;
    }




    /*
    * FLAG Chapter 2 begins !
    * */

    /**
     * http://www.lintcode.com/zh-cn/problem/remove-element/
     *@param A: A list of integers
     *@param elem: An integer
     *@return: The new length after remove
     */
    public int removeElement(int[] A, int elem) {
        // write your code here
        int i = 0;
        int pointer = A.length - 1;
        while(i <= pointer){
            if(A[i] == elem){
                A[i] = A[pointer];
                pointer--;
            } else {
                i++;
            }
        }
        return pointer + 1;
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/remove-duplicates-from-sorted-array/
     * @param nums: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here

        int len = nums.length;
        int size = 0;

        if (nums == null || len == 0) {
            return 0;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != nums[size]) {
                nums[++size] = nums[i];
            }
        }
        return size + 1;
    }






    /**
     * http://www.lintcode.com/zh-cn/problem/first-missing-positive/
     * @param A: an array of integers
     * @return: an integer
     */
    public int firstMissingPositive(int[] A) {
        // write your code here
        if (A == null) {
            return 1;
        }

        for (int i = 0; i < A.length; i++) {
            while (A[i] > 0 && A[i] <= A.length && A[i] != (i+1)) {
                int tmp = A[A[i]-1];
                if (tmp == A[i]) {
                    break;
                }
                A[A[i]-1] = A[i];
                A[i] = tmp;
            }
        }

        for (int i = 0; i < A.length; i ++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }

        return A.length + 1;
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/remove-duplicates-from-sorted-array-ii/
     * @param nums: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates2(int[] nums) {
        // write your code here
        if(nums == null)
            return 0;
        int cur = 0;
        int i ,j;
        for(i = 0; i < nums.length;){
            int now = nums[i];
            for( j = i; j < nums.length; j++){
                if(nums[j] != now)
                    break;
                if(j-i < 2)
                    nums[cur++] = now;
            }
            i = j;
        }
        return cur;
    }






    /**
     * http://www.lintcode.com/zh-cn/problem/product-of-array-exclude-itself/
     * @param A: Given an integers array A
     * @return: A Long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
        // write your code
        int len = A.size();
        ArrayList<Long> B = new  ArrayList<Long>();
        if (len == 1){
            B.add(new Long(1L));
            return B;
        }

        long[] f = new long[len];

        long tmp = 1;
        long now = 1;
        f[len-1] = A.get(len-1);
        //System.out.println(f[len-1]);
        int i ;
        for ( i = len-2; i >= 0; --i)
        {
            f[i] = A.get(i);
            //System.out.println(f[i+1]);
            f[i] = f[i] * f[i+1];
            //System.out.println(f[i]);
        }

        for ( i = 0; i < len; ++i) {

            now = tmp;
            if(i+1<len)
                B.add( now * f[i+1] ); else
                B.add( now );
            now = A.get(i);
            tmp = tmp * now;

        }
        return B;
    }





    /*
    * FLAG Chapter 4 begins !
    * */

    /**
     * http://www.lintcode.com/zh-cn/problem/flip-bits/
     *@param a, b: Two integer
     *return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int count = 0;
        for (int c = a ^ b; c != 0; c = c >>> 1) {
            count += c & 1;
        }
        return count;
    }





    /**
     * http://www.lintcode.com/zh-cn/problem/update-bits/
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        // write your code here
        int max = ~0; /* All 1’s */
        // 1’s through position j, then 0’s
        if (j == 31)
            j = max;
        else
            j = (1 << (j + 1)) - 1;
        int left = max - j;
        // 1’s after position i
        int right = ((1 << i) - 1);
        // 1’s, with 0s between i and j
        int mask = left | right;
        // Clear i through j, then put m in there
        return ((n & mask) | (m << i));
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/unique-binary-search-trees/
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        /*
            The case for 3 elements example
            Count[3] = Count[0]*Count[2]  (1 as root)
                  + Count[1]*Count[1]  (2 as root)
                  + Count[2]*Count[0]  (3 as root)

            Therefore, we can get the equation:
            Count[i] = ∑ Count[0...k] * [ k+1....i]     0<=k<i-1
        */
        int[] count = new int[n+2];
        count[0] = 1;
        count[1] = 1;

        for(int i=2;  i<= n; i++){
            for(int j=0; j<i; j++){
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/fast-power/
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        if (n == 1) {
            return a % b;
        }
        if (n == 0) {
            return 1 % b;
        }

        long product = fastPower(a, b, n / 2);
        product = (product * product) % b;
        if (n % 2 == 1) {
            product = (product * a) % b;
        }
        return (int) product;
    }






    /**
     * http://www.lintcode.com/zh-cn/problem/binary-representation/
     *@param n: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String n) {
        // write your code here
        if (n.indexOf('.') == -1) {
            return parseInteger(n);
        }
        String[] params = n.split("\\.");
        String flt = parseFloat(params[1]);
        if (flt == "ERROR") {
            return flt;
        }
        if (flt.equals("0") || flt.equals("")) {
            return parseInteger(params[0]);
        }
        return parseInteger(params[0]) + "." + flt;
    }

    private String parseInteger(String str) {
        int n = Integer.parseInt(str);
        if (str.equals("") || str.equals("0")) {
            return "0";
        }
        String binary = "";
        while (n != 0) {
            binary = Integer.toString(n % 2) + binary;
            n = n / 2;
        }
        return binary;
    }

    private String parseFloat(String str) {
        double d = Double.parseDouble("0." + str);
        String binary = "";
        HashSet<Double> set = new HashSet<Double>();
        while (d > 0) {
            if (binary.length() > 32 || set.contains(d)) {
                return "ERROR";
            }
            set.add(d);
            d = d * 2;
            if (d >= 1) {
                binary = binary + "1";
                d = d - 1;
            } else {
                binary = binary + "0";
            }
        }
        return binary;
    }






    /*
    * FLAG Chapter 5 begins !
    * */


    /**
     * http://www.lintcode.com/zh-cn/problem/gas-station/
     * @param gas: an array of integers
     * @param cost: an array of integers
     * @return: an integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // write your code here
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
            return -1;
        }

        int sum = 0;
        int total = 0;
        int index = -1;

        for(int i = 0; i<gas.length; i++) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if(sum < 0) {
                index = i;
                sum = 0;
            }
        }
        return total < 0 ? -1 : index + 1;
        // index should be updated here for cases ([5], [4]);
        // total < 0 is for case [2], [2]
    }




    /**
     * http://www.jiuzhang.com/solutions/delete-digits/
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public String DeleteDigits(String A, int k) {
        // write your code here
        StringBuffer sb = new StringBuffer(A);
        int i, j;
        for (i = 0; i < k; i++) {
            for (j = 0; j < sb.length() - 1
                    && sb.charAt(j) <= sb.charAt(j + 1); j++) {
            }
            sb.delete(j, j + 1);
        }
        while (sb.length() > 1 && sb.charAt(0)=='0')
            sb.delete(0,1);
        return sb.toString();
    }







}
