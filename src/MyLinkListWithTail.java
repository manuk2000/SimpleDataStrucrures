import java.util.Optional;

public class MyLinkListWithTail<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void addlast(T val) {
        Node<T> node = new Node<>(val);
        ++size;
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public void addFirst(T val) {
        ++size;
        Node<T> node = new Node<>(val);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        node.next = head;
        head = node;
    }

    public boolean addNode(T val, int index) {
        if (index >= size) {
            return false;
        }
        if (index == 0) {
            addFirst(val);
        } else if (index == size - 1) {
            addlast(val);
        } else {
            Node<T> node = new Node<>(val);
            Node<T> head = findPrevious(index).get();
            Node<T> mid = this.head.next;
            this.head.next = node;
            node.next = mid;
            ++size;
        }
        return true;
    }

    private Optional<Node<T>> findPrevious(int index) {
        Node<T> head = this.head;
        int i = 0;
        while (i < index - 1 && head != null) {
            i++;
            head = head.next;
        }
        return Optional.of(head);
    }

    private NodeIndex findPreviousAndIndex(T o) {
        Node<T> head = this.head;


        if (head.val.equals(o)) {
            return new NodeIndex(head, 0);
        }

        int i = -1;
        while (head.next != null && !head.next.val.equals(o)) {
            ++i;
            head = head.next;
        }
        if (head.next != null) {
            return new NodeIndex(head, i + 2);
        }
        return null;
    }

    public T removeFirst() {
        Node<T> tmp = head;
        head = head.next;
        --size;
        return tmp.val;
    }

    public T removeLast() {
        Node<T> tmp = tail;
        Optional<Node<T>> previous = findPrevious(size - 1);
        if (previous.isPresent()) {
            --size;
            previous.get().next = null;
            tail = previous.get();
        }
        return null;
    }

    public boolean remove(T object) {
        NodeIndex previousAndIndex = findPreviousAndIndex(object);

        if (previousAndIndex == null) {
            return false;
        } else if (previousAndIndex.index == 0) {
            removeFirst();
        } else if (previousAndIndex.index == size - 1) {
            removeLast();
        } else {
            previousAndIndex.node.next = previousAndIndex.node.next.next;
        }
        return true;
    }

    public T getFirst() {
        return head != null ? head.val : null;
    }

    public T getLast() {
        return tail != null ? tail.val : null;
    }

    public T get(int index) {
        return findPrevious(index).get().val;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(T val) {
        return findPreviousAndIndex(val) != null;
    }

    public int indexOf(T val) {
        NodeIndex previousAndIndex = findPreviousAndIndex(val);
        return previousAndIndex != null ? previousAndIndex.index : -1;
    }

    private class NodeIndex {
        Node<T> node;
        int index;

        public NodeIndex(Node<T> node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    private class Node<T> {
        T val;
        Node<T> next;

        public Node(T val) {
            this.val = val;
        }
    }
}
