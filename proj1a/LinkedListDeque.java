/*
* Double ended queue
*
* ˫�˶����Ǿ��ж�̬��С���������������������ˣ�ǰ�˻��ˣ�������չ������
* ʹ��ѭ���ڱ���ʵ�֣�sentinel.pre == sentinel�����Ϊ��
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


    /**  �����յ�˫�˶���*/
    public LinkedListDeque() {
        sentinel = new IntNode(null,null,null); //�����ڱ��ڵ�
        sentinel.prev = sentinel; //�ڱ���prev��next��ָ���Լ�
        sentinel.next = sentinel;
        size = 0; //��ʼ������Ϊ0
    }

    /**  �ڶ���ǰ�����һ������ΪT����Ŀ */
    public void addFirst(T item) {
        IntNode newNode = new IntNode(sentinel.prev, item, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
        }


    /**  �ڶ��к�����һ������ΪT����Ŀ */
    public void addLast(T item) {
        IntNode newNode = new IntNode(sentinel.prev, item, sentinel);
        //
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        //���Ӷ��еĴ�С
        size++;
    }
    /**   ����Ϊ�շ���true�����򷵻�false*/
    public boolean isEmpty() {
        if(sentinel.next == sentinel){
            return true;
        }else {
            return false;
        }

    }

    /**  ���ض����е���Ŀ����*/
    public int size() {
        return size;
    }

    /**  ��ǰ�����ӡ�����е���Ŀ����Ŀ֮���Կո�ָ�*/
    public void printDeque() {
    IntNode current = sentinel.next;
    while (current != sentinel) {
        System.out.print(current.item + " ");
        current = current.next;
    }
    System.out.println();
    }

    /**  �Ƴ������ض���ǰ�˵���Ŀ�����û�и���Ŀ���򷵻�null*/
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

    /**  �Ƴ������ض��к�˵���Ŀ�����û�и���Ŀ���򷵻�null*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        IntNode lastNode = sentinel.prev;
        T item = lastNode.item; //��ȡ���һ��Ԫ��

        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;

        size--;

        return item;
    }

    /**  ��ȡ��������������Ŀ������0��ǰ�ˣ�1����һ����Ŀ���Դ����ơ�û���򷵻�null���˲��������޸Ķ���*/
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
     * ʹ�õݹ�ʵ��get����
     * */
    // LinkedListDeque �еĵݹ� get ����
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null; // �������������Χ���򷵻� null
        }
        return getRecursiveHelper(sentinel.next, index); // ���õݹ鸨������
    }

    // �ݹ鸨����������ָ���ڵ㿪ʼ�ݹ������������Ԫ��
    private T getRecursiveHelper(IntNode node, int index) {
        if (index == 0) {
            return node.item; // �ҵ���������Ԫ�أ����ظ�Ԫ��
        }
        return getRecursiveHelper(node.next, index - 1); // �ݹ���ã�����һ���ڵ��������
    }
}
