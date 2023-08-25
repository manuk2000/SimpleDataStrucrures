public class Mystack<T> {
    private Vector<T> stack;

    public T top() {
        return stack.top();
    }

    public void push(T e) {
        stack.push(e);
    }

    public T pop() {
        return stack.pop();
    }

    public int getSize() {
        return stack.getSize();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
