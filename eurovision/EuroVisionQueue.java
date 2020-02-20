package eurovision;

/**
 * A eurovision queue which holds potential customer in two parllel lines. One
 * according to priority and one according to order of apperance. At any given
 * time, a customer standing at the front of either line may be removed from the
 * data structure.
 * 
 * 
 * 
 *
 */
public class EuroVisionQueue {

    /*
     * The heap and queue which compose the data structure. The most important thing
     * to observe is: A customer element exists in the queue if and only if it also
     * exists in the heap.
     */
    CustomerHeap heap;
    CustomerQueue q;

    /**
     * Creates an empty eurovision queue
     */
    public EuroVisionQueue() {
        this.q = new CustomerQueue();
        this.heap = new CustomerHeap();
    }

    /**
     * Adds a new customer to the data structure. The customer is entered (wrapped
     * by a customer element) to the back of the queue and into the heap, according
     * to its priority.
     * 
     * @param c
     */
    public void addCustomer(Customer c) {
        CustomerElement cust = new CustomerElement(c);
        q.enqueue(cust);
        heap.insert(cust);

    }

    /**
     * Removes the customer with the highest priority from the data structure. The
     * customer must be removed both from the heap and the queue.
     * 
     * @return the customer with the highest priority.
     */
    public Customer servePriorityCustomer() {
        CustomerElement custom = heap.extractMax();
        if (custom == null)
            return null;
        Customer temp = custom.c;

        if (custom.prev != null && custom.next != null) {
            custom.prev = custom.next;
            custom.next.prev = custom.prev;
            return temp;
        }
        if (custom.prev == null && custom.next == null) {
            q.first = null;
            q.last = null;
            return temp;
        }
        if (custom.prev == null) {
            q.first = custom.next;
            q.first.prev = null;
            return temp;

        }
        if (custom.next == null) {
            q.last = custom.prev;
            q.last.next = null;
        }
        return temp;
    }

    /**
     * Removes the customer which arrived first to the data structure. The customer
     * must be removed both from the heap and the queue.
     * 
     * @return the customer which arrived first to the data structure
     */
    public Customer serveRegularCustomer() {
        CustomerElement custom = q.dequeue();
        if (custom != null) {
            heap.remove(custom.heapIndex);
            return custom.c;
        }
        return null;
    }

    public static void main(String[] args) {

        /*
         * A basic test to check your class. Expected outcome: customer: Liran
         * GreyShirt, priority: 20 customer: Netta Barzilay, priority: 10 customer:
         * Izhar Ashdot, priority: 2 customer: Alan Turing, priority: 100 customer: Dana
         * International, priority: 3
         * 
         */
        EuroVisionQueue q = new EuroVisionQueue();
        Customer a = new Customer(10, "Netta Barzilay");
        Customer b = new Customer(2, "Izhar Ashdot");
        Customer c = new Customer(3, "Dana International");
        Customer d = new Customer(20, "Liran GreyShirt");
        Customer e = new Customer(100, "Alan Turing");

        q.addCustomer(a);
        q.addCustomer(b);
        q.addCustomer(c);
        q.addCustomer(d);
        System.out.println(q.servePriorityCustomer());
        System.out.println(q.servePriorityCustomer());
        q.addCustomer(e);
        System.out.println(q.serveRegularCustomer());
        System.out.println(q.servePriorityCustomer());
        System.out.println(q.serveRegularCustomer());
    }
}
