public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate; // Number of tickets released at a time

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int releaseInterval) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    // Define vendor logic in the run() method
    @Override
    public void run() {
        while (true) {
                ticketPool.addTickets(ticketReleaseRate);
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                // Wait for the next release
            } catch (InterruptedException e) {
                System.out.println("Vendor interrupted: " + e.getMessage());
                break;
            }
        }
    }
}
