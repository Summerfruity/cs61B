public class VengefulSLList<T> extends LinkedListDeque<T> {

    LinkedListDeque<T> deletedList;

    public VengefulSLList(){
        super();
        deletedList = new LinkedListDeque<T>();
    }

    @Override
    public T removeLast(){
        T x = super.removeLast();
        deletedList.addLast(x);
        return x;
    }

    public void printLostItems(){
        deletedList.printDeque();
    }

    public static void main(String[] args){
        VengefulSLList<Integer> vs1 = new VengefulSLList<Integer>();
        vs1.addFirst(4);
        vs1.addFirst(3);
        vs1.addFirst(2);
        vs1.addFirst(1);
        //vs1 is now [1,2,3,4]
        vs1.removeLast();
        vs1.removeLast();
        //vs1 is now [1,2]
        vs1.printDeque();
        System.out.println(" ");
        vs1.printLostItems();

    }

}
