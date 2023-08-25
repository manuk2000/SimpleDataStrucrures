import javax.lang.model.element.Element;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyQueue<T> {
    public static void main(String[] args) {
        MyQueue<Integer> o = new MyQueue<>();
        o.add(0);
        o.add(1);
        o.add(2);
        o.add(3);

        o.poll();
        o.poll();
        o.poll();

        o.add(4);
        o.add(5);
        o.add(6);

        o.poll();
        o.poll();
//        o.add(7);
//        o.add(8);

        o.poll();
        o.poll();
        o.poll();
        Integer a = o.poll();
//        ++o.popIndex;
//        ++o.popIndex;
//        ++o.popIndex;
//        o.elementCount--;
//        o.elementCount--;
//        o.elementCount--;

        o.add(9);
        o.add(10);
        o.add(11);
        o.add(12);
        o.add(13);
        o.add(14);
        o.add(15);
        o.add(16);
        o.add(17);
        o.add(18);
    }

    private static final int START_CAPASITY = 4;
    private static final int STANDART_MULTIPLICATION_SIZE = 2;
    private int capasity;
    private int lastIndex;
    private int popIndex;
    private Object[] data;
    private int elementCount;

    public MyQueue() {
        this(START_CAPASITY);
    }

    public MyQueue(int minCapasity) {
        if (minCapasity > 0) {
            capasity = minCapasity;
            data = new Object[capasity];
        } else if (minCapasity == 0) {
            capasity = START_CAPASITY;
            data = new Object[capasity];
        }
        lastIndex = -1;
        popIndex = 0;
        elementCount = 0;
    }

    public void add(T e) {
        if (elementCount == capasity) {
            reSize();
        }
        if (lastIndex == data.length - 1) {
            lastIndex = -1;
        }
        data[++lastIndex] = e;
        ++elementCount;
    }

    private void reSize() {
        int newSize = capasity * STANDART_MULTIPLICATION_SIZE;
        Object[] newArr = new Object[newSize];
        int countRightElement = capasity - popIndex;
        System.arraycopy(data, popIndex, newArr, 0, countRightElement);
        System.arraycopy(data, 0, newArr, countRightElement, popIndex);
        popIndex = 0;
        lastIndex = capasity - 1;
        capasity = newSize;
        data = newArr;
    }


    public T poll() {
        T res = element();
        if (res == null) {
            return null;
        }
        if (popIndex == capasity - 1) {
            popIndex = 0;
        } else {
            ++popIndex;
        }
        --elementCount;
        return res;
    }

    T element() {
        if (elementCount == 0) {
            return null;
        }
        return (T) data[popIndex];

    }

}
