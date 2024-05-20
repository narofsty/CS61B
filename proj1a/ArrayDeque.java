/**ʹ��ѭ������ʵ��
*
*/

public class ArrayDeque <T>{

        private T[] items;
        private int size;
        private int nextFirst;
        private int nextLast;

    /** ����һ���յ�����˫�˶���
     *
    * */
    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
    * ��չ����С����������˽�з���
     *
    * */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int newFront = (capacity -size) / 2;
        for (int i = 0; i < size; i++) {
            newArray[newFront + i] = items[(nextFirst + 1 + i) % items.length];
        }
        items = newArray;
        nextFirst = newFront -1;
        nextLast = newFront + size;
    }
    /** �ڶ���ǰ�����һ������ΪT����Ŀ */
    public void addFirst(T item) {
        if(size == items.length) {
            resize (size * 2);
            items[nextFirst] = item;
            nextFirst = (nextFirst -1 + items.length) % items.length;
            size ++;
        }
    }

    /**
    * �ڶ��к�����һ��Ԫ��
    * */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size ++;
    }

    /**
    *�ж϶����Ƿ�Ϊ��
    */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
    * ���ض����е�Ԫ������
    * */
    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " "); // ��ӡ��ǰԪ�ز��ں�����Ͽո�
        }
        System.out.println(); // ��ӡ��Ϻ���
    }
    /**
     * ��ǰ���Ƴ������ط���Ԫ��
     * */
    public T removFirst() {
     if (isEmpty()) {
         return null;
     }
     nextFirst = (nextFirst + 1) % items.length;
     T item = items[nextFirst];
     items[nextFirst] = null;
     size --;
     if (size > 0 && size == items.length / 4) {
         resize(items.length / 2);
     }
     return item;
    }
    /**
     * �Ӷ��к���Ƴ�������Ԫ��
    * */
    public T removeLast() {
    if (isEmpty()) {
        return null;
    }
    nextLast = (nextLast - 1 + items.length) % items.length;
    T item = items[nextLast];
    items[nextLast] = null;
    size --;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size: " + size);
    }
        return (T) items[index];


    }
    // ArrayDeque �еĵݹ� get ����
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null; // �������������Χ���򷵻� null
        }
        return getRecursiveHelper(items, (nextFirst + index) % items.length, index); // ���õݹ鸨������
    }

    // �ݹ鸨����������ָ����������ʼ�ݹ����Ԫ��
    private T getRecursiveHelper(Object[] array, int currentIndex, int targetIndex) {
        if (targetIndex == 0) {
            return (T) array[currentIndex]; // �ҵ���������Ԫ�أ����ظ�Ԫ��
        }
        return getRecursiveHelper(array, (currentIndex + 1) % array.length, targetIndex - 1); // �ݹ���ã�����һ����������������
    }

}

