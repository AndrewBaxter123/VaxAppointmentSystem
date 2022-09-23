package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    public TabOneController tabOneController; // source = javaFX Multiple Controllers Youtube video

    @FXML
    public TabTwoController tabTwoController;

    @FXML
    public TabThreeController tabThreeController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabOneController.setController(this); // moves data between Controllers if you wanted to do so : source Multiple JavaFX Multiple Controllers - Youtube
        tabTwoController.setController(this);
        tabThreeController.setController(this);

    }

}
