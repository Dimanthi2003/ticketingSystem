import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    private static final String CONFIG_FILE = "config.json";
    private static final Gson gson = new Gson();

    public static void saveConfig(Configuration config) {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(config, writer);
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save configuration: " + e.getMessage());
        }
    }

    public static Configuration loadConfig() {
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Failed to load configuration: " + e.getMessage());
            return null;
        }
    }

    public static void saveConfigToTextFile(Configuration config) {
        try (FileWriter writer = new FileWriter("config.txt")) {
            writer.write("totalTickets=" + config.getTotalTickets() + "\n");
            writer.write("ticketReleaseRate=" + config.getTicketReleaseRate() + "\n");
            writer.write("customerRetrievalRate=" + config.getCustomerRetrievalRate() + "\n");
            writer.write("maxTicketCapacity=" + config.getMaxTicketCapacity() + "\n");
            System.out.println("Configuration saved to text file.");
        } catch (IOException e) {
            System.out.println("Failed to save configuration: " + e.getMessage());
        }
    }

    public static Configuration loadConfigFromTextFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
            int totalTickets = Integer.parseInt(reader.readLine().split("=")[1]);
            int ticketReleaseRate = Integer.parseInt(reader.readLine().split("=")[1]);
            int customerRetrievalRate = Integer.parseInt(reader.readLine().split("=")[1]);
            int maxTicketCapacity = Integer.parseInt(reader.readLine().split("=")[1]);

            return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Failed to load configuration: " + e.getMessage());
            return null;
        }
    }


}
