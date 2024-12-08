public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (!ticketPool.isConsumptionComplete() && Main.isRunning()) {
            try {
                String ticket = ticketPool.removeTicket();
                if (ticket == null) {
                    break; // No more tickets to consume
                }
                Thread.sleep(1000 / customerRetrievalRate);
            } catch (InterruptedException e) {
                System.out.println("Customer interrupted.");
                break;
            }
        }
        System.out.println("Customer stopped.");
    }
}
