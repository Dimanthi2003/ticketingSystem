import java.util.Scanner;
import java.io.*;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Constructor
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Method to initialize Configuration from user input
    public static Configuration initializeFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int totalTickets = getValidIntInput(scanner, "Enter total number of tickets: ");
        int ticketReleaseRate = getValidIntInput(scanner, "Enter ticket release rate: ");
        int customerRetrievalRate = getValidIntInput(scanner, "Enter customer retrieval rate: ");
        int maxTicketCapacity = getValidIntInput(scanner, "Enter maximum ticket capacity: ");

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }

    // Helper method for validating integer input
    private static int getValidIntInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value > 0) {
                    break;
                }
            } else {
                scanner.next();  // discard invalid input
            }
            System.out.println("Invalid input. Please enter a positive integer.");
        }
        return value;
    }

    // Save Configuration to JSON file
//    public void saveToJson(String filename) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        try (FileWriter writer = new FileWriter(filename)) {
//            gson.toJson(this, writer);
//        } catch (IOException e) {
//            System.out.println("Failed to save configuration to JSON: " + e.getMessage());
//        }
//    }
//
//    // Load Configuration from JSON file
//    public static Configuration loadFromJson(String filename) {
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader(filename)) {
//            return gson.fromJson(reader, Configuration.class);
//        } catch (IOException e) {
//            System.out.println("Failed to load configuration from JSON: " + e.getMessage());
//            return null;
//        }
//    }

    // Save Configuration to plain text file
//    public void saveToTextFile(String filename) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
//            writer.write("Total Tickets: " + totalTickets + "\n");
//            writer.write("Ticket Release Rate: " + ticketReleaseRate + "\n");
//            writer.write("Customer Retrieval Rate: " + customerRetrievalRate + "\n");
//            writer.write("Maximum Ticket Capacity: " + maxTicketCapacity + "\n");
//        } catch (IOException e) {
//            System.out.println("Failed to save configuration to text file: " + e.getMessage());
//        }
//    }
//
//    // Load Configuration from plain text file
//    public static Configuration loadFromTextFile(String filename) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            int totalTickets = Integer.parseInt(reader.readLine().split(": ")[1]);
//            int ticketReleaseRate = Integer.parseInt(reader.readLine().split(": ")[1]);
//            int customerRetrievalRate = Integer.parseInt(reader.readLine().split(": ")[1]);
//            int maxTicketCapacity = Integer.parseInt(reader.readLine().split(": ")[1]);
//            return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
//        } catch (IOException | NumberFormatException e) {
//            System.out.println("Failed to load configuration from text file: " + e.getMessage());
//            return null;
//        }
//    }
//}




