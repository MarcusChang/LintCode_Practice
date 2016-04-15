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



}
