package chapter_4.queue;

/**
 * Created by xiang.wei on 2020/2/4
 *
 * @author xiang.wei
 */
public class LinkQueue {
    /**
     * 队头指针
     */
    private volatile Node head;

    /**
     * 队尾指针
     */
    private volatile Node tail;

    /**
     * 队列的长度
     */
    private  int size;

    /**
     * 初始化队列
     */
    public LinkQueue() {
        head = tail = new Node(null);
    }

    /**
     * 入队
     *
     * @param item
     */
    public void enqueue(String item) {
        Node newNode = new Node(item);
        tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = null;
        tail = newNode;
        ++size;
    }

    /**
     * 出队
     *
     * @return
     */
    public Object dequeue() {
        head = head.next;
        size--;
        return head.value;
    }

    /**
     * 遍历队列
     * @return
     */
    public  String pringQueue() {
        StringBuilder queueStr = new StringBuilder();
        for (Node node = head.next; node == null; node = head.next) {
            queueStr.append(head.next.value+"-->");
        }
        return queueStr.toString();
    }
    public static void main(String[] args) {
        LinkQueue linkQueue = new LinkQueue();
        for (int i = 0; i < 6; i++) {
            linkQueue.enqueue("测试" + i);
        }
        System.out.println("队列="+linkQueue.pringQueue());
        for (int i = 0; i < linkQueue.size; i++) {
            linkQueue.dequeue();
        }
    }


    class Node {
        /**
         * 数据域
         */
        Object value;
        /**
         * 下一个结点对象的引用
         */
        Node next;

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(Object value) {
            this.value = value;
        }

        public Node(Node next) {
            this.next = next;
        }

    }
}
