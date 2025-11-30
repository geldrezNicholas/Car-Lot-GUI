import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class TextPane extends Pane {
    private TextField salesText;
    private TextField revenueText;
    private TextField perSaleText;

    public TextPane(){
        salesText = new TextField("0");
        salesText.setPrefSize(110,20);
        salesText.relocate(0,0);
        salesText.setEditable(false);

        revenueText = new TextField("0.0");
        revenueText.setPrefSize(110,20);
        revenueText.relocate(0,30);
        revenueText.setEditable(false);

        perSaleText = new TextField("N/A");
        perSaleText.setPrefSize(110,20);
        perSaleText.relocate(0,60);
        perSaleText.setEditable(false);


        getChildren().addAll(salesText,revenueText,perSaleText);
    }

    public void setSales(int sales) {
        salesText.setText(String.valueOf(sales));
    }
    public void setRevenue(double revenue) {
        revenueText.setText(String.format("%.1f", revenue));
    }
    public void setPerSale(double perSale) {
        if (perSale == 0) {
            perSaleText.setText("N/A");
        } else {
            perSaleText.setText(String.format("%.1f", perSale));
        }

    }

}
