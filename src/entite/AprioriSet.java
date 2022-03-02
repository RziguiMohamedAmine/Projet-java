package entite;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class AprioriSet {
    private int subsetCount; //Actual Cart Items
    private int totalOrdersNumber;
    private List<ItemSupport> superSet;

    public AprioriSet() {
        superSet=new ArrayList<>();
    }

    public int getSubsetCount() {
        return subsetCount;
    }

    public void setSubsetCount(int subsetCount) {
        this.subsetCount = subsetCount;
    }

    public List<ItemSupport> getSuperSet() {
        return superSet;
    }


    public void setTotalOrdersNumber(int totalOrdersNumber) {
        this.totalOrdersNumber = totalOrdersNumber;
    }
    public void calculateConfidences(){
        Iterator<ItemSupport> iter = superSet.iterator();
        while (iter.hasNext()) {
            ItemSupport i = iter.next();
            if (i.calculateConfidence(subsetCount)<0.5);
                //iter.remove();
        }
    }
    public void calculateLifts(){
        Iterator<ItemSupport> iter = superSet.iterator();
        while (iter.hasNext()) {
            ItemSupport i = iter.next();
            if (i.calculateLift(totalOrdersNumber)<1);
                //iter.remove();
        }
    }
    public void filterProducts(){
        calculateConfidences();
        calculateLifts();
    }

    @Override
    public String toString() {
        return "{" +
                "subsetCount=" + subsetCount +
                ", superSet=" + superSet +
                '}';
    }
}
