import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // iterator for traversing items in RandomizedQueue
    private class RandomizedQueueIterator implements Iterator<Item> {

        // keeps track of where the iterator is, in the underlying array
        private int counter = 0;

        // iterator's defensive copy for independent traversal
        private Item[] containerCopy;

        // create an iterator
        public RandomizedQueueIterator() {
            counter = 0;
            containerCopy = (Item[]) new Object[pointer + 1];
            for (int i = 0; i <= pointer; i++)
                containerCopy[i] = container[i];
        }

        public boolean hasNext() {
            return counter <= pointer;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("reached end of iterable");

            int index = StdRandom.uniformInt(counter, pointer + 1);
            Item picked = containerCopy[index];
            containerCopy[index] = containerCopy[counter];
            containerCopy[counter] = picked;
            counter++;
            return picked;
        }
    }

    // minimum size of the queue
    private static final int MIN_SIZE = 10;

    // container for holding items of the queue
    private Item[] container;

    // current capacity of the queue container
    private int capacity;

    // pointer to the last element in the queue (stack-like POV)
    // pointer can be used to know size of queue
    private int pointer;

    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = MIN_SIZE;
        pointer = -1;
        container = (Item[]) new Object[capacity];
    }

    // resize the random queues container
    private void resize(int newCapacity) {

        if (newCapacity <= MIN_SIZE)
            return;
        Item[] copy = (Item[]) new Object[newCapacity];

        for (int i = 0; i <= pointer; i++)
            copy[i] = container[i];

        container = copy;

        capacity = newCapacity;

    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return pointer < 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return pointer + 1;
    }

    // add the item
    public void enqueue(Item item) {

        if (item == null)
            throw new IllegalArgumentException("element cannot be null.");

        if (size() == capacity)
            resize(capacity * 2);
        container[++pointer] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("cannot dequeue from empty queue");

        if (size() <= capacity / 4)
            resize(capacity / 2);

        // find a random index of the container
        int index = StdRandom.uniformInt(pointer + 1);

        // and return the data stored there
        Item data = container[index];

        container[index] = container[pointer];
        container[pointer] = null;
        --pointer;
        return data;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot sample from empty queue");
        }

        int index = StdRandom.uniformInt(pointer + 1);
        return container[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        StdOut.println("[Test] enqueue + dequeue + sample");
        StdOut.println("Emptty? (Expect True) = " + rq.isEmpty());

        rq.enqueue("Code");
        rq.enqueue("Create");
        rq.enqueue("Collaborate");
        StdOut.println("Size (expect 3) = " + rq.size());

        StdOut.println("Sample: " + rq.sample());

        rq.dequeue();
        StdOut.println("[Test] iterator");
        StdOut.print("R-Queue(" + rq.size() + ") - [ ");
        for (String s : rq) {
            StdOut.print(s + " ");
        }
        StdOut.print("]\n");

        StdOut.println("[Test] Testing randomness of the iterator");

        RandomizedQueue<Integer> rq2 = new RandomizedQueue<>();

        rq2.enqueue(1);
        rq2.enqueue(2);
        rq2.enqueue(3);
        rq2.enqueue(4);
        rq2.enqueue(5);

        for (int i = 0; i < 5; i++) {
            StdOut.print("Round " + (i + 1) + ": [ ");
            for (Integer it : rq2) {
                StdOut.print(it + " ");
            }
            StdOut.print(" ]\n");
        }

    }

}
