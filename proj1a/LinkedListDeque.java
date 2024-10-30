public class LinkedListDeque<T> {
    public class Node{
        T item;
        Node prev;
        Node next;
        public Node(T x, Node p, Node n){
            item = x;
            prev = p;
            next = n;
        }
    }
    /** the first item(if it exists) is at sentinel.next */
    private Node sentinel;

    /** Maintain a size variable that caches the size of the list */
    private int size;

    /** The Last pointer always point to the last node */
    private Node last;

    /** Creates an empty list */
    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        last = sentinel;
    }

    /** Adds x to the front of the list*/
    public void addFirst(T item){
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        if (size == 0){
            last = newNode;
        }
        size += 1;
    }

    /** Adds x to the end of the  list*/
    public void addLast(T item){
        Node newNode = new Node(item, last, sentinel);
        last.next = newNode;
        sentinel.prev = newNode;
        last = newNode;
        size +=1;
    }

    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the number of items in the deque */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque(){
        Node tmp = sentinel.next;
        while(tmp.next != sentinel){
            System.out.println(tmp.item + " ");
            tmp = tmp.next;
        }
        System.out.println(" ");
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }

        Node first = sentinel.next; //Get the first node
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size -= 1;
        return first.item;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T item = last.item;
        last = last.prev;
        last.next = sentinel;
        sentinel.prev = last;
        size -= 1;
        return item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
      */
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        for (int cnt = 0; cnt < index; cnt++) {
            p = p.next;
        }
        return p.item;
    }

    /** Same as get, but uses recursion.*/
    public T getRecursive(int index){
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    /** Creates a deep copy of other */
    public LinkedListDeque(LinkedListDeque other){
        this();
        Node p = other.sentinel.next;
        while(p != sentinel){
            this.addLast(p.item);
            p = p.next;
        }
    }
}