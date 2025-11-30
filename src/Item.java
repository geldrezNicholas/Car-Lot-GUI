//Abstract parent class of Vehicle and Tire
public abstract class Item implements Comparable<Item> {
    //states
    private double price;
    private int invQuantity;
    private int soldQuantity;

    //constructor
    public Item(double price, int invQuantity){
        this.price = price;
        this.invQuantity = invQuantity;
        this.soldQuantity = 0;
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof Item anotherItem){
            return(this.toString().equals(anotherItem.toString()));
        }
        return false;

    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(Item o) {
        int result = o.getSoldQuantity() - this.getSoldQuantity(); // descending
        if (result != 0) return result;
        return this.toString().compareTo(o.toString()); // break ties
    }

    //getters
    public double getPrice(){return price;}
    public int getInvQuantity(){return invQuantity;}
    public int getSoldQuantity(){return soldQuantity;}

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public void setInvQuantity(int invQuantity) {
        this.invQuantity = invQuantity;
    }
}
