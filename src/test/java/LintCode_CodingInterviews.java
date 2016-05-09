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





}
