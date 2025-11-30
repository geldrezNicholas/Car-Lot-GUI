import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class AutoParkView extends Pane{

    private AutoPark model;
    private ButtonPane buttonPane;
    private LabelPane labelPane;
    private TextPane textPane;
    private ListView<String> parkInv;
    private ListView<String> currentCart;
    private ListView<String> mostPop;
    private Label salesLabel;
    private Label revenueLabel;
    private Label perSaleLabel;

    public AutoParkView(AutoPark model) {

        this.model = model;

        buttonPane = new ButtonPane();
        buttonPane.relocate(70,310);

        labelPane = new LabelPane();
        labelPane.relocate(0,10);

        textPane = new TextPane();
        textPane.relocate(650,40);

        parkInv = new ListView<>();
        parkInv.setPrefSize(270,250);
        parkInv.relocate(20,40);

        currentCart = new ListView<>();
        currentCart.setPrefSize(270,250);
        currentCart.relocate(300,40);

        mostPop = new ListView<>();
        mostPop.setPrefSize(180,140);
        mostPop.relocate(580,150);

        salesLabel = new Label("# Sales:");
        salesLabel.relocate(590, 45);

        revenueLabel = new Label("Revenue:");
        revenueLabel.relocate(590, 75);

        perSaleLabel = new Label("$ / Sale:");
        perSaleLabel.relocate(590, 105);

        parkInv.setItems(AutoPark.createPark().getItemToString());
        mostPop.setItems(AutoPark.createPark().getXPopularItemStrings());//just sets most popular for 3 items

        getChildren().addAll(buttonPane,labelPane,textPane,parkInv,currentCart,mostPop,salesLabel,revenueLabel,perSaleLabel);

    }

    public ButtonPane getButtonPane() {
        return buttonPane;
    }
    public ListView<String> getCurrentCart() {
        return currentCart;
    }
    public ListView<String> getParkInv() {
        return parkInv;
    }

    public void update() {
        parkInv.setItems(model.getItemToString());  //lines 71,72,73 are listviews
        currentCart.setItems(model.getCartStrings());
        mostPop.setItems(model.getXPopularItemStrings());
        labelPane.setCurrentCart(model.getCartTotal()); //update cart total
        textPane.setSales(model.getCartSales()); // updates total sales
        textPane.setRevenue(model.getRevenue()); // updates revenue

        if (model.getCartSales() == 0) { // checks if there are sales
            textPane.setPerSale(0);  // this will set perSale to N/A
        } else {
            textPane.setPerSale(model.getRevenue() / model.getCartSales()); //since there are sales, which actually display $/Sale
        }
    }
}
