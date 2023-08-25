public class Vector<T> {
    public static void main(String[] args) {
        Vector<Object> o0 = new Vector<>();
        o0.push(2);
        Vector<Object> o = new Vector<>(1);
//        o.resize(20);
        o.push(12);
        o.push(12);
        o.push(123);
        o.push(124);
        o.push(125);
        o.insert(2, 0, 4);
        o.insert(4, new Integer[]{12, 2});
        o.insert(-1, 0);
    }

    private static final int STANDART_MULTIPLICATION_SIZE = 2;
    private static final int START_CAPASITY = 10;
    private Object[] data;
    private int lastIndex;
    private int capasity;

    public Vector() {
        this(START_CAPASITY);
    }

    public Vector(int minCapasity) {
        if (minCapasity > 0) {
            capasity = minCapasity;
            data = new Object[capasity];
        } else if (minCapasity == 0) {
            capasity = START_CAPASITY;
            data = new Object[capasity];
        }
        lastIndex = -1;
    }

    public Vector(Vector<T> copy) {
        data = copy.data;
        lastIndex = copy.lastIndex;
        capasity = copy.capasity;
    }

    private void reduceFreePlace() {
        if (capasity / 2 > lastIndex) {
            setMinCapasity(capasity * 1 / 4);
        }
    }

    public int getSize() {
        return lastIndex;
    }

    public boolean isEmpty() {
        return lastIndex == -1;
    }

    public int getCapasity() {
        return capasity;
    }

    public void clear() {
        capasity = START_CAPASITY;
        data = new Object[capasity];
        lastIndex = -1;
    }

    public T top() {
        return lastIndex >= 0 ? (T) data[lastIndex] : null;
    }

    public void setMinCapasity(int size) {
        int newLength = capasity - size;
        if (newLength <= 1) {
            throw new IllegalArgumentException();
        }
        Object[] newData = new Object[newLength];
        System.arraycopy(data, 0, newData, 0, newLength);
        data = newData;
        capasity = newLength;
        if (capasity <= lastIndex) {
            lastIndex = capasity;
        }
    }

    private void resize(int size) {
        if (size > 0) {
            int newSize = size * STANDART_MULTIPLICATION_SIZE;
            Object[] newData = new Object[newSize];
            System.arraycopy(data, 0, newData, 0, Math.min(size, capasity));
            data = newData;
            capasity = newSize;
            return;
        }
        throw new IllegalArgumentException("Ilegal Argument" + size);
    }

    public void push(T e) {
        if (++lastIndex == capasity) {
            resize(capasity);
        }
        data[lastIndex] = e;
    }

    public void insert(int pos, T val) {
        insert(pos, val, 1);
    }

    public void insert(int index, T val, int countPutVal) {
        chack(index, val, countPutVal);
        if (lastIndex + countPutVal == capasity) {
            resize(Math.max(capasity, countPutVal));
        }
        System.arraycopy(data, index, data, index + countPutVal, lastIndex - index);
        set(index, val, countPutVal);
    }

    public boolean chack(int pos, T[] val, int countPutVal) {
        if (pos < 0 || val == null || countPutVal < 0) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public boolean chack(int pos, T val, int countPutVal) {
        if (pos < 0 || val == null || countPutVal < 0) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public void set(int pos, T val, int countPutVal) {
        chack(pos, val, countPutVal);
        for (int i = pos; i < countPutVal + pos; i++) {
            data[i] = val;
        }
    }

    public void set(int pos, T[] vals) {
        chack(pos, vals, 0);
        for (int i = 0; i < vals.length; i++) {
            data[i + pos] = vals[i];
        }
    }

    public void insert(int pos, T[] arr) {
        chack(pos, arr, 0);
        if (lastIndex + arr.length >= capasity) {
            resize(Math.max(capasity, arr.length));
        }
        System.arraycopy(data, pos, data, pos + arr.length - 1, lastIndex - pos);
        set(pos, arr);
    }

    public T pop() {
        return remove(lastIndex);
    }

    public T remove(int pos, int count) {
        if (pos < 0 || pos > lastIndex) {
            throw new IllegalArgumentException();
        }
        T res = (T) data[pos];

        System.arraycopy(data, pos + count, data, pos, lastIndex - pos + count);
        lastIndex -= count;
        reduceFreePlace();
        return res;
    }

    public T remove(int pos) {
        return remove(pos, 1);
    }
}
