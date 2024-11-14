//import java.util.*;
//import java.io.*;
//
//
//public class Main {
//    private static final int MAX_CAPACITY = 10000;
//    private static Ticket[] tickets = new Ticket[MAX_CAPACITY];
//    private static int ticketCount = 0;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String choice;
//
//        initialize();
//
//        do{
//            displayMenu();
//            choice = input.nextLine().toUpperCase();
//
//            switch(choice) {
//                case "1":
//                    totalTickets();
//                    break;
//                case "2":
//                    ticketReleaseRate();
//                    break;
//                case "3":
//                    customerRateReview();
//                    break;
//                case "4":
//                    maxTicketCapacity();
//                    break;
//                case "5":
//                    System.out.println("Goodbye!");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//
//            }
//
//        }while (! choice.equals("5"));
//
//        input.close();
//
//    }
//
//    private static void initialise(){
//        Arrays.fill(tickets,null);
//        System.out.println("System initialised.");
//    }
//    private static void displayMenu() {
//        System.out.println("\n---Main Menu---");
//        System.out.println("1. Set Total Number of Tickets");
//        System.out.println("2. Set Ticket Release Rate");
//        System.out.println("3. Set Customer Retrieval Rate");
//        System.out.println("4. Set Maximum Tickets Capacity");
//        System.out.println("5. Exit");
//        System.out.println("Enter your choice: ");
//    }
//
//    private static void totalTickets() {
//        int totalNoTickets = MAX_CAPACITY - ticketCount;
//        System.out.println("Total Tickets: " + totalNoTickets);
//
//    }
//    private static void ticketReleaseRate() {
//        if (ticketCount < MAX_CAPACITY) {
//
//        }
//    }
//}


import java.util.*;

public class Main {
    private static final int MAX_CAPACITY = 10000; // Maximum number of tickets that can be held
    private static Ticket[] tickets = new Ticket[MAX_CAPACITY]; // Array to store tickets
    private static int ticketCount = 0; // Current count of tickets
    private static int ticketReleaseRate = 0; // Tickets released per interval
    private static int customerRetrievalRate = 0; // Tickets retrieved by customers per interval
    private static int maxTicketCapacity = MAX_CAPACITY; // Adjustable maximum capacity

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        initialize(); // Initialize ticket array

        do {
            displayMenu();
            choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "1":
                    setTotalTickets(scanner);
                    break;
                case "2":
                    setTicketReleaseRate(scanner);
                    break;
                case "3":
                    setCustomerRetrievalRate(scanner);
                    break;
                case "4":
                    setMaxTicketCapacity(scanner);
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("5"));

        scanner.close();
    }

    // Initialize ticket system
    private static void initialize() {
        Arrays.fill(tickets, null); // Clear ticket array
        ticketCount = 0;
        System.out.println("System initialized.");
    }

    // Display main menu
    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Set Total Number of Tickets");
        System.out.println("2. Set Ticket Release Rate");
        System.out.println("3. Set Customer Retrieval Rate");
        System.out.println("4. Set Maximum Ticket Capacity");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Option 1: Set the total number of tickets
    private static void setTotalTickets(Scanner scanner) {
        System.out.print("Enter the total number of tickets: ");
        int totalTickets = getValidIntInput(scanner);
        if (totalTickets <= maxTicketCapacity) {
            ticketCount = totalTickets;
            System.out.println("Total tickets set to: " + ticketCount);
        } else {
            System.out.println("Error: Exceeds maximum capacity of " + maxTicketCapacity);
        }
    }

    // Option 2: Set the ticket release rate
    private static void setTicketReleaseRate(Scanner scanner) {
        System.out.print("Enter the ticket release rate: ");
        ticketReleaseRate = getValidIntInput(scanner);
        System.out.println("Ticket release rate set to: " + ticketReleaseRate + " tickets per interval.");
    }

    // Option 3: Set the customer retrieval rate
    private static void setCustomerRetrievalRate(Scanner scanner) {
        System.out.print("Enter the customer retrieval rate: ");
        customerRetrievalRate = getValidIntInput(scanner);
        System.out.println("Customer retrieval rate set to: " + customerRetrievalRate + " tickets per interval.");
    }

    // Option 4: Set the maximum ticket capacity
    private static void setMaxTicketCapacity(Scanner scanner) {
        System.out.print("Enter the maximum ticket capacity: ");
        maxTicketCapacity = getValidIntInput(scanner);
        if (ticketCount > maxTicketCapacity) {
            ticketCount = maxTicketCapacity; // Adjust current ticket count if it exceeds the new max capacity
        }
        System.out.println("Maximum ticket capacity set to: " + maxTicketCapacity);
    }

    // Helper method to ensure valid integer input
    private static int getValidIntInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (value > 0) {
                    return value;
                }
            } else {
                scanner.next(); // Consume invalid input
            }
            System.out.print("Invalid input. Please enter a positive integer: ");
        }
    }
}
