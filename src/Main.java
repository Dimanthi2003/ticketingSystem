import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static volatile boolean isRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = null;

        // Step 1: Ask if the user wants to load configurations
        System.out.println("Do you want to load previous configurations? (yes/no):");
        String loadResponse = scanner.nextLine().trim().toLowerCase();

        if (loadResponse.equals("yes")) {
            config = ConfigManager.loadConfig();
            if (config != null) {
                System.out.println("\nLoaded Configuration:");
                System.out.println("+---------------------------+----------------------------+--------------------------------+-------------------------------+");
                System.out.println("| Total tickets             | Ticket release rate        | Customer retrieval rate        | Maximum Ticket capacity       | ");
                System.out.println("+---------------------------+----------------------------+--------------------------------+-------------------------------+");
                System.out.printf("| %-25d | %-25d  | %-25d      | %-25d     |\n",
                        config.getTotalTickets(),
                        config.getTicketReleaseRate(),
                        config.getCustomerRetrievalRate(),
                        config.getMaxTicketCapacity());
                System.out.println("+---------------------------+----------------------------+--------------------------------+-------------------------------+");

                System.out.println("Do you want to save a new configuration?");
                String res = scanner.nextLine().trim().toLowerCase();
                if (res.equals("yes")){
                    config = getNewConfig(scanner);
                }
                return;
            } else {
                System.out.println("No saved configurations found.");
                System.out.println("Do you want to save a new configuration?");
                String res = scanner.nextLine().trim().toLowerCase();
                if (res.equals("yes")){
                    config = getNewConfig(scanner);
                }
            }
        }else{
            config = getNewConfig(scanner);
        }

        // Step 2: Gather new configuration if no configuration was loaded
        if (config == null) {
            config = getNewConfig(scanner);
        }

        // Step 3: Start the system with the finalized configuration
        startTicketingSystem(config);
    }

    private static Configuration getNewConfig(Scanner scanner) {
        System.out.println("Enter total tickets to be released:");
        int totalTickets = scanner.nextInt();

        System.out.println("Enter ticket release rate (tickets/sec):");
        int ticketReleaseRate = scanner.nextInt();

        System.out.println("Enter customer retrieval rate (tickets/sec):");
        int customerRetrievalRate = scanner.nextInt();

        System.out.println("Enter maximum ticket capacity in the pool:");
        int maxTicketCapacity = scanner.nextInt();

        scanner.nextLine(); // Consume newline

        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        // Ask if the user wants to save the configuration
        System.out.println("Do you want to save the configuration? (yes/no):");
        String saveResponse = scanner.nextLine().trim().toLowerCase();

        if (saveResponse.equals("yes")) {
            ConfigManager.saveConfig(config);
            System.out.println("Configuration saved successfully.");
        } else {
            System.out.println("Configuration not saved.");
        }

        return config;
    }

    private static void startTicketingSystem(Configuration config) {
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity(), config.getTotalTickets());

        List<Thread> vendors = new ArrayList<>();
        List<Thread> customers = new ArrayList<>();

        for (int i = 0; i < 3; i++) { // Example: 3 vendors
            Thread vendorThread = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()));
            vendors.add(vendorThread);
            vendorThread.start();
        }

        for (int i = 0; i < 5; i++) { // Example: 5 customers
            Thread customerThread = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate()));
            customers.add(customerThread);
            customerThread.start();
        }

        System.out.println("System is running. Press Enter to terminate.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for termination signal

        isRunning = false; // Signal threads to stop

        // Wait for threads to terminate
        vendors.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Vendor thread interrupted.");
            }
        });
        customers.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Customer thread interrupted.");
            }
        });

        System.out.println("System terminated.");
    }

    public static boolean isRunning() {
        return isRunning;
    }
}
