package entite;
import java.util.Date;

public class Order {
    private int id;
    private int userId;
    private String state;
    private Date date;

    public Order(int id, int userId, String state, Date date) {
        this.id = id;
        this.userId = userId;
        this.state = state;
        this.date = date;
    }

    public Order(int userId, String state) {
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", userId=" + userId +
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
