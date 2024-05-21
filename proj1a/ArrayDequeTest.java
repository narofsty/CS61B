public class ArrayDequeTest {

    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> array1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true,array1.isEmpty());

        array1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1,array1.size()) && passed;
        passed = checkEmpty(false, array1.isEmpty()) && passed;

        System.out.println("-------------in middle");
        array1.addLast("middle");
        passed = checkSize(2,array1.size()) && passed;

        System.out.println("-------------in back");
        array1.addLast("back");
        passed = checkSize(3,array1.size()) && passed;

        System.out.println("-------------in front1");
        array1.addFirst("front1");
        passed = checkSize(4,array1.size()) && passed;

        System.out.println("Printing out deque");
        array1.printDeque();
        printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that ddeque is empty afterwards. */
    public static void addremoveTest() {
        System.out.println("Running remove test.");

        ArrayDeque<String> array1 = new ArrayDeque<>();
        boolean passed = checkEmpty(true,array1.isEmpty());

        array1.addFirst("1");
        passed = checkEmpty(false,array1.isEmpty());
        array1.addFirst("2");
        array1.addFirst("3");
        array1.addFirst("4");
        array1.addFirst("5");

        array1.removeFirst();
        passed = checkEmpty(false,array1.isEmpty());

        array1.printDeque();

        printTestStatus(passed);

    }
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        //addIsEmptySizeTest();
        addremoveTest();

    }
}
