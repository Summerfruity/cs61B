/** . 理解项目需求
 首先，我们需要实现一个双端队列（Deque），它类似于我们之前讨论的 SLList 和 DLList。双端队列允许在队列的两端进行添加和删除操作。

 泛型
 定义泛型类：在类声明的名称后面使用尖括号 <> 包围一个任意的占位符。
 实例化泛型类：在声明时使用尖括号 <> 包围具体的类型。
  理清思路
 任务1：实现 LinkedListDeque
 创建文件：创建 LinkedListDeque.java 文件。
 定义节点类：定义一个内部类 Node，包含一个泛型类型的值 item，一个指向下一个节点的指针 next，和一个指向前一个节点的指针 prev。
 定义链表类：定义 LinkedListDeque 类，包含一个指向头部哨兵节点的指针 sentinel 和一个记录链表大小的变量 size。
 实现构造函数：
 public LinkedListDeque()：创建一个空的双向链表。
 public LinkedListDeque(LinkedListDeque other)：创建一个深拷贝。
 实现主要方法：
 public void addFirst(T item)：在链表头部添加一个节点。
 public void addLast(T item)：在链表尾部添加一个节点。
 public boolean isEmpty()：判断链表是否为空。
 public int size()：返回链表的大小。
 public void printDeque()：打印链表中的所有节点值。
 public T removeFirst()：删除并返回链表的第一个节点的值。
 public T removeLast()：删除并返回链表的最后一个节点的值。
 public T get(int index)：获取指定索引处的节点值。
 public T getRecursive(int index)：递归获取指定索引处的节点值。*/

public class LinkedListDeque<T> {
    // 先构造Node类
    private class Node {
        Node prev;
        T item;
        Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size; // 缓存链表大小
    private Node last;

    /** 初始化哨兵节点 */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        last = sentinel;
    }

    /** 创建深拷贝 */
    public LinkedListDeque(LinkedListDeque<T> other) {
        this();// 调用默认构造函数创建空链表
        Node temp = other.sentinel.next;
        while (temp != other.sentinel) {
            this.addLast(temp.item);
            temp = temp.next;
        }
    }

    /** 在链表头部添加节点 */
    public void addFirst(T x) {
        Node newNode = new Node(x, sentinel, sentinel.next); // 调整好newNode,此时sentinel.next.prev仍然指向sentinel
        sentinel.next.prev = newNode; // sentinel.next也需要调整
        sentinel.next = newNode;
        if (size == 0) {
            last = newNode;
        }
        size += 1;
    }

    /** 在链表尾部添加一个节点 */
    public void addLast(T x) {
        Node newNode = new Node(x, last, sentinel);
        last.next = newNode;
        sentinel.prev = newNode; // 循环链表
        last = newNode;
        if (size == 0) {
            sentinel.next = newNode; // 如果链表为空，哨兵节点的next也指向新节点
        }
        size += 1;
    }

    /** 判断链表是否为空 */
    public boolean isEmpty() {
        return size == 0;
    }

    /** 返回链表的大小 */
    public int size() {
        return size;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = sentinel.next;// 获取第一个节点
        sentinel.next = first.next;// 哨兵节点指向下一个节点
        first.next.prev = sentinel;// 更新第二个节点的prev指针
        size -= 1;
        return first.item;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node lastNode = last; //保存最后一个节点
        last = last.prev;// 更新last指向前一个节点
        last.next = sentinel;// 最后一个节点的next指向哨兵节点
        sentinel.prev = last;// 哨兵节点的prev指向更新后的last
        size-=1;
        return lastNode.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     If no such item exists, returns null. Must not alter the deque! */

    public T get(int index){
        if (index<0||index>=size){
            return null;
        }
        Node temp = sentinel.next;
        for(int i =0;i<index;i++){
            temp=temp.next;
        }
        return temp.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    /** 从头到尾打印双端队列中的项目，各个项目之间用空格分隔。*/

    public void printDeque(){
        Node temp = sentinel.next;
        while (temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }


}











