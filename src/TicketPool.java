import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<String> tickets = new LinkedList<>();
    private final int maxCapacity;
    private final int totalTickets;
    private int producedTickets = 0;
    private int consumedTickets = 0;

    public TicketPool(int maxCapacity, int totalTickets) {
        this.maxCapacity = maxCapacity;
        this.totalTickets = totalTickets;
    }

    public synchronized void addTickets(String ticket) throws InterruptedException {
        while (tickets.size() >= maxCapacity || producedTickets >= totalTickets) {
            if (producedTickets >= totalTickets) {
                return; // Stop adding tickets
            }
            wait();
        }
        tickets.add(ticket);
        producedTickets++;
        System.out.println("Ticket added: " + ticket + " | Current pool size: " + tickets.size());
        notifyAll();
    }

    public synchronized String removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            if (consumedTickets >= totalTickets) {
                return null; // No more tickets to consume
            }
            wait();
        }
        String ticket = tickets.poll();
        consumedTickets++;
        System.out.println("Ticket purchased: " + ticket + " | Current pool size: " + tickets.size());
        notifyAll();
        return ticket;
    }

    public boolean isProductionComplete() {
        return producedTickets >= totalTickets;
    }

    public boolean isConsumptionComplete() {
        return consumedTickets >= totalTickets;
    }
}
