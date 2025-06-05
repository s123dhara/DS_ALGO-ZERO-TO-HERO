import java.util.*;

class Node<T> {
    T pair;
    Node<T> next;
    Node<T> prev;

    public Node() {
    }

    public Node(T pair) {
        this.pair = pair;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;

    public DoublyLinkedList() {
    }

    private boolean isEmpty() {
        return head == null || tail == null;
    }

    public Node<T> addLast(T pair) {
        Node<T> newNode = new Node<>(pair);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        return newNode;
    }

    public Node<T> removeFirst() {
        if (!isEmpty()) {
            Node<T> temp = head;
            if (head.next == null) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            return temp;
        }
        return null;
    }

    public Node<T> moveToEnd(Node<T> node) {
        if (node == tail)
            return tail;

        // Detach
        if (node == head) {
            head = node.next;
            if (head != null)
                head.prev = null;
        } else {
            node.prev.next = node.next;
            if (node.next != null)
                node.next.prev = node.prev;
        }

        // Attach at end
        node.next = null;
        node.prev = tail;
        if (tail != null)
            tail.next = node;
        tail = node;

        return tail;
    }
}

class LRUCache {

    static class Pair {
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private DoublyLinkedList<Pair> doublyLinkedList;
    private HashMap<Integer, Node<Pair>> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        doublyLinkedList = new DoublyLinkedList<>();
        map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node<Pair> node = map.get(key);
        doublyLinkedList.moveToEnd(node);
        return node.pair.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node<Pair> node = map.get(key);
            node.pair.value = value;
            doublyLinkedList.moveToEnd(node);
        } else {
            if (map.size() >= capacity) {
                Node<Pair> removed = doublyLinkedList.removeFirst();
                if (removed != null) {
                    map.remove(removed.pair.key);
                }
            }
            Node<Pair> newNode = doublyLinkedList.addLast(new Pair(key, value));
            map.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */