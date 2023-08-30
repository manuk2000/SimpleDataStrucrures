import java.util.List;

//& Comparable<T>
public class MyLinkList<T extends Comparable<T>>  {

    public static void main(String[] args) {
        MyLinkList<Integer> list1 = new MyLinkList<>();
        list1.pushBack(1);
        list1.pushBack(2);
        list1.pushBack(3);
        list1.pushBack(1);
        list1.pushBack(3);
        list1.pushBack(4);
    }


    private Node<T> head;
    public int getDecimalValue(NodeInteger head) {
        if (head.next == null) {
            int count = head.val;
            head.val = 2;
            return count;
        }
        int decimalValue = getDecimalValue(head.next);
        int count = head.val * head.next.val;
        count += decimalValue;
        head.val = head.next.val * 2;
        return count;
    }

    public boolean isPalindrome(Node<T> head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return (head.next.val == head.val);
        }
        Node<T> slow = head;
        Node<T> fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node<T> slowEnd = slow;
        fast = slow.next;
        Node<T> tmp;
        do {
            tmp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = tmp;
        } while (fast != null);
        slowEnd.next = null;

        while (slow != null) {
            if (slow.val != head.val) {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;
    }


    /**
     * Partitions a singly linked list based on a pivot value 'x'. Nodes with values less than 'x'
     * are moved before nodes with values greater than or equal to 'x', maintaining relative order.
     *
     * @param head The head of the linked list to be partitioned.
     * @param x    The pivot value used for partitioning.
     * @return The head of the partitioned linked list.
     */
    public Node<T> partition(Node<T> head, T x) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> small = head;
        Node<T> root = head;
        Node<T> tmp;

        while (head.next != null && head.next.val.compareTo(x) < 0) {
            head = head.next;
        }
        small = head;
        while (head.next != null) {
            if (head.next.val.compareTo(x) < 0) {
                tmp = small.next;
                small.next = head.next;
                head.next = head.next.next;

                small.next.next = tmp;

                small = small.next;
            } else {
                head = head.next;
            }
        }
        if (small == root) {
            return root;
        }
        if (root.val.compareTo(x) >= 0) {
            tmp = root.next;
            root.next = small.next;
            small.next = root;
            root = tmp;
        }
        return root;
    }

    /**
     * Definition for singly-linked list insertion sort.
     */
    public Node<T> insertionSortList(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> rigth = head.next;
        Node<T> putElement;
        head.next = null;
        while (rigth != null) {
            putElement = rigth;
            rigth = rigth.next;
            putElement.next = null;
            if (head.val.compareTo(putElement.val) > 0) {
                putElement.next = head;
                head = putElement;
            } else {
                insert(head, putElement);
            }
        }
        return head;
    }

    public void insert(Node<T> head, Node<T> in) {

        while (head.next != null) {
            if (head.next.val.compareTo(in.val) >= 0) {
                in.next = head.next;
                head.next = in;
                return;
            }
            head = head.next;
        }
        head.next = in;
    }


    /**
     * Definition for singly-linked list sort : find min element en put frist position.
     */
    public Node<T> sortList(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> preMin = findPreMinElement(head);
        Node<T> min;
        if (preMin.next.val.compareTo(head.val) > 0) {
            min = head;
            head = head.next;
        } else {
            min = preMin.next;
            preMin.next = preMin.next.next;
        }
        min.next = sortList(head);
        return min;
    }

    //returned pre min Node without compare firs elemnt
    public Node<T> findPreMinElement(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> min = head;


        while (head.next != null) {
            if (head.next.val.compareTo(min.next.val) <= 0) {
                min = head;
            }
            head = head.next;
        }
        return min;
    }

    /**
     * End sort.
     */


    /**
     * Definition for singly-linked list merge sort.
     */
    public Node<T> mergSortortList(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> mid = partition(head);
        Node<T> tmp = mid;
        mid = mid.next;
        tmp.next = null;

        Node<T> left = mergSortortList(head);
        Node<T> rigth = mergSortortList(mid);

        return mergeRec(left, rigth);

    }

    public Node<T> mergeRec(Node<T> h1, Node<T> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        if (h1.val.compareTo(h2.val) > 0) {
            h2.next = mergeRec(h1, h2.next);
            return h2;
        }
        h1.next = mergeRec(h1.next, h2);
        return h1;
    }

    public Node<T> partition(Node<T> head) {
        if (head == null) {
            return null;
        }

        Node<T> fast = head.next;
        if (fast == null) {
            return head;
        }
        Node<T> slow = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    //Itaritive merge method
    public Node<T> merge(Node<T> head1, Node<T> head2) {
        Node<T> resHead = null;
        if (head1.val.compareTo(head2.val) > 0) {
            resHead = head2;
            head2 = head2.next;
        } else {
            resHead = head1;
            head1 = head1.next;
        }
        Node<T> currentNode = resHead;

        while (head1 != null && head2 != null) {
            if (head1.val.compareTo(head2.val) == 0) {
                currentNode.next = head1;
                currentNode.next.next = head2;
                currentNode = currentNode.next.next;
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.val.compareTo(head2.val) > 0) {
                currentNode.next = head2;
                currentNode = currentNode.next;
                head2 = head2.next;
            } else {
                currentNode.next = head1;
                currentNode = currentNode.next;
                head1 = head1.next;
            }
        }
        while (head1 != null) {
            currentNode.next = head1;
            currentNode = currentNode.next;
            head1 = head1.next;
        }
        while (head2 != null) {
            currentNode.next = head2;
            currentNode = currentNode.next;
            head2 = head2.next;
        }
        return resHead;
    }


    /**
     * End definition for singly-linked list merge sort.
     */


    public void add(List<T> c) {
        c.forEach(this::pushBack);
    }

    public boolean hasCycle(Node<T> head) {
        if (head == null) {
            return false;
        }
        Node<T> fast = head;
        Node<T> slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public Node<T>[] splitLinkedList(Node<T> head) {
        if (head == null || head.next == null) {
            return null;
        }
        int currentLength = 1;
        int leftLength = 1;
        Node<T> tmp = head;
        Node<T> res = head.next;
        while (head.next != null) {
            ++currentLength;
            if (currentLength / 2 > leftLength) {
                res = res.next;
                ++leftLength;
            }
        }
        return new Node[]{head, res};
    }


    public void pushBack(T val) {
        Node<T> newNode = new Node<>(val);
        Node<T> preLastNode = findPreLastNode();
        if (preLastNode == null) {
            head = newNode;
            return;
        }
        if (preLastNode.equals(head) && preLastNode.next == null) {
            preLastNode.next = newNode;
            return;
        }
        preLastNode.next.next = newNode;
    }

    public T popBack() {
        Node<T> preLastNode = findPreLastNode();
        if (preLastNode == null) {
            return null;
        }


        if (preLastNode.next == null) {
            T val = head.val;
            head = null;
            return val;
        }
        T val = preLastNode.next.val;
        preLastNode.next = null;
        return val;
    }

    public void pushFront(T val) {
        Node<T> newNode = new Node<>(val);
        newNode.next = head;
        head = newNode;
    }

    public T popFront() {
        T res = head.val;
        head = head.next;
        return res;
    }

    public boolean erase(int pos) {
        Node<T> preLastNode = findPreLastNode(pos);
        if (preLastNode == null || preLastNode.next == null) {
            return false;
        }
        if (pos == 0) {
            head = head.next;
            return true;
        }
        if (preLastNode.next.next == null) {
            preLastNode.next = null;
            return true;
        }
        preLastNode.next = preLastNode.next.next;
        return true;
    }

    public boolean insert(int pos, T val) {
        Node<T> preLastNode = findPreLastNode(pos);
        if (preLastNode == null) {
            return false;
        }
        Node<T> newNode = new Node<>(val);
        if (pos == 0) {
            newNode.next = head;
            head = newNode;
            return true;
        }
        newNode.next = preLastNode.next;
        preLastNode.next = newNode;
        return true;
    }


    public void reverse() {
        if (head != null && head.next != null) {
            Node<T> i = head;
            Node<T> j = head.next;
            Node<T> root = head.next.next;
            while (j.next != null) {
                j.next = i;
                i = j;
                j = root;
                root = root.next;
            }
            j.next = i;
            head.next = null;
            head = j;
        }
    }

    public Node<T> reverseRecurian(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
        reverseRecurian(head.next).next = head;
        return head;
    }

    public void clear() {
        head = null;
    }

    public void print() {
        print(head);
    }

    private void print(Node<T> head) {
        Node<T> tmpHead = head;
        while (tmpHead != null) {
            System.out.print(tmpHead.val + " ");
            tmpHead = tmpHead.next;
        }
        System.out.println();
    }

    public boolean empty() {
        return head == null;
    }

    public int size() {
        Node<T> tmpHead = head;
        int count = 0;
        while (tmpHead != null) {
            ++count;
        }
        return count;
    }

    //head == null -> null
    //head.next == null -> head;
    //all case -> preLastNode
    private Node<T> findPreLastNode() {
        Node<T> tmpHead = head;
        if (tmpHead == null) {
            return null;
        }
        if (tmpHead.next == null) {
            return head;
        }
        while (tmpHead.next.next != null) {
            tmpHead = tmpHead.next;
        }
        return tmpHead;
    }

    //pos < 0 || pos > lastIndex + 1 -> null
    //0 ,1 -> tmpphead
    //all case preNode
    private Node<T> findPreLastNode(int pos) {
        Node<T> tmpHead = head;
        if (tmpHead == null || pos < 0) {
            return null;
        }
        while (tmpHead.next != null && pos > 1) {
            --pos;
            tmpHead = tmpHead.next;
        }
        return pos >= 1 ? tmpHead : null;
    }

    private class Node<T> {
        private T val;
        private Node<T> next;

        public Node(T val) {
            this.val = val;
        }
    }

    private static class NodeInteger {
        private int val;
        private NodeInteger next;

        public NodeInteger(int val) {
            this.val = val;
        }
    }
}
