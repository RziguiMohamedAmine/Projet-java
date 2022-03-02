package entite;

import java.util.Objects;

public class ItemSupport {
    private int productId;
    private int superSetCount;
    private int itemCount;
    private float confidence;
    private float lift;

    public ItemSupport(int productId) {
        this.productId = productId;
    }

    public float calculateConfidence(int subsetCount){
        confidence=(float)superSetCount/subsetCount;
        return confidence;
    }
    public float calculateLift(int totalOrdersNumbers){
        lift=confidence/((float)itemCount/totalOrdersNumbers);
        return lift;
    }

    public ItemSupport(int productId, int superSetCount) {
        this.productId = productId;
        this.superSetCount = superSetCount;
    }

    public int getProductId() {
        return productId;
    }

    public int getSuperSetCount() {
        return superSetCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemSupport)) return false;
        ItemSupport that = (ItemSupport) o;
        return productId == that.getProductId();
    }



    @Override
    public String toString() {
        return "\n\tItemSupport{" +
                "productId=" + productId +
                ", superSetCount=" + superSetCount +
                ", itemCount=" + itemCount +
                ", confidence=" + confidence +
                ", lift=" + lift +
                '}';
    }
}
