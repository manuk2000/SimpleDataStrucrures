public class DoubleLinkList<T extends Comparable<T>> {
    public static void main(String[] args) {
        DoubleLinkList<Integer> list = new DoubleLinkList<>();

        list.print();
        // Test pushBack and pushFront
        list.pushBack(1);
        list.print();
        list.pushFront(2);

        list.print();
        list.pushBack(3);
        list.print();
        list.pushFront(4);

        list.print();
        // Test popBack and popFront
        System.out.println("Pop Back: " + list.popBack()); // Output: 3

        list.print();
        System.out.println("Pop Front: " + list.popFront()); // Output: 4

        list.print();
        // Test erase and insert
        list.pushBack(5);
        list.pushBack(6);
        list.printVal();

        list.reverse();
        list.printVal();

        list.head = list.mergSortortList(list.head);
        list.printVal(); // Output: 8 7 2 1 5 6

        list.erase(-1);
        list.print(); // Output: 8 7 1 5 6

        // Test clear and empty
        list.clear();
        list.print();

        System.out.println("Is empty: " + list.empty()); // Output: Is empty: true

        // Test size
        System.out.println("Size: " + list.size()); // Output: Size: 0

    }

    Node<T> head;
    Node<T> tail;

    public void pushBack(T val) {
        if (tail != null) {
            tail.next = new Node<>(val, null, tail);
            tail = tail.next;
        } else {
            head = tail = new Node<>(val, null);
        }
    }

    T popBack() {
        if (tail != null) {
            Node<T> res = tail;
            if (tail == head) {
                head = tail = null;
                return res.val;
            }
            tail = tail.previus;
            tail.next.previus = null;
            tail.next = null;
            return res.val;
        }
        return null;
    }

    void pushFront(T val) {
        if (head != null) {
            head.previus = new Node<T>(val, head, null);
            head = head.previus;
        } else {
            head = tail = new Node<>(val, null);
        }
    }

    T popFront() {
        if (head != null) {
            Node<T> res = head;
            if (head == tail) {
                head = tail = null;
                return res.val;
            }
            head = head.next;
            head.previus.next = null;
            head.previus = null;
            return res.val;
        }
        return null;
    }

    private Node<T> pop(Node<T> node) {
        if (node.previus == null) {
            popFront();
        } else if (node.next == null) {
            popBack();
        } else {
            node.previus.next = node.next;
            node.next.previus = node.previus;
            node.next = node.previus = null;
        }
        return node;
    }

    private Node<T> findNode(int pos) {
        Node<T> tmpHead = head;

        while (tmpHead != null && pos > 0) {
            --pos;
            tmpHead = tmpHead.next;
        }
        return tmpHead;
    }

    private boolean nodeContain(int pos) {
        Node<T> tmpHead = head;

        while (tmpHead != null && pos > 0) {
            --pos;
            tmpHead = tmpHead.next;
        }
        return pos == 0;
    }

    boolean erase(int pos) {
        Node<T> tmpHead = findNode(pos);
        if (nodeContain(pos)) {
            pop(tmpHead);
            return true;
        }
        return false;
    }

    boolean insert(int pos, T val) {
        Node<T> node = findNode(pos);
        if (!nodeContain(pos)) {
            return false;
        } else if (node == null) {
            pushBack(val);
        } else if (node == head) {
            pushFront(val);
        } else {
            Node<T> tNode = new Node<>(val, node, node.previus);
            node.previus.next = tNode;
            node.next.previus = tNode;
        }
        return true;
    }

    void clear() {
        head = tail = null;
    }

    boolean empty() {
        return head == tail;
    }

    int size() {
        Integer size = 0;
        Node<T> head = this.head;
        while (head != null) {
            ++size;
            head = head.next;
        }
        return size;
    }

    void print() {
        Node<T> head = this.head;
        while (head != null) {
            System.out.print(head + "   ");
            head = head.next;
        }
        System.out.println();
    }
    void printVal() {
        Node<T> head = this.head;
        while (head != null) {
            System.out.print(head.val + "   ");
            head = head.next;
        }
        System.out.println();
    }

    void reverse() {
        Node<T> tmpHead = tail;
        tail = head;
        head = tmpHead;
        while (tmpHead != null){
            Node<T> tmp = tmpHead.next;
            tmpHead.next = tmpHead.previus;
            tmpHead.previus = tmp;
            tmpHead = tmpHead.next;
        }
    }

    Node<T> merge(Node<T> head1, Node<T> head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        if (head1.val.compareTo(head2.val) > 0) {
            head2.next = merge(head1, head2.next);
            return head2;
        } else {
            head1.next = merge(head1.next, head2);
            return head1;
        }
    }

    Node<T> partition(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> slow = head;
        Node<T> fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.previus.next = null;
        slow.previus = null;
        return slow;
    }

    Node<T> mergSortortList(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> partition = partition(head);
        Node<T> left = mergSortortList(head);
        Node<T> right = mergSortortList(partition);
        Node<T> merge = merge(left, right);
        return merge;
    }

    private class Node<T extends Comparable<T>> {
        private T val;
        private Node<T> next;
        private Node<T> previus;

        public Node(T val, final Node<T> next, final Node<T> previus) {
            this.val = val;
            this.next = next;
            this.previus = previus;
        }

        public Node(T val, final Node<T> previus) {
            this.val = val;
            this.previus = previus;
        }

        @Override
        public String toString() {
            String prevVal = previus == null ? " null " : previus.val.toString();
            String nextVal = next == null ? " null " : next.val.toString();
            return "Node{" +
                    "val=" + val +
                    ", next=" + nextVal +
                    ", previus=" + prevVal +
                    "}";
        }
    }
}
