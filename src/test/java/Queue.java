/**
 * http://www.lintcode.com/zh-cn/problem/implement-queue-by-linked-list/
 * Created by Marcus_Chang on 2016/4/6.
 */
public class Queue {
    public ListNode first, last;

    public Queue() {
        first = last = null;
        // do initialize if necessary
    }

    public void enqueue(int item) {
        // Write your code here
        if (first == null) {
            last = new ListNode(item);
            first = last;
        } else {
            last.next = new ListNode(item);
            last = last.next;
        }
    }

    public int dequeue() {
        // Write your code here
        if (first != null) {
            int item = first.val;
            first = first.next;
            return item;
        }
        return -1;
    }
}
