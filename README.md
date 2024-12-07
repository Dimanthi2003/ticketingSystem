# Real-Time Event Ticketing System

## 📖 Project Overview
The *Real-Time Event Ticketing System* simulates a dynamic ticketing environment using the *Producer-Consumer pattern* with multi-threading in Java. It allows concurrent ticket releases by vendors and purchases by customers while maintaining data integrity in a shared ticket pool. The system also supports saving and loading configurations for future reuse.

---

## 🔧 Features
- *Concurrency Management*: Implements multi-threading with thread-safe operations to handle ticket releases and purchases simultaneously.
- *Producer-Consumer Pattern*: Vendors act as producers adding tickets, and customers act as consumers retrieving tickets.
- *Persistent Configuration Management*: Save and load multiple configurations, displaying them in a structured table format.
- *Customizable Parameters*: User-defined settings for total tickets, ticket release rate, customer retrieval rate, and maximum ticket capacity.
- *Graceful Shutdown*: Ensures all threads terminate safely when the system stops.

---

## 📂 Project Structure
- **Main**: Entry point for the application. Manages user input, configuration handling, and thread management.
- **Configuration**: Encapsulates user-defined settings for the system.
- **TicketPool**: Thread-safe shared resource for managing ticket storage.
- **Vendor**: Represents ticket producers (vendors).
- **Customer**: Represents ticket consumers (customers).
- **ConfigManager**: Handles saving and loading configurations from a JSON file.

---

## 🚀 How to Run
1. *Clone the Repository:*
   ```bash
   git clone https://github.com/<your-username>/real-time-ticketing-system.git
   cd real-time-ticketing-system