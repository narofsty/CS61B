public class LinkedListDeque <T> {
  private IntNode sentinel;
  private int size;

    public class IntNode {
        IntNode prev;
        T item;
        IntNode next;

        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }



    public LinkedListDeque() {
        sentinel = new IntNode(null,null,null); //创建哨兵节点
        sentinel.prev = sentinel; //哨兵的prev和next都指向自己
        sentinel.next = sentinel;
        size = 0; //初始化队列为0
    }


    public void addFirst(T item) {
        IntNode newNode = new IntNode(sentinel.prev, item, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
        }



    public void addLast(T item) {
        IntNode newNode = new IntNode(sentinel.prev, item, sentinel);

        sentinel.prev.next = newNode;
        sentinel.prev = newNode;

        size++;
    }

    public boolean isEmpty() {
        if(sentinel.next == sentinel){
            return true;
        }else {
            return false;
        }

    }


    public int size() {
        return size;
    }


    public void printDeque() {
    IntNode current = sentinel.next;
    while (current != sentinel) {
        System.out.print(current.item + " ");
        current = current.next;
    }
    System.out.println();
    }


    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        IntNode firstNode = sentinel.next;
        T item = firstNode.item;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        size--;

        return item;
    }


    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        IntNode lastNode = sentinel.prev;
        T item = lastNode.item;

        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;

        size--;

        return item;
    }


    public T get(int index) {
    if(index < 0 || index >= size){
        return null;
    }
    IntNode current =  sentinel.next;
    for(int i = 0;i <size; i++) {
      current = current.next;
    }
    return current.item;
    }


    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }


    private T getRecursiveHelper(IntNode node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
}
