package entite;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private float price;
    private String image;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(int id, String name, int quantity, float price , String image) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image=image;
    }


    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", image=" +image +
                '}';
    }
}
