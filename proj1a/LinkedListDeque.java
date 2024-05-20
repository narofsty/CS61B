/*
* Double ended queue
*
* 双端队列是具有动态大小的序列容器，可以在两端（前端或后端）进行扩展或收缩
* 使用循环哨兵来实现，sentinel.pre == sentinel则队列为空
*
* */
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


    /**  创建空的双端队列*/
    public LinkedListDeque() {
        sentinel = new IntNode(null,null,null); //创建哨兵节点
        sentinel.prev = sentinel; //哨兵的prev和next都指向自己
        sentinel.next = sentinel;
        size = 0; //初始化队列为0
    }

    /**  在队列前端添加一个类型为T的项目 */
    public void addFirst(T item) {
        IntNode newNode = new IntNode(sentinel.prev, item, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
        }


    /**  在队列后端添加一个类型为T的项目 */
    public void addLast(T item) {
        IntNode newNode = new IntNode(sentinel.prev, item, sentinel);
        //
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        //增加队列的大小
        size++;
    }
    /**   队列为空返回true，否则返回false*/
    public boolean isEmpty() {
        if(sentinel.next == sentinel){
            return true;
        }else {
            return false;
        }

    }

    /**  返回队列中的项目数量*/
    public int size() {
        return size;
    }

    /**  从前到后打印队列中的项目，项目之间以空格分隔*/
    public void printDeque() {
    IntNode current = sentinel.next;
    while (current != sentinel) {
        System.out.print(current.item + " ");
        current = current.next;
    }
    System.out.println();
    }

    /**  移除并返回队列前端的项目。如果没有该项目，则返回null*/
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

    /**  移除并返回队列后端的项目。如果没有该项目，则返回null*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        IntNode lastNode = sentinel.prev;
        T item = lastNode.item; //获取最后一个元素

        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;

        size--;

        return item;
    }

    /**  获取给定索引处的项目，其中0是前端，1是下一个项目，以此类推。没有则返回null。此操作不能修改队列*/
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
    /**
     * todo getRecursive(int index)
     * 使用递归实现get方法
     * */
    // LinkedListDeque 中的递归 get 方法
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null; // 如果索引超出范围，则返回 null
        }
        return getRecursiveHelper(sentinel.next, index); // 调用递归辅助方法
    }

    // 递归辅助方法，从指定节点开始递归查找索引处的元素
    private T getRecursiveHelper(IntNode node, int index) {
        if (index == 0) {
            return node.item; // 找到索引处的元素，返回该元素
        }
        return getRecursiveHelper(node.next, index - 1); // 递归调用，在下一个节点继续查找
    }
}
