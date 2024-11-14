public class Ticket{
    private static int counter = 0;
    private final int id;
    private String status;

    public Ticket(){
        this.id = counter++;
        this.status = "Available";
    }
    public int getId() {return id;}
    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", status=" + status + "]";
    }
}


