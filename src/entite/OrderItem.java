package entite;

public class OrderItem {
    private int id;
    private Order order; //ORDER
    private produit produit;//PRODUCT
    private int quantity;

    public OrderItem(int id, Order order, produit produit, int quantity) {
        this.id = id;
        this.order = order;
        this.produit = produit;
        this.quantity = quantity;
    }

    public OrderItem(Order order, produit produit, int quantity) {
        this.order = order;
        this.produit = produit;
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

    public produit getProduct() {
        return produit;
    }

    public void setProduct(produit produit) {
        this.produit = produit;
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
                ", productId=" + produit +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return  order.equals(orderItem.getOrder()) && produit.equals(orderItem.getProduct() ) ;
    }
}
