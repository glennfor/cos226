Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */

Randomized Queue:

For the Randomized Queue, I choose an array as the underlying datatype as the
constant-time access of the elements will be suitable for multiple random
accesses.

The defingin characteristice of this Randomized Queue are the
random traversal, random dequeue and sampling, and resizing the underlying
array

For the random traversal, I simply made a copy of the array for the iterator,
and then progressively swap index at each position with the elements after
it and return it, to make sure no item is repeated while maintaining randomness.

For the dequeue, I used the same idea. Getting a random element, returning it
and replacin it with the last item in the list to maintain randomness

For the sampling, I just choose a random item from the array and return

I also created a helper resize method to be called to shrink or expand the
underlying array as items are removed or added when it is full or too empty.

Deque:

For the queue data structure, I choose a Doubly-list as the underlying
container for the data structure, since addtions and deletions to both ends
is convienient and achievable in constant time, in alignment with the
defining characteristics of a queue.







/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  16n + 24 bytes
    •container array with n items, has
        - overhead  = 16
        - contained refences each of 8 bytes
        Giving a total of 8n + 16 memory size for the container
   •capacity = 4 bytes
   •pointer  = 4 bytes

   In total we have, 8n + 24.

   But in the worst case, we can store n items, and immediately resize
   the array and not store any more items. Then, the array will be twice the
   number of items and the container occupies 16n + 16;


Deque:              ~  24n + 4  bytes
   •A linkedlist of size n, has n nodes.
    A node has 3 references (data, next, previous) each 8 bytes
    so the linkedlist occupies n•3•8 = 24n bytes
   •pointer occupies 4 bytes (as an int)


Deque


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
