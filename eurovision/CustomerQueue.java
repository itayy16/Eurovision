package eurovision;

/**
 * A queue class, implemented as a linked list. The nodes of the linked list are
 * implemnted as the CustomerElement class.
 * 
 * IMPORTANT: you may not use any loops/recursions in this class.
 */
public class CustomerQueue {

    CustomerElement first;
    CustomerElement last;

    /**
     * Constructs an empty queue
     */
    public CustomerQueue() {
        first = null;
        last = null;
    }

    /**
     * Removes and returns the first element in the queue
     * 
     * @return the first element in the queue
     */
    public CustomerElement dequeue() {
        if (first == null) {
            return null;
        }
        if (first == last) {
            CustomerElement temp1 = first;
            first = null;
            last = null;
            return temp1;
        }
        CustomerElement temp2 = first;
        first = first.next;
        first.prev = null;
        return temp2;
    }

    /**
     * Returns and does not remove the first element in the queue
     * 
     * @return the first element in the queue
     */
    public CustomerElement peek() {
        return first;
    }

    /**
     * Adds a new element to the back of the queue
     * 
     * @param node
     */
    public void enqueue(CustomerElement node) {
        if (first == null) {
            first = node;
            last = node;
            return;
        }
        if (first == last) {
            last.next = node;
            node.prev = last;
            last = last.next;
            first.next = last;
            return;
        }
        last.next = node;
        node.prev = last;
        last = last.next;
    }

}
