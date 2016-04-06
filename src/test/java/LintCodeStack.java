import java.util.*;


/**
 * Created by Marcus_Chang on 2016/3/11.
 */
public class LintCodeStack {

    /**
     * http://www.lintcode.com/zh-cn/problem/implement-stack/
    * */
    class Stack {
        // Push a new item into the stack
        public void push(int x) {
            // Write your code here
            array.add(x);
        }

        // Pop the top of the stack
        public void pop() {
            // Write your code here
            int n = array.size();
            if (n > 0)
                array.remove(n-1);
            return;
        }

        // Return the top of the stack
        public int top() {
            // Write your code here
            int n = array.size();
            return array.get(n-1);
        }

        // Check the stack is empty or not.
        public boolean isEmpty() {
            // Write your code here
            return array.size() == 0;
        }

        private List<Integer> array = new ArrayList<Integer>();
    }




    /**
     * http://www.lintcode.com/zh-cn/problem/implement-stack-by-two-queues/
     * */
    class StackByTwoQueues {
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        public StackByTwoQueues() {
            queue1 = new LinkedList<Integer>();
            queue2 = new LinkedList<Integer>();
        }

        private void moveItems() {
            while (queue1.size() != 1) {
                queue2.offer(queue1.poll());
            }
        }

        private void swapQueues() {
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        /**
         * push a new item into the stack
         */
        public void StackByTwoQueuesPush(int value) {
            queue1.offer(value);
        }

        /**
         * return the top of the stack
         */
        public int StackByTwoQueuesTop() {
            moveItems();
            int item = queue1.poll();
            swapQueues();
            queue1.offer(item);
            return item;
        }

        /**
         * pop the top of the stack and return it
         */
        public void StackByTwoQueuesPop() {
            moveItems();
            queue1.poll();
            swapQueues();
        }

        /**
         * check the stack is empty or not.
         */
        public boolean StackByTwoQueuesIsEmpty() {
            return queue1.isEmpty();
        }

    }

}
