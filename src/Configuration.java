import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }



//    public class ConfigManager {
//        private static final String CONFIG_FILE = "config.json";
//
//        private static final Gson gson = new Gson();
//
//        public static void saveConfig(Configuration config) {
//            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
//                gson.toJson(config, writer);
//                System.out.println("Configuration saved successfully.");
//            } catch (IOException e) {
//                System.out.println("Failed to save configuration: " + e.getMessage());
//}
//}
//    }
}
