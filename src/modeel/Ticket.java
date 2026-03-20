package modeel;

public class Ticket {

    private String name;
    private String route;
    private String seat;
    private int price;
    private String payment;

    public Ticket(String name, String route, String seat, int price, String payment) {
        this.name = name;
        this.route = route;
        this.seat = seat;
        this.price = price;
        this.payment = payment;
    }

    public String getName() { return name; }
    public String getRoute() { return route; }
    public String getSeat() { return seat; }
    public int getPrice() { return price; }
    public String getPayment() { return payment; }
}