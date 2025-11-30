import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AutoParkApp extends Application {

    private AutoParkView view;
    private AutoPark model;

    public void start(Stage primaryStage) {

        model = AutoPark.createPark();
        view = new AutoParkView(model);

        view.getParkInv().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                int selectedIndex = view.getParkInv().getSelectionModel().getSelectedIndex();
                //add button will work if something in park inventory is highlighted
                view.getButtonPane().getAddToButton().setDisable(selectedIndex < 0);
            }
        });

        view.getCurrentCart().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                int selectedIndex = view.getCurrentCart().getSelectionModel().getSelectedIndex();
                //same as the add button above, remove will work if something is highlighted in cart
                view.getButtonPane().getRemoveButton().setDisable(selectedIndex < 0);
            }
        });

        view.getButtonPane().getAddToButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String selected = view.getParkInv().getSelectionModel().getSelectedItem();
                model.addToCart(selected);
                view.update();

                //complete is disabled depending on if there are items in the cart
                view.getButtonPane().getAddToButton().setDisable(true);
                //since something is added to cart, complete button will be usable
                view.getButtonPane().getCompButton().setDisable(false);
            }
        });

        view.getButtonPane().getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String selected = view.getCurrentCart().getSelectionModel().getSelectedItem();
                model.removeFromCart(selected);
                view.update();

                //remove set to disabled because when you remove something it un-highlights it
                view.getButtonPane().getRemoveButton().setDisable(true);
                //complete is disabled depending on if there are items in the cart
                view.getButtonPane().getCompButton().setDisable(model.getCart().isEmpty());
            }
        });

        view.getButtonPane().getCompButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                model.completeSale();
                view.update();

                //nothing is left in cart so complete and remove button are disabled
                view.getButtonPane().getCompButton().setDisable(true);
                view.getButtonPane().getRemoveButton().setDisable(true);
            }
        });

        view.getButtonPane().getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                model.reset();
                view.update();

                view.getButtonPane().getAddToButton().setDisable(true);     //resets all buttons to disabled since everything is reset
                view.getButtonPane().getRemoveButton().setDisable(true);
                view.getButtonPane().getCompButton().setDisable(true);
            }
        });

        Scene scene = new Scene(view, 800, 350);
        primaryStage.setTitle(model.getName() + " - Sales and Inventory");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}