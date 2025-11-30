import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ButtonPane extends Pane {
    private Button addToButton;
    private Button removeButton;
    private Button compButton;
    private Button resetButton;

    public ButtonPane(){
        addToButton = new Button("Add to Cart");
        addToButton.setPrefSize(120,20);
        addToButton.relocate(10,0);
        addToButton.setDisable(true);

        removeButton = new Button("Remove Item");
        removeButton.setPrefSize(120,20);
        removeButton.relocate(240,0);
        removeButton.setDisable(true);

        compButton = new Button("Complete Sale");
        compButton.setPrefSize(120,20);
        compButton.relocate(370,0);
        compButton.setDisable(true);

        resetButton = new Button("Reset Stock");
        resetButton.setPrefSize(120,20);
        resetButton.relocate(540,0);

        getChildren().addAll(addToButton,removeButton,compButton,resetButton);

    }

    public Button getCompButton() {
        return compButton;
    }
    public Button getAddToButton() {
        return addToButton;
    }
    public Button getRemoveButton() {
        return removeButton;
    }
    public Button getResetButton() {
        return resetButton;
    }
}
