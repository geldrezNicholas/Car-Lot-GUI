import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

public class AutoPark {
    //states
    private String name;
    private double revenue;
    private ArrayList<Item> items;
    private HashMap<Item, Integer> cart;
    private int uniqueSales;
    private int cartSales;
    private ArrayList<Item> soldItemList;

    //constructor
    public AutoPark(String name) {
        this.name = name;
        revenue = 0;
        items = new ArrayList<>();
        cart = new HashMap<>();
        uniqueSales = 0;
        cartSales = 0;
        soldItemList = new ArrayList<>();

    }

    public String getName() {
        return name;
    }
    public int getCartSales(){
        return cartSales;
    }
    public double getRevenue(){
        return revenue;
    }
    public HashMap<Item, Integer> getCart(){
        return cart;
    }


    //adds an item if there is space available in the array
    //this was used for last assignment
    public boolean addAnItem(Item item) {
        for (Item i : items) {
            if (item.equals(i)) {
                return false;
            }
        }
        items.add(item);
        return true;
    }

    //similar to last assignment, but added more
    public ArrayList<Item> getXPopularItems() {

        //This method ensures that if there are 0 sales, it will show the first 3 items in items(not sortedPopularItems)
        //If there is 1 unique sale, one item will show, if there are 2 unique sales, two items will show
        //and anything after that, maximum 3 items will show.

        ArrayList<Item> popular = new ArrayList<>();
        TreeSet<Item> sortedPopularList = new TreeSet<>(items); //sort by sales if there are sales
        if (uniqueSales == 0) {    //no sales are done, this is only used at the start
            int count = 0;
            for (Item i : items) {
                if (count >= 3) { // makes sure it adds the proper amount of items
                    break;        // by breaking before too many items are added
                }
                popular.add(i);
                count++;
            }
        }else if(uniqueSales == 1 || uniqueSales == 2){
            int count = 0;
            for (Item i : sortedPopularList) {
                if (count >= uniqueSales) { // makes sure it adds the proper amount of items
                    break;               // by breaking before too many items are added
                }
                popular.add(i);
                count++;
            }
        }else{
            int count = 0;
            for (Item i : sortedPopularList) {
                if (count >= 3) {       // makes sure it adds only 3 items
                    break;               // by breaking before too many items are added
                }
                popular.add(i);
                count++;
            }
        }
        return popular;
    }


    public static AutoPark createPark() {
        AutoPark park = new AutoPark("VroomVille Vehicle Haven");
        Sedan s1 = new Sedan("Hyundai", "Sonata", "Black", 2020, 35000, 10);
        Sedan s2 = new Sedan("BMW", "3 Series", "White", 2022, 42000, 10);
        park.addAnItem(s1);
        park.addAnItem(s2);
        SUV suv1 = new SUV("Chevy", "Trailblazer", "Red", 2021, true,32000,10);
        SUV suv2 = new SUV("Jeep", "Grand Cherokee", "Green", 2018, false,21000,10);
        park.addAnItem(suv1);
        park.addAnItem(suv2);
        Truck t1 = new Truck("Toyota", "Tacoma", 2019, "goods",true,28000,10);
        Truck t2 = new Truck("Ford", "Ranger", 2022, "equipment",false,30000,10);
        park.addAnItem(t1);
        park.addAnItem(t2);
        MiniVan v1 = new MiniVan("Ford", "Transit", 2020, "goods",true,22000,10);
        MiniVan v2 = new MiniVan("Ram", "ProMaster", 2019, "equipment",false,19000,10);
        park.addAnItem(v1);
        park.addAnItem(v2);
        Tire tire1 = new Tire(10,30,true,390, 20);
        Tire tire2 = new Tire(12,35,false,320,20);
        park.addAnItem(tire1);
        park.addAnItem(tire2);
        return park;
    }

    public void addToCart(String itemString) {
        for (Item i : items) {
            if (i.toString().equals(itemString) && i.getInvQuantity() > 0) { //if this item is in items and is in stock
                i.setInvQuantity(i.getInvQuantity() - 1);
                if (!cart.containsKey(i)) { // first time car gets added to cart
                    cart.put(i, 1);
                } else { //if item is already in cart
                    cart.put(i, cart.get(i) + 1);
                }
            }
        }
    }

    public void removeFromCart(String itemString) {
        HashSet<Item> keys = new HashSet<>(cart.keySet()); // need to add this since we are removing directly from cart at bottom
        for (Item i : keys) {                              // and cant iterate through car.keySet() since we are removing from it
            if ((cart.get(i) + " x " + i.toString()).equals(itemString)) {
                i.setInvQuantity(i.getInvQuantity() + 1);
                if (cart.get(i) > 1) {
                    cart.put(i, cart.get(i) - 1);
                } else {
                    cart.remove(i); //just removes fully from cart if none left in cart
                }
            }
        }
    }

    public ObservableList<String> getCartStrings() { // for cart list view
        ObservableList<String> cartList = FXCollections.observableArrayList();
        for (Item i : cart.keySet()) {
            if(cart.get(i) > 0) {
                cartList.add(cart.get(i) + " x " + i.toString());
            }
        }
        return cartList;
    }

    public ObservableList<String> getItemToString(){ // for park inv
        ObservableList<String> itemsList = FXCollections.observableArrayList();
        for(Item i: items){
            if (i != null && i.getInvQuantity() > 0){ // to show only the items that have stock
                itemsList.add(i.toString());
            }
        }
        return itemsList;
    }

    public ObservableList<String> getXPopularItemStrings() {
        ObservableList<String> popularStrings = FXCollections.observableArrayList();
        for (Item item : getXPopularItems()) { //gets the list of popular items
            popularStrings.add(item.toString());
        }
        return popularStrings; //returns the readable strings of popular items
    }


    public double getCartTotal() {
        double total = 0;
        for (Item i : cart.keySet()) {  // iterates through every item in cart and gets total
            total += cart.get(i) * i.getPrice();
        }
        return total;
    }

    public void completeSale() {
        for (Item i : cart.keySet()) {  // iterates every item in the cart
            revenue += (i.getPrice() * cart.get(i));    //update revenue

            //This if statement is for the popular sales so that it knows how many items to show
            if(!soldItemList.contains(i)) {
                uniqueSales++; //adds 1 to a unique sale, since item is not in soldItemList
                soldItemList.add(i); //adds item to sold item list only if it is not in it
            }
            i.setSoldQuantity(i.getSoldQuantity() + cart.get(i));  // changes its sold quantity
        }
        cartSales++; //adds 1 to cart sales for the $/sale
        cart.clear(); // clears cart so nothing shows in cart listView
    }

    public void reset(){ // just resets all instance variables
        cart.clear(); //nothing should be in cart, so clear it
        revenue = 0;
        uniqueSales = 0;
        cartSales = 0;
        soldItemList.clear();

        //clears the items list to then repopulate with the same items, but they're untouched
        items.clear();
        items.addAll(createPark().items);
    }

}