package eurovision;

/**
 * A heap, implemnted as an array. The elemnts in the heap are instances of the
 * class CustomerElement, and the heap is ordered according to the Customer
 * instances wrapped by those objects.
 * 
 * IMPORTANT: Except in the constuctor no single function may loop/recurse
 * through all elements in the heap. You may only use loops which look at a
 * small fraction of the heap.
 * 
 *
 */
public class CustomerHeap {

    /*
     * The array in which the elements are kept according to the heap order. The
     * following must always hold true: if i < size then heap[i].heapIndex == i
     */
    CustomerElement[] heap;
    int size; // the number of elements in the heap, neccesarilty size <= heap.length

    /**
     * Creates an empty heap which can initally accomodate 10 elements.
     */
    public CustomerHeap() {
        this.heap = new CustomerElement[10];
        this.size = 0;

    }

    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size() {
        return this.size;
    }

    /**
     * Inserts a given element into the heap.
     *
     * @param e - the element to be inserted.
     */
    public void insert(CustomerElement e) {
        if (size == heap.length - 1) {
            CustomerElement[] temp = new CustomerElement[heap.length * 2];
            for (int i = 1; i < heap.length; i++) {
                temp[i] = heap[i];
            }
            this.heap = temp;
        }
        size++;
        heap[size] = e;
        heap[size].heapIndex = size;
        percUp(size);
    }

    /**
     * Returns and does not remove the element which wraps the cutomer with maximal
     * priority.
     * 
     * @return the element which wraps the cutomer with maximal priority.
     */
    public CustomerElement findMax() {
        return heap[1];
    }

    /**
     * Returns and removes the element which wraps the customer with maximal
     * priority.
     * 
     * @return the element which wraps the customer with maximal priority.
     */
    public CustomerElement extractMax() {
        CustomerElement temp = this.findMax();
        if (temp != null) {
            this.remove(1);
        }
        return temp;
    }

    /**
     * Removes the element located at the given index.
     * 
     * Note: this function is not part of the standard heap API. Make sure you
     * understand how to implement it why it is required. There are several ways
     * this function could be implemented. No matter how you choose to implement it,
     * you need to consider the different possible edge cases.
     * 
     * @param index
     */
    public void remove(int index) {
        if (index > size || index < 1) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            heap[index] = null;
            size--;
            return;
        }
        heap[index] = heap[size];
        heap[index].heapIndex = index;
        size--;
        percUp(index);
        percDown(index);
    }

    /**
     * make an orded in the heap after remove element , to make sure that every node
     * is in the right place.
     * 
     * @param i
     */

    public void percDown(int i) {
        if (i * 2 > this.size())
            return;
        if (i * 2 == this.size()) {
            if (heap[i].c.compareTo(heap[i * 2].c) < 0) {
                CustomerElement temp1 = heap[i * 2];
                heap[i * 2] = heap[i];
                heap[i] = temp1;
                heap[i * 2].heapIndex = i * 2;
                heap[i].heapIndex = i;
            }
            return;
        }
        if (heap[i * 2].c.compareTo(heap[(i * 2) + 1].c) > 0) {
            if (heap[i].c.compareTo(heap[i * 2].c) < 0) {
                CustomerElement temp2 = heap[i];
                heap[i] = heap[i * 2];
                heap[i * 2] = temp2;
                heap[i].heapIndex = i;
                heap[i * 2].heapIndex = i * 2;
                percDown(i * 2);
            }
        }
        if (heap[(i * 2) + 1].c.compareTo(heap[i * 2].c) > 0) {
            if (heap[i].c.compareTo(heap[(i * 2) + 1].c) < 0) {
                CustomerElement temp3 = heap[i];
                heap[i] = heap[(i * 2) + 1];
                heap[(i * 2) + 1] = temp3;
                heap[i].heapIndex = i;
                heap[i * 2 + 1].heapIndex = i * 2 + 1;
                percDown((i * 2) + 1);
            }
        }
        return;
    }

    /**
     * make an orded in the heap after add new element, to make sure that every node
     * is in the right place.
     * 
     * @param i
     */

    public void percUp(int i) {
        if (i == 1)
            return;
        if (heap[i].c.compareTo(heap[i / 2].c) > 0) {
            CustomerElement temp = heap[i / 2];
            heap[i / 2] = heap[i];
            heap[i] = temp;
            heap[i / 2].heapIndex = i / 2;
            heap[i].heapIndex = i;
            percUp(i / 2);
        } else
            return;
    }

    public static void main(String[] args) {
        /*
         * A basic test for the heap. You should be able to run this before implementing
         * the queue.
         * 
         * Expected outcome:
         * customer: Netta, priority: 10 
         * customer: Liran, priority: 20
         * customer: Liran, priority: 20 
         * customer: Netta, priority: 10 
         * customer: Dana, priority: 3 
         * customer: Izhar, priority: 2
         * 
         * 
         */
        CustomerHeap heap = new CustomerHeap();
        Customer a = new Customer(10, "Netta");
        Customer b = new Customer(2, "Izhar");
        Customer c = new Customer(3, "Dana");
        Customer d = new Customer(20, "Liran");

        heap.insert(new CustomerElement(a));
        System.out.println(heap.findMax());

        heap.insert(new CustomerElement(b));
        heap.insert(new CustomerElement(c));
        heap.insert(new CustomerElement(d));
        System.out.println(heap.findMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
    }
}
