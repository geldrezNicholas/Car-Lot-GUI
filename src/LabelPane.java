import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class LabelPane extends Pane {
    private Label invLabel;
    private Label cartLabel;
    private Label summaryLabel;
    private Label mostPopLabel;

    public LabelPane(){
        invLabel = new Label("Park Inventory:");
        invLabel.relocate(20,0);

        cartLabel = new Label("Current Cart ($0.0):");
        cartLabel.relocate(300,0);

        summaryLabel = new Label("Park Summary:");
        summaryLabel.relocate(580,0);

        mostPopLabel = new Label("Most Popular Items:");
        mostPopLabel.relocate(580,120);

        getChildren().addAll(invLabel,cartLabel,summaryLabel,mostPopLabel);
    }

    public void setCurrentCart(double total) {
        cartLabel.setText(String.format("Current Cart ($%,.1f):", total));
    }
}
