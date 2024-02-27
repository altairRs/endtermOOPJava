package houserenting;
public class Home {
    private int id;
    private String address;
    private double price;

    public Home(int id, String adress, double price){
        this.id = id;
        this.address = adress;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}


