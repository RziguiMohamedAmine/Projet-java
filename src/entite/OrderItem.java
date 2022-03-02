package entite;

public class OrderItem {
    private int id;
    private Order order; //ORDER
    private Product product;//PRODUCT
    private int quantity;

    public OrderItem(int id, Order order, Product product, int quantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItem(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void incrementQuantity(){
        quantity++;
    }
    public void decrementQuantity(){
        quantity--;
    }

    @Override
    public String toString() {
        return "OderItem{" +
                "id=" + id +
                ", orderId=" + order +
                ", productId=" + product +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return  order.equals(orderItem.getOrder()) && product.equals(orderItem.getProduct() ) ;
    }
}
