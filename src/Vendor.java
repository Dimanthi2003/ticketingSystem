public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        int ticketCount = 1;
        while (!ticketPool.isProductionComplete() && Main.isRunning()) {
            try {
                ticketPool.addTickets("Ticket-" + ticketCount++);
                Thread.sleep(1000 / ticketReleaseRate);
            } catch (InterruptedException e) {
                System.out.println("Vendor interrupted.");
                break;
            }
        }
        System.out.println("Vendor stopped.");
    }
}
