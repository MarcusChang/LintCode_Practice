import java.util.*;

/**
 * Created by Marcus_Chang on 2016/3/18.
 */
public class AnimalShelter {

    public AnimalShelter() {
        // do initialize if necessary
        tot = 0;
        dogs = new LinkedList<String>();
        cats = new LinkedList<String>();
    }

    /**
     * http://www.lintcode.com/zh-cn/problem/animal-shelter/
     * @param name a string
     * @param type an integer, 1 if Animal is dog or 0
     * @return void
     */
    void enqueue(String name, int type) {
        // write your code here
        tot += 1;
        if (type == 1)
            dogs.add(tot + "#" + name);
        else
            cats.add(tot + "#" + name);
    }

    public String dequeueAny() {
        // write your code here
        if (cats.isEmpty())
            return dequeueDog();
        else if (dogs.isEmpty())
            return dequeueCat();
        else {
            int d_time = getTime(dogs.getFirst());
            int c_time = getTime(cats.getFirst());
            if (c_time < d_time)
                return dequeueCat();
            else
                return dequeueDog();
        }
    }

    public String dequeueDog() {
        // write your code here
        String name = getName(dogs.getFirst());
        dogs.removeFirst();
        return name;
    }

    public String dequeueCat() {
        // write your code here
        String name = getName(cats.getFirst());
        cats.removeFirst();
        return name;
    }

    private int tot;
    private LinkedList<String> cats, dogs;
    private String getName(String str) {
        return str.substring(str.indexOf("#") + 1, str.length());
    }
    private int getTime(String str) {
        return Integer.parseInt(str.substring(0, str.indexOf("#")));
    }

}
