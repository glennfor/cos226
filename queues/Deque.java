import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

// implementation of queue data structure
public class Deque<Item> implements Iterable<Item> {

    // node object for a doubly linked list
    private class Node {
        // hold value of type 'Type'
        Item value;

        // reference to previous element
        Node next;

        // reference to next element
        Node previous;

        // initialise a node object
        public Node(Item data) {
            value = data;
            next = null;
            previous = null;
        }
    }

    // iterator for traversing items in Deque in FIFO order
    private class DequeIterator implements Iterator<Item> {
        // keeps track of where the iterator is, in the linked list
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("reached end of iterable");
            Item data = current.value;
            current = current.next;
            return data;
        }
    }

    // reference to head of linked list container
    private Node head;

    // reference to tail of linked list container
    private Node tail;

    // number of items in queue
    private int size;

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        // technically if one is null, both should be null
        return (head == null) || (tail == null); // size < 1;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("element cannot be null.");

        Node newNode = new Node(item);
        ++size;
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            return;
        }
        newNode.next = head;
        head.previous = newNode;
        head = newNode;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("element cannot be null.");
        Node newNode = new Node(item);
        ++size;
        if (isEmpty()) {
            tail = newNode;
            head = newNode;
            return;
        }
        tail.next = newNode;
        newNode.previous = tail;
        tail = newNode;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannnot remove from empty deque");
        }
        --size;
        Item first = head.value;
        if (head == tail) {
            head = null;
            tail = null;
            return first;
        }
        head = head.next;
        if (head != null)
            head.previous = null;
        return first;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            String error = "cannnot remove from empty deque";
            throw new NoSuchElementException(error);
        }
        --size;
        Item last = tail.value;
        if (head == tail) {
            head = null;
            tail = null;
            return last;
        }
        tail = tail.previous;
        if (tail != null)
            tail.next = null;
        return last;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        StdOut.println("✓ [testing] Add + Remove Methods");
        Deque<Integer> dq = new Deque<>();

        StdOut.println("Empty? (Expect [True] ) = " + dq.isEmpty());

        dq.addFirst(1);
        dq.removeFirst();

        dq.addLast(12);
        dq.removeFirst();

        dq.addFirst(1);
        dq.addFirst(9);

        dq.removeLast();

        dq.addLast(2);
        dq.removeFirst();

        dq.removeLast();


        StdOut.print("Queue(" + dq.size() + ") - [ ");
        for (Integer v : dq) {
            System.out.print(v + " ");
        }
        StdOut.println("]");

    }

}
