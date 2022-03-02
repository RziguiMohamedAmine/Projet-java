package entite;
import java.util.Date;

public class Order {
    private int id;
    private User user;  //USER
    private String state;
    private Date date;

    public Order(int id) {
        this.id = id;
    }

    public Order(int id, User user, String state, Date date) {
        this.id = id;
        this.user = user;
        this.state = state;
        this.date = date;
    }

    public Order(User user, String state) {
        this.user = user;
        this.state = state;
    }
    public Order(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + user +
                ", state='" + state + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId();
    }



}
