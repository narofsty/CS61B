/**使用循环数组实现
*
*/

public class ArrayDeque <T>{

        private T[] items;
        private int size;
        private int nextFirst;
        private int nextLast;

    /** 创建一个空的数组双端队列
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
    * 扩展或缩小数组容量的私有方法
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
    /** 在队列前端添加一个类型为T的项目 */
    public void addFirst(T item) {
        if(size == items.length) {
            resize (size * 2);
            items[nextFirst] = item;
            nextFirst = (nextFirst -1 + items.length) % items.length;
            size ++;
        }
    }

    /**
    * 在队列后端添加一个元素
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
    *判断队列是否为空
    */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
    * 返回队列中的元素数量
    * */
    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " "); // 打印当前元素并在后面加上空格
        }
        System.out.println(); // 打印完毕后换行
    }
    /**
     * 从前端移除并返回返回元素
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
     * 从队列后端移除并返回元素
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
    // ArrayDeque 中的递归 get 方法
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null; // 如果索引超出范围，则返回 null
        }
        return getRecursiveHelper(items, (nextFirst + index) % items.length, index); // 调用递归辅助方法
    }

    // 递归辅助方法，从指定索引处开始递归查找元素
    private T getRecursiveHelper(Object[] array, int currentIndex, int targetIndex) {
        if (targetIndex == 0) {
            return (T) array[currentIndex]; // 找到索引处的元素，返回该元素
        }
        return getRecursiveHelper(array, (currentIndex + 1) % array.length, targetIndex - 1); // 递归调用，在下一个索引处继续查找
    }

}

