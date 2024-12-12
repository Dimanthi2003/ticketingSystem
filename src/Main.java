import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static volatile boolean isRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = null;

        // Ask if the user wants to load configurations
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

        // Gather new configuration if no configuration was loaded
        if (config == null) {
            config = getNewConfig(scanner);
        }

        //Start the system with the finalized configuration
        startTicketingSystem(config);
    }

    private static Configuration getNewConfig(Scanner scanner) {
        int totalTickets = 0;

        while (true) {
            System.out.println("Enter total tickets to be released (between 1 and 10 00):");

            try {
                totalTickets = Integer.parseInt(scanner.nextLine());
                if (totalTickets < 1 || totalTickets > 1000) {
                    System.out.println("Invalid input! Should be a value between 1 to 1000)");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value");
            }
        }

        int ticketReleaseRate = 0;

        while (true) {
            System.out.println("Enter ticket release rate in milliseconds (between 1 and 2000):");
            try {
                ticketReleaseRate = Integer.parseInt(scanner.nextLine());
                if (ticketReleaseRate < 1 || ticketReleaseRate > 2000) {
                    System.out.println("Invalid input! Should be a value between 1 to 2000");
                }else{
                    break;
                }
            }catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value");
            }
        }

        System.out.println("Enter customer retrieval rate in milliseconds(must match ticket release rate):");
        int customerRetrievalRate = scanner.nextInt();
        while (customerRetrievalRate != ticketReleaseRate) {
            System.out.println("Customer retrieval rate must match the ticket release rate. Please try again:)");
            customerRetrievalRate = scanner.nextInt();
        }

        System.out.println("Enter maximum ticket capacity in the pool( must be more than total tickets)");
        int maxTicketCapacity = scanner.nextInt();
        while (maxTicketCapacity <= ticketReleaseRate) {
            System.out.println("Maximum ticket capacity must be more than total tickets.Please try again:");
            maxTicketCapacity = scanner.nextInt();
        }

       scanner.nextLine(); // Consume newline

        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        //Ask if the user wants to save the configuration
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
