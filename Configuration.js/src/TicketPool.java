import java.util.concurrent.atomic.AtomicInteger;

public class TicketPool {
    private final int maxCapacity;
    private AtomicInteger currentTickets;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentTickets = new AtomicInteger(0);
    }

    // Synchronized method to add tickets safely
    public synchronized void addTickets(int numberOfTickets) {
        if (currentTickets.get() + numberOfTickets <= maxCapacity) {
            currentTickets.addAndGet(numberOfTickets);
            System.out.println(numberOfTickets + " tickets added. Current total: " + currentTickets.get());
        } else {
            System.out.println("Cannot add tickets; max capacity reached.");
        }
    }

    public int getCurrentTickets() {
        return currentTickets.get();
    }
}
