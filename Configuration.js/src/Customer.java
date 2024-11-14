public class Customer implements Runnable {
    private TicketPool ticketPool;
    private int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < this.customerRetrievalRate; i++) {
                if (ticketPool.removeTicket()){
                    System.out.println(Thread.currentThread().getName() + ": purchased a ticket.");
                } else{
                    System.out.println(Thread.currentThread().getName() + ": found no tickets available.");
                    return;
                }
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + ": was interrupted.");
                break;
            }
        }
    }


}
